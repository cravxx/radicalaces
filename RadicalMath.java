public class RadicalMath {

    static float[] tcos = new float[360];

    static float[] tsin = new float[360];

    static {
        for (int i = 0; i < 360; i++) {
            tcos[i] = (float) Math.cos((double) i * 0.017453292519943295D);
            tsin[i] = (float) Math.sin((double) i * 0.017453292519943295D);
        }
    }
    
    private RadicalMath() {
        
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
