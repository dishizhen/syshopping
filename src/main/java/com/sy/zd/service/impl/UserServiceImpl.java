package com.sy.zd.service.impl;

import com.sy.zd.mapper.UserMapper;
import com.sy.zd.model.User;
import com.sy.zd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    public User findByUsername(String username) throws Exception{
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", username);
        List<User> list = mapper.selectByExample(example);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

}
