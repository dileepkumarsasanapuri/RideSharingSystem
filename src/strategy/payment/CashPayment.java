package strategy.payment;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amt){
        System.out.println("Payment of Rs."+amt+" made by Cash Payment");
    }
}
