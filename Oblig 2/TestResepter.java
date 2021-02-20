class TestResepter {

  public static void main(String[] args) {
    Vanlig legemiddel1 = new Vanlig("Paracet", 100, 500.2);
    Narkotisk legemiddel2 = new Narkotisk("Heroin", 1589.45, 2.2, 89);
    Vanedannende legemiddel3 = new Vanedannende("Sovepiller", 589.45, 5.2, 49);
    Lege lege1 = new Lege("Jenny");
    HviteResept hvite = new HviteResept(legemiddel1, lege1, 1, 3);
    BlaaResept blaa = new BlaaResept(legemiddel1, lege1, 2, 3);
    Militarresept mili = new Militarresept(legemiddel2, lege1, 3, 4);
    PResept pr = new PResept(legemiddel3, lege1, 4);
    System.out.println(hvite);
    System.out.println(blaa);
    System.out.println(mili);
    System.out.println(pr);
  }

}
