import java.io.IOException;

/**
 This program simulates a vending machine.
 */
public class VendingMachineSimulation {
    public static void main(String[] args) throws IOException {
        VendingMachine machine = new VendingMachine();
        VendingMachineMenu menu = new VendingMachineMenu();

        machine.loadProducts();
        machine.loadMoney();
        machine.loadOperator();

        menu.run(machine);

        machine.writeProducts();
        machine.writeMoney();
        machine.writeOperators();
    }
}
