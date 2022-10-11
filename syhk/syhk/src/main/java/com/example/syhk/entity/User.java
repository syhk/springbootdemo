package com.example.syhk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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


    private String name;

//    存放用户的头像路径
    @TableField("avatarUrl")
    private String avatarUrl;

//用户性别  1 男 0 女
    private Integer gender;

//    用户的 email
    private String email ;

//    用户状态 1 正常 0 禁用
    private Integer status;

//  用户创建时间
    @TableField("createTime")
    private Date createTime;

//    用户更新时间
    @TableField("updateTime")
    private Date updateTime;


//    用户权限  1 管理员  0 普通用户
    private  Integer role;

}
