package com.example.yin.service;

import com.example.yin.domain.Admin;

public interface AdminService {

    boolean veritypasswd(String name, String password);

    //获取token
    String getToken(Admin admin);

    Admin findAdminById(Integer adminId);
}
