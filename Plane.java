import java.awt.Color;
import java.awt.Graphics;

public class Plane {

    Medium m;

    int[] ox;

    int[] oy;

    int[] oz;

    int n;

    int[] c = new int[3];

    float deltaf = 1.0F;

    float projf = 1.0F;

    int av = 0;

    int exp = 0;

    int ofx = 0;

    int adx = 0;

    int ofy = 0;

    int adz = 0;

    int ofz = 0;

    double ady = 0.0D;

    int ofcx = 0;

    int ofcy = 0;

    int ofcz = 0;

    int nx = 0;

    int ny = 0;

    int nz = 0;

    int ezy = 0;

    int exy = 0;

    int azy = 0;

    int axy = 0;

    int[] sx = new int[4];

    int[] sy = new int[4];

    int[] sz = new int[4];

    int sdx = 0;

    int sdz = 0;

    double sdy = 0.0D;

    int sr = 255;

    int sg = 220;

    public void loadprojf() {
        this.projf = 1.0F;
        int i = 0;
        do {
            int j = 0;
            do {
                if (j != i) {
                    this.projf *= (float) (Math.sqrt((double) ((this.ox[i] - this.ox[j]) * (this.ox[i] - this.ox[j]) + (this.oz[i] - this.oz[j]) * (this.oz[i] - this.oz[j]))) / 100.0D);
                }
                ++j;
            } while (j < 3);
            ++i;
        } while (i < 3);
        this.projf /= 3.0F;
    }

