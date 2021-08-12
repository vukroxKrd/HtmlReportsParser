import java.io.IOException;
import java.util.*;

public class ParseProcessor {
    public ArrayList<String> ordersContainer = new ArrayList<>();
    public HashMap<String, String> ordersAndStatusContainer = new HashMap<>();

    public static void printOrdersNumber(List<String>orders){
        System.out.println("Total orders count: " + orders.size());
    }
    public static void printOrdersAndStatus(HashMap<String, String> ordersAndStatus){
        System.out.println("Will be printing orders and status info...");
        int pendingCounter = 0;
        int cancelledCounter = 0;
        int shippedCounter = 0;
        int shippingCounter = 0;

        for (Map.Entry<String, String> entry : ordersAndStatus.entrySet()) {
            if (entry.getValue().equals("Pending")){
                pendingCounter++;
            }
            if (entry.getValue().equals("Cancelled")) {
                cancelledCounter++;
            }
            if (entry.getValue().equals("Shipped")){
                shippedCounter++;
            }
            if (entry.getValue().equals("Shipping")){
                shippingCounter++;
            }
            //To find out what statuses exist:
//                if (!entry.getValue().equals("Shipped")
//                        && !entry.getValue().equals("Cancelled")
//                        && !entry.getValue().equals("Pending")
//                        && !entry.getValue().equals("Shipping")) {
//                    System.out.println(entry.getValue());
//                }
        }
        System.out.println("Pending count:"+pendingCounter);
        System.out.println("Cancelled count:"+cancelledCounter);
        System.out.println("Shipped count:"+shippedCounter);
        System.out.println("Shipping count:"+shippingCounter);

        int shippedAndPending = shippedCounter+pendingCounter;
        System.out.println("Shipped and pending count: " + shippedAndPending);
    }
    public static void printOrdersStatusAndDate(Map<String,ReportEntry> entries){

        System.out.println("Will be printing orders with status and dates info...");

        int pendingCounter = 0;
        int cancelledCounter = 0;
        int shippedCounter = 0;
        int shippingCounter = 0;
        Set<String> distinctDates = new TreeSet<>();


        for (Map.Entry<String, ReportEntry> reportEntry : entries.entrySet()) {
            ReportEntry next = reportEntry.getValue();
            distinctDates.add(next.getDate());

            if (next.getStatus().equals("Pending")){
                pendingCounter++;
            }
            else if (next.getStatus().equals("Cancelled")) {
                cancelledCounter++;
            }else if (next.getStatus().equals("Shipped")){
                shippedCounter++;
            }else if (next.getStatus().equals("Shipping")){
                shippingCounter++;
            }
            //To find out what statuses exist:
//                if (!entry.getValue().equals("Shipped")
//                        && !entry.getValue().equals("Cancelled")
//                        && !entry.getValue().equals("Pending")
//                        && !entry.getValue().equals("Shipping")) {
//                    System.out.println(entry.getValue());
//                }
        }
        System.out.println("Pending count:"+pendingCounter);
        System.out.println("Cancelled count:"+cancelledCounter);
        System.out.println("Shipped count:"+shippedCounter);
        System.out.println("Shipping count:"+shippingCounter);

        int shippedAndPending = shippedCounter+pendingCounter;
        System.out.println("Shipped and pending count: " + shippedAndPending);
        System.out.println("Dates available in the Report: ");
        distinctDates.forEach(System.out::println);
    }
}
