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
   * Deal card(s).
   */
  public void cardDeal(Player p, boolean viewC) {
    Card.Mutable card = deck.getCard();
    card.show(viewC);
    p.dealCard(card);

  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.
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
   * Checks if the dealer is the winner compared to a player.
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
   * Checks if the game is over, i.e. the dealer can take no more cards.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * Player stopped taking cards, dealers turn.
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
   * Inform registered observers about the card status.
   */
  public void notifyObservers(Card card, Player player) {
    for (Observer obs : totObservers) {
      obs.cardUpdate(card, player);
    }
  }

  /**
   * Deal cards.
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
   * Addition of an observer.
   */
  public void addObserver(Observer observer) {
    totObservers.add(observer);
  }

}