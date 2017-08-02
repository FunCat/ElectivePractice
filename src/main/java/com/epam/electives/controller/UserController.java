package com.epam.electives.controller;

import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Crash on 22.07.2017.
 */
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
            modelAndView.addObject("userName", user.getName());
            modelAndView.addObject("userLastname", user.getLastname());
            modelAndView.addObject("userSurname", user.getSurname());
            modelAndView.addObject("userLogin", user.getLogin());
            modelAndView.addObject("userBirthday", user.getOnlyDate());
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
        UserProfile user = userMainService.getByLogin(login.getName());
        user.setName(firstname);
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
        if(!user.getPassword().equals(nowPassword))
            return "Неверный старый пароль!";
        if(!newPassword.equals(newPassword2))
            return "Новые пароли не совпадают!";

        user.setPassword(newPassword);
        UserProfile checking = userMainService.saveOrUpdate(user);
        if(checking != null)
            return "Обновление прошло успешно!";
        return "Возникли неполадки попробуйте чуть позже!";
    }



    @RequestMapping("/registration")
    public ModelAndView userRegistration(){
        return new ModelAndView("registration");
    }

    @RequestMapping(value="/registration_check", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String userRegistrationCheck(@RequestParam("login") String login,
                                         @RequestParam("password") String password,
                                         @RequestParam("password2") String password2,
                                         @RequestParam("firstname") String firstname,
                                         @RequestParam("surname") String surname,
                                         @RequestParam("lastname") String lastname,
                                         @RequestParam("birthday") String birthday) {

        Date dateBirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            return "Неверный формат даты!";
        }
        if(convertedCurrentDate == null) return "Неверный формат даты!";

        if(!password.equals(password2)){
            return "Пароли не совпадают!";
        }

        UserProfile user = userMainService.getByLogin(login);
        if(user != null){
            return "Пользователь с таким логином уже существует!";
        }
        user = new UserProfile();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(firstname);
        user.setLastname(lastname);
        user.setSurname(surname);
        user.setBirthday(convertedCurrentDate);
        user.setEnabled(true);
        userMainService.saveOrUpdate(user);
        userMainService.addUserToRole(user);
        return "Успешная регистрация!";
    }
}
