package com.example.syhk.controller;

import com.example.syhk.entity.User;
import com.example.syhk.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

//    获取所有
    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.list();
    }

//     增加
    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user){
           return userService.save(user);
    }

//    更改
    @PutMapping("/updateUser")
    public boolean updateUser(@RequestBody User user){
       return userService.updateById(user);
    }

//    删除
    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        return userService.removeById(id);
    }

//    测试  http://localhost:8800/user/deleteUser?id=2
//    @DeleteMapping("/deleteUser")
//    public boolean deleteUser(@RequestParam(value = "id",required = true) Integer id){
//    return userService.removeById(id);
//    }


}


































