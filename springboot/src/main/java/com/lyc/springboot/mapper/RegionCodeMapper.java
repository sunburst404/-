package com.lyc.springboot.mapper;

import com.lyc.springboot.entity.RegionCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@Mapper
public interface RegionCodeMapper extends BaseMapper<RegionCode> {

    RegionCode findById(String id);

    @Select("SELECT DISTINCT province FROM region_code")
    List<String> findAllProvince();

    @Select("SELECT id FROM region_code WHERE province LIKE CONCAT('%', #{name}, '%') LIMIT 1")
    String findProvinceCode(@Param("name") String name);

    @Select("SELECT DISTINCT city FROM region_code WHERE province LIKE CONCAT('%', #{province}, '%')")
    List<String> findCities(String province);

    @Select("SELECT DISTINCT county FROM region_code WHERE city LIKE CONCAT('%', #{city}, '%') AND province LIKE CONCAT('%', #{province}, '%') ")
    List<String> findCounties(String city,String province);


    @Select("SELECT DISTINCT town FROM region_code WHERE county LIKE CONCAT('%', #{county}, '%') AND city LIKE CONCAT('%', #{city}, '%')")
    List<String> findTowns(String county,String city);

    @Select("SELECT DISTINCT village FROM region_code WHERE town LIKE CONCAT('%', #{town}, '%') AND county LIKE CONCAT('%', #{county}, '%') ")
    List<String> findVillages(String town,String county);

    @Select("SELECT id FROM region_code WHERE city LIKE CONCAT('%', #{city}, '%') LIMIT 1")
    String findCityCode(String city);

    @Select("SELECT id FROM region_code WHERE county LIKE CONCAT('%', #{county}, '%') AND city LIKE CONCAT('%', #{city}, '%') LIMIT 1")
    String findCountyCode(String county,String city);

    @Select("SELECT id FROM region_code WHERE town LIKE CONCAT('%', #{town}, '%') AND county LIKE CONCAT('%', #{county}, '%') LIMIT 1")
    String findTownCode(String town,String county);

    @Select("SELECT id FROM region_code WHERE village LIKE CONCAT('%', #{village}, '%') AND town LIKE CONCAT('%', #{town}, '%') LIMIT 1")
    String findVillageCode(String village,String town);
}
