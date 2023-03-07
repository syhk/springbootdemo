package com.example.syhk.entity.request;

import lombok.Data;

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
public class Articlereq implements Serializable {
    String title;
    String context;
}
