package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    UserMainService userMainService;
    @Autowired
    CourseMainService courseMainService;

    /**
     * Return page with user profile.
     *
     * @param username user login.
     * @return profile page.
     */
    @RequestMapping(value = "/profile")
    public ModelAndView userProfile(Principal username) {
        String login = username.getName();
        if(login != null) {
            UserProfile user = userMainService.getByLogin(login);
            ModelAndView modelAndView = new ModelAndView("profile");
            modelAndView.addObject("userProfile", user);
            return modelAndView;
        }
        return new ModelAndView("login");
    }

    /**
     * Update information about user.
     *
     * @param login object from java.security that represent user login.
     * @param firstname new user firstname.
     * @param lastname new user lastname.
     * @param middlename new user middlename.
     * @param userlogin new user login.
     * @param birthday new user birthday.
     * @return String with result of updating.
     */
    @RequestMapping(value= "/edit_profile", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  userEditProfile(Principal login,
                                        @RequestParam("firstname") String firstname,
                                        @RequestParam("lastname") String lastname,
                                        @RequestParam("middlename") String middlename,
                                        @RequestParam("userlogin") String userlogin,
                                        @RequestParam("birthday") String birthday) {

        if (checkDateFormat(birthday)) return "Неверный формат даты!<br/> дд/мм/гггг";

        UserProfile user = userMainService.getByLogin(login.getName());
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setSurname(middlename);
        user.setLogin(userlogin);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Неправильно указана дата рождения!";
        }
        user.setBirthday(date);
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null)
            return "Обновление прошло успешно!";
        return "Возникли неполадки попробуйте чуть позже!";
    }

    /**
     * Change user password.
     *
     * @param login object from java.security that represent user login.
     * @param nowPassword password that user has got at the moment.
     * @param newPassword new password.
     * @param newPassword2 repeat new password.
     * @return String with result of updating.
     */
    @RequestMapping(value= "/edit_password", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  userEditProfile(Principal login,
                                   @RequestParam("nowpassword") String nowPassword,
                                   @RequestParam("newpassword") String newPassword,
                                   @RequestParam("newpassword2") String newPassword2) {
        UserProfile user = userMainService.getByLogin(login.getName());
        if(!checkPassword(nowPassword, user.getPassword()))
            return "Неверный старый пароль!";

        if (checkPasswordFormat(newPassword)) return "Ошибка в требовании к паролю!<br/> Минимум 8 символов. Обязательно наличие 1 буквы и 1 числа.";

        if(!newPassword.equals(newPassword2))
            return "Новые пароли не совпадают!";

        user.setPassword(bcryptPassword(newPassword));
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null)
            return "Обновление прошло успешно!";
        return "Возникли неполадки попробуйте чуть позже!";
    }


    /**
     * Show page with form registration.
     *
     * @return Page with form registration.
     */
    @RequestMapping("/registration")
    public ModelAndView userRegistration(){
        return new ModelAndView("registration");
    }

    /**
     * Check the validity of the registration data.
     *
     * @param login user login.
     * @param password user password.
     * @param password2 repeat user password.
     * @param firstname user firstname.
     * @param surname user surname.
     * @param lastname user lastname.
     * @param birthday user birthday.
     * @return String with response.
     */
    @RequestMapping(value="/registration_check", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String userRegistrationCheck(@RequestParam("login") String login,
                                         @RequestParam("password") String password,
                                         @RequestParam("password2") String password2,
                                         @RequestParam("firstname") String firstname,
                                         @RequestParam("surname") String surname,
                                         @RequestParam("lastname") String lastname,
                                         @RequestParam("birthday") String birthday) {

        if (checkDateFormat(birthday)) return "Неверный формат даты!<br/> дд/мм/гггг";

        if (checkPasswordFormat(password)) return "Ошибка в требовании к паролю!<br/> Минимум 8 символов. Обязательно наличие 1 буквы и 1 числа.";

        Date dateBirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            return "Неверный формат даты!";
        }
        if(convertedCurrentDate == null)
            return "Неверный формат даты!";

        if(!password.equals(password2)){
            return "Пароли не совпадают!";
        }

        UserProfile user = userMainService.getByLogin(login);
        if(user != null)
            return "Пользователь с таким логином уже существует!";

        user = new UserProfile();
        user.setLogin(login);
        user.setPassword(bcryptPassword(password));
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setSurname(surname);
        user.setBirthday(convertedCurrentDate);
        user.setEnabled(true);
        userMainService.saveOrUpdate(user);
        userMainService.addUserToRole(user);
        return "Успешная регистрация!";
    }

    private boolean checkPasswordFormat(@RequestParam("password") String password) {
        return !Pattern.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}", password);
    }

    private boolean checkDateFormat(@RequestParam("birthday") String birthday) {
        return !Pattern.matches("(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[-/.](19|20)\\d\\d", birthday);
    }

    @RequestMapping(value = "/usercourses")
    public ModelAndView userCourses(Principal username) {
        String login = username.getName();
        if(login != null) {
            UserProfile user = userMainService.getByLogin(login);
            ModelAndView modelAndView = new ModelAndView("usercourses");
            return modelAndView;
        }
        return new ModelAndView("login");
    }
    @ResponseBody
    @RequestMapping(value = "/partuser", method = RequestMethod.POST)
    public PageDto<Course> getUserCourses(Principal login,
                                            @RequestBody GetEntityRequest request){
        UserProfile user = userMainService.getByLogin(login.getName());
        PageDto<Course> courses = userMainService.getPartUser(request,user);
        return courses;
    }
    /**
     * Password hashing in bcrypt script with the strength of 10
     *
     * @param password user password
     * @return hashing password
     */
    private String bcryptPassword(@RequestParam("password") String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = password;
        int i = 0;
        while (i < 10) {
            hashedPassword = passwordEncoder.encode(password);
            i++;
        }
        System.out.println(hashedPassword);
        return hashedPassword;
    }

    /**
     * Check that stored password matches with
     *
     * @param password_plaintext user password
     * @param stored_hash stored password in DB
     * @return true if password matches or false if not
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }

    /**
     * Makes user account disabled and redirect on courses page.
     *
     * @param user user login.
     */
    @RequestMapping(value = "/deleteaccount")
    public void deleteAccount(Principal user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String login = user.getName();
        userMainService.deleteUserByLogin(login);
        SecurityContextHolder.getContext().setAuthentication(null);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/courses");
    }
}
