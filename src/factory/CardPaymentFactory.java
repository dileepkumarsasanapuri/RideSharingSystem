package factory;

import adapter.card.HDFCAdapter;
import adapter.card.ICICIAdapter;
import adapter.card.SBIAdapter;
import strategy.payment.CardPayment;
import strategy.payment.PaymentStrategy;

public class CardPaymentFactory {
    public static class CardPaymentsData{
        public final String provider;
        public final String cardNo;
        public CardPaymentsData(String provider,String cardNo){
            this.provider=provider;
            this.cardNo=cardNo;
        }
    }
    public static PaymentStrategy createCardpayment(CardPaymentsData data){
        if(data==null) throw new IllegalArgumentException("Card data required");
        String p=data.provider==null?"":data.provider.trim().toLowerCase();
        switch (p){
            case "hdfc":
                return new CardPayment("HDFC", data.cardNo, new HDFCAdapter());
            case "icici":
                return new CardPayment("ICICI", data.cardNo, new ICICIAdapter());
            case "sbi":
                return new CardPayment("SBI", data.cardNo, new SBIAdapter());
            default:
                throw new IllegalArgumentException("Unknown card provider: " + data.provider);
        }
    }
}
