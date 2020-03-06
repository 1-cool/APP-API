<?php
$servername = "localhost";
$username = "admin-data";
$password = "zW4DEKfBWaTYTXsn";
$dbname = "data";

header("Content-Type: text/json;charset=utf-8");
if($_POST["password"]=='zhangaoran')          //判断数据是否为空
{
	// 创建连接
	$conn = new mysqli($servername, $username, $password,$dbname);
	 
	// 检测连接
	if ($conn->connect_error) {
	    die("连接失败: " . $conn->connect_error);
	}
	
	//插入数据
	// $data = date("Y/m/d H:i:s");
	// $sql = "INSERT INTO Info (name, number)
	// VALUES ('John', '184203')";
	// if ($conn->query($sql) === TRUE) {
	//     echo "新记录插入成功";
	// } else {
	//     echo "Error: " . $sql . "<br>" . $conn->error;
	// }
	
    //输出数据
	$sql = "SELECT id, name, number,subject,phone,teachar,qq,sex,count FROM info";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
	    // 输出数据
	    while($row = $result->fetch_assoc()) {
	    	$data = array
	                (
	                        'name'=>$row["name"],
	                        'num'=>$row["number"],
	                        'work'=>$row["subject"],
	                        'phone'=>$row["phone"],
	                        'teachar'=>$row["teachar"],
	                        'qq'=>$row["qq"],
	                        'sex'=>$row["sex"],
	                        'cont'=>$row["count"]
	                );
	                $data = json_encode($data,JSON_UNESCAPED_UNICODE);
	                echo $data;
	    }
	} else {
	    echo "0 结果";
	}
}
?>
