package com.scaffold.auth.persistence;

import com.scaffold.auth.model.UserRole;
import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    UserRole selectByPrimaryKey(String id);

    List<UserRole> selectAll();
}