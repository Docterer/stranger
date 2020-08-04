package com.scaffold.auth.persistence;

import com.scaffold.auth.model.Permission;
import java.util.List;

public interface PermissionMapper {
    int insert(Permission record);

    Permission selectByPrimaryKey(String permissionId);

    List<Permission> selectAll();
}