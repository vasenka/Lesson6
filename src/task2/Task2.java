package task2;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {

        File file = new File("/Users/Vasilisa/IdeaProjects/EpamJavaSE01/Lesson5/src/main/resources/task2/task2.properties");
        Universal universal = new Universal();
        Map<String,String> map = new HashMap<>();
        try {
            map = universal.fileToMap(file);
        } catch (FileNotFoundException e) {

        }
        Scanner in = new Scanner(System.in);
        System.out.println("Enter key ===>");
        String key = in.next();
        System.out.println("The value by this key: "+ universal.find(map, key));


    }
}
class Universal {
    Map<String, String> fileToMap(File file) throws FileNotFoundException {
        Map<String, String> map = new HashMap<>();
        if (!file.getName().contains(".properties")){
            System.out.println("It is not a properties file!!!");
        } else
        if (!file.exists()){
            throw new FileNotFoundException();
        } else {
            try (BufferedReader bf = new BufferedReader(new FileReader(file));) {
                String s;
                String sValue = null;
                while ((s = bf.readLine()) != null) {

                    Pattern p = Pattern.compile("^[^=\\s]+");
                    Matcher m = p.matcher(s);
                    String sKey = null;
                    while (m.find()) {
                        sKey = m.group();
                    }
                    Pattern p2 = Pattern.compile("[^\\s=]+$");
                    Matcher m2 = p2.matcher(s);
                    while (m2.find()) {
                        sValue = m2.group();
                    }
                    map.put(sKey,sValue);

                }


            } catch (IOException e) {

            }

        }
        return map;
    }
    String find(Map<String,String> map, String key){
        String res;
        if (!map.containsKey(key)) {
            throw new NullPointerException("No such key!");
        } else {
            res = map.get(key);
        }
        return res;
    }
}

