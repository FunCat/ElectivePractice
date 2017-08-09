package com.epam.electives.dao.impl;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dto.GetEntityRequest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by rusamaha on 7/22/17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/appconfig-root.xml"})
public class CourseDaoImplTest {
    @Autowired
    CourseDao courseDao;

    @org.junit.Test
    public void findParts() throws Exception {

        GetEntityRequest getCourseRequest = new GetEntityRequest(0, 10);

        System.out.println(courseDao.findParts(getCourseRequest));
    }

}