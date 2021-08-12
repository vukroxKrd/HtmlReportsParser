import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {


    public static File inputFileProvider() {
        File input = new File("null");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = loader.getResourceAsStream("input.properties")) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(inputStream);
            input = new File(prop.getProperty("file_address"));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
        return input;
        }
    }
    public static File inputFileProvider(String consoleInput) {
        File input = new File(consoleInput);
        return input;
    }


    public static ArrayList<String> orderIds(File input){
    ArrayList<String> reportContainer = new ArrayList<>();
        try {
            Document doc = Jsoup.parse(input, "UTF-8", "https://slapi-app-production.sellerlabs.com/");
            for (Element row: doc.select("table tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")){
                    continue;
                } else {
                    final String orderId = row.select("td:nth-of-type(1)").text();
                    reportContainer.add(orderId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportContainer;
    }
    public static HashMap<String, String> orderIds_AndStatus(File input){
        HashMap<String, String> reportContainer = new HashMap<>();
        try {
            Document doc = Jsoup.parse(input, "UTF-8", "https://slapi-app-production.sellerlabs.com/");
            for (Element row: doc.select("table tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")){
                    continue;
                } else {
                    final String orderId = row.select("td:nth-of-type(1)").text();
                    final String orderStatus = row.select("td:nth-of-type(5)").text();
                    reportContainer.put(orderId,orderStatus);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportContainer;
    }
    private static String dateConverter(String input){
        //2021-08-01T18:14:53+00:00
        String from = input.substring(0, input.length()-6);
        DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inputFormatter.parse(from);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat outputFormatter = new SimpleDateFormat("dd/MMMM/yyyy");
        String output = outputFormatter.format(date);
        // Output : Day/month/year
        return output;
    }
    public static Map<String, ReportEntry> orderId_StatusAndDate(File input){
        HashMap<String, ReportEntry> allReportEntries = new HashMap<>();

        try {
            Document doc = Jsoup.parse(input, "UTF-8", "https://slapi-app-production.sellerlabs.com/");
            for (Element row: doc.select("table tr")) {
                if (row.select("td:nth-of-type(1)").text().equals("")||row.select("td:nth-of-type(1)").text().equals("amazon-order-id")){
                    continue;
                }
                if (row.select("td:nth-of-type(1)").text().equals("")||row.select("td:nth-of-type(3)").text().equals("purchase-date")){
                    continue;
                }
                else {
                    final String orderId = row.select("td:nth-of-type(1)").text();
                    final String orderStatus = row.select("td:nth-of-type(5)").text();
                    final String inputDate = row.select("td:nth-of-type(3)").text();
                    String date = dateConverter(inputDate);
                    ReportEntry entry = new ReportEntry(orderId,orderStatus,date);

                    allReportEntries.put(orderId,entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allReportEntries;
    }

    public Parser() throws IOException {
    }

}
