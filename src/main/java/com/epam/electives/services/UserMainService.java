package com.epam.electives.services;

import com.epam.electives.dao.CourseDao;
import com.epam.electives.dao.UserDao;
import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class UserMainService {
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;

    public List<UserProfile> getAll(){
        return userDao.findAll();
    }

    public PageDto<UserProfile> getPart(GetEntityRequest request){

        PageDto<UserProfile> news = userDao.findParts(request);
        return news;
    }

    public List<UserProfile> getN(int n){
        return userDao.findN(n);
    }

    public UserProfile saveOrUpdate(UserProfile user){
        userDao.saveOrUpdate(user);
        return user;
    }

    public UserProfile getByLogin(String login){
        return userDao.findUserByLogin(login);
    }

    public UserProfile getByName(String name, String surname, String lastname){
        return userDao.findUserByName(name, surname, lastname);
    }

    public UserProfile getById(long id){
        return userDao.findUserById(id);
    }

    public void addUserToRole(UserProfile user){
        userDao.addRoleToUser(user);
    }

    public PageDto<Course> getPartUser(GetEntityRequest request, UserProfile userProfile){

        PageDto<Course> courses = courseDao.findCoursesByStudent(request, userProfile);
        return courses;
    }
    public void deleteUserByLogin(String login){
        userDao.deleteUserByLogin(login);
    }
}
