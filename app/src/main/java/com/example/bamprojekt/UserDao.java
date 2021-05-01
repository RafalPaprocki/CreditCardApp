package com.example.bamprojekt;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void registerUser(User user);

    @Query("Select * from users where username = (:username)")
    User getUserByUsername(String username);
}
