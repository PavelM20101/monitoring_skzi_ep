package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.Role;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Set<Role> getAllRoles() {
        List<Role> roles = entityManager.createQuery("from Role", Role.class).getResultList();
        return new HashSet<>(roles);
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery(
                "select r from Role r where r.name=:role", Role.class)
                .setParameter("role", role).getSingleResult();
    }

    @Override
    public void createRole(Role role) {
        entityManager.persist(role);
    }
}
