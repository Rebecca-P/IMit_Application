package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Controller {
//Variable
    private int _cellWant;
    private int _cellStart;
    private double _tempStart;
    private double _pHStart;
    private boolean _oxygeneWant;
    private boolean _glucoseWant;
    private Life cellLife;

//Elements de View
   @FXML
   private TextField cellWant;
    @FXML
    private TextField cellStart;
    @FXML
    private TextField tempStart;
    @FXML
    private TextField pHWant;
    @FXML
    private CheckBox oxygeneWant;
    @FXML
    private CheckBox glucoseWant;
    @FXML
    private ScatterChart<Number,Number> nuagePoint;


    public void onValidation(ActionEvent actionEvent) {//Lorsque l'on clique sur le bouton valider

        //On récupere les valeurs du formulaire
        if(cellWant.getText().matches("[0-9]+"))
        {
            try {
                _cellWant=Integer.parseInt(cellWant.getText());
            }
            catch (NumberFormatException e)
            {
                _cellWant = 0;
            }
        }
        if(cellStart.getText().matches("[0-9]+"))
        {
            try {
                _cellStart=Integer.parseInt(cellStart.getText());
            }
            catch (NumberFormatException e)
            {
                _cellStart = 0;
            }
        }
        if(tempStart.getText().matches("^\\d*\\.\\d+|\\d+\\.\\d*$"))
        {
            try {
                _tempStart=Double.parseDouble(tempStart.getText());
            }
            catch (NumberFormatException e)
            {
                _tempStart = 0;
            }
        }
        if(pHWant.getText().matches("^\\d*\\.\\d+|\\d+\\.\\d*$"))
        {
            try {
                _pHStart=Double.parseDouble(pHWant.getText());
            }
            catch (NumberFormatException e)
            {
                _pHStart = 0;
            }
        }
        _oxygeneWant=oxygeneWant.isSelected();
        _glucoseWant=glucoseWant.isSelected();

        //On commence la simulation
        lifeRun();
    }

    //Fonction permttant de supprimer les doublons
    //Fonction récupéré
    public <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {

        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {

            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        return newList;
    }


    //Simutation où la derniere action est un nuage de points représentant l'évolution de la population de céllule
    public void lifeRun()
    {
        cellLife = new Life();

        if(_cellStart>0)
            cellLife.addGroup(new Cell(_tempStart, _pHStart, _oxygeneWant, _glucoseWant));
        ConcurrentHashMap<Integer, Cell> group;
        Cell o;
        for (int i=0;i<_cellStart-1;i++)
        {
            group=cellLife.getGroup();
            o=group.get(1);
            cellLife.addGroup((Cell)o.iSplit());
        }



        cellLife.start(_cellWant);
        ArrayList<Integer> point = new ArrayList<Integer>();

        while(cellLife.getGroup().size()<_cellWant)
        {
            point.add(cellLife.getGroup().size());
            cellLife.runLife();

            if (cellLife.getGroup().size()==0)
                break;

        }
        point.add(cellLife.getGroup().size());
        if (_oxygeneWant)
            point = removeDuplicates(point);
        final NumberAxis xAxis = new NumberAxis(0, point.size(), 1);
        final NumberAxis yAxis = new NumberAxis(0, _cellWant+10, 1);
        nuagePoint = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Temps (s)");
        yAxis.setLabel("Populations( en cellules)");
        nuagePoint.setTitle("Population en Fonction du temps");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Population");


        int i =0;
        for (Integer a:point ) {
            series1.getData().add(new XYChart.Data(i, a));
            i++;

        }

        nuagePoint.getData().addAll(series1);
        Group root = new Group(nuagePoint);
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();

        stage.setTitle("Evolution");
        stage.setScene(scene);
        stage.show();//Nouvelle fenetre



    }
}
