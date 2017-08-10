package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import com.epam.electives.support.I18nUtil;
import org.apache.log4j.Logger;
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
    @Autowired
    I18nUtil i18nUtil;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

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
        logger.info("User - " + login + " came on the /profile page!");
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
        String userLogin = login.getName();
        logger.info("User - " + userLogin + " started editing the profile page!");
        if (!checkDateFormat(birthday)) {
            logger.warn("User - " + userLogin + " format of birthday - FAILED!");
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        UserProfile user = userMainService.getByLogin(userLogin);
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
            logger.warn("User - " + userLogin + " convert birthday - FAILED!");
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }
        user.setBirthday(date);
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null) {
            logger.info("User - " + userLogin + " updating profile page - OK!");
            return messageSource.getMessage("SuccessUpdate", null, locale);
        }
        logger.warn("User - " + userLogin + " updating profile page - FAILED!");
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
        String userLogin = login.getName();
        logger.info("User - " + userLogin + " started changing the password!");

        UserProfile user = userMainService.getByLogin(userLogin);

        if(!checkPassword(nowPassword, user.getPassword())) {
            logger.warn("User - " + userLogin + " match password with DB password - FAILED!");
            return messageSource.getMessage("NotMatchNowPassword", null, locale);
        }

        if (!checkPasswordFormat(newPassword)){
            logger.warn("User - " + userLogin + " format of new passwords - FAILED!");
            return messageSource.getMessage("ErrorFormatPassword", null, locale);
        }

        if(!newPassword.equals(newPassword2)) {
            logger.warn("User - " + userLogin + " match new passwords - FAILED!");
            return messageSource.getMessage("NotMatchesPassword", null, locale);
        }

        user.setPassword(bcryptPassword(newPassword));
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null) {
            logger.info("User - " + userLogin + " updating password - OK!");
            return messageSource.getMessage("SuccessUpdate", null, locale);
        }
        logger.warn("User - " + userLogin + " updating password - FAILED!");
        return messageSource.getMessage("ErrorSomething", null, locale);
    }


    /**
     * Show page with form registration.
     *
     * @return Page with form registration.
     */
    @RequestMapping("/registration")
    public ModelAndView userRegistration(){
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        logger.info("New user - came on the /registration page!");
        return modelAndView;
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
                                        @RequestParam("lastname") String lastname,
                                        @RequestParam("surname") String surname,
                                        @RequestParam("birthday") String birthday,
                                        Locale locale) {

        logger.info("New user - started registration!");
        if (!checkDateFormat(birthday)){
            logger.warn("Registration new user - format of birthday - FAILED!");
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        if (!checkPasswordFormat(password)){
            logger.warn("Registration new user - format of password - FAILED!");
            return messageSource.getMessage("ErrorFormatPassword", null, locale);
        }

        Date dateBirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            logger.debug("Registration new user - convert birthday - FAILED!");
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        if(!password.equals(password2)){
            logger.warn("Registration new user - match passwords - FAILED!");
            return messageSource.getMessage("NotMatchesPassword", null, locale);
        }

        UserProfile user = userMainService.getByLogin(login);
        if(user != null) {
            logger.warn("Registration new user - login is already used - FAILED!");
            return messageSource.getMessage("LoginIsUsed", null, locale);
        }

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
        logger.info("Registration new user - registration - OK!");
        return messageSource.getMessage("SuccessRegistration", null, locale);
    }

    /**
     * Check for the correct password format.
     *
     * @param password user password.
     * @return True if password format is correct or False.
     */
    boolean checkPasswordFormat(@RequestParam("password") String password) {
        return Pattern.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}", password);
    }

    /**
     * Check for the correct birthday format.
     *
     * @param birthday user birthday.
     * @return True if birthday format is correct or False.
     */
    boolean checkDateFormat(@RequestParam("birthday") String birthday) {
        return Pattern.matches("(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d", birthday);
    }

    /**
     * Returns page with user courses.
     *
     * @param username object from java.security that represent user login.
     * @return Page with user courses.
     */
    @RequestMapping(value = "/usercourses")
    public ModelAndView userCourses(Principal username, @RequestBody(required = false) GetEntityRequest request) {
        String login = username.getName();
        UserProfile user = userMainService.getByLogin(login);
        ModelAndView modelAndView = new ModelAndView("usercourses");
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        PageDto<Course> courses = getUserCourses(username, request);
        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        logger.info("User - " + login + " came on the /usercourses page!");
        return modelAndView;
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
    String bcryptPassword(@RequestParam("password") String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = password;
        int i = 0;
        while (i < 10) {
            hashedPassword = passwordEncoder.encode(password);
            i++;
        }
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

        if(null == stored_hash)
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
        logger.info("User - " + login + " delete account!");
        userMainService.deleteUserByLogin(login);
        SecurityContextHolder.getContext().setAuthentication(null);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/courses");
    }


}
