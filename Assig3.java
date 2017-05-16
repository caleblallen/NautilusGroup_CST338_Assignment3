import java.util.Random;
import java.util.Scanner;
/* ---------------------------------------------------------------------------------------------------------------- 
Nautilus Group
Caleb Allen
Daisy Mayorga
David Harrison
Dustin Whittington
Michael Cline
CST 338
M3: Deck of Cards Java Program
16 May 2017

PURPOSE:
We endeavor to set up some classes that can be used in future programs that involve playing card games with a human,
or simulating card games entirely by a computer.  This includes: class Card, class Hand, and class Deck.

OVERVIEW:

----------------------------------------------------------------------------------------------------------------- */

public class Assig3
{
   public static void main(String[] args) {
      int players;
      Scanner input = new Scanner(System.in);
      System.out.println("How many hands? (1 - 10, please)");
      players = input.nextInt();
      
      //Make sure user has entered a legal number of players
      while (players < 1 || players > 10)
      {
         System.out.println("Please enter a legal value of players between 1 and 10");
         players = input.nextInt();
      }
      
      Deck deck = new Deck(1);
      Hand[] hands = new Hand[players];
      
      // Initialize a hand object for each player
      for (int x = 0; x < players; x++)
      {
         hands[x] = new Hand();
      }
      
      //Deal all cards to players
      while (deck.getTopCard() > 0)
      {
         for (Hand hand : hands)
         {
            //make sure there is still a card to deal
            if (deck.getTopCard() > 0)
            {
               hand.takeCard(deck.dealCard());
            }
         }
      }
      
      //Display all hands
      System.out.println("Here are our hands, from unshuffled deck:");
      for(Hand hand : hands)
      {
         System.out.println(hand);
      }
      
      //Reset all hands
      for (Hand hand : hands)
      {
         hand.resetHand();
      }
      
      //put 52 cards back into deck object
      deck.init(1);
      
      //shuffle deck
      deck.shuffle();
      
      //Deal all cards to players
      while (deck.getTopCard() > 0)
      {
         for (Hand hand : hands)
         {
            //make sure there is still a card to deal
            if (deck.getTopCard() > 0)
            {
               hand.takeCard(deck.dealCard());
            }
         }
      }
      
      //Display all hands
      System.out.println("Here are our hands, from SHUFFLED deck:");
      for(Hand hand : hands)
      {
         System.out.println(hand);
      }
      
      testCardClass();
      testHandClass();
   }

   public static void testCardClass()
   {
      String results = "";
      Card cardClassCard1 = new Card('A', Card.Suit.spades);
      Card cardClassCard2 = new Card('f', Card.Suit.hearts);
      Card cardClassCard3 = new Card('J', Card.Suit.clubs);

      results += expectedResult(cardClassCard1.toString(), "A of spades", "Card 1 (ace of spades)");
      results += expectedResult(cardClassCard2.toString(), "[ invalid ]", "Card 2 (invalid)");
      results += expectedResult(cardClassCard3.toString(), "J of clubs", "Card 3 (jack of spades)");
      // System.out.println(cardClassCard1.toString());
      // System.out.println(cardClassCard2.toString());
      // System.out.println(cardClassCard3.toString());
      // System.out.println("");

      cardClassCard1.set('f', Card.Suit.clubs);
      cardClassCard2.set('Q', Card.Suit.spades);

      results += expectedResult(cardClassCard1.getErrorFlag(), true, "Set card 1 to invalid");
      results += expectedResult(cardClassCard2.getErrorFlag(), false, "Set card 2 to valid");
      results += expectedResult(cardClassCard1.toString(), "[ invalid ]", "Card 1 (invalid)");
      results += expectedResult(cardClassCard2.toString(), "Q of spades", "Card 2 (queen of spades)");
      // System.out.println(cardClassCard1.toString());
      // System.out.println(cardClassCard2.toString());
      // System.out.println(cardClassCard3.toString());
      if (results.indexOf("false") < 0)
      {
         System.out.println("\n------------\nThe Card class has passed all tests!\n------------\n");
      }
      else
      {
         System.out.println("\n------------\nFAILED! The Card class has failed one or more tests!\n------------\n");
      }
   }

