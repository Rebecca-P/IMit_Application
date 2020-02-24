package sample;

import java.lang.Integer;
import java.util.Scanner;
import java.util.ArrayList;

//The original cell acts as a prototype and takes an active role in creating the copy.
public class Cell implements Cloneable {

//Variables
    //Status
    private boolean vie;
    private int time_to_live=16;//durée de vie d'une cellule (en cycle)
    private int time;//temps de vie
    private double temperature_actuelle ;
    private double ph_actuelle;

    //Contrainte
    //temperature
    private double max_temperature=35.0;
    private double min_temperature=25.0;

    //pH
    private double max_pH=8.6;
    private double min_pH=2.4;

    //Ethanol
    private double max_Ethanol=8.0;
    private double ethanol_produit = 0.0;

    //Oxigene
    private boolean aerobiose = true;

    //Glucose
    private boolean glucose = true;


//Constructor


    public Cell(double temperature_actuelle, double ph_actuelle, boolean aerobiose, boolean glucose) {
        this.temperature_actuelle = temperature_actuelle;
        this.ph_actuelle = ph_actuelle;
        this.aerobiose = aerobiose;
        this.glucose = glucose;
        vie=true;
        time=1;

    }



//GET & SET


    public boolean isVie() {
        return vie;
    }

    public void setVie(boolean vie) {
        this.vie = vie;
    }

    public int getTime_to_live() {
        return time_to_live;
    }

    public void setTime_to_live(int time_to_live) {
        this.time_to_live = time_to_live;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTemperature_actuelle() {
        return temperature_actuelle;
    }

    public void setTemperature_actuelle(double temperature_actuelle) {
        this.temperature_actuelle = temperature_actuelle;
    }

    public double getPh_actuelle() {
        return ph_actuelle;
    }

    public void setPh_actuelle(double ph_actuelle) {
        this.ph_actuelle = ph_actuelle;
    }

    public double getMax_temperature() {
        return max_temperature;
    }

    public void setMax_temperature(double max_temperature) {
        this.max_temperature = max_temperature;
    }

    public double getMin_temperature() {
        return min_temperature;
    }

    public void setMin_temperature(double min_temperature) {
        this.min_temperature = min_temperature;
    }

    public double getMax_pH() {
        return max_pH;
    }

    public void setMax_pH(double max_pH) {
        this.max_pH = max_pH;
    }

    public double getMin_pH() {
        return min_pH;
    }

    public void setMin_pH(double min_pH) {
        this.min_pH = min_pH;
    }

    public double getMax_Ethanol() {
        return max_Ethanol;
    }

    public void setMax_Ethanol(double max_Ethanol) {
        this.max_Ethanol = max_Ethanol;
    }

    public double getEthanol_produit() {
        return ethanol_produit;
    }

    public void setEthanol_produit(double ethanol_produit) {
        this.ethanol_produit = ethanol_produit;
    }

    public boolean isAerobiose() {
        return aerobiose;
    }

    public void setAerobiose(boolean aerobiose) {
        this.aerobiose = aerobiose;
    }

    public boolean isGlucose() {
        return glucose;
    }

    public void setGlucose(boolean glucose) {
        this.glucose = glucose;
    }

    //function
    public void iLive(){//Cycle de vie d'une cellule

            if(time>=time_to_live)
            {
                vie=false;//Elle meurt naturellement
            }else{
                time ++;//Son temps passe
            }

    }
    public void upEthanol(){ethanol_produit +=1.0;}//Augmente son taux d'éthanol
    public void nulEthanol(){if (ethanol_produit>0.0)ethanol_produit -= 1.0;}//Réduit son teaux d'ethanol

    //Clonage
    public Object iSplit()
    {
        Object myClone = null;
        try
        {
            myClone= super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }

        return myClone;

    }




}
