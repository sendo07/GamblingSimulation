package GamblingSimulation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;


public class GamblingSim {

  //Declaring initial Variables
  private static final int Stake = 100;
  private static final int BetAmt = 1;
  private static int gamblingMoney = Stake;
  private static int profitAmt = 0;
  private static int lossAmt = 0;
  private static int dayBetCount = 0;
  private static double StakePercent = 0.50;
  private static int flag2;
  private static String flag1;
  private static LinkedHashMap<String,String> resultsMonth = new LinkedHashMap<>();
  
  public static void main(String[] args) {
    welcomeScreen();
    monthGamble();
    results();
  }

  public static void welcomeScreen() {
    System.out.println("\nWelcome to Gambling Simulation!\nStarting bets...");
  }

  public static void results() {
    
    System.out.println("\n\n========================");
    System.out.println("\n\nDaywise Results: \n");

    for (String key: resultsMonth.keySet()) {
      System.out.println(key + ": " + resultsMonth.get(key));
    }
    
  }

  //Method for Gambling for a Month(30days)
  public static void monthGamble() {
    int i=1;
    System.out.println("\n\n========================");
    System.out.println("\n\nProfit & Loss Details (Day21 onwards)");
    
    while (i<=30) {
      ArrayList<Integer> x = singleDayGamble();

      if (x.get(2) == 1) {
        resultsMonth.put("Day"+i, "Won" +" by "+ x.get(0) +" bets");
      } else if (x.get(2) == 0) {
        resultsMonth.put("Day"+i, "Lost" +" by "+ x.get(1) +" bets");
      }
      
      if (i>20) {
        System.out.println("\nDay "+i+"\nTotal Profit: $"+profitAmt+"\nTotal Loss: $"+lossAmt);
      }
      i++;
    }
  }

  // Method for Gambling per Day
  public static ArrayList<Integer> singleDayGamble() {

    int StakeValue = (int) (StakePercent*Stake);
    int winningStake = (int) (Stake + StakeValue);
    int losingStake = (int) (Stake - StakeValue);

    int winCounter = 0;
    int lossCounter = 0;
    ArrayList<Integer> arrDay = new ArrayList<>();
    
    loop1: while (true) {
      String y= gamble();
      dayBetCount++;
      if ( y == "Won") {
        winCounter++;
      }else if ( y == "Lost") {
        lossCounter++;
      }
      if (gamblingMoney == losingStake || gamblingMoney == winningStake) {
        if (gamblingMoney == losingStake) {
          lossAmt+=StakeValue;
          flag2 = 0; // Day Lost
        } else if (gamblingMoney == winningStake) {
          profitAmt+=StakeValue;
          flag2 = 1; //Day Won
        }
        break loop1;
      }
    }
    arrDay.add(0, winCounter);
    arrDay.add(1, lossCounter);
    arrDay.add(2, flag2);
    return arrDay;
  }

  // Method for a Single Gamble
  public static String gamble() {
    
    Random r1 = new Random();
    boolean betCall = r1.nextBoolean();

    if (betCall == true) {
      // bet won
      flag1 = "Won";
      gamblingMoney += BetAmt;
    } else if (betCall == false) {
      // bet lost
      flag1 = "Lost";
      gamblingMoney -= BetAmt;
    }
    return flag1;
  }
}