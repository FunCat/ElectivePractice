package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.services.CourseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/")
public class MainController  {

    @Autowired
    CourseMainService courseMainService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(Model model){
        return courses(null);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView courses(@RequestBody(required = false) GetEntityRequest request){
        ModelAndView modelAndView = new ModelAndView("courses");
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        PageDto<Course> courses = courseMainService.getPart(request);
//        courseMainService.getAll().size();
        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public PageDto<Course> mainAllNews(@RequestBody GetEntityRequest request){
//        ModelAndView modelAndView = new ModelAndView("course.main");
//        modelAndView.addObject("course", courseMainService.getPart(request));
        PageDto<Course> courses = courseMainService.getPart(request);
        return courses;
        //return modelAndView;
    }


    @RequestMapping(value="/login")
    public ModelAndView login(@RequestParam(value = "error",required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        model.setViewName("login");
        return model;
    }
}
