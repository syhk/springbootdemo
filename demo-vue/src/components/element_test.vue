<template>
  <p>{{ msg }}</p>
  <button @click="ch">点击</button>

  <p>{{ userInfo.email }}</p>

  <button @click="logout">退出登录</button>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
// import axios from "axios";
import api from "../api/index";
import router from "../router/index";

// 引入封闭好的网络请求
export default defineComponent({
  setup() {
    const msg = ref<string>("");
    function ch() {
      // api
      //   .getAll()
      //   .then((res) => {
      //     msg.value = res.data;
      //   })
      //   .catch((err) => {
      //     msg.value = err.data;
      //   })
      //   .finally(() => {
      //     console.log("失败了");
      //   });
      api
        .getAll()
        .then((res) => (msg.value = res.data))
        .catch((err) => (msg.value = err.data));
    }

    let userInfo = window.localStorage.getItem("userInfo");
    if (userInfo) {
      // JSON.parse 方法解析 JSON 字符串，构造字符串描述的 JS 值或对象
      userInfo = JSON.parse(userInfo);
    }
    console.log(userInfo);
    console.log(userInfo.email);

    // 登出方法
    function logout() {
      api
        .logout()
        .then((res) => {
          if (res.data.flag == true) {
            localStorage.removeItem("userInfo");
            // 回到登录页面
            router.push("/login");
            // 也可以使用
            // window.location.href = "http://localhost:5173/login";
          }
        })
        .catch((err) => alert("登出失败！"));
    }

    return {
      msg,
      ch,
      userInfo,
      logout,
    };
  },
  mounted() {},
});
</script>

<style scoped lang="scss"></style>
