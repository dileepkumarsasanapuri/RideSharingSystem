package adapter.upi;

import strategy.payment.PaymentStrategy;

class GpayGateway{
    public void payViaGPay(double amt,String upiId){
        System.out.println("GPay UPI "+upiId+" paid Rs."+amt);
    }
}
public class GPayAdapter implements PaymentStrategy {
    private GpayGateway gpay;
    private String upiId;
    public GPayAdapter(String upiId){
        this.gpay=new GpayGateway();
        this.upiId=upiId;
    }

    @Override
    public void pay(double amt){
        gpay.payViaGPay(amt,upiId);
    }
}
