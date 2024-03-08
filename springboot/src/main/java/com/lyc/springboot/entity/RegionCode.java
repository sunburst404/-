package com.lyc.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@Getter
@Setter
  @TableName("region_code")
public class RegionCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String province;

    private String city;

    private String county;

    private String town;

    private String village;


}
