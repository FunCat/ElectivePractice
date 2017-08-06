package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/")
public class MainController  {
    @Autowired
    UserMainService userMainService;
    @Autowired
    CourseMainService courseMainService;

    @RequestMapping
    public ModelAndView start(){
        return courses(null);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView courses(@RequestBody(required = false) GetEntityRequest request){
        ModelAndView modelAndView = new ModelAndView("courses");
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        PageDto<Course> courses = courseMainService.getPart(request);
        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }

    @RequestMapping(value = "/courseinfo", method = RequestMethod.GET)
    public ModelAndView courseinfo(@RequestParam(value = "id") Long id, @RequestBody(required = false) GetEntityRequest request, HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView("courseinfo");
        Course course = courseMainService.getById(id);
        boolean courseContainsUser = false;
        modelAndView.addObject("course",course);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if(!login.equals("anonymousUser")) {
            for (UserProfile user : courseMainService.getStudentsFromCourse(course)) {
                if (user.getLogin().equals(login)) courseContainsUser = true;
            }
        }
        modelAndView.addObject("userAlreadyRegistredForCourse", courseContainsUser);

        if(!httpServletRequest.isUserInRole("ROLE_TEACHER")) return modelAndView;

        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        PageDto<Group> group = courseMainService.getPartOfStudentsByCourse(request, id);  //// TODO: rename table Group
        modelAndView.addObject("group", group.getData());
        modelAndView.addObject("numOfPages",
                (group.getRecordsTotal() % 10 == 0) ?
                        group.getRecordsTotal() / 10 :
                        group.getRecordsTotal() / 10 + 1);

        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public PageDto<Course> mainAllNews(@RequestBody GetEntityRequest request){
        PageDto<Course> courses = courseMainService.getPart(request);
        return courses;
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
