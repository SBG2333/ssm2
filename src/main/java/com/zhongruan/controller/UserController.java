package com.zhongruan.controller;


import com.zhongruan.bean.PageInfo;
import com.zhongruan.bean.User;
import com.zhongruan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login.do")
    public ModelAndView login(User user, HttpSession session){
        boolean flag = userService.login(user.getUsername(), user.getPassword());
        ModelAndView modelAndView=new ModelAndView();
        if(flag){
            session.setAttribute("user",user);
            modelAndView.setViewName("main");
        }else {
            modelAndView.setViewName("../failer");
        }
       return modelAndView;

    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int currentPage,String username,@RequestParam(defaultValue = "0")int type,HttpSession session){
        //当搜索的时候
      /*  if(username!=null && username!=""){
            session.setAttribute("searchname",username);
        }else if(type==0) {
            username= (String) session.getAttribute("searchname");
        }*/
        if(type==1){
            session.setAttribute("searchname",username);
        }else {
            username= (String) session.getAttribute("searchname");
        }



        PageInfo<User> pageInfo=userService.findAll(currentPage,username);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/deleteById.do")
    public String delete(int id){
        userService.deleteById(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/add.do")
    public String add(User user){
        userService.add(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("toUpdate.do")
    public ModelAndView toUpdate(int id){
        User user=userService.selectUserById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("user-update");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("/update.do")
    public String update(User user){
        userService.update(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("deleteAll.do")
    public String deleteAll(String userList){
        String[] strs = userList.split(",");
        List<Integer> ids=new ArrayList<>();
        for(String s:strs){
            ids.add(Integer.parseInt(s));
        }
        userService.deleteAll(ids);

        return "redirect:findAll.do";
    }







}
