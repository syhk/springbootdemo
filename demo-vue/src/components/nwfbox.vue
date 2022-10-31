<template>
  <!-- 拟物测试 -->

  <div class="container">
    <div class="neumorphism"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  setup() {},
});
</script>

<style lang="scss">
$bg_color: #e6e6e6;
$bs_color1: #bbbbbb;
$bs_color2: #ffffff;

.container {
  display: grid;
  //   place-items 是 align-items 和 justify-items 两个的简写
  place-items: center;
  background-color: $bg_color;
}
.neumorphism {
  width: 200px;
  height: 75px;
  border: none;
  border-radius: 20px;
  background-color: $bg_color;
  box-shadow: 5px 5px 10px $bs_color1, -5px -5px 10px $bs_color2;
}

.neumorphism:active {
  box-shadow: inset 5px 5px 10px $bs_color1, inset -5px -5px 10px $bs_color2;
}
</style>
