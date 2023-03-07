<template>
  <!-- <div class="header"></div> -->
  <!-- <div><span class="text"> 欢迎来到博客</span></div> -->
  <a-carousel autoplay>
    <div><img class="autoplay" src="../assets/images/拾光_wallhaven_m98218.jpg" alt="1" /></div>
    <div><img class="autoplay" src="../assets/images/3.jpg" alt="2" /></div>
    <div><img class="autoplay"  src="../assets/images/拾光_wallhaven_x8dk8z.jpg" alt="3" /></div>
    <div>
      <img class="autoplay"  src="../assets/images/home-cover.jpg" alt="4" />
    </div>
  </a-carousel>

       <section class="message -left cartdm">
        <i class="nes-bulbasaur" style="margin-bottom: -50px;margin-top: 30px;"></i>
      <div class="nes-balloon from-left dmmsg">
        <p @click="godm">去动漫基地</p>
      </div>
    </section>

  <!-- 导航模块  -->
  <div class="tabs">
    <!-- TAB 0 -->
    <input type="radio" name="tab" id="tab-0" checked />
    <label @click="" for="tab-0">
      <div class="wave"></div>
      <p class="font_zhz">首页</p>
    </label>
    <!-- TAB 2 -->
    <input type="radio" name="tab" id="tab-02" />
    <label @click="fbblog" for="tab-02">
      <!-- <svg>
        <use xlink:href="#icon-02" class="icon" />
      </svg> -->
      <div class="wave"></div>
      <p class="font_zhz">发布博客</p>
    </label>
    <!-- TAB 3 -->
    <input type="radio" name="tab" id="tab-03" />
    <label @click="blog" for="tab-03">
      <!-- <svg>
        <use xlink:href="#icon-03" class="icon" /> -->
      <!-- </svg> -->
      <div class="wave"></div>
      <p class="font_zhz">博客列表</p>
    </label>
    <!-- TAB 1 -->
    <input type="radio" name="tab" id="tab-01" />
    <label @click="zx" for="tab-01">
      <!-- <svg>
        <use xlink:href="#icon-01" class="icon" />
      </svg> -->
      <div class="wave"></div>
      <p class="font_zhz">个人中心</p>
    </label>
    <!-- TAB 4 -->
    <input type="radio" name="tab" id="tab-04" />
    <label @click="logout" for="tab-04">
      <!-- <svg>
        <use xlink:href="#icon-04" class="icon" />
      </svg> -->
      <div class="wave"></div>
      <p class="font_zhz">退出登录</p>
    </label>
  </div>

  <div class="gif">
    <img style="border-radius: 200px;" src="../assets/lottie/111458-space-travel-icon-animation.gif" alt="">

  </div>


  <!-- SVG -->
  <svg xmlns="http://www.w3.org/2000/svg" style="display: none">
    <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 26" id="icon-01">
      <path
        d="M13.93,1H20.8A4.21,4.21,0,0,1,25,5.2V20.8A4.21,4.21,0,0,1,20.8,25H5.2A4.21,4.21,0,0,1,1,20.8V5.2A4.21,4.21,0,0,1,5.2,1h.47"
      />
      <line x1="16" y1="10" x2="18" y2="10" />
      <line x1="8" y1="10" x2="12" y2="10" />
      <line x1="8" y1="15" x2="18" y2="15" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 26" id="icon-02">
      <path
        d="M17,1a12.33,12.33,0,0,1,8,11.65A12.18,12.18,0,0,1,13,25,12.18,12.18,0,0,1,1,12.65,12.33,12.33,0,0,1,9,1"
      />
      <polygon points="15 14.33 11 17 11 11.67 15 9 15 14.33" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 26" id="icon-03">
      <path
        d="M7.91,2.08a11.15,11.15,0,0,0-5.73,9.81v6a7.83,7.83,0,0,1-1,2.92A1.47,1.47,0,0,0,2.43,23H23.57a1.47,1.47,0,0,0,1.26-2.16,7.83,7.83,0,0,1-1-2.92v-6A11.06,11.06,0,0,0,15.18,1"
      />
      <path d="M15,23a2,2,0,0,1-4,0" />
      <path d="M16,5.51A6.53,6.53,0,0,1,19.65,9.4" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" viewBox="0 0 26 26" id="icon-04">
      <path
        d="M17,1a12.33,12.33,0,0,1,8,11.65A12.18,12.18,0,0,1,13,25,12.18,12.18,0,0,1,1,12.65,12.33,12.33,0,0,1,9,1"
      />
      <path d="M18,18.26a8,8,0,0,1-10.09-.1" />
    </symbol>
  </svg>

  <svg xmlns="http://www.w3.org/2000/svg" height="0" width="0">
    <clipPath id="path-icon-01">
      <path
        d="M13.93,1H20.8A4.21,4.21,0,0,1,25,5.2V20.8A4.21,4.21,0,0,1,20.8,25H5.2A4.21,4.21,0,0,1,1,20.8V5.2A4.21,4.21,0,0,1,5.2,1h.47"
      />
    </clipPath>
    <clipPath id="path-icon-02">
      <path
        d="M17,1a12.33,12.33,0,0,1,8,11.65A12.18,12.18,0,0,1,13,25,12.18,12.18,0,0,1,1,12.65,12.33,12.33,0,0,1,9,1"
      />
    </clipPath>
    <clipPath id="path-icon-03">
      <path
        d="M7.91,2.08a11.15,11.15,0,0,0-5.73,9.81v6a7.83,7.83,0,0,1-1,2.92A1.47,1.47,0,0,0,2.43,23H23.57a1.47,1.47,0,0,0,1.26-2.16,7.83,7.83,0,0,1-1-2.92v-6A11.06,11.06,0,0,0,15.18,1"
      />
    </clipPath>
    <clipPath id="path-icon-04">
      <path
        d="M17,1a12.33,12.33,0,0,1,8,11.65A12.18,12.18,0,0,1,13,25,12.18,12.18,0,0,1,1,12.65,12.33,12.33,0,0,1,9,1"
      />
    </clipPath>
  </svg>

  <div class="socials">
    <!-- 悬浮标 =========================-->
    <section class="nes-container">
      <section class="message-list">
        <section class="message -left">
          <i class="nes-octocat animate"></i>
          <!-- Balloon -->
          <div class="nes-balloon from-left">
            <p class="font_zhz">欢迎来访</p>
          </div>
        </section>
      </section>
    </section>
    <!-- ==================================== -->
    <a
      class="dribbble"
      href="https://dribbble.com/shots/9713873-Tabbar-animation-CodePen"
      target="_blank"
      ><svg
        xmlns="http://www.w3.org/2000/svg"
        width="30"
        height="30"
        viewBox="0 0 32 32"
      >
        <path
          fill-rule="evenodd"
          clip-rule="evenodd"
          fill="black"
          d="M16 0C7.16703 0 0 7.16703 0 16C0 24.833 7.16703 32 16 32C24.8156 32 32 24.833 32 16C32 7.16703 24.8156 0 16 0ZM26.5683 7.37527C28.4772 9.70065 29.6226 12.6681 29.6573 15.8785C29.2061 15.7918 24.6941 14.872 20.1475 15.4447C20.0434 15.2191 19.9566 14.9761 19.8525 14.7332C19.5748 14.0738 19.2625 13.397 18.9501 12.7549C23.9826 10.7072 26.2733 7.75705 26.5683 7.37527ZM16 2.36009C19.4707 2.36009 22.6464 3.66161 25.0586 5.7961C24.8156 6.14317 22.7505 8.90239 17.8915 10.7245C15.6529 6.61171 13.1714 3.24512 12.7896 2.72451C13.8134 2.48156 14.8894 2.36009 16 2.36009ZM10.1866 3.64425C10.551 4.13015 12.9805 7.5141 15.2538 11.5401C8.86768 13.2408 3.22777 13.2061 2.62039 13.2061C3.50542 8.9718 6.36876 5.44902 10.1866 3.64425ZM2.32538 16.0174C2.32538 15.8785 2.32538 15.7397 2.32538 15.6009C2.9154 15.6182 9.54447 15.705 16.3644 13.6573C16.7636 14.4208 17.128 15.2017 17.4751 15.9826C17.3015 16.0347 17.1106 16.0868 16.9371 16.1388C9.89154 18.4121 6.14317 24.6247 5.8308 25.1453C3.6616 22.7332 2.32538 19.5228 2.32538 16.0174ZM16 29.6746C12.8416 29.6746 9.92625 28.5987 7.61822 26.7939C7.86117 26.2907 10.6377 20.9458 18.3427 18.256C18.3774 18.2386 18.3948 18.2386 18.4295 18.2213C20.3557 23.2017 21.1367 27.3839 21.3449 28.5813C19.6963 29.2928 17.8915 29.6746 16 29.6746ZM23.6182 27.3319C23.4794 26.4989 22.7505 22.5076 20.9631 17.5965C25.2495 16.9197 28.9978 18.0304 29.4664 18.1866C28.8764 21.987 26.6898 25.2668 23.6182 27.3319Z"
        ></path></svg
    ></a>
    <a class="twitter" href="https://twitter.com/MilanRaring" target="_blank"
      ><svg
        xmlns="http://www.w3.org/2000/svg"
        width="30"
        height="30"
        viewBox="0 0 72 72"
      >
        <path
          fill="black"
          d="M67.812 16.141a26.246 26.246 0 0 1-7.519 2.06 13.134 13.134 0 0 0 5.756-7.244 26.127 26.127 0 0 1-8.313 3.176A13.075 13.075 0 0 0 48.182 10c-7.229 0-13.092 5.861-13.092 13.093 0 1.026.118 2.021.338 2.981-10.885-.548-20.528-5.757-26.987-13.679a13.048 13.048 0 0 0-1.771 6.581c0 4.542 2.312 8.551 5.824 10.898a13.048 13.048 0 0 1-5.93-1.638c-.002.055-.002.11-.002.162 0 6.345 4.513 11.638 10.504 12.84a13.177 13.177 0 0 1-3.449.457c-.846 0-1.667-.078-2.465-.231 1.667 5.2 6.499 8.986 12.23 9.09a26.276 26.276 0 0 1-16.26 5.606A26.21 26.21 0 0 1 4 55.976a37.036 37.036 0 0 0 20.067 5.882c24.083 0 37.251-19.949 37.251-37.249 0-.566-.014-1.134-.039-1.694a26.597 26.597 0 0 0 6.533-6.774z"
        ></path></svg
    ></a>
  </div>

  <!-- 个人信息卡片 -->
  <cart @click="gopeople" class="cart"></cart>

  <section class="icon-list">
    <!-- controllers -->
    <i class="nes-logo"></i>
    <i class="nes-jp-logo"></i>
    <i class="snes-logo"></i>
    <i class="snes-jp-logo"></i>
  </section>

  <!-- 这里不是 v-for 利用 localStorge 进行加载 -->
  <div class="container">
    <div class="tp4 tp"></div>
    <div @click="blog1(1)" class="bt">
      <p class="wz font_zhz">{{ blogfour[0].articleMd }}</p>
    </div>
  </div>
  <div class="container">
    <div class="tp1 tp"></div>
    <div @click="blog1(2)" class="bt">
      <p class="wz font_zhz">{{ blogfour[1].articleMd }}</p>
    </div>
  </div>
  <div class="container">
    <div class="tp2 tp"></div>
    <div @click="blog1(4)" class="bt">
      <p class="wz font_zhz">{{ blogfour[2].articleMd }}</p>
    </div>
  </div>
  <div class="container">
    <div class="tp3 tp"></div>
    <div @click="blog1(6)" class="bt">
      <p class="wz font_zhz">{{ blogfour[3].articleMd }}</p>
    </div>
  </div>
  <a-timeline class="sjt font_zhz" mode="alternate">
    <a-timeline-item>2014-09-01 加入</a-timeline-item>
    <a-timeline-item color="green">2014-12-01 写第一篇文章</a-timeline-item>
    <a-timeline-item>
      <template #dot><ClockCircleOutlined style="font-size: 16px" /></template>
      2015-09-01 更新了头像
    </a-timeline-item>
    <a-timeline-item color="red">2015-10-01 写了一篇文章</a-timeline-item>
    <a-timeline-item>2016-08-12 写了一篇文章</a-timeline-item>
    <a-timeline-item>
      <template #dot><ClockCircleOutlined style="font-size: 16px" /></template>
      2017-05-23 写了一篇文章
    </a-timeline-item>
  </a-timeline>
  <!-- 回到顶部 -->
  <a-back-top />
