<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>智能问答</p>
		</header>

		<div id="app">
			<div v-for="message in chatHistory" :key="message.id">
				<p v-if="message.role === 'user'" class="user-message">You: {{ message.content }}</p>
				<p v-if="message.role === 'bot'" class="bot-message">Bot: {{ message.content }}</p>
			</div>
			<div class="input-container">
				<input type="text" v-model="messageInput" @keydown.enter="sendMessage" placeholder="输入消息...">
				<button @click="sendMessage">发送</button>
			</div>
		</div>

		<Footer></Footer>
	</div>
</template>

<script>
	import axios from 'axios';
	import Footer from '../components/Footer.vue';

	export default {
		components: {
			Footer
		},
		data() {
			return {
				userId: 'user123',
				messageInput: '',
				chatHistory: []
			};
		},
		methods: {
			async sendMessage() {
				const requestData = {
					userId: this.userId,
					message: this.messageInput
				};

				try {
					const response = await axios.post('/chatgpt', requestData);
					const reply = response.data;

					this.chatHistory.push({
						role: 'user',
						content: this.messageInput
					});
					this.chatHistory.push({
						role: 'bot',
						content: reply
					});

					this.messageInput = '';
				} catch (error) {
					console.error(error);
				}
			}
		}
	};
</script>

<style scoped>
	/* 总容器 */
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/* header部分 */
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

	/* 聊天框 */
	#app {
		padding-top: 12vw;
		padding-bottom: 14vw;
	}

	.user-message {
		margin: 3vw;
		padding: 0.5rem;
		background-color: #F0F0F0;
		border-radius: 4px;
	}

	.bot-message {
		margin: 3vw;
		padding: 0.5rem;
		background-color: #E5E5E5;
		border-radius: 4px;
	}

	.input-container {
		display: flex;
		align-items: center;
		margin-top: 1rem;
	}

	#app input[type="text"] {
		flex: 1;
		padding: 0.5rem;
		font-size: 1rem;
		border: none;
		border-bottom: 1px solid #ccc;
		margin-left: 1vw;
	}

	#app button {
		margin-left: 1rem;
		padding: 0.5rem 1rem;
		font-size: 1rem;
		color: #fff;
		background-color: #0097FF;
		border: none;
		border-radius: 4px;
		cursor: pointer;
		margin-right: 1vw;
	}
</style>
