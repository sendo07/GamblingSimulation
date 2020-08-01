package GamblingSimulation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.awt.List;
import java.lang.ProcessBuilder.Redirect;
import java.security.KeyStore.Entry;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class GamblingSim {

  // Declaring Constants
  private static final int STAKE = 100;
  private static final int BET_AMT = 1;
  private static final double STAKE_PERCENT = 0.50;
  private static final int NUM_OF_DAYS_IN_A_MONTH = 30;

  // Declaring Variables
  private static int gamblingMoney = STAKE;
  private static int profitAmt = 0;
  private static int lossAmt = 0;
  private static int dayBetCount = 0;
  private static int flag2;
  private static String flag1;
  private static String maxWinsDay;
  private static String maxLossDay;
  private static String resDayComparison;
  private static LinkedHashMap<String, String> resultsMonth = new LinkedHashMap<>();
  private static LinkedHashMap<Integer, String> winDaysHashMap = new LinkedHashMap<>();
  private static LinkedHashMap<Integer, String> lossDaysHashMap = new LinkedHashMap<>();
  
  public static void main(String[] args) {
    progExecution();
  }

  public static void progExecution() {
    System.out.println("\nWelcome to Gambling Simulation!");
    
    Scanner scan = new Scanner(System.in);

    
    ProgramContinue: while (true) { 
      startGamble();
      monthGamble();
      results();
      System.out.println("\n\n"+comparisonByDays()+"\n");
      System.out.println(resultsMonth);
      System.out.println(winDaysHashMap);
      System.out.println(lossDaysHashMap); 
      System.out.println("\n\nWould like to continue Gambling? Y/N: ");
      String usrIn = scan.nextLine().toUpperCase();
      switch (usrIn) {
        case "Y":
          continue;
        case "N":
          System.out.println("\nThank for Playing!\nVisit again!");
          break ProgramContinue;
        default:
          System.out.println("..............\n\nError: Invalid Option\nTry Again!");
      }  
    }
    scan.close();
  }

  // Method for displaying the start of Gambling
  public static void startGamble() {
    System.out.println("\n\nStarting Stats: \n1. Stake = $"+STAKE+"\n2. Bet Amount = $"+BET_AMT+"\n3. Stake Percent = "+STAKE_PERCENT+"\n\n\nStarting bets...");
  }

  // Method to Display the results
  public static void results() {
    
    System.out.println("\n\n_______________________________________________________________________________________________________");
    System.out.println("\n\nDaywise Results: \n");

    for (String key: resultsMonth.keySet()) {
      System.out.println(key + ": " + resultsMonth.get(key));
    }
    
    System.out.println("\n\n_______________________________________________________________________________________________________");
    System.out.println("\n\nLuckiest & Unluckiest Day: \n");
    System.out.println("\nLuckiest Day: "+ luckyDay());
    System.out.println("\nUnluckiest Day: "+ unluckyDay());
    System.out.println("\n\n_______________________________________________________________________________________________________");
  }

  // Method
  public static String comparisonByDays() {
    if (winDaysHashMap.size() > lossDaysHashMap.size()) {
      //Number of days won is more
      resDayComparison =  "You've won maximum number of days in this Month";
    } else if (lossDaysHashMap.size() > winDaysHashMap.size()) {
      // Number of days lost is more
      resDayComparison =  "You've lost maximum number of days in this Month";
    }
    return resDayComparison;
  }

  // Method to find Luckiest Day
  public static String luckyDay() {
    try {
      maxWinsDay = winDaysHashMap.get(Collections.max(winDaysHashMap.keySet()));
      return maxWinsDay;   
    } catch (NoSuchElementException e) {
      return "No Win days";
    }
    
  }

  // Method to find Unluckiest Day
  public static String unluckyDay() {
    try {
      maxLossDay = lossDaysHashMap.get(Collections.max(lossDaysHashMap.keySet()));
      return maxLossDay;
    } catch (NoSuchElementException e) {
      return "No Loss days";
    }
    
  }

  //Method for Gambling for a Month(30days)
  public static void monthGamble() {
    
    System.out.println("\n\n_______________________________________________________________________________________________________");
    System.out.println("\n\nProfit & Loss Details (Day21 onwards)");

    int i=1;
    while (i<=NUM_OF_DAYS_IN_A_MONTH) {
      ArrayList<Integer> arrTemp = singleDayGamble();

      if (arrTemp.get(2) == 1) {
        
        // Adding win days and number of bets
        resultsMonth.put("Day"+i, "Won" +" by "+ arrTemp.get(0) +" bets");
        winDaysHashMap.put(arrTemp.get(0),"Day"+i);

      } else if (arrTemp.get(2) == 0) {
        
        // Adding loss days and number of bets
        resultsMonth.put("Day"+i, "Lost" +" by "+ arrTemp.get(1) +" bets");
        lossDaysHashMap.put(arrTemp.get(1),"Day"+i);

      }
      
      if (i>20) {
        System.out.println("\nDay "+i+"\nTotal Profit: $"+profitAmt+"\nTotal Loss: $"+lossAmt);
      }
      i++;
    }
  }

  // Method for Gambling per Day
  public static ArrayList<Integer> singleDayGamble() {

    int stakeValue = (int) (STAKE_PERCENT*STAKE);
    int winningStake = (int) (STAKE + stakeValue);
    int losingStake = (int) (STAKE - stakeValue);

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
          lossAmt+=stakeValue;
          flag2 = 0; // Day Lost
        } else if (gamblingMoney == winningStake) {
          profitAmt+=stakeValue;
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