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
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController  {

    @Autowired
    CourseMainService courseMainService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView start(Model model){
        return courses(null);
    }

    @RequestMapping(value = "/courses.main", method = RequestMethod.GET)
    public ModelAndView courses(@RequestBody(required = false) GetEntityRequest request){
        ModelAndView modelAndView = new ModelAndView("courses.main");
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

    @RequestMapping("/courses")
    public ModelAndView coursesAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        ModelAndView modelAndView;
        if(!currentPrincipalName.equals("anonymousUser"))
            modelAndView = new ModelAndView("user/main");
        else
            modelAndView = new ModelAndView("courses");
        modelAndView.addObject("listCourses", courseMainService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/coursesPage", method = RequestMethod.POST)
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

    @ResponseBody
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public PageDto<Course> mainAllNews(@RequestBody GetEntityRequest request){
//        ModelAndView modelAndView = new ModelAndView("course.main");
//        modelAndView.addObject("course", courseMainService.getPart(request));
        PageDto<Course> courses = courseMainService.getPart(request);
        return courses;
        //return modelAndView;
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

//    @RequestMapping(value="/editcourse2", method=RequestMethod.POST)
//    public Course editCourse2(@RequestBody GetEntityRequest request) {
//        Course course = courseMainService.getById(id);
////        ModelAndView modelAndView = new ModelAndView("editcourse");
////        modelAndView.addObject("courseName", course.getName());
////        modelAndView.addObject("courseId", course.getId());
//        return course;
//    }



    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView updateCourse(@RequestParam long id, @RequestParam String courseName) {
        Course course = courseMainService.getById(id);
        course.setName(courseName);
        course.setStartDate(new Date());
        courseMainService.saveOrUpdate(course);
        return coursesAll();
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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(@RequestBody(required = false) GetEntityRequest request){
        ModelAndView modelAndView = new ModelAndView("home");
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

    @RequestMapping(value="/teacher/main")
    public ModelAndView getTeacherPage() {
        ModelAndView modelAndView = new ModelAndView("teacher/main");
        return modelAndView;
    }

}
