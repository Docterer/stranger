package com.scaffold.auth.service;

import com.scaffold.auth.model.Role;
import com.scaffold.auth.model.User;
import com.scaffold.auth.persistence.RoleMapper;
import com.scaffold.common.util.UniqueCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author danyiran
 * @create 2020/8/5 09:16
 */
@Service
public class RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleMapper roleMapper;

    private static final User user = (User) SecurityUtils.getSubject().getPrincipal();

    @Transactional(rollbackFor = RuntimeException.class)
    public int addRole(Role role) {
        log.info("添加角色:{}", role);
        role.setRoleId(UniqueCodeUtil.initUniqueCode());
        role.setCreateUserId(user.getUserId());
        role.setCreateTime(new Date());
        int result = roleMapper.insertOrUpdate(role);
        log.info("添加角色结果:{}", result);
        return result;
    }
}
