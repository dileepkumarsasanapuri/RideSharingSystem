package adapter.card;

import strategy.payment.PaymentStrategy;

class SBIGateway{
    public void payWithSBI(double amt,String cardNo) {
        System.out.println("SBI Card " + cardNo + " paid Rs." + amt);
    }
}

public class SBIAdapter implements PaymentStrategy {
    private SBIGateway sbi;
    private String cardNo;
    public SBIAdapter(String cardNo){
        this.sbi=new SBIGateway();
        this.cardNo=cardNo;
    }

    @Override
    public void pay(double amt){
        sbi.payWithSBI(amt,cardNo);
    }
}
