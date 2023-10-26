package model.rules;

/**
 * Creates concrete rules.
 */
public class RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.

   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    return new Rulesoft17();
  }

  /**
   * Crates the rule to use when starting a new game.

   * @return The rule to use.
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
