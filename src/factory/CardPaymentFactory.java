package factory;

import adapter.card.HDFCAdapter;
import adapter.card.ICICIAdapter;
import adapter.card.SBIAdapter;
import strategy.payment.PaymentStrategy;

public class CardPaymentFactory {
    public static PaymentStrategy createCardpayment(String bank,String cardNo){
        return switch (bank.toLowerCase()){
            case "hdfc"->new HDFCAdapter(cardNo);
            case "sbi" -> new SBIAdapter(cardNo);
            case "icici"-> new ICICIAdapter(cardNo);
            default -> throw new IllegalArgumentException("Unsupported Bank: "+bank);
        };
    }
}
