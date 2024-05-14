package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository;

import org.springframework.data.repository.CrudRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.Role;

import java.util.Set;

public interface RoleRepository {
    Set<Role> getAllRoles();

    Role getRoleByName(String name);
    void createRole(Role role);
}
