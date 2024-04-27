package com.learning.irctcbackend.dao;

import com.learning.irctcbackend.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

public interface UserRoleDao {
    List<UserRole> getRolesByUserUuid(@Param("userUuid") UUID userUuid);

    int insert(@Param("userRole") UserRole userRole);
}
