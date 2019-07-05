<?php
header("Content-Type: text/html;charset=utf-8");             //确保不会中文乱码
if(empty($_POST["name"])||empty($_POST["num"]))
        echo "填写的数据不能为空";
else
{
        $name=$_POST["name"];                               //获取姓名
        $num=$_POST["num"];                                 //获取学号
        $file=$num.'/'.$num.".txt";                         //签到日志位置
        if(!file_exists($file))                             //判断文件是否存在，来判断是否存在该用户
        {
                mkdir($num);                            //创建文件夹
                file_put_contents($file, '', FILE_APPEND | LOCK_EX);            //创建签到日志
                echo "用户创建成功";
        }
        else
                echo "此用户已存在";
}
?>
