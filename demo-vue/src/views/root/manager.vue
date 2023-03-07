<!-- 管理首页 -->

<template>
  <a-menu v-model:selectedKeys="current" mode="horizontal">
    <a-sub-menu key="sub1">
      <template #icon>
        <setting-outlined class="set" />
      </template>
      <template #title>管理</template>
      <a-menu-item-group title="博客管理">
        <a-menu-item @click="changeyh" key="setting:1">文章管理</a-menu-item>
      </a-menu-item-group>
      <a-menu-item-group title="用户管理">
        <a-menu-item @click="changeman()" key="setting:3">注册用户</a-menu-item>
      </a-menu-item-group>
    </a-sub-menu>
    <a-menu-item  key="alipay">
        <home-outlined  class="re" />
      <router-link to="/">返回首页</router-link>
    </a-menu-item>
  </a-menu>
<!-- 渲染用户表格 -->
<div class="tablediv" v-if="flag">
    <p class="tabm">用户管理</p>
<!--  flag 实现监听用户点击哪个就渲染哪个 -->
 <a-table   :columns="usercolumns" :data-source="userdataSource" bordered>
    <template #bodyCell="{ column, text, record }">
      <template class="usertable" v-if="['name', 'avatarUrl', 'gender','email','pwd','status','create_time','role','qm'].includes(usercolumns.dataIndex)">
        <div>
          <a-input
            v-if="editableData[record.key]"
            v-model:value="editableData[record.key][column.dataIndex]"
            style="margin: -5px 0"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <div class="editable-row-operations">
          <span v-if="editableData[record.key]">
            <a-typography-link @click="save(record.key)">Save</a-typography-link>
            <a-popconfirm title="Sure to cancel?" @confirm="cancel(record.key)">
              <a>Cancel</a>
            </a-popconfirm>
          </span>
          <span v-else>
            <a @click="edit(record.key)">Edit</a>
          </span>
        </div>
      </template>
    </template>
  </a-table>
</div>
<!-- 渲染文章表格 -->
<div class="tablediv"  v-else>
    <p class="tabm">文章管理</p>
 <a-table  :columns="columns" :data-source="dataSource" bordered>
    <template #bodyCell="{ column, text, record }">
      <template v-if="['name', 'age', 'address'].includes(column.dataIndex)">
        <div>
          <a-input
            v-if="editableData[record.key]"
            v-model:value="editableData[record.key][column.dataIndex]"
            style="margin: -5px 0"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <div class="editable-row-operations">
          <span v-if="editableData[record.key]">
            <a-typography-link @click="save(record.key)">Save</a-typography-link>
            <a-popconfirm title="Sure to cancel?" @confirm="cancel(record.key)">
              <a>Cancel</a>
            </a-popconfirm>
          </span>
          <span v-else>
            <a @click="edit(record.key)">Edit</a>
          </span>
        </div>
      </template>
    </template>
  </a-table>




</div>




</template>
<script lang="ts">
import { defineComponent, ref,reactive } from 'vue';
import { MailOutlined, AppstoreOutlined, SettingOutlined, FlagFilled } from '@ant-design/icons-vue';
import {message} from 'ant-design-vue'
import api from '../../api/index'

import {
  HomeOutlined,
  SettingFilled,
  SmileOutlined,
  SyncOutlined,
  LoadingOutlined,
} from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import type { UnwrapRef } from 'vue';

//  文章列数组 
const columns = [
  {
    title: '作者',
    dataIndex: 'author',
    width: '15%',
  },
  {
    title: '类型',// 文章类型 eg: cpp java c ...
    dataIndex: 'summary',
    width: '10%',
  },
  {
    title: '标题',
    dataIndex: 'title',
    width: '15%',
  },
  {
    title: '发布时间',
    dataIndex: 'create_time',
    width: '15%',
  },
  {
    title: '收藏数',
    dataIndex: 'like_count',
    width: '10%',
  },
  {
    title: '评论数',
    dataIndex: 'remark_count',
    width: '10%',
  },
  {
    title: '阅读数',
    dataIndex: 'read_count',
    width: '10%',
  },
  {
    title: '内容',
    dataIndex: 'context',
    width: '20%',
  },
  {
    title: '操作',
    dataIndex: 'operation',
  },
];

//  文章接口
interface DataItem {
  key: string;
  author: string;
  summary: string;
  title: string;
  create_time:string;
  like_count:number;
  remark_count:number;
  read_count:number;
  context:string;
}

