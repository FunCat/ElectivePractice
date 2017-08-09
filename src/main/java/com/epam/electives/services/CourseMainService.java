package com.epam.electives.services;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.UserProfile;
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

        if(course.getId()== null) {
            course.setStatus(Course.Status.ACTIVE);
        }

        courseDao.saveOrUpdate(course);
        return course;
    }

    public Course getById(long id){
        return courseDao.findCourseById(id);
    }

    public List<UserProfile> getStudentsFromCourse(Course course){
        return courseDao.findStudentsByCourse(course);
    }

    public PageDto<Course> getByTeacher(GetEntityRequest request, UserProfile userProfile){
        return courseDao.findByTeacher(request, userProfile);
    }

    public PageDto<Course> getCoursesByTag(String tag, GetEntityRequest request){
        return courseDao.getCoursesByTag(tag, request);
    }
    public PageDto<Group> getPartOfStudentsByCourse(GetEntityRequest request, Long id){
        return courseDao.getPartOfStudentsByCourse(request, id);
    }

}
