<template>


    <!-- 视频播放器 -->

 <div>
    <vue3VideoPlay v-bind="options" poster=''/>
  </div>

  <div class="box">
   <span class="nes-text is-error header">动漫基地，享受动漫带来的心灵沉静</span>
   <button type="button" @click="re" style="margin-top: 15px;" class="nes-btn is-error">返回首页</button>
  </div>

  <!-- 视频列表 -->
  <div class="videolist">
    <a-card  v-for="item in list" @click="change(item.id,item.path,item.name)"   hoverable style="width: 240px;height: 300px; margin-top: 20px;">
    <template #cover>
      <img style="overflow:hidden;width: 100%;height: 200px;" alt="封面" :src=item.fm />
    </template>
    <a-card-meta title="动漫">
      <template  #description>{{ item.name }}</template>
    </a-card-meta>
  </a-card>
  </div>
</template>

<script lang="ts" setup>
import { message } from 'ant-design-vue';
import { reactive,onMounted ,ref} from 'vue';
import api from '../api/index'
import router from '../router';
let list=ref([]);
const options = reactive({
  width: '100%', //播放器宽度
  height: '650px', //播放器高度
  color: "#faf8f9", //主题色
  title: '你的名字', //视频名称
  // 默认播放你的名字
  src:"http://localhost:8800/user/downheadpor?name=25876383_p1-1-16.mp4", //视频源
  muted: false, //静音
  webFullScreen: false,
  speedRate: ["0.75", "1.0", "1.25", "1.5", "2.0"], //播放倍速
  autoPlay: false, //自动播放
  loop: false, //循环播放
  mirror: false, //镜像画面
  ligthOff: false,  //关灯模式
  volume: 0.3, //默认音量大小
  control: true, //是否显示控制
  controlBtns:['audioTrack', 'quality', 'speedRate', 'volume', 'setting', 'pip', 'pageFullScreen', 'fullScreen'] //显示所有按钮,
})

// 根据 path 进行更换播放视频
 function change(id:number,path:string,name:string){
  console.log(id+"  "+path);
   options.src = path;
   options.title=name;
   options.autoPlay=true;
 };


 function re(){
  router.push("/")
 }


// 在组件挂载时，请求视频数据
onMounted(()=>{
 api.getallvideo().then((res)=>{
  console.log(res);
  list.value=res.data.data;
 }).catch((err)=>{
  message.error("发生了错误："+err.message)
 })
});

</script>


<style lang="scss" scoped>

.videolist{
  margin-top: 35px;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  background: linear-gradient(45deg,#2a4ae430,#fb949e6b);

}

.header{
  font-size: 10px;
}

.box{
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

</style>
