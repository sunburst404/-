package com.lyc.springboot.controller;

import com.lyc.springboot.service.IRegionCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chose")
public class ChoseController {

    // 假设您有一个数据库服务或数据模型来获取省份、城市、县、乡镇、村的数据
    @Resource
    private IRegionCodeService choseService;

    @GetMapping("/getProvinces")
    public ResponseEntity<List<String>> getProvinces() {
        List<String> provinces = choseService.getProvinces(); // 从数据库中获取省份列表
        // System.out.println(provinces);
        return ResponseEntity.ok(provinces);
    }

    @GetMapping("/getCities")
    public ResponseEntity<List<String>> getCities(@RequestParam String province) {
        List<String> cities = choseService.getCities(province);
        //System.out.println(cities);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/getCounties")
    public ResponseEntity<List<String>> getCounties(@RequestParam String city) {
        List<String> counties = choseService.getCounties(city);
        //System.out.println(counties);
        return ResponseEntity.ok(counties);
    }

    @GetMapping("/getTowns")
    public ResponseEntity<List<String>> getTowns(@RequestParam String county) {
        List<String> towns = choseService.getTowns(county);
        //System.out.println(towns);
        return ResponseEntity.ok(towns);
    }

    @GetMapping("/getVillages")
    public ResponseEntity<List<String>> getVillages(@RequestParam String town) {
        List<String> villages = choseService.getVillages(town);
        //System.out.println(villages);
        return ResponseEntity.ok(villages);
    }
}
