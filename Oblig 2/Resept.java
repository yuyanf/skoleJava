abstract class Resept {
  protected static int rID = -1;//Holder styr på hvor mange unike resepter som er skrevet ut.
  protected Legemidler legemiddel;
  protected Lege utskrivendeLege;
  protected int pasientId;
  protected int reit;
  protected int virkeligID;//Hver resept får da sitt unike ID fra rID.

  public Resept(Legemidler legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasientId = pasientId;
    this.reit = reit;
    rID++;
    virkeligID = rID;
  }//konstruktøren til Resept

  public String toString() {
      return String.format("ReseptID:     %3d | Farge: %4s | Lege: %-10s\nPasientID:    %3d | Reit: %2d\nLegemiddel%s\nPris etter rabatt: %8.2f.-\n\n", hentID(), farge(), utskrivendeLege, hentPasientId(), hentReit(), legemiddel, prisAaBetale());
    //formating av utskrift
  }

  int hentID() {
    return virkeligID;
  }

  Legemidler hentLegemiddel() {
    return legemiddel;
  }

  Lege hentLege() {
    return utskrivendeLege;
  }

  int hentPasientId() {
    return pasientId;
  }

  int hentReit() {
    return reit;
  }

  public boolean bruk(){
    if (reit == 0) {
      return false;
    }
    reit--; //minus en reit hvergang lege bruker en resept
    return true;
  }

  abstract public String farge();

  abstract public double prisAaBetale();
} //klassen Resept
