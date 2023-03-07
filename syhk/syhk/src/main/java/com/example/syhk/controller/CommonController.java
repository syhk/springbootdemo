package com.example.syhk.controller;

import com.example.syhk.common.ResultData;
import com.example.syhk.common.ReturnCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Slf4j
@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")

public class CommonController {

       private static String  basePath="D:\\work\\springbootdemo\\syhk\\syhk\\src\\main\\resources\\upload\\";

//     文件上传
//    这个 file 名字必须跟前端的对应
//    @PostMapping("/upload")
    public static ResultData<String> uplaod(@RequestBody MultipartFile file) throws IOException {
        if(file == null)
        {
            return ResultData.fail(ReturnCode.RC999.getCode(),"文件上传错误!");
        }
//        如果传递过来的文件不保存下来，本次请求结束就会消失（是一个临时文件），所以转存下来
        log.info("进入了文件"+file.toString());
//        获得原始文件名
        String originalFilename = file.getOriginalFilename();


//        动态截取原始文件的类型 , 也就是后缀名
        String suffix=    originalFilename.substring(originalFilename.indexOf("."));


//      使用 uuid 重新生成文件名，让文件名唯一，不重复
        String fileName = UUID.randomUUID() + suffix;
//
        log.info("生成的文件名为： "+fileName);


//        创建一个目录
        File dir = new File(basePath);

//        判断当前目录是否存在
        if(!dir.exists()){
//            目录不存在，需要创建
            dir.mkdirs();
            log.info("目录不存在，创建了目录");
        }

//        将临时文件转存到指定位置
        file.transferTo(new File(basePath+fileName));

//        把文件名返回去，给页面使用，暂时返回同样的文件名
        return ResultData.success(fileName);
    }
//     文件下载
//   通过二进制 流的形式写回数据
//    @GetMapping("/download")
    public  static void download(String name , HttpServletResponse response) throws IOException {
//        输入流，通过输入流读取文件内容
        FileInputStream inputStream = new FileInputStream(new File(basePath+name));
        log.info("请求下载的文件名为： "+basePath+name);
//       输出流，通过输出流将文件写回浏览器，在浏览器展示
//        输出流不用创建，因为是要写浏览器，直接从响应获取
        ServletOutputStream servletOutputStream = response.getOutputStream();
//        设置响应回去的文件类型
        response.setContentType("image/jpeg");
        int len = 0;
        byte[] bytes = new byte[1024];
        while(true){

                if (!((len = inputStream.read(bytes) )!= -1)) break;

            servletOutputStream.write(bytes,0, len);
            servletOutputStream.flush();
        }
//        关闭流
            servletOutputStream.close();
            inputStream.close();
    }
}