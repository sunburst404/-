package com.lyc.springboot.mapper;

import com.lyc.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyc
 * @since 2023-12-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