   // Driver function for Hand Class tests.
   public static void testHandClass()
   {
      String results = "";
      Hand pokerHand = new Hand();
      pokerHand.takeCard(new Card('A', Card.Suit.spades));
      results += expectedResult(pokerHand.inspectCard(0).toString(), "A of spades", "Add and Inspect First Card");
      pokerHand.takeCard(new Card('Q', Card.Suit.hearts));
      results += expectedResult(pokerHand.inspectCard(1).toString(), "Q of hearts", "Add and Inspect Second Card");
      pokerHand.takeCard(new Card('J', Card.Suit.diamonds));
      results += expectedResult(pokerHand.inspectCard(2).toString(), "J of diamonds", "Add and Inspect Third Card");
      results += expectedResult(pokerHand.toString(), "( A of spades, Q of hearts, J of diamonds )",
         "Hand.toString()");
      results += expectedResult(pokerHand.playCard().toString(), "J of diamonds", "playCard() return value");
      results += expectedResult(pokerHand.toString(), "( A of spades, Q of hearts )",
         "The test to see if a card is removed by Hand.playCard()");
      results += expectedResult(pokerHand.getNumCards(), 2,
         "The test to see if Hand.numCards is decremented properly with Hand.playCard()");
      pokerHand.resetHand();
      results += expectedResult(pokerHand.inspectCard(pokerHand.MAX_CARDS).getErrorFlag(), true,
         "inspectCard on empty hand return");
      results += expectedResult(pokerHand.toString(), "(  )", "Hand.resetHand()");

      String expectedToString = "( ";
      for (int i = 0; i <= pokerHand.MAX_CARDS; i++)
      {
         /*
          * Create between two and five explicit Card objects and one Hand
          * object. Use takeCard() on these few cards (resulting in many,
          * unavoidable "duplicates" in the hand) in a loop to populate the
          * hand until the maximum allowable cards is met (use this criterion
          * to end the loop).
          */
         Card[] testCards = { new Card('A', Card.Suit.spades), new Card('Q', Card.Suit.hearts),
               new Card('J', Card.Suit.diamonds), new Card('T', Card.Suit.clubs) };
         int roll = (int) (Math.random() * testCards.length);
         boolean cardWasAdded = pokerHand.takeCard(testCards[roll]);
         if (i < pokerHand.MAX_CARDS)
         {
            expectedToString += testCards[roll].toString();
            if (i + 1 < pokerHand.MAX_CARDS)
            {
               expectedToString += ", ";
            }
            //At some point in your program, test inspectCard() with both
            //Legal and illegal int arguments.
            results += expectedResult(pokerHand.inspectCard(i).getValue(), testCards[roll].getValue(),
               "inspect card (" + pokerHand.inspectCard(i).getValue() + ")");
         }
         else
         {
            results += expectedResult(cardWasAdded, false,
               "Test to see if Hand cuts off after numCards exceeds MAX_CARDS");

            expectedToString += " )";
            // At some point in your program, test inspectCard() with both
            // legal and illegal int arguments.
            results += expectedResult(pokerHand.inspectCard(i).getErrorFlag(), true, "inspect card invalid");
         }
      }
      results += expectedResult(pokerHand.getNumCards(), pokerHand.MAX_CARDS, "Completely filled hand");
      results += expectedResult(pokerHand.toString(), expectedToString,
         "Hand.toString when attempting to added more than MAX_CARDS to hand");
      // Display the hand using toString()
      System.out.println(pokerHand);
      // Next, play each card in a loop, until the hand is empty.
      for (int i = 0; i < pokerHand.MAX_CARDS; i++)
      {
         // Display the card played as it is played,
         System.out.println(pokerHand.playCard());
      }
      // and finally, display the (now empty) hand
      System.out.println(pokerHand);
      results += expectedResult(pokerHand.toString(), "(  )", "empty hand .toString() after play");
      if (results.indexOf("false") < 0)
      {
         System.out.println("\n------------\nThe Hand class has passed all tests!\n------------\n");
      }
      else
      {
         System.out.println("\n------------\nFAILED! The Hand class has failed one or more tests!\n------------\n");
      }
   }

