class PResept extends HviteResept {

  private double rabatt = 108;

  public PResept(Legemidler legemiddel, Lege utskrivendeLege, int pasientId) {
    super(legemiddel, utskrivendeLege, pasientId, 3);
  }

  public double prisAaBetale() {
    if (legemiddel.hentPris() >= rabatt) { //if-sjekk sjekker om originale pris er mer enn 108, hvis ikke, legemiddel er gratis
      return legemiddel.hentPris() - 108;
    }
    else {
      return 0;
    }
  }
}//klassen PResept
