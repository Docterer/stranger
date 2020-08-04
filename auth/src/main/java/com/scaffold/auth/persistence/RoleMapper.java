package com.scaffold.auth.persistence;

import com.scaffold.auth.model.Role;
import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    Role selectByPrimaryKey(String roleId);

    List<Role> selectAll();
}