package com.epam.electives.dao.impl;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dto.GetEntityRequest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rusamaha on 7/22/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/application-context.xml",})
public class CourseDaoImplTest {
    @Autowired
    CourseDao courseDao;

    @org.junit.Test
    public void findParts() throws Exception {


        GetEntityRequest getCourseRequest = new GetEntityRequest();
        getCourseRequest.setStart(0);
        getCourseRequest.setLength(2);

        System.out.println(courseDao.findParts(getCourseRequest));
    }

}