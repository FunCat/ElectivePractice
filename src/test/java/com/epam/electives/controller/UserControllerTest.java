package com.epam.electives.controller;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dao.impl.CourseDaoImpl;
import com.epam.electives.model.UserProfile;
import com.epam.electives.model.UserRole;
import com.epam.electives.services.UserMainService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/appconfig-root.xml"})
public class UserControllerTest {

    @Autowired
    UserController userController;
    @Autowired
    UserMainService userMainService;
    @Autowired
    private MessageSource messageSource;

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

        // Error format date
        String result = userController.userRegistrationCheck(user.getLogin(), user.getPassword(), user.getPassword(),
                user.getFirstname(), user.getLastname(), user.getSurname(), "40/05/1999", Locale.getDefault());
        assertEquals(result, messageSource.getMessage("ErrorFormatDate", null, Locale.getDefault()));

        // Error format password
        result = userController.userRegistrationCheck(user.getLogin(), "123", "123",
                user.getFirstname(), user.getLastname(), user.getSurname(), birthday, Locale.getDefault());
        assertEquals(result, messageSource.getMessage("ErrorFormatPassword", null, Locale.getDefault()));

        // Success Registration
        result = userController.userRegistrationCheck(user.getLogin(), user.getPassword(), user.getPassword(),
                user.getFirstname(), user.getLastname(), user.getSurname(), birthday, Locale.getDefault());
        assertEquals(result, messageSource.getMessage("SuccessRegistration", null, Locale.getDefault()));

        // Check correct registration
        UserProfile userProfile = userMainService.getByLogin("testLogin");
        assertEquals(user.getLogin(), userProfile.getLogin());
        assertEquals(user.getFirstname(), userProfile.getFirstname());
        assertEquals(user.getLastname(), userProfile.getLastname());
        assertEquals(user.getSurname(), userProfile.getSurname());
        assertEquals(user.getOnlyDate(), userProfile.getOnlyDate());

        // Login is used
        result = userController.userRegistrationCheck(user.getLogin(), user.getPassword(), user.getPassword(),
                user.getFirstname(), user.getLastname(), user.getSurname(), birthday, Locale.getDefault());
        assertEquals(result, messageSource.getMessage("LoginIsUsed", null, Locale.getDefault()));

        // Not matches password
        result = userController.userRegistrationCheck("newLogin", user.getPassword(), "",
                user.getFirstname(), user.getLastname(), user.getSurname(), birthday, Locale.getDefault());
        assertEquals(result, messageSource.getMessage("NotMatchesPassword", null, Locale.getDefault()));

        // Clear DB after testing
        userMainService.deleteUserFromDB(user);
    }
}