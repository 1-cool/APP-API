<?php


header("Content-Type: text/json;charset=utf-8");
if ($_SERVER["REQUEST_METHOD"] == "POST")//verification post
{
    //recevie post data
    $token=checkinput($_POST["token"]);
    $pass=" ";
    $part=checkinput($_POST["part"]);
    $number=checkinput($_POST["number"]);
    $password=checkinput($_POST["password"]);
    $modify=checkinput($_POST["modify"]);
    $modifydata=checkinput($_POST["modifydata"]);
    $note=checkinput($_POST["note"]);
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
            $result=$con->query("SELECT * FROM `$part` WHERE number='$number' ");
            if($result->num_rows>=1)//check user exist
            {
                $row=$result->fetch_assoc();
                $cpass=$row["password"];
                $flag=$row["flag"];
                if($password==$cpass)
                {
                    $name=$row["name"];//get name
                    //return all user's data
                    if(empty($modify)||empty($modifydata))
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
                                        "id"=>$row["id"],
                                        "name"=>$row["name"],
                                        "num"=>$row["number"],
                                        "work"=>$row["subject"],
                                        "phone"=>$row["phone"],
                                        "teacher"=>$row["teacher"],
                                        "qq"=>$row["qq"],
                                        "sex"=>$row["sex"],
                                        "cont"=>$row["count"],
                                        "flag"=>$row["flag"]
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
                        if($modify=="subject"||$modify=="phone"||$modify=="teacher"||$modify=="qq"||$modify=="count"||$modify=="password")
                        {
                            if($flag==6&&!empty($cnumber))
                                $number=$cnumber;
                            if($modify=="count")//change count
                            {
                                if(empty($note))
                                {
                                    echo "note-can`t-be-empty";
                                    exit(-1);
                                }
                                $before=((($con->query("SELECT * FROM `$part` WHERE number='$number'"))->fetch_assoc())["count"]);
                                $after=$before+$modifydata;
                                if((($con->query("SHOW TABLES LIKE '$number'"))->num_rows)!=1)//check count table
                                {
                                    $sql="CREATE TABLE `$number`(".
                                        "id INT UNSIGNED AUTO_INCREMENT,".
                                        "cbefore INT NOT NULL,".
                                        "cadd INT NOT NULL,".
                                        "cafter INT NOT NULL,".
                                        "note VARCHAR(100) NOT NULL,".
                                        "change_by VARCHAR(15) NOT NULL,".
                                        "date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,".
                                        "PRIMARY KEY ( id )".
                                        ")ENGINE=InnoDB;";
                                    $result=$con->query("$sql");//create table
                                    if(!$result)
                                    {
                                        echo "create-table-fail";
                                        exit(-1);
                                    }
                                }
                                $sql="INSERT INTO `$number`".
                                    "(cbefore,cadd, cafter,note,change_by)".
                                    "VALUES".
                                    "('$before','$modifydata','$after','$note','$name')";
                                $result=$con->query("$sql");//at number table record
                                if($result)
                                {
                                    echo "insert-success";
                                    $result=$con->query("UPDATE `$part` SET $modify='$after' WHERE number='$number'");//update count
                                    if($result)
                                        echo "-count-update-success";
                                    else
                                        echo "-count-update-fail";
                                }
                                else
                                    echo "insert-fail";

                            }
                            else//change other data
                            {
                                $result=$con->query("UPDATE `$part` SET $modify='$modifydata' WHERE number='$number'");
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
