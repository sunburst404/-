package com.lyc.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.lyc.springboot.service.IUserService;
import com.lyc.springboot.entity.User;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyc
 * @since 2023-12-15
 */
@RestController
@RequestMapping("/user")
        public class UserController {
    
    @Resource
        private IUserService userService;

    @PostMapping
        public Boolean login(@RequestBody User user) {
        return userService.saveOrUpdate(user);
        }
    @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
        }
    @PostMapping("/del/batch")
        public boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
        }


    @GetMapping
        public List<User> findAll() {
        return userService.list();
        }

        @GetMapping("/{id}")
            public User  findOne(@PathVariable Integer id) {
        return userService.getById(id);
        }



        @GetMapping("/page")
            public Page<User> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(defaultValue = "") String username,
                                       @RequestParam(defaultValue = "") String no
        ) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if(!"".equals(username)){
                queryWrapper.like("username",username);
            }
            if(!"".equals(no)){
                queryWrapper.like("no",no);
            }
            queryWrapper.orderByDesc("id");
        return userService.page(new Page<>(pageNum, pageSize),queryWrapper);
        }

        }

    