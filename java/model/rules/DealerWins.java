package model.rules;

/**
 * class implementing WinnerRule, handles when the dealer wins.
 */
public class DealerWins implements WinnerRule {
  @Override
  public boolean doesDealerWinTie() {
    return true;
  }
}
