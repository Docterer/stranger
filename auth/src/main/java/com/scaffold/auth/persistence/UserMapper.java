package com.scaffold.auth.persistence;

import com.scaffold.auth.model.User;
import java.util.List;

public interface UserMapper {
    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();
}