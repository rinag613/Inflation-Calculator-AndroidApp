package com.example.androidfinalproject;

public class InflationCalc {
    private double rate;
    private double money;
    private double moneyLost;
    private double total;


    public InflationCalc(double mn, double rt) {
        total=0;
        rate=0;
        money=0;
        this.money=mn;
        this.rate = rt;
        rate =(rate/100)+1;
    }

    public void setRate(double rt){
        rate =rt;
    }
    public void setMoney(double mn){
        money= mn;
    }
    public double getRate(){
        return rate;
    }
    public double getMoney(){
        return money;
    }
    public void setTotal(double t){
        total=t;
    }
    private double checkAndGetGreaterThanZero (double value, String description)
    {
        if (value >0)
            return value;
        else
            throw new IllegalArgumentException (description + " must be greater than zero.");
    }

    public double calculate(){
        total = (getMoney()*getRate());
        moneyLost=total-money;
        return total;
    }
}