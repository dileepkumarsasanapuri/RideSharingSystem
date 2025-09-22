package adapter.upi;

import strategy.payment.PaymentStrategy;


public class PhonePeAdapter implements UPIAdapter {
    class PhonePeGateway{
        public void payViaPhonepe(double amt,String upiId){
            System.out.println("PhonePe UPI "+upiId+" paid Rs."+amt);
        }
    }
    private PhonePeGateway phonepe;

    public PhonePeAdapter(){
        this.phonepe=new PhonePeGateway();

    }

    @Override
    public void payWithUPI(double amt,String upiID){
        phonepe.payViaPhonepe(amt,upiID);
    }
}
