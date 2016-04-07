import java.awt.Graphics;

public class userCraft {

    int rspeed = 0;

    float speed = 0.0F;

    int rlift = 0;

    double lift = 0.0D;

    boolean pexp = false;

    int ltyp = 0;

    int[] maxspeed = new int[] { 120, 100, 90, 80, 76 };

    int[] elev = new int[] { 1, 2, 1, 1, 1 };

    int[] trnn = new int[] { 0, 0, 1, 2, 1 };

    int[] dnjm = new int[] { 7, 5, 4, 3, 4 };

    String[] name = new String[] { "E-7 Sky Bullet", "BP-6 Hammer Head", "E-9 Dragon Bird", "EXA-1 Destroyer", "Silver F-51 Legend" };

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
                conto.zy -= (int) ((float) (4 + this.elev[this.ltyp]) * conto.m.cs.cos(conto.xy));
                conto.xz += (int) ((float) (b * (2 + this.elev[this.ltyp])) * conto.m.cs.sin(conto.xy));
            }
            if (control.down) {
                conto.zy += (int) ((float) (4 + this.elev[this.ltyp]) * conto.m.cs.cos(conto.xy));
                conto.xz -= (int) ((float) (b * (2 + this.elev[this.ltyp])) * conto.m.cs.sin(conto.xy));
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
                this.speed = 30.0F;
                this.pexp = true;
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
                this.smoke = true;
            }
            if (l2 < 0) {
                ++conto.zy;
                this.smoke = true;
            }
            if (this.speed > 10.0F && control.down) {
                conto.zy += (int) (5.0F * conto.m.cs.cos(conto.xy));
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
        k = (int) ((float) (b * (3 + this.trnn[this.ltyp])) * conto.m.cs.sin(conto.xy));
        conto.xz -= k;
        this.rlift = (int) (this.speed * conto.m.cs.cos(conto.zy) * conto.m.cs.cos(conto.xy)) - 40;
        if (this.lift < (double) this.rlift) {
            this.lift += 0.5D;
        }
        if (this.lift > (double) this.rlift) {
            this.lift -= 0.5D;
        }
        if (this.lift < (double) (-(50.0F - this.speed / 2.0F))) {
            this.lift = (double) (-(50.0F - this.speed / 2.0F));
        }
        l = (int) (5.0F * conto.m.cs.cos(conto.zy) * conto.m.cs.cos(conto.xy));
        if (this.lift > (double) l) {
            this.lift = (double) l;
        }
        conto.y -= (int) this.lift;
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
        if (!this.pexp && conto.exp) {
            if (this.speed > 40.0F) {
                this.speed = -15.0F;
                this.pexp = true;
            } else if (conto.nhits > conto.maxhits) {
                this.pexp = true;
            } else {
                conto.exp = false;
                this.speed = -(((float) this.rspeed + this.speed) / 2.0F);
            }
        }
        if (this.pexp) {
            if (this.speed > 0.0F) {
                this.speed = (float) ((double) this.speed - 0.3D);
            }
            if (this.speed < 0.0F) {
                this.speed = (float) ((double) this.speed + 0.3D);
            }
        } else {
            if (this.speed > (float) this.rspeed) {
                if (this.speed > (float) this.maxspeed[this.ltyp]) {
                    this.speed -= (this.speed - (float) this.rspeed) / 20.0F;
                } else {
                    this.speed = (float) ((double) this.speed - 0.5D);
                }
            }
            if (this.speed < (float) this.rspeed) {
                ++this.speed;
            }
        }
        if (conto.nhits > conto.maxhits - conto.maxhits / 6 && !conto.exp) {
            if (this.speed > 60.0F) {
                this.speed = 60.0F;
            }
            conto.xz += (int) (Math.random() * (double) (this.speed / 10.0F) - (double) (this.speed / 20.0F));
            conto.zy += (int) (Math.random() * (double) (this.speed / 10.0F) - (double) (this.speed / 20.0F));
        }
        if (control.plus && this.rspeed < this.maxspeed[this.ltyp]) {
            this.rspeed += 2;
        }
        if (control.mins && this.rspeed > 0) {
            this.rspeed -= 2;
        }
        if (control.jump != 0 && this.njumps != 0) {
            if (control.jump == 1) {
                this.speed = 400.0F;
                control.jump = 2;
                conto.m.jumping = 5;
            }
            if (conto.m.jumping == 0) {
                this.speed = 800.0F;
                control.jump = 0;
                this.njumps += -1;
            }
        }
        if (control.fire && !conto.exp) {
            if (this.skip && this.bulkc < this.lsr.srate[this.ltyp]) {
                this.lx[this.nl] = conto.x;
                this.ly[this.nl] = conto.y;
                this.lz[this.nl] = conto.z;
                this.lxz[this.nl] = conto.xz;
                this.lzy[this.nl] = conto.zy;
                this.lxy[this.nl] = conto.xy;
                if (this.ly[this.nl] > 215) {
                    this.ly[this.nl] = 215;
                }
                this.lspeed[this.nl] = (int) ((float) this.lsr.speed[this.ltyp] + this.speed);
                this.lstage[this.nl] = 1;
                this.lhit[this.nl] = 0;
                ++this.nl;
                if (this.nl == 20) {
                    this.nl = 0;
                }
                this.skip = false;
            } else if (!this.skip) {
                this.skip = true;
            }
            ++this.bulkc;
            if (this.bulkc > 12) {
                this.bulkc = 0;
            }
        }
        i1 = 0;
        j1 = 0;
        do {
            if (this.lstage[j1] != 0) {
                ++i1;
                if (this.ly[j1] > 240 && this.lhit[j1] == 0) {
                    this.lhit[j1] = 1;
                }
                if (this.lhit[j1] == 0) {
                    if (this.lstage[j1] > 10) {
                        int i2 = 22500;
                        int j2 = -1;
                        for (k1 = 1; k1 < i; ++k1) {
                            l2 = this.getpy(contos[ints[k1]].x, contos[ints[k1]].y, contos[ints[k1]].z, j1);
                            if (l2 < i2 && l2 > 0 && !contos[ints[k1]].exp) {
                                i2 = l2;
                                j2 = k1;
                            }
                        }
                        if (j2 != -1) {
                            if (this.lspeed[j1] > 230) {
                                this.lspeed[j1] = 230;
                            }
                            k1 = contos[ints[j2]].x;
                            l2 = contos[ints[j2]].z;
                            int k2 = contos[ints[j2]].y;
                            short s = 0;
                            if (k1 - this.lx[j1] > 0) {
                                s = 180;
                            }
                            this.lxz[j1] = (int) ((double) (90 + s) + Math.atan((double) (l2 - this.lz[j1]) / (double) (k1 - this.lx[j1])) / 0.017453292519943295D);
                            short s2 = 0;
                            if (k2 - this.ly[j1] < 0) {
                                s2 = -180;
                            }
                            int l3 = (int) Math.sqrt((double) ((l2 - this.lz[j1]) * (l2 - this.lz[j1]) + (k1 - this.lx[j1]) * (k1 - this.lx[j1])));
                            this.lzy[j1] = -((int) ((double) (90 + s2) - Math.atan((double) l3 / (double) (k2 - this.ly[j1])) / 0.017453292519943295D));
                        }
                    }
                    this.lx[j1] -= (int) ((float) this.lspeed[j1] * conto.m.cs.sin(this.lxz[j1]) * conto.m.cs.cos(this.lzy[j1]));
                    this.lz[j1] += (int) ((float) this.lspeed[j1] * conto.m.cs.cos(this.lxz[j1]) * conto.m.cs.cos(this.lzy[j1]));
                    this.ly[j1] -= (int) ((float) this.lspeed[j1] * conto.m.cs.sin(this.lzy[j1]));
                    ++this.lstage[j1];
                    if (this.lstage[j1] > 80) {
                        this.lstage[j1] = 0;
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
            this.bulkc = 0;
        }
        conto.x -= (int) (this.speed * conto.m.cs.sin(conto.xz) * conto.m.cs.cos(conto.zy));
        conto.z += (int) (this.speed * conto.m.cs.cos(conto.xz) * conto.m.cs.cos(conto.zy));
        conto.y -= (int) (this.speed * conto.m.cs.sin(conto.zy));
        if (conto.y > 215) {
            conto.y = 215;
        }
        if (conto.y < -25000) {
            conto.y = -25000;
        }
        if (this.ester == 0) {
            if (conto.x > 2800 && conto.x < 3200 && conto.z > -2100 && conto.z < -1900 && conto.y > -30) {
                this.ester = 1;
                conto.nhits = 0;
                control.jump = 0;
                this.njumps = this.dnjm[this.ltyp];
            }
        } else {
            if (this.ester < 13) {
                if (this.ltyp == 0) {
                    if (conto.m.er == 0) {
                        conto.m.er = 1;
                    } else {
                        conto.m.er = 0;
                    }
                }
                if (this.ltyp == 1) {
                    if (conto.m.eg == 0) {
                        conto.m.eg = 1;
                    } else {
                        conto.m.eg = 0;
                    }
                }
                if (this.ltyp == 2) {
                    if (conto.m.eb == 0) {
                        conto.m.eb = 1;
                    } else {
                        conto.m.eb = 0;
                    }
                }
                if (this.ltyp == 3) {
                    if (conto.m.er == 0) {
                        conto.m.er = 1;
                        conto.m.eg = 1;
                    } else {
                        conto.m.er = 0;
                        conto.m.eg = 0;
                    }
                }
                if (this.ltyp == 4) {
                    if (conto.m.eb == 0) {
                        conto.m.eb = 1;
                        conto.m.eg = 1;
                    } else {
                        conto.m.eb = 0;
                        conto.m.eg = 0;
                    }
                }
            }
            if (this.ester == 1) {
                conto.wire = true;
            }
            if (this.ester == 3) {
                conto.wire = false;
            }
            ++this.ester;
            if (this.ester == 45) {
                this.ester = 0;
            }
        }
    }

    public void dosmokes(Graphics graphics, ContO conto) {
        if (!conto.exp) {
            int i;
            if (conto.nhits > conto.maxhits - conto.maxhits / 3) {
                if (this.dms[this.nd] == -1) {
                    this.dx[this.nd] = conto.x + (int) (Math.random() * 60.0D - 30.0D);
                    this.dy[this.nd] = conto.y;
                    this.dz[this.nd] = conto.z;
                    this.dxz[this.nd] = conto.xz;
                    this.dzy[this.nd] = conto.zy;
                    this.dms[this.nd] = 0;
                    ++this.nd;
                    if (this.nd == 4) {
                        this.nd = 0;
                    }
                }
                i = 0;
                do {
                    if (this.dms[i] != -1) {
                        if (this.dms[i] < 4) {
                            this.lsr.hsmoke(graphics, this.dx[i], this.dy[i], this.dz[i], this.dxz[i], this.dzy[i], this.dms[i]);
                        }
                        this.dy[i] -= 15;
                        ++this.dms[i];
                        if (this.dms[i] >= 7) {
                            this.dms[i] = -1;
                        }
                    }
                    ++i;
                } while (i < 4);
            }
            if (this.smoke && conto.y > 200 && this.sms[this.ns] == -1) {
                this.sx[this.ns] = conto.x + (int) (Math.random() * 80.0D - 40.0D);
                this.sy[this.ns] = conto.y + 15;
                this.sz[this.ns] = conto.z;
                this.sxz[this.ns] = conto.xz;
                this.szy[this.ns] = conto.zy;
                this.sms[this.ns] = 0;
                ++this.ns;
                if (this.ns == 4) {
                    this.ns = 0;
                }
                this.smoke = false;
            }
            i = 0;
            do {
                if (this.sms[i] != -1) {
                    if (this.sms[i] < 4) {
                        this.lsr.gsmoke(graphics, this.sx[i], this.sy[i], this.sz[i], this.sxz[i], this.szy[i], this.sms[i]);
                    }
                    this.sy[i] -= 15;
                    ++this.sms[i];
                    if (this.sms[i] == 10) {
                        this.sms[i] = -1;
                    }
                }
                ++i;
            } while (i < 4);
        }
    }

    public void reset(int i) {
        this.rspeed = 0;
        this.speed = 0.0F;
        this.rlift = 0;
        this.lift = 0.0D;
        this.pexp = false;
        this.ltyp = i;
        this.njumps = this.dnjm[i];
        int j = 0;
        do {
            this.lstage[j] = 0;
            ++j;
        } while (j < 20);
    }

    public  userCraft(Medium var1) {
        this.lsr = new Lasers(var1);
        int var2 = 0;
        do {
            this.sms[var2] = -1;
            ++var2;
        } while (var2 < 4);
        var2 = 0;
        do {
            this.dms[var2] = -1;
            ++var2;
        } while (var2 < 4);
    }

    public void lasercolid(ContO conto) {
        if (!conto.exp && !conto.out) {
            int i = 0;
            do {
                if (this.lstage[i] != 0 && this.lhit[i] == 0) {
                    int j = this.getpy(conto.x, conto.y, conto.z, i);
                    if (j < conto.maxR / 10 * (conto.maxR / 10) && j > 0) {
                        if (conto.rcol != 0 && j < conto.maxR / (10 * conto.rcol) * (conto.maxR / (10 * conto.rcol)) + this.lsr.rads[this.ltyp] / 10 * (this.lsr.rads[this.ltyp] / 10)) {
                            this.lhit[i] = 1;
                            if (conto.maxhits != -1) {
                                conto.hit = true;
                                if (Math.random() > 0.5D) {
                                    conto.nhits += this.lsr.damg[this.ltyp];
                                } else {
                                    conto.nhits += 2;
                                }
                            }
                        }
                        if (conto.pcol != 0) {
                            for (int k = 0; k < conto.npl; ++k) {
                                for (int l = 0; l < conto.p[k].n; ++l) {
                                    if (!conto.hit && (this.lx[i] - (conto.x + conto.p[k].ox[l])) * (this.lx[i] - (conto.x + conto.p[k].ox[l])) + (this.ly[i] - (conto.y + conto.p[k].oy[l])) * (this.ly[i] - (conto.y + conto.p[k].oy[l])) + (this.lz[i] - (conto.z + conto.p[k].oz[l])) * (this.lz[i] - (conto.z + conto.p[k].oz[l])) < this.lsr.rads[this.ltyp] * 10 / conto.pcol * (this.lsr.rads[this.ltyp] * 10 / conto.pcol)) {
                                        this.lhit[i] = 1;
                                        if (conto.maxhits != -1) {
                                            conto.hit = true;
                                            if (Math.random() > 0.5D) {
                                                conto.nhits += this.lsr.damg[this.ltyp];
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
        return (i - this.lx[l]) / 10 * ((i - this.lx[l]) / 10) + (j - this.ly[l]) / 10 * ((j - this.ly[l]) / 10) + (k - this.lz[l]) / 10 * ((k - this.lz[l]) / 10);
    }

    public void dl(Graphics graphics) {
        int i = 0;
        do {
            if (this.lstage[i] != 0) {
                this.lsr.d(graphics, this.ltyp, this.lx[i], this.ly[i], this.lz[i], this.lxz[i], this.lzy[i], this.lxy[i], this.lhit[i]);
                if (this.lhit[i] != 0) {
                    ++this.lhit[i];
                    if (this.lhit[i] > 2) {
                        this.lstage[i] = 0;
                    }
                }
            }
            ++i;
        } while (i < 20);
    }
}
