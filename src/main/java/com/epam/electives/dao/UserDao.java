package com.epam.electives.dao;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.User;

import java.util.List;

/**
 * Created by Crash on 7/22/17.
 */
public interface UserDao {
    List<User> findAll();

    User findUserById(Long id);

    User findUserByName(String name,String middlename, String lastname);

    User findUserByLogin(String login);

    PageDto<User> findParts(GetEntityRequest request);

    void saveOrUpdate(User user);

    List<User> findN(int n);

}
