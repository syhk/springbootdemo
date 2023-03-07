package com.example.syhk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.syhk.Utils.JWTUtils;
import com.example.syhk.Utils.UpsetString;
import com.example.syhk.common.ResultData;
import com.example.syhk.common.ReturnCode;
import com.example.syhk.dao.UserMapper;
import com.example.syhk.entity.User;
import com.example.syhk.entity.request.Userlogin;
import com.example.syhk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")
@Api(tags = "用户数据接口")
public class UserController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
//    获取所有
//    TODO: 接口功能：返回所有用户并统计用户所写的博客数,以及用户所发布评论数
//    先直接返回
    @GetMapping("/getAll")
    public ResultData getAll(){
        return ResultData.success(userService.list());
    }
//     增加
    @ApiOperation(value = "增加用户")
   @ApiImplicitParam(name = "user",value = "用户对象",required = true,dataType = "User")
    @PostMapping("/addUser")
    public ResultData addUser(@RequestBody User user){
        log.info("进行了 addUser....");
//        对密码进行 md5 加密
        user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
        return ResultData.success(userService.save(user));
    }

//    注册
    @ApiOperation(value = "注册接口",notes = "注册接口")
    @ApiImplicitParam(paramType = "body",name = "userlogin",value = "用户信息",required = true,dataType = "User")
    @PostMapping("/register")
    public ResultData register(@RequestBody Userlogin userlogin)
    {
        User user = new User();
        user.setName(userlogin.getName());
        user.setEmail(userlogin.getEmail());
        user.setPwd(userlogin.getPwd());
        user.setAvatarUrl("muoren.jpg");
        user.setBirthday(LocalDate.now());
        user.setQm("人生的每一条路都是风景！(默认签名)");
        log.info("进行注册中...");
        log.info("注册的用户为： "+user.toString());
//        判断下是否已经注册过
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getEmail,user.getEmail());
        long tempuser=userService.count(lambdaQueryWrapper);
        if(tempuser != 0 )
        {
//            已经注册过
            return ResultData.fail(ReturnCode.RC200.getCode(),"该账号已经注册");
        }
        user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
         if(userService.save(user)){
             return  ResultData.success(user);
         }
         return ResultData.fail(ReturnCode.RC200.getCode(),"注册失败");
    }


//    更改
    @ApiOperation(value = "更改用户信息",notes = "更改用户信息")
    @ApiImplicitParam(name = "user",value = "用户对象",required = true,dataType = "User")
    @PutMapping("/updateUser")
    public ResultData updateUser(@RequestBody User user){
        log.info("进行了 updateUser....");
        return ResultData.success(userService.updateById(user));
    }
//    删除
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String")
    @ApiOperation(value = "删除用户",notes = "删除用户")
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


    @CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")
    @PostMapping("/loginredis")
    @ApiOperation(value = "登录接口",notes = "登录接口")
    @ApiImplicitParam(paramType = "body",name = "userlogin",value = "用户信息",required = true,dataType = "User")
    public ResultData loginredis(@RequestBody Userlogin userlogin,HttpSession session,HttpServletResponse response) throws IOException {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmail,userlogin.getEmail()).eq(User::getPwd,DigestUtils.md5DigestAsHex(userlogin.getPwd().getBytes()));
        User user= userService.getOne(lambdaQueryWrapper);
        if(user == null) return ResultData.fail(ReturnCode.RC999.getCode(),"该用户没有注册或密码错误！！！");
        if(user.getStatus() == 0) return ResultData.fail(ReturnCode.RC999.getCode(),"该账号已禁用");
