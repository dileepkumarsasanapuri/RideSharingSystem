package adapter.upi;

import strategy.payment.PaymentStrategy;

class PhonePeGateway{
    public void payViaPhonepe(double amt,String upiId){
        System.out.println("PhonePe UPI "+upiId+" paid Rs."+amt);
    }
}
public class PhonePeAdapter implements PaymentStrategy {
    private PhonePeGateway phonepe;
    private String upiID;
    public PhonePeAdapter(String upiID){
        this.phonepe=new PhonePeGateway();
        this.upiID=upiID;
    }

    @Override
    public void pay(double amt){
        phonepe.payViaPhonepe(amt,upiID);
    }
}
