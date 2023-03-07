package com.example.syhk.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

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
public class ReturnArticle implements Serializable {
    private String author;
    private String summary;
    private String title;
    private LocalDate create_time;
    private Integer like_count;
    private Integer remark_count;
    private Integer read_count;
    private String context;
    private Integer article_id;

}
