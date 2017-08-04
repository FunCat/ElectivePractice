package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import java.util.Locale;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    UserMainService userMainService;
    @Autowired
    CourseMainService courseMainService;

    @Autowired
    private MessageSource messageSource;

    /**
     * Return page with user profile.
     *
     * @param username user login.
     * @return profile page.
     */
    @RequestMapping(value = "/profile")
    public ModelAndView userProfile(Principal username) {
        String login = username.getName();
        UserProfile user = userMainService.getByLogin(login);
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("userProfile", user);
        return modelAndView;
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
                                        @RequestParam("birthday") String birthday,
                                        Locale locale) {

        if (checkDateFormat(birthday)) return messageSource.getMessage("ErrorFormatDate", null, locale);

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
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }
        user.setBirthday(date);
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null)
            return messageSource.getMessage("SuccessUpdate", null, locale);
        return messageSource.getMessage("ErrorSomething", null, locale);
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
                                   @RequestParam("newpassword2") String newPassword2,
                                   Locale locale) {
        UserProfile user = userMainService.getByLogin(login.getName());
        if(!checkPassword(nowPassword, user.getPassword()))
            return messageSource.getMessage("NotMatchNowPassword", null, locale);

        if (checkPasswordFormat(newPassword)) return messageSource.getMessage("ErrorFormatPassword", null, locale);

        if(!newPassword.equals(newPassword2))
            return messageSource.getMessage("NotMatchesPassword", null, locale);

        user.setPassword(bcryptPassword(newPassword));
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null)
            return messageSource.getMessage("SuccessUpdate", null, locale);
        return messageSource.getMessage("ErrorSomething", null, locale);
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
                                        @RequestParam("birthday") String birthday,
                                        Locale locale) {

        if (checkDateFormat(birthday)) return messageSource.getMessage("ErrorFormatDate", null, locale);

        if (checkPasswordFormat(password)) return messageSource.getMessage("ErrorFormatPassword", null, locale);

        Date dateBirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }
        if(convertedCurrentDate == null)
            return messageSource.getMessage("ErrorFormatDate", null, locale);

        if(!password.equals(password2)){
            return messageSource.getMessage("NotMatchesPassword", null, locale);
        }

        UserProfile user = userMainService.getByLogin(login);
        if(user != null)
            return messageSource.getMessage("LoginIsUsed", null, locale);

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
        return messageSource.getMessage("SuccessRegistration", null, locale);
    }

    /**
     * Check for the correct password format.
     *
     * @param password user password.
     * @return True if password format is correct or False.
     */
    private boolean checkPasswordFormat(@RequestParam("password") String password) {
        return !Pattern.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}", password);
    }

    /**
     * Check for the correct birthday format.
     *
     * @param birthday user birthday.
     * @return True if birthday format is correct or False.
     */
    private boolean checkDateFormat(@RequestParam("birthday") String birthday) {
        return !Pattern.matches("(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[-/.](19|20)\\d\\d", birthday);
    }

    /**
     * Returns page with user courses.
     *
     * @param username object from java.security that represent user login.
     * @return Page with user courses.
     */
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

    /**
     * Returns the list of courses in which the recorded user.
     *
     * @param login object from java.security that represent user login.
     * @param request for pagination.
     * @return The list of courses in which the recorded user.
     */
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
