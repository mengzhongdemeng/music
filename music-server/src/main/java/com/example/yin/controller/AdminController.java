package com.example.yin.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.yin.domain.Admin;
import com.example.yin.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Controller
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

//    判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpServletResponse response,HttpSession session){
        JSONObject jsonObject = new JSONObject();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        boolean res = adminService.veritypasswd(name, password);
        Admin admin=new Admin(name,password);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");
            String token=adminService.getToken(admin);
            session.setAttribute("name", name);
            session.setAttribute("admin",admin);
            jsonObject.put("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            return jsonObject;
        }

    }


}
