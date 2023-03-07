package com.example.syhk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */


@Data
@NoArgsConstructor
@TableName("user")
public class User  implements Serializable {
//实体类要记得实现序列化的这个接口

//    主键自增
    @TableId(type = IdType.AUTO)
    private Integer id;

//    用户名称
    private String name="null";

//    存放用户的头像路径
    @TableField("avatarUrl")
    private String avatarUrl="null";

//用户性别  1 男 0 女
    private Integer gender=1;

//    用户的 email
    private String email ;

//    用户的密码
    private String pwd;

//    用户状态 1 正常 0 禁用
    private Integer status=1;

//  用户创建时间
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createTime;

//    用户更新时间
    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDate updateTime;

//    用户权限  1 管理员  0 普通用户
    private  Integer role=0;

//    签名
    private  String qm="";

//    生日
    private  LocalDate birthday;

}
