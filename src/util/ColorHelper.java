package util;

import java.awt.Color;

public class ColorHelper {
    public static Color getPrimaryColor() {
        return new Color(13, 110, 253);
    }
    public static Color getDarkerPrimaryColor() {
        return new Color(13, 110, 253).darker();
    }

    public static Color getInfoColor() {
        return new Color(13, 202, 240);
    }
    public static Color getDarkerInfoColor() {
        return new Color(13, 202, 240).darker();
    }
}
