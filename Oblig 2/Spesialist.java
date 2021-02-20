class Spesialist extends Lege implements Godkjenningsfritak {
  private int kontrollID;

  public Spesialist(String navn, int kontrollID) {
    super(navn);
    this.kontrollID = kontrollID;
  }

  public String toString() {
    return super.toString() + ", kontrollID: " + Integer.toString(hentKontrollID());
  }

  public int hentKontrollID() {
    return kontrollID;
  }
}//klassen Spesialist
