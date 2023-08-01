package com.example.penguin.Service;

import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Repository.UserAccRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccService {

    @Autowired
    UserAccRepositoy repositoy;

    public UserEntity saveUser(UserEntity userEntity)
    {
        return repositoy.save(userEntity);
    }

    public UserEntity findNameUser(String username)
    {
        return repositoy.findByName(username);

    }

    public List<UserEntity> findAllUser()
    {
        return repositoy.findAll();
    }

    public void deleteById(int id)
    {

         repositoy.deleteById(id);
    }
    public Optional<UserEntity> findById(int id)
    {
        return repositoy.findById(id);
    }
    public UserEntity findByPhone(String phone)
    {
        return repositoy.findByPhone(phone);
    }
}
