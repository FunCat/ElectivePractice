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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    @Value("${pgn.elements}")
    private Integer pgElNum;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    /**
     * Return page where teacher can manage his courses.
     *
     * @param login   object from java.security that represent user login.
     * @param request uses for pagination (if request is null, get default value).
     * @return page with teacher courses.
     */
    @RequestMapping(value = "/teacher/managecourses")
    public ModelAndView courses(Principal login, @RequestBody(required = false) GetEntityRequest request) {

        ModelAndView modelAndView = new ModelAndView("managecourses");
        if (request == null) {
            request = new GetEntityRequest(0, pgElNum);
        }
        PageDto<Course> courses = partByTeacher(login, request);
        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % pgElNum == 0) ?
                        courses.getRecordsTotal() / pgElNum :
                        courses.getRecordsTotal() / pgElNum + 1);
        logger.info("User - " + login + " came on the /mangagecourse page!");
        return modelAndView;
    }

    /**
     * Return PageDto object that display total amount of courses from DB and part of courses that managed by the teacher.
     *
     * @param login   object from java.security that represent teacher login.
     * @param request indicates the beginning and the number of courses that need to get from DB.
     * @return PageDto object.
     */
    @ResponseBody
    @RequestMapping(value = "/teacher/part")
    public PageDto<Course> partByTeacher(Principal login, @RequestBody(required = false) GetEntityRequest request) {
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        if (request == null) {
            request = new GetEntityRequest(0, pgElNum);
        }
        logger.info("Teacher's PageDto object - " + login + " pagination part!");
        return courseMainService.getByTeacher(request, userProfile);
    }


    /**
     * Return PageDto object that display total amount of courses from DB and part of group that managed by the teacher and recorded by users.
     *
     * @param id      course id that is looking by the teacher.
     * @param request uses for pagination (if request is null, get default value).
     * @return PageDto object.
     */
    @ResponseBody
    @RequestMapping(value = "/course/students")
    public PageDto<Group> partGroupByTeacher(@RequestParam(value = "id") Long id, @RequestBody(required = false) GetEntityRequest request) {
        if (request == null) {
            request = new GetEntityRequest(0, pgElNum);
        }
        PageDto<Group> group = courseMainService.getPartOfStudentsByCourse(request, id);
        logger.info("Teachers's Students PageDto object created!");
        return group;
    }

    /**
     * Update information about course group by the teacher.
     *
     * @param group group that need to update.
     */
    @ResponseBody
    @RequestMapping(value = "/editgroup", method = RequestMethod.POST)
    public void editGroup(@RequestBody Group group) {
        logger.info("Update information about course group by the teacher!");
        if(group.getGroupId().getCourse().getStatus() == Course.Status.ARCHIVE) {
            groupMainService.editGradeReview(group);
        }
    }

    /**
     * Return editcourse page where teacher can update his course.
     *
     * @param login    object from java.security that represent teacher login.
     * @param courseid course id that teacher wants to update.
     * @return editcourse page.
     */
    @RequestMapping(value = "/editcourse")
    public ModelAndView editCourse(Principal login, @RequestParam("courseid") int courseid) {
        ModelAndView modelAndView = new ModelAndView("editcourse");
        UserProfile userProfile = userMainService.getByLogin(login.getName());
        Course course = courseMainService.getById(courseid);
        if (!userProfile.equals(course.getTeacher())) {
            logger.warn("Editcourse page where teacher" + login + "can update his course loaded.");
            return new ModelAndView("static/404");
        }
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject(course);
        logger.info("Editcourse page where teacher" + login + "can update his course loaded.");
        return modelAndView;
    }

    /**
     * Return deletecourse page where teacher need to confirm delete operation.
     *
     * @param login object from java.security that represent teacher login.
     * @param id    course id that teacher wants to delete.
     * @return deletecourse page.
     */
    @RequestMapping(value = "/teacher/deletecourse", method = RequestMethod.GET)
    public ModelAndView deleteCourses(Principal login, @RequestParam(value = "courseid") int id) {
        ModelAndView modelAndView = new ModelAndView("deletecourse");
        logger.info("Update information about course group by the teacher" + login +"!");
        Course course = courseMainService.getById(id);
        modelAndView.addObject("course", course);
        return modelAndView;
    }

    /**
     * Change course status on ARCHIVE status.
     *
     * @param user                object from java.security that represent teacher login.
     * @param courseid            course id that teacher wants to archive.
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "/teacher/сompletecourse", method = RequestMethod.GET)
    public void completeCourse(Principal user, @RequestParam(value = "courseid") int courseid,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws IOException {
        Course course = courseMainService.getById(courseid);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        if (login.equals(course.getTeacher().getLogin())) {
            course.setStatus(Course.Status.ARCHIVE);
            courseMainService.saveOrUpdate(course);
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/courseinfo?id=" + courseid);
    }


    @RequestMapping(value = "/teacher/cancelcourse", method = RequestMethod.GET)
    public void cancelCourse(Principal user, @RequestParam(value = "courseid") int courseid,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse) throws IOException {
        Course course = courseMainService.getById(courseid);
// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = user.getName();
        if (login.equals(course.getTeacher().getLogin())) {
            course.setStatus(Course.Status.CANCELED);
            courseMainService.saveOrUpdate(course);
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/courseinfo?id=" + courseid);
    }

    /**
     * Update information about course.
     *
     * @param login             object from java.security that represent teacher login.
     * @param idCourse          course id that teacher wants to update.
     * @param nameCourse        name of course.
     * @param startDateCourse   start date of course.
     * @param endDateCourse     end date of course.
     * @param descriptionCourse description of course.
     * @param locale            current locale that used by the user.
     * @return String with success text or error text.
     */
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

        if (!userController.checkDateFormat(startDateCourse))
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        if (!userController.checkDateFormat(endDateCourse))
            return messageSource.getMessage("ErrorFormatDate", null, locale);

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

        if (endDate.before(startDate))
            return messageSource.getMessage("EndDateBeforeStartDate", null, locale);

        course.setName(nameCourse);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setDescription(descriptionCourse);

        courseMainService.saveOrUpdate(course);

        return messageSource.getMessage("SuccessUpdateCourse", null, locale);
    }

    /**
     * Return addcourse page where teacher can create a new course.
     *
     * @return addcourse page.
     */
    @RequestMapping(value = "/addcourse")
    public ModelAndView addCourse() {
        ModelAndView modelAndView = new ModelAndView("addcourse");
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        return modelAndView;
    }

    /**
     * Save information about new course.
     *
     * @param login             object from java.security that represent teacher login.
     * @param nameCourse        name of course.
     * @param startDateCourse   start date of course.
     * @param endDateCourse     end date of course.
     * @param descriptionCourse description of course.
     * @param locale            current locale that used by the user.
     * @return String with success text or error text.
     */
    @RequestMapping(value = "/add_new_course", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String addNewCourse(Principal login,
                               @RequestParam("namecourse") String nameCourse,
                               @RequestParam("startdatecourse") String startDateCourse,
                               @RequestParam("enddatecourse") String endDateCourse,
                               @RequestParam("descriptionCourse") String descriptionCourse,
                               Locale locale) {

        if (!userController.checkDateFormat(startDateCourse))
            return messageSource.getMessage("ErrorFormatDate", null, locale);
        if (!userController.checkDateFormat(endDateCourse))
            return messageSource.getMessage("ErrorFormatDate", null, locale);

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

        if (endDate.before(startDate))
            return messageSource.getMessage("EndDateBeforeStartDate", null, locale);

        Course course = new Course();
        UserProfile userProfile = userMainService.getByLogin(login.getName());

        course.setName(nameCourse);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setDescription(descriptionCourse);
        course.setTeacher(userProfile);
        course.setStatus(Course.Status.ACTIVE);

        courseMainService.saveOrUpdate(course);

        return messageSource.getMessage("SuccessUpdateCourse", null, locale);
    }

    /**
     * Return page with public information about teacher (name and courses).
     *
     * @param teacherId teacher id which we want to look.
     * @param request   uses for pagination (if request is null, get default value).
     * @return public page with information about teacher.
     */
    @RequestMapping(value = "/teacher")
    public ModelAndView teacherCourses(@RequestParam("id") int teacherId, @RequestBody(required = false) GetEntityRequest request) {
        UserProfile teacher = userMainService.getById(teacherId);
        ModelAndView modelAndView = new ModelAndView("teacher");
        if (request == null) {
            request = new GetEntityRequest(0, pgElNum);
        }
        PageDto<Course> courses = courseMainService.getActiveCoursesByTeacher(request, (long) teacherId);

        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % pgElNum == 0) ?
                        courses.getRecordsTotal() / pgElNum :
                        courses.getRecordsTotal() / pgElNum + 1);
        return modelAndView;
    }

    /**
     * Return PageDto object that display total amount of courses from DB and part of courses which created by teacher
     *
     * @param teacherId teacher id which we want to look.
     * @param request   uses for pagination (if request is null, get default value).
     * @return PageDto object.
     */
    @ResponseBody
    @RequestMapping(value = "/teacher/courses")
    public PageDto<Course> teacherPublicPage(@RequestParam("id") int teacherId, @RequestBody(required = false) GetEntityRequest request) {
        if (request == null) {
            request = new GetEntityRequest(0, pgElNum);
        }
        return courseMainService.getActiveCoursesByTeacher(request, (long) teacherId);
    }

    /**
     * Return PageDto object that display total amount of courses from DB and part of courses which match with the tag search.
     *
     * @param columSorting the number of column by which is sorted.
     * @param desc         boolean variable that shows do we need to sort by DESC order (true - DESC / false - ASC).
     * @param tag          tag search.
     * @param request      uses for pagination (if request is null, get default value).
     * @return PageDto object.
     */
    @ResponseBody
    @RequestMapping(value = "/coursestag")
    public PageDto<Course> partByTeacher(@RequestParam("columSorting") int columSorting,
                                         @RequestParam("desc") boolean desc,
                                         @RequestParam("term") String tag,
                                         @RequestBody(required = false) GetEntityRequest request) {
        if (request == null) {
            request = new GetEntityRequest(0, pgElNum);
        }
        return courseMainService.getCoursesByTag(columSorting, desc, tag, request);
    }

}
