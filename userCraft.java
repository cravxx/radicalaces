import java.awt.Graphics;

/**
 * usercraft
 * @author Omar Waly
 */
public class userCraft {

	public int rspeed = 0;

	public float speed = 0.0F;

    private int rlift = 0;

    private double lift = 0.0D;

    private boolean pexp = false;

    public int ltyp = 0;

    /**
     * max flying speed of plane
     */
    public static int maxspeed[] = { 
    		120, 100, 90, 80, 76
    };

    /**
     * responsiveness of the craft
     */
    public static int elev[] = { 
    		1, 2, 1, 1, 1 
    };

    /**
     * turning speed of craft
     */
    public static int trnn[] = { 
    		0, 0, 1, 2, 1 
    };

    /**
     * number of light speed jumps craft has
     */
    public static int dnjm[] = { 
    		7, 5, 4, 3, 4 
    };

    /**
     * craft names
     */
    public static String name[] = { 
    		"E-7 Sky Bullet", "BP-6 Hammer Head", "E-9 Dragon Bird", "EXA-1 Destroyer", "Silver F-51 Legend" 
    };

    public int njumps = 0;

    public int ester = 0;

    private int[] lx = new int[20];

    private int[] ly = new int[20];

    private int[] lz = new int[20];

    private int[] lxz = new int[20];

    private int[] lzy = new int[20];

    private int[] lxy = new int[20];

    private int[] lstage = new int[20];

    private int[] lspeed = new int[20];

    private int[] lhit = new int[20];

    private int nl = 0;

    private Lasers lsr;

    private boolean skip = false;

    private int bulkc = 0;

    public int[] sms = new int[4];

    private int[] sx = new int[4];

    private int[] sy = new int[4];

    private int[] sz = new int[4];

    private int[] sxz = new int[4];

    private int[] szy = new int[4];

    private int ns = 0;

    private boolean smoke = false;

    private int[] dms = new int[4];

    private int[] dx = new int[4];

    private int[] dy = new int[4];

    private int[] dz = new int[4];

    private int[] dxz = new int[4];

    private int[] dzy = new int[4];

    private int nd = 0;

