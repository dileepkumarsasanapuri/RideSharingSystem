package adapter.card;

import strategy.payment.PaymentStrategy;

class HDFCgateway{
    public void payWithHDFC(double amt,String cardNo){
        System.out.println("HDFC Card "+cardNo+" paid Rs."+amt);
    }
}
public class HDFCAdapter implements PaymentStrategy {
    private HDFCgateway hdfc;
    private String cardNo;
    public HDFCAdapter(String cardNo){
        this.cardNo=cardNo;
        this.hdfc=new HDFCgateway();
    }

    @Override
    public void pay(double amt){
        hdfc.payWithHDFC(amt,cardNo);
    }
}
