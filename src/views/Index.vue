<!-- <template>部分为html，<script>部分为js，<style>部分为css -->
<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<div class="icon-location-box">
				<div class="icon-location"></div>
			</div>
			<div class="location-text">沈阳市规划大厦<i class="fa fa-caret-down"></i></div>
		</header>
		<!-- search部分 -->
		<!-- 
 搜索框部分（此块与search-fixed-top块宽度高度一致，用于当
 search-fixed-top块固定后，挡住下面块不要窜上去）
 -->
		<div class="search">
			<!-- 当滚动条超过上面的定位块时，search-fixed-top块变成固定在顶部。 -->
			<div class="search-fixed-top" ref="fixedBox">
				<!-- 搜索框部分中间的白框 -->
				<input type="text" v-model="key" placeholder="搜索饿了么商家、商品名称">
				<i class="fa fa-search" @click="toBusinessList1(key)"></i>
			</div>
		</div>
		<!-- 点餐分类部分 -->
		<ul class="foodtype">
			<li @click="toBusinessList(1)">
				<!-- onclick换为@click，点击事件改为vue事件类型 -->
				<img src="../assets/dcfl01.png">
				<!--图片路径需要修改 -->
				<p>美食</p>
			</li>
			<li @click="toBusinessList(2)">
				<img src="../assets/dcfl02.png">
				<p>早餐</p>
			</li>
			<li @click="toBusinessList(3)">
				<img src="../assets/dcfl03.png">
				<p>跑腿代购</p>
			</li>
			<li @click="toBusinessList(4)">
				<img src="../assets/dcfl04.png">
				<p>汉堡披萨</p>
			</li>
			<li @click="toBusinessList(5)">
				<img src="../assets/dcfl05.png">
				<p>甜品饮品</p>
			</li>
			<li @click="toBusinessList(6)">
				<img src="../assets/dcfl06.png">
				<p>速食简餐</p>
			</li>
			<li @click="toBusinessList(7)">
				<img src="../assets/dcfl07.png">
				<p>地方小吃</p>
			</li>
			<li @click="toBusinessList(8)">
				<img src="../assets/dcfl08.png">
				<p>米粉面馆</p>
			</li>
			<li @click="toBusinessList(9)">
				<img src="../assets/dcfl09.png">
				<p>包子粥铺</p>
			</li>
			<li @click="toBusinessList(10)">
				<img src="../assets/dcfl10.png">
				<p>炸鸡炸串</p>
			</li>
		</ul>
		<!-- 横幅广告部分（注意：此处有背景图片） -->
		<div class="banner">
			<h3>品质套餐</h3>
			<p>搭配齐全吃得好</p>
			<a>立即抢购 &gt;</a>
		</div>
		<!-- 超级会员部分 -->
		<div class="supermember">
			<div class="left">
				<img src="../assets/super_member.png">
				<h3>超级会员</h3>
				<p>&#8226; 每月享超值权益</p>
			</div>
			<div class="right">
				立即开通 &gt;
			</div>
		</div>
		<!-- 推荐商家部分 -->
		<div class="recommend">
			<div class="recommend-line"></div>
			<p>推荐商家</p>
			<div class="recommend-line"></div>
		</div>
		<!-- 推荐方式部分 -->
		<ul class="recommendtype">
			<li>综合排序<i class="fa fa-caret-down"></i></li>
			<li>距离最近</li>
			<li>销量最高</li>
			<li>筛选<i class="fa fa-filter"></i></li>
		</ul>
		<!-- 推荐商家列表部分 -->
		<ul class="business">
					<li>
						<img src="../assets/sj01.png">
						<div class="business-info">
							<div class="business-info-h">
								<p @click="toBusinessInfo(10001)">万家饺子（软件园E18店）</p>
								<div class="business-info-like">&#8226;</div>
							</div>
							<div class="business-info-star">
								<div class="business-info-star-left">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<p>4.9 月售345单</p>
								</div>
								<div class="business-info-star-right">
									蜂鸟专送
								</div>
							</div>
							<div class="business-info-delivery">
								<p>&#165;15起送 | &#165;3配送</p>
								<p>3.22km | 30分钟</p>
							</div>
							<div class="business-info-explain">
								<div>各种饺子</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">新</div>
									<p>饿了么新用户首单立减9元</p>
								</div>
								<div class="business-info-promotion-right">
									<p>2个活动</p>
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
									<p>特价商品5元起</p>
								</div>
							</div>
						</div>
					</li>
					<li>
						<img src="../assets/sj02.png">
						<div class="business-info">
							<div class="business-info-h">
								<h3 @click="toBusinessInfo(10002)">小锅饭豆腐馆（全运店）</h3>
								<div class="business-info-like">&#8226;</div>
							</div>
							<div class="business-info-star">
								<div class="business-info-star-left">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<p>4.9 月售345单</p>
								</div>
								<div class="business-info-star-right">
									蜂鸟专送
								</div>
							</div>
							<div class="business-info-delivery">
								<p>&#165;15起送 | &#165;3配送</p>
								<p>3.22km | 30分钟</p>
							</div>
							<div class="business-info-explain">
								<div>各种饺子</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">新</div>
									<p>饿了么新用户首单立减9元</p>
								</div>
								<div class="business-info-promotion-right">
									<p>2个活动</p>
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">特</div>
									<p>特价商品5元起</p>
								</div>
							</div>
						</div>
					</li>
					<li>
						<img src="../assets/sj03.png">
						<div class="business-info">
							<div class="business-info-h">
								<h3 @click="toBusinessInfo(10003)">麦当劳麦乐送（全运路店）</h3>
								<div class="business-info-like">&#8226;</div>
							</div>
							<div class="business-info-star">
								<div class="business-info-star-left">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<p>4.9 月售345单</p>
								</div>
								<div class="business-info-star-right">
									蜂鸟专送
								</div>
							</div>
							<div class="business-info-delivery">
								<p>&#165;15起送 | &#165;3配送</p>
								<p>3.22km | 30分钟</p>
							</div>
							<div class="business-info-explain">
								<div>各种饺子</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">新</div>
									<p>饿了么新用户首单立减9元</p>
								</div>
								<div class="business-info-promotion-right">
									<p>2个活动</p>
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">特</div>
									<p>特价商品5元起</p>
								</div>
							</div>
						</div>
					</li>
					<li>
						<img src="../assets/sj04.png">
						<div class="business-info">
							<div class="business-info-h">
								<h3 @click="toBusinessInfo(10004)">米村拌饭（浑南店）</h3>
								<div class="business-info-like">&#8226;</div>
							</div>
							<div class="business-info-star">
								<div class="business-info-star-left">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<p>4.9 月售345单</p>
								</div>
								<div class="business-info-star-right">
									蜂鸟专送
								</div>
							</div>
							<div class="business-info-delivery">
								<p>&#165;15起送 | &#165;3配送</p>
								<p>3.22km | 30分钟</p>
							</div>
							<div class="business-info-explain">
								<div>各种饺子</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">新</div>
									<p>饿了么新用户首单立减9元</p>
								</div>
								<div class="business-info-promotion-right">
									<p>2个活动</p>
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">特</div>
									<p>特价商品5元起</p>
								</div>
							</div>
						</div>
					</li>
					<li>
						<img src="../assets/sj05.png">
						<div class="business-info">
							<div class="business-info-h">
								<h3 @click="toBusinessInfo(10005)">申记串道（中海康城店）</h3>
								<div class="business-info-like">&#8226;</div>
							</div>
							<div class="business-info-star">
								<div class="business-info-star-left">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<p>4.9 月售345单</p>
								</div>
								<div class="business-info-star-right">
									蜂鸟专送
								</div>
							</div>
							<div class="business-info-delivery">
								<p>&#165;15起送 | &#165;3配送</p>
								<p>3.22km | 30分钟</p>
							</div>
							<div class="business-info-explain">
								<div>各种饺子</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">新</div>
									<p>饿了么新用户首单立减9元</p>
								</div>
								<div class="business-info-promotion-right">
									<p>2个活动</p>
									<i class="fa fa-caret-down"></i>
								</div>
							</div>
							<div class="business-info-promotion">
								<div class="business-info-promotion-left">
									<div class="business-info-promotion-left-incon">特</div>
									<p>特价商品5元起</p>
								</div>
							</div>
						</div>
					</li>
				</ul>
		<!-- 底部菜单部分 调用子组件Footer代替代码，css直接删去-->
		<Footer></Footer>
	</div>
