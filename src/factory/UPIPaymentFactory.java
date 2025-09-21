package factory;

import adapter.upi.GPayAdapter;
import adapter.upi.PaytmAdapter;
import adapter.upi.PhonePeAdapter;
import strategy.payment.PaymentStrategy;

public class UPIPaymentFactory {
    public static PaymentStrategy createUPIPayment(String upi, String upiId){
        return switch (upi.toLowerCase()){
            case "phonepe" -> new PhonePeAdapter(upiId);
            case "paytm"->new PaytmAdapter(upiId);
            case "gpay"->new GPayAdapter(upiId);
            default -> throw new IllegalArgumentException("Unsupported upi: "+upi);
        };
    }
}
