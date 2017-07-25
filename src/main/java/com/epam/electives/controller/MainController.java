package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.services.CourseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class MainController  {

    @Autowired
    CourseMainService courseMainService;

    @RequestMapping("/")
    public ModelAndView startPage(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping("/courses.main")
    public ModelAndView courses(@RequestParam int n){
        ModelAndView modelAndView = new ModelAndView("courses.main");
        modelAndView.addObject("courses", courseMainService.getN(n));
        return modelAndView;
    }

    @RequestMapping("/courses")
    public ModelAndView coursesAll(){
        ModelAndView modelAndView = new ModelAndView("courses");
        modelAndView.addObject("listCourses", courseMainService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/coursesPart", method = RequestMethod.POST)
    @ResponseBody
    public PageDto<Course> coursesPage(@RequestParam int page){
        int start = (page - 1) * 10;
        PageDto<Course> pd = courseMainService.getPart(new GetEntityRequest(start, 10));
        if(page * 10 > pd.getRecordsTotal())
            pd = courseMainService.getPart(new GetEntityRequest(start, (int)(pd.getRecordsTotal() - start)));
        else if(page * 10 < 0)
            pd = courseMainService.getPart(new GetEntityRequest(1, 10));

        return pd;
    }

    @RequestMapping(value="/addcourse", method=RequestMethod.POST)
    public ModelAndView addCourse(@RequestParam("courseName") String courseName) {
        Course course = new Course();
        course.setName(courseName);
        courseMainService.saveOrUpdate(course);
        return coursesAll();
    }

    @RequestMapping(value="/newcourse")
    public ModelAndView addCourse() {
        ModelAndView modelAndView = new ModelAndView("newcourse");
        return modelAndView;
    }

    @RequestMapping(value="/editcourse")
    public ModelAndView editCourse(@RequestParam long id) {
        Course course = courseMainService.getById(id);
        ModelAndView modelAndView = new ModelAndView("editcourse");
        modelAndView.addObject("courseName", course.getName());
        modelAndView.addObject("courseId", course.getId());
        return modelAndView;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView updateCourse(@RequestParam long id, @RequestParam String courseName) {
        Course course = courseMainService.getById(id);
        course.setName(courseName);
        course.setUpdateDate(new Date());
        courseMainService.saveOrUpdate(course);
        return coursesAll();
    }
}
