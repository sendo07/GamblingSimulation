package GamblingSimulation;

import java.util.Random;

public class GamblingSim {

  //Declaring initial Variables
  private static final int moneyAtStart=100;
  private static final int betAmt=1;
  private static int gamblingMoney = moneyAtStart;

  public static void main(String[] args) {
    gambler();
  }

  public static void gambler() {
    loop1: while (true) {
      gamble();
      if (gamblingMoney == 50 || gamblingMoney == 150) {
        break loop1;
      }
    }
  }

  // Function for a Single Gamble
  public static void gamble() {

    Random r1 = new Random();
    boolean betCall = r1.nextBoolean();

    if (betCall == true) {
      // bet won
      gamblingMoney += betAmt;
      System.out.println("Won: " +" "+ gamblingMoney+" " + moneyAtStart);
    } else if (betCall ==false) {
      //bet lost
      gamblingMoney-=betAmt;
      System.out.println("Lost: " +" "+ gamblingMoney+" " + moneyAtStart);
    }
  }
}