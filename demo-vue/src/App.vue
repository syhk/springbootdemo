<template>
  <!-- <div   class="link showno bounce-in-top " style="position:relative;top:100px;">
    <router-link class="link1 tracking-in-expand-fwd" to="/login">登录</router-link>
    <router-link class="link1 tracking-in-expand-fwd" to="/reg">注册</router-link>
  </div> -->
  <!-- 音乐播放 -->
   <div  style="position: fixed;z-index: 10;left: 1%;top:5%">
    <div class="fm">
      <img src="./assets/lottie/127848-happy-spaceman.gif" alt="">
    </div>
    <audio class="music" :src="mus" loop controls autoplay></audio>
  </div>

  <router-view></router-view>
  <div class="wave"></div>
  <link
    href="https://unpkg.com/nes.css@latest/css/nes.min.css"
    rel="stylesheet"
  />

   

</template>


<script lang="ts" setup>

//TODO: 暂时固定加载音乐，后面更换播放列表，歌词
let mus="http://localhost:8800/user/downheadpor?name=3631428663.mp3"



</script>


<style lang="scss">
#app {
  padding: 0;
  // width: 100vw;
  // height: 100vh;
  font-size: 12px;
  background-color: #ecf0f3;
  // color: #a0a5a8;
}
body {
  margin: auto;
  font-family: zhz;
  overflow: auto;
  background: linear-gradient(
    315deg,
    rgb(245, 130, 130) 3%,
    rgb(208, 238, 228) 38%,
    rgb(186, 221, 230) 68%,
    rgb(221, 187, 200) 98%
  );
  animation: gradient 10s ease infinite;
  background-size: 400% 400%;
  background-attachment: fixed;
}

// 音乐播放
.music{
  width: 20px;
  height: 5px;
}

.music:hover{
width: 250px;
height:54px;
border-radius: 10px;
transition: all 0.5s ease;
}

.fm img {
  width: 60px;
  border-radius: 50%;
}

.fm img:hover{
width: 150px;
border-radius: 10px;
transition: all 2s ease;
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


// router-link 样式
.link{
  padding: 8px;
  margin: auto;
  // margin-top: 100px;
  border: solid 3px;
  border-top: 0px;
  border-left: 1px;
  border-right: 1px;
  border-radius: 20%;
  width: 50%;
  display: flex;
  justify-content: space-around;
  .link1 {
    font-family: zhz;
    font-size: 20px;
    color: rgb(221, 6, 78);
  }
}

.link:hover{
  border-color: tomato;
  border-radius: 0%;
  transition: all 0.5s ease;
  transition-delay: 0.5s;
}

.tracking-in-expand-fwd {
	-webkit-animation: tracking-in-expand-fwd 1.5s cubic-bezier(0.215, 0.610, 0.355, 1.000) both;
	        animation: tracking-in-expand-fwd 1.5s cubic-bezier(0.215, 0.610, 0.355, 1.000) both;
}


@-webkit-keyframes tracking-in-expand-fwd {
  0% {
    letter-spacing: -0.5em;
    -webkit-transform: translateZ(-700px);
            transform: translateZ(-700px);
    opacity: 0;
  }
  40% {
    opacity: 0.6;
  }
  100% {
    -webkit-transform: translateZ(0);
            transform: translateZ(0);
    opacity: 1;
  }
}
@keyframes tracking-in-expand-fwd {
  0% {
    letter-spacing: -0.5em;
    -webkit-transform: translateZ(-700px);
            transform: translateZ(-700px);
    opacity: 0;
  }
  40% {
    opacity: 0.6;
  }
  100% {
    -webkit-transform: translateZ(0);
            transform: translateZ(0);
    opacity: 1;
  }
}

// div 横线动画
.bounce-in-top {
	-webkit-animation: bounce-in-top 1.8s both;
	        animation: bounce-in-top 1.8s both;
}

@-webkit-keyframes bounce-in-top {
  0% {
    -webkit-transform: translateY(-500px);
            transform: translateY(-500px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
    opacity: 0;
  }
  38% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
    opacity: 1;
  }
  55% {
    -webkit-transform: translateY(-65px);
            transform: translateY(-65px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  72% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  81% {
    -webkit-transform: translateY(-28px);
            transform: translateY(-28px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  90% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  95% {
    -webkit-transform: translateY(-8px);
            transform: translateY(-8px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  100% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
}
@keyframes bounce-in-top {
  0% {
    -webkit-transform: translateY(-500px);
            transform: translateY(-500px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
    opacity: 0;
  }
  38% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
    opacity: 1;
  }
  55% {
    -webkit-transform: translateY(-65px);
            transform: translateY(-65px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  72% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  81% {
    -webkit-transform: translateY(-28px);
            transform: translateY(-28px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  90% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  95% {
    -webkit-transform: translateY(-8px);
            transform: translateY(-8px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  100% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
}


</style>
