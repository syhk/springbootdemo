<template>
  <div class="container">
    <div class="card">
      <figure class="rotate-center">
        <img  alt="" :src=avatarUrl />
      </figure>
      <p class="name">
        <b>{{ name }}</b>
      </p>
      <p class="content">
        {{ qm }}
      </p>
      <!--性别 -->
      <div>
        <a-radio-group class="font_zhz" v-model:value="value">
          <a-radio :value="1">男</a-radio>
          <a-radio :value="2">女</a-radio>
        </a-radio-group>
      </div>
    </div>
    <!-- ================================= -->
    <div>
      <!-- 生日 -->
      <div class="nes-container with-title is-centered font_zhz">
        <p class="title">生日:{{ value1 }}</p>
        <p>Because not sure, will be looking forward to tomorrow</p>
      </div>
      <!-- 更改 -->
      <div class="font_zhz">
        更改生日:
        <a-space direction="vertical" :size="12">
          <a-date-picker v-model:value="value1" />
        </a-space>
      </div>

      <!-- 更改名称 -->
      <div class="mc font_zhz">
        <a-input v-model:value="name" :bordered="false" />
      </div>

      <!-- 更改签名 -->
      <div class="qm font_zhz">
        <a-input v-model:value="qm" :bordered="false" />
      </div>
    </div>

    <!-- 更换头像 -->
     <a-upload
    v-model:file-list="fileList"
    name="avatar"
    list-type="picture-card"
    class="avatar-uploader"
    :show-upload-list="false"
    action="http://localhost:8800/user/upheadpor"
    :before-upload="beforeUpload"
    @change="handleChange"
  >
    <img style="width:260px;height: 100px;overflow: hidden;border-radius: 50%;" v-if="imageUrl" :src="imageUrl" alt="avatar" />
    <div v-else>
      <loading-outlined v-if="loading"></loading-outlined>
      <plus-outlined v-else></plus-outlined>
      <div class="ant-upload-text">更改头像</div>
    </div>
  </a-upload>
    <!-- 保存信息 -->
    <div class="save font_zhz">
      <button @click="re" type="button" class="nes-btn is-primary">返回</button>
      <button @click="save" type="button" class="nes-btn is-success">
        保存
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import type { Dayjs } from "dayjs";
import dayjs from "dayjs";
import router from "../router";
import api from '../api/index'
import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import type { UploadChangeParam, UploadProps } from 'ant-design-vue';
function getBase64(img: Blob, callback: (base64Url: string) => void) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result as string));
  reader.readAsDataURL(img);
}

    // 拿到当前登录用户信息
  let user =  localStorage.getItem("email");
  console.log("个人中心： "+user);
