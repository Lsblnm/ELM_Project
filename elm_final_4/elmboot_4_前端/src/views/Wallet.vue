<template>
    <div class="wrapper">
        <Header>
			<p>我的钱包</p>
		</Header>
        <div class="card">

           <button class="fa-list-ul" @click="checktranscation">查看流水</button>
 
        </div>
		
		<div class="box-below">
			<div class="card-body">
			    <div class="row">
			        <div class="col-6">余额：{{virtualWallet.balance}}</div>
			    </div>
			</div>

			<button class="charge" @click="todebit">提现</button>

		    <button class="draw" @click="tocredit">充值</button>

		    
		</div>
		<Footer></Footer>
    </div>
</template>
<!-- VirtualWallet.balance -->
<script>
	import Footer from '../components/Footer.vue';
export default {
    name: "Wallet",
	data() {
			return {
	// 			userId: this.$route.query.userId,
	 			virtualWallet: {}
			}
	 	},
     created() {
         this.user = this.$getSessionStorage('user');
		 this.$axios.post('VirtualWalletController/getWalletbyuserId', this.$qs.stringify({
		 userid: this.user.userId,
		 })).then(response => {
		   this.virtualWallet = response.data;
		   // this.$setSessionStorage('virtualWallet', virtualWallet);
		  // console.log(this.VirtualWallet);
		   }).catch(error => {
		        console.error(error);
		    });
},
     methods:{
		 todebit(){
         this.$router.push({
         	path: '/debit',
         });
			 
		 },
		 tocredit(){
         this.$router.push({
         	path: '/credit',
         });
			 
		 },
		 checktranscation(){
			 this.$router.push({
			 	path: '/transcation',
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
    width: 100%;
	height: 100%;
	background-image: linear-gradient(to top, rgba(255, 0, 0, 0), rgb(59, 176, 246));
}

.card {
    display: flex;
    align-items: center;
    justify-content: space-between;
    
    height: 9vw;
    
    font-size: 3.5vw;
    color: white;
    background-color: #359cff;
    margin: 2vw 3vw 0 3vw;
    
    border-radius: 10px 10px 0 0;
}

.fa-list-ul {
  background-color: #359cff;
  border: #359cff;
}

.box-below {
  display: flex;
  flex-direction: column;
  height: 35vw;
  font-size: 3vw;
  color: black;
  background-color: #FFFFFF;
  margin: 0 3vw 0 3vw;
  border-radius: 10px;
  border: solid 1px #DDD;
  bottom: 5vw;
}

.card-body {
	margin-left: 3vw;
	flex: 1;
	border-right: solid 1px #DDD;
}

.charge {
  width: 15vw;
  height: 6vw;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 3vw;
  margin-top: 27vw;
  border-radius: 10px;
  color: #ffffff;
  background-color: #359cff;
  position: absolute;
  border: none;
}

.draw {
  width: 15vw;
  height: 6vw;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 25vw;
  margin-top: 27vw;
  border-radius: 10px;
  color: #ffffff;
  background-color: #359cff;
  position: absolute;
  border: none;
}

.wrapper .box-below .card-body .row .col-6{
	font-size: 7vw;
}

</style>