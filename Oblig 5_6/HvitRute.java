public class HvitRute extends Rute{

  public HvitRute(int kol, int rad, int totKol, int totRad){
    super(kol, rad, totKol, totRad);
  }

  public boolean erAapning() { //sjekker om hvit rute er åpning 
    if(kol == 0 || kol == totKol-1 || rad == 0 || rad == totRad-1) {
      return true;
    }
    else {
      return false;
    }
  }

  public char tilTegn(){
    return '.';
  }
}
