<template>
	<div class="wrapper">
		<header>
			<p>积分流水</p>
		</header>
		<ul class="transcation">
			<li v-for="item in transcationArr">
				<div class="trancation-info">
					<p><strong>用户ID:</strong> {{ item.userid }}</p>
<!-- 					<p><strong>订单ID:</strong> {{ item.orderid }}</p> -->
					<p><strong>积分数额:</strong> {{ item.amount }}</p>
					<p><strong>类型:</strong> {{ item.type ? '收入' : '支出' }}</p>
					<p><strong>创建时间:</strong> {{ item.createTime }}</p>
					<p><strong>过期时间:</strong> {{ item.expiredTime || '无' }}</p>
				</div>
			</li>
		</ul>
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';
	export default {
		name: 'Bonuspointstranscation',
		data() {
			return {
				transcationArr: [],
			}
		},
		created() {

			this.user = this.$getSessionStorage('user');

			this.$axios.post('BonuspointsController/listtransactionbyid', this.$qs.stringify({
				userid: this.user.userId
			})).then(response => {
				let result = response.data;
				console.log(result);
				this.transcationArr = result;

			}).catch(error => {
				console.error(error);
			});
		},
		components: {
			Footer
		}
	}
</script>

<style scoped>
	.wrapper {
		padding: 20px;
	}
	
	header {
		background-color: #f5f5f5;
		padding: 10px;
		margin-bottom: 20px;
	}
	
	p {
		font-size: 20px;
		font-weight: bold;
	}
	
	.transcation {
		list-style-type: none;
		padding: 0;
	}
	
	.transcation li {
		margin-bottom: 10px;
	}
	
	.trancation-info {
		background-color: #f5f5f5;
		padding: 10px;
	}
	
	.trancation-info p {
		margin: 0;
		font-size: 16px;
	}
	
	.trancation-info strong {
		font-weight: bold;
	}
</style>