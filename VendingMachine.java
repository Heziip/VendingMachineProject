import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 A vending machine.
 */
class VendingMachine {
    private final String productFileName = "stock.csv";
    private final String moneyFileName = "money.csv";
    private final String operatorFileName = "operators.csv";
    private ArrayList<Product> products;
    private CoinSet coins;
    private CoinSet currentCoins;
    private List<Operator> operators = new ArrayList<>();

    /**
     Constructs a VendingMachine object.
     */
    VendingMachine() {
        products = new ArrayList<Product>();
        coins = new CoinSet();
        currentCoins = new CoinSet();
    }

    void loadProducts() {
        File f = new File(productFileName);
        if (f.isFile()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for(int i = 0; i < Integer.parseInt(values[2]); i++){
                    products.add(new Product(values[0], Double.parseDouble(values[1]), Integer.parseInt(values[2])));
                }
            }
            scanner.close();
        } else {
            try {
                f.createNewFile();
                System.out.println("Created new " + productFileName);
            } catch (IOException e) {
                System.out.println("Failed to create: " + productFileName + "\n" + e.getStackTrace());
            }
        }
    }

    void loadMoney() {
        File f = new File(moneyFileName);
        if (f.isFile()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                coins.addCoin(new Coin(values[0], Double.parseDouble(values[1]), Integer.parseInt(values[2])));
            }
            scanner.close();
        } else {
            try {
                f.createNewFile();
                System.out.println("Created new " + moneyFileName);
            } catch (IOException e) {
                System.out.println("Failed to create: " + moneyFileName + "\n" + e.getStackTrace());
            }
        }
    }

    void loadOperator() {
        File f = new File(operatorFileName);
        if (f.isFile()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                operators.add(new Operator(values[0], Integer.parseInt(values[2])));
            }
            scanner.close();
        } else {
            try {
                f.createNewFile();
                System.out.println("Created new " + operatorFileName);
            } catch (IOException e) {
                System.out.println("Failed to create: " + operatorFileName + "\n" + e.getStackTrace());
            }
        }
    }

    double removeMoney() {
        return coins.removeCoinSet();
    }

    Product[] getProductTypes() {
        Set<Product> s = new HashSet<>(products);
        Product[] prods = new Product[s.size()];
        return s.toArray(prods);
    }

    void addCoin(Coin choice) {
        currentCoins.addCoin(choice);
        coins.addCoin(choice);
    }

    boolean buyProduct(Product p) {
        double total = currentCoins.getTotal();
        boolean sufficientCoins;

        if (p.getPrice() <= currentCoins.getTotal()) {
            products.remove(products.lastIndexOf(p));
            currentCoins.empty();
            sufficientCoins = true;
            //p.getQuantity() -= 1;
        } else {
            System.out.println("Insufficient Coins!");
            sufficientCoins = false;
        }

        return sufficientCoins;
    }

    void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
    }

    void writeProducts() {
        PrintWriter pw;
        Set<Product> s = new LinkedHashSet<>(products);
        try {
            pw = new PrintWriter(productFileName);
            for (Product p : s) {
                pw.println(p.getCSV());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to create: " + productFileName + "\n" + e.getStackTrace());
        }
    }

    void writeMoney() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(moneyFileName);
            for (Coin c : currentCoins.getCoins()) {
                pw.println(c.getCSV());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to create: " + moneyFileName + "\n" + e.getStackTrace());
        }
    }

    void writeOperators() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(operatorFileName);
            for (Operator o : operators) {
                pw.println(o.getCSV());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to create: " + operatorFileName + "\n" + e.getStackTrace());
        }
    }
}