package com.epam.electives.dao;

import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.GroupId;
import com.epam.electives.model.UserProfile;

public interface GroupDao {
    boolean insertUserIntoGroup(UserProfile user, Course course);
    boolean deleteUserFromGroup(UserProfile user, Course course);
    void editGradeReview(Group group);
    Group getGroup(GroupId groupId);
}
