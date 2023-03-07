<template>
  <div class=" focus-in-expand dang font_zhz">博客文章列表</div>

  <div class="link2  bounce-in-top">
    <!-- <router-link class="link1 tracking-in-expand-fwd" to="/onblog">公共博客</router-link>
    <router-link class="link1 tracking-in-expand-fwd" to="/publog">个人博客</router-link> -->
    <!-- 更换为两个图标 -->
     <i class="nes-mario link1 tracking-in-expand-fwd"></i>
      <i class="nes-pokeball snak link1 tracking-in-expand-fwd"></i>
      <i class="nes-mario link1 tracking-in-expand-fwd"></i>
  </div>
  <!-- 搜索框 -->
  <div class="search bounce-in-top">
 <a-space>
    <a-input-search
      v-model:value="value"
      placeholder="输入搜索文章"
      enter-button
      style="width:350px;"
      @search="onSearch"
    />
</a-space>
  </div>
  <a-list
  style="min-height:400px"
    :grid="{
      gutter: 30,
      xs: 1,
      sm: 2,
      md: 2,
      lg: 2,
      xl: 2,
      xxl: 2,
      xxxl: 2,
      list: 2,
    }"
    v-model:data-source="data"
  >
    <template  #renderItem="{ item }">
      <a-list-item class="focus-in-expand">
        <a-card class="font_zhz roll-in-blurred-left shadow-drop-center " :title="item.title" @click="go(item.articleId)"
          ><p class="wz">{{ item.articleMd }}</p>
         
          <!-- 用于展示 5 个：发表日期，收藏数，阅读数，评论数，作者 -->
           <div class="fiveshow">
            <div class="icon">
             <icon-font  type="icon-shijian" />
            <p class="fwz">{{ item.pushTime }}</p>

            </div>
             <!-- <heart-two-tone two-tone-color="#eb2f96" /> -->
             <div class="icon">
             <icon-font type="icon-shoucang1" />
            <p class="fwz">{{ item.likeCount }}</p>
             </div>

             <div class="icon">
             <icon-font type="icon-xiangmuchengyuan" />
            <p class="fwz">{{ item.readCount }}</p>

             </div>

             <div class="icon">

             <icon-font type="icon-gengduo" />
            <p class="fwz">{{ item.remarkCount }}</p>

             </div>
            <!-- <p class="fwz">作者: syhk</p> -->
            <!-- 作用需要单独写方法进行请求，这里先测试
              利用多表查询返回的结果进行渲染
            -->
           </div>

          
          </a-card
        >
      </a-list-item>
    </template>
  </a-list>
  <!--  -->
  <!-- 分页 -->
  <a-pagination
    class="fy font_zhz slide-in-bottom "
    v-model:current="current"
    simple
    :total="articsum"
    @change="onChange"
  />
    <!-- <a-pagination @change="onChange" v-model:current="current" :total="articsum" show-less-items /> -->
  <!-- 占位使用 -->
  <div style="height:10px">

  </div>
</template>
<script lang="ts">
import { reactive, onMounted, ref } from "vue";
import api from "../api";
import router from '../router'
import { message } from 'ant-design-vue';

import { SmileTwoTone, HeartTwoTone, CheckCircleTwoTone,createFromIconfontCN } from '@ant-design/icons-vue';
const targetOffset = ref<number | undefined>(undefined);
const current = ref<number>(1);
const IconFont = createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_8d5l8fzk5b87iudi.js',
});


// 用文章的 id 标识当前点击的哪个文章,然后进行数据库查找 ,
// 可用 vue 的数据传递把编号传递过去请求加载,也可以存储到浏览器本地对象里面,
// 但是有 bug 直接不经过任何文章点击,而是通过url 进去会出错
function go(id:number) {
  // 加载前先获取到当前点击的文章是哪个
  console.log("当前点击的文章id为:" + id);
  // 使用 api 方法获取文章
  api.getOneByid(id).then((res) => {
    console.log(res);
    router.push({
      path:'/pre',
      // name 和 params 一起用；query 和 path 一起用
      // 这里 name 和 params 一起使用无法传递参数过去，原因暂时未知
      query:{
        articleId:id
      }
    });
  }).catch(err =>{
    message.error("发生了错误："+err.message);
});
}



