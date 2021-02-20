class Test{
  public static void main(String[] args){
    Liste<Integer> liste = new Lenkeliste<Integer>();
    liste.leggTil(0);
    liste.leggTil(1);
    liste.leggTil(2);

    System.out.println(liste.stoerrelse());

  }
}
