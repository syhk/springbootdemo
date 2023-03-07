<template>
  <div>
    <div class="wave"></div>
    <div class="wave"></div>
    <div class="wave"></div>
    <div class="syhk"><span> SYHK </span></div>
  </div>
</template>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
}

body {
  margin: auto;
  font-family: -apple-system, BlinkMacSystemFont, sans-serif;
  overflow: auto;
  background: linear-gradient(
    315deg,
    rgb(245, 130, 130) 3%,
    rgb(73, 209, 250) 38%,
    rgb(122, 247, 215) 68%,
    rgb(149, 229, 243) 98%
  );
  animation: gradient 15s ease infinite;
  background-size: 400% 400%;
  background-attachment: fixed;
}

.syhk {
  position: absolute;
  left: 50%;
  margin-left: -100px;
  span {
    color: rgb(6, 72, 99);
    font-size: 100px;
  }
}

@keyframes gradient {
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 100% 100%;
  }
  100% {
    background-position: 0% 0%;
  }
}

.wave {
  background: rgb(255 255 255 / 25%);
  border-radius: 1000% 1000% 0 0;
  position: fixed;
  width: 200%;
  height: 12em;
  animation: wave 10s -3s linear infinite;
  transform: translate3d(0, 0, 0);
  opacity: 0.8;
  bottom: 0;
  left: 0;
  z-index: -1;
}

.wave:nth-of-type(2) {
  bottom: -1.25em;
  animation: wave 18s linear reverse infinite;
  opacity: 0.8;
}

.wave:nth-of-type(3) {
  bottom: -2.5em;
  animation: wave 20s -1s reverse infinite;
  opacity: 0.9;
}

@keyframes wave {
  2% {
    transform: translateX(1);
  }

  25% {
    transform: translateX(-25%);
  }

  50% {
    transform: translateX(-50%);
  }

  75% {
    transform: translateX(-25%);
  }

  100% {
    transform: translateX(1);
  }
}
</style>

<script lang="ts" setup></script>
