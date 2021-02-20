class Hovedprogram {
  public static void main(String[] args) {
    Vanlig legemiddel1 = new Vanlig("Paracet", 100, 6.2);
    Narkotisk legemiddel2 = new Narkotisk("Heroin",3089, 2.2, 89);
    Vanedannende legemiddel3 = new Vanedannende("Sovepiller", 589.45, 5.2, 49);
    Lege lege1 = new Lege("Jenny");
    Spesialist lege2 = new Spesialist("Ludo", 12345);
    HviteResept hvite = new HviteResept(legemiddel1, lege1, 1, 1);
    BlaaResept blaa = new BlaaResept(legemiddel2, lege1, 2, 3);
    Militarresept mili = new Militarresept(legemiddel2, lege2, 3, 4);
    PResept pr = new PResept(legemiddel3, lege2, 4);
    hvite.bruk();
    blaa.bruk();
    mili.bruk();
    pr.bruk();
    System.out.println(pr);
    legemiddel3.settNyPris(1978);//sett ny pris på legemiddel3
    System.out.println(hvite);//null rabatt til paracet med Jenny
    System.out.println(blaa);// 75% rabatt til Heroin med Jenny
    System.out.println(mili);// gratis til Heroin med Ludo
    System.out.println(pr); //-108 kroner til Sovepiller med Ludo

  }
}
