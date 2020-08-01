package GamblingSimulation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;


public class GamblingSim {

  // Declaring Constants
  private static final int STAKE = 100;
  private static final int BET_AMT = 1;
  private static final double STAKE_PERCENT = 0.50;

  // Declaring Variables
  private static int gamblingMoney = STAKE;
  private static int profitAmt = 0;
  private static int lossAmt = 0;
  private static int dayBetCount = 0;
  private static int flag2;
  private static String flag1;
  private static LinkedHashMap<String,String> resultsMonth = new LinkedHashMap<>();
  
  public static void main(String[] args) {
    welcomeScreen();
    monthGamble();
    results();
  }

  public static void welcomeScreen() {
    System.out.println("\nWelcome to Gambling Simulation!\n\nStarting Stats: \n1. Stake = $"+STAKE+"\n2. Bet Amount = $"+BET_AMT+"\n3. Stake Percent = "+STAKE_PERCENT+"\n\n\nStarting bets...");
  }

  public static void results() {
    
    System.out.println("\n\n__________________________");
    System.out.println("\n\nDaywise Results: \n");

    for (String key: resultsMonth.keySet()) {
      System.out.println(key + ": " + resultsMonth.get(key));
    }
    
  }

  //Method for Gambling for a Month(30days)
  public static void monthGamble() {
    int i=1;
    System.out.println("\n\n__________________________");
    System.out.println("\n\nProfit & Loss Details (Day21 onwards)");
    
    while (i<=30) {
      ArrayList<Integer> arrTemp = singleDayGamble();

      if (arrTemp.get(2) == 1) {
        resultsMonth.put("Day"+i, "Won" +" by "+ arrTemp.get(0) +" bets");
      } else if (arrTemp.get(2) == 0) {
        resultsMonth.put("Day"+i, "Lost" +" by "+ arrTemp.get(1) +" bets");
      }
      
      if (i>20) {
        System.out.println("\nDay "+i+"\nTotal Profit: $"+profitAmt+"\nTotal Loss: $"+lossAmt);
      }
      i++;
    }
  }

  // Method for Gambling per Day
  public static ArrayList<Integer> singleDayGamble() {

    int STAKEValue = (int) (STAKE_PERCENT*STAKE);
    int winningSTAKE = (int) (STAKE + STAKEValue);
    int losingSTAKE = (int) (STAKE - STAKEValue);

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
      if (gamblingMoney == losingSTAKE || gamblingMoney == winningSTAKE) {
        if (gamblingMoney == losingSTAKE) {
          lossAmt+=STAKEValue;
          flag2 = 0; // Day Lost
        } else if (gamblingMoney == winningSTAKE) {
          profitAmt+=STAKEValue;
          flag2 = 1; // Day Won
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
      gamblingMoney += BET_AMT;
    } else if (betCall == false) {
      // bet lost
      flag1 = "Lost";
      gamblingMoney -= BET_AMT;
    }
    return flag1;
  }
}