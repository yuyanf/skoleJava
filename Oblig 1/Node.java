public class Node {
  private int minne;
  private int prosessor; //instansvariabler minne og prosessor
  public Node(int minneStorrelse, int antallProsessor) { //konstruktøren tar imot to parametre
    minne = minneStorrelse;
    prosessor = antallProsessor;
  }

  int mineProsessorer() {
    return prosessor;
  }

  boolean mineMinner(int kravet) {
    if(minne >= kravet) {
      return true;
    }
    else {
      return false;
    }
  }

}
