package com.example.penguin.Service.ServiceImpl;

import com.example.penguin.Entities.UserEntity;
import com.example.penguin.Repository.UserAccRepositoy;
import com.example.penguin.Service.UserAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccServiceImpl implements UserAccService {

    @Autowired
    UserAccRepositoy repositoy;

    @Override
    public UserEntity saveUser(UserEntity userEntity)
    {
        return repositoy.save(userEntity);
    }

    @Override
    public UserEntity findNameUser(String username)
    {
        return repositoy.findByName(username);

    }

    @Override
    public List<UserEntity> findAllUser()
    {
        return repositoy.findAll();
    }

    @Override
    public void deleteById(int id)
    {

         repositoy.deleteById(id);
    }
    @Override
    public Optional<UserEntity> findById(int id)
    {
        return repositoy.findById(id);
    }
    @Override
    public UserEntity findByPhone(String phone)
    {
        return repositoy.findByPhone(phone);
    }

    @Override
    public Page<UserEntity> findAllUser(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber -1,pageSize);
        return repositoy.findAll(pageable);
    }
}
