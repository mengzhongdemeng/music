package com.example.yin.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.yin.dao.AdminMapper;
import com.example.yin.domain.Admin;
import com.example.yin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean veritypasswd(String name, String password) {

        return adminMapper.verifyPassword(name, password)>0?true:false;
    }

    @Override
    public String getToken(Admin admin) {
        Date start = new Date();
        //6小时有效时间
        long currentTime = System.currentTimeMillis() + 1000 * 60 * 60 * 6;
        Date end = new Date(currentTime);

        String token = JWT.create().withAudience(admin.getName())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(admin.getPassword()));

        return token;
    }

    @Override
    public Admin findAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }


}
