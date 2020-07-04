package com.zhongruan.service;

import com.zhongruan.bean.PageInfo;
import com.zhongruan.bean.User;

import java.util.List;

public interface IUserService {
    boolean login(String username,String password);

    PageInfo<User> findAll(int currentPage,String username);

    void deleteById(int id);

    void add(User user);

    User selectUserById(int id);

    void update(User user);

    void deleteAll(List<Integer> ids);
}
