


class BlaaResept extends Resept {
  private double rabatt = 0.25; //rabatt er 75%
  public BlaaResept(Legemidler legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public double prisAaBetale() {
    return legemiddel.hentPris()*rabatt;
  }

  public String farge() {
      return "blaa";
  }
}//klassen BlaaResept
