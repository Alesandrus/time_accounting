package ru.treejoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.treejoy.domain.CustomUserDetail;
import ru.treejoy.domain.User;
import ru.treejoy.repository.UsersRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByLogin(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Login not found!"));
        return new CustomUserDetail(user);
    }
}
