import java.awt.color.ColorSpace;

public  class CIELab {

    public static int[] rgb2lab(int Re, int Gr, int Bl) {

        double r = Re / 255.0;
        double g = Gr / 255.0;
        double b = Bl / 255.0;

        // D65 standard referent
        double X = 0.9504, Y = 1.0, Z = 1.0888;
        // D50 standard referent
        //double X = 0.9642, Y = 1.0, Z = 0.8251;


        // second, map sRGB to CIE XYZ
        r = r <= 0.04045 ? r / 12.92 : Math.pow((r + 0.055) / 1.055, 2.4);
        g = g <= 0.04045 ? g / 12.92 : Math.pow((g + 0.055) / 1.055, 2.4);
        b = b <= 0.04045 ? b / 12.92 : Math.pow((b + 0.055) / 1.055, 2.4);
        double x = (0.4124564 * r + 0.3575761 * g + 0.1804375 * b) / X,
                y = (0.2126729 * r + 0.7151522 * g + 0.0721750 * b) / Y,
                z = (0.0193339 * r + 0.1191920 * g + 0.9503041 * b) / Z;

        // third, map CIE XYZ to CIE L*a*b* and return
        x = x > 0.008856 ? Math.pow(x, 1.0 / 3) : 7.787037 * x + 4.0 / 29;
        y = y > 0.008856 ? Math.pow(y, 1.0 / 3) : 7.787037 * y + 4.0 / 29;
        z = z > 0.008856 ? Math.pow(z, 1.0 / 3) : 7.787037 * z + 4.0 / 29;

        int L = (int) Math.round(116 * y - 16),
                A = (int) Math.round(500 * (x - y)),
                B = (int) Math.round(200 * (y - z));

      /*  if (binSize > 0) {
            L = binSize * Math.floor(L / binSize);
            A = binSize * Math.floor(A / binSize);
            B = binSize * Math.floor(B / binSize);
        }*/

        return new int[]{L, A, B};
    }
}