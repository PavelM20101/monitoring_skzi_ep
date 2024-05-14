package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService;

import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();

    Role getRoleByName(String name);
}
