package com.epam.electives.controller;

import com.epam.electives.model.User;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by Crash on 22.07.2017.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("login")
public class UserController {
    @Autowired
    UserMainService userMainService;

    @RequestMapping(value= "/login")
    public ModelAndView userLogin() {
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "/profile")
    public ModelAndView userProfile(@ModelAttribute("login") String login) {
        if(login != null)
            return new ModelAndView("courses");
        return new ModelAndView("user/login");
    }

    @RequestMapping(value= "/login_check", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String userLoginCheck(@RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 ModelMap model) {
        User user = userMainService.getByLogin(login);
        if(user != null){
            if(user.getPassword().equals(password)) {
                model.put("login", login);
                return "Успешная авторизация!";
            }
            else return "Неверный логин или пароль!";
        }
        return "Такого пользователя не существует!";
    }

    @RequestMapping("/main")
    public ModelAndView userMain() {
        ModelAndView modelAndView = new ModelAndView("user/main");
        //modelAndView.addObject("listCourses", courseMainService.getAll());
        return modelAndView;
    }

    @RequestMapping("/registration")
    public ModelAndView userRegistration(){
        return new ModelAndView("user.registration");
    }

    @RequestMapping(value= "/registration", method=RequestMethod.POST)
    public ModelAndView userRegistration(@RequestParam("login") String login,
                                         @RequestParam("password") String password,
                                         @RequestParam("password2") String password2,
                                         @RequestParam("firstname") String firstname,
                                         @RequestParam("middlename") String middlename,
                                         @RequestParam("lastname") String lastname,
                                         @RequestParam("birthday")Date birthday) {

        if(login == "" || password=="" || password2=="" ||
                firstname=="" || middlename=="" || lastname=="" || birthday ==null){
            return userRegistration();
        }

        if(!password.equals(password2)){
            return userRegistration();
        }

        User user = userMainService.getByLogin(login);
        if(user !=null){
            return userRegistration();
        }
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMiddlename(middlename);
        user.setBirthday(birthday);
        userMainService.saveOrUpdate(user);
        return userMain();
    }
}