</template>

<script>
	//（1）导入共通组件Footer，底部写一遍即可
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Index',
		data() {
			return {
				key: ''
			}
		},
		mounted() { //初始化vue对象，初始时就拥有该函数性质
document.onscroll = () => { //箭头函数指向vue对象=>(),function函数指向document function（），vue中应该使用箭头
				//获取滚动条位置
				let s1 = document.documentElement.scrollTop;
				let s2 = document.body.scrollTop;
				let scroll = s1 == 0 ? s2 : s1;
				//获取视口宽度
				let width = document.documentElement.clientWidth;

				//获取顶部固定块，vue形式操作doem
				let search = this.$refs.fixedBox;

				//判断滚动条超过视口宽度的12%时，搜索块变固定定位
				if (scroll > width * 0.12) {
					search.style.position = 'fixed';
					search.style.left = '0';
					search.style.top = '0';
				} else {
					search.style.position = 'static';
				}
			}
			},
		unmounted() {
			//当切换到其他组件时，就不需要document滚动条事件，所以将此事件去掉，只有顶部固定才需要获取滚动条位置
			document.onscroll = null;
		},
		//（2）Footer.vue添加到Index.vue对象中，成为子组件，代替底部代码
		components: {
			Footer
		},
		//事件函数，点餐分类跳转到商家列表
		methods: {
			toBusinessList(orderTypeId) {
				this.$router.push({
					path: '/businessList', //跳转到的路径，下一个组件
					query: {
						orderTypeId: orderTypeId ,//传参，参数名：参数值
						
					}
				});
			},
			toBusinessList1(key) {
				this.$router.push({
					path: '/businessList', //跳转到的路径，下一个组件
					query: {
						key: key //传参，参数名：参数值
					}
				});
			},
			toBusinessInfo(businessId){
							this.$router.push({
								path: '/businessInfo',
								query:{
									businessId:businessId
								}
							});
						}
		}
	}
