package de.vignold.vignoldmanagement.service.Impl;

import de.vignold.vignoldmanagement.dao.UserRepository;
import de.vignold.vignoldmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByUserName(username);
        if(user == null){
            throw  new UsernameNotFoundException("Invalid username or password");
        }
        return  new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
