package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository;


import org.springframework.data.relational.core.sql.In;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User;

import java.util.List;

public interface UserRepository {
    User getUserByUsername(String username);


    void deleteUser(Integer id);

    User getUser(Integer id);

    void updateUser(User user);

    void createUser(User user);

    List<User> getAllUsers();
}
