import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Labyrint {
  public static Rute[][] labyrint;
  public Liste<String> utveier = new Lenkeliste<String>();
  public int totKol;
  public int totRad;

  private Labyrint(Rute[] ruter, int totKol, int totRad) {
    labyrint = new Rute[totKol][totRad];

    for(int i = 0; i < ruter.length; i++){ //forløkka looper igjennom antall ganger som antall ruter for å sette hver rute inn i labyrinten
      int kol = ruter[i].hentKol();
      int raden = ruter[i].hentRad();
      labyrint[kol][raden] = ruter[i];
      this.totKol = totKol;
      this.totRad = totRad; 
    }
  }

  public String toString(){
    String utskriften = "";
    for (int rad = 0; rad < labyrint[0].length; rad++){
      for (int kol = 0; kol < labyrint.length; kol++){
        utskriften += labyrint[kol][rad].tilTegn();
      }
      utskriften += "\n";
    }

    return utskriften;
  }// toString skriver ut labyrint i tegn form


  static Labyrint lesFraFil(File fil) throws FileNotFoundException{

    Scanner minLeser = new Scanner(fil);

    String[] kolRad = minLeser.nextLine().split(" "); //leser første linje i filen om antall koloner og rader- det blir storrelse til labyrint

    int totKol = Integer.parseInt(kolRad[1]);
    int totRad = Integer.parseInt(kolRad[0]);
    Rute[] ruter = new Rute[totKol*totRad];//oppretter en tom array som skal beholde alle ruter senere

    int rad = 0;
    int posisjon = 0; //posisjon i ruter array
    while (minLeser.hasNextLine()){
      String[] liste = minLeser.nextLine().split(""); //oppretter en liste og spliter hver linje etter karakter og settes inni lista
      for (int kol = 0; kol < liste.length; kol++){ //looper antall liste-ganger
        if (liste[kol].equals("#")){
          SortRute svart = new SortRute(kol, rad, totKol, totRad); //opprett svart ruter
          ruter[posisjon] = svart; //sett objekt i tom ruter array vi lagde tidligere
          posisjon++; //oppdaterer posisjon og gjør klar til neste rute
        } else if (liste[kol].equals(".") && (kol == 0 || kol == totKol-1 || rad == 0 || rad == totRad-1)){ //sjekk om det kan være en åpning
          Aapning aapne = new Aapning(kol, rad, totKol, totRad);
          ruter[posisjon] = aapne;
          posisjon++;
        } else { //ellers hvit
          HvitRute hvit = new HvitRute(kol, rad, totKol, totRad);
          ruter[posisjon] = hvit;
          posisjon++;
        }
      }
      rad++; //oppdaterer rad etter en linje er lest ferdig siden jeg starter med koloner også rad
    }

    Labyrint laby = new Labyrint(ruter, totKol, totRad); //oppretter labyrint objekt laby

    for(int i = 0; i < ruter.length; i++){ //forløkka looper igjennom antall ganger som antall ruter
      ruter[i].settLab(laby);//sett labyrint vi nettopp opprettet som laby i rute slik at hver rute hver hvilken labyrint den er en del av
      ruter[i].finnNabo(); //kall på finnNabo slik at hver rute vet sin nabo
    }

    return laby;

  }//lesFraFil

  public Liste<String> finnUtveiFra(int kol, int rad){
    utveier = new Lenkeliste<String>();
    if(labyrint[kol][rad].tilTegn() == '#') { //dersom start rute er svart
      utveier.leggTil("Ugyldig start rute.");
    }
    else { //ellers kall på metoden
      labyrint[kol][rad].finnUtvei();
    }

    return utveier; //retunerer liste
  }


}//Labyrint
