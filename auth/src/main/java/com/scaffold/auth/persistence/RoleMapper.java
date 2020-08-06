package com.scaffold.auth.persistence;

import com.scaffold.auth.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    int insertOrUpdate(Role record);

    Role selectByPrimaryKey(String roleId);

    List<Role> selectAll();
}