</template>

<style lang="scss" scoped>
.nes-octocat[data-v-b301384e] {
  left: -56px;
  top: 65px;
}
.nes-balloon {
  top: -76px;
  left: -104px;
}
.nes-container {
  border-style: none;
}

.icon-list {
  position: relative;
  top: -70px;
}

.header {
  height: 500px;
  background: #74ebd5; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    #acb6e5,
    #74ebd5
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #acb6e5,
    #74ebd5
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.text {
  position: absolute;
  top: 13%;
  left: 39%;
  font-size: 50px;
  color: tomato;
}
.container {
  position: relative;
  top: -42px;
  background-color: white;
  border-radius: 10px;
  border: solid 1px;
  height: 240px;
  width: 900px;
  margin: auto auto;
  margin-top: 5px;
  .tp {
    float: left;
    height: 100%;
    width: 35%;
    position: relative;
    object-fit: cover;
    transition: all 0.4s ease;
    background-position: center;
    overflow: hidden;
  }

  .tp1 {
    background: url(../assets/lottie/116204-flying-rocket-in-the-sky.gif) no-repeat;
    background-size: cover;
  }
  .tp2 {
    background: url(../assets/lottie/108681-spaceman.gif) no-repeat;
    background-position: 0px;
    background-size: cover;
  }
  .tp3 {
    background: url(../assets/lottie/127848-happy-spaceman.gif) no-repeat;
    background-size: cover;
  }
  .tp4 {
    background: url(../assets/lottie/127848-happy-spaceman.gif) ;
    background-size: cover;
  }
  .bt {
    padding: 10px;
    height: 100%;
  }
  .wz {
    position: relative;
    left: 81px;
    width: 400px;
    word-break: break-all;
    word-wrap: break-word;
    font-size: 34px;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    line-height: 3;
    -webkit-line-clamp: 2;
  }
}
.sjt {
  margin-top: 100px;
  height: 300px;
}
// 轮播图
/* For demo */
.ant-carousel :deep(.slick-slide) {
  text-align: center;
  height: 400px;
  line-height: 400px;
  background: #364d79;
  overflow: hidden;
}

.ant-carousel :deep(.slick-slide h3) {
  color: #fff;
}

.cart {
  margin: auto;
  position: relative;
  top: -100px;
}

.tabs {
  --background: #ffffff;
  --light-grey: #c6cfd9;
  --wave-color: #2ac8dd;
  --width: 300px;
  --height: 75px;
  --border-radius: var(--height);
  background: var(--background);
  width: var(--width);
  height: var(--height);
  padding: 0;
  position: absolute;
  right: 120px;
  top: 20px;
  border-radius: var(--border-radius);
  box-shadow: 0 10px 30px rgba(#414856, 0.05);
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 0 15px;
  box-sizing: border-box;
  overflow: hidden;
  label {
    width: 26px;
    height: 26px;
    cursor: pointer;
    position: relative;
    display: flex;
    justify-content: space-around;
    align-items: center;
    transform-origin: center bottom;
    &::before {
      content: "";
      height: 4px;
      width: 4px;
      border-radius: 0% 100% 50% 50% / 0% 50% 50% 100%;
      transform: scale(1, 1) rotate(45deg);
      transform-origin: 50% 50%;
      background: var(--wave-color);
      display: block;
      position: absolute;
      top: -30px;
    }
    svg {
      width: 100%;
      height: 100%;
      position: relative;
      z-index: 1;
      .icon {
        fill: none;
        stroke: var(--light-grey);
        stroke-dashoffset: 98;
        stroke-width: 2px;
        stroke-linecap: round;
        stroke-linejoin: round;
        transition: stroke 0.15s linear 0.3s;
      }
    }
    .wave {
      height: 26px;
      width: 26px;
      position: absolute;
      overflow: hidden;
      &::before,
      &::after {
        content: "";
        position: absolute;
        left: 0;
        bottom: -12px;
        right: 0;
        background-repeat: repeat;
        height: 14px;
        width: 48px;
        background-size: 12px 12px;
        animation: wave-animation 3s linear infinite;
        transition: bottom 0.15s ease-out 0.35s;
      }
      &::before {
        background-image: radial-gradient(
          circle at 6px -3px,
          transparent 6px,
          var(--wave-color) 6px
        );
        animation-duration: 2s;
        opacity: 0.5;
      }
      &::after {
        background-image: radial-gradient(
          circle at 6px -3px,
          transparent 6px,
          var(--wave-color) 6px
        );
        animation-duration: 3s;
      }
    }
    @for $icon from 1 through 4 {
      &:nth-of-type(#{$icon}) {
        .wave {
          clip-path: url(#path-icon-0+$icon);
        }
      }
    }
  }
  input {
    display: none;
    &:checked {
      + label {
        animation: wiggle-animation 1s ease 0.3s;
        &::before {
          animation: drop-animation 0.35s ease-in both;
        }
        svg {
          .icon {
            stroke: #4b4b4b;
          }
        }
        .wave {
          &:before {
            bottom: 0;
          }
          &:after {
            bottom: -4px;
          }
        }
      }
    }
  }
}
@keyframes wiggle-animation {
  0% {
    transform: rotate(0deg);
  }
  20% {
    transform: rotate(-7deg);
  }
  60% {
    transform: rotate(7deg);
  }
  90% {
    transform: rotate(-3deg);
  }
  100% {
    transform: rotate(0deg);
  }
}
@keyframes wave-animation {
  0% {
    transform: translateX(0%);
  }
  100% {
    transform: translateX(-50%);
  }
}
@keyframes drop-animation {
  70% {
    transform: scale(0.8, 3) rotate(45deg);
    top: -7px;
  }
  100% {
    transform: scale(1.5, 0.5) rotate(45deg);
    top: 19px;
  }
}

//--- ## BASIC #############
body {
  background: #eeeef5;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .socials {
    position: fixed;
    display: block;
    left: 20px;
    bottom: 20px;
    > a {
      display: block;
      width: 30px;
      opacity: 0.2;
      transform: scale(var(--scale, 0.8));
      transition: transform 0.3s cubic-bezier(0.38, -0.12, 0.24, 1.91);
      &:hover {
        --scale: 1;
      }
    }
  }
}

.nes-octocat {
  left: 10px;
}

.gif{
  position: absolute;
  left: 33%;
}

.cartdm{
  position: absolute;
  top:55%;
  left:15%;
  display: none;
}
@keyframes turtle {
  0%{
    transform: translateX(-200px);
  }
  20%{
    transform: translateX(-180px);
  }
  30%{
    transform: translateX(-160px);
  }
  40%{
    transform: translateX(-140px);
  }
  50%{
    transform: translateX(-120px);
  }
  60%{
    transform: translateX(-100px);
  }
  70%{
    transform: translateX(-80px);
  }
  80%{
    transform: translateX(-60px);
  }
  90%{
    transform: translateX(-40px);
  }
  100%{
    transform: translateX(0px);
  }


}

.cartdm {
  display: block;
  animation:turtle 5s ease ;
}



.dmmsg{
  transition: all 3s ease;
  animation: rotate 3s linear infinite;
}
.dmmsg:hover{
  animation: none;
  background-color: tomato;
  text-decoration: underline;
}
@keyframes rotate {
  25%{
    transform: rotateY(80deg);
    background-color: #58cada;
  }
  50%{
    transform: rotateY(160deg);
    background-color: #95d0e7;
  }
  75%{
    transform: rotateY(300deg);
    background-color: #7d589b;
  }
  100%{
    transform: rotateY(360deg);
  }
}

.autoplay{
  background-size: cover;
  background-repeat: no-repeat;
}





</style>

<script lang="ts">
import { reactive, ref } from "vue";
// import axios from "axios";
import api from "../api/index";
import router from "../router/index";
import { ClockCircleOutlined } from "@ant-design/icons-vue";
import cart from "../components/cart.vue";
import { log } from "console";
import { message } from "ant-design-vue";
import video from './element_test.vue';
// 首页的文章先固定加载
const blog1 = function (id:number) { //1 2  4  6
   api.getOneByid(id).then(res=>{
    router.push({
      path:'pre',
      query:{
        articleId:id
      }
    })
   }).catch(err=>{
    message.error("发生了错误："+err.message)
   })


};

const blog=function(){
  router.push("/blog")
}

const fbblog = function () {
  router.push("/md");
};
const gopeople=function(){
  router.push("/zx")
}
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
  // JSON.parse 方法解析 JSON 字符串，构造字符串描述的 JS 值或对象，也就是将符合格式的字符串转换为 JSON 对象
   userInfo = JSON.parse(userInfo);
}
console.log("index ============================^^^^^^^==============================================")
console.log(userInfo);
console.log(userInfo.email);
// 登出方法
function logout() {
  console.log("logout: 上传的 email 为 "+userInfo.email)
  api
    .logout(userInfo.email)
    .then((res) => {
      if (res.data.flag == true) {
        localStorage.removeItem("userInfo");
        // 退出登录时移除 localStorage 里面的所有存储
        window.localStorage.removeItem("email");
        localStorage.removeItem("name");
        localStorage.removeItem("token");
        localStorage.removeItem("arid");
        localStorage.removeItem("userid");
        message.success("成功退出登录,回到登录页面");
        // 回到登录页面
        router.push("/login");
       // window.location.reload();
        // 也可以使用
        // window.location.href = "http://localhost:5173/login";
      }
    })
    .catch((err) => message.error("登出失败！"+err.message));
}
// 个人中心
function zx() {
  router.push("/zx");
}
// 去动漫基地
function godm(){
  router.push("/cart")
}

let no=localStorage.getItem('show')=='no'?true:false
// 引入封闭好的网络请求
export default {
  data() {
    return {
      msg,
      ch,
      userInfo,
      logout,
      blog1,
      blog,
      blogfour: [],
      fbblog,
      zx,
      no,
      godm,
      gopeople
    };
  },

  components: {
    ClockCircleOutlined,
    cart,
  },
  created() {
    api
      .getfourblog()
      .then((res) => {
        this.blogfour = res.data.data;
        console.log(this.blogfour);
      })
      .catch((err) => {
        message.error(err.message);
      });
  },
  watch: {
    router(to, from) {
      // 监测路由发生跳转时刷新一次页面
      window.location.reload();
    },
  },
};
</script>
