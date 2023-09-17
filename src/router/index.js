import {
	createApp
} from 'vue';
import App from '../App.vue';
//import router from './router';
import {
	createRouter,
	createWebHistory
} from 'vue-router';
import Index from '../views/Index.vue';
import BusinessList from '../views/BusinessList.vue';
import BusinessInfo from '../views/BusinessInfo.vue';
import Login from '../views/Login.vue';
import Orders from '../views/Orders.vue';
import UserAddress from '../views/UserAddress.vue';
import Payment from '../views/Payment.vue';
import OrderList from '../views/OrderList.vue';
import AddUserAddress from '../views/AddUserAddress.vue';
import EditUserAddress from '../views/EditUserAddress.vue';
import Register from '../views/Register.vue';
import Mine from '../views/Mine.vue';
import Bonuspoints from '../views/Bonuspoints'
import Bonuspointstranscation from '../views/Bonuspointstranscation.vue'
import Credit from '../views/Credit.vue'
import Debit from '../views/Debit.vue'
import Transcation from '../views/Transcation.vue'
import Wallet from '../views/Wallet.vue'
import Remark from '../views/Remark.vue'
import UpdatePassword from '../views/UpdatePassword.vue'
import Chatgpt from '../views/Chatgpt.vue'
// import Error403 from '../components/Error403.vue'

const routes = [{
		path: '/',
		name: 'Home',
		component: Index
	},
	{
		path: '/index',
		name: 'Index',
		component: Index
	},
	{
		path: '/businessList',
		name: 'BusinessList',
		component: BusinessList
	},
	{
		path: '/businessInfo',
		name: 'BusinessInfo',
		component: BusinessInfo
	},
	{
		path: '/login',
		name: 'Login',
		component: Login
	},
	{
		path: '/orders',
		name: 'Orders',
		component: Orders
	},
	{
		path: '/userAddress',
		name: 'UserAddress',
		component: UserAddress
	},
	{
		path: '/payment',
		name: 'Payment',
		component: Payment
	},
	{
		path: '/orderList',
		name: 'OrderList',
		component: OrderList
	},
	{
		path: '/addUserAddress',
		name: 'AddUserAddress',
		component: AddUserAddress
	},
	{
		path: '/editUserAddress',
		name: 'EditUserAddress',
		component: EditUserAddress
	},
	{
		path: '/register',
		name: 'Register',
		component: Register
	},
	{
		path: '/mine',
		name: 'Mine',
		component: Mine
	},
	{
		path: '/bonuspoints',
		name: 'Bonuspoints',
		component: Bonuspoints
	},
	{
		path: '/bonuspointstranscation',
		name: 'Bonuspointstranscation',
		component: Bonuspointstranscation
	},
	{
		path: '/credit',
		name: 'Credit',
		component: Credit
	},
	{
		path: '/transcation',
		name: 'Transcation',
		component: Transcation
 	},
	{
		path: '/wallet',
		name: 'Wallet',
		component: Wallet
	},
	{
		path: '/debit',
		name: 'Debit',
		component: Debit
	},
	{
		path: '/remark',
		name: 'Remark',
		component: Remark
	},
	{
		path:'/updatePassword',
		name: 'UpdatePassword',
		component: UpdatePassword
	},
	{
		path:'/chatgpt',
		name: 'Chatgpt',
		component: Chatgpt
	}
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
});

createApp(App).use(router).mount('#app');

const originalPush = router.push;
router.push = function push(location) {
	return originalPush.call(this, location).catch(err => err);
};

export default router;

//解决重复路由报异常问题
/*const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})
export default router*/