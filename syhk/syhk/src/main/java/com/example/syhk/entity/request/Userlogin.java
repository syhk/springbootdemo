package com.example.syhk.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO:
 * 用于用户登录实体类
 */
@Data
public class Userlogin implements Serializable {

    private String email;

    private String pwd;
}
