package com.lyc.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lyc
 * @since 2023-12-15
 */
@Getter
@Setter
  public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 账号
     */
      private String no;

      /**
     * 名字
     */
      private String username;

      /**
     * 密码
     */
      private String password;

    private Integer age;

      /**
     * 性别
     */
      private String sex;

      /**
     * 电话
     */
      private Integer phone;

      /**
     * ⻆⾊ 0超级管理员，1管理员，2普通账号
     */
      private Integer roleId;

    private String isvalid;

    private LocalDateTime createTime;

    private String avatarUrl;


}
