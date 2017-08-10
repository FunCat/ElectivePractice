package com.epam.electives.dao.impl;

import com.epam.electives.dao.UserDao;
import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.UserProfile;
import com.epam.electives.model.UserRole;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.criterion.Projections.rowCount;


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
    public List<UserProfile> findAll() {
        Criteria criteria = getCurrentSession().createCriteria(UserProfile.class);
        return criteria.list();
    }

    @Override
    public UserProfile findUserById(Long id) {
        return (UserProfile) getCurrentSession().get(UserProfile.class, id);
    }

    @Override
    public UserProfile findUserByName(String name, String surname, String lastname) {
        Criteria criteria = getCurrentSession().createCriteria(UserProfile.class);
        return (UserProfile)criteria.add(Restrictions.eq("lastname",lastname))
                .add(Restrictions.eq("name",name))
                .add(Restrictions.eq("surname",surname))
                .uniqueResult();
    }

    @Override
    public UserProfile findUserByLogin(String login) {
        Criteria UserProfileCriteria = getCurrentSession().createCriteria(UserProfile.class);
        UserProfileCriteria.add(Restrictions.eq("login", login));
        return (UserProfile) UserProfileCriteria.uniqueResult();
    }

    @Override
    public List<UserProfile> findN(int n) {
        Criteria criteria = getCurrentSession().createCriteria(UserProfile.class);
        criteria.setMaxResults(n);
        return criteria.list();
    }

    @Override
    public void saveOrUpdate(UserProfile UserProfile) {
        getCurrentSession().saveOrUpdate(UserProfile);
    }

    @Override
    public PageDto<UserProfile> findParts(GetEntityRequest request) {
        Criteria criteria = getCurrentSession().createCriteria(UserProfile.class);

        Long totalRecordsCount = (Long) criteria.setProjection(rowCount()).uniqueResult();

        // Сбрасываем, чтобы использовать снова.
        criteria.setProjection(null)
                .setResultTransformer(Criteria.ROOT_ENTITY);

        criteria.addOrder(org.hibernate.criterion.Order.desc("createDate"));

        if (request.getStart() != null)
            criteria.setFirstResult(request.getStart());

        if (request.getLength() != null)
            criteria.setMaxResults(request.getLength());

        List<UserProfile> records = criteria.list();

        return new PageDto<>(records, totalRecordsCount);
    }

    @Override
    public void addRoleToUser(UserProfile user) {
        UserRole role = new UserRole();
        role.setUser(user);
        role.setAuthority("ROLE_USER");
        getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public void deleteUserByLogin(String login) {
        UserProfile userProfile = findUserByLogin(login);
        userProfile.setEnabled(false);
        saveOrUpdate(userProfile);
    }

    @Override
    public void deleteUserByUserProfile(UserProfile userProfile){
        Criteria criteria = getCurrentSession().createCriteria(UserRole.class);
        UserRole userRole = (UserRole) criteria.add(Restrictions.eq("user.id", userProfile.getId())).uniqueResult();
        getCurrentSession().delete(userRole);
        Criteria criteria1 = getCurrentSession().createCriteria(UserProfile.class);
        UserProfile userProfile1 = (UserProfile) criteria1.add(Restrictions.eq("id", userProfile.getId())).uniqueResult();
        getCurrentSession().delete(userProfile1);
    }
}