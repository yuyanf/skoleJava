public class SortRute extends Rute {

  public SortRute(int kol, int rad, int totKol, int totRad){
    super(kol, rad, totKol, totRad);
  }

  public boolean erAapning() { //svart rute kan aldri være åpning
    return false;
  }

  public char tilTegn(){
    return '#';
  }
}
