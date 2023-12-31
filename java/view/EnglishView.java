package view;

import model.Card;

/**
 * Implements an english console view.
 */
public class EnglishView implements View {
  private int playerInput;

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
    System.out.println("Hello Black Jack World");
    System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
  }

  /**
   * Returns pressed characters from the keyboard.
   */
  public int getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  public void displayCard(model.Card card) {
    System.out.println("" + card.getValue() + " of " + card.getColor());
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Player", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Dealer", hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Has: ");
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Score: " + score);
    System.out.println("");
  }

  /**
   * Displays the winner of the game.
   * 
   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("GameOver: ");
    if (dealerIsWinner) {
      System.out.println("Dealer Won!");
    } else {
      System.out.println("You Won!");
    }

  }

  @Override
  public boolean hit() {
    return playerInput == 'h';
  }

  @Override
  public boolean stand() {
    return playerInput == 's';
  }

  @Override
  public void input() {
    playerInput = getInput();
  }

  @Override
  public boolean exit() {
    return playerInput == 'q';
  }

  @Override
  public boolean initiate() {
    return playerInput == 'p';
  }

  @Override
  public void dealerView(Card card) {
    System.out.println("current dealer hand is: ");
    displayCard(card);
  }

  @Override
  public void playerView(Card card) {
    System.out.println("current player hand is: ");
    displayCard(card);
  }

}
