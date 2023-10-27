package model.rules;

import model.Dealer;
import model.Deck;
import model.Player;

class AmericanNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Deck deck, Dealer dealer, Player player) {

    dealer.dealCardToPlayer(player, true);

    dealer.dealCardToPlayer(dealer, true);

    dealer.dealCardToPlayer(player, true);

    dealer.dealCardToPlayer(dealer, false);

    return true;
  }
}