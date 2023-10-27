package controller;

import model.Card;
import model.Game;
import model.Observer;
import view.View;

/**
 * Scenario controller for playing the game.
 */
public class Player implements Observer {
  public Game game;
  public View view;

  /**
   * Simple constructor.
   */
  public Player(Game game, View view) {
    this.game = game;
    this.view = view;
    game.addObservers(this);

  }

  /**
   * Runs the play use case.
   */
  public boolean play(Game game, View view) {
    view.displayWelcomeMessage();

    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    view.input();

    if (view.initiate()) {
      game.newGame();
    } else if (view.hit()) {
      game.hit();
    } else if (view.stand()) {
      game.stand();
    }

    return !(view.exit());
  }

  @Override
  public void cardUpdate(Card card, model.Player player) {
    if (player instanceof model.Dealer) {
      view.dealerView(card);
    } else {
      view.playerView(card);
    }
  }
}