
class Militarresept extends HviteResept {
  private double etterRabatt = 0;// 100% rabatt er gratis

  public Militarresept(Legemidler legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public double prisAaBetale() {
    return etterRabatt;
  }
}//klassen Militarresept
