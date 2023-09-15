<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>用户评论</p>
		</header>
		<!--评论区部分-->
		<ul class="remark-box">
			<li v-for="item in remarkArr">
				<div class="id">{{item.userName}}:</div>
				<div class="content">{{item.remark}}</div>
				<!-- <i class="fa fa-remove" @click="deleteRemark(this.remark)"></i> -->
			</li>
		</ul>
		<!-- 发表评论部分 -->
		<div class="search">
			<div class="search-content">
				<p>评论：</p>
			</div>
			<input type="text" v-model="remarks" placeholder="请友善发言,恶语伤人心">
			<i class="fa fa-mail-reply" @click="userRemark(remarks)"></i>
		</div>
		<!--底部部分-->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Index',
		data() {
			return {
				businessId: this.$route.query.businessId,
				remarks: '',
				remarkArr: [],
				user: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');

			this.$axios.post('RemarkController/listRemarksByBussinessId', this.$qs.stringify({
				businessId: this.businessId
			})).then(response => {
				this.remarkArr = response.data;
			}).catch(error => {
				console.error(error); //输出错误信息
			});
		},
		methods: {
			userRemark(remarks) {
				if (this.remarks == '') {
					alert('评论不能为空！');
					return;
				}
				this.$axios.post('RemarkController/saveRemarks', this.$qs.stringify({
					remark: this.remarks,
					userId: this.user.userId,
					businessId: this.businessId,
					userName: this.user.userName
				})).then(response => {
					if (response.data > 0) {
						alert('评论成功！');
						this.$axios.post('RemarkController/listRemarksByBussinessId', this.$qs.stringify({
							businessId: this.businessId
						})).then(response => {
							this.remarkArr = response.data;
						}).catch(error => {
							console.error(error); //输出错误信息
						});
						this.remarks='';
					} else {
						alert('评论失败！');
					}
				}).catch(error => {
					console.error(error);
				});
			}
		},
		components: {
			Footer
		}
	}
</script>
<style scoped>
	.wrapper {
		width: 100%;
		height: 100zh;
	}

	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;

		display: flex;
		justify-content: center;
		align-items: center;
		
		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;
	}

	/****************** 评论区 ******************/
	.wrapper .remark-box {
		width: 100%;
		margin-top: 12vw;
		margin-bottom: 24vw;
	}

	.wrapper .remark-box li {
		width: 100%;
		height: 7vw;

		border: 1px solid #000000;
		margin-bottom: 0.2vw;
		border-radius: 0.4vw;

		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.wrapper .remark-box li .id {
		margin-left: 8vw;
	}

	.wrapper .remark-box li .content {
		margin-left: 5vw;
	}

	/****************** 评论 *****************/

	.wrapper .search {
		width: 100%;
		height: 10vw;
		background-color: #0097FF;
		position: fixed;
		bottom: 14vw;
		display: flex;
		justify-content: space-around;
		align-items: center;
		border-radius: 0.5vw;
	}

	.wrapper .search .search-content {

		display: flex;
		justify-content: center;
		align-items: center;

		font-size: 3.5vw;

		margin-left: 2.5vw;
	}

	.wrapper .search input {
		width: 70%;
		height: 5vw;
		border: none;
		border-radius: 4px;
		font-size: 2.5vw;
		color: #000;
		background-color: #f0f0f0;
		padding: 0 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		transition: background-color 0.3s ease;
	}

	.wrapper .search input:focus {
		outline: none;
		background-color: #fff;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	}

	.wrapper .search .fa-mail-reply {
		font-size: 5vw;
		margin: 0 2vw 0 2vw;
	}
</style>
