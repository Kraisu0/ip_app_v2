package ip_app;

public class ip_manager {


    /**
     * Funckja pomocnicza zamieniajaca wartosc numeryczna na binarna
     * @param num tablica z wartoscią num, którą chcemy zamienić
     * @param value wielokrotonsc 2
     * @return wartosc jednego bitu
     */
    public static int f_binary_change(int[] num, int value)
    {
        int num1 = num[0] % value;
        if(num1 < num[0]) {
            num[0] = num1;
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Funckja pomocnicza zamieniajaca wartosc numeryczna na binarna w wersji long
     * @param num tablica z wartoscią num, którą chcemy zamienić
     * @param value wielokrotonsc 2
     * @return wartosc jednego bitu
     */
    public static int f_binary_change_long(long[] num, long value)
    {
        long num1 = num[0] % value;
        if(num1 < num[0]) {
            num[0] = num1;
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Funckja zamieniajaca liczbe w liczbe binarny 4x8
     * @param num numer do zamiany
     * @return tablica binarna
     */
    public static int[][] f_numeric_to_binary_long(long num)
    {
        int[][] tab_temp = new int[4][8];
        long[] x = {num};
        long pow_two = 2147483648L;

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 8; j++, pow_two=pow_two/2)
            {
                tab_temp[i][j] = f_binary_change_long(x, pow_two);
            }
        }

        return tab_temp;
    }

    /**
     * Funckja zamieniąca maske sleszową na numeryczną
     * @param mask maska w postaci sleszowej bez slesza
     * @return stringa w postaci maski numerycznej
     */
    public static String f_mask_change(int mask)
    {

        int[][] mask_bin = new int[4][8];
        String resault = "";

        for(int i = 0;i<4;i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(mask > 0)
                    mask_bin[i][j] = 1;
                else
                    mask_bin[i][j] = 0;

                mask--;
            }
        }

        for(int i = 0;i<4;i++)
        {
            int temp = f_bin_to_num(mask_bin[i]);
            resault += temp;

            if(i < 3)
                resault += ".";
        }

        return resault;
    }

    /**
     * Funckja zamieniajaca wartosc binarną na numeryczną
     * @param bin oktet binarny w formie tablicy intów
     * @return wartość numeryczna
     */
    public static int f_bin_to_num(int bin[])
    {
        int num = 0;

        for(int i = 0, x = 128; i < 8; i++, x=x/2)
        {
            num += f_check_value(bin[i], x);
        }

        return num;
    }

    /**
     * Funckja pomocnicza sprawdza wartosc binarna na numeryczna
     * @param bin wartosc binarna
     * @param value wielokrotność 2
     * @return wartosc wspiwywana do sumy numerycznej
     */
    public static int f_check_value(int bin, int value)
    {

        if(bin == 1)
            return value;
        else
            return 0;

    }


    /**
     * Funckja zamieniajaca wartosc numeryczna na binarna
     * @param number wartosć numeryczna
     * @return jeden oktet binarnie w formie tablciy intów
     */
    public static int[] f_numeric_to_binary(int number)
    {
        int[] num = {number};
        int[] binary = new int[8];

        for(int i = 0, x = 128; i < 8; i++, x=x/2)
        {
            binary[i] = f_binary_change(num, x);
        }

        return binary;
    }

    /**
     * Funckja zwraca numeryczną maske z ip
     * @param ip podane ip
     * @return maska w postaci sleszowej
     */
    public static int f_get_mask(String ip)
    {
        String[] parts = ip.split("\\/");

        return  Integer.parseInt(parts[1]);
    }

    /**
     * Funckja zwraca numeryczne ip
     * @param ip podane ip
     * @return ip w postaci numerycznej
     */
    public static String f_get_ip(String ip)
    {
        String[] parts = ip.split("\\/");

        return  parts[0];
    }

    /**
     * Funkcja zamienia ip string (x.x.x.x) na tablice int
     * @param ip wejsciowej ip po kropkach
     * @return tablcie int z tymi wartosciami
     */
    public static int[] f_ip_to_num(String ip)
    {
        int[] new_ip = new int[4];
        String[] parts = ip.split("\\.");

        for(int i=0; i < 4; i++) {
            new_ip[i] = Integer.parseInt(parts[i]);
        }

        return new_ip;
    }

    /**
     * Funckja zwraca adres sieci
     * @param ip_full adres ip gdzie szukamy adresu sieci
     * @return adres sieci
     */
    public static String f_network_address(String ip_full)
    {
        String mask = f_mask_change(f_get_mask(ip_full));
        String ip = f_get_ip(ip_full);
        String result = "";

        int[][] tab_mask = new int [4][8];
        int[][] tab_ip = new int [4][8];
        int[][] tab_result = new int [4][8];

        for(int i = 0; i < 4; i++)
        {
            tab_mask[i] = f_numeric_to_binary(f_ip_to_num(mask)[i]);
            tab_ip[i] = f_numeric_to_binary(f_ip_to_num(ip)[i]);

            for(int j = 0; j < 8; j++)
            {
                tab_result[i][j]=f_masking(tab_ip[i][j], tab_mask[i][j]);
            }

            result += f_bin_to_num(tab_result[i]);
            if(i < 3)
                result += ".";
        }

        result += "/" + f_get_mask(ip_full);

        return result;
    }

    /**
     * Funckja zwraca adres rozgłoszeniowy
     * @param ip_full adres ip gdzie szukamy adresu rozgłoszeniowego
     * @return adres rozgłoszeniowy
     */
    public static String f_broadcast_address(String ip_full)
    {
        String mask = f_mask_change(f_get_mask(ip_full));
        String ip = f_get_ip(ip_full);
        String result = "";

        int[][] tab_mask = new int [4][8];
        int[][] tab_ip = new int [4][8];
        int[][] tab_result = new int [4][8];

        for(int i = 0; i < 4; i++)
        {
            tab_mask[i] = f_numeric_to_binary(f_ip_to_num(mask)[i]);
            tab_ip[i] = f_numeric_to_binary(f_ip_to_num(ip)[i]);

            tab_mask[i] = f_change_0_to_1(tab_mask[i]);

            for(int j = 0; j < 8; j++)
            {
                tab_result[i][j]=f_or(tab_ip[i][j], tab_mask[i][j]);
            }

            result += f_bin_to_num(tab_result[i]);
            if(i < 3)
                result += ".";
        }

        return result;
    }

    /**
     * Funkcja zwraca ip podanegto numeru hosta
     * @param ip_full ip sieci
     * @param nr_host nr szukanego hosta
     * @return adres numeru szukanego hosta
     */
    public static String f_host_nr(String ip_full, long nr_host)
    {

        String ip = f_get_ip(ip_full);
        String result = "";

        int[][] tab_ip = new int [4][8];
        int[][] tab_result = new int [4][8];
        int[][] tab_host = new int [4][8];

        tab_host = f_numeric_to_binary_long(nr_host);

        for(int i = 0; i < 4; i++)
        {
            tab_ip[i] = f_numeric_to_binary(f_ip_to_num(ip)[i]);

            for(int j = 0; j < 8; j++)
            {
                tab_result[i][j]=f_or(tab_ip[i][j], tab_host[i][j]);
            }

            result += f_bin_to_num(tab_result[i]);
            if(i < 3)
                result += ".";
        }

        result += "/" + f_get_mask(ip_full);

        return result;
    }

    /**
     * Funkcja wykonujaca operacje OR na bitach
     * @param first_value bit pierwszy
     * @param second_value bit drugi
     * @return zwraca odpowiedni bit
     */
    public static int f_or(int first_value, int second_value)
    {
        if(first_value == 0 && second_value == 0)
            return 0;
        else
            return 1;
    }

    /**
     * Funkcja wykonujaca operacje AND na bitach
     * @param first_value bit pierwszy
     * @param second_value bit drugi
     * @return zwraca odpowiedni bit
     */
    public static int f_and(int first_value, int second_value)
    {
        if(first_value == 1 && second_value == 1)
            return 1;
        else
            return 0;
    }

    /**
     * Funkcja wykonujaca operacje maskowania na bitach
     * @param first_value bit pierwszy
     * @param second_value bit drugi
     * @return zwraca odpowiedni bit
     */
    public static int f_masking(int first_value, int second_value)
    {
        if(second_value == 1)
            return first_value;
        else
            return 0;
    }

    /**
     * Funckaj obliczajaca liczbę hostów w danej sieci
     * @param ip ip sieci
     * @return ilosc dostępnych hostów w danej sieci
     */
    public static long f_host_quantity(String ip)
    {
        return (long)(Math.pow(2,(32-f_get_mask(ip)))-2);
    }

    /**
     * Funkcja wykonujaca operacje NOT na bitach
     * @param value tablica bitów
     * @return zanegowana tablica bitów
     */
    public static int[] f_change_0_to_1(int[] value)
    {
        int[] temp = new int[8];


        for(int j = 0; j < 8; j++)
        {
            if(value[j] == 1)
                temp[j] = 0;
            else
                temp[j] = 1;
        }


        return temp;
    }

    //public static void f_



}
