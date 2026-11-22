public class FingerlingPotato extends Potato{
    public FingerlingPotato(String id, String name){
        super(id, name, "FINGERLING");
    }
    @Override
    public int calculateScore(int payloadKg, int flightRating){
        return 3*payloadKg + 2*flightRating;
    }
}
