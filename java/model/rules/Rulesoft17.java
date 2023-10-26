package model.rules;

import model.Player;
import model.Card;

public class Rulesoft17 implements HitStrategy {

  @Override
  public boolean doHit(Player instance) {
    int points = instance.calcScore();
    int ace = 0;
    for (Card card : instance.getHand()) {
      if (card.getValue() == Card.Value.Ace) {
        ace += 1;
      }
    }
    if (points == 17) {
      int sub = ace * 10;
      return ((points - sub) == 7);
    }
    else {
      return (points < 17);
    }
  }

}
