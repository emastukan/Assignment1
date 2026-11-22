import java.io.File;
        import java.util.*;

public class DataLoader {

    public static Map <String, Potato> loadPotatoes (String path) throws Exception {
        Map<String, Potato> map = new HashMap<>();
        Scanner sc = new Scanner (new File(path));
        if(sc.hasNextLine()) sc.nextLine();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.trim().isEmpty()) continue;
            String[] p = line.split(",");
            String id = p[0].trim();
            String name = p[1].trim();
            String type = p[2].trim();
            if(type.equals("FINGERLING")) map.put(id, new FingerlingPotato(id, name));
            else if(type.equals("PURPLE")) map.put(id, new PurplePotato(id, name));
            else if(type.equals("RUSSET")) map.put(id, new RussetPotato(id, name));
            else if(type.equals("YUKON_GOLD")) map.put(id, new YukonGoldPotato(id, name));
        }
       sc.close();
        return map;
    }

    public static Map<String, Launcher> loadLaunchers(String path) throws Exception{
        Map<String, Launcher> map = new HashMap<>();
        Scanner sc = new Scanner(new File(path));
        if(sc.hasNextLine()) sc.nextLine();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.trim().isEmpty()) continue;
                String[] p = line.split(",");
            map.put(p[0].trim(), new Launcher(p[0].trim(),p[1].trim(), Integer.parseInt(p[2].trim())));
        }
        sc.close();
        return map;
    }

    public static ArrayList<LaunchAttempt> loadAttempts(String path, Map <String, Potato> potatoes, Map <String, Launcher> launchers) throws Exception {
        ArrayList<LaunchAttempt> list = new ArrayList<>();
        Scanner sc = new Scanner(new File(path));
        if(sc.hasNextLine()) sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.trim().isEmpty()) continue;
            String[] p = line.split(",");
            Potato pot = potatoes.get(p[1].trim());
            Launcher lau = launchers.get(p[2].trim());
            list.add(new LaunchAttempt(p[0].trim(), pot, lau, Integer.parseInt(p[3].trim()), Integer.parseInt(p[4].trim())));
        }
        sc.close();
        return list;
    }
}
