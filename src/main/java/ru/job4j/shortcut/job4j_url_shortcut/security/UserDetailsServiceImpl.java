package ru.job4j.shortcut.job4j_url_shortcut.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.job4j.shortcut.job4j_url_shortcut.domain.UserDAO;
import ru.job4j.shortcut.job4j_url_shortcut.service.UserService;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDAO = userService.findByUsername(username);
        if (userDAO == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userDAO.getUsername(), userDAO.getPassword(), emptyList());
    }
}