package edu.up.campus.regier21.dominiongamestate;

public class DominionShopPileState {
    private DominionCardState card;
    private int amount;

    public DominionShopPileState(DominionCardState card, int amount){
        this.card = card;
        this.amount = amount;
    }

    public DominionCardState getCard() {
        return card;
    }

    public int getAmount(){
        return amount;
    }

    public void removeCard(){
        amount--;
    }

    public void removeAmount(int amount){
        this.amount -= amount;
    }
}