//        允许登录 存放 session id
        session.removeAttribute("user");
        session.setAttribute("user",user.getId());
        log.info("存储到 session 中的用户 id 为： "+session.getAttribute("user"));
        redisTemplate.opsForValue().set("userid", String.valueOf(user.getId()));
        redisTemplate.opsForValue().set("loginUser:"+user.getId(),session.getId());
        log.info("登录接口中 存储到 redis 的 数据为 ："+redisTemplate.opsForValue().get("loginUser:"+user.getId()));
        Userlogin resUser = new Userlogin();
        resUser.setEmail(user.getEmail());
        resUser.setName(user.getName());
        resUser.setGender(user.getGender());
        resUser.setAvatarUrl(user.getAvatarUrl());
        resUser.setPwd("null");
        resUser.setRole(user.getRole());
        String token  = JWTUtils.createToken(session.getId());
        response.setHeader("token",token);
        log.info("id:"+session.getAttribute("user"));
        log.info(" token为 : {}",token);
        resUser.setToken(token);
        return ResultData.success(resUser);

    }


    @PostMapping("/login")
    @ApiOperation(value = "登录接口",notes = "登录接口")
    @ApiImplicitParam(paramType = "body",name = "userlogin",value = "用户信息",required = true,dataType = "User")
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
        HttpSession session = request.getSession();
        log.info("登录接口当前请求的 session id 为："+session.getId());
//        request.getSession(true);    // 这个有参数： true: 获取不到的时候就自动创建一个  false：获取不到的时候，不自动创建返回 null
       session.setAttribute("user",user1.getId());
       log.info("session 存放的用户 id 为 {}",session.getAttribute("user"));
       session.setMaxInactiveInterval(1800); //30 min 过期
        Userlogin resUser = new Userlogin();
        resUser.setEmail(user1.getEmail());
        resUser.setName(user1.getName());
        resUser.setGender(user1.getGender());
        resUser.setAvatarUrl(user1.getAvatarUrl());
        resUser.setRole(user1.getRole());
//        密码更改后返回
//        resUser.setPwd(UpsetString.upsetString(user1.getPwd()));
//        密码直接返回 null
        resUser.setPwd("null");
//        用 userid 生成 token
       String token  = JWTUtils.createToken(request.getSession(false).getId());
       log.info("id:"+session.getAttribute("user"));
       log.info(" token为 : {}",token);
       resUser.setToken(token);
        return ResultData.success(resUser);
    }
//退出登录
@PostMapping("/logout")
@ApiOperation(value = "退出登录接口",notes = "退出登录接口")
@ApiImplicitParam(paramType = "body",name = "userlogin",value = "用户信息",required = true,dataType = "User")
    public  ResultData<String> logout(HttpServletRequest request,@RequestBody Userlogin userlogin){
        log.info("本次请求 URL： "+request.getRequestURI());
        log.info("进入 退出登录 logout 方法");
        log.info(request.getSession().getId());
//        查询用户的 id
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,userlogin.getEmail());
        User user= userService.getOne(queryWrapper);
        log.info("退出的用户 id 为： "+user.getId());
//        移除 redis 中存放的用户登录标识
        redisTemplate.opsForValue().getOperations().delete("loginUser:"+user.getId());
    redisTemplate.opsForValue().getOperations().delete("userid");
        request.getSession(false).invalidate(); // 使当前对话无效
        return ResultData.success("退出成功");
    }
//    头像上传
    @ApiOperation(value = "头像上传接口",notes = "头像上传接口")
    @ApiImplicitParam(paramType = "query",name = "avatar",value = "用户头像",required = true,dataType = "String")
    @PostMapping("/upheadpor")
    public ResultData<String> upheadpor(@RequestParam("avatar")  MultipartFile file, HttpServletRequest req) throws IOException {
        String filename =  CommonController.uplaod(file).getData();
        log.info("upheadpor 生成的文件名为： {}",filename);
//        log.info("传递进来的 id 为： {}",id);
//         保存文件名到用户信息中
        UpdateWrapper<User> up = new UpdateWrapper<>();
        up.eq("Id",8);
        up.set("avatarUrl",filename);
        userService.update(null,up);
        return  ResultData.success(filename);
    }


