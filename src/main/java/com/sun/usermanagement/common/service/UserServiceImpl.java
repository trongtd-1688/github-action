package com.sun.usermanagement.common.service;

import com.sun.usermanagement.common.Exception.DataConflictException;
import com.sun.usermanagement.common.Exception.UserManagementException;
import com.sun.usermanagement.common.dao.UserRepository;
import com.sun.usermanagement.common.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserEntity register(UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findUserEntityByUsername(userEntity.getUsername());

        if(user.isPresent()) {
            throw new UserManagementException.Builder(DataConflictException.class)
                    .build("Username existed !");
        }

        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }
}