    public int ys(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - this.m.focus_point) * (this.m.cy - i) / j + i;
    }

    public  Plane(Medium var1, int[] var2, int[] var3, int[] var4, int var5, int[] var6) {
        this.m = var1;
        this.n = var5;
        this.ox = new int[this.n];
        this.oz = new int[this.n];
        this.oy = new int[this.n];
        int var7;
        for (var7 = 0; var7 < this.n; ++var7) {
            this.ox[var7] = var2[var7];
            this.oy[var7] = var4[var7];
            this.oz[var7] = var3[var7];
        }
        var7 = 0;
        do {
            this.c[var7] = var6[var7];
            ++var7;
        } while (var7 < 3);
        var7 = 0;
        do {
            int var8 = 0;
            do {
                if (var8 != var7) {
                    this.deltaf *= (float) (Math.sqrt((double) ((this.ox[var8] - this.ox[var7]) * (this.ox[var8] - this.ox[var7]) + (this.oy[var8] - this.oy[var7]) * (this.oy[var8] - this.oy[var7]) + (this.oz[var8] - this.oz[var7]) * (this.oz[var8] - this.oz[var7]))) / 100.0D);
                }
                ++var8;
            } while (var8 < 3);
            ++var7;
        } while (var7 < 3);
        this.deltaf /= 3.0F;
    }

    public void d(Graphics graphics, int i, int j, int k, int l, int i1, int j1, boolean flag, boolean flag2, boolean flag3) {
        if (this.exp != 7) {
            int[] ints = new int[this.n];
            int[] ints2 = new int[this.n];
            int[] ints3 = new int[this.n];
            int k1;
            for (k1 = 0; k1 < this.n; ++k1) {
                ints[k1] = this.ox[k1] + i;
                ints3[k1] = this.oy[k1] + j;
                ints2[k1] = this.oz[k1] + k;
            }
            this.rot(ints, ints3, i, j, i1, this.n);
            this.rot(ints3, ints2, j, k, j1, this.n);
            this.rot(ints, ints2, i, k, l, this.n);
            if (this.exp == 2) {
                this.sdx = (int) (Math.random() * 100.0D - 50.0D);
                this.sdz = (int) (Math.random() * 100.0D - 50.0D);
                this.sdy = Math.random() * 100.0D - 50.0D;
                this.sx[0] = this.ofcx + ints[this.nx] + 2 - i;
                this.sx[1] = this.ofcx + ints[this.nx] - 2 - i;
                this.sy[0] = this.ofcy + ints3[this.ny] + 2 - j;
                this.sy[1] = this.ofcy + ints3[this.ny] - 2 - j;
                this.sz[0] = this.ofcz + ints2[this.nx] + 2 - k;
                this.sz[1] = this.ofcz + ints2[this.nx] - 2 - k;
                this.sx[2] = this.sx[1] - this.sdx;
                this.sx[3] = this.sx[0] - this.sdx;
                this.sy[2] = (int) ((double) this.sy[1] - this.sdy);
                this.sy[3] = (int) ((double) this.sy[0] - this.sdy);
                this.sz[2] = this.sz[1] - this.sdz;
                this.sz[3] = this.sz[0] - this.sdz;
                this.sr = 255;
                this.sg = 220;
                this.exp = 3;
            }
            int[] ints4;
            int[] ints5;
            int l2;
            int i2;
            if (this.exp != 0) {
                this.ofx += this.adx;
                this.ofz += this.adz;
                this.ofy += (int) this.ady;
                for (k1 = 0; k1 < this.n; ++k1) {
                    ints[k1] += this.ofx;
                    ints2[k1] += this.ofz;
                    ints3[k1] += this.ofy;
                }
                this.rot(ints3, ints2, this.ofcy + ints3[this.ny], this.ofcz + ints2[this.nx], this.ezy, this.n);
                this.rot(ints, ints3, this.ofcx + ints[this.nx], this.ofcy + ints3[this.ny], this.exy, this.n);
                for (k1 = 0; k1 < this.n; ++k1) {
                    if (ints3[k1] > this.m.ground) {
                        this.exp = 7;
                    }
                }
                this.ezy += this.azy;
                this.exy += this.axy;
                this.ady += 0.5D;
                if (this.sy[3] < this.m.ground) {
                    int[] ints6 = new int[4];
                    ints4 = new int[4];
                    ints5 = new int[4];
                    l2 = 0;
                    do {
                        if (this.exp < 6) {
                            ints6[l2] = this.sx[l2] + i + (int) (Math.random() * 50.0D - 25.0D);
                            ints4[l2] = this.sy[l2] + j + (int) (Math.random() * 50.0D - 25.0D);
                            ints5[l2] = this.sz[l2] + k + (int) (Math.random() * 50.0D - 25.0D);
                            if (this.exp >= 4) {
                                ++this.exp;
                            }
                        } else {
                            ints6[l2] = this.sx[l2] + i;
                            ints4[l2] = this.sy[l2] + j;
                            ints5[l2] = this.sz[l2] + k;
                        }
                        this.sx[l2] += this.sdx;
                        this.sy[l2] = (int) ((double) this.sy[l2] + this.sdy);
                        this.sz[l2] += this.sdz;
                        ++l2;
                    } while (l2 < 4);
                    this.sdy += 0.5D;
                    this.rot(ints6, ints5, this.m.cx, this.m.cz, this.m.xz, 4);
                    this.rot(ints4, ints5, this.m.cy, this.m.cz, this.m.zy, 4);
                    int[] ints7 = new int[4];
                    int[] ints8 = new int[4];
                    boolean flag4 = false;
                    i2 = 0;
                    do {
                        ints7[i2] = this.xs(ints6[i2], ints5[i2]);
                        ints8[i2] = this.ys(ints4[i2], ints5[i2]);
                        if (ints8[i2] > 0 && ints8[i2] < this.m.h && ints7[i2] > 0 && ints7[i2] < this.m.w && ints5[i2] > 10 && ints4[i2] < this.m.ground) {
                            flag4 = true;
                        }
                        ++i2;
                    } while (i2 < 4);
                    if (flag4 && this.sr > 111) {
                        graphics.setColor(new Color(this.sr, this.sg, 111));
                        if (this.exp == 3) {
                            graphics.setColor(new Color(255, 255, 255));
                            this.exp = 4;
                        }
                        graphics.fillPolygon(ints7, ints8, 4);
                        if (this.sr > 111) {
                            this.sr -= 2;
                        }
                        if (this.sg > 111) {
                            this.sg -= 2;
                        }
                    }
                }
            }
            if (i1 != 0 || j1 != 0 || this.exp != 0 || l != 0) {
                this.projf = 1.0F;
                k1 = 0;
                do {
                    int j2 = 0;
                    do {
                        if (j2 != k1) {
                            this.projf *= (float) (Math.sqrt((double) ((ints[k1] - ints[j2]) * (ints[k1] - ints[j2]) + (ints2[k1] - ints2[j2]) * (ints2[k1] - ints2[j2]))) / 100.0D);
                        }
                        ++j2;
                    } while (j2 < 3);
                    ++k1;
                } while (k1 < 3);
                this.projf /= 3.0F;
            }
            this.rot(ints, ints2, this.m.cx, this.m.cz, this.m.xz, this.n);
            boolean flag5 = false;
            ints4 = new int[this.n];
            ints5 = new int[this.n];
            l2 = 500;
            int k2;
            for (k2 = 0; k2 < this.n; ++k2) {
                ints4[k2] = this.xs(ints[k2], ints2[k2]);
                ints5[k2] = this.ys(ints3[k2], ints2[k2]);
            }
            k2 = 0;
            int l3 = 1;
            int i3;
            for (i2 = 0; i2 < this.n; ++i2) {
                for (i3 = 0; i3 < this.n; ++i3) {
                    if (i2 != i3 && Math.abs(ints4[i2] - ints4[i3]) - Math.abs(ints5[i2] - ints5[i3]) < l2) {
                        l3 = i2;
                        k2 = i3;
                        l2 = Math.abs(ints4[i2] - ints4[i3]) - Math.abs(ints5[i2] - ints5[i3]);
                    }
                }
            }
            if (ints5[k2] < ints5[l3]) {
                i2 = k2;
                k2 = l3;
                l3 = i2;
            }
            if (this.spy(ints[k2], ints2[k2]) > this.spy(ints[l3], ints2[l3])) {
                flag5 = true;
                i2 = 0;
                for (i3 = 0; i3 < this.n; ++i3) {
                    if (ints2[i3] < 50 && ints3[i3] > this.m.cy) {
                        flag5 = false;
                    } else if (ints3[i3] == ints3[0]) {
                        ++i2;
                    }
                }
                if (i2 == this.n && ints3[0] > this.m.cy) {
                    flag5 = false;
                }
            }
            this.rot(ints3, ints2, this.m.cy, this.m.cz, this.m.zy, this.n);
            boolean flag6 = true;
            boolean flag7 = false;
            int[] ints9 = new int[this.n];
            int[] ints10 = new int[this.n];
            int j3 = 0;
            int k3 = 0;
            int l4 = 0;
            int i4 = 0;
            int j4 = 0;
            int k4;
            for (k4 = 0; k4 < this.n; ++k4) {
                ints9[k4] = this.xs(ints[k4], ints2[k4]);
                ints10[k4] = this.ys(ints3[k4], ints2[k4]);
                if (ints10[k4] < 0 || ints2[k4] < 10) {
                    ++j3;
                }
                if (ints10[k4] > this.m.h || ints2[k4] < 10) {
                    ++k3;
                }
                if (ints9[k4] < 0 || ints2[k4] < 10) {
                    ++l4;
                }
                if (ints9[k4] > this.m.w || ints2[k4] < 10) {
                    ++i4;
                }
                if (ints2[k4] > 32) {
                    ++j4;
                }
            }
            if (l4 == this.n || j3 == this.n || k3 == this.n || i4 == this.n || j4 == this.n) {
                flag6 = false;
            }
            if (l4 != 0 || j3 != 0 || k3 != 0 || i4 != 0 || ints2[0] > 2000) {
                flag7 = true;
            }
            if (flag6) {
                float f = (float) ((double) (this.projf / this.deltaf) + 0.5D);
                if ((double) f > 1.2D) {
                    f = 1.2F;
                }
                if (!flag3) {
                    if ((double) f < 0.5D || flag5) {
                        f = 0.5F;
                    }
                } else if ((double) f < 0.9D || flag5) {
                    f = 0.9F;
                }
                int l5;
                int i5;
                int j5;
                if (!flag) {
                    if (this.m.er == 0) {
                        l5 = (int) ((float) this.c[0] * f);
                    } else {
                        l5 = this.c[0];
                    }
                    if (l5 > 225) {
                        l5 = 225;
                    }
                    if (this.m.eg == 0) {
                        i5 = (int) ((float) this.c[1] * f);
                    } else {
                        i5 = this.c[1];
                    }
                    if (i5 > 225) {
                        i5 = 225;
                    }
                    if (this.m.eb == 0) {
                        j5 = (int) ((float) this.c[2] * f);
                    } else {
                        j5 = this.c[2];
                    }
                    if (j5 > 225) {
                        j5 = 225;
                    }
                } else {
                    l5 = (int) (400.0F * f);
                    if (l5 > 255) {
                        l5 = 255;
                    }
                    i5 = (int) (400.0F * f);
                    if (i5 > 255) {
                        i5 = 255;
                    }
                    j5 = (int) (400.0F * f);
                    if (j5 > 255) {
                        j5 = 255;
                    }
                }
                graphics.setColor(new Color(l5, i5, j5));
                if (!flag2) {
                    graphics.fillPolygon(ints9, ints10, this.n);
                }
                if (!flag7) {
                    if (!flag2) {
                        l5 -= 15;
                        if (l5 < 0) {
                            l5 = 0;
                        }
                        i5 -= 15;
                        if (i5 < 0) {
                            i5 = 0;
                        }
                        j5 -= 15;
                        if (j5 < 0) {
                            j5 = 0;
                        }
                        graphics.setColor(new Color(l5, i5, j5));
                    } else {
                        graphics.setColor(new Color(l5 / 2, (i5 + 255) / 2, j5 / 2));
                    }
                    graphics.drawPolygon(ints9, ints10, this.n);
                }
            }
            this.av = 0;
            for (k4 = 0; k4 < this.n; ++k4) {
                this.av += (int) Math.sqrt((double) ((this.m.cy - ints10[k4]) * (this.m.cy - ints10[k4]) + (this.m.cx - ints9[k4]) * (this.m.cx - ints9[k4]) + ints2[k4] * ints2[k4]));
            }
            this.av /= this.n;
        }
    }

    public void rot(int[] ints, int[] ints2, int i, int j, int k, int l) {
        if (k != 0) {
            for (int i1 = 0; i1 < l; ++i1) {
                int j1 = ints[i1];
                int k1 = ints2[i1];
                ints[i1] = i + (int) ((float) (j1 - i) * this.m.cs.getcos(k) - (float) (k1 - j) * this.m.cs.getsin(k));
                ints2[i1] = j + (int) ((float) (j1 - i) * this.m.cs.getsin(k) + (float) (k1 - j) * this.m.cs.getcos(k));
            }
        }
    }

    public int xs(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - this.m.focus_point) * (this.m.cx - i) / j + i;
    }

    public void s(Graphics graphics, int i, int j, int k, int l, int i1, int j1, boolean flag) {
        if (this.exp != 7) {
            int[] ints = new int[this.n];
            int[] ints2 = new int[this.n];
            int[] ints3 = new int[this.n];
            int k1;
            for (k1 = 0; k1 < this.n; ++k1) {
                ints[k1] = this.ox[k1] + i;
                ints3[k1] = this.oy[k1] + j;
                ints2[k1] = this.oz[k1] + k;
            }
            this.rot(ints, ints3, i, j, i1, this.n);
            this.rot(ints3, ints2, j, k, j1, this.n);
            this.rot(ints, ints2, i, k, l, this.n);
            if (this.exp == 1) {
                this.adx = (int) (Math.random() * 30.0D - 15.0D);
                this.adz = (int) (Math.random() * 30.0D - 15.0D);
                this.ady = -(Math.random() * 20.0D);
                this.ofcx = (int) (Math.random() * 10.0D - 5.0D);
                this.ofcy = (int) (Math.random() * 10.0D - 5.0D);
                this.ofcz = (int) (Math.random() * 10.0D - 5.0D);
                this.nx = (int) (Math.random() * (double) this.n);
                this.ny = (int) (Math.random() * (double) this.n);
                this.nz = (int) (Math.random() * (double) this.n);
                this.azy = (int) (Math.random() * 30.0D - 15.0D);
                this.axy = (int) (Math.random() * 30.0D - 15.0D);
                this.exy = 0;
                this.ezy = 0;
                this.ofx = 0;
                this.ofy = 0;
                this.ofz = 0;
                this.exp = 2;
            }
            if (this.exp != 0) {
                this.ofx += this.adx;
                this.ofz += this.adz;
                this.ofy += (int) this.ady;
                for (k1 = 0; k1 < this.n; ++k1) {
                    ints[k1] += this.ofx;
                    ints2[k1] += this.ofz;
                    ints3[k1] += this.ofy;
                }
                this.rot(ints3, ints2, this.ofcy + ints3[this.ny], this.ofcz + ints2[this.nz], this.ezy, this.n);
                this.rot(ints, ints3, this.ofcx + ints[this.nx], this.ofcy + ints3[this.nx], this.exy, this.n);
            }
            k1 = 0;
            for (int l2 = 0; l2 < this.n; ++l2) {
                if (ints3[l2] >= this.m.ground) {
                    ++k1;
                } else {
                    ints3[l2] = this.m.ground;
                }
            }
            if (k1 != this.n) {
                this.rot(ints, ints2, this.m.cx, this.m.cz, this.m.xz, this.n);
                this.rot(ints3, ints2, this.m.cy, this.m.cz, this.m.zy, this.n);
                boolean flag2 = false;
                int[] ints4 = new int[this.n];
                int[] ints5 = new int[this.n];
                for (int i2 = 0; i2 < this.n; ++i2) {
                    ints4[i2] = this.xs(ints[i2], ints2[i2]);
                    ints5[i2] = this.ys(ints3[i2], ints2[i2]);
                    if (ints5[i2] > 0 && ints5[i2] < this.m.h && ints4[i2] > 0 && ints4[i2] < this.m.w && ints2[i2] > 10 && ints2[i2] < 32) {
                        flag2 = true;
                    }
                }
                if (flag2) {
                    if (!flag) {
                        graphics.setColor(new Color(60, 54, 42));
                    } else {
                        graphics.setColor(new Color(60, 60, 60));
                    }
                    graphics.fillPolygon(ints4, ints5, this.n);
                }
            }
        }
    }

    public int spy(int i, int j) {
        return (int) Math.sqrt((double) ((i - this.m.cx) * (i - this.m.cx) + j * j));
    }
}
