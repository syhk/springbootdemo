<!-- 博客内容浏览页面 -->

<template>
  <div class="nes-container with-title is-centered">
    <p class="title font_zhz">阅读使我们进步</p>
    <p class="font_zhz wz">
      Because not sure, will be looking forward to tomorrow
    </p>
    <i class="nes-kirby bounce-top"></i>
  </div>
  <!-- 标题模块 -->
  <div class="artitle font_zhz">
    <p>{{ title }}</p>
  </div>
  <!-- 内容模块 -->
  <v-md-preview style="min-height: 300px" :text="text"></v-md-preview>

  <!-- 发布评论 -->
    <a-space>
  <icon-font type="icon-gengduo" /> 
    </a-space>
  <div class="rem">
  <a-textarea  autoSize=true  v-model:value="value2" show-count :maxlength="500" placeholder="欢迎留言评论" />
  </div>

  <a-button @click="submit()" class="remfb" type="primary">发布评论</a-button>

  <!-- 评论模块 -->
  <div class="comment">
    <a-comment v-for="item in remark" :key="item.id">
      <template #actions>
        <span key="comment-basic-like">
          <a-tooltip title="Like">
            <template v-if="action === 'liked'">
              <LikeFilled @click="like" />
            </template>
            <template v-else>
              <LikeOutlined @click="like" />
            </template>
          </a-tooltip>
          <span style="padding-left: 8px; cursor: auto">
            {{ likes }}
          </span>
        </span>
        <span key="comment-basic-dislike">
          <a-tooltip title="Dislike">
            <template v-if="action === 'disliked'">
              <DislikeFilled @click="dislike" />
            </template>
            <template v-else>
              <DislikeOutlined @click="dislike" />
            </template>
          </a-tooltip>
          <span style="padding-left: 8px; cursor: auto">
            {{ dislikes }}
          </span>
        </span>
        <span key="comment-basic-reply-to">Reply to</span>
      </template>
      <template #author><a>{{ item.name }}</a></template>
      <template #avatar>
        <img :src="item.avatarurl" alt="头像" />
        <!-- <a-avatar src="../assets/images/20.jpg" alt="Han Solo" /> -->
      </template>
      <template #content>
        <p>{{ item.context }}</p>
      </template>
      <template #datetime>
        <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
          <!-- <span>{{ dayjs().fromNow() }}</span> -->
          <span>{{ item.createTime }}</span>
        </a-tooltip>
      </template>
    </a-comment>
  </div>
</template>

<!-- 
  TODO: 待解决问题：Like 和 dislike 全部评论都是同步的
   更改表结构为评论表新增一个主键 id ，用于区分各个不同的评论；已经完成：添加字段为 remark_id
 -->

<script lang="ts">
import dayjs from "dayjs";
import { createFromIconfontCN } from '@ant-design/icons-vue';

import {
  LikeFilled,
  LikeOutlined,
  DislikeFilled,
  DislikeOutlined,
} from "@ant-design/icons-vue";
import { defineComponent, ref,reactive } from "vue";
import relativeTime from "dayjs/plugin/relativeTime";
import { useRoute } from "vue-router";
import router from "../router";
import api from '../api'
import { message } from "ant-design-vue";
dayjs.extend(relativeTime);
// 文章标题和内容 begin
    let title="";
    let text=""; 
    let tonxian=ref<string>("");
//  end========
 const likes = ref<number>(0);
    const dislikes = ref<number>(0);
    const action = ref<string>();

    const like = () => {
      likes.value = 1;
      dislikes.value = 0;
      action.value = "liked";
    };
    let value2 = ref<string>('');
    let remark=reactive([])

const IconFont = createFromIconfontCN({
  scriptUrl: '//at.alicdn.com/t/font_8d5l8fzk5b87iudi.js',
});
    const dislike = () => {
      likes.value = 0;
      dislikes.value = 1;
      action.value = "disliked";
    };
    // 获取当前时间参数用于渲染到评论上
    // const getDate = () => {
    //   const now = new Date();
    //   const year = now.getFullYear();
    //   const month = now.getMonth() + 1;
    //   const day = now.getDate();
    //   const hour = now.getHours();
    //   const minute = now.getMinutes();
    //   const second = now.getSeconds();
    //   return `${year}-${month}-${day} ${hour}:${minute}:${second}`;}
    //   // 定义响应式数据
    //   let nowTime=getDate();
    //   let now=ref(nowTime);


    // 提交评论
    function submit(){
      api.submitremark(localStorage.getItem('arid'),localStorage.getItem('userid'),value2.value,0,0).then((res)=>{
        console.log("评论提交成功");
        message.success("评论提交成功");
        console.log(res)
        // 提交成功后，清除输入框内容，重新加载评论
        value2.value="";
      api.getRemark(localStorage.getItem('arid')).then((res)=>{
        console.log(res);
        remark=res.data.data;
        // 提交成功后刷新下网页
        window.location.reload();
      });

      }).catch(err => {
        console.log(err.message);
        message.error("评论提交失败:"+err.message);
      })

    }
