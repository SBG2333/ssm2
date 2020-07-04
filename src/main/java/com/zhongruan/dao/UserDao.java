package com.zhongruan.dao;

import com.zhongruan.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User findUserByUserName(String username);

    List<User> findAll(@Param("start") int start,@Param("username") String username);

    void deleteById(int id);

    void add(User user);

    User selectById(int id);

    void update(User user);

    int getTotalCount(@Param("username")String username);


    void deleteAll(@Param("ids") List<Integer> ids);
}
