package strategy.payment;

public class UpiPayment implements PaymentStrategy {
    private String upiId;
    public UpiPayment(String upiId){
        this.upiId=upiId;
    }

    @Override
    public void pay(double amt){
        System.out.println("Payment of Rs."+amt+" made by UPI Payment via upiId "+upiId+".");
    }
}
