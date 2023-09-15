import { createApp } from 'vue';
import App from '../App.vue';
//import router from './router';
import { createRouter, createWebHistory } from 'vue-router';
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
import Mine from '../views/Mine.vue'
// import Points from '../views/Points.vue'
// import Pointshop from '../views/Pointshop.vue'
// import MyWallet from '../views/MyWallet.vue'
// import WalletDetail from '../views/WalletDetail.vue'
// import Recharge from '../views/Recharge.vue'
// import Withdraw from '../views/Withdraw.vue'
// import UserBankCard from '../views/UserBankCard.vue'
// import AddBankCard from '../views/AddBankCard.vue'
// import PaymentResult from '../views/PaymentResult.vue'
// import Availablepoints from '../views/Availablepoints.vue'
// import Error403 from '../components/Error403.vue'

const routes = [
  {
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
  // {
  // 	path: '/points',
  // 	name: 'Points',
  // 	component: Points
  // },
  // {
  // 	path: '/pointshop',
  // 	name: 'Pointshop',
  // 	component: Pointshop
  // },
  // {
  // 	path: '/myWallet',
  // 	name: 'Mywallet',
  // 	component: MyWallet
  // },
  // {
  // 	path: '/walletDetail',
  // 	name: 'WalletDetail',
  // 	component: WalletDetail
  // },
  // {
  // 	path: '/recharge',
  // 	name: 'Recharge',
  // 	component: Recharge
  // },
  // {
  // 	path: '/withdraw',
  // 	name: 'Withdraw',
  // 	component: Withdraw
  // },
  // {
  // 	path: '/userBankCard',
  // 	name: 'UserBankCard',
  // 	component: UserBankCard
  // },
  // {
  // 	path: '/addBankCard',
  // 	name: 'AddBankCard',
  // 	component: AddBankCard
  // },
  // {
  // 	path: '/paymentResult',
  // 	name: 'PaymentResult',
  // 	component: PaymentResult
  // },
  // {
  // 	path: '/availablepoints',
  // 	name: 'Availablepoints',
  // 	component: Availablepoints,
  // },
  // {
  // 	path: '/error403',
  // 	name: 'Error403',
  // 	component: Error403
  // }
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
