<template>
  <div>
    <img :src="avatarUrl" width="100">
    <input type="file" ref="fileInput" @change="handleFileChange">
  </div>
</template>

<script lang="ts" >
import axios from 'axios';

export default {
  data() {
    return {
      avatarUrl: ''
    };
  },
  methods: {
    async handleFileChange(event) {
      const file = event.target.files[0];
      const formData = new FormData();
      formData.append('file', file);
      const response = await axios.post('/api/avatar', formData);
      this.avatarUrl = response.data;
    }
  }
};
</script>