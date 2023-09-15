<template>
	<div class="wrapper">
		<header>
			<p>我的信息</p>
		</header>

		<div class="kuai0">
			<img src="../assets/touxiang.png">
		</div>
		<div class="kuai1">
			<h3>头像</h3>
		</div>


		<ul class="place">
			<li>
				<div class="kuai2">
					➤常用功能
				</div>
			</li>
			<li @click="toMyWallet">
				<div class="kuai3">
					➤我的钱包
				</div>
			</li>
			<li>
				<div class="kuai4">
					➤互动玩乐
				</div>
			</li>
			<li @click="toMyPoints">
				<div class="kuai5">
					➤我的积分
				</div>
		 </li>

		</ul>


		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>


</template>

<script>
	import Footer from '../components/Footer.vue';
	export default {
		name: 'Mine',
		components: {
			Footer
		},
		methods: {
			toMyPoints() {
				this.$router.push({
					path: '/mypoints'
				});
			},
			toMyWallet(){
				this.$router.push({
					path:'/myWallet'
				});
			}
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
		width: 100%;
		margin-top: 14vw;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.wrapper .kuai1 {
		width: 100%;
		margin-top: 4vw;
		padding: 3vw 3vw 0 3vw;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.wrapper .place {
		width: 100%;
		margin-top: 2vw;
	}

	.wrapper .place li {
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
		display: flex;
		align-items: center;
	}

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
		color:black;
	}

	.wrapper .place li .kuai5 {
		font-size: 4vw;
		font-weight: 700;
		color: black;
	}
</style>
