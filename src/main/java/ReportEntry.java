public class ReportEntry {
    private String orderId;
    private String status;
    private String date;

    public ReportEntry(String orderId, String status, String date) {
        this.orderId = orderId;
        this.status = status;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
