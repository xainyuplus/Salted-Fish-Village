        //复制来的倒计时功能函数
function countdown() {
  var countdownDate = new Date("2024-10-30 21:20:00").getTime(); // 设置倒计时的截止日期，可以修改为你所需的日期

  var x = setInterval(function() {
    var now = new Date().getTime(); // 获取当前时间
    var distance = countdownDate - now; // 计算距离截止日期的时间差

    var days = Math.floor(distance / (1000 * 60 * 60 * 24)); // 计算天数
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)); // 计算小时数
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)); // 计算分钟数
    var seconds = Math.floor((distance % (1000 * 60)) / 1000); // 计算秒数

    var countdownElement = document.getElementById("countdown"); // 获取倒计时显示的元素
    countdownElement.innerHTML = days + " 天 " + hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒"; // 更新倒计时显示

    if (distance < 0) {
      clearInterval(x); // 倒计时结束后清除定时器
      countdownElement.innerHTML = "倒计时已结束"; // 显示倒计时结束的提示
    }
  }, 1000); // 每隔 1 秒更新一次倒计时
}

countdown(); // 调用倒计时函数