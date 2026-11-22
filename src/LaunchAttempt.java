

public class LaunchAttempt implements Comparable<LaunchAttempt>{
   private String attemptId;
   private Potato potato;
   private Launcher launcher;
   private int payloadKg;
   private int flightRating;

   public LaunchAttempt (String attemptId, Potato potato, Launcher launcher, int payloadKg, int flightRating){
       this.attemptId = attemptId;
       this.potato = potato;
       this.launcher = launcher;
       this.payloadKg = payloadKg;
       this.flightRating = flightRating;
   }

    public String getAttemptId() {
       return attemptId;
    }

    public Potato getPotato(){
       return potato;
    }

    public Launcher getLauncher(){
       return launcher;
    }

    public int getPayloadKg() {
       return payloadKg;
    }

    public int getFlightRating(){
       return flightRating;
    }

    public int getScore(){
       return potato.calculateScore(payloadKg, flightRating);
    }

    @Override
    public int compareTo(LaunchAttempt other){
       if(this.flightRating != other.flightRating){
           return Integer.compare(other.flightRating, this.flightRating);
       }
       if(this.payloadKg != other.payloadKg){
           return Integer.compare(other.payloadKg, this.payloadKg);
       }
       return this.attemptId.compareTo(other.attemptId);
    }
}
