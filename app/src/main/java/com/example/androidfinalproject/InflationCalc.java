package com.example.androidfinalproject;

public class InflationCalc {
    private double rate;
    private double money;
    private double moneyLost;
    private double total;


    public InflationCalc(double mn, double rt) {
        this.money=mn;
        this.rate = rt;
        rate =(rate/100)+1;
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