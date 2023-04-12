package ip_app;

import java.util.Arrays;

public class format {

    public static boolean f_ipv4(String ip)
    {


        String[] temp = ip.split("\\.");
        //int[] parts = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();
        int[] parts = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            parts[i] = Integer.parseInt(temp[i]);
        }

        if(ip.length() < 7 || ip.length() >15)
        {
            System.out.println("Działa return true.");
            return true;
        }
        else
        {
            if(parts[0] >= 0 && parts[0] <= 255 &&
                    parts[1] >= 0 && parts[1] <= 255 &&
                    parts[2] >= 0 && parts[2] <= 255 &&
                    parts[3] >= 0 && parts[3] <= 255)
            {
                System.out.println("Działa return flase.");
                return false;
            }
        }

        System.out.println("Nie działa return true.");
        return true;
    }
}
