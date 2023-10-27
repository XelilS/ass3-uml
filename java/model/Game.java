package model;

/**
 * Represents the entirety of the game. Acts as a Facade to the model.
 */
public class Game {

  private Dealer dealer;
  private Player player;

  /**
   * Constructor that creates a new game instance with a dealer and player.
   */
  public Game() {
    dealer = new Dealer(new model.rules.RulesFactory());
    player = new Player();
  }

  /**
   * Checks if the game has ended.
   */
  public boolean isGameOver() {
    return dealer.isGameOver();
  }

  /**
   * Checks if the dealer is the winner.
   */
  public boolean isDealerWinner() {
    return dealer.isDealerWinner(player);
  }

  /**
   * Stars a new game.
   */
  public boolean newGame() {
    return dealer.newGame(player);
  }

  /**
   * Call to let the player get a new card.
   */
  public boolean hit() {
    return dealer.hit(player);
  }

  /**
   * Call to let the dealer take cards.
   */
  public boolean stand() {
    return dealer.stand();
  }

  /**
   * Gets the cards currently in the dealer's hand.
   */
  public Iterable<Card> getDealerHand() {
    return dealer.getHand();
  }

  /**
   * Returns the score of the dealer's hand.
   */
  public int getDealerScore() {
    return dealer.calcScore();
  }

  /**
   * Gets the cards currently in the player's hand.
   */
  public Iterable<Card> getPlayerHand() {
    return player.getHand();
  }

  /**
   * addition of an observer.
   */
  public void addObservers(controller.Player player) {
    dealer.addObserver(player);
  }

  /**
   * Returns the score of the player's hand.
   */
  public int getPlayerScore() {
    return player.calcScore();
  }

}