package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.UserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery(
                        "FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.getUser(id);
        if (user == null) {
            throw new NullPointerException();
        }
        entityManager.remove(user);
        entityManager.flush();
    }

    @Override
    public User getUser(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User u LEFT JOIN FETCH u.roles", User.class).getResultList();
    }
}
