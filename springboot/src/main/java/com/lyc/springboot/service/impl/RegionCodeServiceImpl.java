package com.lyc.springboot.service.impl;

import com.lyc.springboot.entity.RegionCode;
import com.lyc.springboot.mapper.RegionCodeMapper;
import com.lyc.springboot.service.IRegionCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@Service
public class RegionCodeServiceImpl extends ServiceImpl<RegionCodeMapper, RegionCode> implements IRegionCodeService {

    public RegionCode regionCode = new RegionCode();

    @Autowired
    public RegionCodeMapper regionCodeRepository;

    @Override
    public List<String> getProvinces(){
        return regionCodeRepository.findAllProvince();
    }

    @Override
    public List<String> getCities(String province) {
        //System.out.println(province);
        regionCode.setProvince(province);
        //map.put("province",regionCodeRepository.findProvinceCode(province).substring(0, 2));
        //System.out.println(map.get("province"));
        return regionCodeRepository.findCities(province);
    }

    @Override
    public List<String> getCounties(String city) {
        //System.out.println(city);
        regionCode.setCity(city);
        //map.put("city",regionCodeRepository.findCityCode(city).substring(2, 4));
        //System.out.println(map.get("city"));
        return regionCodeRepository.findCounties(city,regionCode.getProvince());
    }
    @Override
    public List<String> getTowns(String county) {
        //System.out.println(county);
        regionCode.setCounty(county);
        //map.put("county",regionCodeRepository.findCountyCode(county,regionCode.getCity()).substring(4, 6));
        //System.out.println(map.get("county"));
        return regionCodeRepository.findTowns(county,regionCode.getCity());
    }
    @Override
    public List<String> getVillages(String town) {
        //System.out.println(town);
        regionCode.setTown(town);
        //System.out.println(regionCode);
        //map.put("town",regionCodeRepository.findTownCode(town,regionCode.getCounty()).substring(6, 9));
        //System.out.println(map.get("town"));
        return regionCodeRepository.findVillages(town,regionCode.getCounty());
    }

}
