package adapter.upi;

import strategy.payment.PaymentStrategy;

class PaytmGateway{
    public void payViaPaytm(double amt,String upiId){
        System.out.println("Paytm UPI "+upiId+" paid Rs."+amt);
    }
}
public class PaytmAdapter implements PaymentStrategy {
    private PaytmGateway paytm;
    private String upiId;
    public PaytmAdapter(String upiId){
        this.paytm=new PaytmGateway();
        this.upiId=upiId;
    }

    @Override
    public void pay(double amt){
        paytm.payViaPaytm(amt,upiId);
    }
}
