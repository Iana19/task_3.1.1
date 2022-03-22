package com.task_springboot.demo.dao;

import com.task_springboot.demo.model.Role;
import com.task_springboot.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        List<User> resultList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return resultList;
    }

    @Override
    public void save(User user) {
        User managed = entityManager.merge(user);
        entityManager.persist(managed);
    }

    @Override
    public void delete(User user) {
        User managed = entityManager.merge(user);
        entityManager.remove(managed);
    }

    @Override
    //21/03
    public User getById(Long id) {

//        try {
//            List<Role> roles = entityManager.createQuery("SELECT r FROM Role r LEFT JOIN users_roles" +
//                    " ON roles_id = role.id WHERE r.id = :id", Role.class)
//                    .setParameter("id", id)
//                    .getResultList();
//
//            User user = entityManager.createQuery("SELECT u FROM User u LEFT JOIN users_roles" +
//                    " ON users.id = roles.user_id WHERE u.id = :id", User.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//
//            user.setRoles((Set<Role>) roles);
//
//            return user;
//        } catch (NoResultException e) {
//            return null;
//        }

        return (User) entityManager.createNativeQuery("SELECT r FROM Role r LEFT JOIN users_roles" +
                    " ON roles_id = role.id WHERE r.id = :id", Role.class);

    }
    //        return entityManager.find(User.class, id);

    @Override
    public User getUserByLogin(String login) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u where u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }
}