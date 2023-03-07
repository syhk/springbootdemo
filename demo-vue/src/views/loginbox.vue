<template>
  <div style="width: 100%; height: 100vh">
      <div   class="link showno bounce-in-top " style="position:relative;top:100px;">
    <router-link class="link1 tracking-in-expand-fwd" to="/login">登录</router-link>
    <!-- <router-link class="link1 tracking-in-expand-fwd" to="/reg">注册</router-link> -->
  </div>
    <div class="loginbox slide-in-bottom">
      <!-- Gif 图片 -->
      <div class="d1"> 
      <img style="width: 400px;height: 400px;" src="../assets/lottie/animation_500_lee4k41q.gif" alt="">
      </div>
      <!-- <div class="d1"></div> -->
      <div class="d2">
        <a-form
          :model="formState"
          name="normal_login"
          class="login-form"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
        >
          <a-form-item
            class="font_zhz"
            label="邮件"
            name="email"
            :rules="[{ required: true, message: '请输入您的 email!' }]"
          >
            <a-input class="test" v-model:value="formState.email">
              <template #prefix>
                <UserOutlined class="site-form-item-icon" />
              </template>
            </a-input>
          </a-form-item>
          <a-form-item
            class="font_zhz"
            label="密码"
            name="pwd"
            :rules="[
              { required: true, message: '请输入您的密码!' },
            ]"
          >
            <a-input-password class="test" v-model:value="formState.pwd">
              <template #prefix>
                <LockOutlined class="site-form-item-icon" />
              </template>
            </a-input-password>
          </a-form-item>
          <a-form-item>
            <a-form-item name="remember" no-style>
              <a-checkbox class="font_zhz" v-model:checked="formState.remember"
                >记住我</a-checkbox
              >
            </a-form-item>
          </a-form-item>
          <button @click="success" style="position:relative;left:-22px;" class="test2 font_zhz" type="submit">登录</button>
          <router-link class="reg" to="/reg">注册账号</router-link>
        </a-form>
      </div>
    </div>
  </div>
</template>


<script lang="ts">
import { defineComponent, reactive, computed } from "vue";
import { UserOutlined, LockOutlined } from "@ant-design/icons-vue";
import api from "../api/index";
import router from "../router/index";
import useStore from '../pinia/state'
import { message } from "ant-design-vue";
interface FormState {
  email: string;

  wd: string;
  remember: boolean;
}
export default defineComponent({
  components: {
    UserOutlined,
    LockOutlined,
  },
  setup() {
    message.success("欢迎来到此博客")
    const formState = reactive<FormState>({
      email: "",
      pwd: "",
      remember: false,
    });
      // 先进行验证用户是否已经登录过了
      if(localStorage.getItem("userInfo") != null)
      {
        console.log("用户已经登录，无须再次登录");
        message.success("用户已经登录，无须再次登录！")
        router.push("/");
      }
    // 提交验证成功后的回调事件
    const onFinish = (values: any) => {

      console.log("values=============" + formState.email + formState.pwd);

      // redis session 接口
      console.log("开始登录接口测试：");
      api.loginredis(formState.email,formState.pwd)
      .then((res)=>{
        console.log("登录成功");
        console.log(res);
        if(res.data.flag === true){
          localStorage.setItem("userInfo",JSON.stringify(res.data.data));
          // 把用户 email 存放进去
          localStorage.setItem("email",res.data.data.email);
          // 用户名存放进去
          localStorage.setItem("name",res.data.data.name)
          // 存储 token 
          localStorage.setItem("token",res.data.data.token);
          console.log("开始路由到 /");
          // 判断是普通用户还是超级用户
          if(res.data.data.role=== 1)
          {//root 用户
            message.success("root用户登录成功")
            router.push("/select");
          }else{
            message.success("登录成功")
          router.push("/");
          }
        }
      }).catch((err)=>{
        console.log("发生了错误"+err.message);
        message.success("发生了错误"+err.message)
      })
      // 拦截器接口 
      // api
      //   .login(formState.email, formState.pwd)
      //   .then((res) => {
      //     console.log(res.data);
      //     if (res.data.flag === true) {
      //       localStorage.setItem("userInfo", JSON.stringify(res.data.data)); //把用户数据存储起来
      //       console.log("页面跳转到首页");
      //       router.push("/");
      //     }
      //   })
      //   .catch((err) => {
      //     console.log(err.data);
      //   });
    };
    // 提交验证失败后的回调事件
    const onFinishFailed = (errorInfo: any) => {
      message.error("Failed: 请输入正确的账号信息!");
      console.log("Failed:", errorInfo);
    };
    const disabled = computed(() => {
      return !(formState.email && formState.pwd);
    });
    const success=()=>{
      // message.success("start logining")
     const hide=  message.loading("正在登录...", 0);
     setTimeout(hide,2500);
    }
    return {
      formState,
      onFinish,
      onFinishFailed,
      disabled,
      success,
    };
  },
  watch: {
    router(to, from) {
      window.location.reload();
    },
  },
});
</script>
<style lang="scss">
#components-form-demo-normal-login .login-form {
  max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
  float: right;
}
.loginbox {
  position: relative;
  top: 200px;
  margin: auto;
  // margin-top: 200px;
  width: 800px;
  height: 400px;
  background-color: rgb(197, 191, 190);
  display: flex;
  justify-content: center;
  .d1 {
    flex: 1;
    background-color: rgb(59 127 173);
  }
  .d2 {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
  }
}
.test {
  border-radius: 10px;
  width: 200px;
}

.test2 {
  text-align: center;
  margin-left: 82px;
  width: 150px;
  border-radius: 20px;
}

.reg{
  position: absolute;
  top: 376px;
  left: 591px;
  font-family: zhz;
  font-size: 15px;
  color: rgb(0, 128, 128);
}
.reg:hover{
 color: tomato;
 font-size: 20px;
 transition: all 0.5s ease;
}

</style>
