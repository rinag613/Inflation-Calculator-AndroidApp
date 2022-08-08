package com.example.androidfinalproject;

public class InflationCalc {
    private double rate;
    private double money;

    public InflationCalc(){
        this.rate = 0;
        this.money=0;
    }
    public void setRate(double rt){
        rate = checkAndGetGreaterThanZero(rt, "Rate")/100;
    }
    public void setMoney(double mn){
        money = checkAndGetGreaterThanZero(mn, "Money")/100;
    }
    public double getRate(){
        return rate;
    }
    public double getMoney(){
        return money;
    }
    public InflationCalc(double rt, double mn){
        rate =checkAndGetGreaterThanZero(rt, "Rate")/100;

        money = checkAndGetGreaterThanZero(mn, "Money");
    }

    private double checkAndGetGreaterThanZero (double value, String description)
    {
        if (value >0)
            return value;
        else
            throw new IllegalArgumentException (description + " must be greater than zero.");
    }

    public double calculate(){
        double mn=getMoney();
        double total = (getMoney()*getRate())+mn;
        return total;
    }
}