package model.rules;

public class DealerLoses implements WinnerRule{
  @Override
  public boolean doesDealerWinTie() {
    return false;
  }
}
