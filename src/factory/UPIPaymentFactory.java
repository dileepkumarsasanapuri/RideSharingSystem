package factory;

import adapter.upi.GPayAdapter;
import adapter.upi.PaytmAdapter;
import adapter.upi.PhonePeAdapter;
import strategy.payment.PaymentStrategy;
import strategy.payment.UpiPayment;

public class UPIPaymentFactory {
    public static class UPIPaymentData {
        public final String provider;
        public final String upiId;

        public UPIPaymentData(String provider, String upiId) {
            this.provider = provider;
            this.upiId = upiId;
        }
    }
    public static PaymentStrategy createUPIPayment(UPIPaymentData data){
        if (data == null) throw new IllegalArgumentException("upi data required");
        String p = data.provider == null ? "" : data.provider.trim().toLowerCase();
        switch (p) {
            case "gpay":
                return new UpiPayment("GPay", data.upiId, new GPayAdapter());
            case "phonepe":
                return new UpiPayment("PhonePe", data.upiId, new PhonePeAdapter());
            case "paytm":
                return new UpiPayment("Paytm", data.upiId, new PaytmAdapter());
            default:
                throw new IllegalArgumentException("Unknown UPI provider: " + data.provider);
        }
    }
}
