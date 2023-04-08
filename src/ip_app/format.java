package ip_app;

import java.util.Arrays;

public class format {

    public static boolean f_ipv4(String ip)
    {
        String[] temp = ip.split("\\.");
        int[] parts = Arrays.stream(temp).mapToInt(Integer::parseInt).toArray();

        if(ip.length() < 7 || ip.length() >15)
            return true;
        else
        {
            if(parts[0] >= 0 && parts[0] <= 255 &&
                    parts[1] >= 0 && parts[1] <= 255 &&
                    parts[2] >= 0 && parts[2] <= 255 &&
                    parts[3] >= 0 && parts[3] <= 255)
                return false;
        }

        return true;
    }
}
