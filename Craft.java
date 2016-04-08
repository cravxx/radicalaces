import java.awt.Graphics;

/**
 * craft
 * @author Omar Waly
 */
public class Craft {

    cControl u = new cControl();

    int rspeed = 0;

    float speed = 0.0F;

    int rlift = 0;

    double lift = 0.0D;

    boolean pexp = false;

    int ltyp = 3;

    int[] lx = new int[20];

    int[] ly = new int[20];

    int[] lz = new int[20];

    int[] lxz = new int[20];

    int[] lzy = new int[20];

    int[] lxy = new int[20];

    int[] lstage = new int[20];

    int[] lspeed = new int[20];

    int[] lhit = new int[20];

    int[] nf = new int[20];

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

    int gxz = 0;

    int gzy = 0;

    boolean responce = false;

    int trgxz = 0;

    int trgzy = 0;

    int out = 0;

    int turnat = (int) (Math.random() * 50.0D);

    int tcnt = 0;

    boolean engage = true;

    int enx = 0;

    int eny = 0;

    int enz = 0;

    int ens = 4;

    boolean targeting = false;

    int mode = 0;

    int m3o = 0;

    int m3cnt = 0;

    int m1cnt = 0;

    int relax = 50;

    int runn = 30;

    int liftup = 500;

    boolean dracs = false;

