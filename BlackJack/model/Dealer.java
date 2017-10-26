package BlackJack.model;

import BlackJack.model.rules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinStrategy m_winRule;
  private List<ICardObserver> observers;

  public Dealer(RulesFactory a_rulesFactory) {
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_winRule = a_rulesFactory.GetWinRule();
    observers = new ArrayList<>();
  }


  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(this, a_player);
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {

      this.DealCard(a_player, true);

      return true;
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {

    return m_winRule.IsDealerWinner(a_player, this);
  }

  public boolean IsGameOver() {
    if (m_deck != null && !m_hitRule.DoHit(this)) {
      return true;
    }
    return false;
  }

  public boolean Stand() {
    if (m_deck != null) {
      ShowHand();

      while (m_hitRule.DoHit(this)) {
        Hit(this);
      }
    }
  return false;
  }

  public void DealCard(Player a_player, boolean showCard) {
    Card c;
    c = m_deck.GetCard();
    a_player.AddToHand(c);

    c.Show(showCard);

    for (ICardObserver o: observers) {
        o.CardDealt();
    }
    try {
      Thread.sleep(2000);
    }
    catch (Exception e) {}
  }

  public void addSubscriber(ICardObserver newObserver) {
    this.observers.add(newObserver);
  }
}