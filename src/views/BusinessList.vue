<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>商家列表</p>
		</header>
		<!-- 商家列表部分 -->
		<ul class="business">
			<!-- v-for遍历businessArr，item为遍历出来的每个对象，不用像html列出所有对象了 -->
			<li v-for="item in businessArr" @click="toBusinessInfo(item.businessId)">
				<!--@click点击发生事件，toBusinessInfo跳转到商家信息方法，在下面method中写具体函数，item.businessId为商家编号参数-->
				<div class="business-img">
					<img :src="item.businessImg"><!-- 从数据库中查找图片 -->
					<div class="business-img-quantity" v-show="item.quantity>0">
						<!-- v-show（用于图层显示）判断该item是否有东西，没有则不显示红点 -->
						{{item.quantity}}
					</div>
				</div>
				<div class="business-info">
					<h3>{{item.businessName}}</h3>
					<p>&#165;{{item.starPrice}}起送 | &#165;{{item.deliveryPrice}}配送</p>
					<p>{{item.businessExplain}}</p>
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
		name: 'BusinessList',
		data() {
			return {
				orderTypeId: this.$route.query.orderTypeId, //首页到列表页传递过来的参数
				key: this.$route.query.key,
				businessArr: [], //商家列表
				user: {}
			}
		},
		created() { //组件初始化
			this.user = this.$getSessionStorage('user');

			//根据orderTypeId查询商家信息
			//this.$axios.post（请求路径，提交参数）
			//axios中内含'http://localhost:8080/elm/'
			//'BusinessController/listBusinessByOrderTypeId'为接口
			//this.$qs.stringify({orderTypeId: this.orderTypeId})使用qs库中的stringify方法将传递的jason对象（{orderTypeId: this.orderTypeId）转化为键值对的形式才能被post传递到服务器中
			//服务器端查询成功调用then，response响应传回来的业务信息，=>调用箭头函数
			//失败调用catch，error响应传回来的错误信息，
			if (this.$route.query.orderTypeId) {
				this.$axios.post('BusinessController/listBusinessByOrderTypeId', this.$qs.stringify({
						orderTypeId: this.$route.query.orderTypeId
					}))
					.then(response => {
						this.businessArr = response.data;
						if (this.user != null) {
							this.listCart();
						}
					})
					.catch(error => {
						console.error(error);
					});
			} else if (this.$route.query.key) {
				this.$axios.post('BusinessController/searchBusinessByKey', this.$qs.stringify({
						key: this.$route.query.key
					}))
					.then(response => {
						this.businessArr = response.data;
						if (this.user != null) {
							this.listCart();
						}
					})
					.catch(error => {
						console.error(error);
					});
			}
		},
		//共通组件
		components: {
			Footer
		},
		methods: {
			//保存登录用户购物车中的食品信息
			listCart() {
				this.$axios.post('CartController/listCart', this.$qs.stringify({
					userId: this.user.userId
				})).then(response => {
					let cartArr = response.data;
					//遍历所有食品列表
					for (let businessItem of this.businessArr) {
						businessItem.quantity = 0;
						for (let cartItem of cartArr) {
							if (cartItem.businessId == businessItem.businessId) { //只要购物车中businessId出现一次，商家信息中就得加一次，故用+=
								businessItem.quantity += cartItem.quantity;
							}
						}
					}
					this.businessArr.sort();
				}).catch(error => {
					console.error(error);
				});
			},
			//商家列表到商家信息跳转函数，businessId为商家信息的参数
			toBusinessInfo(businessId) {
				this.$router.push({
					path: '/businessInfo', //跳转路径
					query: {
						businessId: businessId //参数
					}
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

	/****************** 商家列表部分 ******************/
	.wrapper .business {
		width: 100%;
		margin-top: 12vw;
		margin-bottom: 14vw;
	}

	.wrapper .business li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		border-bottom: solid 1px #DDD;
		user-select: none;
		cursor: pointer;
		display: flex;
		align-items: center;
	}

	.wrapper .business li .business-img {
		/*这里设置为相当定位，成为business-img-quantity元素的父元素*/
		position: relative;
	}

	.wrapper .business li .business-img img {
		width: 20vw;
		height: 20vw;
	}

	.wrapper .business li .business-img .business-img-quantity {
		width: 5vw;
		height: 5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;
		border-radius: 2.5vw;
		display: flex;
		justify-content: center;
		align-items: center;
		/*设置成绝对定位，不占文档流空间*/
		position: absolute;
		right: -1.5vw;
		top: -1.5vw;
	}

	.wrapper .business li .business-info {
		margin-left: 3vw;
	}

	4.3.3.BusinessInfo组件 .wrapper .business li .business-info h3 {
		font-size: 3.8vw;
		color: #555;
	}

	.wrapper .business li .business-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
	}
</style>
