import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class ContO {

    Medium m;

    Plane[] p;

    int npl = 0;

    int x = 0;

    int y = 0;

    int z = 0;

    int xz = 0;

    int xy = 0;

    int zy = 0;

    int dist = 0;

    int maxR = 0;

    int disp = 0;

    boolean shadow = false;

    boolean loom = false;

    int grounded = 1;

    boolean colides = false;

    int rcol = 0;

    int pcol = 0;

    boolean out = false;

    boolean fire = false;

    boolean hit = false;

    int nhits = 0;

    int maxhits = -1;

    boolean wire = false;

    boolean exp = false;

    public int ys(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - m.focus_point) * (m.cy - i) / j + i;
    }

    public void reset() {
        exp = false;
        nhits = 0;
        xz = 0;
        xy = 0;
        zy = 0;
    }

    public ContO(byte[] b, Medium medium, int tx, int ty, int tz) {
        m = medium;
        p = new Plane[100];
        x = tx;
        y = ty;
        z = tz;
        boolean var8 = false;
        int var9 = 0;
        float var10 = 1.0F;
        int[] var11 = new int[100];
        int[] var12 = new int[100];
        int[] var13 = new int[100];
        int[] var14 = new int[] { 50, 50, 50 };
        try {
            DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(b));
            String var6;
            while ((var6 = datainputstream.readLine()) != null) {
                String var7 = "" + var6.trim();
                if (var7.startsWith("<p>")) {
                    var8 = true;
                    var9 = 0;
                }
                if (var8) {
                    if (var7.startsWith("c")) {
                        var14[0] = getvalue("c", var7, 0);
                        var14[1] = getvalue("c", var7, 1);
                        var14[2] = getvalue("c", var7, 2);
                    }
                    if (var7.startsWith("p")) {
                        var11[var9] = (int) ((float) getvalue("p", var7, 0) * var10);
                        var12[var9] = (int) ((float) getvalue("p", var7, 1) * var10);
                        var13[var9] = (int) ((float) getvalue("p", var7, 2) * var10);
                        ++var9;
                    }
                }
                if (var7.startsWith("</p>")) {
                    p[npl] = new Plane(m, var11, var13, var12, var9, var14);
                    ++npl;
                    var8 = false;
                }
                if (var7.startsWith("MaxRadius")) {
                    maxR = getvalue("MaxRadius", var7, 0);
                }
                if (var7.startsWith("disp")) {
                    disp = getvalue("disp", var7, 0);
                }
                if (var7.startsWith("shadow")) {
                    shadow = true;
                }
                if (var7.startsWith("loom")) {
                    loom = true;
                }
                if (var7.startsWith("out")) {
                    out = true;
                }
                if (var7.startsWith("hits")) {
                    maxhits = getvalue("hits", var7, 0);
                }
                if (var7.startsWith("colid")) {
                    colides = true;
                    rcol = getvalue("colid", var7, 0);
                    pcol = getvalue("colid", var7, 1);
                }
                if (var7.startsWith("grounded")) {
                    grounded = getvalue("grounded", var7, 0);
                }
                if (var7.startsWith("div")) {
                    var10 = (float) getvalue("div", var7, 0) / 10.0F;
                }
            }
            datainputstream.close();
        } catch (Exception var16) {
            ;
        }
    }

    public ContO(Medium var1, ContO var2, int var3, int var4, int var5) {
        m = var1;
        npl = var2.npl;
        maxR = var2.maxR;
        disp = var2.disp;
        loom = var2.loom;
        colides = var2.colides;
        maxhits = var2.maxhits;
        out = var2.out;
        rcol = var2.rcol;
        pcol = var2.pcol;
        shadow = var2.shadow;
        grounded = var2.grounded;
        p = new Plane[var2.npl];
        x = var3;
        y = var4;
        z = var5;
        for (int var6 = 0; var6 < npl; ++var6) {
            p[var6] = new Plane(m, var2.p[var6].ox, var2.p[var6].oz, var2.p[var6].oy, var2.p[var6].n, var2.p[var6].c);
        }
    }

    public void d(Graphics graphics) {
        if (dist != 0) {
            dist = 0;
        }
        int i = 0;
        int j;
        for (j = 0; j < npl; ++j) {
            if (!exp) {
                if (p[j].exp != 0) {
                    p[j].exp = 0;
                }
            } else if (p[j].exp == 0) {
                p[j].exp = 1;
            } else if (p[j].exp == 7) {
                ++i;
            }
        }
        if (!out && i != npl) {
            if (fire) {
                dist = 1;
            }
            j = m.cx + (int) ((float) (x - m.x - m.cx) * m.cs.getcos(m.xz) - (float) (z - m.z - m.cz) * m.cs.getsin(m.xz));
            int k = m.cz + (int) ((float) (x - m.x - m.cx) * m.cs.getsin(m.xz) + (float) (z - m.z - m.cz) * m.cs.getcos(m.xz));
            int l = m.cz + (int) ((float) (y - m.y - m.cy) * m.cs.getsin(m.zy) + (float) (k - m.cz) * m.cs.getcos(m.zy));
            if (xs(j + maxR, l) > 0 && xs(j - maxR, l) < m.w && l > -maxR && l < '썐' && xs(j + maxR, l) - xs(j - maxR, l) > disp || exp) {
                int i1;
                int j1;
                if (shadow || exp) {
                    i1 = m.cy + (int) ((float) (m.ground - m.cy) * m.cs.getcos(m.zy) - (float) (k - m.cz) * m.cs.getsin(m.zy));
                    int k1 = m.cz + (int) ((float) (m.ground - m.cy) * m.cs.getsin(m.zy) + (float) (k - m.cz) * m.cs.getcos(m.zy));
                    if (ys(i1 + maxR, k1) > 0 && ys(i1 - maxR, k1) < m.h || exp) {
                        for (j1 = 0; j1 < npl; ++j1) {
                            p[j1].s(graphics, x - m.x, y - m.y, z - m.z, xz, xy, zy, loom);
                        }
                    }
                }
                i1 = m.cy + (int) ((float) (y - m.y - m.cy) * m.cs.getcos(m.zy) - (float) (k - m.cz) * m.cs.getsin(m.zy));
                if (ys(i1 + maxR, l) > 0 && ys(i1 - maxR, l) < m.h || exp) {
                    if (m.jumping != 0 && m.jumping < 4) {
                        hit = true;
                    }
                    int[] ints = new int[npl];
                    int l2;
                    for (j1 = 0; j1 < npl; ++j1) {
                        ints[j1] = 0;
                        for (l2 = 0; l2 < npl; ++l2) {
                            if (p[j1].av != p[l2].av) {
                                if (p[j1].av < p[l2].av) {
                                    ++ints[j1];
                                }
                            } else if (j1 > l2) {
                                ++ints[j1];
                            }
                        }
                    }
                    for (j1 = 0; j1 < npl; ++j1) {
                        for (l2 = 0; l2 < npl; ++l2) {
                            if (ints[l2] == j1) {
                                p[l2].d(graphics, x - m.x, y - m.y, z - m.z, xz, xy, zy, hit, wire, loom);
                            }
                        }
                    }
                    dist = (int) Math.sqrt((double) ((int) Math.sqrt((double) ((m.x + m.cx - x) * (m.x + m.cx - x) + (m.z - z) * (m.z - z) + (m.y + m.cy - y) * (m.y + m.cy - y))))) * grounded;
                }
            }
        }
        if (hit) {
            hit = false;
            if (m.jumping == 0 && nhits > maxhits) {
                exp = true;
            }
        }
    }

    public void tryexp(ContO conto) {
        if (!conto.exp && !out && !exp) {
            int i = getpy(conto.x, conto.y, conto.z);
            if (i < maxR / 10 * (maxR / 10) + conto.maxR / 10 * (conto.maxR / 10) && i > 0) {
                if (pcol != 0) {
                    for (int j = 0; j < npl; ++j) {
                        for (int k = 0; k < p[j].n; ++k) {
                            if ((conto.x - (x + p[j].ox[k])) * (conto.x - (x + p[j].ox[k])) + (conto.y - (y + p[j].oy[k])) * (conto.y - (y + p[j].oy[k])) + (conto.z - (z + p[j].oz[k])) * (conto.z - (z + p[j].oz[k])) < conto.maxR * 10 / pcol * (conto.maxR * 10 / pcol)) {
                                conto.exp = true;
                                break;
                            }
                        }
                    }
                }
                if (rcol != 0 && i < maxR / (10 * rcol) * (maxR / (10 * rcol)) + conto.maxR / 10 * (conto.maxR / 10)) {
                    conto.exp = true;
                }
            }
        }
    }

    public int getpy(int i, int j, int k) {
        return (i - x) / 10 * ((i - x) / 10) + (j - y) / 10 * ((j - y) / 10) + (k - z) / 10 * ((k - z) / 10);
    }

    public void loadrots(boolean flag) {
        if (!flag) {
            reset();
        }
        for (int i = 0; i < npl; ++i) {
            p[i].rot(p[i].ox, p[i].oy, 0, 0, xy, p[i].n);
            p[i].rot(p[i].oy, p[i].oz, 0, 0, zy, p[i].n);
            p[i].rot(p[i].ox, p[i].oz, 0, 0, xz, p[i].n);
            p[i].loadprojf();
        }
        if (flag) {
            reset();
        }
    }

    public int getvalue(String string, String string2, int i) {
        int j = 0;
        String string3 = "";
        for (int k = string.length() + 1; k < string2.length(); ++k) {
            String string4 = "" + string2.charAt(k);
            if (string4.equals(",") || string4.equals(")")) {
                ++j;
                ++k;
            }
            if (j == i) {
                string3 = string3 + string2.charAt(k);
            }
        }
        return Integer.valueOf(string3).intValue();
    }

    public int xs(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - m.focus_point) * (m.cx - i) / j + i;
    }
}
