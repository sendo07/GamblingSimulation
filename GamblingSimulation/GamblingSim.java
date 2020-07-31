package GamblingSimulation;

import java.util.Random;

public class GamblingSim {

  //Declaring initial Variables
  private static final int stake = 100;
  private static final int betAmt = 1;
  private static int gamblingMoney = stake;
  private static int profitAmt = 0;
  private static int lossAmt = 0;
  private static int dayBetCount = 0;
  private static int winCounter = 0;
  private static int lossCounter = 0;
  private static double stakePercent = 0.50;

  public static void main(String[] args) {
    monthGamble();
  }

  //Method for Gambling for a Month(30days)
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

  // Method for Gambling per Day
  public static int singleDayGamble() {
    int stakeValue = (int) (stakePercent*stake);
    int winningStake = (int) (stake + stakeValue);
    int losingStake = (int) (stake - stakeValue);
    loop1: while (true) {
      gamble();
      dayBetCount++;
      if (gamblingMoney == losingStake || gamblingMoney == winningStake) {
        if (gamblingMoney == losingStake) {
          lossAmt+=stakeValue;
        } else if (gamblingMoney == winningStake) {
          profitAmt+=stakeValue;
        }
        break loop1;
      }
    }
    return dayBetCount;
  }

  // Method for a Single Gamble
  public static void gamble() {

    Random r1 = new Random();
    boolean betCall = r1.nextBoolean();

    if (betCall == true) {
      // bet won
      winCounter++;
      gamblingMoney += betAmt;
      //System.out.println("Won: " +" "+ gamblingMoney+" " + stake);
    } else if (betCall == false) {
      //bet lost
      lossCounter++;
      gamblingMoney-=betAmt;
      //System.out.println("Lost: " +" "+ gamblingMoney+" " + stake);
    }
  }
}