   public static boolean expectedResult(boolean result, boolean expectation, String functionName)
   {
      try
      {
         if (result == expectation)
         {
            System.out.println(functionName + " has passed.");
            return true;
         }
         else
         {
            System.out.println(
               functionName + " has failed! Expected " + expectation + " and received " + result + ".");
            return false;
         }
      }
      catch (Exception e)
      {
         System.out.println(functionName + " has crashed!\n" + e.getMessage());
         return false;
      }
   }

   public static boolean expectedResult(String result, String expectation, String functionName)
   {
      try
      {
         if (result.equals(expectation))
         {
            System.out.println(functionName + " has passed.");
            return true;
         }
         else
         {
            System.out.println(
               functionName + " has failed! Expected " + expectation + " and received " + result + ".");
            return false;
         }
      }
      catch (Exception e)
      {
         System.out.println(functionName + " has crashed!\n" + e.getMessage());
         return false;
      }
   }

   public static boolean expectedResult(int result, int expectation, String functionName)
   {
      try
      {
         if (result == expectation)
         {
            System.out.println(functionName + " has passed.");
            return true;
         }
         else
         {
            System.out.println(
               functionName + " has failed! Expected " + expectation + " and received " + result + ".");
            return false;
         }
      }
      catch (Exception e)
      {
         System.out.println(functionName + " has crashed!\n" + e.getMessage());
         return false;
      }
   }

   public static boolean expectedResult(char result, char expectation, String functionName)
   {
      try
      {
         if (result == expectation)
         {
            System.out.println(functionName + " has passed.");
            return true;
         }
         else
         {
            System.out.println(
               functionName + " has failed! Expected " + expectation + " and received " + result + ".");
            return false;
         }
      }
      catch (Exception e)
      {
         System.out.println(functionName + " has crashed!\n" + e.getMessage());
         return false;
      }
   }
}

class Card
{
   // Enumerator for the card's suit
   public enum Suit
   {
      clubs, diamonds, hearts, spades
   };

   private char value;
   private Suit suit;
   private boolean errorFlag;

   // Default constructor for card overloaded to return A of spades
   public Card()
   {
      this.value = 'A';
      this.suit = Suit.spades;
   }

   // Parameterized constructor for card that accepts value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   // Parameterized constructor for card that accepts value, suit, and
   // errorFlag
   public Card(char value, Suit suit, boolean errorFlag)
   {
      set(value, suit);
      this.errorFlag = errorFlag;
   }

   // If error flag is false, returns value and suit of card in a single
   // string, otherwise returns invalid
   public String toString()
   {
      if (errorFlag == false)
      {
         return this.value + " of " + this.suit;
      }
      else
      {
         return "[ invalid ]";
      }
   }

   // Mutator to set value and suit for a card
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit))
      {
         this.value = value;
         this.suit = suit;
         this.errorFlag = false;
         return true;
      }
      else
      {
         this.errorFlag = true;
         return false;
      }
   }

   public Suit getSuit()
   {
      return this.suit;
   }

   public char getValue()
   {
      /*
       * Accessor for Value
       */
      return this.value;
   }

   public boolean getErrorFlag()
   {
      /*
       * Accessor for errorFlag
       */
      return this.errorFlag;
   }

   private boolean isValid(char value, Suit suit)
   {
      /*
       * a private helper method that returns true or false, depending on the
       * legality of the parameters. Note that, although it may be impossible
       * for suit to be illegal (due to its enum-ness), we pass it, anyway, in
       * anticipation of possible changes to the type from enum to, say, char
       * or int, someday. We only need to test value, at this time.
       */
      char[] cardType = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
      for (int i = 0; i < cardType.length; i++)
         if (value == cardType[i])
            return true;

      return false;
   }

   public boolean equals(Card card)
   {
      if (card == this)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}

class Hand
{
   public final int MAX_CARDS = 50;

   private Card[] myCards;
   private int numCards;

   public Hand()
   {
      /*
       * Default Constructor;
       */
      this.numCards = 0;
      this.myCards = new Card[MAX_CARDS];
   }

   public void resetHand()
   {
      /*
       * remove all cards from the hand (in the simplest way).
       */

      // Remove all reference to the old hand.
      this.myCards = new Card[MAX_CARDS];

      // Reset the card counter.
      this.numCards = 0;
   }

