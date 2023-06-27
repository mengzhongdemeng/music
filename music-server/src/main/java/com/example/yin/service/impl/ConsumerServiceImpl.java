package com.example.yin.service.impl;

import com.example.yin.dao.ConsumerMapper;
import com.example.yin.domain.Consumer;
import com.example.yin.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import static com.example.yin.constant.Constants.salt;

import java.util.List;
import java.util.UUID;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;


    /**
     * 新增用户
     */
    @Override
    public boolean addUser(Consumer consumer) {

        String passw = consumer.getPassword();
        String hashedPwd1 = DigestUtils.md5DigestAsHex((passw + salt).getBytes());
        consumer.setPassword(hashedPwd1);
        return consumerMapper.insertSelective(consumer) > 0;
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {

        String hashedPwd3 = DigestUtils.md5DigestAsHex((consumer.getPassword() + salt).getBytes());
        consumer.setPassword(hashedPwd3);
        return consumerMapper.updateUserMsg(consumer) > 0;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {

        return consumerMapper.updateUserAvator(consumer) > 0;
    }

    @Override
    public boolean existUser(String username) {

        return consumerMapper.existUsername(username) > 0;
    }

    @Override
    public boolean veritypasswd(String username, String password) {

        String hashedPwd2 = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        return consumerMapper.verifyPassword(username, hashedPwd2) > 0;
    }

    //    删除用户
    @Override
    public boolean deleteUser(Integer id) {
        return consumerMapper.deleteUser(id) > 0;
    }

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.allUser();
    }

    @Override
    public List<Consumer> userOfId(Integer id) {

        return consumerMapper.userOfId(id);
    }

    @Override
    public List<Consumer> loginStatus(String username) {

        return consumerMapper.loginStatus(username);
    }
}
