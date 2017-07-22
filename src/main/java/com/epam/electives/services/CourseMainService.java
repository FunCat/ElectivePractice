package com.epam.electives.services;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dto.GetCourseRequest;
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

    public PageDto<Course> getPart(GetCourseRequest request){

        PageDto<Course> news = courseDao.findParts(request);
        return news;
    }

    public List<Course> getN(int n){
        return courseDao.findN(n);
    }

    public Course saveOrUpdate(Course course){

        if(course.getId()!=null) {
            course.setUpdateDate(new Date());
        } else {
            course.setCreateDate(new Date());
            course.setUpdateDate(new Date());
            course.setStatus(Course.Status.ACTIVE);
        }
        courseDao.saveOrUpdate(course);
        return course;
    }

    public Course getById(long id){
        return courseDao.findCourseById(id);
    }
}
