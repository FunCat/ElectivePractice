package com.epam.electives.controller;

import com.epam.electives.model.User;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    CourseMainService courseMainService;

    @RequestMapping("/main")
    public ModelAndView userMain() {
        ModelAndView modelAndView = new ModelAndView("user/main");
        modelAndView.addObject("listCourses", courseMainService.getAll());
        return modelAndView;
    }

    @RequestMapping("/home")
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView("user/home");
        return modelAndView;
    }

    @RequestMapping(value= "/login")
    public ModelAndView userLogin() {
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

    @RequestMapping(value = "/cabinet")
    public ModelAndView userProfile(@ModelAttribute("login") String login) {
        if(login != null) {
            User user = userMainService.getByLogin(login);
            ModelAndView modelAndView = new ModelAndView("user/cabinet");
            modelAndView.addObject("userFirstname", user.getFirstname());
            modelAndView.addObject("userLastname", user.getLastname());
            modelAndView.addObject("userMiddlename", user.getMiddlename());
            modelAndView.addObject("userLogin", user.getLogin());
            modelAndView.addObject("userBirthday", user.getOnlyDate());
            return modelAndView;
        }
        return new ModelAndView("user/login");
    }

    @RequestMapping(value= "/edit_profile", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String  userEditProfile(@ModelAttribute("login") String login,
                                        @RequestParam("firstname") String firstname,
                                        @RequestParam("lastname") String lastname,
                                        @RequestParam("middlename") String middlename,
                                        @RequestParam("userlogin") String userlogin,
                                        @RequestParam("birthday") String birthday) {
        User user = userMainService.getByLogin(login);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMiddlename(middlename);
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
        User checking = userMainService.saveOrUpdate(user);
        if(checking != null)
            return "Обновление прошло успешно!";
        return "Возникли неполадки попробуйте чуть позже!";
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
