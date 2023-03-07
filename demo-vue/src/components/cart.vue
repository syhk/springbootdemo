<template>
  <a-card hoverable style="width: 300px">
    <template #cover>
      <img
        alt="头像"
        :src=avatarUrl
      />
    </template>
    <template #actions>
      <setting-outlined key="setting" />
      <edit-outlined key="edit" />
      <ellipsis-outlined key="ellipsis" />
    </template>
    <a-card-meta class="font_zhz" :title="name" :description="email">
    </a-card-meta>
  </a-card>
</template>
<script lang="ts">
import {
  SettingOutlined,
  EditOutlined,
  EllipsisOutlined,
} from "@ant-design/icons-vue";

import { defineComponent,ref } from "vue";
let name =ref<string>(localStorage.getItem("name"));
let email =ref<string>(localStorage.getItem("email"));
let avatarUrl=ref<string>("")
export default defineComponent({
  data() {
    return { name, email,avatarUrl };
  },
  components: {
    SettingOutlined,
    EditOutlined,
    EllipsisOutlined,
  },
  created(){
    // 获取用户头像
    let userInfo = localStorage.getItem("userInfo");
    userInfo=JSON.parse(userInfo);
    console.log(userInfo);
    let ava="http://localhost:8800/user/downheadpor?name="+userInfo.avatarUrl;
  this.avatarUrl=ava;
  console.log(this.avatarUrl);
  
    



  }
});
</script>
