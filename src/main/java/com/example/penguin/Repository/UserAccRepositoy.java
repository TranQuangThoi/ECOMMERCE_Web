package com.example.penguin.Repository;

import com.example.penguin.Entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccRepositoy extends JpaRepository<UserAccountEntity , Integer> {


    UserAccountEntity findByName(String username);

   @Query("select user from UserAccount user where user.email=:email")
   UserAccountEntity findByEmail(String email);



    @Query("select user FROM UserAccount user WHERE user.phone=:phone")
    UserAccountEntity findByPhone(String phone);
}
