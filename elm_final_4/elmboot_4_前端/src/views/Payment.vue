<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>在线支付</p>
		</header>
		<!-- 订单信息部分 -->
		<h3>订单信息：</h3>
		<div class="order-info">
			<div class="left">
				{{orders.business.businessName}}
				<i class="fa fa-caret-down" @click="detailetShow"></i>
				<div>&#165;{{orders.orderTotal}}</div>
			</div>
			
			<div class="right">
				使用积分后的价格：&#165;{{discountPrice}}
			</div>
		  
		</div>
		<!-- 订单明细部分 -->
		<ul class="order-detailet" v-show="isShowDetailet">
			<li v-for="item in orders.list">
				<p>{{item.food.foodName}} x {{item.quantity}}</p>
				<p>&#165;{{(item.food.foodPrice*item.quantity).toFixed(2)}}</p>
				
			</li>
			<li>
				<p>配送费</p>
				<p>&#165;{{orders.business.deliveryPrice}}</p>
			</li>
		</ul>
		<!-- 支付方式部分 -->
		<ul class="payment-type">
			<li>
				<img src="../assets/alipay.png">
				<i class="fa fa-check-circle"></i>
			</li>
			<li>
				<img src="../assets/wechat.png">
			</li>
		</ul>

		<div class="payment-button">
			<button @click="transfer">确认支付</button>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>
<script>
	import Footer from '../components/Footer.vue';
	export default {
		name: 'Payment',
		data() {
			return {
				virtualwallet: {},
				orderId: this.$route.query.orderId,
				orders: {
					business: {}
				},
				isShowDetailet: false,
				bonuspoints: {}
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

			this.$axios.post('OrdersController/getOrdersById', this.$qs.stringify({
				orderId: this.orderId
			})).then(response => {
				this.orders = response.data;
			}).catch(error => {
				console.error(error);
			});

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
		mounted() {
			//这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件。
			//先将当前url添加到history对象中
			history.pushState(null, null, document.URL);
			//popstate事件能够监听history对象的变化
			window.onpopstate = () => {
				this.$router.push({
					path: '/index'
				});
			}
		},
		destroyed() {
			window.onpopstate = null;
		},
		methods: {
			transfer() {
				if(this.bonuspoints.balance<this.orders.orderTotal*100){
					if (this.virtualwallet.balance < this.orders.orderTotal-this.bonuspoints.balance/100) {
						alert('余额不足！');
						return;
					}
					
					else{
					this.$axios.post('VirtualWalletController/debit', this.$qs.stringify({
						userId: this.user.userId,
						amount: this.orders.orderTotal-this.bonuspoints.balance/100
					})).catch(error => {
						console.error(error)
					});
					}
					this.$axios.post('VirtualWalletController/credit', this.$qs.stringify({
						userId: this.orders.businessId,
						amount: this.orders.orderTotal-this.bonuspoints.balance/100
					})).catch(error => {
						console.error(error)
					});
					this.$axios.post('BonuspointsController/debit', this.$qs.stringify({
						userId: this.user.userId,
						amount: this.bonuspoints.balance
					})).catch(error => {
						console.error(error)
					});
					this.$axios.post('BonuspointsController/credit', this.$qs.stringify({
					    userId: this.user.userId,
					    amount: Math.floor((this.orders.orderTotal-this.bonuspoints.balance/100) * 10)
					}))
					.then(response => {
					    // 处理成功响应
					    alert('支付成功!');
					    return this.$axios.post('OrdersController/updateOrderstateByOrderId', this.$qs.stringify({
					        orderId: this.orders.orderId
					    }));
					})
					.then(response => {
					    // 处理第二个请求成功响应
					    this.$router.go(-1);
					})
					.catch(error => {
					    // 处理任何错误
					    console.error(error);
					});
				}
				else{
					alert('支付成功!')
					
					this.$axios.post('BonuspointsController/debit', this.$qs.stringify({
						userId: this.user.userId,
						amount: this.orders.orderTotal*100
					})).catch(error => {
						console.error(error)
					});
					
					this.$axios.post('OrdersController/updateOrderstateByOrderId',this.$qs.stringify({
						orderId: this.orders.orderId
					})).catch(error => {
						console.error(error)
					});
					this.$router.go(-1)
				}
			},

			detailetShow() {
				this.isShowDetailet = !this.isShowDetailet;
			},

		},
		computed:{
			discountPrice(){
				let num=this.orders.orderTotal-(this.bonuspoints.balance*0.01>this.orders.orderTotal?this.orders.orderTotal:this.bonuspoints.balance*0.01);;
				let nums=num.toFixed(2);
				return nums;
			}
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

	/****************** 订单信息部分 ******************/
	.wrapper h3 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 4vw 4vw 0;
		font-size: 4vw;
		font-weight: 300;
		color: #999;
	}

	.wrapper .order-info {
		
		box-sizing: border-box;
		padding: 4vw;
		font-size: 4vw;
		color: #666;
		display: flex;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
	}

	/****************** 订单明细部分 ******************/
	.wrapper .order-detailet {
		width: 100%;
	}

	.wrapper .order-detailet li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 4vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-detailet li p {
		font-size: 3vw;
		color: #666;
	}

	/****************** 支付方式部分 ******************/
	.wrapper .payment-type {
		width: 100%;
	}

	.wrapper .payment-type li {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .payment-type li img {
		width: 33vw;
		height: 8.9vw;
	}

	.wrapper .payment-type li .fa-check-circle {
		font-size: 5vw;
		color: #38CA73;
	}

	.wrapper .payment-button {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;
	}

	.wrapper .payment-button button {
		width: 100%;
		height: 10vw;
		border: none;
		/*去掉外轮廓线*/
		outline: none;
		border-radius: 4px;
		background-color: #38CA73;
		color: #fff;
	}
</style>