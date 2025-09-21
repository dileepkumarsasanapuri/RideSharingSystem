package strategy.payment;

public class CardPayment implements PaymentStrategy {
    private String cardNo;
    public CardPayment(String cardNo){
        this.cardNo=cardNo;
    }

    @Override
    public void pay(double amt){
        System.out.println("Payment of Rs."+amt+" made by Card Payment using card ending with "+cardNo.substring(cardNo.length()-4)+" . ");
    }
}
