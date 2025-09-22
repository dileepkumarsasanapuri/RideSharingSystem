package adapter.upi;

public interface UPIAdapter {
    void payWithUPI(double amt,String upiID) throws Exception;
}