export default defineComponent({
  components:{
        LoadingOutlined,
    PlusOutlined,
  },
  setup() {
    const value = ref<number>(1);
    let value1 = ref<Dayjs>();
    let name = ref<string>("syhk");
    const qm = ref<string>("We need some creative ideas（更改签名）");
    const avatarUrl=ref<string>("");

      const fileList = ref([]);
    const loading = ref<boolean>(false);
    const imageUrl = ref<string>('');

    const handleChange = (info: UploadChangeParam) => {
      if (info.file.status === 'uploading') {
        loading.value = true;
        return;
      }
      if (info.file.status === 'done') {
        // Get this url from response in real world.
        getBase64(info.file.originFileObj, (base64Url: string) => {
          imageUrl.value = base64Url;
          loading.value = false;
          // 使用一个 api 获取头像连接，上传完成更新头像
          api.getavatarUrl(localStorage.getItem('email'))
          .then((res)=>{
              avatarUrl.value=res.data.data;
              console.log("头像请求成功");
              console.log(res);
              console.log(avatarUrl.value);
          }).catch((err)=>{
            message.error("头像数据请求error: "+err.message);
          })
        });
      }
      if (info.file.status === 'error') {
        loading.value = false;
        message.error('上传失败');
      }
    };

    const beforeUpload = (file: UploadProps['fileList'][number]) => {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        message.error('只能上传图片文件!');
      }
      const isLt4M = file.size / 1024 / 1024 < 4;
      if (!isLt4M) {
        message.error('上传的图像必须小于 4M!');
      }
      return isJpgOrPng && isLt4M;
    };


    return {
      value,
      value1,
      qm,
      re,
      save,
      name,
       fileList,
      loading,
      imageUrl,
      handleChange,
      beforeUpload,
      avatarUrl
    };



    // 按钮事件
    function re() {
      router.push("/");
    }
    function save() {
      // 上传信息至数据库
      // 头像先上传空
      const abs=dayjs(
        JSON.parse(JSON.stringify(value1.value))
      ).format("YYYY-MM-DD")
      console.log("转换格式后的日期： "+abs);
      if(avatarUrl.value=== null){
        avatarUrl.value="=muoren.jpg";
      }
      // 处理 avatarUrl ，截取 = 后面所有字符 
      const avatarUrl1=avatarUrl.value.substring(avatarUrl.value.indexOf("=")+1); 
      api.updateUser(avatarUrl1,name.value,qm.value,value.value,abs,localStorage.getItem("email")).then((res)=>{
          message.success("信息修改成功");
        if(res.data.data.flag === true)
        {
          message.success("信息修改成功");
          localStorage.setItem("userInfo",res.data.data)
        }
      }).catch((err)=>{
        message.error("信息修改失败："+err.message);
      })

    }

    // 更换头像事件
    function changeheadpro(){

    }


  },
  created(){
  // 拿取用户信息
  api.getUserByEmail(localStorage.getItem("email")).then((res)=>{
    console.log(res);
    this.name = res.data.data.name;
    this.qm = res.data.data.qm;
    this.value1 = dayjs(res.data.data.birthday);
    this.avatarUrl=res.data.data.avatarUrl;

  }) .catch((err)=>{
    message.error("用户信息获取失败："+err);
  })




  },
  watch: {
    router(to, from) {
      // 监测路由发生跳转时刷新一次页面
      window.location.reload();
    },
  },
});
</script>

<style lang="scss" scoped>
// 头像动画类========================begin
.rotate-center:hover {
  -webkit-animation: rotate-center 0.6s ease-in-out both;
  animation: rotate-center 0.6s ease-in-out both;
}
@-webkit-keyframes rotate-center {
  0% {
    -webkit-transform: rotate(0);
    transform: rotate(0);
  }
  100% {
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@keyframes rotate-center {
  0% {
    -webkit-transform: rotate(0);
    transform: rotate(0);
  }
  100% {
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}

// ===================================end
.container {
  display: flex;
  padding: 96px 24px 48px;
  justify-content: center;
  align-items: center;
  flex-direction: column;
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
  text-align: center;
  margin: 2rem;
  line-height: 1.5;
  color: #101011;
  font-family: zhz;
}

.name {
  font-size: 15px;
  font-size: medium;
  font-family: zhz;
}

// 生日框
.nes-container {
  //  border-style: none;
  margin-top: 5px;
  border: solid 2px rgb(215, 209, 216);
}

// 签名
.qm {
  margin-top: 20px;
  border: solid 3px;
  border-top: 0px;
  // border-left: 1px;
  border-right: 0px;
  border-radius: 10px;
}

// 名称
.mc {
  margin: auto;
  width: 200px;
  margin-top: 20px;
  border: solid 3px;
  border-top: 0px;
  border-left: 1px;
  // border-right: 0px;
  border-radius: 10px;
}
.save {
  margin-top: 50px;
  width: 700px;
  display: flex;
  justify-content: space-around;
}
.avatar-uploader > .ant-upload {
  width: 128px;
  height: 128px;
  overflow: hidden;
}
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
