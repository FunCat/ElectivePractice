package com.epam.electives.controller;

import com.epam.electives.model.User;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by Crash on 22.07.2017.
 */
@Controller
public class UserController {
    @Autowired
    UserMainService userMainService;

    @RequestMapping(value= "/user/login", method=RequestMethod.POST)
    public ModelAndView userLogin(@RequestParam("login") String login, @RequestParam("password") String password) {
        User user = userMainService.getByLogin(login);
        if(user !=null){
            if(user.getPassword().equals(password)) {
                return userMain();
            }
        }
        return new ModelAndView("user.login"); //todo Заполнить user.login.jsp!!!
    }

    @RequestMapping("/user/main")
    public ModelAndView userMain() {
        ModelAndView modelAndView = new ModelAndView("all");
        //modelAndView.addObject("listCourses", courseMainService.getAll()); //todo Создать метод для выборки курсов
        return modelAndView;
    }

    @RequestMapping("/user/registration")
    public ModelAndView userRegistration(){
        return new ModelAndView("user.registration"); // todo Заполнить user.registration.jsp
    }

    @RequestMapping(value= "/user/registration", method=RequestMethod.POST)
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
        return userMain();
    }
}
