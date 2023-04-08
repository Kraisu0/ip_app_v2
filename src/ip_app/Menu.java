package ip_app;
import java.util.Scanner;


public class Menu
{

    Scanner scanner = new Scanner(System.in);

       public void display_menu()
       {
           String choice = null;
           boolean flag = true;

           while(flag)
           {
               System.out.println("-------------------------ip_app.Menu-------------------------");
               System.out.println("------------------------------------------------------");
               System.out.println("1 - Sprawdzenie czy podane ip są w jednej sieci.");
               System.out.println("2 - Obliczenie parametrów dla podanego ip.");
               System.out.println("3 - Dokonanie podziału sieci na podsieci. ");
               System.out.println("4 - Zmiana ip na binarny. ");
               System.out.println("\nq - Wyjście. ");
               System.out.println("------------------------------------------------------");
               System.out.print(">> ");
               choice = scanner.nextLine();
               System.out.println();

               try {
                   switch (choice) {
                       case "1" -> check_network();
                       case "4" -> bianry_calculator();
                       case "q" -> flag = false;
                       default -> throw new myException.Input_Variable_Exception();


                   }
               }catch(myException.Input_Variable_Exception e)
               {
                  System.out.println("Wystąpił błąd: " + e.getMessage());
              }

               System.out.println();
           }

           System.out.println("Koniec programu!");
       }

       public void check_network()
       {
           String x1 = null;
           String x2 = null;
           System.out.println("-------------------------ip_app.Menu-------------------------");
           System.out.println("Sprawdzenie czy podane adresy ip są w jednej sieci. ");
           System.out.println("------------------------------------------------------\n");

           System.out.println("Podaj pierwsze ip: ");

           System.out.print(">> ");
           x1 = scanner.nextLine();

           System.out.println("Podaj drugie ip: ");

           System.out.print(">> ");
           x2 = scanner.nextLine();

       }

    public void bianry_calculator()
    {
        String x1;
        int[] numeric = new int[4];
        int[] binary = new int[8];

        System.out.println("------------------------------------------------------");
        System.out.println("--------------- Zamiana ip na binarny ----------------");
        System.out.println("------------------------------------------------------");
        System.out.println("Podaj ip: ");
        System.out.print(">> ");
        x1 = scanner.nextLine();

        System.out.println();
        System.out.println("ip num: " + x1);
        System.out.print("ip bin: ");

        //String[] parts = x1.split("\\.");
        for(int i=0; i < 4; i++) {
            binary = ip_manager.f_numeric_to_binary(ip_manager.f_ip_to_num(x1)[i]);

            for(int x: binary)
                System.out.print(x);

            System.out.print("  ");
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

}
