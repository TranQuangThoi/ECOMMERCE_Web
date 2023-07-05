package com.example.penguin.Service;

import com.example.penguin.Entities.UserAccountEntity;
import com.example.penguin.Repository.UserAccRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccService{

    @Autowired
    UserAccRepositoy repositoy;

    public UserAccountEntity saveUser(UserAccountEntity userAccount)
    {
        return repositoy.save(userAccount);
    }

    public UserAccountEntity findNameUser(String username)
    {
        return repositoy.findByName(username);

    }

    public List<UserAccountEntity> findAllUser()
    {
        return repositoy.findAll();
    }

    public void deleteById(int id)
    {

         repositoy.deleteById(id);
    }
    public Optional<UserAccountEntity> findById(int id)
    {
        return repositoy.findById(id);
    }
    public UserAccountEntity findByPhone(String phone)
    {
        return repositoy.findByPhone(phone);
    }
}
