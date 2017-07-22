package com.epam.electives.dao.impl;

import com.epam.electives.dao.UserDao;
import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.criterion.Projections.rowCount;

@Log4j
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this. entityManager = entityManager;
    }

    Session getCurrentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<User> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        return criteria.list();
    }

    @Override
    public List<User> findN(int n) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        criteria.setMaxResults(n);
        return criteria.list();
    }

    @Override
    public User findUserById(Long id){
        return (User) getCurrentSession().get(User.class, id);
    }

    @Override
    public User findUserByName(String firstname, String middlename, String lastname) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);
        return (User)criteria.add(Restrictions.eq("lastname",lastname))
                .add(Restrictions.eq("firstname",firstname))
                .add(Restrictions.eq("middlename",middlename))
                .uniqueResult();
    }

    @Override
    public User findUserByLogin(String login) {
        return (User) getCurrentSession().get(User.class, login);
    }

    @Override
    public void saveOrUpdate(User user) {
        getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public PageDto<User> findParts(GetEntityRequest request) {
        Criteria criteria = getCurrentSession().createCriteria(User.class);

        Long totalRecordsCount = (Long) criteria.setProjection(rowCount()).uniqueResult();

        // Сбрасываем, чтобы использовать снова.
        criteria.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY);

        criteria.addOrder(org.hibernate.criterion.Order.desc("createDate"));

        if (request.getStart() != null)
            criteria.setFirstResult(request.getStart());

        if (request.getLength() != null)
            criteria.setMaxResults(request.getLength());

        List<User> records = criteria.list();

        return new PageDto<>(records, totalRecordsCount);
    }

}