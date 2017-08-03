package com.epam.electives.dao;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.internal.EntityManagerImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.invoke.MethodType;
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

    List<String> getUserRoles(String login);

    void addRoleToUser(UserProfile user);

    void deleteUserByLogin(String login);
}
