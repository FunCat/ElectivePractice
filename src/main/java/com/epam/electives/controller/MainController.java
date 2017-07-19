package com.epam.electives.controller;

import com.epam.electives.dto.GetCourseRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.services.CourseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController  {

    @Autowired
    CourseMainService courseMainService;

    @RequestMapping("/courses.main")
    public ModelAndView courses(@RequestParam int n){
        ModelAndView modelAndView = new ModelAndView("courses.main");
        modelAndView.addObject("news", courseMainService.getN(n));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/news.all", method = RequestMethod.POST)
    public PageDto<Course> mainAllNews(@RequestBody GetCourseRequest request){
//        ModelAndView modelAndView = new ModelAndView("course.main");
//        modelAndView.addObject("course", courseMainService.getPart(request));
        PageDto<Course> news = courseMainService.getPart(request);
        return news;
        //return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public Course addNews(@RequestBody Course news){

        // temp appId, later take it from HttpRequest request
//        ModelAndView modelAndView = new ModelAndView("addNews");
//        modelAndView.addObject("news", newsMainService.saveOrUpdate(news));
        courseMainService.saveOrUpdate(news);
        return news;
        //return modelAndView;
    }
}
