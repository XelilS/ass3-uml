package model.rules;

public class DealerWins implements WinnerRule {
  @Override
  public boolean doesDealerWinTie() {
    return true;
  }

}