   public boolean takeCard(Card card)
   {
      /*
       * adds a card to the next available position in the myCards array. This
       * is an object copy, not a reference copy, since the source of the Card
       * might destroy or change its data after our Hand gets it -- we want
       * our local data to be exactly as it was when we received it.
       */

      // Make sure we're not above our hand size limit.
      if (numCards < MAX_CARDS)
      {
         // Create a copy of the taken card and advance the card counter.
         myCards[numCards++] = new Card(card.getValue(), card.getSuit());

         return true;
      }
      else
      {
         return false;
      }
   }

   public Card playCard()
   {
      /*
       * returns and removes the card in the top occupied position of the
       * array.
       */
      if (numCards > 0)
      {
         // Make a copy of the card in the myCards array.
         Card playedCard = new Card(myCards[numCards - 1].getValue(), myCards[numCards - 1].getSuit());

         // Decrement card counter. Remove the topmost card from the array.
         myCards[--numCards] = null;

         return playedCard;
      }
      else
      {
         // returns an invalid card to be consistent with inspectCard()
         return new Card('#', Card.Suit.clubs);
      }
   }

   public String toString()
   {
      /*
       * a stringizer that the client can use prior to displaying the entire
       * hand.
       */
      String output = "( ";
      for (int i = 0; i < numCards; i++)
      {
         // print the card.
         output += this.myCards[i].toString();

         // add a comma to every card except the last.
         if (i + 1 < numCards)
         {
            output += ", ";
         }
      }
      return output + " )";
   }

   public int getNumCards()
   {
      /*
       * Accessor for numCards
       */

      return this.numCards;
   }

   public Card inspectCard(int k)
   {
      /*
       * Accessor for an individual card. Returns a card with errorFlag = true
       * if k is bad.
       * 
       * Valid k: 0 <= k < numCards
       */

      // Returns card if k is valid
      if (0 <= k && k < numCards)
      {
         return myCards[k];
      }
      else
      {
         // Returns invalid card if k is bad
         return new Card('#', Card.Suit.clubs);
      }
   }
}

class Deck
{
   // Sets maximum number of cards to be played which is 6 decks (6 * 52 = 312)
   public final static int MAX_CARDS = 312;

   // This is a private static Card array containing exactly 52 card
   // references, which point to all the standard cards
   // Avoids repeatedly declaring the same 52 cards as game play continues
   private static Card[] masterPack;

   /*
    * Changes to true as soon as masterPack is created for the first time.
    * Future deck objects will not re-create master pack once set to true.
    */
   private static boolean masterPackCreated = false;

   private Card[] cards;
   private int topCard;
   private int numPacks = 1;

   public Deck(int numPacks)
   {
      // Over loaded deck method so a user can request the number of decks to
      // use.
      init(numPacks);
   }

   public Deck()
   {
      // Calls init to build a deck
      numPacks = 1;
      init(numPacks);
   }

   public void init(int numPacks)
   {
      // Ensures the deck is not more than the maximum size.
      if (numPacks > 6)
      {
         numPacks = 6;
      }
      // Initializes the pointer and cards array based on the requested number
      // of packs.
      topCard = numPacks * 52;
      cards = new Card[topCard];
      // Calls allocateMasterPack to make sure the master pack has been
      // created.
      allocateMasterPack();
      // Uses arraycopy to copy the number of requested packs from masterPack
      // into cards.
      for (int count = 0; numPacks > count; count++)
      {
         System.arraycopy(masterPack, 0, cards, 52 * count, 52);
      }
   }

   public void shuffle()
   {
      int randomNumber;
      Card copy;
      Random generator = new Random();
      // Applies the Fisher-Yates shuffle algorithm to the cards array.
      for (int deckCount = topCard - 1; deckCount > 0; deckCount--)
      {
         randomNumber = generator.nextInt(deckCount + 1);
         copy = cards[randomNumber];
         cards[randomNumber] = cards[deckCount];
         cards[deckCount] = copy;
      }
   }

   public Card dealCard()
   {
      // Removes the top card from the deck before reducing top card.
      Card card = cards[topCard - 1];
      cards[topCard - 1] = null;
      topCard--;
      // The top card is returned
      return card;
   }

   // Accessor to return array index of top card
   // This value also tells us how many total cards are currently in the array
   public int getTopCard()
   {
      return this.topCard;
   }

