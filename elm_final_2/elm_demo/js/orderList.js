window.onload = function() {
	//根据类样式获取隐藏按键数组
	let showBtnArr = document.getElementsByClassName('fa-caret-down');
	//根据类样式获取订单列表数组
	let detailetBoxArr = document.getElementsByClassName('order-detailet');
	//遍历每一个订单数组元素置为默认隐藏
	for (let i = 0; i < showBtnArr.length; i++) {
		detailetBoxArr[i].style.display = 'none';
	}
	//点击隐藏与否按钮
	for (let i = 0; i < showBtnArr.length; i++) {
		showBtnArr[i].onclick = function() {
			if (detailetBoxArr[i].style.display == 'none') {
				detailetBoxArr[i].style.display = 'block';
			} else {
				detailetBoxArr[i].style.display = 'none';
			}
		}
	}
}