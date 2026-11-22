public class RussetPotato extends Potato{
    public RussetPotato(String id, String name){
        super(id, name, "RUSSET");
    }
    @Override
    public int calculateScore(int payloadKg, int flightRating){
        return 2*payloadKg + flightRating;
    }
}
