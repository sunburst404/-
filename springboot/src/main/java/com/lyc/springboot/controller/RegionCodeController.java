package com.lyc.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.lyc.springboot.service.IRegionCodeService;
import com.lyc.springboot.entity.RegionCode;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@RestController
@RequestMapping("/region-code")
        public class RegionCodeController {
    
    @Resource
        private IRegionCodeService regionCodeService;

    @PostMapping
        public Boolean save(@RequestBody RegionCode regionCode) {
        return regionCodeService.saveOrUpdate(regionCode);
        }
    @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
        return regionCodeService.removeById(id);
        }
    @PostMapping("/del/batch")
        public boolean deleteBatch(@RequestBody List<Integer> ids){
        return regionCodeService.removeBatchByIds(ids);
        }


    @GetMapping
        public List<RegionCode> findAll() {
        return regionCodeService.list();
        }

        @GetMapping("/{id}")
            public RegionCode  findOne(@PathVariable Integer id) {
        return regionCodeService.getById(id);
        }



        @GetMapping("/page")
            public Page<RegionCode> findPage(@RequestParam Integer pageNum,
            @RequestParam Integer pageSize) {
        return regionCodeService.page(new Page<>(pageNum, pageSize));
        }

        }

    