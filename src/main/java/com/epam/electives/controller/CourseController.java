package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.GroupMainService;
import com.epam.electives.services.UserMainService;
import com.epam.electives.support.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by rusamaha on 7/31/17.
 */

@Controller
@RequestMapping("/")
public class CourseController {

    @Autowired
    CourseMainService courseMainService;

    @Autowired
    UserMainService userMainService;

    @Autowired
    GroupMainService groupMainService;

    @Autowired
    I18nUtil i18nUtil;

    @RequestMapping(value = "/teacher/managecourses")
    public ModelAndView courses(Principal login, @RequestBody(required = false) GetEntityRequest request) {
        ModelAndView modelAndView = new ModelAndView("managecourses");
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Course> courses = partByTeacher(login, request);
        modelAndView.addObject("courses", courses.getData());

        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());

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
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        return courseMainService.getByTeacher(request, userProfile);
    }


    /**
     *
     * @param id courseId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/course/students")
    public PageDto<Group> partGroupByTeacher(@RequestParam(value = "id") Long id, @RequestBody(required = false) GetEntityRequest request){

        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Group> group = courseMainService.getPartOfStudentsByCourse(request, id);
        return group;
    }

    @ResponseBody
    @RequestMapping(value = "/editgroup", method = RequestMethod.POST)
    public boolean editGroup(@RequestBody Group group){

        return groupMainService.editGradeReview(group);
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

    @RequestMapping(value = "/teacher/deletecourse", method = RequestMethod.GET)
    public ModelAndView deleteCourses(Principal login, @RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletecourse");

        return modelAndView;
    }

}
