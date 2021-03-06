
/*
 * Program: Assignment2 - Casino
 * Author: Jason Lloyd
 * Class: CST 338
 * Date: 5/3/2017
*/

import java.util.*;
import java.lang.Math;

public class Assig2 
{
   static Scanner keyboard = new Scanner(System.in);
   
   public static void main(String []args) 
   {
	  int theBet = 0;
	  int multiplier = 0;
	  int totalWinnings = 0;
	  int winnings = 0;
	  
	  do
	  {
	     //Start the game. getBet() get a int from 0 - 100
	     theBet = getBet();
	     if (theBet != 0)
	     {
	        //Player is making a wager
	        //a bet of 0 will skip this stanza
	        
	        //mySpin will now contain the results of the pull
		TripleString mySpin = pull();
		//Find out if mySpin contains a winning combo, return 0 if not
		multiplier = getPayMultiplier(mySpin);
		//Calculate the player's willing for this pull()
		winnings = theBet * multiplier;
		TripleString.saveWinnings(winnings);
		//Show the player what they won
		display(mySpin, winnings);
		//Add to the grand total
		totalWinnings += winnings;
		
		//Increment numPulls for the next wager and pull()
		TripleString.numPulls = TripleString.numPulls + 1;
	     }
	  } 
	  //Continue playing until the wager is 0 or we reach MAX_PULLS
	  while(theBet != 0 && TripleString.numPulls < TripleString.MAX_PULLS);      

	  keyboard.close();
	  
	  //Display the end result to the player
	  System.out.println("Thanks for playing at the Casino!");
	  System.out.println("Your individual winnings were:");
	  System.out.println(TripleString.displayWinnings());
	  System.out.println("Your total winnings were: $" +
	        totalWinnings);
   }

   public static int getBet()
   // getBet() prompts the user for an integer between 0 - 100 
   // getBet() will keep loop until the an integer in the correct range is supplied.
   // Returns: an integer from 0 - 100
   {
      int returnValue = 0;
      boolean inputValid = false;
      
      do
      {
         System.out.print("How much would you like to bet ");
         System.out.print("(1 - 100) or 0 to quit? ");
         
         returnValue = keyboard.nextInt();
         
         if (returnValue >= 0 && returnValue <= 100)
         {
            inputValid = true;
         }
      } 
      while (!inputValid);
      
      return returnValue;
   }
   
   public static TripleString pull()
   //pull() creates three string values using randString
   // The 3 string values are added to a new TripleString object
   // The new TripleString object is returned
   {
      TripleString returnValue = new TripleString();
      
      String string1 = randString();
      String string2 = randString();
      String string3 = randString();
      
      returnValue.setString1(string1);
      returnValue.setString2(string2);
      returnValue.setString3(string3);
      
      return returnValue;        
   }
   
   public static void display(TripleString thePull, int winnings)
   //display() outputs the TripleString and whether or not the player won that spin
   //If winnings > 0, a congrats message is displayed
   //The TripleString method toString() is used here
   {
      System.out.println("whirrrrr .... and your pull is ...");
      System.out.println(" " + thePull.toString());
      
      if (winnings > 0)
      {
         System.out.println("congratulations, you win: " + winnings);
      }
      else
      {
         System.out.println("sorry, you lose.");
      }
   }
   
   private static String randString()
   //randString() uses Math.random() to pick what value to return
   {
      String returnValue = "";
      //random becomes a number between 1 - 1000
      int random = (int)(Math.random() * 1000);
      
      //50% chance for BAR
      if (random <= 500)
      {
         returnValue = "BAR";
      } 
      //25% chance for "cherries"
      else if (random <= 750)
      {
         returnValue = "cherries";
      } 
      //12.5% chance for "space"
      else if (random <= 875)
      {
         returnValue = "space";
      } 
      //12.5% chance for "7"
      else 
      {
         returnValue ="7";
      }
      
      return returnValue;
   }
   
   public static int getPayMultiplier(TripleString thePull)
   //getPayMultiplier looks at the strings inside TripleString to determine a multiplier
   {
      // Assume a loss, so multiplier = 0
      int multiplier = 0;
      
      //Checking for cherries, any, any
      if (thePull.getString1().equals("cherries"))
      {
         multiplier = 5;
         //Now checking for cherries, cherries, any
         if (thePull.getString2().equals("cherries"))
         {
            multiplier = 15;
            //Finally checking for cherries, cherries, cherries
            if (thePull.getString3().equals("cherries"))
            {
               multiplier = 30;
            }
         }
      } 
      // only BAR BAR BAR is a winner, so use short circuiting AND statements
      else if (thePull.getString1().equals("BAR") && 
            thePull.getString2().equals("BAR") && thePull.getString3().equals("BAR"))
      {
         multiplier = 50;
      }
      // only 7 7 7 is a winner, so use short circuiting AND statements
      else if (thePull.getString1().equals("7") &&
            thePull.getString2().equals("7") && thePull.getString3().equals("7"))
      {
         multiplier = 100;
      }
      // If no match is found, multiplier will still be 0
      return multiplier;
   }
}

class TripleString 
{
   public static final int MAX_PULLS = 40;
   public static int numPulls = 0;
   
   private static final int MAX_LEN = 20;   
   private static int[] pullWinnings = new int[MAX_PULLS];
   private String string1, string2, string3;
   
   public TripleString() 
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }

   public boolean setString1(String str) 
   {
      if (validString(str)) 
      {
         string1 = str;
         return true;
      } 
      else 
      {
         return false;
      }
   }

   public boolean setString2(String str) 
   {
      if (validString(str)) 
      {
         string2 = str;
         return true;
      } 
      else 
      {
         return false;
      }
   }

   public boolean setString3(String str) 
   {
      if (validString(str)) 
      {
         string3 = str;
         return true;
      } 
      else 
      {
         return false;
      }
   }

   public String getString1() 
   {
      return string1;
   }

   public String getString2() 
   {
      return string2;
   }

   public String getString3() 
   {
      return string3;
   }
   
   public String toString() 
   {
      String result = string1 + " " + string2 + " " + string3;
      return result;
   }

   public static boolean saveWinnings(int winnings) 
   {
      if (winnings >= 0) 
      {
         pullWinnings[numPulls] = winnings;
         return true;
      } 
      else 
      {
         return false;
      }
   }

   public static String displayWinnings() 
   {
      String returnString = "";

      for (int i = 0; i < numPulls; i++) 
      {
         returnString += pullWinnings[i];
         returnString += " ";
      }
      return returnString;
   }

   private boolean validString(String str) 
   {
      if (str.length() > 0 && str.length() <= MAX_LEN) 
      {
         return true;
      } 
      else 
      {
         return false;
      }
   }
}
