abstract class Legemidler {
  protected String navn;
  protected static int ID = -1; //ID starter med -1 slik at første legemidler starter med ID 0
  protected double pris;
  protected double virkestoff;
  protected int virkeligID;//Hver resept får da sitt unike ID fra rID.


  public Legemidler(String navn, double pris, double virkestoff) {
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    ID++;
    virkeligID = ID;
  }//konstruktøren til klassen Legemidler

  public String toString() {
    return String.format("ID: %3d | Navn: %-10s | Pris: %6.2f | Virkestoff: %6.2f", hentID(), hentNavn(), hentPris(), hentVirkestoff());
    //formatting utskrift. 4stk "%" sier at det skal kalle på fire metoder; d står for int, s står får String og f står for float; tallet 6 er avstand mens tallet 2 sier det er 2 plasser bak komma.
  }

  int hentID() {
    return virkeligID;
  }

  String hentNavn() {
    return navn;
  }

  double hentPris() {
    return pris;
  }

  double hentVirkestoff() {
    return virkestoff;
  }

  void settNyPris(double nyPris){
    pris = nyPris;
  }
}//klassen Legemidler
