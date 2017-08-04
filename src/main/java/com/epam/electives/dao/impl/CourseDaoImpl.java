package com.epam.electives.dao.impl;

import com.epam.electives.dao.CourseDao;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.model.Group;
import com.epam.electives.model.GroupId;
import com.epam.electives.model.UserProfile;
import lombok.extern.log4j.Log4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Projections.rowCount;

@Log4j
@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this. entityManager = entityManager;
    }

    Session getCurrentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<Course> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(Course.class);
        return criteria.list();
    }

    @Override
    public List<Course> findN(int n) {
        Criteria criteria = getCurrentSession().createCriteria(Course.class);
        criteria.setMaxResults(n);
        return criteria.list();
    }

    @Override
    public Course findCourseById(Long id){
        return (Course) getCurrentSession().get(Course.class, id);
    }

    @Override
    public PageDto<Course> findByTeacher(GetEntityRequest request, UserProfile userProfile){
        Criteria criteria = getCurrentSession().createCriteria(Course.class);

        criteria.add(Restrictions.eq("teacher", userProfile));

        Long totalRecordsCount = (Long) criteria.setProjection(rowCount()).uniqueResult();

        criteria.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY);

        criteria.addOrder(org.hibernate.criterion.Order.asc("id"));

        if (request.getStart() != null)
            criteria.setFirstResult(request.getStart());

        if (request.getLength() != null)
            criteria.setMaxResults(request.getLength());

        List<Course> records = criteria.list();

        return new PageDto<>(records, totalRecordsCount);
    }

    @Override
    public void saveOrUpdate(Course course) {
        getCurrentSession().saveOrUpdate(course);
    }

    @Override
    public PageDto<Course> findParts(GetEntityRequest request) {
        Criteria criteria = getCurrentSession().createCriteria(Course.class);

        criteria.add(Restrictions.eq("status", Course.Status.ACTIVE));

        Long totalRecordsCount = (Long) criteria.setProjection(rowCount()).uniqueResult();

//        // Сбрасываем, чтобы использовать снова.
        criteria.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY);

        criteria.addOrder(Order.asc("id"));

        if (request.getStart() != null)
            criteria.setFirstResult(request.getStart());

        if (request.getLength() != null)
            criteria.setMaxResults(request.getLength());

        List<Course> records = criteria.list();

        return new PageDto<>(records, totalRecordsCount);
    }

    @Override
    public List<UserProfile> findStudentsByCourse(Course course){
        Criteria criteria = getCurrentSession().createCriteria(Group.class);
        List<Group> groups = criteria.add(Restrictions.eq("groupId.course.id", course.getId())).list();
        List<UserProfile> result = new ArrayList<UserProfile>();
        for(Group group : groups){
            result.add(group.getGroupId().getStudent());
        }
        return result;
    }

    @Override
    public PageDto<Course> findCoursesByStudent(GetEntityRequest request, UserProfile userProfile){
        Criteria criteria = getCurrentSession().createCriteria(Group.class);
        List<Group> groups = criteria.add(Restrictions.eq("groupId.student.id",userProfile.getId())).list();
        List<Course> courses = new ArrayList<>();
        for(Group group : groups){
            courses.add(group.getGroupId().getCourse());
        }
        Criteria criteria2 = getCurrentSession().createCriteria(Course.class);
        for(Course course: courses) {
            criteria2.add(Restrictions.eq("id", course.getId()));
        }
        Long totalRecordsCount = (Long) criteria2.setProjection(rowCount()).uniqueResult();
        criteria2.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY);
        criteria2.addOrder(org.hibernate.criterion.Order.asc("id"));
        if (request.getStart() != null)
            criteria2.setFirstResult(request.getStart());
        if (request.getLength() != null)
            criteria2.setMaxResults(request.getLength());
        List<Course> result = criteria2.list();
        return new PageDto<>(result,totalRecordsCount);
    }
}