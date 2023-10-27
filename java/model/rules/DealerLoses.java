package model.rules;

/**
 * class implementing WinnerRule, handles when the dealer loses.
 */
public class DealerLoses implements WinnerRule {
  @Override
  public boolean doesDealerWinTie() {
    return false;
  }
}
