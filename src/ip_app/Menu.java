package ip_app;
import java.util.Arrays;
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
                       //case "1" -> check_network();
                       case "2" -> param_of_ip();
                       case "3" -> subnetting();
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
           String ip_1 = "";
           String ip_2 = "";
           System.out.println("-------------------------ip_app.Menu-------------------------");
           System.out.println("Sprawdzenie czy podane adresy ip są w jednej sieci. ");
           System.out.println("------------------------------------------------------\n");

           System.out.println("Podaj pierwsze ip: ");

           System.out.print(">> ");
           ip_1 = scanner.nextLine();

           System.out.println("Podaj drugie ip: ");

           System.out.print(">> ");
           ip_2 = scanner.nextLine();

           if((ip_manager.f_network_address(ip_1)) == (ip_manager.f_network_address(ip_2)))
           {
               System.out.println("Podane ip są w jednej sieci.");
               System.out.println("->Adres sieci: " + ip_manager.f_network_address(ip_1));
           }else
           {
               System.out.println("Podane ip są w dwóch różnych sieciach.");
               System.out.println("->Adres sieci dla ip " + ip_1 + ": " + ip_manager.f_network_address(ip_1));
               System.out.println("->Adres sieci dla ip " + ip_2 + ": " + ip_manager.f_network_address(ip_2));
           }



       }

    public void bianry_calculator()
    {
        String ip = "";
        int[] binary = new int[8];

        System.out.println("------------------------------------------------------");
        System.out.println("--------------- Zamiana ip na binarny ----------------");
        System.out.println("--------- FORMAT: x.x.x.x, np: 192.168.10.5 ----------");
        System.out.println("------------------------------------------------------");
        System.out.println("Podaj ip: ");
        System.out.print(">> ");
        try{
            ip = scanner.nextLine();
            if(format.f_ipv4(ip))             //TODO: TUTAJ ZMIENIĆ IFA I DOROBIĆ FUNKCJE ZWRACAJCĄ TRUE LUB FALSE W PRZYPADKU ZŁĘGO PODANIA FORMATU, TRZEBA TEŻ ZROBIĆ KILKA FORMATÓW
                throw new myException.Input_Variable_Exception();
        }
        catch(myException.Input_Variable_Exception e)
        {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }


        System.out.println();
        System.out.println("ip num: " + ip);
        System.out.print("ip bin: ");

        for(int i=0; i < 4; i++) {
            binary = ip_manager.f_numeric_to_binary(ip_manager.f_ip_to_num(ip)[i]);

            for(int x: binary)
                System.out.print(x);

            System.out.print("  ");
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void param_of_ip()
    {
        String ip = "";

        System.out.println("------------------------------------------------------");
        System.out.println("---- Obliczanie parametrów sieci dla podanego ip -----");
        System.out.println("----- FORMAT: x.x.x.x/maska, np: 192.168.10.5/30 -----");
        System.out.println("------------------------------------------------------");
        System.out.println("Podaj ip/maska: ");
        System.out.print(">> ");
        ip = scanner.nextLine();

        System.out.println();
        System.out.println("Adres sieci: " + ip_manager.f_network_address(ip));
        System.out.println("Adres pierwszego hosta: " + ip_manager.f_host_nr(ip_manager.f_network_address(ip),1));
        System.out.println("Adres ostatniego hosta: " + ip_manager.f_host_nr(ip_manager.f_network_address(ip),ip_manager.f_host_quantity(ip)));
        System.out.println("Adres rozgłoszeniowy: " + ip_manager.f_broadcast_address(ip));
        System.out.println("Ilość hostów: " + ip_manager.f_host_quantity(ip));
        System.out.println();


    }

    public void subnetting()
    {
        String ip = "";
        int subnet_quantity = 1;


        System.out.println("------------------------------------------------------");
        System.out.println("---- Obliczanie podsieci dla podanych parametrów -----");
        System.out.println("----- FORMAT: x.x.x.x/maska, np: 192.168.10.5/30 -----");
        System.out.println("------------------------------------------------------");
        System.out.println("Podaj głowne ip/maska: ");
        System.out.print(">> ");
        ip = scanner.nextLine();
        System.out.println("Podaj ile potrzebujesz podsieci: ");
        System.out.print(">> ");
        subnet_quantity = scanner.nextInt();
        int[]tab_sub = new int [subnet_quantity];


        String[][] subnets = new String[subnet_quantity][7];

        for(int i = 0; i < subnet_quantity; i++)
        {

            System.out.println("Podaj ile hostów potrzebujesz dla sieci [" + (i+1) + "/" + subnet_quantity + "]: ");
            System.out.print(">> ");
            tab_sub[i]  = scanner.nextInt();
        }

        for(int i = 0; i < subnet_quantity; i++)
        {
            int hosts = Arrays.stream(tab_sub).max().getAsInt();
            int nr = ip_manager.findIndex(tab_sub, hosts);
            tab_sub[nr] = 0;

            subnets[i] = ip_manager.f_subnet_maker(ip, hosts, nr+1);

            System.out.println("ip b: " + ip);
            ip = subnets[i][7];

            System.out.println("ip a: " + ip);

        }

        ip_manager.f_write_out_subnet(subnets,subnet_quantity);


    }


}
