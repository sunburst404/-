package com.lyc.springboot.service.impl;

import com.lyc.springboot.entity.Longid;
import com.lyc.springboot.mapper.LongidMapper;
import com.lyc.springboot.service.ILongidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@Service
public class LongidServiceImpl extends ServiceImpl<LongidMapper, Longid> implements ILongidService {

}
