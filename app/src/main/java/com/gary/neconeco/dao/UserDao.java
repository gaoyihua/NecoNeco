package com.gary.neconeco.dao;

import com.gary.neconeco.pojo.User;

public interface UserDao {
    void save(User user);
    void find(int userId);
    void findAll();
    void delete(int userId);
}
