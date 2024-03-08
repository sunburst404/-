package com.lyc.springboot.service;

import com.lyc.springboot.entity.RegionCode;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
public interface IRegionCodeService extends IService<RegionCode> {

    List<String> getProvinces();
    List<String> getCities(String province);
    List<String> getCounties(String city);
    List<String> getTowns(String county);
    List<String> getVillages(String town);

}
