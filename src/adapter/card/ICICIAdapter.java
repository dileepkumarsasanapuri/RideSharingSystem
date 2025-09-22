package adapter.card;




public class ICICIAdapter implements CardAdapter {
    class ICICIGateway{
        public void payWithICICI(double amt,String cardNo){
            System.out.println("ICICI Card "+mask(cardNo)+" paid Rs."+amt);
        }
    }
   private ICICIGateway icici;

   public ICICIAdapter(){
       this.icici=new ICICIGateway();

   }
    private String mask(String s) {
        if (s == null || s.length() < 4) return "****";
        return "****-****-****-" + s.substring(s.length() - 4);
    }

   @Override
    public void payWithCard(double amt,String cardNo){
       icici.payWithICICI(amt,cardNo);
   }
}
