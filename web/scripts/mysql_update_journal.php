<?php
$servername = "xxxxxxxxx";
$username = "xxxxxxxxxxx";
$password = "xxxxxxxxxxx";
$dbname = "xxxxxxxxxxxx";
// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

// $sql = "SELECT name, content,date FROM UpdateJournal";
// $result = $conn->query($sql);

// if ($result->num_rows > 0) {
//     // 输出数据
//     while($row = $result->fetch_assoc()) {
//         echo "(".$row["date"]. "by " . $row["name"]. ")" ."<br>". $row["content"];
//     }
// } else {
//     echo "0 结果";
// }
// $conn->close();

// $d="2024/2/28";
// $sql = "INSERT INTO UpdateJournal (name, content, date)
// VALUES ('lhx', '设置了更新日志的添加功能，但有严重bug，新内容会在刷新后消失，正在改/_\\\ ','$d')";
 
// if ($conn->query($sql) === TRUE) {
//     echo "新记录插入成功";
// } else {
//     echo "Error: " . $sql . "<br>" . $conn->error;
// }
mysqli_query($conn,"DELETE FROM UpdateJournal WHERE date='2024-08-03 21:25:49'");


?>