export default {
  setup(){

  const value = ref<string>('');
  const data = reactive([])
  let searchdata=null;
    const onSearch = (searchValue: string) => {
      console.log('use value', searchValue);
      console.log('or use this.value', value.value);
      // 对 keyword 进行编码处理
      // value.value=encodeURIComponent(value.value);
      // 搜索文章并更新内容
      api.search(value.value).then((res)=>{
        console.log("搜索 key 为： "+value.value);
        console.log("搜索成功："+res);
        message.success("有 bug 暂不能使用")

        // TODO: 后端数据返回不正常
       searchdata=res.data.data; 
      }).catch(err=>{
        message.error(err.message);
      })
    };
      return {
      data,
      targetOffset,
      current,
      go,
      no:true,
      articsum:ref(1),
       value,
      onSearch,
    };

  },
  components:{
    SmileTwoTone,
    HeartTwoTone,
    CheckCircleTwoTone,
     IconFont,
  },
  data() {
  
  },
  mounted() {
    // 使用分页请求每页 6 条数据
    api
      // .getAllblog()
      .getpagingblog(current.value, 6)
      .then((res) => {
        console.log("请求文章开始");
        this.data = res.data.data.records;
        console.log(this.data);
      })
      .catch((err) => message.error("发生了错误：" + err));
    // 获取博客文章总数
    api.getblogcount()
    .then((res)=>{
      console.log("博客文章数为： "+res.data.data)
      // 总页数计算
      this.articsum=res.data.data/6*10;
      console.log(this.articsum);
    }).catch((err)=>{
      message.error("发生了错误: "+err.data.message)
    })
  },
  watch: {
    router(to, from) {
      // 监测路由发生跳转时刷新一次页面
      // window.location.reload();
      // this.$router.go(0);
    },
  },methods:{
// 写一个当分页参改变时自动重新加载数据
  onChange(page: number) {
  console.log(page);
  current.value = page;
  api.getpagingblog(page,6).then((res)=>{
    console.log("开始分页请求：");
    this.data=res.data.data.records;
    console.log(this.data);
    console.log(res);
  }).catch((err)=>{
    message.error(err.message);
  })
}
  }
};

onMounted(() => {
  targetOffset.value = window.innerHeight / 2;
});
</script>

<style lang="scss" scoped>
#app {
  margin: 0;
  padding: 0;
}
.container {
  width: 100vw;
  height: 100vh;
  background: #f0f0f0;
}

.fiveshow{
  width: 100%;
  max-height: 90%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  position: relative;
  top: 34px;
  // text-decoration: underline;
}

.fwz {
  font-family: zhz;
  font-size: 8px;
  color: #7c777791;
}

// 图标样式
.icon {
  position: relative;
  top: -7px;
  
}



.bigbox {
  background: #f0f0f0;
  box-sizing: border-box;
  display: flex;
  justify-content: flex-start;
  // align-items: center;
  flex-direction: row;
  width: calc(100% - 50px);
  height: 100%;
  flex-wrap: wrap;
  margin: 0 auto;
}
.box {
  margin-left: 23px;
  border-radius: 20px;
  position: relative;
  width: 300px;
  height: 240px;
  margin-top: 15px;
  background: #fafafa;
  box-shadow: 40px 40px 40px #cccccc, 0 0 0 #ffffff, 0 0 0 #cccccc inset,
    2px 2px 2px #ffffff inset;
  // width: 60px;
  // height: 60px;
  // background: #f0f0f0;
  // box-shadow: 0 0 0 #cccccc, 0 0 0 #ffffff, 10px 10px 10px #cccccc inset,
  //   -10px -10px 10px #ffffff inset;
  /* animation-fill-mode: forwards; */
  span {
    width: 100px;
    height: 100px;
    left: 7px;
    top: 5px;
    border-radius: 50%;
    // background-color: rgb(217, 216, 221);
    background-image: url("../assets/images/home-cover.jpg");
    background-position: center;
    position: absolute;
    display: inline-block;
  }
  p {
    width: calc(100% - 117px);
    position: absolute;
    left: 113px;
    top: 5px;
    height: 100px;
    background-color: #d1cdcd;
    border-radius: 10px;
    text-overflow: ellipsis;
  }
  .son {
    position: absolute;
    width: calc(100% - 10px);
    height: calc(100% - 117px);
    background-color: #d1cdcd;
    top: 110px;
    left: 7px;
    border-radius: 15px;
  }
}
.box:active {
  animation: anime 3s cubic-bezier(0.16, 1, 0.3, 1) 1s infinite alternate;
}
@keyframes anime {
  0% {
    width: 60px;
    height: 60px;
    background: #f0f0f0;
    box-shadow: 0 0 0 #cccccc, 0 0 0 #ffffff, 10px 10px 10px #cccccc inset,
      -10px -10px 10px #ffffff inset;
  }
  25% {
    width: 60px;
    height: 60px;
    background: #f8f8f8;
    box-shadow: 10px 10px 10px #cccccc, 10px 10px 10px #ffffff,
      0 0 0 #cccccc inset, 0 0 0 #ffffff inset;
  }
  50% {
    width: 60px;
    height: 240px;
    background: #f8f8f8;
    box-shadow: 10px 10px 10px #cccccc, 10px 10px 10px #ffffff,
      0 0 0 #cccccc inset, 0 0 0 #ffffff inset;
  }
  100% {
    width: 480px;
    height: 240px;
    background: #fafafa;
    box-shadow: 40px 40px 40px #cccccc, 0 0 0 #ffffff, 0 0 0 #cccccc inset,
      2px 2px 2px #ffffff inset;
  }
}

.nav {
  background-color: tomato;
  position: absolute;
  width: 100px;
  height: 420px;
  top: 130px;
  left: 0;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  border-radius: 35px;
  .a1 {
    margin-top: 30px;
  }
}

.wz {
  font-size: 14px;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  line-height: 2;
  -webkit-line-clamp: 2;
  position: relative;
  top: 13px;
  // text-decoration: underline;
  color: rgb(103, 151, 153);
}



