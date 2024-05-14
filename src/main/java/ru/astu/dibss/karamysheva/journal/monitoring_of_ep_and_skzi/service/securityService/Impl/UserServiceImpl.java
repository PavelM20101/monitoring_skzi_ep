package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.entities.securityEntities.User;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository.securityRepository.UserRepository;
import ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.service.securityService.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        repository.deleteUser(id);
    }

    @Override
    public User getUser(Integer id) {
        return repository.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        repository.updateUser(user);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        repository.createUser(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    @Transactional
    public void createOrUpdateUser(User user) {
        if (user.getId() == 0) {
            this.createUser(user);
        } else {
            this.updateUser(user);
        }
    }
}
