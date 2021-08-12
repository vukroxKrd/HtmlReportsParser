import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client {

    public static void selectAction(int input) {
        switch (input) {
            case (1):
                ArrayList<String> orderIds = Parser.orderIds(Parser.inputFileProvider());
                ParseProcessor.printOrdersNumber(orderIds);
                break;
            case (2):
                HashMap<String, String> ordersAndStatus = Parser.orderIds_AndStatus(Parser.inputFileProvider());
                ParseProcessor.printOrdersAndStatus(ordersAndStatus);
                break;
            case (3):
                Map<String, ReportEntry> ordersStatusAndDates = Parser.orderId_StatusAndDate(Parser.inputFileProvider());
                ParseProcessor.printOrdersStatusAndDate(ordersStatusAndDates);
                break;
        }
    }
        public static void selectAction(int input, String filePath) {

            switch (input) {
                case (1):
                    ArrayList<String> orderIds = Parser.orderIds(Parser.inputFileProvider(filePath));
                    ParseProcessor.printOrdersNumber(orderIds);
                    break;
                case (2):
                    HashMap<String, String> ordersAndStatus = Parser.orderIds_AndStatus(Parser.inputFileProvider(filePath));
                    ParseProcessor.printOrdersAndStatus(ordersAndStatus);
                    break;
                case (3):
                    Map<String, ReportEntry> ordersStatusAndDates = Parser.orderId_StatusAndDate(Parser.inputFileProvider(filePath));
                    ParseProcessor.printOrdersStatusAndDate(ordersStatusAndDates);
                    break;
            }
        }

        public static void prompt(){
            System.out.println
                    ("Для просмотра количества ордеров нажми \"1\"\n"+
                            "Для просмотра количества ордеров со статусами нажми \"2\"\n"+
                            "Для просмотра количества ордеров со статусами и датами нажми \"3\"");
        }

        public static void fullPrompt(){
        System.out.println
                ("Для просмотра количества ордеров нажми \"1\"\n"+
                        "Для просмотра количества ордеров со статусами нажми \"2\"\n"+
                        "Для просмотра количества ордеров со статусами и датами нажми \"3\"\n"+
                        "Для ввода пути к исполняемому файлу нажми: \"4\"\n");
    }

    public static String getStringInputFromUser(){
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
    public static int getNumberInputFromUser(){
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        try {
            input = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }


    public static void main(String[] args) {
        fullPrompt();
        int input = getNumberInputFromUser();
        if (input == 4) {
            prompt();
            input = getNumberInputFromUser();
            System.out.println("Укажи адрес до файла");
            String filePath = getStringInputFromUser();
            selectAction(input,filePath);
        } else {
            Client.selectAction(input);
        }
    }
}
