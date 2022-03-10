package com.task_springboot.demo.dao;

import com.task_springboot.demo.model.Role;

public interface RoleDao {
    void save(Role role);
    void delete(Role role);
    Role getById(Long id);
    Role getRoleByName(String rolename);
    Role createRoleIfNotFound(String name, long id);
}