//  用户列数组 
const usercolumns = [
  {
    title: '名称',
    dataIndex: 'name',
    width: '15%',
  },
  {
    title: '头像',// 文章类型 eg: cpp java c ...
    dataIndex: 'avatarUrl',
    width: '10%',
  },
  {
    title: '性别',
    dataIndex: 'gender',
    width: '10%',
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: '15%',
  },
  {
    title: '密码',
    dataIndex: 'pwd',
    width: '10%',
  },
  {
    title: '是否禁用',
    dataIndex: 'status',
    width: '10%',
  },
  {
    title: '注册时间',
    dataIndex: 'create_time',
    width: '10%',
  },
  {
    title: '权限',
    dataIndex: 'role',
    width: '10%',
  },
  {
    title: '签名',
    dataIndex: 'qm',
    width: '10%',
  },
  {
    title: '操作',
    dataIndex: 'operation',
  },
];


// 用户接口
interface Datauser{
    name:string,
    avatarUrl:string,
    gender:number,
    email:string,
    pwd:string,
    status:number,
    create_time:string,
    role:number,
    qm:string
}


// 文章数据源
let data: DataItem[] = [];
// user 数据源
let datauser: Datauser[] = [];
    // 文章数据渲染
  let dataSource = ref(data);
    //   用户数据渲染
  let userdataSource = ref(datauser);

// for (let i = 0; i < 100; i++) {
//   data.push({
//     key: i.toString(),
//     author: `Edrward ${i}`,
//     summary: 'cpp',
//     address: `London Park no. ${i}`,
//   });
// }

export default defineComponent({
  components: {
    MailOutlined,
    AppstoreOutlined,
    SettingOutlined,
    HomeOutlined,
    SettingFilled,
    SmileOutlined,
    SyncOutlined,
    LoadingOutlined,
  },
  setup() {
    const current = ref<string[]>(['mail']);



    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const edit = (key: string) => {
      editableData[key] = cloneDeep(dataSource.value.filter(item => key === item.key)[0]);
    };
    const save = (key: string) => {
      Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);
      delete editableData[key];
    };
    const cancel = (key: string) => {
      delete editableData[key];
    };
    let flag=ref<Boolean>(true)
    function changeman ()  {
        console.log("changeman 点击事件");
        flag.value=true;
    }
    const changeyh= () =>{
        console.log("changeyh 点击事件");
        flag.value=false;
    }
    // 点击用户管理事件


    return {
      current,
      dataSource,
      userdataSource,
      columns,
      editingKey: '',
      editableData,
      edit,
      save,
      cancel,
      flag,
      changeman,
      changeyh,
      usercolumns
    };
  },
  created(){

    // 默认加载用户数据
    api.getAll().then(res=>{
        console.log(res.data);
        for(let i = 0 ; i < res.data.data.length ; i++)
        {
            // 把数据存放入 datauser 中
            let temp:Datauser={
                name:res.data.data[i].name,
                avatarUrl:res.data.data[i].avatarUrl,
                gender:res.data.data[i].gender,
                email:res.data.data[i].email,
                pwd:res.data.data[i].pwd,
                status:res.data.data[i].status,
                create_time:res.data.data[i].create_time,
                role:res.data.data[i].role,
                qm:res.data.data[i].qm
            }
            datauser.push(temp)
        }
        console.log(datauser);
    }).catch(err => {
        message.error("发生了错误："+err.message);
    });

    // 文章数据也加载进去
    api.getallblog().then(res=>{
        console.log(res.data);
        for(let i = 0 ; i < res.data.data.length ; i++)
        {
            // 把数据存放入 data 中
            let temp:DataItem={
                key:res.data.data[i].id,
                author:res.data.data[i].author,
                summary:res.data.data[i].summary,
                title:res.data.data[i].title,
                create_time:res.data.data[i].create_time,
                like_count:res.data.data[i].like_count,
                remark_count:res.data.data[i].remark_count,
                read_count:res.data.data[i].read_count,
                context:res.data.data[i].context,
            }
            data.push(temp)
        }
        console.log(data);

    }).catch(err =>{
        message.error("发生了错误："+err.message);
    })
  },mounted(){
  }
});
</script>


<style lang="scss" scoped>

.re{
    position: relative;
    top: -4px;
    left: -8px;
}
.set{
    position: relative;
    top: -3px;


}

.editable-row-operations a {
  margin-right: 8px;
}

.tabm{
    font-size: 25px;
    margin-top: 30px;
}

.tablediv{
  max-height: 80%;
}



</style>