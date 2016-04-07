import java.awt.Graphics;

public class userCraft {
    int rspeed = 0;
    float speed = 0.0F;
    int rlift = 0;
    double lift = 0.0D;
    boolean pexp = false;
    int ltyp = 0;
    int[] maxspeed = new int[] {
            120, 100, 90, 80, 76
    };
    int[] elev = new int[] {
            1, 2, 1, 1, 1
    };
    int[] trnn = new int[] {
            0, 0, 1, 2, 1
    };
    int[] dnjm = new int[] {
            7, 5, 4, 3, 4
    };
    String[] name = new String[] {
            "E-7 Sky Bullet", "BP-6 Hammer Head", "E-9 Dragon Bird", "EXA-1 Destroyer", "Silver F-51 Legend"
    };
    int njumps = 0;
    int ester = 0;
    int[] lx = new int[20];
    int[] ly = new int[20];
    int[] lz = new int[20];
    int[] lxz = new int[20];
    int[] lzy = new int[20];
    int[] lxy = new int[20];
    int[] lstage = new int[20];
    int[] lspeed = new int[20];
    int[] lhit = new int[20];
    int nl = 0;
    Lasers lsr;
    boolean skip = false;
    int bulkc = 0;
    int[] sms = new int[4];
    int[] sx = new int[4];
    int[] sy = new int[4];
    int[] sz = new int[4];
    int[] sxz = new int[4];
    int[] szy = new int[4];
    int ns = 0;
    boolean smoke = false;
    int[] dms = new int[4];
    int[] dx = new int[4];
    int[] dy = new int[4];
    int[] dz = new int[4];
    int[] dxz = new int[4];
    int[] dzy = new int[4];
    int nd = 0;
    public void preform(final Control control, final ContO conto, final ContO[] contos, final int[] ints, final int i) {
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
                conto.zy -= (int) ((4 + elev[ltyp]) * SinCos.cos(conto.xy));
                conto.xz += (int) (b * (2 + elev[ltyp]) * SinCos.sin(conto.xy));
            }
            if (control.down) {
                conto.zy += (int) ((4 + elev[ltyp]) * SinCos.cos(conto.xy));
                conto.xz -= (int) (b * (2 + elev[ltyp]) * SinCos.sin(conto.xy));
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
            final boolean flag = Math.abs(i1) < 90 && Math.abs(j1) < 90 || Math.abs(i1) > 90 && Math.abs(j1) > 90;
            final boolean flag2 = Math.abs(k) > 30 || Math.abs(l) > 30;
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
                conto.zy += (int) (5.0F * SinCos.cos(conto.xy));
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
        k = (int) (b * (3 + trnn[ltyp]) * SinCos.sin(conto.xy));
        conto.xz -= k;
        rlift = (int) (speed * SinCos.cos(conto.zy) * SinCos.cos(conto.xy)) - 40;
        if (lift < rlift) {
            lift += 0.5D;
        }
        if (lift > rlift) {
            lift -= 0.5D;
        }
        if (lift < -(50.0F - speed / 2.0F)) {
            lift = -(50.0F - speed / 2.0F);
        }
        l = (int) (5.0F * SinCos.cos(conto.zy) * SinCos.cos(conto.xy));
        if (lift > l) {
            lift = l;
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
                speed = -((rspeed + speed) / 2.0F);
            }
        }
        if (pexp) {
            if (speed > 0.0F) {
                speed = (float) (speed - 0.3D);
            }
            if (speed < 0.0F) {
                speed = (float) (speed + 0.3D);
            }
        } else {
            if (speed > rspeed) {
                if (speed > maxspeed[ltyp]) {
                    speed -= (speed - rspeed) / 20.0F;
                } else {
                    speed = (float) (speed - 0.5D);
                }
            }
            if (speed < rspeed) {
                ++speed;
            }
        }
        if (conto.nhits > conto.maxhits - conto.maxhits / 6 && !conto.exp) {
            if (speed > 60.0F) {
                speed = 60.0F;
            }
            conto.xz += (int) (Math.random() * (speed / 10.0F) - speed / 20.0F);
            conto.zy += (int) (Math.random() * (speed / 10.0F) - speed / 20.0F);
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
            if (skip && bulkc < lsr.srate[ltyp]) {
                lx[nl] = conto.x;
                ly[nl] = conto.y;
                lz[nl] = conto.z;
                lxz[nl] = conto.xz;
                lzy[nl] = conto.zy;
                lxy[nl] = conto.xy;
                if (ly[nl] > 215) {
                    ly[nl] = 215;
                }
                lspeed[nl] = (int) (lsr.speed[ltyp] + speed);
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
                            final int k2 = contos[ints[j2]].y;
                            short s = 0;
                            if (k1 - lx[j1] > 0) {
                                s = 180;
                            }
                            lxz[j1] = (int) (90 + s + Math.atan((double) (l2 - lz[j1]) / (double) (k1 - lx[j1])) / 0.017453292519943295D);
                            short s2 = 0;
                            if (k2 - ly[j1] < 0) {
                                s2 = -180;
                            }
                            final int l3 = (int) Math.sqrt((l2 - lz[j1]) * (l2 - lz[j1]) + (k1 - lx[j1]) * (k1 - lx[j1]));
                            lzy[j1] = -((int) (90 + s2 - Math.atan((double) l3 / (double) (k2 - ly[j1])) / 0.017453292519943295D));
                        }
                    }
                    lx[j1] -= (int) (lspeed[j1] * SinCos.sin(lxz[j1]) * SinCos.cos(lzy[j1]));
                    lz[j1] += (int) (lspeed[j1] * SinCos.cos(lxz[j1]) * SinCos.cos(lzy[j1]));
                    ly[j1] -= (int) (lspeed[j1] * SinCos.sin(lzy[j1]));
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
        conto.x -= (int) (speed * SinCos.sin(conto.xz) * SinCos.cos(conto.zy));
        conto.z += (int) (speed * SinCos.cos(conto.xz) * SinCos.cos(conto.zy));
        conto.y -= (int) (speed * SinCos.sin(conto.zy));
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
    public void dosmokes(final Graphics graphics, final ContO conto) {
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
    public void reset(final int i) {
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
    public userCraft(final Medium var1) {
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
    public void lasercolid(final ContO conto) {
        if (!conto.exp && !conto.out) {
            int i = 0;
            do {
                if (lstage[i] != 0 && lhit[i] == 0) {
                    final int j = getpy(conto.x, conto.y, conto.z, i);
                    if (j < conto.maxR / 10 * (conto.maxR / 10) && j > 0) {
                        if (conto.rcol != 0 && j < conto.maxR / (10 * conto.rcol) * (conto.maxR / (10 * conto.rcol)) + lsr.rads[ltyp] / 10 * (lsr.rads[ltyp] / 10)) {
                            lhit[i] = 1;
                            if (conto.maxhits != -1) {
                                conto.hit = true;
                                if (Math.random() > 0.5D) {
                                    conto.nhits += lsr.damg[ltyp];
                                } else {
                                    conto.nhits += 2;
                                }
                            }
                        }
                        if (conto.pcol != 0) {
                            for (int k = 0; k < conto.npl; ++k) {
                                for (int l = 0; l < conto.p[k].n; ++l) {
                                    if (!conto.hit && (lx[i] - (conto.x + conto.p[k].ox[l])) * (lx[i] - (conto.x + conto.p[k].ox[l])) + (ly[i] - (conto.y + conto.p[k].oy[l])) * (ly[i] - (conto.y + conto.p[k].oy[l])) + (lz[i] - (conto.z + conto.p[k].oz[l])) * (lz[i] - (conto.z + conto.p[k].oz[l])) < lsr.rads[ltyp] * 10 / conto.pcol * (lsr.rads[ltyp] * 10 / conto.pcol)) {
                                        lhit[i] = 1;
                                        if (conto.maxhits != -1) {
                                            conto.hit = true;
                                            if (Math.random() > 0.5D) {
                                                conto.nhits += lsr.damg[ltyp];
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
    public int getpy(final int i, final int j, final int k, final int l) {
        return (i - lx[l]) / 10 * ((i - lx[l]) / 10) + (j - ly[l]) / 10 * ((j - ly[l]) / 10) + (k - lz[l]) / 10 * ((k - lz[l]) / 10);
    }
    public void dl(final Graphics graphics) {
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
