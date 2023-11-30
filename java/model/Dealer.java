package model;

import java.util.ArrayList;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;
import model.rules.WinnerRule;

/**
 * Represents a dealer player that handles the deck of cards and runs the game
 * using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private WinnerRule winRule;
  ArrayList<Observer> totObservers;

  /**
   * Initializing constructor.
   */
  public Dealer(RulesFactory rulesFactory) {

    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    winRule = rulesFactory.getDealerWinsTie();
    totObservers = new ArrayList<>();
  }

  /**
   * Starts a new game if the game is not currently under way.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(deck, this, player);
    }
    return false;
  }

  /**
   * Deals a card to a specified player and determines its visibility.
   */
  public void cardDeal(Player p, boolean viewC) {
    Card.Mutable card = deck.getCard();
    card.show(viewC);
    p.dealCard(card);

  }

  /**
   * Allows a player to take an additional card (hit) if certain conditions are
   * met.
   * Returns true if the player was able to take a card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      Card.Mutable c;
      c = deck.getCard();
      c.show(true);
      player.dealCard(c);

      return true;
    }
    return false;
  }

  /**
   * Determines if the dealer has won against a specified player.
   * Returns true if the dealer is the winner, false otherwise.
   */
  public boolean isDealerWinner(Player player) {
    if (player.calcScore() > maxScore) {
      return true;
    } else if (calcScore() > maxScore) {
      return false;
    }
    return winRule.doesDealerWinTie();
  }

  /**
   * Checks if the game has ended, meaning the dealer can't take more cards.
   * Returns true if the game is over, false otherwise.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * Represents the action when a player decides not to take any more cards and
   * it's the dealer's turn.
   * The dealer will continue to take cards until certain conditions are met.
   */
  public boolean stand() {
    showHand();

    while (deck != null && calcScore() < 17) {
      Card.Mutable c = deck.getCard();
      c.show(true);
      dealCard(c);
    }

    return true;
  }

  /**
   * Notifies all registered observers about the current status of a card.
   */
  public void notifyObservers(Card card, Player player) {
    for (Observer obs : totObservers) {
      obs.cardUpdate(card, player);
    }
  }

  /**
   * Deals a card to a player and determines its visibility.
   * Also notifies observers about the card status and introduces a delay.
   */
  public void dealCardToPlayer(Player player, boolean displayCard) {
    Card.Mutable cardMutable = deck.getCard();
    cardMutable.show(displayCard);
    player.dealCard(cardMutable);
    notifyObservers(cardMutable, player);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // Error.
    }
  }

  /**
   * Registers a new observer.
   */
  public void addObserver(Observer observer) {
    totObservers.add(observer);
  }

}