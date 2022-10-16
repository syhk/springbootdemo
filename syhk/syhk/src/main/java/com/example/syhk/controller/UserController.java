package com.example.syhk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.syhk.common.ResultData;
import com.example.syhk.common.ReturnCode;
import com.example.syhk.dao.UserMapper;
import com.example.syhk.entity.User;
import com.example.syhk.entity.request.Userlogin;
import com.example.syhk.service.UserService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserMapper userMapper;

//    获取所有
    @GetMapping("/getAll")
    public ResultData getAll(){
        log.info("进行了 getAll....");
        return ResultData.success(userService.list());
    }

//     增加
    @PostMapping("/addUser")
    public ResultData addUser(@RequestBody User user){
        log.info("进行了 addUser....");
//        对密码进行 md5 加密
        user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
        return ResultData.success(userService.save(user));
    }


//    更改
    @PutMapping("/updateUser")
    public ResultData updateUser(@RequestBody User user){
        log.info("进行了 updateUser....");
        return ResultData.success(userService.updateById(user));
    }

//    删除
    @DeleteMapping("/deleteUser/{id}")
    public ResultData deleteUser( @PathVariable  Integer id){
        log.info("进行了 deleteUser....");
        return userService.removeById(id) ? ResultData.success("删除用户成功！") : ResultData.fail(ReturnCode.RC999.getCode(),"没有此用户！！！");
//        return ResultData.success(userService.removeById(id));
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
//   查询名字包含syhk 的所有数据并且 status 值为 1 的所有数据
    @GetMapping("/getsyhk")
    public ResultData<List<User>> getsyhk(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        查询
        queryWrapper.like(User::getName,"syhk").eq(User::getStatus,1);
        return ResultData.success(userService.list(queryWrapper));
// 也可以像这样写
//        return ResultData.success(new LambdaQueryChainWrapper<>(userMapper).like(User::getName,"syhk").eq(User::getStatus,1).list());
    }

    @PostMapping("/login")
    public ResultData loginCheck(HttpServletRequest request, @RequestBody Userlogin user){
        log.info("进行了登录....");
        System.out.println("================="+ user.getEmail());
        System.out.println("==================="+user.getPwd());
        System.out.println("user=="+user.toString());
//        这里要写泛型类型
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getEmail, user.getEmail()).eq(User::getPwd,DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
        User user1=   userService.getOne(queryWrapper); // email 是创建表时指定了唯一性约束，所以只能查出来一个

//        没有这个用户
        if(user1 == null) return ResultData.fail(ReturnCode.RC999.getCode(),"登录失败！！");
        log.info("数据查询到的密码为"+user1.getPwd());

//        查看账号是否禁用
        if(user1.getStatus() == 0){
            return ResultData.fail(ReturnCode.RC999.getCode(),"账号已禁用");
        }
//        登录成功，将用户的 id 存放 Session 并返回
        request.getSession().setAttribute("user",user1.getId());

//        用 userid 生成 token


        return ResultData.success(user1);
    }


//退出登录
    @PostMapping("/logout")
    public  ResultData<String> logout(HttpServletRequest request){
        log.info("进入 退出登录 logout 方法");
//        需要操作 session 需要 request
        request.getSession().removeAttribute("user");
        return ResultData.success("退出成功");
    }



    

}

































