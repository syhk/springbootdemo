package com.example.syhk.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO:
 * 用于用户登录实体类
 */
@Data
public class Userlogin implements Serializable {

    private String name="null";

    private String avatarUrl="null";


    private Integer gender=1;



    private String email;

    private String pwd;

    private String token = "null";

}
