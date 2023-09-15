<template>
  <div class="back-button" @click="goBack">
    <i class="fa fa-chevron-left" style="color: white;"></i> 
  </div>
</template>

<script>
export default {
  methods: {
    goBack() {
      this.$router.go(-1); // 返回上一页
    }
  }
};
</script>

<style scoped>
.back-button {
  position: absolute;
  top: 13px;
  left: 8px;
  cursor: pointer;
  font-size: 18px;
  display: flex;
  align-items: center;
}

.back-button i {
  margin-right: 5px;
}
</style>