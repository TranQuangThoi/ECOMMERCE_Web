package com.example.penguin.Repository;

import com.example.penguin.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccRepositoy extends JpaRepository<UserEntity, Integer> {


    UserEntity findByName(String username);

   @Query("select user from user user where user.email=:email")
   UserEntity findByEmail(String email);



    @Query("select user FROM user user WHERE user.phone=:phone")
    UserEntity findByPhone(String phone);
}
