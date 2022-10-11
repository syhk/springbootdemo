package com.example.syhk.controller;

import com.example.syhk.common.ResultData;
import com.example.syhk.entity.User;
import com.example.syhk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    获取所有
    @GetMapping("/getAll")
    public ResultData<List<User>> getAll(){
        log.info("进行了 getAll....");
        return ResultData.success(userService.list());
    }

//     增加
    @PostMapping("/addUser")
    public ResultData<Boolean> addUser(@RequestBody User user){
        log.info("进行了 addUser....");
        return ResultData.success(userService.save(user));
    }

//    更改
    @PutMapping("/updateUser")
    public ResultData<Boolean> updateUser(@RequestBody User user){
        log.info("进行了 updateUser....");
        return ResultData.success(userService.updateById(user));
    }

//    删除
    @DeleteMapping("/deleteUser/{id}")
    public ResultData<Boolean> deleteUser(@PathVariable Integer id){
        log.info("进行了 deleteUser....");
        return ResultData.success(userService.removeById(id));
    }

//    测试  http://localhost:8800/user/deleteUser?id=2
//    @DeleteMapping("/deleteUser")
//    public boolean deleteUser(@RequestParam(value = "id",required = true) Integer id){
//    return userService.removeById(id);
//    }

    @GetMapping("/wrong")
    public int error(){
        int i = 9/0;
        return i;
    }

}


































