package com.epam.electives.controller;

import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;
import com.epam.electives.services.CourseMainService;
import com.epam.electives.services.GroupMainService;
import com.epam.electives.services.UserMainService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/appconfig-root.xml"})
public class MainControllerTest {

    @Autowired
    MainController mainController;
    @Autowired
    UserMainService userMainService;
    @Autowired
    CourseMainService courseMainService;
    @Autowired
    GroupMainService groupMainService;

    UserProfile user;
    UserProfile teacher;
    Course course;

    @Before
    public void setUpUser(){
        user = new UserProfile();
        user.setLogin("Dude");
        user.setPassword("test12345");
        user.setFirstname("Dude");
        user.setLastname("Dude");
        user.setSurname("Dude");
        String birthday = "06/06/2017";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(convertedCurrentDate);
        user.setEnabled(true);
        userMainService.registrateUser(user);

        teacher = new UserProfile();
        teacher.setLogin("MainDude");
        teacher.setPassword("test12345");
        teacher.setFirstname("MainDude");
        teacher.setLastname("MainDude");
        teacher.setSurname("MainDude");
        birthday = "08/08/2017";
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        teacher.setBirthday(convertedCurrentDate);
        teacher.setEnabled(true);
        userMainService.registrateUser(teacher);

        course = new Course();
        course.setStatus(Course.Status.ACTIVE);
        course.setDescription("blablabla");
        course.setName("BLA_COURSE");
        course.setTeacher(teacher);
        courseMainService.saveOrUpdate(course);
    }

    @Test
    public void testmethod1() throws Exception {

        boolean result = mainController.subscribe(() -> user.getLogin(), course.getId());
        assertEquals("Subscribe1",true,result);
        result = mainController.subscribe(() -> user.getLogin(), course.getId());
        assertEquals("Subscribe2",false,result);

        result = mainController.unsubscribe(() -> user.getLogin(), course.getId());
        assertEquals("Unsubscribe1",true,result);
        result = mainController.unsubscribe(() -> user.getLogin(), course.getId());
        assertEquals("Unsubscribe2",false,result);

    }

    @After
    public void tearDown(){
        groupMainService.removeUserFromCourse(user,course);
        userMainService.deleteUserByUserProfile(user);
        courseMainService.deleteCourseFromDB(course);
        userMainService.deleteUserByUserProfile(teacher);
    }
}