package com.sy.zd.service;


import com.sy.zd.model.User;

public interface UserService {
    public User findByUsername(String username) throws Exception;
}