    public void preform(Control control, ContO conto, ContO[] contos, int[] ints, int i) {
        int j;
        for (j = Math.abs(conto.zy); j > 360; j -= 360) {
            ;
        }
        byte b = 1;
        if (j > 90 && j < 270) {
            b = -1;
        }
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l2;
        if (conto.y < 207) {
            if (control.up) {
                conto.zy -= (int) ((float) (4 + elev[ltyp]) * RadicalMath.cos(conto.xy));
                conto.xz += (int) ((float) (b * (2 + elev[ltyp])) * RadicalMath.sin(conto.xy));
            }
            if (control.down) {
                conto.zy += (int) ((float) (4 + elev[ltyp]) * RadicalMath.cos(conto.xy));
                conto.xz -= (int) ((float) (b * (2 + elev[ltyp])) * RadicalMath.sin(conto.xy));
            }
        } else {
            for (k = Math.abs(conto.zy); k > 90; k -= 180) {
                ;
            }
            for (l = Math.abs(conto.xy); l > 90; l -= 180) {
                ;
            }
            for (i1 = Math.abs(conto.zy); i1 > 270; i1 -= 360) {
                ;
            }
            for (j1 = Math.abs(conto.xy); j1 > 270; j1 -= 360) {
                ;
            }
            boolean flag = Math.abs(i1) < 90 && Math.abs(j1) < 90 || Math.abs(i1) > 90 && Math.abs(j1) > 90;
            boolean flag2 = Math.abs(k) > 30 || Math.abs(l) > 30;
            if ((!flag || flag2) && !conto.exp) {
                conto.exp = true;
                conto.y = 170;
                speed = 30.0F;
                pexp = true;
            }
            for (k1 = Math.abs(conto.zy); k1 > 270; k1 -= 360) {
                ;
            }
            if (k1 > 90) {
                conto.xy = 180;
            } else {
                conto.xy = 0;
            }
            for (l2 = conto.zy; l2 > 90; l2 -= 180) {
                ;
            }
            while (l2 < -90) {
                l2 += 180;
            }
            if (l2 > 0) {
                conto.zy += -1;
                smoke = true;
            }
            if (l2 < 0) {
                ++conto.zy;
                smoke = true;
            }
            if (speed > 10.0F && control.down) {
                conto.zy += (int) (5.0F * RadicalMath.cos(conto.xy));
            }
        }
        if (control.left) {
            if (conto.y < 207) {
                conto.xy -= 10;
            } else {
                conto.xz += 2;
            }
        }
        if (control.right) {
            if (conto.y < 207) {
                conto.xy += 10;
            } else {
                conto.xz -= 2;
            }
        }
        k = (int) ((float) (b * (3 + trnn[ltyp])) * RadicalMath.sin(conto.xy));
        conto.xz -= k;
        rlift = (int) (speed * RadicalMath.cos(conto.zy) * RadicalMath.cos(conto.xy)) - 40;
        if (lift < (double) rlift) {
            lift += 0.5D;
        }
        if (lift > (double) rlift) {
            lift -= 0.5D;
        }
        if (lift < (double) (-(50.0F - speed / 2.0F))) {
            lift = (double) (-(50.0F - speed / 2.0F));
        }
        l = (int) (5.0F * RadicalMath.cos(conto.zy) * RadicalMath.cos(conto.xy));
        if (lift > (double) l) {
            lift = (double) l;
        }
        conto.y -= (int) lift;
        if (conto.x < -40000) {
            conto.x = -40000;
            if (k <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (conto.x > 40000) {
            conto.x = 40000;
            if (k <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (conto.z > 40000) {
            conto.z = 40000;
            if (k <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (conto.z < -40000) {
            conto.z = -40000;
            if (k <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (!pexp && conto.exp) {
            if (speed > 40.0F) {
                speed = -15.0F;
                pexp = true;
            } else if (conto.nhits > conto.maxhits) {
                pexp = true;
            } else {
                conto.exp = false;
                speed = -(((float) rspeed + speed) / 2.0F);
            }
        }
        if (pexp) {
            if (speed > 0.0F) {
                speed = (float) ((double) speed - 0.3D);
            }
            if (speed < 0.0F) {
                speed = (float) ((double) speed + 0.3D);
            }
        } else {
            if (speed > (float) rspeed) {
                if (speed > (float) maxspeed[ltyp]) {
                    speed -= (speed - (float) rspeed) / 20.0F;
                } else {
                    speed = (float) ((double) speed - 0.5D);
                }
            }
            if (speed < (float) rspeed) {
                ++speed;
            }
        }
        if (conto.nhits > conto.maxhits - conto.maxhits / 6 && !conto.exp) {
            if (speed > 60.0F) {
                speed = 60.0F;
            }
            conto.xz += (int) (Math.random() * (double) (speed / 10.0F) - (double) (speed / 20.0F));
            conto.zy += (int) (Math.random() * (double) (speed / 10.0F) - (double) (speed / 20.0F));
        }
        if (control.plus && rspeed < maxspeed[ltyp]) {
            rspeed += 2;
        }
        if (control.mins && rspeed > 0) {
            rspeed -= 2;
        }
        if (control.jump != 0 && njumps != 0) {
            if (control.jump == 1) {
                speed = 400.0F;
                control.jump = 2;
                conto.m.jumping = 5;
            }
            if (conto.m.jumping == 0) {
                speed = 800.0F;
                control.jump = 0;
                njumps += -1;
            }
        }
        if (control.fire && !conto.exp) {
            if (skip && bulkc < Lasers.srate[ltyp]) {
                lx[nl] = conto.x;
                ly[nl] = conto.y;
                lz[nl] = conto.z;
                lxz[nl] = conto.xz;
                lzy[nl] = conto.zy;
                lxy[nl] = conto.xy;
                if (ly[nl] > 215) {
                    ly[nl] = 215;
                }
                lspeed[nl] = (int) ((float) Lasers.speed[ltyp] + speed);
                lstage[nl] = 1;
                lhit[nl] = 0;
                ++nl;
                if (nl == 20) {
                    nl = 0;
                }
                skip = false;
            } else if (!skip) {
                skip = true;
            }
            ++bulkc;
            if (bulkc > 12) {
                bulkc = 0;
            }
        }
        i1 = 0;
        j1 = 0;
        do {
            if (lstage[j1] != 0) {
                ++i1;
                if (ly[j1] > 240 && lhit[j1] == 0) {
                    lhit[j1] = 1;
                }
                if (lhit[j1] == 0) {
                    if (lstage[j1] > 10) {
                        int i2 = 22500;
                        int j2 = -1;
                        for (k1 = 1; k1 < i; ++k1) {
                            l2 = getpy(contos[ints[k1]].x, contos[ints[k1]].y, contos[ints[k1]].z, j1);
                            if (l2 < i2 && l2 > 0 && !contos[ints[k1]].exp) {
                                i2 = l2;
                                j2 = k1;
                            }
                        }
                        if (j2 != -1) {
                            if (lspeed[j1] > 230) {
                                lspeed[j1] = 230;
                            }
                            k1 = contos[ints[j2]].x;
                            l2 = contos[ints[j2]].z;
                            int k2 = contos[ints[j2]].y;
                            short s = 0;
                            if (k1 - lx[j1] > 0) {
                                s = 180;
                            }
                            lxz[j1] = (int) ((double) (90 + s) + Math.atan((double) (l2 - lz[j1]) / (double) (k1 - lx[j1])) / 0.017453292519943295D);
                            short s2 = 0;
                            if (k2 - ly[j1] < 0) {
                                s2 = -180;
                            }
                            int l3 = (int) Math.sqrt((double) ((l2 - lz[j1]) * (l2 - lz[j1]) + (k1 - lx[j1]) * (k1 - lx[j1])));
                            lzy[j1] = -((int) ((double) (90 + s2) - Math.atan((double) l3 / (double) (k2 - ly[j1])) / 0.017453292519943295D));
                        }
                    }
                    lx[j1] -= (int) ((float) lspeed[j1] * RadicalMath.sin(lxz[j1]) * RadicalMath.cos(lzy[j1]));
                    lz[j1] += (int) ((float) lspeed[j1] * RadicalMath.cos(lxz[j1]) * RadicalMath.cos(lzy[j1]));
                    ly[j1] -= (int) ((float) lspeed[j1] * RadicalMath.sin(lzy[j1]));
                    ++lstage[j1];
                    if (lstage[j1] > 80) {
                        lstage[j1] = 0;
                    }
                }
            }
            ++j1;
        } while (j1 < 20);
        if (i1 != 0) {
            if (!conto.fire) {
                conto.fire = true;
            }
        } else if (conto.fire) {
            conto.fire = false;
            bulkc = 0;
        }
        conto.x -= (int) (speed * RadicalMath.sin(conto.xz) * RadicalMath.cos(conto.zy));
        conto.z += (int) (speed * RadicalMath.cos(conto.xz) * RadicalMath.cos(conto.zy));
        conto.y -= (int) (speed * RadicalMath.sin(conto.zy));
        if (conto.y > 215) {
            conto.y = 215;
        }
        if (conto.y < -25000) {
            conto.y = -25000;
        }
        if (ester == 0) {
            if (conto.x > 2800 && conto.x < 3200 && conto.z > -2100 && conto.z < -1900 && conto.y > -30) {
                ester = 1;
                conto.nhits = 0;
                control.jump = 0;
                njumps = dnjm[ltyp];
            }
        } else {
            if (ester < 13) {
                if (ltyp == 0) {
                    if (conto.m.er == 0) {
                        conto.m.er = 1;
                    } else {
                        conto.m.er = 0;
                    }
                }
                if (ltyp == 1) {
                    if (conto.m.eg == 0) {
                        conto.m.eg = 1;
                    } else {
                        conto.m.eg = 0;
                    }
                }
                if (ltyp == 2) {
                    if (conto.m.eb == 0) {
                        conto.m.eb = 1;
                    } else {
                        conto.m.eb = 0;
                    }
                }
                if (ltyp == 3) {
                    if (conto.m.er == 0) {
                        conto.m.er = 1;
                        conto.m.eg = 1;
                    } else {
                        conto.m.er = 0;
                        conto.m.eg = 0;
                    }
                }
                if (ltyp == 4) {
                    if (conto.m.eb == 0) {
                        conto.m.eb = 1;
                        conto.m.eg = 1;
                    } else {
                        conto.m.eb = 0;
                        conto.m.eg = 0;
                    }
                }
            }
            if (ester == 1) {
                conto.wire = true;
            }
            if (ester == 3) {
                conto.wire = false;
            }
            ++ester;
            if (ester == 45) {
                ester = 0;
            }
        }
    }

    public void dosmokes(Graphics graphics, ContO conto) {
        if (!conto.exp) {
            int i;
            if (conto.nhits > conto.maxhits - conto.maxhits / 3) {
                if (dms[nd] == -1) {
                    dx[nd] = conto.x + (int) (Math.random() * 60.0D - 30.0D);
                    dy[nd] = conto.y;
                    dz[nd] = conto.z;
                    dxz[nd] = conto.xz;
                    dzy[nd] = conto.zy;
                    dms[nd] = 0;
                    ++nd;
                    if (nd == 4) {
                        nd = 0;
                    }
                }
                i = 0;
                do {
                    if (dms[i] != -1) {
                        if (dms[i] < 4) {
                            lsr.hsmoke(graphics, dx[i], dy[i], dz[i], dxz[i], dzy[i], dms[i]);
                        }
                        dy[i] -= 15;
                        ++dms[i];
                        if (dms[i] >= 7) {
                            dms[i] = -1;
                        }
                    }
                    ++i;
                } while (i < 4);
            }
            if (smoke && conto.y > 200 && sms[ns] == -1) {
                sx[ns] = conto.x + (int) (Math.random() * 80.0D - 40.0D);
                sy[ns] = conto.y + 15;
                sz[ns] = conto.z;
                sxz[ns] = conto.xz;
                szy[ns] = conto.zy;
                sms[ns] = 0;
                ++ns;
                if (ns == 4) {
                    ns = 0;
                }
                smoke = false;
            }
            i = 0;
            do {
                if (sms[i] != -1) {
                    if (sms[i] < 4) {
                        lsr.gsmoke(graphics, sx[i], sy[i], sz[i], sxz[i], szy[i], sms[i]);
                    }
                    sy[i] -= 15;
                    ++sms[i];
                    if (sms[i] == 10) {
                        sms[i] = -1;
                    }
                }
                ++i;
            } while (i < 4);
        }
    }

    public void reset(int i) {
        rspeed = 0;
        speed = 0.0F;
        rlift = 0;
        lift = 0.0D;
        pexp = false;
        ltyp = i;
        njumps = dnjm[i];
        int j = 0;
        do {
            lstage[j] = 0;
            ++j;
        } while (j < 20);
    }

    public userCraft(Medium var1) {
        lsr = new Lasers(var1);
        int var2 = 0;
        do {
            sms[var2] = -1;
            ++var2;
        } while (var2 < 4);
        var2 = 0;
        do {
            dms[var2] = -1;
            ++var2;
        } while (var2 < 4);
    }

    public void lasercolid(ContO conto) {
        if (!conto.exp && !conto.out) {
            int i = 0;
            do {
                if (lstage[i] != 0 && lhit[i] == 0) {
                    int j = getpy(conto.x, conto.y, conto.z, i);
                    if (j < conto.maxR / 10 * (conto.maxR / 10) && j > 0) {
                        if (conto.rcol != 0 && j < conto.maxR / (10 * conto.rcol) * (conto.maxR / (10 * conto.rcol)) + Lasers.rads[ltyp] / 10 * (Lasers.rads[ltyp] / 10)) {
                            lhit[i] = 1;
                            if (conto.maxhits != -1) {
                                conto.hit = true;
                                if (Math.random() > 0.5D) {
                                    conto.nhits += Lasers.damg[ltyp];
                                } else {
                                    conto.nhits += 2;
                                }
                            }
                        }
                        if (conto.pcol != 0) {
                            for (int k = 0; k < conto.npl; ++k) {
                                for (int l = 0; l < conto.p[k].n; ++l) {
                                    if (!conto.hit && (lx[i] - (conto.x + conto.p[k].ox[l])) * (lx[i] - (conto.x + conto.p[k].ox[l])) + (ly[i] - (conto.y + conto.p[k].oy[l])) * (ly[i] - (conto.y + conto.p[k].oy[l])) + (lz[i] - (conto.z + conto.p[k].oz[l])) * (lz[i] - (conto.z + conto.p[k].oz[l])) < Lasers.rads[ltyp] * 10 / conto.pcol * (Lasers.rads[ltyp] * 10 / conto.pcol)) {
                                        lhit[i] = 1;
                                        if (conto.maxhits != -1) {
                                            conto.hit = true;
                                            if (Math.random() > 0.5D) {
                                                conto.nhits += Lasers.damg[ltyp];
                                            } else {
                                                conto.nhits += 2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ++i;
            } while (i < 20);
        }
    }

    public int getpy(int i, int j, int k, int l) {
        return (i - lx[l]) / 10 * ((i - lx[l]) / 10) + (j - ly[l]) / 10 * ((j - ly[l]) / 10) + (k - lz[l]) / 10 * ((k - lz[l]) / 10);
    }

    public void dl(Graphics graphics) {
        int i = 0;
        do {
            if (lstage[i] != 0) {
                lsr.d(graphics, ltyp, lx[i], ly[i], lz[i], lxz[i], lzy[i], lxy[i], lhit[i]);
                if (lhit[i] != 0) {
                    ++lhit[i];
                    if (lhit[i] > 2) {
                        lstage[i] = 0;
                    }
                }
            }
            ++i;
        } while (i < 20);
    }
}
