public class SinCos {
    private static float[] tcos = new float[360];
    private static float[] tsin = new float[360];

    static {
        int i = 0;
        do {
            tcos[i] = (float) Math.cos(i * 0.017453292519943295D);
            ++i;
        } while (i < 360);
        i = 0;
        do {
            tsin[i] = (float) Math.sin(i * 0.017453292519943295D);
            ++i;
        } while (i < 360);
    }

    public static float sin(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        return tsin[i];
    }

    public static float cos(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        return tcos[i];
    }
}
