package strategy.payment;

public class UpiPayment implements PaymentStrategy {
    private final String upiId;
    private final String providerName;
    private final adapter.upi.UPIAdapter adapter;
    public UpiPayment(String providerName,String upiId,adapter.upi.UPIAdapter adapter){
        {
            this.upiId=upiId;
            this.providerName=providerName;
            this.adapter=adapter;
        }
    }

    @Override
    public void pay(double amt) throws Exception{
        System.out.println("Payment of Rs."+amt+" made by UPI Payment via upiId "+upiId+".");
        adapter.payWithUPI(amt,upiId);
    }
}
