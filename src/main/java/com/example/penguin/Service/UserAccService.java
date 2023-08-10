package com.example.penguin.Service;

import com.example.penguin.Entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserAccService {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity findNameUser(String username);

    List<UserEntity> findAllUser();
    void deleteById(int id);

    Optional<UserEntity> findById(int id);

    UserEntity findByPhone(String phone);
}
