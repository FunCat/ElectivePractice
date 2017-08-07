package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class CourseController {

    @Autowired
    CourseMainService courseMainService;

    @Autowired
    UserMainService userMainService;

    @RequestMapping(value = "/teacher/managecourses")
    public ModelAndView courses(Principal login, @RequestBody(required = false) GetEntityRequest request){
        ModelAndView modelAndView = new ModelAndView("managecourses");
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        PageDto<Course> courses = partByTeacher(login, request);
        modelAndView.addObject("courses", courses.getData());

        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/teacher/part")
    public PageDto<Course> partByTeacher(Principal login, @RequestBody(required = false) GetEntityRequest request){
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        return courseMainService.getByTeacher(request, userProfile);
    }

    @RequestMapping(value = "/editcourse")
    public ModelAndView editCourse(Principal login, @RequestParam("courseid") int courseid){
        ModelAndView modelAndView = new ModelAndView("editcourse");
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        Course course = courseMainService.getById(courseid);

        if(!userProfile.equals(course.getTeacher())){
            return new ModelAndView("404");
        }
        modelAndView.addObject(course);
        return modelAndView;
    }

    @RequestMapping(value = "teacher")
    public ModelAndView teacherCourses(@RequestParam("id") int teacherId, @RequestBody(required = false) GetEntityRequest request){
        UserProfile teacher = userMainService.getById(teacherId);
        ModelAndView modelAndView = new ModelAndView("teacher");
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        PageDto<Course> courses = partByTeacher(new Principal() {
            @Override
            public String getName() {
                return teacher.getLogin();
            }
        }, request);
        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("teacher", teacher);

        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/coursestag")
    public PageDto<Course> partByTeacher(@RequestParam("term") String tag, @RequestBody(required = false) GetEntityRequest request){
        if(request == null) {
            request = new GetEntityRequest(0,10);
        }
        return courseMainService.getCoursesByTag(tag, request);
    }

}
