package BlackJack.controller;

import BlackJack.model.ICardObserver;
import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements ICardObserver{
  private IView m_view;
  private Game a_game;

  @Override
  public void CardDealt() {
    m_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    m_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    try {
      Thread.sleep(2000);
    }
    catch (Exception e) {}
  }

  public enum GameInput {
    Play, Hit, Stand, Quit
  }

  public boolean Play(Game a_game, IView a_view) {
    this.a_game = a_game;
    this.m_view = a_view;

    a_game.getDealer().addSubscriber(this);
    a_view.DisplayWelcomeMessage();
    
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    GameInput input = a_view.GetInput();
    
    if (input == GameInput.Play)
    {
        a_game.NewGame();
    }
    else if (input == GameInput.Hit)
    {
        a_game.Hit();
    }
    else if (input == GameInput.Stand)
    {
        a_game.Stand();
    }

    return input != GameInput.Quit;
  }
}