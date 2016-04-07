public class SinCos {

    float[] tcos = new float[360];

    float[] tsin = new float[360];

    public  SinCos() {
        int var1 = 0;
        do {
            this.tcos[var1] = (float) Math.cos((double) var1 * 0.017453292519943295D);
            ++var1;
        } while (var1 < 360);
        var1 = 0;
        do {
            this.tsin[var1] = (float) Math.sin((double) var1 * 0.017453292519943295D);
            ++var1;
        } while (var1 < 360);
    }

    public float getsin(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        return this.tsin[i];
    }

    public float getcos(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        return this.tcos[i];
    }
}
