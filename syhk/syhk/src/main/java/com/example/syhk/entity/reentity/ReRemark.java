package com.example.syhk.entity.reentity;

import com.example.syhk.entity.ArticleRemark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class ReRemark extends ArticleRemark implements Serializable {
//    添加一个 Name
    private String name;

//    添加 url
    private String avatarurl;
}