//    请求头像
    @ApiOperation(value = "请求头像/视频/大部分文件接口",notes = "请求头像/视频/大部分文件接口")
    @ApiImplicitParam(paramType = "query",name = "name",value = "文件名",required = true,dataType = "null")
    @GetMapping("/downheadpor")
    public void downheadpor(@Parameter  String name, HttpServletResponse response) throws IOException {
        CommonController.download(name,response);
    }

//    测试正常
    // 根据用户 id 返回用户名
    @GetMapping("/getUserByid")
    @ApiOperation(value = "根据用户 id 返回用户名",notes = "根据用户 id 返回用户名")
    @ApiImplicitParam(paramType = "query",name = "id",value = "用户 id",required = true,dataType = "String")
    public ResultData<String> getuserName(@RequestParam Integer id)
    {
        User user = userService.getById(id);
        if(user == null)
        {
            return  ResultData.fail(ReturnCode.RC500.getCode(),"系统错误，请稍后再试");
        }
        return ResultData.success(user.getName());
    }



//    TODO: 增加一个超级用户登录界面，用于管理博客文章和当前注册用户
    @PostMapping("/updateUser")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(paramType = "body",name = "user",value = "用户信息",required = true,dataType = "User")
    public ResultData updateUser(@RequestBody Userlogin userlogin){
        User user = new User();
        log.info("更新信息: "+userlogin.toString());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getEmail,userlogin.getEmail());
        user.setName(userlogin.getName());
//        不更新头像信息
        user.setAvatarUrl(userlogin.getAvatarUrl());
        user.setRole(userlogin.getRole());
        user.setGender(userlogin.getGender());
        user.setQm(userlogin.getQm());
        user.setBirthday(userlogin.getBirthday());
        user.setUpdateTime(LocalDate.now());
//        需要增加一个签名变量，表，类
        userService.update(user,lambdaQueryWrapper);
        return ResultData.success(userlogin);
    }

    private final String base="http://localhost:8800/user/downheadpor?name=";
//    通过 email 返回用户信息
    @GetMapping("/getUserByEmail")
    @ApiOperation(value = "通过 email 返回用户信息",notes = "通过 email 返回用户信息")
    @ApiImplicitParam(paramType = "query",name = "email",value = "用户邮箱",required = true,dataType = "String")
    public ResultData<Userlogin> getUserByEmail(@RequestParam String email){
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail,email));
        Userlogin userlogin = new Userlogin();
        userlogin.setEmail(email);
        userlogin.setQm(user.getQm());
        userlogin.setName(user.getName());
        userlogin.setGender(user.getGender());
        userlogin.setAvatarUrl(base+user.getAvatarUrl());
        userlogin.setBirthday(user.getBirthday());
        if(user == null)
        {
            return  ResultData.fail(ReturnCode.RC500.getCode(),"系统错误，请稍后再试");
        }
        return ResultData.success(userlogin);
    }

//    返回用户头像信息
     @GetMapping("/avatar")
     @ApiOperation(value = "返回用户头像信息",notes = "返回用户头像信息")
     @ApiImplicitParam(paramType = "query",name = "name",value = "用户头像名",required = true,dataType = "String")
     public ResultData<String> getavatar(@RequestParam(value = "email") String email){
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getEmail,email);
            User user = userService.getOne(lambdaQueryWrapper);
            if(user == null)
            {
                return ResultData.success( base+"muoren.jpg");

            }
//            返回默认头像
       return  ResultData.success(base+user.getAvatarUrl());

     }

//     根据 email 返回用户 id
    @GetMapping("/getId")
    @ApiOperation(value = "根据 email 返回用户 id",notes = "根据 email 返回用户 id")
    @ApiImplicitParam(paramType = "query",name = "email",value = "用户邮箱",required = true,dataType = "String")
    public ResultData getId(@RequestParam(value = "email") String email)
    {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getEmail,email));
        if(user == null)
        {
            return  ResultData.fail(ReturnCode.RC500.getCode(),"系统错误，请稍后再试");
        }
        return ResultData.success(user.getId());
    }
}
































