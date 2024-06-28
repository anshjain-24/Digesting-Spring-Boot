package project.springboot.journal_app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserServiceInterface {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

