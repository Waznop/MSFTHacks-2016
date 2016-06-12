import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Waznop on 2016-06-04.
 */
public class Main {

    public static void main(String[] args) {

        String APPLICATIONID = "ac969f99";
        String APPLICATIONKEYS = "5d26d83a246e6c0d6010b6a18b081344";
        NutritionIx nutritionIx = new NutritionIx(APPLICATIONID, APPLICATIONKEYS);

        Scanner scan = new Scanner(System.in);

        /*
        System.out.println("What is your name? ");
        String name = scan.nextLine();
        System.out.println("\nWhat is your age? ");
        int age = scan.nextInt();
        System.out.println("\nWhat is your gender? (0 for male, 1 for female, 2 otherwise) ");
        int gender = scan.nextInt();
        System.out.println("\nWhat is your activity level? (0 for sedentary, 1 for moderate, 2 for active) ");
        int activity = scan.nextInt();
        */

        String name = "Waznop";
        int age = 20;
        int gender = 0;
        int activity = 2;

        CalLog calLog = new CalLog(name, gender, age, activity, new ArrayList<>());

        /*
        // to take pictures directly from terminal - need imagesnap

        String[] cam = new String[] {"/bin/bash", "-c", "imagesnap", "-w", "5"};
        try {
            Process proc = new ProcessBuilder(cam).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        // hardcoded directory

        System.out.println("\nWhich picture would you like to scan? ");
        String pic = scan.nextLine();
        String myUrl = "/Users/Waznop/Desktop/MSFTHacks/MSFT/" + pic;

        String APPID = "2EveVQEv-KtHd36PmOwWsC6yN1kcFYAfHuKzm5-6";
        String APPSECRET = "JYmwMdlD3t0xGk3V6hdVpVJ8cClXlKkfdgN8Abx4";
        ClarifaiManager clarifaiManager = new ClarifaiManager(APPID, APPSECRET);

        String LINK = "https://young-mountain-93767.herokuapp.com/tags/";
        TagManager tagManager = new TagManager(LINK);

        try {
            List<String> lst = clarifaiManager.read(myUrl);
            System.out.println("Processing tags: " + lst);
            String toSearch = tagManager.getResults(lst);

            if (toSearch == "") {
                System.out.println("no matches");
                return;
            }

            Results logTest = nutritionIx.searchFood(toSearch);
            System.out.println("/**********************************************************/");
            System.out.println(logTest);
            System.out.println("/**********************************************************/");
            calLog.addEntry(logTest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
