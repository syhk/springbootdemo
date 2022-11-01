<template>
  <div class="container">
    <div class="bigbox">
      <div class="box" v-for="(item, index) in bloglist" :key="index">
        <span>{{ item.img }}</span>
        <p>{{ item.title }}</p>
        <div class="son">{{ item.content }}</div>
      </div>
      <!-- <div class="box">
        <span></span>
        <p>title</p>
        <div class="son">content</div>
      </div>
      <div class="box">
        <p>title</p>
        <div class="son">content</div>
      </div>
      <div class="box">
        <span></span>
        <p>title</p>
        <div class="son">content</div>
      </div>
      <div class="box">
        <span></span>
        <p>title</p>
        <div class="son">content</div>
      </div> -->
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  setup() {
    interface blog {
      img?: String;
      title: String;
      content: String;
    }

    const bloglist: blog[] = new Array(
      {
        img: "null",
        title: "syhk1",
        content: "java",
      },
      {
        img: "1",
        title: "syhk2",
        content: "cpp",
      },
      {
        img: "2",
        title: "syhk2",
        content: "ts",
      },
      {
        img: "3",
        title: "syhk3",
        content: "rust",
      },
      {
        img: "4",
        title: "syhk4",
        content: "go",
      },
      {
        img: "5",
        title: "syhk5",
        content: "syhk",
      },
      {
        img: "5",
        title: "syhk5",
        content: "syhk",
      }
    );

    console.log(bloglist);

    return {
      bloglist,
    };
  },
});
</script>

<style lang="scss" scoped>
#app {
  padding: 0;
  margin: 0;
}

.container {
  width: 100vw;
  height: 100vh;
  background: #f0f0f0;
}

.bigbox {
  background: #f0f0f0;
  box-sizing: border-box;
  display: flex;
  justify-content: flex-start;
  align-items: center;
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
    background-color: rgb(217, 216, 221);
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
</style>
