package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService;
 import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User;

 import java.util.List;


public interface UserService {
    ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User getUserByUsername(String username);

    void deleteUser(Integer id);

    ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User getUser(Integer id);

    void updateUser(User user);

    void createUser(User user);

    List<User> getAllUsers();

    void createOrUpdateUser(User user);
}