</script>
<style scoped>
	/*该vue为组件index私有，所以需要加scoped*/
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****************** header ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		display: flex;
		align-items: center;
	}

	.wrapper header .icon-location-box {
		width: 3.5vw;
		height: 3.5vw;
		margin: 0 1vw 0 3vw;
	}

	.wrapper header .location-text {
		font-size: 4.5vw;
		font-weight: 700;
		color: #fff;
	}

	.wrapper header .location-text .fa-caret-down {
		margin-left: 1vw;
	}

	/****************** search ******************/
	.wrapper .search {
		width: 100%;
		height: 13vw;
	}

	.wrapper .search .search-fixed-top {
		width: 100%;
		height: 13vw;
		background-color: #0097FF;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .search .search-fixed-top input {
		width: 80%;
		height: 9vw;
		border: none;
		border-radius: 4px;
		font-size: 3.5vw;
		color: #000;
		background-color: #f0f0f0;
		padding: 0 10px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		transition: background-color 0.3s ease;
	}

	.wrapper .search .search-fixed-top input:focus {
		outline: none;
		background-color: #fff;
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	}

	.wrapper .search .search-fixed-top .fa-search {
		font-size: 5vw;
		margin-left: 3vw;
	}

	/****************** 点餐分类部分 ******************/
	.wrapper .foodtype {
		width: 100%;
		height: 48vw;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		/*要使用align-content。10个子元素将自动换行为两行，而且两行作为一个整体垂直居中*/
		align-content: center;
	}

	.wrapper .foodtype li {
		/*一共10个子元素，通过计算，子元素宽度在16.7 ~ 20 之间，才能保证换两行*/
		width: 18vw;
		height: 20vw;
		display: flex;
		/*弹性盒子主轴方向设为column，然后仍然是垂直水平方向居中*/
		flex-direction: column;
		justify-content: center;
		align-items: center;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .foodtype li img {
		width: 12vw;
		/*视频讲解时高度设置为12vw，实际上设置为10.3vw更佳*/
		height: 10.3vw;
	}

	.wrapper .foodtype li p {
		font-size: 3.2vw;
		color: #666;
	}

	/****************** 横幅广告部分 ******************/
	.wrapper .banner {
		/**
 * 设置容器宽度95%，然后水平居中，这样两边留白; 
 * 这里不能用padding，因为背景图片也会覆盖padding
 */
		width: 95%;
		margin: 0 auto;
		height: 29vw;
		/*此三个样式组合，可以保证背景图片充满整个容器*/
		background-image: url(../assets/index_banner.png);
		background-repeat: no-repeat;
		background-size: cover;
		box-sizing: border-box;
		padding: 2vw 6vw;
	}

	.wrapper .banner h3 {
		font-size: 4.2vw;
		margin-bottom: 1.2vw;
	}

	.wrapper .banner p {
		font-size: 3.4vw;
		color: #666;
		margin-bottom: 2.4vw;
	}

	.wrapper .banner a {
		font-size: 3vw;
		color: #C79060;
		font-weight: 700;
	}

	/****************** 超级会员部分 ******************/
	.wrapper .supermember {
		/*这里也设置容器宽度95%，不能用padding，因为背景色也会充满padding*/
		width: 95%;
		margin: 0 auto;
		height: 11.5vw;
		background-color: #FEEDC1;
		margin-top: 1.3vw;
		border-radius: 2px;
		color: #644F1B;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .supermember .left {
		display: flex;
		align-items: center;
		margin-left: 4vw;
		user-select: none;
	}

	.wrapper .supermember .left img {
		width: 6vw;
		height: 6vw;
		margin-right: 2vw;
	}

	.wrapper .supermember .left h3 {
		font-size: 4vw;
		margin-right: 2vw;
	}

	.wrapper .supermember .left p {
		font-size: 3vw;
	}

	.wrapper .supermember .right {
		font-size: 3vw;
		margin-right: 4vw;
		cursor: pointer;
	}

	/****************** 推荐商家部分 ******************/
	.wrapper .recommend {
		width: 100%;
		height: 14vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .recommend .recommend-line {
		width: 6vw;
		height: 0.2vw;
		background-color: #888;
	}

	.wrapper .recommend p {
		font-size: 4vw;
		margin: 0 4vw;
	}

	/****************** 推荐方式部分 ******************/
	.wrapper .recommendtype {
		width: 100%;
		height: 5vw;
		margin-bottom: 5vw;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.wrapper .recommendtype li {
		font-size: 3.5vw;
		color: #555;
	}

	/****************** 推荐商家列表部分 ******************/
	.wrapper .business {
		width: 100%;
		margin-bottom: 14vw;
	}

	.wrapper .business li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		user-select: none;
		border-bottom: solid 1px #DDD;
		display: flex;
	}

	.wrapper .business li img {
		width: 18vw;
		height: 18vw;
	}

	.wrapper .business li .business-info {
		width: 100%;
		box-sizing: border-box;
		padding-left: 3vw;
	}

	.wrapper .business li .business-info .business-info-h {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2vw;
	}

	.wrapper .business li .business-info .business-info-h h3 {
		font-size: 4vw;
		color: #333;
	}

	.wrapper .business li .business-info .business-info-h .business-info-like {
		width: 1.6vw;
		height: 3.4vw;
		background-color: #666;
		color: #fff;
		font-size: 4vw;
		margin-right: 4vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business li .business-info .business-info-star {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2vw;
		font-size: 3.1vw;
	}

	.wrapper .business li .business-info .business-info-star .business-info-star-left {
		display: flex;
		align-items: center;
	}

	.wrapper .business li .business-info .business-info-star .business-info-star-left .fa-star {
		color: #FEC80E;
		margin-right: 0.5vw;
	}

	.wrapper .business li .business-info .business-info-star .business-info-star-left p {
		color: #666;
		margin-left: 1vw;
	}

	.wrapper .business li .business-info .business-info-star .business-info-star-right {
		background-color: #0097FF;
		color: #fff;
		font-size: 2.4vw;
		border-radius: 2px;
		padding: 0 0.6vw;
	}

	.wrapper .business li .business-info .business-info-delivery {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2vw;
		color: #666;
		font-size: 3.1vw;
	}

	.wrapper .business li .business-info .business-info-explain {
		display: flex;
		align-items: center;
		margin-bottom: 3vw;
	}

	.wrapper .business li .business-info .business-info-explain div {
		border: solid 1px #DDD;
		font-size: 2.8vw;
		color: #666;
		border-radius: 3px;
		padding: 0 0.1vw;
	}

	.wrapper .business li .business-info .business-info-promotion {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 1.8vw;
	}

	.wrapper .business li .business-info .business-info-promotion .business-info-promotion-left {
		display: flex;
		align-items: center;
	}

	.wrapper .business li .business-info .business-info-promotion .business-info-promotion-left .business-info-promotion-left-incon {
		width: 4vw;
		height: 4vw;
		background-color: #70BC46;
		border-radius: 3px;
		font-size: 3vw;
		color: #fff;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business li .business-info .business-info-promotion .business-info-promotion-left p {
		color: #666;
		font-size: 3vw;
		margin-left: 2vw;
	}

	.wrapper .business li .business-info .business-info-promotion .business-info-promotion-right {
		display: flex;
		align-items: center;
		font-size: 2.5vw;
		color: #999;
	}

	.wrapper .business li .business-info .business-info-promotion .business-info-promotion-right p {
		margin-right: 2vw;
	}
</style>
