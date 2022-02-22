package com.sun.usermanagement.common.service;

import com.sun.usermanagement.common.Exception.DataConflictException;
import com.sun.usermanagement.common.Exception.UserManagementException;
import com.sun.usermanagement.common.dao.UserRepository;
import com.sun.usermanagement.common.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {

    public UserEntity register(UserEntity userEntity);

}
