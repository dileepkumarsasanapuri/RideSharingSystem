package adapter.card;

import strategy.payment.PaymentStrategy;



public class SBIAdapter implements CardAdapter {
    class SBIGateway{
        public void payWithSBI(double amt,String cardNo) {
            System.out.println("SBI Card " + mask(cardNo) + " paid Rs." + amt);
        }
    }
    private SBIGateway sbi;

    public SBIAdapter(){
        this.sbi=new SBIGateway();

    }

    private String mask(String s) {
        if (s == null || s.length() < 4) return "****";
        return "****-****-****-" + s.substring(s.length() - 4);
    }
    @Override
    public void payWithCard(double amt,String cardNo)
    {
        sbi.payWithSBI(amt,cardNo);
    }
}
