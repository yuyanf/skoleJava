import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Dataklynge {

  int [][] biterArray2 = new int[3][3]; //lager en ny array av array for å konvertere string til int
  private String[][] biterArray = new String[3][3]; //lager en array av array hvor hver har størrelser-3 for å holde string fra filen
  private int antallRack;
  private Rack[] rackArray = new Rack[100]; //en liste av Rackobjekter- array må ha en størrelse og jeg setter den til 100 først og fremst
  private int maksAntNoder;
  // instansvariabler holder orden i antall Rack og mask antall noder per rack


  public Dataklynge(String filNavn) {  //konstruktøren tar imot parameter som leser inn en fil
    int posisjon = 0;
    try {
      Scanner minFil = new Scanner(new File(filNavn)); //åpner og leser filen
      while (minFil.hasNextLine()) { //når filen har neste linje, while-løkka looper
        String [] biter = minFil.nextLine().split(" "); //spliter linjevis med mellomrom og oppretter array biter
        biterArray[posisjon] = biter; //sett array biter inni array biterArray
        posisjon++; //oppdaterer posisjon
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    for (int i = 0; i < biterArray.length; i++) { //for-løkka looper igjennom biterArray for å gjøre alle String om til int, og settes til en ny array
      for (int j = 0; j < biterArray[i].length; j++) {
          int denneErInt = Integer.parseInt(biterArray[i][j]);
          biterArray2[i][j] = denneErInt;
      }
    }
    maksAntNoder = biterArray2[0][0]; //setter maks antall node per rack
    Rack rack1 = new Rack(maksAntNoder); //opprett en rackobjekt
    rackArray[0] = rack1; //denne ny Rackobjekt settes som første rackobjekt i lista RackArray
    antallRack = 1; //instansvariabel antallRack teller på hvor mange rack som finnes, og samtidig dens verdi = neste ny racks posisjon i rackArray
  }




  void settInnNode(Node enNode) { //denne metoden gjør sånn at det kan bare være maks 12 noder i en rack
    int hvorMangeNoder = rackArray[antallRack-1].antallNodeIRack();  //metode finner antall noder i siste rack av rackArray ved å kalle på metode antallNodeIRack.

    if(hvorMangeNoder < maksAntNoder) { //Dersom antall noder som finnes i rack allerede nå er mindre enn maks antall noder per rack som er tall 12, betyr det at rack har ledig plass
      rackArray[antallRack-1].settInnNoden(enNode); //denne setter inn en node i ledig posisjon i racket.
    }
    else { // dersom antall noder erlik eller større enn maks antall noder per rack
      Rack nyRack = new Rack(maksAntNoder); //oppretter en ny rackobjekt
      rackArray[antallRack] = nyRack; //ny rackobjekt settes inni rackArray med en gang
      antallRack++; //oppdaterer antall rack og gjør klar for neste ny rack
      rackArray[antallRack-1].settInnNoden(enNode); //sett inn nodeobjekt i siste rack sin posisjon 0, som er den nye rackobjekt vi nettopp lagde
    }

  }

  int antallProsessorer() {
    int total = 0; //sett teller til 0 først
    for(int i = 0; i < rackArray.length; i++) { //for-løkka looper igjennom rackArray
      if(rackArray[i] != null) {//sjekker om noen rackobjekter er null fordi vi satt plass til 100
        total += rackArray[i].prosessorene();
      }
    }
    return total;
  }

  int noderMedNokMinne(int paakrevdMinne) {
    int total = 0;
    for(int i = 0; i < rackArray.length; i++) {
      if(rackArray[i] != null) {
        total += rackArray[i].noderMedNokMinne(paakrevdMinne);
      }
    }
    return total;
  }




  int antallRacks() { //metode returnerer antall racks totalt
    return antallRack;
  }
}
