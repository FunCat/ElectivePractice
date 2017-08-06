package com.epam.electives.controller;

import com.epam.electives.model.UserProfile;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

//@ComponentScan(basePackages = {
//        "com.epam.electives.controller",
//        "com.epam.electives.services",
//        "com.epam.electives.dao",
//
//})
//class SpringTestContext{
//}
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringTestContext.class)
public class UserControllerTest {

    UserController userController;

    @Before
    public void init(){
         userController = new UserController();
    }

    @Test
    public void checkPasswordFormat() throws Exception {

        String password = "12345";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "abcd";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "123abcd";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "123AbCd";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "1$23acd";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "1$23a_cd";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "1$2O3Rcd";
        assertEquals(false, userController.checkPasswordFormat(password));

        password = "12345abcd";
        assertEquals(true, userController.checkPasswordFormat(password));

        password = "abcd12345";
        assertEquals(true, userController.checkPasswordFormat(password));

        password = "abcd123Fed5sRW45";
        assertEquals(true, userController.checkPasswordFormat(password));
    }

    @Test
    public void checkDateFormat() throws Exception {

        String date = "05 08 2017";
        assertEquals(false, userController.checkDateFormat(date));

        date = "12.01.2017";
        assertEquals(true, userController.checkDateFormat(date));

        date = "01.12.2017";
        assertEquals(true, userController.checkDateFormat(date));

        date = "31.12.2017";
        assertEquals(true, userController.checkDateFormat(date));

        date = "12.31.2017";
        assertEquals(false, userController.checkDateFormat(date));

        date = "05.08.2017";
        assertEquals(true, userController.checkDateFormat(date));

        date = "05/08/2017";
        assertEquals(true, userController.checkDateFormat(date));

        date = "05-08-2017";
        assertEquals(true, userController.checkDateFormat(date));

        date = "05082017";
        assertEquals(false, userController.checkDateFormat(date));
    }

    @Test
    @Ignore
    public void checkUserRegistration(){
        UserProfile user = new UserProfile();

        user.setLogin("testLogin");
        user.setPassword("test12345");
        user.setFirstname("TestName");
        user.setLastname("TestLastname");
        user.setSurname("TestSurname");
        String birthday = "06/08/2017";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(convertedCurrentDate);
        user.setEnabled(true);

        String result = userController.userRegistrationCheck(user.getLogin(), user.getPassword(), user.getPassword(),
                user.getFirstname(), user.getLastname(), user.getSurname(), birthday, Locale.getDefault());

        assertEquals(result, "Success registration!");

    }
}