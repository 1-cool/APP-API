<?php
header("Content-Type: text/html;charset=utf-8");
$name=$_POST["name"];
$num=$_POST["num"];
$file=$num.'/'.$num.".txt";
if(date("Y/m/d")!=date("Y/m/d",filemtime($file)))
{
        file_put_contents($file, date("Y/m/d H:i:s")."\n", FILE_APPEND | LOCK_EX);
        echo "签到成功<br>"; echo $name."<br>";
        echo "你的学号是："; echo $num."<br>";
        echo date("Y/m/d H:i:s");
}
else
        echo "签到失败";
?>
