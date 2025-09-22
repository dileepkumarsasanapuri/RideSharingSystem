package adapter.card;

import strategy.payment.PaymentStrategy;


public class HDFCAdapter implements CardAdapter {
    class HDFCgateway{
        public void payWithHDFC(double amt,String cardNo){
            System.out.println("HDFC Card "+mask(cardNo)+" paid Rs."+amt);
        }
    }
    private HDFCgateway hdfc;

    public HDFCAdapter(){
        this.hdfc=new HDFCgateway();
    }

    @Override
    public void payWithCard(double amt,String cardNo){
        hdfc.payWithHDFC(amt,cardNo);
    }
    private String mask(String s) {
        if (s == null || s.length() < 4) return "****";
        return "****-****-****-" + s.substring(s.length() - 4);
    }
}
