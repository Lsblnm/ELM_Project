<template>
	<div class="wrapper">
		<Header name="我的积分" />
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-6">积分余额：{{bonuspoints.balance}}</div>
				</div>
			</div>
		</div>
		<div class="checktranscation-button">
			<button @click="checktranscation">查看流水</button>
		</div>
				<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';
	export default {
		name: "Bonuspoints",
		data() {
			return {
				// 			userId: this.$route.query.userId,
				bonuspoints: {}
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');
			this.$axios.post('BonuspointsController/getBonuspointsbyuserId', this.$qs.stringify({
				userid: this.user.userId,
			})).then(response => {
				this.bonuspoints = response.data;
				// this.$setSessionStorage('virtualWallet', virtualWallet);
				// console.log(this.VirtualWallet);
			}).catch(error => {
				console.error(error);
			});
		},
		methods: {
			checktranscation() {
				this.$router.push({
					path: '/Bonuspointstranscation',
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
	  padding: 20px;
	}
	
	.card {
	  background-color: #f5f5f5;
	  border-radius: 5px;
	  padding: 20px;
	  margin-top: 20px;
	}
	
	.card-body {
	  font-size: 18px;
	}
	
	.row {
	  margin-bottom: 10px;
	}
	
	.col-6 {
	  width: 50%;
	}
	
	.checktranscation-button {
	  margin-top: 20px;
	}
	
	button {
	  background-color: #0097FF;
	  color: #fff;
	  font-size: 16px;
	  padding: 10px 20px;
	  border: none;
	  border-radius: 5px;
	  cursor: pointer;
	}
	
	button:hover {
	  background-color: #0079D3;
	}
</style>