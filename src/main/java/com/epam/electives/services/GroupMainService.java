package com.epam.electives.services;

import com.epam.electives.dao.GroupDao;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GroupMainService {
    @Autowired
    GroupDao groupDao;

    public boolean addUserToCourse(UserProfile user, Course course){
        return groupDao.insertUserIntoGroup(user,course);
    }

    public boolean removeUserFromCourse(UserProfile user, Course course){
        return groupDao.deleteUserFromGroup(user,course);
    }

    public void editGradeReview(Group group){
        groupDao.editGradeReview(group);
    }
}

