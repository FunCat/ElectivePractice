package com.epam.electives.services;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional

public class CourseMainService {
    @Autowired
    CourseDao courseDao;

    public List<Course> getAll(){
        return courseDao.findAll();
    }

    public PageDto<Course> getPart(GetEntityRequest request){

        PageDto<Course> courses = courseDao.findParts(request);
        return courses;
    }

    public List<Course> getN(int n){
        return courseDao.findN(n);
    }

    public Course saveOrUpdate(Course course){

        if(course.getId()!=null) {
            course.setStartDate(new Date());
        } else {
            course.setEndDate(new Date());
            course.setStatus(Course.Status.ACTIVE);
        }
        courseDao.saveOrUpdate(course);
        return course;
    }
}
