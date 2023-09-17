<template>
  <div>
    <h1>注销用户</h1>
    <p>您确定要注销吗？</p>
    <button @click="logout">注销</button>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      // 发送请求到后端以删除用户
      this.$axios
        .delete(`/api/user/${this.userId}`) // 假设您的删除用户API接口为 DELETE /api/user/{userId}
        .then((response) => {
          // 处理成功的响应，例如显示成功消息，导航到登录页面等
          console.log('用户已注销');
          this.$router.push('/login'); // 导航到登录页面或其他目标页面
        })
        .catch((error) => {
          // 处理错误，例如显示错误消息
          console.error('注销失败', error);
        });
    },
  },
};
</script>

<style>
</style>