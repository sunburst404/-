package com.lyc.springboot.service.impl;

import com.lyc.springboot.entity.User;
import com.lyc.springboot.mapper.UserMapper;
import com.lyc.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyc
 * @since 2023-12-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
