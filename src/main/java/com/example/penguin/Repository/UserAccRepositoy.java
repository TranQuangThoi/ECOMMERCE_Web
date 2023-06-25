package com.example.penguin.Repository;

import com.example.penguin.Entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccRepositoy extends JpaRepository<UserAccountEntity , Integer> {


    UserAccountEntity findByName(String username);



    @Query("select user FROM UserAccount user WHERE user.phone=:phone")
    UserAccountEntity findByPhone(String phone);
}
