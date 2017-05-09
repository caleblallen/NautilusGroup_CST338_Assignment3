/*
 * The Nautilus Group - Write a Java program: Decks of Cards
 * 
 * Team Members: Caleb Allen, Daisy Mayorga, David Harrison, 
 *               Dustin Whittington, & Michael Cline
 */
public class Assig3
{

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      System.out.println("Hello World!");

   }

}

class Card
{
   //Enumerator for the Card's suit
   enum Suit { clubs, diamonds, hearts, spades };
   
   private char value;
   private Suit suit;
   private boolean errorFlag;
   
   public Card(char value, Suit suit)
   {
      /*
       * The constructor should call the proper mutator(s).  
       * Overload this to cope with a client that wants to instantiate 
       * without parameters and use 'A' and 'spades' as the default 
       * value and suit when not supplied.  Provide at least two 
       * constructors -- no parameters and all parameters -- or more 
       * if you wish.  Because we have the errorFlag member, the 
       * constuctor (via the mutator), can set that member when it gets 
       * bad data; it does not have to assign default values upon 
       * receipt of bad data.  This is a new technique for us.  
       * Again, default card (no parameters passed) is the ('A', spades).
       */
   }
   
   public String toString()
   {
      /*
       * a stringizer that the client can use prior to 
       * displaying the card.  It provides a clean 
       * representation of the card.  If errorFlag == true, 
       * it should return correspondingly reasonable 
       * reflection of this fact (something like "[ invalid ]" 
       * rather than a suit and value).
       */
      return "";
   }
   
   public boolean set(char value, Suit suit)
   {
      /*
       * a mutator that accepts the legal values 
       * established in the earlier section.  When 
       * bad values are passed, errorFlag is set to 
       * true and other values can be left in any state 
       * (even partially set). If good values are passed, 
       * they are stored and errorFlag is set to false.  
       * Make use of the private helper, listed below.
       */
      return false;
   }
   
   public Suit getSuit()
   {
      /*
       * Accessor for Suit
       */
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
   //TODO: Does this need to be final? Not sure
   public final int MAX_CARDS = 50;
   
   private Card[] myCards;
   private int numCards;
   
   public Hand()
   {
      /*
       * Default Constructor;
       */
   }
   
   public void resetHand()
   {
      /*
       * remove all cards from the hand (in the simplest way).
       */
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
      
      return false;
   }
   
   public Card playCard()
   {
      /*
       * returns and removes the card in the top occupied position of the array.
       */
      return null;
   }
   
   public String toString()
   {
      /*
       * a stringizer that the client can 
       * use prior to displaying the entire hand.
       */
      return "";
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
       */
      return null;
   }
   
   
}

class Deck
{
   public final int MAX_CARDS = 6*52;
   
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
   
   public int getTopCard()
   {
      /*
       * An accessor for the int, topCard (no mutator.)
       */
      return 0;
   }
   
   public Card inspectCard(int k)
   {
      /*
       * Accessor for an individual card.  
       * Returns a card with errorFlag = true if k is bad.
       */
      return null;
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
