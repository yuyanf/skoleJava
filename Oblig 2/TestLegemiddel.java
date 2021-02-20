class TestLegemiddel {


  public static void main(String[] args) {
    Vanlig legemiddel1 = new Vanlig("Paracet", 100, 500.2);

    Narkotisk legemiddel2 = new Narkotisk("Heroin", 1589.45, 2.2, 89);

    Vanedannende legemiddel3 = new Vanedannende("Sovepiller", 589.45, 5.2, 49);

    Vanedannende legemiddel4 = new Vanedannende("ExtremeSht", 234, 53, 200);
    System.out.println(legemiddel1);
    System.out.println(legemiddel2);
    System.out.println(legemiddel3);
    System.out.println(legemiddel4);





  }
}
