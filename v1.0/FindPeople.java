package com.example.dianming;

import android.widget.ImageView;


public class FindPeople {
    private String name,num;
    private ImageView pic;

    public  String getName(String name) {
        Addpeople addpeople=new Addpeople();
        addpeople.add();
        int i;
        for (i=0;i<addpeople.getList().size();i++){
            if(String.valueOf(addpeople.getList().get(i).get("name"))==name){
                break;
            }
        }
        return String.valueOf(addpeople.getList().get(i).get("name"));




    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum(String num) {
        Addpeople addpeople=new Addpeople();
        addpeople.add();
        int i;
        for (i=0;i<addpeople.getList().size();i++){
            if(String.valueOf(addpeople.getList().get(i).get("num"))==num){
                break;
            }
        }
        return String.valueOf(addpeople.getList().get(i).get("num"));
    }

    public void setNum(String num) {
        this.num = num;
    }

    public ImageView getPic() {
        return pic;
    }

    public void setPic(ImageView pic) {
        this.pic = pic;
    }

    public void add(){
        Addpeople addpeople=new Addpeople();
        addpeople.add();


    }


}

