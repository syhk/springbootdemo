<template>
  <!-- <div class="header"> -->
  <!-- <span>syhk</span> -->
  <!-- </div> -->
  <div class="wave"></div>
  <router-view></router-view>
</template>

<style lang="scss">
#app {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100vw;
  height: 100vh;
}

.header {
  width: 100vw;
  height: 50px;
  background-color: antiquewhite;
  span {
    font-size: 30px;
  }
}
</style>
