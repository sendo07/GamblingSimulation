package GamblingSimulation;

import java.util.Random;

public class GamblingSim {

  //Declaring initial Variables
  private static final int stake = 100;
  private static final int betAmt = 1;
  private static int gamblingMoney = stake;
  private static int dayBetCount = 0;
  private static int profitAmt = 0;
  private static int lossAmt = 0;

  public static void main(String[] args) {
    monthGamble();
  }

  public static void monthGamble() {
    int i=1;
    while (i<=30) {
      singleDayGamble();
      if (i>20) {
        System.out.println("\nDay "+i+"\nTotal Profit: $"+profitAmt+"\nTotal Loss: $"+lossAmt);
      }
      i++;
    }
  }


  public static int singleDayGamble() {
    int winningStake = (int) (stake + (.5*stake));
    int losingStake = (int) (stake - (.5*stake));
    loop1: while (true) {
      gamble();
      dayBetCount++;
      if (gamblingMoney == losingStake || gamblingMoney == winningStake) {
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
      //System.out.println("Won: " +" "+ gamblingMoney+" " + stake);
    } else if (betCall ==false) {
      //bet lost
      lossAmt++;
      gamblingMoney-=betAmt;
      //System.out.println("Lost: " +" "+ gamblingMoney+" " + stake);
    }
  }
}