.fy {
  margin-top: 10px;
  margin-bottom: 50px;
}

.ant-card {
  margin-left: 50px;
  margin-right: 50px;
}
.ant-card-body {
  padding: 9px;
  margin-top: 40px;
}

// 博客文字动画
.focus-in-expand {
  -webkit-animation: focus-in-expand 1.2s cubic-bezier(0.25, 0.46, 0.45, 0.94)
    both;
  animation: focus-in-expand 1.2s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}
@-webkit-keyframes focus-in-expand {
  0% {
    letter-spacing: -0.5em;
    -webkit-filter: blur(12px);
    filter: blur(12px);
    opacity: 0;
  }
  100% {
    -webkit-filter: blur(0px);
    filter: blur(0px);
    opacity: 1;
  }
}
@keyframes focus-in-expand {
  0% {
    letter-spacing: -0.5em;
    -webkit-filter: blur(12px);
    filter: blur(12px);
    opacity: 0;
  }
  100% {
    -webkit-filter: blur(0px);
    filter: blur(0px);
    opacity: 1;
  }
}

.link2{
  padding: 8px;
  margin: auto;
  margin-bottom: 30px;
  border: solid 3px;
  border-top: 0px;
  border-left: 1px;
  border-right: 1px;
  border-radius: 20%;
  width: 50%;
  display: flex;
  justify-content: space-around;
  .link1 {
    height: 95px;
   
  }
}

.link2:hover{
  border-color: tomato;
  border-radius: 0%;
  transition: all 0.8s ease;
  transition-delay: 0.8s;
}

// 图标样式 begin
.icons-list :deep(.anticon) {
  margin-right: 6px;
  font-size: 24px;
}

// 图标样式 end


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
// 底部弹跳
.bounce-in-bottom {
	-webkit-animation: bounce-in-bottom 2.1s both;
	        animation: bounce-in-bottom 2.1s both;
}

@-webkit-keyframes bounce-in-bottom {
  0% {
    -webkit-transform: translateY(500px);
            transform: translateY(500px);
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
    -webkit-transform: translateY(65px);
            transform: translateY(65px);
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
    -webkit-transform: translateY(28px);
            transform: translateY(28px);
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
    -webkit-transform: translateY(8px);
            transform: translateY(8px);
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
@keyframes bounce-in-bottom {
  0% {
    -webkit-transform: translateY(500px);
            transform: translateY(500px);
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
    -webkit-transform: translateY(65px);
            transform: translateY(65px);
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
    -webkit-transform: translateY(28px);
            transform: translateY(28px);
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
    -webkit-transform: translateY(8px);
            transform: translateY(8px);
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

.snak:hover{
  	-webkit-animation: shake-top 0.8s cubic-bezier(0.455, 0.030, 0.515, 0.955) both;
	        animation: shake-top 0.8s cubic-bezier(0.455, 0.030, 0.515, 0.955) both;
}

// 滚入模糊动画 begin 
.roll-in-blurred-left {
	-webkit-animation: roll-in-blurred-left 1.65s cubic-bezier(0.230, 1.000, 0.320, 1.000) both;
	        animation: roll-in-blurred-left 1.65s cubic-bezier(0.230, 1.000, 0.320, 1.000) both;
}

@-webkit-keyframes roll-in-blurred-left {
  0% {
    -webkit-transform: translateX(-1000px) rotate(-720deg);
            transform: translateX(-1000px) rotate(-720deg);
    -webkit-filter: blur(50px);
            filter: blur(50px);
    opacity: 0;
  }
  100% {
    -webkit-transform: translateX(0) rotate(0deg);
            transform: translateX(0) rotate(0deg);
    -webkit-filter: blur(0);
            filter: blur(0);
    opacity: 1;
  }
}
@keyframes roll-in-blurred-left {
  0% {
    -webkit-transform: translateX(-1000px) rotate(-720deg);
            transform: translateX(-1000px) rotate(-720deg);
    -webkit-filter: blur(50px);
            filter: blur(50px);
    opacity: 0;
  }
  100% {
    -webkit-transform: translateX(0) rotate(0deg);
            transform: translateX(0) rotate(0deg);
    -webkit-filter: blur(0);
            filter: blur(0);
    opacity: 1;
  }
}


// end

.dang {
  display: inline-block;
  color: white;
  font-family: zhz;
  font-size: 5em; 
  text-shadow: .03em .03em 0 hsla(230,40%,50%,1);
  }
  .dang:active {
    content: attr(data-shadow);
    position: absolute;
    top: .06em; left: .06em;
    z-index: -1;
    text-shadow: none;
    background-image:
      linear-gradient(
        45deg,
        transparent 45%,
        hsla(48,20%,90%,1) 45%,
        hsla(48,20%,90%,1) 55%,
        transparent 0
        );
    background-size: .05em .05em;
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    animation: shad-anim 15s linear infinite;
    }

@keyframes shad-anim {
  0% {background-position: 0 0}
  0% {background-position: 100% -100%}
  }

  .search{
    margin-bottom: 10px; 
  }

</style>
