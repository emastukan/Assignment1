
public class Main {
    public static void main(String[] args){
        System.out.println("The Great Potato Space Program is ready!");
    try {
       var potatoes = DataLoader.loadPotatoes("input/potatoes.csv");
       var launchers = DataLoader.loadLaunchers("input/launchers.csv");
       var attempts = DataLoader.loadAttempts("input/launch_attempts.csv", potatoes, launchers);

       ReportGenerator.generatePotatoFlightLog(potatoes, attempts);
       ReportGenerator.generateLauncherActivity(launchers, attempts);
       ReportGenerator.generateLaunchResultsByPair(attempts);
       //ReportGenerator.generatePayloadViolations(attempts);
       //ReportGenerator.generateBestTestFlights(attempts);

       System.out.println("The reports 1 and 2 are generated in the output folder.");
       System.out.println("The report 3 is generated in the output folder.");
       //System.out.println("The reports 4 and 5 are generated in the output folder.");
    }
    catch (Exception e){
        System.out.println("An error occurred " + e.getMessage());
    }
}
}
