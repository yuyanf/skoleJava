import java.util.concurrent.*;// gjør klar for å lage barrierer etterpå

abstract class Rute {
  protected int kol, rad, totKol, totRad;
  protected Labyrint laby;
  protected Rute nord, syd, vest, ost;
  protected Rute[] naboer = new Rute[4];

  abstract boolean erAapning();
  abstract char tilTegn();

  public Rute(int kol, int rad, int totKol, int totRad){
    this.kol = kol;
    this.rad = rad;
    this.totKol = totKol;
    this.totRad = totRad;
    nord = syd = vest = ost = null; //sett alle naboruter til null først
    laby = null;
  }

  public int hentKol(){
    return kol;
  }

  public int hentRad(){
    return rad;
  }

  public void settLab(Labyrint lab){
    laby = lab;
  }

  public void finnNabo(){ // metoden finner naboer- dersom selve rute ikke er på første/siste rad/kolone, har den nabo. ellers er nabo null
    if (kol != 0) vest = laby.labyrint[kol-1][rad];
    if (kol != laby.labyrint.length-1) ost = laby.labyrint[kol+1][rad];
    if (rad != 0) nord = laby.labyrint[kol][rad-1];
    if (rad != laby.labyrint[0].length-1) syd = laby.labyrint[kol][rad+1];
    naboer = new Rute[]{nord, ost, syd, vest};
  }

  public void finnUtvei() {

    String kordinater = String.format("(%d, %d)", kol, rad); //en string som inneholder posisjon til start-rute foreløpig
    Runnable minRun = null;
    CountDownLatch latch = null;
    int tradlaget = 0;
    int antallHvitNabo = 0;
    for(int i = 0; i < naboer.length; i++) {  //looper igjennom naboer til start-rute
      if(naboer[i] != null) { //så lenge nabo ikke er null og nabo er hvit
        if(naboer[i].tilTegn() == '.') {
          antallHvitNabo++;// for å finne hvor mange mulige veiver start-rute har
        }
      }
    }


    if(antallHvitNabo > 1) { //dersom det er minst 2 mulige veier
      latch = new CountDownLatch(antallHvitNabo-1); //barriere på antall tråder som må kjøres ferdig

      for(int i = 0; i < naboer.length; i++) {  //looper igjennom naboer til start-rute
        if(naboer[i] != null) { //så lenge nabo ikke er null og nabo er hvit
          if(naboer[i].tilTegn() == '.') {
            if(tradlaget == (antallHvitNabo-1)) { //kall alltid gaa på hoved vei
              naboer[i].gaa(this, kordinater);
            }
            else {//lag nye tråder på andre mulige veier
              minRun = new Gaar(latch, this, naboer[i], kordinater);
              Thread t = new Thread(minRun);
              t.start();
              tradlaget++;
            }
          }
        }
      }
      try {
        latch.await(); // venter på alle tråder blir kjørt ferdig og gå tilbake til finnUtveiFra()
      } catch (InterruptedException e) {}

    }



    if(antallHvitNabo == 1) { //kall på gaa dersom det er bare en mulig vei
      for(int i = 0; i < naboer.length; i++) {  //looper igjennom naboer til start-rute
        if(naboer[i] != null) { //så lenge nabo ikke er null og nabo er hvit
          if(naboer[i].tilTegn() == '.') {
            naboer[i].gaa(this, kordinater);
          }
        }
      }
    }

  }


  public void gaa(Rute forrige, String kordinater){
    Runnable minRun = null;
    CountDownLatch latch = null;
    kordinater = kordinater + " --> (" + kol + ", " + rad + ")";  //vi setter nabo-rute sin posisjon etter start-rute sin posisjon og lager en vei

    if(this.erAapning()) { // this har blitt nabo-rute fordi det er nabo-rute som kaller på gaa. Dersom nabo-rute er en åpning har vi funnet en utvei allerede her(start-rute til nabo-rute)
      laby.utveier.leggTil(kordinater); //utvei legges i lista

    }
    else { //dersom nabo-rute ikke er en åpning

      int tradlaget = 0;
      int antallHvitNabo = 0;
      for(int i = 0; i < naboer.length; i++) {
        if(naboer[i] != null && naboer[i] != forrige) { //passer på ikke gå på ruten den kommer fra
          if(naboer[i].tilTegn() == '.') {
            antallHvitNabo++;
          }
        }
      }

      if(antallHvitNabo > 1) {
        latch = new CountDownLatch(antallHvitNabo-1);
        for(int i = 0; i < naboer.length; i++) {
          if(naboer[i] != null && naboer[i] != forrige) {
            if(naboer[i].tilTegn() == '.') {
              if(tradlaget == antallHvitNabo-1) {
                naboer[i].gaa(this, kordinater);
              }
              else {
                minRun = new Gaar(latch, this, naboer[i], kordinater);
                Thread t = new Thread(minRun);
                t.start();
                tradlaget++;
              }
            }
          }
        }
        try {
          latch.await();
        } catch (InterruptedException e) {}

      }



      if(antallHvitNabo == 1) {
        for(int i = 0; i < naboer.length; i++) {
          if(naboer[i] != null && naboer[i] != forrige) {
            if(naboer[i].tilTegn() == '.') {
              naboer[i].gaa(this, kordinater);
            }
          }
        }
      }
    }
  }//gaa


}//Rute
