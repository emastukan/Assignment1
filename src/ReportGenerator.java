import java.io.File;
        import java.io.PrintWriter;
            import java.util.*;
public class ReportGenerator {
    private static void ensureOutputDir(){
        File dir = new File("output");
        if(!dir.exists()){
            dir.mkdir();
        }
    }
   public static void generatePotatoFlightLog(Map<String, Potato> potatoes, List<LaunchAttempt> attempts) throws Exception {
       ensureOutputDir();
       PrintWriter pw = new PrintWriter(new File("output/01_potato_flight_log.csv"));
       pw.println("potatoId,name,type,attemptCount,totalPoints");

       List<String> sortedIds = new ArrayList<>(potatoes.keySet());
       Collections.sort(sortedIds);

       for (String id : sortedIds){
           Potato p = potatoes.get(id);
           int count = 0;
           int totalPoints = 0;

           for (LaunchAttempt att: attempts) {
               if(att.getPotato().getPotatoId().equals(id)){
                   count++;
                   totalPoints += att.getScore();
               }
           }

           pw.println(p.getPotatoId() + "," + p.getName() + "," + p.getType() + "," + count + "," + totalPoints);
       }
       pw.close();
   }

   public static void generateLauncherActivity(Map<String, Launcher> launchers, List <LaunchAttempt> attempts) throws Exception {
       ensureOutputDir();
       PrintWriter pw = new PrintWriter(new File("output/02_launcher_activity.csv"));
       pw.println("launcherId,name,attemptCount,totalPayloadKg,overLimitCount");

       List<String> sortedIds = new ArrayList<>(launchers.keySet());
       Collections.sort(sortedIds);

       for(String id : sortedIds){
           Launcher l = launchers.get(id);
           int count = 0;
           int totalPayload = 0;
           int overLimitCount = 0;

           for (LaunchAttempt att : attempts){
               if(att.getLauncher().getLauncherId().equals(id)){
                   count++;
                   totalPayload += att.getPayloadKg();
                   if(att.getPayloadKg() > l.getMaxPayloadKg()) {
                       overLimitCount++;
                   }
               }
           }
    pw.println(l.getLauncherId() + "," + l.getName() + "," + count + "," + totalPayload + "," + overLimitCount);
       }
       pw.close();
   }

   public static void generateLaunchResultsByPair(List<LaunchAttempt> attempts)throws Exception{
        ensureOutputDir();
        PrintWriter pw = new PrintWriter(new File("output/03_launch_results_by_pair.csv"));
        pw.println("potatoId,launcherId,attemptCount,totalPoints");

        Map<String, Integer> countMap = new HashMap<>();
        Map<String, Integer> pointsMap = new HashMap<>();
        Map<String, String[]> pairInfoMap = new HashMap<>();

        for (LaunchAttempt att : attempts) {
            String pId = att.getPotato().getPotatoId();
            String lId = att.getLauncher().getLauncherId();
            String key = pId + ":" + lId;

            pairInfoMap.put(key, new String[]{pId,lId});
            countMap.put(key, countMap.getOrDefault(key,0)+1);
            pointsMap.put(key, pointsMap.getOrDefault(key,0)+att.getScore());
        }

        List<String[]> sortedPairs = new ArrayList<>(pairInfoMap.values());
        sortedPairs.sort((a, b) -> {
            int cmpPotato = a[0].compareTo(b[0]);
            if(cmpPotato != 0) return cmpPotato;
            return a[1].compareTo(b[1]);
                });
        for(String[] info : sortedPairs){
            String pId = info[0];
            String lId = info[1];
            String key = pId + ":" + lId;
            int count = countMap.get(key);
            int points = pointsMap.get(key);
            pw.println(pId + "," + lId + "," + count + "," + points);
        }
        pw.close();
   }
   public static void generatePayloadViolations(List <LaunchAttempt> attempts) throws Exception {
        ensureOutputDir();
        PrintWriter pw = new PrintWriter(new File("output/04_payload_violations.csv"));
        pw.println("attemptId,potatoId,launcherId,payloadKg,maxPayloadKg,excessKg");

        List<String[]> violations = new ArrayList<>();
        for(LaunchAttempt att : attempts) {
            int payload = att.getPayloadKg();
            int maxpayload = att.getLauncher().getMaxPayloadKg();
            if(payload > maxpayload){
                int excess = payload - maxpayload;
                violations.add(new String[]{
                    att.getAttemptId(),
                    att.getPotato().getPotatoId(),
                    att.getLauncher().getLauncherId(),
                    String.valueOf(payload),
                    String.valueOf(maxpayload),
                    String.valueOf(excess)
                });
            }
        }
  violations.sort((a, b)-> {
            int excessA = Integer.parseInt(a[5]);
           int excessB = Integer.parseInt(b[5]);
           if(excessA != excessB){
               return Integer.compare(excessB, excessA);
           }
           return a[0].compareTo(b[0]);
       });
for (String[] v : violations) {
    pw.println(String.join(",",v));
}
pw.close();
    }

   public static void generateBestTestFlights(List<LaunchAttempt> attempts) throws Exception{
     ensureOutputDir();
     PrintWriter pw = new PrintWriter(new File("output/05_best_test_flights.csv"));
      pw.println("attemptId,potatoName,launcherName,flightRating,payloadKg,points");

      List<LaunchAttempt> sortedAttempts = new ArrayList<>(attempts);
      Collections.sort(sortedAttempts);
      int limit = Math.min(3, sortedAttempts.size());
      for(int i = 0; i < limit; i++){
          LaunchAttempt att = sortedAttempts.get(i);
          pw.println(
                  att.getAttemptId() + "," +
                  att.getPotato().getName() + "," +
                  att.getLauncher().getName() + "," +
                  att.getFlightRating() + "," +
                  att.getPayloadKg() + "," +
                  att.getScore()

          );
      }
        pw.close();
    }

}
