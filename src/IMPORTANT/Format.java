package IMPORTANT;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project name: HomeWork
 * Created by pavel on 25.08.17.
 */
public class Format {
    public static int GetIntFromString(String x) {
        String digit = new String();
        Pattern pat = Pattern.compile("[-]?[0-9]");
        Matcher matcher = pat.matcher(x);
        while (matcher.find()) {
            digit += matcher.group();
        }
        return Integer.parseInt(digit);
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(2);
        double v = 456;
        System.out.println(df.format(v));
    }
}
