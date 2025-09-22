package adapter.upi;

import strategy.payment.PaymentStrategy;

public class PaytmAdapter implements UPIAdapter {

    class PaytmGateway{
        public void payViaPaytm(double amt,String upiId){
            System.out.println("Paytm UPI "+upiId+" paid Rs."+amt);
        }
    }

    private PaytmGateway paytm;

    public PaytmAdapter(){
        this.paytm=new PaytmGateway();

    }

    @Override
    public void payWithUPI(double amt,String upiId){
        paytm.payViaPaytm(amt,upiId);
    }
}
