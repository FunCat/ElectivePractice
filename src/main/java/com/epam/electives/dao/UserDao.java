package com.epam.electives.dao;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.UserProfile;

import java.util.List;


/**
 * Created by Crash on 7/22/17.
 */
public interface UserDao {
    List<UserProfile> findAll();

    UserProfile findUserById(Long id);

    UserProfile findUserByName(String name,String middlename, String lastname);

    UserProfile findUserByLogin(String login);

    PageDto<UserProfile> findParts(GetEntityRequest request);

    void saveOrUpdate(UserProfile user);

    List<UserProfile> findN(int n);

}
