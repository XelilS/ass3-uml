package model.rules;

/**
 * Creates concrete rules.
 */
public class RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.
   */
  public HitStrategy getHitRule() {
    return new Rulesoft17();
  }

  /**
   * Crates the rule to use when starting a new game.
   */
  public NewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy();
  }

  public WinnerRule getDealerWinsTie() {
    return new DealerWins();
  }

  public WinnerRule getDealerLosesTie() {
    return new DealerLoses();
  }

}
