package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.Role;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.RoleRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService.RoleService;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Role> getAllRoles() {
        return repository.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return repository.getRoleByName(name);
    }
}
