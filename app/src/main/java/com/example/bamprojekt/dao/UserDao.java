package com.example.bamprojekt.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bamprojekt.models.User;

@Dao
public interface UserDao {
    @Insert()
    void registerUser(User user);

    @Query("Select * from users where username = (:username)")
    User getUserByUsername(String username);
}
