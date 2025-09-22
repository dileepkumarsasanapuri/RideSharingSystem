package adapter.upi;

import strategy.payment.PaymentStrategy;


public class GPayAdapter implements UPIAdapter {
    class GpayGateway{
        public void payViaGPay(double amt,String upiId){
            System.out.println("GPay UPI "+upiId+" paid Rs."+amt);
        }
    }
    private GpayGateway gpay;

    public GPayAdapter(){
        this.gpay=new GpayGateway();

    }

    @Override
    public void payWithUPI(double amt,String upiId){
        gpay.payViaGPay(amt,upiId);
    }
}
