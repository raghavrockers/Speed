package com.example.shreyas.speed;

/**
 * Created by Shreyas on 11/11/2017.
 */

public class ZonepointsActivity {

    private String first_coordinate;
    private String second_coordinate;

    public ZonepointsActivity(String first_coordinate , String second_coordinate){
        this.first_coordinate = first_coordinate;
        this.second_coordinate = second_coordinate;
    }

    public ZonepointsActivity(){

    }

    public void setFirstCoordinate(String first_coordinate){
        this.first_coordinate = first_coordinate;
    }

    public void setSecondCoordinate(String second_coordinate){
        this.second_coordinate = second_coordinate;
    }

    public String getFirstCoordinate(){
        return first_coordinate;
    }

    public String getSecondCoordinate(){
        return second_coordinate;
    }

}
