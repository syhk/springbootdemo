<template>
  <el-button>Default</el-button>
  <el-button type="primary">Primary</el-button>
  <el-button type="success">success</el-button>
  <el-button type="info">info</el-button>
  <el-button type="warning">warning</el-button>
  <el-button type="danger">danger</el-button>
  <el-button @click="changemsg">Chinese</el-button>
  <p>{{ msg }}</p>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import axios from "axios";
import querystring from "querystring";
// 引入封闭好的网络请求
import api from "../api/index";
export default defineComponent({
  setup() {
    const msg = ref<string>("");
    function changemsg() {
      // axios({
      //   method: "get",
      //   url: "http://iwenwiki.com/api/blueberrypai/getChengpinDetails.php",
      // }).then((res) => {
      //   msg.value = res.data;
      // });
      // axios
      //   .get("http://iwenwiki.com/api/blueberrypai/getChengpinDetails.php ")
      //   .then((res) => {
      //     msg.value = res.data;
      //   });
      // axios
      //   .post(
      //     " http://iwenwiki.com/api/blueberrypai/getChengpinDetails.php ",
      //     querystring.stringify({
      //       user_id: String("iwen@qq.com"),
      //       password: String("iwen123"),
      //       verification_code: String("crfvw"),
      //     })
      //   )
      //   .then((res) => {
      //     msg.value = res.data;
      //   });

      api.getChengpin().then((res) => {
        msg.value = res.data;
      });
      console.log(msg.value);
    }
    return {
      msg,
      changemsg,
    };
  },

  mounted() {},
});
</script>

<style scoped lang="scss"></style>
