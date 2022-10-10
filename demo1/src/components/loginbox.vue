<template>
  <!-- 实现毛玻璃效果 -->
  <div class="login-box">
    <div class="button-box">这是模糊效果</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  setup() {},
  beforeCreate() {
    document
      .querySelector("body")
      ?.setAttribute(
        "style",
        "margin:0 auto; wdith:100%;min-width:500px;background:tomato;overflow-x: hidden; height:100%;"
      );
  },
});
</script>

<style lang="scss">
.login-box {
  background: rgba(255, 255, 255, 0.3);
  backdrop-filter: blur(5px);
  padding: 200px;
  border-radius: 10px;
}
.button-box {
  width: 100%;
  height: auto;
}
</style>
