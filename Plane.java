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
        projf = 1.0F;
        int i = 0;
        do {
            int j = 0;
            do {
                if (j != i) {
                    projf *= (float) (Math.sqrt((double) ((ox[i] - ox[j]) * (ox[i] - ox[j]) + (oz[i] - oz[j]) * (oz[i] - oz[j]))) / 100.0D);
                }
                ++j;
            } while (j < 3);
            ++i;
        } while (i < 3);
        projf /= 3.0F;
    }

    public int ys(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - m.focus_point) * (m.cy - i) / j + i;
    }

    public Plane(Medium var1, int[] var2, int[] var3, int[] var4, int var5, int[] var6) {
        m = var1;
        n = var5;
        ox = new int[n];
        oz = new int[n];
        oy = new int[n];
        int var7;
        for (var7 = 0; var7 < n; ++var7) {
            ox[var7] = var2[var7];
            oy[var7] = var4[var7];
            oz[var7] = var3[var7];
        }
        var7 = 0;
        do {
            c[var7] = var6[var7];
            ++var7;
        } while (var7 < 3);
        var7 = 0;
        do {
            int var8 = 0;
            do {
                if (var8 != var7) {
                    deltaf *= (float) (Math.sqrt((double) ((ox[var8] - ox[var7]) * (ox[var8] - ox[var7]) + (oy[var8] - oy[var7]) * (oy[var8] - oy[var7]) + (oz[var8] - oz[var7]) * (oz[var8] - oz[var7]))) / 100.0D);
                }
                ++var8;
            } while (var8 < 3);
            ++var7;
        } while (var7 < 3);
        deltaf /= 3.0F;
    }

    public void d(Graphics graphics, int i, int j, int k, int l, int i1, int j1, boolean flag, boolean flag2, boolean flag3) {
        if (exp != 7) {
            int[] ints = new int[n];
            int[] ints2 = new int[n];
            int[] ints3 = new int[n];
            int k1;
            for (k1 = 0; k1 < n; ++k1) {
                ints[k1] = ox[k1] + i;
                ints3[k1] = oy[k1] + j;
                ints2[k1] = oz[k1] + k;
            }
            rot(ints, ints3, i, j, i1, n);
            rot(ints3, ints2, j, k, j1, n);
            rot(ints, ints2, i, k, l, n);
            if (exp == 2) {
                sdx = (int) (Math.random() * 100.0D - 50.0D);
                sdz = (int) (Math.random() * 100.0D - 50.0D);
                sdy = Math.random() * 100.0D - 50.0D;
                sx[0] = ofcx + ints[nx] + 2 - i;
                sx[1] = ofcx + ints[nx] - 2 - i;
                sy[0] = ofcy + ints3[ny] + 2 - j;
                sy[1] = ofcy + ints3[ny] - 2 - j;
                sz[0] = ofcz + ints2[nx] + 2 - k;
                sz[1] = ofcz + ints2[nx] - 2 - k;
                sx[2] = sx[1] - sdx;
                sx[3] = sx[0] - sdx;
                sy[2] = (int) ((double) sy[1] - sdy);
                sy[3] = (int) ((double) sy[0] - sdy);
                sz[2] = sz[1] - sdz;
                sz[3] = sz[0] - sdz;
                sr = 255;
                sg = 220;
                exp = 3;
            }
            int[] ints4;
            int[] ints5;
            int l2;
            int i2;
            if (exp != 0) {
                ofx += adx;
                ofz += adz;
                ofy += (int) ady;
                for (k1 = 0; k1 < n; ++k1) {
                    ints[k1] += ofx;
                    ints2[k1] += ofz;
                    ints3[k1] += ofy;
                }
                rot(ints3, ints2, ofcy + ints3[ny], ofcz + ints2[nx], ezy, n);
                rot(ints, ints3, ofcx + ints[nx], ofcy + ints3[ny], exy, n);
                for (k1 = 0; k1 < n; ++k1) {
                    if (ints3[k1] > m.ground) {
                        exp = 7;
                    }
                }
                ezy += azy;
                exy += axy;
                ady += 0.5D;
                if (sy[3] < m.ground) {
                    int[] ints6 = new int[4];
                    ints4 = new int[4];
                    ints5 = new int[4];
                    l2 = 0;
                    do {
                        if (exp < 6) {
                            ints6[l2] = sx[l2] + i + (int) (Math.random() * 50.0D - 25.0D);
                            ints4[l2] = sy[l2] + j + (int) (Math.random() * 50.0D - 25.0D);
                            ints5[l2] = sz[l2] + k + (int) (Math.random() * 50.0D - 25.0D);
                            if (exp >= 4) {
                                ++exp;
                            }
                        } else {
                            ints6[l2] = sx[l2] + i;
                            ints4[l2] = sy[l2] + j;
                            ints5[l2] = sz[l2] + k;
                        }
                        sx[l2] += sdx;
                        sy[l2] = (int) ((double) sy[l2] + sdy);
                        sz[l2] += sdz;
                        ++l2;
                    } while (l2 < 4);
                    sdy += 0.5D;
                    rot(ints6, ints5, m.cx, m.cz, m.xz, 4);
                    rot(ints4, ints5, m.cy, m.cz, m.zy, 4);
                    int[] ints7 = new int[4];
                    int[] ints8 = new int[4];
                    boolean flag4 = false;
                    i2 = 0;
                    do {
                        ints7[i2] = xs(ints6[i2], ints5[i2]);
                        ints8[i2] = ys(ints4[i2], ints5[i2]);
                        if (ints8[i2] > 0 && ints8[i2] < m.h && ints7[i2] > 0 && ints7[i2] < m.w && ints5[i2] > 10 && ints4[i2] < m.ground) {
                            flag4 = true;
                        }
                        ++i2;
                    } while (i2 < 4);
                    if (flag4 && sr > 111) {
                        graphics.setColor(new Color(sr, sg, 111));
                        if (exp == 3) {
                            graphics.setColor(new Color(255, 255, 255));
                            exp = 4;
                        }
                        graphics.fillPolygon(ints7, ints8, 4);
                        if (sr > 111) {
                            sr -= 2;
                        }
                        if (sg > 111) {
                            sg -= 2;
                        }
                    }
                }
            }
            if (i1 != 0 || j1 != 0 || exp != 0 || l != 0) {
                projf = 1.0F;
                k1 = 0;
                do {
                    int j2 = 0;
                    do {
                        if (j2 != k1) {
                            projf *= (float) (Math.sqrt((double) ((ints[k1] - ints[j2]) * (ints[k1] - ints[j2]) + (ints2[k1] - ints2[j2]) * (ints2[k1] - ints2[j2]))) / 100.0D);
                        }
                        ++j2;
                    } while (j2 < 3);
                    ++k1;
                } while (k1 < 3);
                projf /= 3.0F;
            }
            rot(ints, ints2, m.cx, m.cz, m.xz, n);
            boolean flag5 = false;
            ints4 = new int[n];
            ints5 = new int[n];
            l2 = 500;
            int k2;
            for (k2 = 0; k2 < n; ++k2) {
                ints4[k2] = xs(ints[k2], ints2[k2]);
                ints5[k2] = ys(ints3[k2], ints2[k2]);
            }
            k2 = 0;
            int l3 = 1;
            int i3;
            for (i2 = 0; i2 < n; ++i2) {
                for (i3 = 0; i3 < n; ++i3) {
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
            if (spy(ints[k2], ints2[k2]) > spy(ints[l3], ints2[l3])) {
                flag5 = true;
                i2 = 0;
                for (i3 = 0; i3 < n; ++i3) {
                    if (ints2[i3] < 50 && ints3[i3] > m.cy) {
                        flag5 = false;
                    } else if (ints3[i3] == ints3[0]) {
                        ++i2;
                    }
                }
                if (i2 == n && ints3[0] > m.cy) {
                    flag5 = false;
                }
            }
            rot(ints3, ints2, m.cy, m.cz, m.zy, n);
            boolean flag6 = true;
            boolean flag7 = false;
            int[] ints9 = new int[n];
            int[] ints10 = new int[n];
            int j3 = 0;
            int k3 = 0;
            int l4 = 0;
            int i4 = 0;
            int j4 = 0;
            int k4;
            for (k4 = 0; k4 < n; ++k4) {
                ints9[k4] = xs(ints[k4], ints2[k4]);
                ints10[k4] = ys(ints3[k4], ints2[k4]);
                if (ints10[k4] < 0 || ints2[k4] < 10) {
                    ++j3;
                }
                if (ints10[k4] > m.h || ints2[k4] < 10) {
                    ++k3;
                }
                if (ints9[k4] < 0 || ints2[k4] < 10) {
                    ++l4;
                }
                if (ints9[k4] > m.w || ints2[k4] < 10) {
                    ++i4;
                }
                if (ints2[k4] > 50000) {
                    ++j4;
                }
            }
            if (l4 == n || j3 == n || k3 == n || i4 == n || j4 == n) {
                flag6 = false;
            }
            if (l4 != 0 || j3 != 0 || k3 != 0 || i4 != 0 || ints2[0] > 2000) {
                flag7 = true;
            }
            if (flag6) {
                float f = (float) ((double) (projf / deltaf) + 0.5D);
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
                    if (m.er == 0) {
                        l5 = (int) ((float) c[0] * f);
                    } else {
                        l5 = c[0];
                    }
                    if (l5 > 225) {
                        l5 = 225;
                    }
                    if (m.eg == 0) {
                        i5 = (int) ((float) c[1] * f);
                    } else {
                        i5 = c[1];
                    }
                    if (i5 > 225) {
                        i5 = 225;
                    }
                    if (m.eb == 0) {
                        j5 = (int) ((float) c[2] * f);
                    } else {
                        j5 = c[2];
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
                    graphics.fillPolygon(ints9, ints10, n);
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
                    graphics.drawPolygon(ints9, ints10, n);
                }
            }
            av = 0;
            for (k4 = 0; k4 < n; ++k4) {
                av += (int) Math.sqrt((double) ((m.cy - ints10[k4]) * (m.cy - ints10[k4]) + (m.cx - ints9[k4]) * (m.cx - ints9[k4]) + ints2[k4] * ints2[k4]));
            }
            av /= n;
        }
    }

    public void rot(int[] ints, int[] ints2, int i, int j, int k, int l) {
        if (k != 0) {
            for (int i1 = 0; i1 < l; ++i1) {
                int j1 = ints[i1];
                int k1 = ints2[i1];
                ints[i1] = i + (int) ((float) (j1 - i) * SinCos.getcos(k) - (float) (k1 - j) * SinCos.getsin(k));
                ints2[i1] = j + (int) ((float) (j1 - i) * SinCos.getsin(k) + (float) (k1 - j) * SinCos.getcos(k));
            }
        }
    }

    public int xs(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - m.focus_point) * (m.cx - i) / j + i;
    }

    public void s(Graphics graphics, int i, int j, int k, int l, int i1, int j1, boolean flag) {
        if (exp != 7) {
            int[] ints = new int[n];
            int[] ints2 = new int[n];
            int[] ints3 = new int[n];
            int k1;
            for (k1 = 0; k1 < n; ++k1) {
                ints[k1] = ox[k1] + i;
                ints3[k1] = oy[k1] + j;
                ints2[k1] = oz[k1] + k;
            }
            rot(ints, ints3, i, j, i1, n);
            rot(ints3, ints2, j, k, j1, n);
            rot(ints, ints2, i, k, l, n);
            if (exp == 1) {
                adx = (int) (Math.random() * 30.0D - 15.0D);
                adz = (int) (Math.random() * 30.0D - 15.0D);
                ady = -(Math.random() * 20.0D);
                ofcx = (int) (Math.random() * 10.0D - 5.0D);
                ofcy = (int) (Math.random() * 10.0D - 5.0D);
                ofcz = (int) (Math.random() * 10.0D - 5.0D);
                nx = (int) (Math.random() * (double) n);
                ny = (int) (Math.random() * (double) n);
                nz = (int) (Math.random() * (double) n);
                azy = (int) (Math.random() * 30.0D - 15.0D);
                axy = (int) (Math.random() * 30.0D - 15.0D);
                exy = 0;
                ezy = 0;
                ofx = 0;
                ofy = 0;
                ofz = 0;
                exp = 2;
            }
            if (exp != 0) {
                ofx += adx;
                ofz += adz;
                ofy += (int) ady;
                for (k1 = 0; k1 < n; ++k1) {
                    ints[k1] += ofx;
                    ints2[k1] += ofz;
                    ints3[k1] += ofy;
                }
                rot(ints3, ints2, ofcy + ints3[ny], ofcz + ints2[nz], ezy, n);
                rot(ints, ints3, ofcx + ints[nx], ofcy + ints3[nx], exy, n);
            }
            k1 = 0;
            for (int l2 = 0; l2 < n; ++l2) {
                if (ints3[l2] >= m.ground) {
                    ++k1;
                } else {
                    ints3[l2] = m.ground;
                }
            }
            if (k1 != n) {
                rot(ints, ints2, m.cx, m.cz, m.xz, n);
                rot(ints3, ints2, m.cy, m.cz, m.zy, n);
                boolean flag2 = false;
                int[] ints4 = new int[n];
                int[] ints5 = new int[n];
                for (int i2 = 0; i2 < n; ++i2) {
                    ints4[i2] = xs(ints[i2], ints2[i2]);
                    ints5[i2] = ys(ints3[i2], ints2[i2]);
                    if (ints5[i2] > 0 && ints5[i2] < m.h && ints4[i2] > 0 && ints4[i2] < m.w && ints2[i2] > 10 && ints2[i2] < 50000) {
                        flag2 = true;
                    }
                }
                if (flag2) {
                    if (!flag) {
                        graphics.setColor(new Color(60, 54, 42));
                    } else {
                        graphics.setColor(new Color(60, 60, 60));
                    }
                    graphics.fillPolygon(ints4, ints5, n);
                }
            }
        }
    }

    public int spy(int i, int j) {
        return (int) Math.sqrt((double) ((i - m.cx) * (i - m.cx) + j * j));
    }
}
