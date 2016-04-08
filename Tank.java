import java.awt.Graphics;

public class Tank {

    cControl u = new cControl();

    int rspeed = 0;

    int ltyp = 0;

    float speed = 0.0F;

    boolean pexp = false;

    boolean left = false;

    boolean right = false;

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

    int ns = 0;

    boolean smoke = false;

    int turnat = (int) (Math.random() * 50.0D);

    int tcnt = 0;

    int gxz = 0;

    int attack = 0;

    boolean responce = false;

    int trgxz = 180;

    int trgt = 0;

    public void preform(ContO conto, ContO[] contos, int i, int j) {
        int k;
        for (k = Math.abs(conto.zy); k > 270; k -= 360) {
            ;
        }
        if (k > 90) {
            if (conto.xy < 180) {
                ++conto.xy;
                smoke = true;
            }
            if (conto.xy > 180) {
                conto.xy += -1;
                smoke = true;
            }
        } else {
            if (conto.xy < 0) {
                ++conto.xy;
                smoke = true;
            }
            if (conto.xy > 0) {
                conto.xy += -1;
                smoke = true;
            }
        }
        int l;
        for (l = conto.zy; l > 90; l -= 180) {
            ;
        }
        while (l < -90) {
            l += 180;
        }
        if (l > 0) {
            if (l > 4) {
                conto.zy -= 2;
            } else {
                conto.zy += -1;
            }
        }
        if (l < 0) {
            if (l < -4) {
                conto.zy += 2;
            } else {
                ++conto.zy;
            }
        }
        if (u.left) {
            conto.xz += 5;
            if ((conto.xy == 0 || conto.xy == 180) && !left) {
                conto.xy = (int) ((float) conto.xy + speed / 5.0F);
                left = true;
            }
        } else if (left) {
            left = false;
        }
        if (u.right) {
            conto.xz -= 5;
            if ((conto.xy == 0 || conto.xy == 180) && !right) {
                conto.xy = (int) ((float) conto.xy - speed / 5.0F);
                right = true;
            }
        } else if (right) {
            right = false;
        }
        if (conto.x < -40000) {
            conto.x = -40000;
        }
        if (conto.x > 40000) {
            conto.x = 40000;
        }
        if (conto.z > 40000) {
            conto.z = 40000;
        }
        if (conto.z < -40000) {
            conto.z = -40000;
        }
        if (!pexp && conto.exp) {
            if (conto.nhits < conto.maxhits) {
                conto.exp = false;
                if (u.left) {
                    conto.xz += 5;
                } else {
                    conto.xz -= 5;
                }
                conto.xy += 15 - (int) (Math.random() * 30.0D);
                conto.zy += 5 + (int) (Math.random() * 5.0D);
                conto.y -= 30 + (int) (Math.random() * 15.0D);
            } else {
                pexp = true;
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
                speed = (float) ((double) speed - 0.2D);
            }
            if (speed < (float) rspeed) {
                ++speed;
            }
            if (conto.y > 240) {
                conto.y = 240;
            } else if (conto.y > 235) {
                ++conto.y;
            } else {
                conto.y += 5;
            }
        }
        if (u.fire && !conto.exp) {
            if (skip && bulkc < lsr.srate[ltyp]) {
                lx[nl] = conto.x;
                ly[nl] = conto.y;
                lz[nl] = conto.z;
                lxz[nl] = conto.xz;
                lzy[nl] = conto.zy + 10;
                lxy[nl] = conto.xy;
                if (ly[nl] > 215) {
                    ly[nl] = 215;
                }
                lspeed[nl] = (int) ((float) lsr.speed[ltyp] + speed);
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
        int i1 = 0;
        int j1 = 0;
        int k1;
        do {
            if (lstage[j1] != 0) {
                ++i1;
                if (ly[j1] > 240 && lhit[j1] == 0) {
                    lhit[j1] = 1;
                }
                if (lhit[j1] == 0) {
                    if (lstage[j1] > 10 && nf[j1] < 15) {
                        k1 = -1;
                        int l2 = -1;
                        if (!contos[i].exp) {
                            k1 = getpy(contos[i].x, contos[i].y, contos[i].z, j1);
                            l2 = i;
                        }
                        int i2;
                        int j2;
                        for (i2 = j; i2 < j + 13; ++i2) {
                            j2 = getpy(contos[i2].x, contos[i2].y, contos[i2].z, j1);
                            if (j2 < k1 && j2 > 0 && !contos[i2].exp) {
                                k1 = j2;
                                l2 = i2;
                            }
                        }
                        if (k1 < 22500 && k1 > 0) {
                            if (lspeed[j1] > 230) {
                                lspeed[j1] = 230;
                            }
                            i2 = contos[l2].x;
                            j2 = contos[l2].z;
                            int k2 = contos[l2].y;
                            short s = 0;
                            if (i2 - lx[j1] > 0) {
                                s = 180;
                            }
                            lxz[j1] = (int) ((double) (90 + s) + Math.atan((double) (j2 - lz[j1]) / (double) (i2 - lx[j1])) / 0.017453292519943295D);
                            short s2 = 0;
                            if (k2 - ly[j1] < 0) {
                                s2 = -180;
                            }
                            int l3 = (int) Math.sqrt((double) ((j2 - lz[j1]) * (j2 - lz[j1]) + (i2 - lx[j1]) * (i2 - lx[j1])));
                            lzy[j1] = -((int) ((double) (90 + s2) - Math.atan((double) l3 / (double) (k2 - ly[j1])) / 0.017453292519943295D));
                            ++nf[j1];
                        }
                    }
                    lx[j1] -= (int) ((float) lspeed[j1] * SinCos.getsin(lxz[j1]) * SinCos.getcos(lzy[j1]));
                    lz[j1] += (int) ((float) lspeed[j1] * SinCos.getcos(lxz[j1]) * SinCos.getcos(lzy[j1]));
                    ly[j1] -= (int) ((float) lspeed[j1] * SinCos.getsin(lzy[j1]));
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
        }
        conto.x -= (int) (speed * SinCos.getsin(conto.xz) * SinCos.getcos(conto.zy));
        conto.z += (int) (speed * SinCos.getcos(conto.xz) * SinCos.getcos(conto.zy));
        conto.y -= (int) (speed * SinCos.getsin(conto.zy));
        if (tcnt > turnat) {
            if (trgt != 0) {
                trgt = 0;
            }
            short s3 = 0;
            if (contos[j + 4].x - conto.x > 0) {
                s3 = 180;
            }
            gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[j + 4].z - conto.z) / (double) (contos[j + 4].x - conto.x)) / 0.017453292519943295D);
            turnat = (int) (Math.random() * 200.0D);
            j1 = getcpy(contos[j + 4], conto);
            if (j1 < 1500 && j1 > 0) {
                if (Math.random() > 0.5D) {
                    gxz += (int) (70.0D + Math.random() * 20.0D);
                } else {
                    gxz -= (int) (70.0D + Math.random() * 20.0D);
                }
            } else {
                gxz += (int) (Math.random() * 40.0D - 20.0D);
                trgt = 1;
            }
            j1 = getcpy(contos[i], conto);
            if (j1 < 15000 && j1 > 0 && !contos[i].exp) {
                if (attack == 0) {
                    if (Math.random() > 0.5D) {
                        attack = 1;
                    } else {
                        attack = 2;
                    }
                }
                if (attack == 1) {
                    s3 = 0;
                    if (contos[i].x - conto.x > 0) {
                        s3 = 180;
                    }
                    gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[i].z - conto.z) / (double) (contos[i].x - conto.x)) / 0.017453292519943295D);
                    turnat = (int) (Math.random() * 3.0D);
                    trgt = 2;
                }
            } else if (attack != 0) {
                attack = 0;
            }
            if (gxz >= 360) {
                gxz -= 360;
            }
            if (gxz < 0) {
                gxz += 360;
            }
            tcnt = 0;
        } else {
            ++tcnt;
        }
        if (conto.hit && Math.random() > 0.5D) {
            attack = 1;
            turnat = (int) (Math.random() * 10.0D);
        }
        if (u.fire) {
            u.fire = false;
        }
        if (trgt == 1 && trgxz < 90) {
            j1 = getcpy(contos[j + 4], conto);
            if (j1 > 0 && j1 < 10000) {
                u.fire = true;
            }
        }
        if (trgt == 2 && trgxz < 90) {
            u.fire = true;
        }
        if (responce) {
            if (u.left) {
                u.left = false;
            }
            if (u.right) {
                u.right = false;
            }
            for (k1 = conto.xz; k1 >= 360; k1 -= 360) {
                ;
            }
            while (k1 < 0) {
                k1 += 360;
            }
            if (Math.abs(k1 - gxz) > 5) {
                if (k1 > 270 && gxz < 90) {
                    u.left = true;
                    trgxz = 360 - k1 + gxz;
                } else if (k1 < 90 && gxz > 270) {
                    u.right = true;
                    trgxz = 360 - gxz + k1;
                } else if (k1 < gxz) {
                    u.left = true;
                    trgxz = gxz - k1;
                } else {
                    u.right = true;
                    trgxz = k1 - gxz;
                }
            }
            responce = false;
        } else {
            responce = true;
        }
    }

    public void dosmokes(Graphics graphics, ContO conto) {
        if (conto.y > 200) {
            if (smoke && !conto.exp && sms[ns] == -1) {
                sx[ns] = conto.x + (int) (Math.random() * 150.0D - 75.0D);
                sy[ns] = conto.y + 10;
                sz[ns] = conto.z;
                sxz[ns] = conto.xz;
                sms[ns] = 0;
                ++ns;
                if (ns == 4) {
                    ns = 0;
                }
                smoke = false;
            }
            int i = 0;
            do {
                if (sms[i] != -1) {
                    if (sms[i] < 5) {
                        lsr.gsmoke(graphics, sx[i], sy[i], sz[i], sxz[i], 0, sms[i]);
                    }
                    sy[i] -= 10;
                    ++sms[i];
                    if (sms[i] == 10) {
                        sms[i] = -1;
                    }
                }
                ++i;
            } while (i < 4);
        }
    }

    public void reset(int i, int j) {
        rspeed = i;
        pexp = false;
        ltyp = j;
        int k = 0;
        do {
            lstage[k] = 0;
            ++k;
        } while (k < 20);
    }

    public Tank(Medium var1) {
        lsr = new Lasers(var1);
        int var2 = 0;
        do {
            sms[var2] = -1;
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
                        if (conto.rcol != 0 && j < conto.maxR / (10 * conto.rcol) * (conto.maxR / (10 * conto.rcol)) + lsr.rads[ltyp] / 10 * (lsr.rads[ltyp] / 10)) {
                            lhit[i] = 1;
                            if (conto.maxhits != -1) {
                                conto.hit = true;
                                conto.nhits += lsr.damg[ltyp];
                            }
                        }
                        if (conto.pcol != 0) {
                            for (int k = 0; k < conto.npl; ++k) {
                                for (int l = 0; l < conto.p[k].n; ++l) {
                                    if (!conto.hit && (lx[i] - (conto.x + conto.p[k].ox[l])) * (lx[i] - (conto.x + conto.p[k].ox[l])) + (ly[i] - (conto.y + conto.p[k].oy[l])) * (ly[i] - (conto.y + conto.p[k].oy[l])) + (lz[i] - (conto.z + conto.p[k].oz[l])) * (lz[i] - (conto.z + conto.p[k].oz[l])) < lsr.rads[ltyp] * 10 / conto.pcol * (lsr.rads[ltyp] * 10 / conto.pcol)) {
                                        lhit[i] = 1;
                                        if (conto.maxhits != -1) {
                                            conto.hit = true;
                                            conto.nhits += lsr.damg[ltyp];
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
