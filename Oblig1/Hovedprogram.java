public class Hovedprogram {


  public static void main(String[] args) {

    Dataklynge abel = new Dataklynge("dataklynge.txt");

    for(int i = 0; i < abel.biterArray2[1][0]; i++) {   //looper 650 ganger og lage 650 noder og sett dem i rack
      Node noden = new Node(abel.biterArray2[1][1], abel.biterArray2[1][2]); //biterArray sin posisjon er minnestørrelse og antall prosessorer
      abel.settInnNode(noden);
    }
    for(int i = 0; i < abel.biterArray2[2][0]; i++) { // samme som forrige men looper 16 ganger
      Node noden = new Node(abel.biterArray2[2][1], abel.biterArray2[2][2]);
      abel.settInnNode(noden);
    }



    System.out.println("Antall noder med 32GB eller mer: " + abel.noderMedNokMinne(32));
    System.out.println("Antall noder med 64GB eller mer: " + abel.noderMedNokMinne(64));
    System.out.println("Antall noder med 128GB eller mer: " + abel.noderMedNokMinne(128));

    System.out.println("Antall prosessorer: " + abel.antallProsessorer());
    System.out.println("Antall rack: " + abel.antallRacks());

  }
}
