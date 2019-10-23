<?php
$servername = "localhost";
$username = "test123456";
$password = "test";
$dbname = "test123456";
 
// 创建连接
$conn = new mysqli($servername, $username, $password,$dbname);
 
// 检测连接
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
} 
echo "连接成功<br>";

// 使用 sql 创建数据表
// $sql = "CREATE TABLE Info (
// id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
// name VARCHAR(30) NOT NULL,
// number VARCHAR(30) NOT NULL,
// date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
// )";
 
// if ($conn->query($sql) === TRUE) {
//     echo "Table MyGuests created successfully";
// } else {
//     echo "创建数据表错误: " . $conn->error;
// }
// echo "<br>";

//插入数据
$data = date("Y/m/d H:i:s");
$sql = "INSERT INTO Info (name, number)
VALUES ('John', '184203')";
if ($conn->query($sql) === TRUE) {
    echo "新记录插入成功";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
echo "<br>";


//输出数据
$sql = "SELECT id, name, number,date FROM Info";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // 输出数据
    while($row = $result->fetch_assoc()) {
        echo "id: " . $row["id"]. " - 姓名: " . $row["name"]. " - 学号:" . $row["number"] ." - 时间:" . $row["date"] . "<br>";
    }
} else {
    echo "0 结果";
}


$conn->close();
?>
