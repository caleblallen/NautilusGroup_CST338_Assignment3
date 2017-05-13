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
   public static void main(String[] args)
   {

   }
}

class Card
{
   //Enumerator for the card's suit
   public enum Suit { clubs, diamonds, hearts, spades };
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   //Default constructor for card overloaded to return A of spades
   public Card()
   {
      this.value = 'A';
      this.suit = Suit.spades;
   }
   
   //Parameterized constructor for card that accepts value and suit
   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   //Parameterized constructor for card that accepts value, suit, and errorFlag
   public Card(char value, Suit suit, boolean errorFlag)
   {
      set(value, suit);
      this.errorFlag = errorFlag;
   }
   
   //If error flag is false, returns value and suit of card in a single string, otherwise returns invalid
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
   
   //Mutator to set value and suit for a card
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
   
   public boolean equals(Card card)
   {
      /*
       * returns true if all the fields (members) 
       * are identical and false, otherwise.
       */
      return false;
   }
   
   private boolean isValid(char value, Suit suit)
   {
      /*
       * a private helper method that returns true or false, 
       * depending on the legality of the parameters.  
       * Note that, although it may be impossible for 
       * suit to be illegal (due to its enum-ness), we pass it, 
       * anyway, in anticipation of possible changes to the type 
       * from enum to, say, char or int, someday.  We only need to 
       * test value, at this time.
       */
      return false;
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
      
      //Remove all reference to the old hand.
      this.myCards = new Card[MAX_CARDS];
      
      //Reset the card counter.
      this.numCards = 0;
   }
   
   public boolean takeCard(Card card)
   {
      /*
       * adds a card to the next available position 
       * in the myCards array.  This is an object copy, 
       * not a reference copy, since the source of 
       * the Card might destroy or change its data 
       * after our Hand gets it -- we want our local 
       * data to be exactly as it was when we received it.
       */
      
      //Make sure we're not above our hand size limit.
      if(numCards < MAX_CARDS)
      {
         //Create a copy of the taken card and advance the card counter.
         myCards[numCards++] = new Card(card.getValue(),card.getSuit());
         
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
       * returns and removes the card in the top occupied position of the array.
       */
      if (numCards > 0)
      {
         //Make a copy of the card in the myCards array.
         Card playedCard = new Card(myCards[numCards - 1].getValue(),myCards[numCards - 1].getSuit());
         
         //Decrement card counter. Remove the topmost card from the array. 
         myCards[--numCards] = null;
        
         return playedCard;
      }
      else
      {
         //returns an invalid card to be consistent with inspectCard()
         return new Card('#',Card.Suit.clubs);
      }
      
   }
   
   public String toString()
   {
      /*
       * a stringizer that the client can 
       * use prior to displaying the entire hand.
       */
      String output = "( ";
      for(int i = 0; i < numCards; i++)
      {
         //print the card.
         output += this.myCards[i].toString();
         
         //add a comma to every card except the last.
         if(i + 1 < numCards)
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
       * Accessor for an individual card.  
       * Returns a card with errorFlag = true if k is bad.
       * 
       *  Valid k: 0 <= k < numCards
       */
      
      //Returns card if k is valid
      if (0 <= k && k < numCards)
      {
         return myCards[k];
      }
      else
      {
         //Returns invalid card if k is bad
         return new Card('#',Card.Suit.clubs);
      }
   } 
}

class Deck
{
   //Sets maximum number of cards to be played which is 6 decks (6 * 52 = 312)
   public final static int MAX_CARDS = 312;
   
   //This is a private static Card array containing exactly 52 card references, which point to all the standard cards
   //Avoids repeatedly declaring the same 52 cards as game play continues
   private static Card[] masterPack;
   
   private Card[] cards;
   private int topCard;
   private int numPacks;
   
   public Deck(int numPacks)
   {
      /*
       * a constructor that populates the arrays 
       * and assigns initial values to members.  
       * Overload so that if no parameters are 
       * passed, 1 pack is assumed.
       */
   }
   
   public Deck()
   {
      /*
       * Overload so that if no parameters are 
       * passed, 1 pack is assumed.
       */
      
   }
   
   public void init(int numPacks)
   {
      /*
       * re-populate cards[] with the standard 52 × numPacks 
       * cards.  We should not repopulate the static 
       * array, masterPack[], since that was done once, 
       * in the (first-invoked) constructor and  never changes.
       */
   }
   
   public void shuffle()
   {
      /*
       * mixes up the cards with the help of the standard random number generator.
       */
   }
   
   public Card dealCard()
   {
      /*
       * returns and removes the card 
       * in the top occupied position of cards[].
       */
      return null;
   }
   
   //Accessor to return array index of top card
   //This value also tells us how many total cards are currently in the array
   public int getTopCard()
   {
      return this.topCard;
   }
   
   //Method to test that the index of the card is legal
   public Card inspectCard(int k)
   {
      if(k >= 0 && k <= topCard)
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
      /*
       * this is a private method that will be called 
       * by the constructor.  However, it has to be done 
       * with a very simple twist:  even if many Deck 
       * objects are constructed in a given program, 
       * this static method will not allow itself to 
       * be executed more than once.  Since masterPack[] 
       * is a static, unchanging, entity, it need not be 
       * built every time a new Deck is instantiated.  
       * So this method needs to be able to ask itself, 
       * "Have I been here before?", and if the answer is 
       * "yes", it will immediately return without doing 
       * anything;  it has already built masterPack[] in 
       * a previous invocation.
       */
   }
   
}

