<template>
  <div class="loginbox">
    <div class="d1"></div>

    <div class="d2">
      <a-form
        :model="formState"
        name="normal_login"
        class="login-form"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <a-form-item
          label="邮件"
          name="email"
          :rules="[{ required: true, message: 'Please input your email!' }]"
        >
          <a-input class="test" v-model:value="formState.email">
            <template #prefix>
              <UserOutlined class="site-form-item-icon" />
            </template>
          </a-input>
        </a-form-item>

        <a-form-item
          label="密码"
          name="pwd"
          :rules="[{ required: true, message: 'Please input your password!' }]"
        >
          <a-input-password class="test" v-model:value="formState.pwd">
            <template #prefix>
              <LockOutlined class="site-form-item-icon" />
            </template>
          </a-input-password>
        </a-form-item>

        <a-form-item>
          <a-form-item name="remember" no-style>
            <a-checkbox v-model:checked="formState.remember">记住我</a-checkbox>
          </a-form-item>
        </a-form-item>
        <button class="test2" type="submit">登录</button>
      </a-form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, computed } from "vue";
import { UserOutlined, LockOutlined } from "@ant-design/icons-vue";
import api from "../api/index";
import router from "../router/index";
interface FormState {
  email: string;
  pwd: string;
  remember: boolean;
}
export default defineComponent({
  components: {
    UserOutlined,
    LockOutlined,
  },
  setup() {
    const formState = reactive<FormState>({
      email: "",
      pwd: "",
      remember: false,
    });
    // 提交验证成功后的回调事件
    const onFinish = (values: any) => {
      console.log("values=============" + formState.email + formState.pwd);
      api
        .login(formState.email, formState.pwd)
        .then((res) => {
          console.log(res.data);
          if (res.data.flag === true) {
            localStorage.setItem("userInfo", JSON.stringify(res.data.data)); //把用户数据存储起来
            console.log("页面跳转到首页");
            router.push("/test");
          }
        })
        .catch((err) => {
          console.log(err.data);
        });
    };
    // 提交验证失败后的回调事件
    const onFinishFailed = (errorInfo: any) => {
      console.log("Failed:", errorInfo);
    };
    const disabled = computed(() => {
      return !(formState.email && formState.pwd);
    });
    return {
      formState,
      onFinish,
      onFinishFailed,
      disabled,
    };
  },
  // watch: {
  //   router(to, from) {
  //     this.$router.go(0);
  //   },
  // },
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
  width: 800px;
  height: 400px;
  background-color: rgb(197, 191, 190);
  display: flex;
  justify-content: center;
  .d1 {
    flex: 1;
    background-color: teal;
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
</style>
