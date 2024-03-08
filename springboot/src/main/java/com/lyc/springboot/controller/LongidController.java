package com.lyc.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.lyc.springboot.service.ILongidService;
import com.lyc.springboot.entity.Longid;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@RestController
@RequestMapping("/longid")
        public class LongidController {
    
    @Resource
        private ILongidService longidService;

//    @PostMapping
//        public Boolean save(@RequestBody Longid longid) {
//        return longidService.add(longid);
//        }
    @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
        return longidService.removeById(id);
        }
    @PostMapping("/del/batch")
        public boolean deleteBatch(@RequestBody List<Integer> ids){
        return longidService.removeBatchByIds(ids);
        }


    @GetMapping
        public List<Longid> findAll() {
        return longidService.list();
        }

        @GetMapping("/{id}")
            public Longid  findOne(@PathVariable Integer id) {
        return longidService.getById(id);
        }



        @GetMapping("/page")
            public Page<Longid> findPage(@RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        return longidService.page(new Page<>(pageNum, pageSize));
        }

        }

    