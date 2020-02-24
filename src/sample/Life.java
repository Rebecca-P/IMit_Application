package sample;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

//Class permettant de simuler simplement une population de cellules
public class Life {

    private ConcurrentHashMap<Integer, Cell> group = new ConcurrentHashMap<Integer, Cell>();

    private int limitation;
    private Random rand;
    private int nbSplit = 0;

    public ConcurrentHashMap<Integer, Cell> getGroup() {
        return group;
    }

    public void setGroup(ConcurrentHashMap<Integer, Cell> group) {
        this.group = group;
    }

    public void addGroup(Cell o)
    {
        group.put(group.size()+1 ,o);
    }

    //Simule un tour de vie
    public void runLife()
    {

        Iterator<Map.Entry<Integer, Cell>> it = group.entrySet().iterator();
        while (it.hasNext())
        {

            Map.Entry<Integer, Cell> cello = it.next();
            Cell o = cello.getValue();//on copie la cellule pour lamettre à jour
            if (o.isVie()){
                if(!o.isGlucose())//Sans glucose, les cellules meuts automatiquement car elle ne peuvent plus produire d'énergie nécessaire à leur survie
                {

                    o.setVie(false);
                    it.remove();
                }else if(!o.isAerobiose())
                {
                    o.upEthanol();
                    if (o.getEthanol_produit()>o.getMax_Ethanol())
                    {
                        o.setVie(false);
                        it.remove();
                    }
                }else if(o.isAerobiose())//Donc oxyge + glucose
                {
                    o.nulEthanol();
                    if(o.getTemperature_actuelle()>o.getMax_temperature() || o.getTemperature_actuelle()<o.getMin_temperature()) {//Température trop haut ou trop basse
                        o.setVie(false);
                        it.remove();
                    }
                    else if(o.getPh_actuelle()>o.getMax_pH() || o.getPh_actuelle()<o.getMin_pH()) {//Ph trop haut ou trop bas
                        o.setVie(false);
                        it.remove();
                    }
                    else if (rand.nextInt(100) %2==0)//condition du clonage
                    {
                        if(group.size()<=limitation)
                        {

                            Cell clone =(Cell) o.iSplit();
                            clone.setTime(1);
                            group.put(nbSplit,clone);
                            nbSplit+=1;
                        }
                    }
                }

                o.iLive();
                group.put(cello.getKey(),o);//on remet la copie à jour à la place de l'ancienne version
            }else{
                it.remove();//On la retire de la liste
            }
            if(group.size()>=limitation)//la taille demandé a été atteinte
            {
                break;
            }else if(group.size()==0)//aucun survivant
            {
                break;
            }

        }


    }

    public void start(int limit)//Initialisation du nombre demandé(limitation) et de random
    {
        rand = new Random();
        limitation=limit;
    }
}
