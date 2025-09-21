package adapter.card;

import strategy.payment.PaymentStrategy;

class ICICIGateway{
    public void payWithICICI(double amt,String cardNo){
        System.out.println("ICICI Card "+cardNo+" paid Rs."+amt);
    }
}
public class ICICIAdapter implements PaymentStrategy {
   private ICICIGateway icici;
   private String cardNo;
   public ICICIAdapter(String cardNo){
       this.icici=new ICICIGateway();
       this.cardNo=cardNo;
   }

   @Override
    public void pay(double amt){
       icici.payWithICICI(amt,cardNo);
   }
}
