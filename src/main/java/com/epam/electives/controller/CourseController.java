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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    UserController userController;
    @Autowired
    GroupMainService groupMainService;
    @Autowired
    I18nUtil i18nUtil;
    @Autowired
    private MessageSource messageSource;

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
    public PageDto<Course> partByTeacher(Principal login, @RequestBody(required = false) GetEntityRequest request) {
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        return courseMainService.getByTeacher(request, userProfile);
    }


    /**
     * @param id      courseId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/course/students")
    public PageDto<Group> partGroupByTeacher(@RequestParam(value = "id") Long id, @RequestBody(required = false) GetEntityRequest request) {
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Group> group = courseMainService.getPartOfStudentsByCourse(request, id);
        return group;
    }

    @ResponseBody
    @RequestMapping(value = "/editgroup", method = RequestMethod.POST)
    public void editGroup(@RequestBody Group group) {
        groupMainService.editGradeReview(group);
    }


    @RequestMapping(value = "/editcourse")
    public ModelAndView editCourse(Principal login, @RequestParam("courseid") int courseid) {
        ModelAndView modelAndView = new ModelAndView("editcourse");
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        Course course = courseMainService.getById(courseid);
        if (!userProfile.equals(course.getTeacher())) {
            return new ModelAndView("static/404");
        }
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject(course);
        return modelAndView;
    }

    @RequestMapping(value = "/teacher/deletecourse", method = RequestMethod.GET)
    public ModelAndView deleteCourses(Principal login, @RequestParam(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("deletecourse");
        return modelAndView;
    }

    @RequestMapping(value = "/save_course", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String userEditProfile(Principal login,
                                  @RequestParam("idcourse") int idCourse,
                                  @RequestParam("namecourse") String nameCourse,
                                  @RequestParam("startdatecourse") String startDateCourse,
                                  @RequestParam("enddatecourse") String endDateCourse,
                                  @RequestParam("descriptionCourse") String descriptionCourse,
                                  Locale locale) {
        Course course = courseMainService.getById(idCourse);

        if (!userController.checkDateFormat(startDateCourse)) return messageSource.getMessage("ErrorFormatDate", null, locale);
        if (!userController.checkDateFormat(endDateCourse)) return messageSource.getMessage("ErrorFormatDate", null, locale);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = sdf.parse(startDateCourse);
        } catch (ParseException e) {
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        Date endDate = null;
        try {
            endDate = sdf.parse(endDateCourse);
        } catch (ParseException e) {
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        if(endDate.before(startDate))
            return messageSource.getMessage("EndDateBeforeStartDate", null, locale);

        course.setName(nameCourse);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setDescription(descriptionCourse);
        course.setStatus(Course.Status.ACTIVE);

        courseMainService.saveOrUpdate(course);

        return messageSource.getMessage("SuccessUpdateCourse", null, locale);
    }

    @RequestMapping(value = "/addcourse")
    public ModelAndView addCourse() {
        ModelAndView modelAndView = new ModelAndView("addcourse");
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        return modelAndView;
    }

    @RequestMapping(value = "/add_new_course", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String addNewCourse(Principal login,
                               @RequestParam("namecourse") String nameCourse,
                               @RequestParam("startdatecourse") String startDateCourse,
                               @RequestParam("enddatecourse") String endDateCourse,
                               @RequestParam("descriptionCourse") String descriptionCourse,
                               Locale locale) {

        if (!userController.checkDateFormat(startDateCourse)) return messageSource.getMessage("ErrorFormatDate", null, locale);
        if (!userController.checkDateFormat(endDateCourse)) return messageSource.getMessage("ErrorFormatDate", null, locale);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        try {
            startDate = sdf.parse(startDateCourse);
        } catch (ParseException e) {
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        Date endDate = null;
        try {
            endDate = sdf.parse(endDateCourse);
        } catch (ParseException e) {
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        }

        if(endDate.before(startDate))
            return messageSource.getMessage("EndDateBeforeStartDate", null, locale);

        Course course = new Course();
        UserProfile userProfile = userMainService.getByLogin(login.getName());

        course.setName(nameCourse);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setDescription(descriptionCourse);
        course.setTeacher(userProfile);

        courseMainService.saveOrUpdate(course);

        return messageSource.getMessage("SuccessUpdateCourse", null, locale);
    }

    @RequestMapping(value = "teacher")
    public ModelAndView teacherCourses(@RequestParam("id") int teacherId, @RequestBody(required = false) GetEntityRequest request) {
        UserProfile teacher = userMainService.getById(teacherId);
        ModelAndView modelAndView = new ModelAndView("teacher");
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Course> courses = partByTeacher(new Principal() {
            @Override
            public String getName() {
                return teacher.getLogin();
            }
        }, request);

        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/coursestag")
    public PageDto<Course> partByTeacher(@RequestParam("columSorting") int columSorting,
                                         @RequestParam("desc") boolean desc,
                                         @RequestParam("term") String tag,
                                         @RequestBody(required = false) GetEntityRequest request) {
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        return courseMainService.getCoursesByTag(columSorting, desc, tag, request);
    }

}
