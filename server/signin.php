<?php


header("Content-Type: text/json;charset=utf-8");
if ($_SERVER["REQUEST_METHOD"] == "POST")//verification post
{
    //recevie post data
    $token=checkinput($_POST["token"]);
    $pass="";
    $part=checkinput($_POST["part"]);
    $number=checkinput($_POST["number"]);
    $password=checkinput($_POST["password"]);
    $modify=checkinput($_POST["modify"]);
    $modifydata=checkinput($_POST["modifydata"]);
    $remark=checkinput($_POST["remark"]);
    $cnumber=checkinput($_POST["cnumber"]);

    if($token==$pass)//verification password
    {
        // create connection
        $con = new mysqli($servername, $dbusername, $dbpassword,$dbname);

        // check connection
        if ($con->connect_error)
            die("connection-fail:" . $con->connect_error);

        if(!empty($number)&&!empty($password))
        {
            $result=$con->query("SELECT * FROM `$part` WHERE number='$number'");
            if($result->num_rows>=1)//check user exist
            {
                $row=$result->fetch_assoc();
                $cpass=$row["password"];
                $flag=$row["flag"];
                if($password==$cpass)
                {
                    //return all user's data
                    if(empty($modify)||empty($modifydata)||empty($remark))
                    {
                        //print json data
                        $result = $con->query("SELECT * FROM `$part`");
                        if ($result->num_rows > 0)
                        {
                            $data=[];
                            while($row = $result->fetch_assoc())
                            {
                                $info = array
                                    (
                                        'id'=>$row["id"],
                                        'name'=>$row["name"],
                                        'num'=>$row["number"],
                                        'work'=>$row["subject"],
                                        'phone'=>$row["phone"],
                                        'teacher'=>$row["teacher"],
                                        'qq'=>$row["qq"],
                                        'sex'=>$row["sex"],
                                        'cont'=>$row["count"],
                                        'flag'=>$row["flag"]
                                    );
                                $data[]=$info;
                            }
                            $data = json_encode($data,JSON_UNESCAPED_UNICODE);
                            echo $data;
                        }
                    }

                    else
                    {
                        //modify data
                        if($modify!="id"&&$modify!="name"&&$modify!="number"&&$modify!="sex"&&$modify!="flag")
                        {
                            if($flag==6&&!empty($cnumber))
                                $number=$cnumber;
                            if($modify=='count')//change count
                            {
                                $before=((($con->query("SELECT * FROM `$part` WHERE number='$number'"))->fetch_assoc())["count"]);
                                $after=$before+$modifydata;
                                if((($con->query("SHOW TABLES LIKE '$number'"))->num_rows)!=1)//check count table
                                {
                                    $sql="CREATE TABLE `$number`(".
                                        "id INT UNSIGNED AUTO_INCREMENT,".
                                        "cbefore INT NOT NULL,".
                                        "cadd INT NOT NULL,".
                                        "cafter INT NOT NULL,".
                                        "remark VARCHAR(100) NOT NULL,".
                                        "PRIMARY KEY ( id )".
                                        ")ENGINE=InnoDB;";
                                    $result=$con->query("$sql");//create table
                                    if(!$result)
                                        echo "create-table-fail";
                                }
                                $sql="INSERT INTO `$number`".
                                    "(cbefore,cadd, cafter,remark)".
                                    "VALUES".
                                    "('$before','$modifydata','$after','$remark')";
                                $result=$con->query("$sql");//at number table record
                                if($result)
                                    echo "insert-success";
                                else
                                    echo "insert-fail";
                                $result=$con->query("UPDATE `$part` SET $modify='$after' WHERE number=$number");//update count
                                if($result)
                                    echo "count-update-success";
                                else
                                    echo "count-update-fail";
                            }
                            else//change other data
                            {
                                $result=$con->query("UPDATE `$part` SET $modify='$modifydata' WHERE number=$number");
                                if($result)
                                    echo "update-success";
                                else
                                    echo "update-fail";
                            }
                        }
                        else
                            echo "not-allowed";
                    }
                }
                else
                    echo "wrong-password";
            }
            else
                echo "user-does-not-exist";
        }

    }
}

//filter input
function checkinput($input)
{
    $input=trim($input);
    $input=stripslashes($input);
    $input=htmlspecialchars($input);
    return $input;
}
?>
