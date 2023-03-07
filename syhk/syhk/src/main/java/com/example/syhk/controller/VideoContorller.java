package com.example.syhk.controller;

import com.example.syhk.common.ResultData;
import com.example.syhk.entity.Video;
import com.example.syhk.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@RestController
@RequestMapping("/video")
@Slf4j
public class VideoContorller {
    @Autowired
    private VideoService videoService;
    private final String base="http://localhost:8800/user/downheadpor?name=";

//    获取所有视频信息
    @GetMapping("/getall")
    public ResultData<List<Video>> getall(){
//        处理下数据再返回
       List<Video> videos = videoService.list();
       log.info(String.valueOf(videos.size()));
       for(Video video:videos)
       {
           video.setPath(base+video.getPath());
           video.setFm(base+video.getFm());
       }
        return  ResultData.success(videos);
    }
}
