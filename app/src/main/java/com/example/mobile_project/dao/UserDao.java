package com.example.mobile_project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mobile_project.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void addUser(User user);

    @Delete
    void deleteUSer(User user);

    @Query("SELECT * FROM User")
    List<User> getUserList();



   @Query("SELECT * FROM User WHERE password LIKE :password AND email LIKE :email")
     User find_User(String password,String email);

    @Query("SELECT * FROM User WHERE email LIKE :email")
    User existe_email(String email);

    @Query("UPDATE User SET password =:password Where email LIKE :email ")
    void update_password(String password,String email );






}
