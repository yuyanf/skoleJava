import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.text.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.event.*;
import java.io.FileNotFoundException;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;


public class LabyrintGUI extends Application {

  File minFil;
  Labyrint laby = null;
  Liste<String> utveiene;

  Text tekstboks = new Text();
  GridPane rutenett = new GridPane();


  public static void main(String[] args){
    launch(args);
  }


  public void start(Stage vindu) {
    FileChooser filvelger = new FileChooser();
    rutenett.setAlignment(Pos.CENTER);

    Button velgFilKnapp = new Button("Velg en labyrintfil");//knappen for å velge fil




    //Lage rutenett fra valgt fil.
    velgFilKnapp.setOnAction(e -> {
      rutenett.getChildren().clear(); //slette gamle labyrint når bruker velger ny fil
      minFil = filvelger.showOpenDialog(vindu);
      try {
        laby = Labyrint.lesFraFil(minFil); //vise labyrint grafisk hvor svart rute er svart rektangel og hvit rute er klikkbar knappe
        for(int i = 0; i < laby.totKol; i++){
          for(int j = 0; j< laby.totRad; j++){
            if (laby.labyrint[i][j] instanceof HvitRute){ //dersom det er en hvit rute
              Button b = new Button(); //lag en knapp
              b.setOnAction(d -> { //når bruker klikkr en hvit rute, finner utveier fra den ruta
                int kol = rutenett.getColumnIndex(b);
                int rad = rutenett.getRowIndex(b);
                Liste<String> utveier = laby.finnUtveiFra(kol, rad);
                int antallUtveier = utveier.stoerrelse();
                if(antallUtveier != 0) //dersom det er utveier
                {
                  boolean[][] losninger = LosningKonvertering.losningStringTilTabell(utveier.hent(0), laby.totKol, laby.totRad); //metode returnerer en 2D array med boolean verdi som viser utveier bestått av hvilke ruter
                  settFargePaaUtvei(losninger);//kall på metode for å farge den forste utveien gronnt
                  tekstboks.setText("Det finns total " + antallUtveier + " utvei(er) fra denne rute."); //endre tekst
                }
                else //dersom det er ingen utveier
                {
                  for(Node node: rutenett.getChildren()) {
                    node.setStyle(null);   //reset alle knappenes farge til default
                  }
                  b.setStyle("-fx-background-color: #cc0000");//farge rute til rodt
                  tekstboks.setText("Dessverre finns det ingen utvei fra denne rute. Trykk paa en annen rute eller velg ny fil");
                }

              });
              b.setPrefSize(30,30); //gi stoerrelse til knapp
              rutenett.add(b, laby.labyrint[i][j].kol, laby.labyrint[i][j].rad);// legg knapp til gridpane
            }
            else { //dersom det er en svart rute
              Rectangle r = new Rectangle(30,30,Color.BLACK); //lag rektanle med farge svart og legges til gridpane
              rutenett.add(r, laby.labyrint[i][j].kol, laby.labyrint[i][j].rad);
            }
          }
        }
      } catch (FileNotFoundException t) {}

      tekstboks.setText("Trykk paa en hvit rute for aa finne utveier");

    });//knapp-setOnAction



    //Scrollpane
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(rutenett);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);

    //Vbox
    VBox layout = new VBox();
    layout.setAlignment(Pos.CENTER);
    layout.setSpacing(30);
    layout.getChildren().addAll(velgFilKnapp, scrollPane, tekstboks);
    layout.setVgrow(scrollPane, Priority.ALWAYS);
    layout.setMargin(tekstboks, new Insets(0,0,30,0));//Inset(Top, Right, Bottom, Left)
    layout.setMargin(velgFilKnapp, new Insets(30,0,0,0));

    //Scene
    Scene scene = new Scene(layout);

    //Stage
    vindu.setHeight(600);
    vindu.setWidth(800);
    vindu.setTitle("Velkommen til Labyrint!");
    vindu.setScene(scene);
    vindu.show();
  }


  //metode for å farge utveie gronnt
  void settFargePaaUtvei (boolean[][] losning) {
    for(Node node: rutenett.getChildren()) {
      node.setStyle(null);  //reset gamle utveie til default farge
    }

    for(int rad = 0; rad < laby.totRad; rad++) {
      for(int kol= 0; kol < laby.totKol; kol++) {

        if(losning[rad][kol]) { //finn forst hvilken posisjon er true
          getNodeFromGridPane(rutenett, kol, rad).setStyle("-fx-background-color: #33cc33"); //send inn posisjon som parametre for å få true knapp, og farge den gronnt
        }
      }
    }
  }


  //metode for å returnere true knapp ved å ta i mot true posisjon
  private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
      for (Node node : gridPane.getChildren()) { //for each looper igjennom alle objekter i gridpane
          if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) { //dersom posisjon stemmer
            return node;
          }
      }
      return null;
  }





}