export default{
  data(){
  return {
      likes,
      dislikes,
      action,
      like,
      dislike,
      dayjs,
      text ,
      title,
      remark,
      value2,
      submit,
      tonxian
    };
  },  created(){
    // 根据 id 请求对应文章数据
    // 接收路由传递过来的参数 id
    const route = useRoute();
    let articleId=route.query.articleId;
    // 把文章 id 存储起来
    if(articleId !== null)
    localStorage.setItem('arid',articleId);
    console.log("路由传递的文章 id 参数为：===="+articleId);// 数据参数接收正常
     // 进行后台请求
      // 发送请求
      api.getOneByid(articleId).then((res)=>{
       console.log("文章数据请求成功");
       console.log(res.data);
       
      //  加载到页面上
       this.title=res.data.data.title;
       console.log(this.title);
       this.text=res.data.data.articleMd;
       console.log(this.text);
      }).catch((err)=>{
        message.error("文章数据请求失败"+err);
      })

      // 请求评论
      api.getRemark(articleId).then((res)=>{
        console.log("评论数据请求成功");
        console.log(res);
        this.remark=res.data.data;
        // data.id,createTime,high,low,context
        // high 和 low 数据重新渲染不渲染到评论里面
        console.log(this.remark);
      }).catch((err)=>{
        message.error("评论数据请求失败"+err);
      })

      api.getUserIdByEmail(localStorage.getItem('email')).then(res=>{
    let        id = res.data.data;
    localStorage.setItem("userid",id);
      }).catch(err=>{
        console.log(err.message);
        message.error("用户数据请求失败："+err.message);
      })    
  },
  components: {
    LikeFilled,
    LikeOutlined,
    DislikeFilled,
    DislikeOutlined,
    IconFont,
  },
};
</script>

<style lang="scss" scoped>
// 标题 begin
.artitle {
  margin-top: 30px;
  font-size: 30px;
}

//标题 end
.container {
  display: flex;
  padding: 96px 24px 48px;
  justify-content: center;
  align-items: center;
  background: #f3f1fe;
}

.card {
  width: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background: #fff;
  border-radius: 10px;
  margin: 8px;
  box-shadow: 0 0 5px -2px rgba(0, 0, 0, 0.1);
}

.card figure {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  margin-top: -60px;
  position: relative;
}

.card figure::before {
  content: "";
  border-radius: inherit;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 100%;
  height: 100%;
  transform: translate(-50%, -50%);
  border: 1rem solid #f3f1fe;
  box-shadow: 0 1px rgba(0, 0, 0, 0.1);
}

.card figure img {
  border-radius: inherit;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card .content {
  // text-align: center;
  margin: 2rem;
  line-height: 1.5;
  color: #101010;
}

.wz {
  font-size: 15px;
}

.nes-container {
  border: solid 3px;
  //   margin-left: 5px;
  //   margin-right: 5px;
  // margin-top: 30px;
  max-height: 177px;
}

// 评论模块
.comment {
  padding: 50px;
  font-family: zhz;
}


// 弹跳动画 begin 
.bounce-top {
	-webkit-animation: bounce-top 2.3s infinite;
	        animation: bounce-top 2.3s infinite;
}

.bounce-top:active {
	-webkit-animation: bounce-top 0.9s both;
	        animation: bounce-top 0.9s both;
}

@-webkit-keyframes bounce-top {
  0% {
    -webkit-transform: translateY(-45px);
            transform: translateY(-45px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
    opacity: 1;
  }
  24% {
    opacity: 1;
  }
  40% {
    -webkit-transform: translateY(-24px);
            transform: translateY(-24px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  65% {
    -webkit-transform: translateY(-12px);
            transform: translateY(-12px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  82% {
    -webkit-transform: translateY(-6px);
            transform: translateY(-6px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  93% {
    -webkit-transform: translateY(-4px);
            transform: translateY(-4px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  25%,
  55%,
  75%,
  87% {
    -webkit-transform: translateY(0px);
            transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  100% {
    -webkit-transform: translateY(0px);
            transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
    opacity: 1;
  }
}
@keyframes bounce-top {
  0% {
    -webkit-transform: translateY(-45px);
            transform: translateY(-45px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
    opacity: 1;
  }
  24% {
    opacity: 1;
  }
  40% {
    -webkit-transform: translateY(-24px);
            transform: translateY(-24px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  65% {
    -webkit-transform: translateY(-12px);
            transform: translateY(-12px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  82% {
    -webkit-transform: translateY(-6px);
            transform: translateY(-6px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  93% {
    -webkit-transform: translateY(-4px);
            transform: translateY(-4px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  25%,
  55%,
  75%,
  87% {
    -webkit-transform: translateY(0px);
            transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  100% {
    -webkit-transform: translateY(0px);
            transform: translateY(0px);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
    opacity: 1;
  }
}

// end

.rem{
  margin-left: 30px;
  margin-right: 30px;
  border: solid 1px rgb(4, 148, 214);
  // border-top: 0px;
}
.rem:hover{
  border: solid 1px tomato;
  transition: all 1s ease;
}


.icons-list :deep(.anticon) {
  margin-right: 6px;
  font-size: 44px;
}

.remfb {
  position: relative;
  left: 46%;
  top:20px;
}
.remfb:hover{
  background-color: tomato;
  border-radius: 15px;
  transition: all 0.5s ease;
  color: #101010;
}


</style>
