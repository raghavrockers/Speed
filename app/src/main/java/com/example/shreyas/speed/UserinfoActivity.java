package com.example.shreyas.speed;

/**
 * Created by Shreyas on 11/3/2017.
 */

public class UserinfoActivity {

    private String userName;
    private String userNumber;

    public UserinfoActivity(){

    }
    public UserinfoActivity(String userName , String userNumber) {
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserNumber(){
        return userNumber;
    }
}
