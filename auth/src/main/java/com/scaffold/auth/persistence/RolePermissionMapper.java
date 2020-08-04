package com.scaffold.auth.persistence;

import com.scaffold.auth.model.RolePermission;
import java.util.List;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    List<RolePermission> selectAll();
}