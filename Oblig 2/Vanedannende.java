class Vanedannende extends Legemidler {
  private int styrke;

  public Vanedannende(String navn, double pris, double virkestoff, int styrke) {
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public String toString() {
    return super.toString() + "| Styrke: " + Integer.toString(hentVanedannendeStyrke());
  }

  int hentVanedannendeStyrke() {
    return styrke;
  }
}//klassen Vanedannende
