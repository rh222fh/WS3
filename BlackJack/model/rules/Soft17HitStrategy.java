package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

class Soft17HitStrategy implements IHitStrategy {
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer) {
        if (a_dealer.CalcScore() < g_hitLimit) {
            return true;
        }
        else if (a_dealer.CalcScore() == g_hitLimit) {
            for(Card c : a_dealer.GetHand()) {
                if (c.GetValue() == Card.Value.Ace)
                {
                    return true;
                }
            }
        }
        return false;
    }
}