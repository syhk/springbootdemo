<template>
  <p>{{ msg }}</p>
  <button @click="ch">点击</button>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
// import axios from "axios";
import api from "../api/index";
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
    return {
      msg,
      ch,
    };
  },
  mounted() {},
});
</script>

<style scoped lang="scss"></style>
