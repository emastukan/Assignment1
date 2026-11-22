public class PurplePotato extends Potato {
    public PurplePotato(String id, String name){
        super(id, name, "PURPLE");
    }
    @Override
    public int calculateScore(int payloadKg, int flightRating){
        return 4*payloadKg + flightRating;
    }
}
