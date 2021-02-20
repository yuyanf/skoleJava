//lagde ny klasse Gaar for å kunne kalle gaa() i en ny tråd

import java.util.concurrent.*;
public class Gaar implements Runnable {

  Rute r = null;
  Rute nabo = null;
  String kordinater = "";
  CountDownLatch latch = null;

  public Gaar(CountDownLatch latch, Rute r, Rute nabo, String kordinater) {
    this.r = r;
    this.nabo = nabo;
    this.kordinater = kordinater;
    this.latch = latch;
  }

  public void run() {
    nabo.gaa(r, kordinater);
    latch.countDown();
  }

}
