import java.util.ArrayList;
import java.util.List;

/**
 A set of coins.
 */
public class CoinSet {
    private ArrayList<Coin> set;

    /**
     Constructs a CoinSet object.
     */
    CoinSet() {
        set = new ArrayList<Coin>();
    }

    void addCoin(Coin choice) {
        set.add(choice);
    }

    double getTotal() {
        double total = 0;
        for (Coin c : set) {
            total += c.getValue();
        }
        return total;
    }

    public double removeAmount(double price) {
        return getTotal() - price;
    }

    public void setSet(ArrayList<Coin> set) {
        this.set = set;
    }

    double removeCoinSet() {
        double result = 0;
        for(Coin c: set)
        {
            result += c.getValue();
        }
        set.clear();
        return result;
    }

    protected List<Coin> getCoins() {
        return set;
    }

    @Override
    public String toString() {
        return "CoinSet{" +
                "set=" + getTotal() +
                '}';
    }

    void empty() {
        set.clear();
    }
}