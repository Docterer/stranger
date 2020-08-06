package com.scaffold.auth.controller;

import com.scaffold.auth.model.Role;
import com.scaffold.auth.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author danyiran
 * @create 2020/8/5 10:37
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    /**
     * 添加或修改角色
     *
     * @param role 角色对象
     * @return int结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int saveRole(@RequestBody Role role) {
        log.info("添加或修改角色:{}", role);
        int result = roleService.addRole(role);
        return result;
    }
}
