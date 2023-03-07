package com.example.syhk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@TableName(value = "video")
public class Video {

    @TableField(value = "id")
    private Integer id;
   @TableField(value = "name")
    private String name;
   @TableField(value = "path")
    private String path;
   @TableField(value = "fm")
    private String fm;
}