   // Method to test that the index of the card is legal
   public Card inspectCard(int k)
   {
      if (k >= 0 && k <= topCard)
      {
         return cards[k];
      }
      else
      {
         return new Card('Q', Card.Suit.hearts, true);
      }
   }

   private static void allocateMasterPack()
   {
      // Check if master pack has already been created by previous deck
      // objects
      if (!masterPackCreated)
      {
         // initialize card array
         masterPack = new Card[52];
         // Create all 52 Card objects for master pack
         masterPack[0] = new Card('A', Card.Suit.clubs);
         masterPack[1] = new Card('A', Card.Suit.diamonds);
         masterPack[2] = new Card('A', Card.Suit.hearts);
         masterPack[3] = new Card('A', Card.Suit.spades);
         masterPack[4] = new Card('2', Card.Suit.clubs);
         masterPack[5] = new Card('2', Card.Suit.diamonds);
         masterPack[6] = new Card('2', Card.Suit.hearts);
         masterPack[7] = new Card('2', Card.Suit.spades);
         masterPack[8] = new Card('3', Card.Suit.clubs);
         masterPack[9] = new Card('3', Card.Suit.diamonds);
         masterPack[10] = new Card('3', Card.Suit.hearts);
         masterPack[11] = new Card('3', Card.Suit.spades);
         masterPack[12] = new Card('4', Card.Suit.clubs);
         masterPack[13] = new Card('4', Card.Suit.diamonds);
         masterPack[14] = new Card('4', Card.Suit.hearts);
         masterPack[15] = new Card('4', Card.Suit.spades);
         masterPack[16] = new Card('5', Card.Suit.clubs);
         masterPack[17] = new Card('5', Card.Suit.diamonds);
         masterPack[18] = new Card('5', Card.Suit.hearts);
         masterPack[19] = new Card('5', Card.Suit.spades);
         masterPack[20] = new Card('6', Card.Suit.clubs);
         masterPack[21] = new Card('6', Card.Suit.diamonds);
         masterPack[22] = new Card('6', Card.Suit.hearts);
         masterPack[23] = new Card('6', Card.Suit.spades);
         masterPack[24] = new Card('7', Card.Suit.clubs);
         masterPack[25] = new Card('7', Card.Suit.diamonds);
         masterPack[26] = new Card('7', Card.Suit.hearts);
         masterPack[27] = new Card('7', Card.Suit.spades);
         masterPack[28] = new Card('8', Card.Suit.clubs);
         masterPack[29] = new Card('8', Card.Suit.diamonds);
         masterPack[30] = new Card('8', Card.Suit.hearts);
         masterPack[31] = new Card('8', Card.Suit.spades);
         masterPack[32] = new Card('9', Card.Suit.clubs);
         masterPack[33] = new Card('9', Card.Suit.diamonds);
         masterPack[34] = new Card('9', Card.Suit.hearts);
         masterPack[35] = new Card('9', Card.Suit.spades);
         masterPack[36] = new Card('T', Card.Suit.clubs);
         masterPack[37] = new Card('T', Card.Suit.diamonds);
         masterPack[38] = new Card('T', Card.Suit.hearts);
         masterPack[39] = new Card('T', Card.Suit.spades);
         masterPack[40] = new Card('J', Card.Suit.clubs);
         masterPack[41] = new Card('J', Card.Suit.diamonds);
         masterPack[42] = new Card('J', Card.Suit.hearts);
         masterPack[43] = new Card('J', Card.Suit.spades);
         masterPack[44] = new Card('Q', Card.Suit.clubs);
         masterPack[45] = new Card('Q', Card.Suit.diamonds);
         masterPack[46] = new Card('Q', Card.Suit.hearts);
         masterPack[47] = new Card('Q', Card.Suit.spades);
         masterPack[48] = new Card('K', Card.Suit.clubs);
         masterPack[49] = new Card('K', Card.Suit.diamonds);
         masterPack[50] = new Card('K', Card.Suit.hearts);
         masterPack[51] = new Card('K', Card.Suit.spades);
         /*
          * Set masterPackCreated to true now that master pack has been
          * created once
          */
         masterPackCreated = true;
      }
   }
}

