package com.zhongruan.service.impl;

import com.zhongruan.bean.PageInfo;
import com.zhongruan.bean.User;
import com.zhongruan.dao.UserDao;
import com.zhongruan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String username, String password) {
        User user = userDao.findUserByUserName(username);
        if (user!=null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<User> findAll(int currentPage,String username) {
        PageInfo<User> pageInfo=new PageInfo<>();
        pageInfo.setSize(5);

        int tc=userDao.getTotalCount(username);
        pageInfo.setTotalCount(tc);
        int tp=(int)Math.ceil(tc/5.0);
        pageInfo.setTotalPage(tp);
        if(currentPage<1){
            pageInfo.setCurrentPage(1);
        }else if(currentPage>tp){
            pageInfo.setCurrentPage(tp);
        }else {
            pageInfo.setCurrentPage(currentPage);
        }
        //0,5,10
        int start=(pageInfo.getCurrentPage()-1)*5;
        List<User> userList = userDao.findAll(start,username);
        pageInfo.setList(userList);
        return pageInfo;
    }

    /*@Override
    public List<User> findAll() {
        return userDao.findAll();
    }*/

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User selectUserById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        userDao.deleteAll(ids);
    }
}
