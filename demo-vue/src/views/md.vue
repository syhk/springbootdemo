<template>
  <div class="container">
    <div class="header">
      <h1 class="font_zhz">编写博客</h1>
    </div>
    <div class="body">
      <textarea
        id="textarea_field"
        v-model="value2"
        class="nes-textarea font_zhz"
      ></textarea>
      <v-md-editor class="font_zhz" v-model="text" height="700px" @copy-code-success="handleCopyCodeSuccess"></v-md-editor>
    </div>

    <div class="tail font_zhz">
      <a-button type="primary" @click="showModal">返回</a-button>
      <a-modal
        class="font_zhz"
        v-model:visible="visible"
        title="返回至首页"
        @ok="handleOk"
      >
        <p>返回后编写的内容将不存在</p>
      </a-modal>
      <a-button type="primary" @click="rel" danger>发布</a-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import router from "../router/index";
import api from "../api/index";
import { message } from "ant-design-vue";
let text = ref<string>("# 请使用 markdown 语法编写博客");
const value2 = ref<string>("请输入标题，最多不超过 100 字");

interface article {
  title: any;
  context: any;
}

 function  handleCopyCodeSuccess(code) {
      console.log(code);
    }

// 返回按钮返回首页
const visible = ref<boolean>(false);
const showModal = () => {
  visible.value = true;
};

const handleOk = (e: MouseEvent) => {
  visible.value = false;
  router.push("/");
};

function rel() {
  if (
    value2.value == "请输入标题，最多不超过 100 字" ||
    value2.value.length === 0
  ) {
    message.error("请输入标题！");
  } else {
    console.log(text);
    if (text.value.length < 100) {
      message.error("正文字数太少!");
    } else {
      const act: article = {
        title: value2.value,
        context: text.value,
      };
      console.log("act" + act);
      // 上传至数据库
      api
        .putblog(act.title, act.context)
        .then((res) => {
          console.log(res);
          if (res.data.flag === true) {
            console.log(res.data.message);
            message.success("博客发布成功!")
            router.push("/blog");
          }
        })
        .catch((err) => {
          message.error("博客发布失败"+err);
        });
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  height: 900px;
  min-width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
.header {
}

.body {
  min-width: 100%;
}

.tail {
  display: flex;
  justify-content: space-around;
}
</style>
