class Narkotisk extends Legemidler {
  private int styrke;

  public Narkotisk(String navn, double pris, double virkestoff, int styrke) {
    super(navn, pris, virkestoff); //initialisere arvede instansvariabler fra superklassen Legemidler
    this.styrke = styrke;
  }

  public String toString() {
    return super.toString() + "| Styrke: " + Integer.toString(hentNarkotiskStyrke()); //arver toString fra superklasse og legge til styrke i tillegg.
  }

  int hentNarkotiskStyrke() {
    return styrke;
  }
}//klassen Narkotisk
