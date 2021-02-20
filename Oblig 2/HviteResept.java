class HviteResept extends Resept {
  public HviteResept(Legemidler legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public double prisAaBetale() {
    return legemiddel.hentPris();
  }

  public String farge() {
      return "hvit";
  }

}//klassen HviteResept
