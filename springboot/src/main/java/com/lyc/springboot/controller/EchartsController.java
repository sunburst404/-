package com.lyc.springboot.controller;


import com.lyc.springboot.config.Result;
import com.lyc.springboot.entity.Detailed;
import com.lyc.springboot.service.IDetailedService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.util.CollectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IDetailedService detailedService;
//    @GetMapping("/example")
//    public Result get(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("x", CollUtils.newArrayList());
//        map.put("y", CollUtils.newArrayList());
//    return Result.success();
//    };
    @GetMapping("/pic")
    public Result pic(){
        List<Detailed> list = detailedService.list();
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;

        for(Detailed detailed:list){
            LocalDateTime localDateTime = detailed.getDate().toLocalDateTime();
            Year year = Year.from(localDateTime);
            switch (year.getValue()){
                case 2019: sum1 +=1;break;
                case 2020: sum2 +=1;break;
                case 2021: sum3 +=1;break;
                case 2022: sum4 +=1;break;
                case 2023: sum5 +=1;break;
                default:break;
            }

        }
        List<Integer> resultList = Arrays.asList(sum1, sum2, sum3, sum4, sum5);
        return Result.success(resultList);
    }



}
