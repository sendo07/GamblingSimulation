package GamblingSimulation;

import java.util.Random;

public class GamblingSim {

  //Declaring initial Variables
  private static final int moneyAtStart=100;
  private static final int betAmt=1;
  private static int gamblingMoney = moneyAtStart;
  private static int dayBetCount=0;
  private static int profitAmt=0;
  private static int lossAmt=0;


  public static void main(String[] args) {
    monthGamble();
  }

  public static void monthGamble() {
    int i=1;
    loop2: while (i<=30) {
      singleDayGamble();
      if (i==20) {
        System.out.println("Total Profit in 20 days: "+profitAmt);
        System.out.println("Total loss in 20 days: "+lossAmt);
        break loop2;
      }
      i++;
    }
  }


  public static int singleDayGamble() {
    loop1: while (true) {
      gamble();
      dayBetCount++;
      if (gamblingMoney == 50 || gamblingMoney == 150) {
        break loop1;
      }
    }
    return dayBetCount;
  }

  // Function for a Single Gamble
  public static void gamble() {

    Random r1 = new Random();
    boolean betCall = r1.nextBoolean();

    if (betCall == true) {
      // bet won
      profitAmt++;
      gamblingMoney += betAmt;
      System.out.println("Won: " +" "+ gamblingMoney+" " + moneyAtStart);
    } else if (betCall ==false) {
      //bet lost
      lossAmt++;
      gamblingMoney-=betAmt;
      System.out.println("Lost: " +" "+ gamblingMoney+" " + moneyAtStart);
    }
  }
}