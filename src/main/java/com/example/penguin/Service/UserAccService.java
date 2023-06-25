package com.example.penguin.Service;

import com.example.penguin.Entities.UserAccountEntity;
import com.example.penguin.Repository.UserAccRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserAccountEntity findByPhone(String phone)
    {
        return repositoy.findByPhone(phone);
    }
}
