import java.awt.Color;
import java.awt.Graphics;

public class Medium {

    boolean isun = false;

    int focus_point = 400;

    int ground = 250;

    int er = 0;

    int eg = 0;

    int eb = 0;

    int jumping = 0;

    int cx = 250;

    int cy = 150;

    int cz = 50;

    int xz = 0;

    int zy = 0;

    int x = 3000;

    int y = -1000;

    int z = -2000;

    int w = 500;

    int h = 360;

    int tart = 0;

    int yart = -100;

    int zart = 0;

    int ztgo = 0;

    boolean td = false;

    int vxz = 0;

    int adv = -500;

    boolean vert = false;

    public int ys(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - this.focus_point) * (this.cy - i) / j + i;
    }

    public void infront(ContO conto) {
        int i = conto.zy;
        int j;
        for (j = conto.xz; i > 360; i -= 360) {
            ;
        }
        while (i < 0) {
            i += 360;
        }
        if (i > 90 && i < 270) {
            this.tart += (180 - this.tart) / 3;
            this.yart += (100 - this.yart) / 3;
        } else {
            this.tart -= this.tart / 3;
            this.yart += (-100 - this.yart) / 3;
        }
        j += this.tart;
        if (i > 90) {
            i = 180 - i;
        }
        if (i < -90) {
            i = -180 - i;
        }
        int k = conto.y + (int) ((float) (conto.y + this.yart - conto.y) * SinCos.getcos(conto.zy) - (float) (conto.z + 800 - conto.z) * SinCos.getsin(conto.zy));
        int l = conto.z + (int) ((float) (conto.y + this.yart - conto.y) * SinCos.getsin(conto.zy) + (float) (conto.z + 800 - conto.z) * SinCos.getcos(conto.zy));
        int i1 = conto.x + (int) ((float) (-(l - conto.z)) * SinCos.getsin(conto.xz));
        int j1 = conto.z + (int) ((float) (l - conto.z) * SinCos.getcos(conto.xz));
        this.zy = i;
        this.xz = -(j + 180);
        this.x += (i1 - this.cx - this.x) / 3;
        this.z = (int) ((double) this.z + (double) (j1 - this.cz - this.z) / 1.5D);
        this.y = (int) ((double) this.y + (double) (k - this.cy - this.y) / 1.5D);
    }

    public void d(Graphics graphics) {
        if (this.zy > 90) {
            this.zy = 90;
        }
        if (this.zy < -90) {
            this.zy = -90;
        }
        if (this.y > 0) {
            this.y = 0;
        }
        this.ground = 250 - this.y;
        int i = 70000;
        int j = 250;
        if (this.zy != 0) {
            j = this.cy + (int) ((float) (250 - this.cy) * SinCos.getcos(this.zy) - (float) (70000 - this.cz) * SinCos.getsin(this.zy));
            i = this.cz + (int) ((float) (250 - this.cy) * SinCos.getsin(this.zy) + (float) (70000 - this.cz) * SinCos.getcos(this.zy));
        }
        int[] ints = new int[4];
        int[] ints2 = new int[4];
        ints[0] = 0;
        ints2[0] = 0;
        ints[1] = this.w;
        ints2[1] = 0;
        ints[2] = this.w;
        ints2[2] = this.ys(j, i);
        if (ints2[2] > this.h) {
            ints2[2] = this.h;
        }
        ints[3] = 0;
        ints2[3] = ints2[2];
        if (ints2[2] > 0) {
            if (this.jumping != 0) {
                if (this.jumping == 3) {
                    ints2[2] = this.h;
                    ints2[3] = this.h;
                    graphics.setColor(new Color(240, 240, 240));
                    graphics.fillPolygon(ints, ints2, 4);
                }
            } else {
                if (!this.isun) {
                    graphics.setColor(new Color(159 + 52 * this.er, 180 + 56 * this.eg, 189 + 58 * this.eb));
                } else {
                    graphics.setColor(new Color(159 + 52 * this.er, 176 + 56 * this.eg, 191 + 58 * this.eb));
                }
                graphics.fillPolygon(ints, ints2, 4);
            }
        }
        ints[0] = -1;
        ints2[0] = this.ys(j, i);
        if (ints2[0] < 0) {
            ints2[0] = -1;
        }
        ints[1] = -1;
        ints2[1] = this.h;
        ints[2] = this.w;
        ints2[2] = this.h;
        ints[3] = this.w;
        ints2[3] = ints2[0];
        if (ints2[0] < this.h && this.jumping == 0) {
            if (!this.isun) {
                graphics.setColor(new Color(177 + 55 * this.er, 154 + 50 * this.eg, 120 + 44 * this.eb));
            } else {
                graphics.setColor(new Color(175 + 55 * this.er, 151 + 50 * this.eg, 112 + 44 * this.eb));
            }
            graphics.fillPolygon(ints, ints2, 4);
            ints[1] = -1;
            ints2[1] = ints2[0];
            ints[0] = -1;
            ints2[0] -= 3;
            ints[2] = this.w;
            ints2[2] = ints2[1];
            ints[3] = this.w;
            ints2[3] = ints2[0];
            if (!this.isun) {
                graphics.setColor(new Color(169 + 55 * this.er, 171 + 50 * this.eg, 160 + 44 * this.eb));
            } else {
                graphics.setColor(new Color(167 + 55 * this.er, 164 + 50 * this.eg, 151 + 44 * this.eb));
            }
            graphics.fillPolygon(ints, ints2, 4);
        }
        if (this.jumping != 0) {
            this.jumping += -1;
        }
    }

    public void watch(ContO conto) {
        if (!this.td) {
            this.y = conto.y + (int) ((float) (conto.y - 300 - conto.y) * SinCos.getcos(conto.zy) - (float) (conto.z + 3000 - conto.z) * SinCos.getsin(conto.zy));
            int i = conto.z + (int) ((float) (conto.y - 300 - conto.y) * SinCos.getsin(conto.zy) + (float) (conto.z + 3000 - conto.z) * SinCos.getcos(conto.zy));
            this.x = conto.x + (int) ((float) (conto.x + 400 - conto.x) * SinCos.getcos(conto.xz) - (float) (i - conto.z) * SinCos.getsin(conto.xz));
            this.z = conto.z + (int) ((float) (conto.x + 400 - conto.x) * SinCos.getsin(conto.xz) + (float) (i - conto.z) * SinCos.getcos(conto.xz));
            this.td = true;
        }
        short s = 0;
        if (conto.x - this.x - this.cx > 0) {
            s = 180;
        }
        int j = -((int) ((double) (90 + s) + Math.atan((double) (conto.z - this.z) / (double) (conto.x - this.x - this.cx)) / 0.017453292519943295D));
        short s2 = 0;
        if (conto.y - this.y - this.cy < 0) {
            s2 = -180;
        }
        int k = (int) Math.sqrt((double) ((conto.z - this.z) * (conto.z - this.z) + (conto.x - this.x - this.cx) * (conto.x - this.x - this.cx)));
        int l = (int) ((double) (90 + s2) - Math.atan((double) k / (double) (conto.y - this.y - this.cy)) / 0.017453292519943295D);
        this.xz = j;
        this.zy += (l - this.zy) / 5;
        if ((int) Math.sqrt((double) ((conto.z - this.z) * (conto.z - this.z) + (conto.x - this.x - this.cx) * (conto.x - this.x - this.cx) + (conto.y - this.y - this.cy) * (conto.y - this.y - this.cy))) > 3500) {
            this.td = false;
        }
    }

    public void around(ContO conto, int i) {
        byte b = 1;
        if (i == 6000) {
            b = 2;
        }
        this.y = conto.y + this.adv;
        this.x = conto.x + (int) ((float) (conto.x - i + this.adv * b - conto.x) * SinCos.getcos(this.vxz));
        this.z = conto.z + (int) ((float) (conto.x - i + this.adv * b - conto.x) * SinCos.getsin(this.vxz));
        if (i == 6000) {
            if (!this.vert) {
                this.adv -= 10;
            } else {
                this.adv += 10;
            }
            if (this.adv < -900) {
                this.vert = true;
            }
            if (this.adv > 1200) {
                this.vert = false;
            }
        } else {
            if (!this.vert) {
                this.adv -= 2;
            } else {
                this.adv += 2;
            }
            if (this.adv < -500) {
                this.vert = true;
            }
            if (this.adv > 150) {
                this.vert = false;
            }
            if (this.adv > 300) {
                this.adv = 300;
            }
        }
        this.vxz += 2;
        if (this.vxz > 360) {
            this.vxz -= 360;
        }
        short s = 0;
        int j = this.y;
        if (j > 0) {
            j = 0;
        }
        if (conto.y - j - this.cy < 0) {
            s = -180;
        }
        int k = (int) Math.sqrt((double) ((conto.z - this.z) * (conto.z - this.z) + (conto.x - this.x - this.cx) * (conto.x - this.x - this.cx)));
        int l = (int) ((double) (90 + s) - Math.atan((double) k / (double) (conto.y - j - this.cy)) / 0.017453292519943295D);
        this.xz = -this.vxz + 90;
        this.zy += (l - this.zy) / 10;
    }

    public void left(ContO conto) {
        int i = conto.y;
        int j = conto.x + (int) ((float) (conto.x + 600 - conto.x) * SinCos.getcos(conto.xz));
        int k = conto.z + (int) ((float) (conto.x + 600 - conto.x) * SinCos.getsin(conto.xz));
        this.zy = 0;
        this.xz = -(conto.xz + 90);
        this.x = (int) ((double) this.x + (double) (j - this.cx - this.x) / 1.5D);
        this.z = (int) ((double) this.z + (double) (k - this.cz - this.z) / 1.5D);
        this.y = (int) ((double) this.y + (double) (i - this.cy - this.y) / 1.5D);
    }

    public void right(ContO conto) {
        int i = conto.y;
        int j = conto.x + (int) ((float) (conto.x - 600 - conto.x) * SinCos.getcos(conto.xz));
        int k = conto.z + (int) ((float) (conto.x - 600 - conto.x) * SinCos.getsin(conto.xz));
        this.zy = 0;
        this.xz = -(conto.xz - 90);
        this.x += (j - this.cx - this.x) / 3;
        this.z = (int) ((double) this.z + (double) (k - this.cz - this.z) / 1.5D);
        this.y = (int) ((double) this.y + (double) (i - this.cy - this.y) / 1.5D);
    }

    public void behinde(ContO conto) {
        int i = conto.zy;
        int j;
        for (j = conto.xz; i > 360; i -= 360) {
            ;
        }
        while (i < 0) {
            i += 360;
        }
        if (i > 90 && i < 270) {
            this.tart += (180 - this.tart) / 3;
            this.yart += (100 - this.yart) / 4;
        } else {
            this.tart -= this.tart / 3;
            this.yart += (-100 - this.yart) / 4;
        }
        j += this.tart;
        if (i > 90) {
            i = 180 - i;
        }
        if (i < -90) {
            i = -180 - i;
        }
        int k = conto.y + (int) ((float) (conto.y + this.yart - conto.y) * SinCos.getcos(conto.zy) - (float) (conto.z - 600 - conto.z) * SinCos.getsin(conto.zy));
        int l = conto.z + (int) ((float) (conto.y + this.yart - conto.y) * SinCos.getsin(conto.zy) + (float) (conto.z - 600 - conto.z) * SinCos.getcos(conto.zy));
        int i1 = conto.x + (int) ((float) (-(l - conto.z)) * SinCos.getsin(conto.xz));
        int j1 = conto.z + (int) ((float) (l - conto.z) * SinCos.getcos(conto.xz));
        this.zy = -i;
        this.xz = -j;
        this.x += (i1 - this.cx - this.x) / 3;
        this.z = (int) ((double) this.z + (double) (j1 - this.cz - this.z) / 1.5D);
        this.y = (int) ((double) this.y + (double) (k - this.cy - this.y) / 1.5D);
    }
}
