package com.epam.electives.dao.impl;

import com.epam.electives.dao.GroupDao;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.GroupId;
import com.epam.electives.model.UserProfile;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


    @Repository
    @Transactional
    public class GroupDaoImpl implements GroupDao {
        private EntityManager entityManager;

        @PersistenceContext
        public void setEntityManager(EntityManager entityManager) {
            this. entityManager = entityManager;
        }

        Session getCurrentSession(){
            return entityManager.unwrap(Session.class);
        }

        @Override
        public boolean insertUserIntoGroup(UserProfile user, Course course){
            Group group = new Group();
            GroupId groupId = new GroupId();
            groupId.setStudent(user);
            groupId.setCourse(course);
            if (getCurrentSession().get(Group.class, groupId)!=null){
                return false;
            }
            group.setGroupId(groupId);
            getCurrentSession().saveOrUpdate(group);
            return true;
        }

        @Override
        public boolean deleteUserFromGroup(UserProfile user, Course course){
            GroupId groupId = new GroupId();
            groupId.setStudent(user);
            groupId.setCourse(course);
            Group group = (Group) getCurrentSession().get(Group.class, groupId);
            if (getCurrentSession().get(Group.class, groupId)==null){
                return false;
            }
            getCurrentSession().delete(group);
            return true;
        }

        @Override
        public void editGradeReview(Group group) {
            getCurrentSession().saveOrUpdate(group);
        }

        @Override
        public Group getGroup(GroupId groupId){
            return (Group) getCurrentSession().get(Group.class, groupId);
        }
    }
