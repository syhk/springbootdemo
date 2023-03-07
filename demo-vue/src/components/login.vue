<template>
     <div   class="link showno bounce-in-top " style="position:relative;top:100px;">
    <!-- <router-link class="link1 tracking-in-expand-fwd" to="/login">登录</router-link> -->
    <router-link class="link1 tracking-in-expand-fwd" to="/reg">注册</router-link>
  </div>
  <div class="main slide-in-bottom font_zhz">
    <div class="container a-container" id="a-container">
      <form class="form" id="a-form" method="" action="">
        <h2 class="form_title title">注册账户</h2>
         <a-input class="font_zhz inreg" v-model:value="name" placeholder="姓名" />
          <a-input class="font_zhz inreg" v-model:value="email" placeholder="邮箱" />
        <a-input class=" font_zhz inreg" v-model:value="value" placeholder="密码" />
         <a-button class="regbtn form_button button" @click="reg" type="primary">注册</a-button>
      </form>
    </div>
  </div>
  
</template>

<script lang="ts" setup>
import { message } from 'ant-design-vue';
import {defineComponent,ref} from 'vue'
import api from '../api/index'
import router from '../router';
  const value = ref<string>('');
  const name = ref<string>('');
  const email = ref<string>('');
  message.success("欢迎注册")
  // 注册按钮事件
  function reg(){
   console.log("开始注册");
    // 判断 email 是否符合要求，name 是否为空，value 是否不小于 6 个字符 
    if (name.value == '') {
      message.error('请输入您的姓名');
      return;
    }
    if (email.value == '') {
      message.error('请输入您的邮箱');
      return;
    }
    if (value.value == '') {
      message.error('请输入您的密码');
      return;
    }
    if (value.value.length < 6) {
      message.error('密码长度不能小于 6 个字符');
      return;
    }
    // 验证邮箱格式是否正确
    const regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (!regex.test(email.value)) {
      message.error('请输入正确的邮箱格式');
      return;
    }
   api.register(name.value,email.value,value.value).then((res)=>{
    console.log(res);
    console.log("注册成功");
    if(res.data.flag==false)
    {//注册失败
      // alert(res.data.message);
      message.error("注册失败："+res.data.message)
    }
    // 改变策略，注册好后跳转至登录页面
    // 存储当前登录的用户信息
    // localStorage.setItem("userInfo",JSON.stringify(res));
    // 保存用户信息
    // localStorage.setItem("name",res.data.data.name);
    // localStorage.setItem("email",res.data.data.email);
    message.success("注册成功");
    router.push('/login');
    // window.location.reload();
   }).catch((err)=>{
    message.error("发生了错误："+err.data.message);
   })
     }
</script>

<style>
/**/
.main {
  margin: auto;
  margin-top: 190px;
  position: relative;
  width: 700px;
  min-width: 600px;
  min-height: 400px;
  height: 400px;
  padding: 15px;
  background-color: #ecf0f3;
  box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius: 12px;
  overflow: hidden;
}
@media (max-width: 1200px) {
  .main {
    transform: scale(0.7);
  }
}
@media (max-width: 1000px) {
  .main {
    transform: scale(0.6);
  }
}
@media (max-width: 800px) {
  .main {
    transform: scale(0.5);
  }
}
@media (max-width: 600px) {
  .main {
    transform: scale(0.4);
  }
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 600px;
  height: 100%;
  padding: 25px;
  background-color: #ecf0f3;
  transition: 1.25s;
}

.form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
}
.form__icon {
  object-fit: contain;
  width: 30px;
  margin: 0 5px;
  opacity: 0.5;
  transition: 0.15s;
}
.form__icon:hover {
  opacity: 1;
  transition: 0.15s;
  cursor: pointer;
}
.form__input {
  width: 350px;
  height: 40px;
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  font-family: "Montserrat", sans-serif;
  background-color: #ecf0f3;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}
.form__input:focus {
  box-shadow: inset 4px 4px 4px #d1d9e6, inset -4px -4px 4px #f9f9f9;
}
.form__span {
  margin-top: 30px;
  margin-bottom: 12px;
}
.form__link {
  color: #181818;
  font-size: 15px;
  margin-top: 25px;
  border-bottom: 1px solid #a0a5a8;
  line-height: 2;
}

.title {
  font-size: 34px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
}

.description {
  font-size: 14px;
  letter-spacing: 0.25px;
  text-align: center;
  line-height: 1.6;
}

.button {
  width: 180px;
  height: 50px;
  border-radius: 25px;
  margin-top: 50px;
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1.15px;
  background-color: #4b70e2;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
}

/**/
.a-container {
  z-index: 100;
  left: calc(100% - 646px);
}

.b-container {
  left: calc(100% - 600px);
  z-index: 0;
}

.switch {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 400px;
  padding: 50px;
  z-index: 200;
  transition: 1.25s;
  background-color: #ecf0f3;
  overflow: hidden;
  box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
}
.switch__circle {
  position: absolute;
  width: 500px;
  height: 500px;
  border-radius: 50%;
  background-color: #ecf0f3;
  box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
  bottom: -60%;
  left: -60%;
  transition: 1.25s;
}
.switch__circle--t {
  top: -30%;
  left: 60%;
  width: 300px;
  height: 300px;
}
.switch__container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: absolute;
  width: 400px;
  padding: 50px 55px;
  transition: 1.25s;
}
.switch__button {
  cursor: pointer;
}
.switch__button:hover {
  box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
  transform: scale(0.985);
  transition: 0.25s;
}
.switch__button:active,
.switch__button:focus {
  box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
  transform: scale(0.97);
  transition: 0.25s;
}

/**/
.is-txr {
  left: calc(100% - 400px);
  transition: 1.25s;
  transform-origin: left;
}

.is-txl {
  left: 0;
  transition: 1.25s;
  transform-origin: right;
}

.is-z200 {
  z-index: 200;
  transition: 1.25s;
}

.is-hidden {
  visibility: hidden;
  opacity: 0;
  position: absolute;
  transition: 1.25s;
}

.is-gx {
  animation: is-gx 1.25s;
}

@keyframes is-gx {
  0%,
  10%,
  100% {
    width: 400px;
  }
  30%,
  50% {
    width: 500px;
  }
}

.inreg{
  margin-top: 10px;
  background-color: #ecf0f3;
  border: solid 2px;
  border-top: 0px;
  border-left: 1px;
  border-right: 1px;
  border-radius: 0px;






}




</style>
