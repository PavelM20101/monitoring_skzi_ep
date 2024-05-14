//package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.Role;
//import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User;
//import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.RoleRepository;
//import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.UserRepository;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class InitDbService {
//
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public InitDbService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Transactional
//    @PostConstruct
//    public void init() {
//        Role roleAdmin = new Role();
//        roleAdmin.setName("ROLE_ADMIN");
//        roleRepository.createRole(roleAdmin);
//
//        User admin = new User();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("$2a$12$bF0xtZpvgusPeL9II2O2AeSTj0uhVbvxdTti365nko2bbv"));
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleAdmin);
//        admin.setRoles(roles);
//        userRepository.createUser(admin);
//    }
//}
