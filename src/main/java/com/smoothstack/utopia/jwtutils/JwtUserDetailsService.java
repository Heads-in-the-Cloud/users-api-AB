package com.smoothstack.utopia.jwtutils;

import com.smoothstack.utopia.entity.UserEntity;
import com.smoothstack.utopia.dao.UserDao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> user = userDao.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
   }

}

