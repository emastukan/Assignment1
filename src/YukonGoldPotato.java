public class YukonGoldPotato extends Potato {
    public YukonGoldPotato(String id, String name){
        super(id, name, "YUKON_GOLD");
    }
    @Override
    public int calculateScore(int payloadKg, int flightRating){
        return 2*payloadKg + 3*flightRating;
    }
}
