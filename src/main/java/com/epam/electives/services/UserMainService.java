package com.epam.electives.services;

import com.epam.electives.dao.UserDao;
import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional

public class UserMainService {
    @Autowired
    UserDao userDao;

    public List<User> getAll(){
        return userDao.findAll();
    }

    public PageDto<User> getPart(GetEntityRequest request){

        PageDto<User> news = userDao.findParts(request);
        return news;
    }

    public List<User> getN(int n){
        return userDao.findN(n);
    }

    public User saveOrUpdate(User user){
        userDao.saveOrUpdate(user);
        return user;
    }

    public User getByLogin(String login){
        return userDao.findUserByLogin(login);
    }

    public User getByName(String firstname, String middlename, String lastname){
        return userDao.findUserByName(firstname, middlename, lastname);
    }

    public User getById(long id){
        return userDao.findUserById(id);
    }
}
