<template>
	<div class="wrapper">
		<header>
			<p>我的信息</p>
		</header>

		<div class="kuai0">
			<img :src="user.userImg" v-show="this.user!=null">
		</div>
		<div class="kuai1">
			{{this.user.userName}}
		</div>


		<div class="place">
			<li @click="detailetShow">
				<div class="kuai2">
					➤常用功能
				</div>
			</li>
			<ul class="kuai2-detailet" v-show="isShowDetailet">
				<!-- <li>
					<p>更换头像</p>
					<input type="file" ref="user.userImg" @change="handleFileUpload" accept="image/*">
				</li> -->
				<li>
					<p>删除全部评论</p>
					<button @click="deleteRemark"> 删除评论 </button>
				</li>
			<!-- 	<li>
					<p>修改密码</p>
					<button @click="toUpdatePassword"> 修改密码 </button>
				</li> -->
			</ul>
			<li @click="toMyWallet">
				<div class="kuai3">
					➤我的钱包
				</div>
			</li>
			<li @click="toMyPoints">
				<div class="kuai4">
					➤我的积分
				</div>
			</li>
			<li @click="logout">
				<div class="kuai5">
					➤退出登录
				</div>
			</li>
		</div>


		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>


</template>

<script>
	import Footer from '../components/Footer.vue';
	export default {
		name: 'Mine',
		data() {
			return {
				user: {
					userImg: '',
					userId: ''
				},
				isShowDetailet: false
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');

		},
		methods: {
			toMyPoints() {
				this.$router.push({
					path: '/bonuspoints'
				});
			},
			toMyWallet() {
				this.$router.push({
					path: '/wallet'
				});
			},
			detailetShow() {
				this.isShowDetailet = !this.isShowDetailet;
			},
			logout() {
				if (this.user == null) {
					alert('用户未登录！');
					return;
				} else {
					alert('用户已退出登录！');
					sessionStorage.removeItem('user');
					this.$router.push({
						path: '/login'
					});
				}
			},
			deleteRemark() {
				if (!confirm('确认要删除全部评论吗？')) {
					return;
				}

				this.$axios.post('RemarkController/removeAllRemarksByUserId', this.$qs.stringify({
					userId: this.user.userId
				})).then(
				
				).catch(error => {
					console.error(error); //输出错误信息
				});
			},
			// handleFileUpload() {
			// 	this.$axios.post('UserController/updateUserImg', this.$qs.stringify(
			// 			this.user
			// 	)).then(response => {
			// 			if (response.data > 0) {
			// 				alert('更换头像成功！');
			// 			} else {
			// 				alert('更换头像失败！');
			// 			}
			// 		}

			// 	).catch(error => {
			// 		console.error(error); //输出错误信息
			// 	});
			// },
			// toUpdatePassword(){
			// 	this.$router.push({
			// 		path: '/updatePassword'
			// 	});
			// }
		},
		components: {
			Footer
		}
	}
</script>

<style scoped>
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****************** header部分 ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;

		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	/****************** 表单部分 ******************/
	.wrapper .kuai0 {
		width: 30%;
		height: 20%;
		margin: 0 auto;
		margin-top: 14vw;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.wrapper .kuai0 img {
		object-fit: contain;
		/* 图像自适应容器 */
		width: 100%;
		/* 图像宽度设置为100% */
		height: 100%;
		/* 图像高度设置为100% */
	}

	.wrapper .kuai1 {
		width: 100%;
		height: 7vw;
		margin-top: 2vw;

		font-size: 5vw;

		display: flex;
		align-items: center;
		justify-content: center;
	}

	.wrapper .place {
		width: 100%;
		margin-top: 10vw;
	}

	.wrapper .place li {
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
		display: flex;
		align-items: center;
	}
	
	/* .wrapper .place .kuai2-detailet{
		margin-top: 50vw;
	} */

	.wrapper .place li .kuai2 {
		font-size: 4vw;
		font-weight: 700;
		color: black;
	}

	.wrapper .place li .kuai3 {
		font-size: 4vw;
		font-weight: 700;
		color: black;
	}

	.wrapper .place li .kuai4 {
		font-size: 4vw;
		font-weight: 700;
		color: black;
	}

	.wrapper .place li .kuai5 {
		font-size: 4vw;
		font-weight: 700;
		color: black;
	}
</style>