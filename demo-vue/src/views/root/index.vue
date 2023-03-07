<!-- 选择路由：正常首页，或者管理首页 -->
<template>
<div  class="header">
<h1>选择要加载的页面(root用户)</h1>
<div class="rlbox">
<router-link class="rl" to="/">博客页面</router-link>
<router-link class="rl" to="/manager">后台管理</router-link>
</div>
</div>


      
</template>


<script lang="ts" >


</script>



<style lang="scss" scoped>

.header{
    position: relative;
    top: 150px;

    h1{
        color: tomato;
    }
}

.gif{
    width: 200px;
    height: 200px;

}


.rlbox{
    display: flex;
    align-items: center;
    justify-content: space-around;
}

.rl{
    position: relative;
    top: 150px;
    color: linear-gradient( #00dbde);
    font-size: 30px;
    text-decoration: underline;

}

.rl:hover{
    color: rgb(219, 56, 97);

}



</style>


