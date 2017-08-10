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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    UserMainService userMainService;
    @Autowired
    CourseMainService courseMainService;
    @Autowired
    GroupMainService groupMainService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    I18nUtil i18nUtil;

    @RequestMapping
    public ModelAndView start() {
        return courses(null);
    }

    /**
     * Return page with all courses (main page).
     *
     * @param request uses for pagination (if request is null, get default value).
     * @return courses page.
     */
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ModelAndView courses(@RequestBody(required = false) GetEntityRequest request) {
        ModelAndView modelAndView = new ModelAndView("courses");
        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Course> courses = courseMainService.getCoursesByTag(3, false, "", request);
        modelAndView.addObject("courses", courses.getData());
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        modelAndView.addObject("numOfPages",
                (courses.getRecordsTotal() % 10 == 0) ?
                        courses.getRecordsTotal() / 10 :
                        courses.getRecordsTotal() / 10 + 1);
        return modelAndView;
    }

    /**
     * Return page with info about course.
     *
     * @param id                 course id.
     * @param request            uses for pagination (if request is null, get default value).
     * @param httpServletRequest uses for detect if the user has ROLE_TEACHER.
     * @return courseinfo page.
     */
    @RequestMapping(value = "/courseinfo", method = RequestMethod.GET)
    public ModelAndView courseinfo(@RequestParam(value = "id") Long id, @RequestBody(required = false) GetEntityRequest request, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("courseinfo");
        modelAndView.addObject("i18nKeys", i18nUtil.getKeys());
        Course course = courseMainService.getById(id);
        modelAndView.addObject("course", course);

        boolean courseContainsUser = false;
        boolean isUserCreatorOfTheCourse = false;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();

        if (!login.equals("anonymousUser")) {
            for (UserProfile user : courseMainService.getStudentsFromCourse(course)) {
                if (user.getLogin().equals(login)) courseContainsUser = true;
            }
        }
        modelAndView.addObject("userAlreadyRegistredForCourse", courseContainsUser);
        if (login.equals(course.getTeacher().getLogin())) {
            isUserCreatorOfTheCourse = true;
        }
        modelAndView.addObject("isUserCreatorOfTheCourse", isUserCreatorOfTheCourse);

        if (!httpServletRequest.isUserInRole("ROLE_TEACHER")) return modelAndView;

        if (request == null) {
            request = new GetEntityRequest(0, 10);
        }
        PageDto<Group> group = courseMainService.getPartOfStudentsByCourse(request, id);  //// TODO: rename table Group
        modelAndView.addObject("group", group.getData());
        modelAndView.addObject("numOfPages",
                (group.getRecordsTotal() % 10 == 0) ?
                        group.getRecordsTotal() / 10 :
                        group.getRecordsTotal() / 10 + 1);

        return modelAndView;
    }

    /**
     * Subscribe user for the course.
     *
     * @param login    object from java.security that represent user login.
     * @param courseId course id where user want to subscribe.
     * @return true/false if user is subscribed
     */
    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    @ResponseBody
    public boolean subscribe(Principal login, @RequestParam(value = "courseid") long courseId) {
        boolean success = false;
        Course course = courseMainService.getById(courseId);
        if (course.getTeacher().getLogin().equals(login)) return success;
        if (course.getStatus() == Course.Status.ACTIVE) {
            UserProfile user = userMainService.getByLogin(login.getName());
            success = groupMainService.addUserToCourse(user, course);
        }
        return success;
    }

    /**
     * Unsubscribe user for the course.
     *
     * @param login    object from java.security that represent user login.
     * @param courseId course id where user want to unsubscribe.
     * @return true/false if user is unsubscribed
     */
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    @ResponseBody
    public boolean unsubscribe(Principal login, @RequestParam(value = "courseid") long courseId) {
        boolean success = false;
        Course course = courseMainService.getById(courseId);
        if (course.getStatus() == Course.Status.ACTIVE) {
            UserProfile user = userMainService.getByLogin(login.getName());
            success = groupMainService.removeUserFromCourse(user, course);
        }
        return success;
    }

    /**
     * Return PageDto object that display total amount of courses from DB and part courses.
     *
     * @param request indicates the beginning and the number of courses that need to get from DB.
     * @return PageDto object.
     */
    @ResponseBody
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public PageDto<Course> mainAllNews(@RequestBody GetEntityRequest request) {
        PageDto<Course> courses = courseMainService.getPart(request);
        return courses;
    }

    /**
     * Return login page.
     *
     * @param error  unnecessary param that indicates when failed login.
     * @param locale current locale that used by the user.
     * @return login page.
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, Locale locale) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", messageSource.getMessage("ErrorSignIn", null, locale));
        }

        model.setViewName("login");
        return model;
    }

    /**
     * Return 404 - Page not found!
     *
     * @return page with error 404.
     */
    @RequestMapping(value = "/404")
    public ModelAndView errorPage() {
        return new ModelAndView("static/404");
    }

    /**
     * Return 403 - Access is denied!
     *
     * @param user object from java.security that represent user login.
     * @return page with error 403.
     */
    @RequestMapping(value = "/403")
    public ModelAndView errorAccessDenied(Principal user) {
        ModelAndView modelAndView = new ModelAndView("static/403");
        UserProfile userProfile = userMainService.getByLogin(user.getName());
        modelAndView.addObject(userProfile);
        return modelAndView;
    }
}
