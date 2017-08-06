package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.UserMainService;
import com.epam.electives.support.CustomBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

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
    ApplicationContext applicationContext; //TODO: make Autowired CustomBundleMessageSource instead taking it from appCtx
//    @Qualifier("messageSource")
//    CustomBundleMessageSource customBundleMessageSource;

    @RequestMapping(value = "/teacher/managecourses")
    public ModelAndView courses(Principal login, @RequestBody(required = false) GetEntityRequest request) {
        ModelAndView modelAndView = new ModelAndView("managecourses");
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Course> courses = partByTeacher(login, request);
        modelAndView.addObject("courses", courses.getData());

//        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
//        applicationContext.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");

        CustomBundleMessageSource customBundleMessageSource = (CustomBundleMessageSource) applicationContext.getBean("messageSource");

        List<String> i18Keys = customBundleMessageSource.getKeys(LocaleContextHolder.getLocale());
        modelAndView.addObject("i18nKeys", i18Keys);

        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/teacher/part")
    public PageDto<Course> partByTeacher(Principal login, @RequestBody(required = false) GetEntityRequest request) {
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        return courseMainService.getByTeacher(request, userProfile);
    }

    @RequestMapping(value = "/teacher/deletecourse", method = RequestMethod.GET)
    public ModelAndView deleteCourses(Principal login, @RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletecourse");

        return modelAndView;
    }

//    @RequestMapping(value = "/teacher/courseinfo", method = RequestMethod.GET)
//    public ModelAndView teacherCourseInfo(Principal login, @RequestParam(value = "id") Long id, @RequestBody(required = false) GetEntityRequest request) {
//
//        ModelAndView modelAndView = new ModelAndView("courseinfo");
//        if(request == null) {
//            request = new GetEntityRequest(0,10);
//        }
//        PageDto<Group> group = courseMainService.getPartOfStudentsByCourse(request, id);  //// TODO: rename table Group
//        modelAndView.addObject("group", group.getData());
//        modelAndView.addObject("numOfPages",
//                (group.getRecordsTotal() % 10 == 0) ?
//                        group.getRecordsTotal() / 10 :
//                        group.getRecordsTotal() / 10 + 1);
//
//        return modelAndView;
//    }
}