/***************************OUTPUT 1*****************************************
How many hands? (1 - 10, please)
5
Here are our hands, from unshuffled deck:
( K of spades, Q of hearts, J of diamonds, T of clubs, 8 of spades, 7 of hearts, 6 of diamonds, 5 of clubs, 3 of spades, 2 of hearts, A of diamonds )
( K of hearts, Q of diamonds, J of clubs, 9 of spades, 8 of hearts, 7 of diamonds, 6 of clubs, 4 of spades, 3 of hearts, 2 of diamonds, A of clubs )
( K of diamonds, Q of clubs, T of spades, 9 of hearts, 8 of diamonds, 7 of clubs, 5 of spades, 4 of hearts, 3 of diamonds, 2 of clubs )
( K of clubs, J of spades, T of hearts, 9 of diamonds, 8 of clubs, 6 of spades, 5 of hearts, 4 of diamonds, 3 of clubs, A of spades )
( Q of spades, J of hearts, T of diamonds, 9 of clubs, 7 of spades, 6 of hearts, 5 of diamonds, 4 of clubs, 2 of spades, A of hearts )
Here are our hands, from SHUFFLED deck:
( 2 of diamonds, 5 of hearts, 2 of hearts, 4 of spades, 5 of clubs, 8 of clubs, Q of spades, 7 of hearts, 7 of spades, J of clubs, 5 of diamonds )
( 2 of spades, T of diamonds, K of diamonds, 8 of diamonds, K of spades, 8 of spades, T of spades, 2 of clubs, A of diamonds, T of clubs, Q of clubs )
( J of hearts, Q of hearts, 4 of hearts, 5 of spades, 4 of diamonds, K of hearts, Q of diamonds, 7 of diamonds, 6 of clubs, J of spades )
( 9 of spades, 6 of spades, 3 of hearts, 6 of diamonds, J of diamonds, 3 of clubs, 6 of hearts, 9 of hearts, 8 of hearts, A of clubs )
( T of hearts, 3 of diamonds, 9 of clubs, 7 of clubs, 9 of diamonds, 3 of spades, A of hearts, K of clubs, 4 of clubs, A of spades )
****************************************************************************/


/***************************OUTPUT 2*****************************************
How many hands? (1 - 10, please)
0
Please enter a legal value of players between 1 and 10
20
Please enter a legal value of players between 1 and 10
8
Here are our hands, from unshuffled deck:
( K of spades, J of spades, 9 of spades, 7 of spades, 5 of spades, 3 of spades, A of spades )
( K of hearts, J of hearts, 9 of hearts, 7 of hearts, 5 of hearts, 3 of hearts, A of hearts )
( K of diamonds, J of diamonds, 9 of diamonds, 7 of diamonds, 5 of diamonds, 3 of diamonds, A of diamonds )
( K of clubs, J of clubs, 9 of clubs, 7 of clubs, 5 of clubs, 3 of clubs, A of clubs )
( Q of spades, T of spades, 8 of spades, 6 of spades, 4 of spades, 2 of spades )
( Q of hearts, T of hearts, 8 of hearts, 6 of hearts, 4 of hearts, 2 of hearts )
( Q of diamonds, T of diamonds, 8 of diamonds, 6 of diamonds, 4 of diamonds, 2 of diamonds )
( Q of clubs, T of clubs, 8 of clubs, 6 of clubs, 4 of clubs, 2 of clubs )
Here are our hands, from SHUFFLED deck:
( J of diamonds, A of clubs, 3 of spades, 3 of diamonds, 2 of diamonds, K of spades, 8 of spades )
( 4 of diamonds, 9 of hearts, 5 of spades, 7 of spades, T of hearts, 6 of spades, Q of spades )
( 2 of hearts, J of spades, K of hearts, 9 of diamonds, J of hearts, T of clubs, 9 of clubs )
( 3 of hearts, 7 of hearts, J of clubs, 8 of clubs, 5 of clubs, K of diamonds, Q of hearts )
( Q of clubs, 4 of spades, 4 of clubs, 8 of hearts, 6 of clubs, A of hearts )
( T of diamonds, 7 of clubs, 6 of diamonds, 7 of diamonds, 9 of spades, Q of diamonds )
( 4 of hearts, 3 of clubs, A of spades, T of spades, 5 of hearts, A of diamonds )
( 6 of hearts, 2 of spades, 8 of diamonds, 2 of clubs, 5 of diamonds, K of clubs )
****************************************************************************/