<?php 
$servername = "localhost";
$username = "xxxxxxxxxxx";
$password = "xxxxxxxxxxxxx";
$dbname = "xxxxxxxxxxxxx";
// 创建连接
$conn = new mysqli($servername, $username, $password, $dbname);

$sql = "SELECT name, content,date FROM UpdateJournal";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // 输出数据
    while($row = $result->fetch_assoc()) {
        echo '<p style="color: #f1f1f1;font-size: smaller;">'."(".$row["date"]. "by " . $row["name"]. ")" ."<br>". $row["content"]."</p>";
    }
} else {
    echo "0 结果";
}
$conn->close();
?>