package com.learning.irctcbackend.dao;

import com.learning.irctcbackend.model.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersDao {
    Users getUserByEmail(@Param("email") String email);

    int insert(@Param("user") Users user);
}
