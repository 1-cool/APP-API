<?php


header("Content-Type: text/json;charset=utf-8");
if ($_SERVER["REQUEST_METHOD"] == "POST")//verification post
{
    //recevie post data
    

    if($password==$pass)//verification password
    {
        // create connection
        $con = new mysqli($servername, $dbusername, $dbpassword,$dbname);

        // check connection
        if ($con->connect_error)
            die("connection fail: " . $con->connect_error);

        //return all user's data
        if(empty($number))
        {
            //print json data
            $result = $con->query("SELECT id, name, number,subject,phone,teacher,qq,sex,count FROM info");
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
                            'cont'=>$row["count"]
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
            if($modify!="id"&&$modify!="name"&&$modify!="number"&&$modify!="sex")
            {
                $result=$con->query("UPDATE info SET $modify=$modifydata WHERE number=$number");
                if($result)
                    echo "success";
                else
                    echo "fail";
            }
            else
                echo "not-allowed";
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
