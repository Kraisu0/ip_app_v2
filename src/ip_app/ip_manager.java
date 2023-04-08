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


}
