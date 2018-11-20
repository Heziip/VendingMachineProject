public class Operator {
    private String type;
    private int code;

    public Operator(String type, int code) {
        this.type = type;
        this.code = code;
    }

    protected String getCSV() {
        return type + "," + code;
    }
}