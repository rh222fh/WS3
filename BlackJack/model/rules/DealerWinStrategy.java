package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerWinStrategy implements IWinStrategy {

    @Override
    public boolean IsDealerWinner(Player a_player, Dealer a_dealer) {
        if (a_player.CalcScore() > a_player.getMaxScore()) {
            return true;
        } else if (a_dealer.CalcScore() > a_dealer.getMaxScore()) {
            return false;
        }
        return a_dealer.CalcScore() >= a_player.CalcScore();
    }
}
