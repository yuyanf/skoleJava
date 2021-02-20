public class Rack {
  private Node[] nodeArray;  //deklarere instansvariabel nodeArray- en array av nodeobjekter
  private int antallNode = 0; //instansvariabler holder orden i hvor mange noder som finnes i rack

  public Rack(int maksNoder) { //konstruktøren tar imot parameter som maks antall noder per rack og settes som størrelse til nodeArray, slikt har vi laget en liste av mange nodeobjekter men null verdi hver
    nodeArray = new Node[maksNoder];
  }

  int antallNodeIRack() { // metode returnerer antall noder som finnes i en rack
    return antallNode;
  }

  void settInnNoden(Node node) {
    nodeArray[antallNode] = node;//antallNode kan sees som en ledig posisjon, sett nodeobjekt inni siste rack fra rackArray sin nodeArray sin ledig posisjon
    antallNode++;//oppdterer instansvariabler fra Rack klasse
  }

  int prosessorene() {
    int totalProsessor = 0;

    //Looper gjennom array med Noder, for hver node, legg til antall prosessorer noden har til "totalProsessor"
    for(int i = 0; i < nodeArray.length; i++) {
      if(nodeArray[i] != null) totalProsessor += nodeArray[i].mineProsessorer();
    }

    return totalProsessor;
  }

  int noderMedNokMinne(int kravMinne) {
    int totalNodeMedNokMinne = 0;

    for(int i = 0; i < nodeArray.length; i++) {
      if(nodeArray[i] != null && nodeArray[i].mineMinner(kravMinne)) totalNodeMedNokMinne++;
    }

    return totalNodeMedNokMinne;
  }

}
