import java.util.Scanner;
import java.io.IOException;

/**
 A menu from the vending machine.
 */
class VendingMachineMenu {
   private Scanner in;
   private static Coin[] coins = { new Coin(0.05, "5 cent"),
           new Coin(0.1, "10 cent"),
           new Coin(0.5, "50 cent"),
           new Coin(1, "euro") };

   /**
    Constructs a VendingMachineMenu object
    */
   VendingMachineMenu() {
      in = new Scanner(System.in);
   }

   /**
    Runs the vending machine system.
    @param machine the vending machine
    */
   public void run(VendingMachine machine) throws IOException {
      boolean more = true;

      while (more) {
         System.out.println("S)how products  I)nsert coin  B)uy  A)dd product  R)emove coins  Q)uit");
         String command = in.nextLine().toUpperCase();

         switch (command) {
            case "S":   /*
         	getProductTypes() returns an array of products that doesn't contain duplicates
         	*/
               for (Product p : machine.getProductTypes())
                  System.out.println(p.getDescription());
               break;
            case "I": //allows one coin be inserted at a time
               machine.addCoin((Coin) getChoice(coins));
               break;
            case "R":
               System.out.println("Removed: " + machine.removeMoney());
               break;
            case "B":
               try {
                  Product p = (Product) getChoice(machine.getProductTypes());
                  if (machine.buyProduct(p))
                     System.out.println("Purchased: " + p);
                  else
                     new VendingException("Insufficient Funds!");
               } catch (VendingException ex) {
                  System.out.println(ex.getMessage());
               }
               break;
            case "A":
               System.out.println("Description:");
               String description = in.nextLine();
               System.out.println("Price:");
               double price = in.nextDouble();
               System.out.println("Quantity:");
               int quantity = in.nextInt();
               in.nextLine(); // read the new-line character

               machine.addProduct(new Product(description, price), quantity);
               break;
            case "Q":
               more = false;
               break;
         }
      }
   }

   private Object getChoice(Object[] choices) {
      while (true)
      {
         char c = 'A';
         for (Object choice : choices)
         {
            System.out.println(c + ") " + choice);
            c++;
         }
         String input = in.nextLine();
         int n = input.toUpperCase().charAt(0) - 'A';
         if (0 <= n && n < choices.length)
            return choices[n];
      }
   }
}