    public void preform(ContO conto, ContO[] contos, int[] ints, int i, int j, int k) {
        int l;
        for (l = Math.abs(conto.zy); l > 360; l -= 360) {
            ;
        }
        byte b = 1;
        if (l > 90 && l < 270) {
            b = -1;
        }
        int i1;
        int j1;
        int k1;
        int l2;
        int i2;
        int j2;
        if (conto.y < 207) {
            if (u.up) {
                conto.zy -= (int) (5.0F * RadicalMath.cos(conto.xy));
                conto.xz += (int) ((float) (b * 3) * RadicalMath.sin(conto.xy));
            }
            if (u.down) {
                conto.zy += (int) (5.0F * RadicalMath.cos(conto.xy));
                conto.xz -= (int) ((float) (b * 3) * RadicalMath.sin(conto.xy));
            }
        } else {
            for (i1 = Math.abs(conto.zy); i1 > 90; i1 -= 180) {
                ;
            }
            for (j1 = Math.abs(conto.xy); j1 > 90; j1 -= 180) {
                ;
            }
            for (k1 = Math.abs(conto.zy); k1 > 270; k1 -= 360) {
                ;
            }
            for (l2 = Math.abs(conto.xy); l2 > 270; l2 -= 360) {
                ;
            }
            boolean flag = Math.abs(k1) < 90 && Math.abs(l2) < 90 || Math.abs(k1) > 90 && Math.abs(l2) > 90;
            boolean flag2 = Math.abs(i1) > 30 || Math.abs(j1) > 30;
            if ((!flag || flag2) && !conto.exp) {
                conto.exp = true;
                conto.y = 170;
                speed = 30.0F;
                pexp = true;
            }
            for (i2 = Math.abs(conto.zy); i2 > 270; i2 -= 360) {
                ;
            }
            if (i2 > 90) {
                conto.xy = 180;
            } else {
                conto.xy = 0;
            }
            for (j2 = conto.zy; j2 > 90; j2 -= 180) {
                ;
            }
            while (j2 < -90) {
                j2 += 180;
            }
            if (j2 > 0) {
                conto.zy += -1;
                smoke = true;
            }
            if (j2 < 0) {
                ++conto.zy;
                smoke = true;
            }
            if (speed > 10.0F && u.down) {
                conto.zy += (int) (5.0F * RadicalMath.cos(conto.xy));
            }
        }
        if (u.left) {
            if (conto.y < 207) {
                if (conto.xy > -90) {
                    conto.xy -= 10;
                }
            } else {
                conto.xz += 2;
            }
        }
        if (u.right) {
            if (conto.y < 207) {
                if (conto.xy < 90) {
                    conto.xy += 10;
                }
            } else {
                conto.xz -= 2;
            }
        }
        i1 = (int) ((float) (b * 4) * RadicalMath.sin(conto.xy));
        conto.xz -= i1;
        if (conto.nhits > conto.maxhits - conto.maxhits / 6 && !conto.exp) {
            if (rspeed > 60) {
                rspeed = 60;
                speed = 60.0F;
            }
            conto.xz += (int) (Math.random() * (double) (speed / 10.0F) - (double) (speed / 20.0F));
            conto.zy += (int) (Math.random() * (double) (speed / 10.0F) - (double) (speed / 20.0F));
        }
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
        j1 = (int) (5.0F * RadicalMath.cos(conto.zy) * RadicalMath.cos(conto.xy));
        if (lift > (double) j1) {
            lift = (double) j1;
        }
        conto.y -= (int) lift;
        if (conto.x < -40000) {
            conto.x = -40000;
            if (i1 <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (conto.x > 40000) {
            conto.x = 40000;
            if (i1 <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (conto.z > 40000) {
            conto.z = 40000;
            if (i1 <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (conto.z < -40000) {
            conto.z = -40000;
            if (i1 <= 0) {
                conto.xz += 5;
            } else {
                conto.xz -= 5;
            }
        }
        if (!pexp && conto.exp) {
            if (speed > 30.0F) {
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
                speed = (float) ((double) speed - 0.5D);
            }
            if (speed < (float) rspeed) {
                ++speed;
            }
        }
        if (u.fire && !conto.exp) {
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
                nf[nl] = 0;
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
        k1 = 0;
        l2 = 0;
        int k2;
        int l3;
        int i3;
        do {
            if (lstage[l2] != 0) {
                ++k1;
                if (ly[l2] > 240 && lhit[l2] == 0) {
                    lhit[l2] = 1;
                }
                if (lhit[l2] == 0) {
                    if (lstage[l2] > 10 && nf[l2] < 15) {
                        l3 = -1;
                        i3 = -1;
                        if (!contos[j].exp) {
                            l3 = getpy(contos[j].x, contos[j].y, contos[j].z, l2);
                            i3 = j;
                        }
                        for (i2 = k; i2 < k + 13; ++i2) {
                            j2 = getpy(contos[i2].x, contos[i2].y, contos[i2].z, l2);
                            if (j2 < l3 && j2 > 0 && !contos[i2].exp) {
                                l3 = j2;
                                i3 = i2;
                            }
                        }
                        if (l3 < 22500 && l3 > 0) {
                            if (lspeed[l2] > 230) {
                                lspeed[l2] = 230;
                            }
                            i2 = contos[i3].x;
                            j2 = contos[i3].z;
                            k2 = contos[i3].y;
                            short s = 0;
                            if (i2 - lx[l2] > 0) {
                                s = 180;
                            }
                            lxz[l2] = (int) ((double) (90 + s) + Math.atan((double) (j2 - lz[l2]) / (double) (i2 - lx[l2])) / 0.017453292519943295D);
                            short s2 = 0;
                            if (k2 - ly[l2] < 0) {
                                s2 = -180;
                            }
                            int j3 = (int) Math.sqrt((double) ((j2 - lz[l2]) * (j2 - lz[l2]) + (i2 - lx[l2]) * (i2 - lx[l2])));
                            lzy[l2] = -((int) ((double) (90 + s2) - Math.atan((double) j3 / (double) (k2 - ly[l2])) / 0.017453292519943295D));
                            ++nf[l2];
                        }
                    }
                    lx[l2] -= (int) ((float) lspeed[l2] * RadicalMath.sin(lxz[l2]) * RadicalMath.cos(lzy[l2]));
                    lz[l2] += (int) ((float) lspeed[l2] * RadicalMath.cos(lxz[l2]) * RadicalMath.cos(lzy[l2]));
                    ly[l2] -= (int) ((float) lspeed[l2] * RadicalMath.sin(lzy[l2]));
                    ++lstage[l2];
                    if (lstage[l2] > 80) {
                        lstage[l2] = 0;
                    }
                }
            }
            ++l2;
        } while (l2 < 20);
        if (k1 != 0) {
            if (!conto.fire) {
                conto.fire = true;
            }
        } else if (conto.fire) {
            conto.fire = false;
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
        if (tcnt > turnat) {
            if (targeting) {
                targeting = false;
            }
            short s3;
            short s4;
            if (mode != 1 && mode != 3) {
                if (engage) {
                    s3 = 0;
                    if (contos[k + ens].x - conto.x > 0) {
                        s3 = 180;
                    }
                    gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[k + ens].z - conto.z) / (double) (contos[k + ens].x - conto.x)) / 0.017453292519943295D);
                    s4 = 0;
                    if (contos[k + ens].y - conto.y < 0) {
                        s4 = -180;
                    }
                    i3 = (int) Math.sqrt((double) ((contos[k + ens].z - conto.z) * (contos[k + ens].z - conto.z) + (contos[k + ens].x - conto.x) * (contos[k + ens].x - conto.x)));
                    gzy = -((int) ((double) (90 + s4) - Math.atan((double) i3 / (double) (contos[k + ens].y - conto.y)) / 0.017453292519943295D));
                    l2 = getcpy(conto, contos[k + ens]);
                    if (l2 > 0 && l2 < 15000) {
                        targeting = true;
                    }
                    if (l2 > 0 && l2 < 200 && Math.random() > 0.7D) {
                        if (Math.random() > 0.5D) {
                            enx = -6800 + (int) (2000.0D + 30000.0D * Math.random());
                        } else {
                            enx = -6800 - (int) (2000.0D + 30000.0D * Math.random());
                        }
                        if (Math.random() > 0.5D) {
                            enz = -1150 + (int) (2000.0D + 30000.0D * Math.random());
                        } else {
                            enz = -1150 - (int) (2000.0D + 30000.0D * Math.random());
                        }
                        if (Math.random() > 0.7D) {
                            eny = 0;
                        } else {
                            eny = -((int) (Math.random() * 23000.0D));
                        }
                        engage = false;
                        targeting = false;
                    }
                } else {
                    s3 = 0;
                    if (enx - conto.x > 0) {
                        s3 = 180;
                    }
                    gxz = (int) ((double) (90 + s3) + Math.atan((double) (enz - conto.z) / (double) (enx - conto.x)) / 0.017453292519943295D);
                    s4 = 0;
                    if (eny - conto.y < 0) {
                        s4 = -180;
                    }
                    i3 = (int) Math.sqrt((double) ((enz - conto.z) * (enz - conto.z) + (enx - conto.x) * (enx - conto.x)));
                    gzy = -((int) ((double) (90 + s4) - Math.atan((double) i3 / (double) (eny - conto.y)) / 0.017453292519943295D));
                    l2 = getepy(conto);
                    if (l2 > 0 && l2 < 500) {
                        ens = 4 + (int) (Math.random() * 5.0D);
                        engage = true;
                    }
                }
                turnat = (int) (Math.random() * 50.0D);
            }
            l2 = getcpy(contos[j], conto);
            if (l2 > 0) {
                if (l2 < 20000 && !contos[j].exp) {
                    if (mode == 0 && mode != 3) {
                        if (Math.random() > 0.5D && conto.maxR != 151) {
                            mode = 2;
                        } else {
                            mode = 1;
                            m1cnt = 0;
                        }
                    }
                } else if (mode != 0) {
                    mode = 0;
                }
            }
            if (mode == 1) {
                s3 = 0;
                if (contos[j].x - conto.x > 0) {
                    s3 = 180;
                }
                gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[j].z - conto.z) / (double) (contos[j].x - conto.x)) / 0.017453292519943295D);
                s4 = 0;
                if (contos[j].y - conto.y < 0) {
                    s4 = -180;
                }
                i3 = (int) Math.sqrt((double) ((contos[j].z - conto.z) * (contos[j].z - conto.z) + (contos[j].x - conto.x) * (contos[j].x - conto.x)));
                gzy = -((int) ((double) (90 + s4) - Math.atan((double) i3 / (double) (contos[j].y - conto.y)) / 0.017453292519943295D));
                turnat = (int) (Math.random() * 3.0D);
                if (l2 < 7000) {
                    targeting = true;
                }
                ++m1cnt;
                if (m1cnt > relax) {
                    mode = 0;
                }
            }
            if (mode == 3) {
                s3 = 0;
                if (contos[m3o].x - conto.x > 0) {
                    s3 = 180;
                }
                gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[m3o].z - conto.z) / (double) (contos[m3o].x - conto.x)) / 0.017453292519943295D);
                s4 = 0;
                if (contos[m3o].y - conto.y < 0) {
                    s4 = -180;
                }
                i3 = (int) Math.sqrt((double) ((contos[m3o].z - conto.z) * (contos[m3o].z - conto.z) + (contos[m3o].x - conto.x) * (contos[m3o].x - conto.x)));
                gzy = -((int) ((double) (90 + s4) - Math.atan((double) i3 / (double) (contos[m3o].y - conto.y)) / 0.017453292519943295D));
                turnat = (int) (Math.random() * 10.0D);
                ++m3cnt;
                if (m3cnt == runn) {
                    mode = 0;
                }
            }
            tcnt = 0;
        } else {
            ++tcnt;
        }
        if (mode != 3 && conto.hit && Math.random() > 0.85D) {
            if (Math.random() > 0.5D) {
                m3o = nearst(contos, ints, i, j, conto);
                mode = 3;
                m3cnt = 0;
            } else if (mode == 2) {
                if (conto.zy < 15 && Math.random() < 0.5D && conto.maxR != 151) {
                    turnat = 20;
                    gzy = 80;
                    mode = 0;
                } else {
                    mode = 1;
                    m1cnt = 0;
                }
            } else if (conto.zy < 15 && Math.random() < 0.5D) {
                turnat = 20;
                gzy = 80;
                mode = 0;
            } else {
                mode = 2;
            }
        }
        byte b2 = 0;
        if ((float) conto.y > 100.0F + (float) liftup * RadicalMath.sin(conto.zy)) {
            b2 = 1;
        }
        l3 = conto.y + (int) ((float) (-(conto.z + 1000 - conto.z)) * RadicalMath.sin(conto.zy));
        i3 = conto.z + (int) ((float) (conto.z + 1000 - conto.z) * RadicalMath.cos(conto.zy));
        i2 = conto.x + (int) ((float) (-(i3 - conto.z)) * RadicalMath.sin(conto.xz));
        j2 = conto.z + (int) ((float) (i3 - conto.z) * RadicalMath.cos(conto.xz));
        if (myway(contos, ints, i, j, i2, l3, j2)) {
            b2 = 2;
        }
        if (u.left) {
            u.left = false;
        }
        if (u.right) {
            u.right = false;
        }
        if (u.up) {
            u.up = false;
        }
        if (u.down) {
            u.down = false;
        }
        if (b2 != 2) {
            for (k2 = conto.xz; k2 >= 360; k2 -= 360) {
                ;
            }
            while (k2 < 0) {
                k2 += 360;
            }
            if (Math.abs(k2 - gxz) > 5 && b2 == 0) {
                if (k2 > 270 && gxz < 90) {
                    u.left = true;
                    trgxz = 360 - k2 + gxz;
                } else if (k2 < 90 && gxz > 270) {
                    u.right = true;
                    trgxz = 360 - gxz + k2;
                } else if (k2 < gxz) {
                    u.left = true;
                    trgxz = gxz - k2;
                } else {
                    u.right = true;
                    trgxz = k2 - gxz;
                }
                if (dracs && Math.abs(conto.xy) > 80) {
                    u.down = true;
                }
            } else {
                if (conto.xy > 0) {
                    u.left = true;
                }
                if (conto.xy < 0) {
                    u.right = true;
                }
                if (b2 == 1 && Math.abs(conto.xy) < 30 && conto.zy < -30) {
                    gzy = 20;
                }
            }
            if (Math.abs(conto.zy - gzy) > 5 && Math.abs(conto.xy) < 45) {
                if (gzy < conto.zy) {
                    u.up = true;
                }
                if (gzy > conto.zy) {
                    u.down = true;
                }
                trgzy = Math.abs(conto.zy - gzy);
            }
        } else if (Math.abs(conto.xy) >= 60 && conto.zy >= 10) {
            if (conto.xy > 0) {
                u.right = true;
            }
            if (conto.xy < 0) {
                u.left = true;
            }
            if (conto.zy < 80) {
                u.down = true;
            }
            tcnt = 0;
            turnat = (int) (Math.random() * 4.0D + 3.0D);
        } else {
            if (conto.xy > 0) {
                u.left = true;
            }
            if (conto.xy < 0) {
                u.right = true;
            }
            if (conto.zy < 80) {
                u.down = true;
                gzy = 80;
            }
            tcnt = 0;
            turnat = (int) (Math.random() * 6.0D + 4.0D);
        }
        if (trgxz < 90 && trgzy < 40 && targeting) {
            if (!u.fire) {
                u.fire = true;
            }
        } else if (u.fire) {
            u.fire = false;
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
            if (conto.y > 200) {
                if (smoke && sms[ns] == -1) {
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
    }

    public int nearst(ContO[] contos, int[] ints, int i, int j, ContO conto) {
        int k = getcpy(contos[ints[0]], conto);
        int l = ints[0];
        for (int i1 = 0; i1 < i; ++i1) {
            if (ints[i1] != j) {
                int j1 = getcpy(contos[ints[i1]], conto);
                if (j1 > 0 && j1 < k && !contos[ints[i1]].exp || k < 0) {
                    k = j1;
                    l = ints[i1];
                }
            }
        }
        return l;
    }

    public void reset(int i, int j, int k, int l, int i1, int j1) {
        rspeed = i;
        speed = (float) i;
        rlift = 0;
        lift = 0.0D;
        pexp = false;
        ltyp = j;
        mode = 0;
        relax = k;
        runn = l;
        liftup = i1;
        if (j1 == 1) {
            dracs = true;
        } else {
            dracs = false;
        }
        int k1 = 0;
        do {
            lstage[k1] = 0;
            ++k1;
        } while (k1 < 20);
    }

    public Craft(Medium m) {
        lsr = new Lasers(m);
        for (int i = 0; i < 4; i++) {
            sms[i] = -1;
            dms[i] = -1;
        }
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
                                conto.nhits += Lasers.damg[ltyp];
                            }
                        }
                        if (conto.pcol != 0) {
                            for (int k = 0; k < conto.npl; ++k) {
                                for (int l = 0; l < conto.p[k].n; ++l) {
                                    if (!conto.hit && (lx[i] - (conto.x + conto.p[k].ox[l])) * (lx[i] - (conto.x + conto.p[k].ox[l])) + (ly[i] - (conto.y + conto.p[k].oy[l])) * (ly[i] - (conto.y + conto.p[k].oy[l])) + (lz[i] - (conto.z + conto.p[k].oz[l])) * (lz[i] - (conto.z + conto.p[k].oz[l])) < Lasers.rads[ltyp] * 10 / conto.pcol * (Lasers.rads[ltyp] * 10 / conto.pcol)) {
                                        lhit[i] = 1;
                                        if (conto.maxhits != -1) {
                                            conto.hit = true;
                                            conto.nhits += Lasers.damg[ltyp];
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

    public int getcpy(ContO conto, ContO conto2) {
        return (conto.x - conto2.x) / 100 * ((conto.x - conto2.x) / 100) + (conto.y - conto2.y) / 100 * ((conto.y - conto2.y) / 100) + (conto.z - conto2.z) / 100 * ((conto.z - conto2.z) / 100);
    }

    public boolean myway(ContO[] contos, int[] ints, int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < i; ++j1) {
            if (ints[j1] != j) {
                int k1 = contos[ints[j1]].maxR / 20 * (contos[ints[j1]].maxR / 20);
                if (k1 < 5000) {
                    k1 = 5000;
                }
                int l2 = (contos[ints[j1]].x - k) / 10 * ((contos[ints[j1]].x - k) / 10) + (contos[ints[j1]].y - l) / 10 * ((contos[ints[j1]].y - l) / 10) + (contos[ints[j1]].z - i1) / 10 * ((contos[ints[j1]].z - i1) / 10);
                if (l2 > 0 && l2 < k1 && !contos[ints[j1]].exp && contos[ints[j1]].maxR > 75) {
                    return true;
                }
            }
        }
        return false;
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

    public int getepy(ContO conto) {
        return (conto.x - enx) / 100 * ((conto.x - enx) / 100) + (conto.y - eny) / 100 * ((conto.y - eny) / 100) + (conto.z - enz) / 100 * ((conto.z - enz) / 100);
    }
}
