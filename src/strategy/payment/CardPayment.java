package strategy.payment;

public class CardPayment implements PaymentStrategy {
    private final String providerName;
    private final String cardNo;
    private final adapter.card.CardAdapter adapter;
    public CardPayment(String providerName,String cardNo,adapter.card.CardAdapter adapter){
        this.cardNo=cardNo;
        this.providerName=providerName;
        this.adapter=adapter;
    }

    @Override
    public void pay(double amt) throws Exception{
        adapter.payWithCard(amt,cardNo);
    }
}
