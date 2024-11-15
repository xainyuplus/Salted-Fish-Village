<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="" method="post">
提交者: <input type="text" name="name"><br>
内容 : <input type="text" name="content">
<br>
<input type="submit" value="提交">
</form>
    
    
    
    
    <?php
$servername = "localhost";
$username = "xxxxxxxxxx";
$password = "xxxxxxxxxxxxx";
$dbname = "xxxxxxxxx";
// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

$d=date("Y/m/d H:i:s");
$n=$_POST["name"];
$c=$_POST["content"];
if($_POST["name"]){
$sql = "INSERT INTO UpdateJournal (name, content, date)
VALUES ('$n', '$c','$d')";
 
if ($conn->query($sql) === TRUE) {
    echo "新记录插入成功";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
sleep(1);
//header("Refresh:0;url=about:blank");
echo '<script>window.close();</script>';
}

//加个if排除空值就行了，我是个天才，哈哈哈哈哈哈
// mysqli_query($conn,"DELETE FROM UpdateJournal WHERE content='设置了更新日志的添加功能，但有严重bug，新内容会在刷新后消失，正在改'");
?>
</body>
</html>