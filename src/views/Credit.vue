<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>充值</p>
		</header>
		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					充值金额：
				</div>
				<div class="content">
					<input type="text" v-model="amount" placeholder="充值金额">
				</div>
			</li>
		</ul>
		<div class="button-debit">
			<button @click="debit">确定</button>
		</div>
     </div>
</template>

<script>
	export default {
		name: 'Debit',
		data() {
			return {
                virtualwallet :{},
				amount:''
			}
		},

		created() {
			this.user = this.$getSessionStorage('user');
			this.$axios.post('VirtualWalletController/getWalletbyuserId', this.$qs.stringify({
				userid: this.user.userId
			})).then(response => {
				this.virtualwallet = response.data;
			}).catch(error => {
				console.error(error);
			});
			
		},
		methods: {
			debit() {
				// if (this.virtualwallet.balance < this.amount) {
				// 	alert('余额不足！');
				// 	return;
				// }
				this.$axios.post('VirtualWalletController/credit', this.$qs.stringify({

						userId: this.user.userId,
						amount: this.amount,
					}
				)).then(
				alert('充值成功！'),
				this.$router.go(-1)
			).catch(error => {
				console.error(error)
			});

		}
	},
	}
	// if(this.virtualwallet.balance<this.orders.orderTotal)
	// {
	// 	alert('余额不足！');
	// 	return;
	// }

	// this.$axios.post('VirtualWalletController/debit', this.$qs.stringify({

	// 	userId: this.user.userId,
	// 	amount: this.orders.orderTotal,

	// 
</script>

<style scoped>
	.wrapper {
	  width: 100%;
	  max-width: 400px;
	  margin: 0 auto;
	  padding: 20px;
	}
	
	header {
	  background-color: #007bff;
	  color: #fff;
	  text-align: center;
	  padding: 10px 0;
	}
	
	ul.form-box {
	  list-style: none;
	  padding: 0;
	}
	
	ul.form-box li {
	  margin-bottom: 20px;
	}
	
	.title {
	  font-weight: bold;
	}
	
	.content input[type="text"] {
	  width: 100%;
	  padding: 10px;
	  border: 1px solid #ccc;
	  border-radius: 5px;
	}
	
	.button-debit button {
	  background-color: #007bff;
	  color: #fff;
	  padding: 10px 20px;
	  border: none;
	  border-radius: 5px;
	  cursor: pointer;
	}
	
	.button-debit button:hover {
	  background-color: #0056b3;
	}
</style>
