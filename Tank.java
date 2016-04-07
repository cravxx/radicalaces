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
                this.smoke = true;
            }
            if (conto.xy > 180) {
                conto.xy += -1;
                this.smoke = true;
            }
        } else {
            if (conto.xy < 0) {
                ++conto.xy;
                this.smoke = true;
            }
            if (conto.xy > 0) {
                conto.xy += -1;
                this.smoke = true;
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
        if (this.u.left) {
            conto.xz += 5;
            if ((conto.xy == 0 || conto.xy == 180) && !this.left) {
                conto.xy = (int) ((float) conto.xy + this.speed / 5.0F);
                this.left = true;
            }
        } else if (this.left) {
            this.left = false;
        }
        if (this.u.right) {
            conto.xz -= 5;
            if ((conto.xy == 0 || conto.xy == 180) && !this.right) {
                conto.xy = (int) ((float) conto.xy - this.speed / 5.0F);
                this.right = true;
            }
        } else if (this.right) {
            this.right = false;
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
        if (!this.pexp && conto.exp) {
            if (conto.nhits < conto.maxhits) {
                conto.exp = false;
                if (this.u.left) {
                    conto.xz += 5;
                } else {
                    conto.xz -= 5;
                }
                conto.xy += 15 - (int) (Math.random() * 30.0D);
                conto.zy += 5 + (int) (Math.random() * 5.0D);
                conto.y -= 30 + (int) (Math.random() * 15.0D);
            } else {
                this.pexp = true;
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
                this.speed = (float) ((double) this.speed - 0.2D);
            }
            if (this.speed < (float) this.rspeed) {
                ++this.speed;
            }
            if (conto.y > 240) {
                conto.y = 240;
            } else if (conto.y > 235) {
                ++conto.y;
            } else {
                conto.y += 5;
            }
        }
        if (this.u.fire && !conto.exp) {
            if (this.skip && this.bulkc < this.lsr.srate[this.ltyp]) {
                this.lx[this.nl] = conto.x;
                this.ly[this.nl] = conto.y;
                this.lz[this.nl] = conto.z;
                this.lxz[this.nl] = conto.xz;
                this.lzy[this.nl] = conto.zy + 10;
                this.lxy[this.nl] = conto.xy;
                if (this.ly[this.nl] > 215) {
                    this.ly[this.nl] = 215;
                }
                this.lspeed[this.nl] = (int) ((float) this.lsr.speed[this.ltyp] + this.speed);
                this.lstage[this.nl] = 1;
                this.lhit[this.nl] = 0;
                this.nf[this.nl] = 0;
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
        int i1 = 0;
        int j1 = 0;
        int k1;
        do {
            if (this.lstage[j1] != 0) {
                ++i1;
                if (this.ly[j1] > 240 && this.lhit[j1] == 0) {
                    this.lhit[j1] = 1;
                }
                if (this.lhit[j1] == 0) {
                    if (this.lstage[j1] > 10 && this.nf[j1] < 15) {
                        k1 = -1;
                        int l2 = -1;
                        if (!contos[i].exp) {
                            k1 = this.getpy(contos[i].x, contos[i].y, contos[i].z, j1);
                            l2 = i;
                        }
                        int i2;
                        int j2;
                        for (i2 = j; i2 < j + 13; ++i2) {
                            j2 = this.getpy(contos[i2].x, contos[i2].y, contos[i2].z, j1);
                            if (j2 < k1 && j2 > 0 && !contos[i2].exp) {
                                k1 = j2;
                                l2 = i2;
                            }
                        }
                        if (k1 < 22500 && k1 > 0) {
                            if (this.lspeed[j1] > 230) {
                                this.lspeed[j1] = 230;
                            }
                            i2 = contos[l2].x;
                            j2 = contos[l2].z;
                            int k2 = contos[l2].y;
                            short s = 0;
                            if (i2 - this.lx[j1] > 0) {
                                s = 180;
                            }
                            this.lxz[j1] = (int) ((double) (90 + s) + Math.atan((double) (j2 - this.lz[j1]) / (double) (i2 - this.lx[j1])) / 0.017453292519943295D);
                            short s2 = 0;
                            if (k2 - this.ly[j1] < 0) {
                                s2 = -180;
                            }
                            int l3 = (int) Math.sqrt((double) ((j2 - this.lz[j1]) * (j2 - this.lz[j1]) + (i2 - this.lx[j1]) * (i2 - this.lx[j1])));
                            this.lzy[j1] = -((int) ((double) (90 + s2) - Math.atan((double) l3 / (double) (k2 - this.ly[j1])) / 0.017453292519943295D));
                            ++this.nf[j1];
                        }
                    }
                    this.lx[j1] -= (int) ((float) this.lspeed[j1] * conto.m.cs.getsin(this.lxz[j1]) * conto.m.cs.getcos(this.lzy[j1]));
                    this.lz[j1] += (int) ((float) this.lspeed[j1] * conto.m.cs.getcos(this.lxz[j1]) * conto.m.cs.getcos(this.lzy[j1]));
                    this.ly[j1] -= (int) ((float) this.lspeed[j1] * conto.m.cs.getsin(this.lzy[j1]));
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
        }
        conto.x -= (int) (this.speed * conto.m.cs.getsin(conto.xz) * conto.m.cs.getcos(conto.zy));
        conto.z += (int) (this.speed * conto.m.cs.getcos(conto.xz) * conto.m.cs.getcos(conto.zy));
        conto.y -= (int) (this.speed * conto.m.cs.getsin(conto.zy));
        if (this.tcnt > this.turnat) {
            if (this.trgt != 0) {
                this.trgt = 0;
            }
            short s3 = 0;
            if (contos[j + 4].x - conto.x > 0) {
                s3 = 180;
            }
            this.gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[j + 4].z - conto.z) / (double) (contos[j + 4].x - conto.x)) / 0.017453292519943295D);
            this.turnat = (int) (Math.random() * 200.0D);
            j1 = this.getcpy(contos[j + 4], conto);
            if (j1 < 1500 && j1 > 0) {
                if (Math.random() > 0.5D) {
                    this.gxz += (int) (70.0D + Math.random() * 20.0D);
                } else {
                    this.gxz -= (int) (70.0D + Math.random() * 20.0D);
                }
            } else {
                this.gxz += (int) (Math.random() * 40.0D - 20.0D);
                this.trgt = 1;
            }
            j1 = this.getcpy(contos[i], conto);
            if (j1 < 15000 && j1 > 0 && !contos[i].exp) {
                if (this.attack == 0) {
                    if (Math.random() > 0.5D) {
                        this.attack = 1;
                    } else {
                        this.attack = 2;
                    }
                }
                if (this.attack == 1) {
                    s3 = 0;
                    if (contos[i].x - conto.x > 0) {
                        s3 = 180;
                    }
                    this.gxz = (int) ((double) (90 + s3) + Math.atan((double) (contos[i].z - conto.z) / (double) (contos[i].x - conto.x)) / 0.017453292519943295D);
                    this.turnat = (int) (Math.random() * 3.0D);
                    this.trgt = 2;
                }
            } else if (this.attack != 0) {
                this.attack = 0;
            }
            if (this.gxz >= 360) {
                this.gxz -= 360;
            }
            if (this.gxz < 0) {
                this.gxz += 360;
            }
            this.tcnt = 0;
        } else {
            ++this.tcnt;
        }
        if (conto.hit && Math.random() > 0.5D) {
            this.attack = 1;
            this.turnat = (int) (Math.random() * 10.0D);
        }
        if (this.u.fire) {
            this.u.fire = false;
        }
        if (this.trgt == 1 && this.trgxz < 90) {
            j1 = this.getcpy(contos[j + 4], conto);
            if (j1 > 0 && j1 < 10000) {
                this.u.fire = true;
            }
        }
        if (this.trgt == 2 && this.trgxz < 90) {
            this.u.fire = true;
        }
        if (this.responce) {
            if (this.u.left) {
                this.u.left = false;
            }
            if (this.u.right) {
                this.u.right = false;
            }
            for (k1 = conto.xz; k1 >= 360; k1 -= 360) {
                ;
            }
            while (k1 < 0) {
                k1 += 360;
            }
            if (Math.abs(k1 - this.gxz) > 5) {
                if (k1 > 270 && this.gxz < 90) {
                    this.u.left = true;
                    this.trgxz = 360 - k1 + this.gxz;
                } else if (k1 < 90 && this.gxz > 270) {
                    this.u.right = true;
                    this.trgxz = 360 - this.gxz + k1;
                } else if (k1 < this.gxz) {
                    this.u.left = true;
                    this.trgxz = this.gxz - k1;
                } else {
                    this.u.right = true;
                    this.trgxz = k1 - this.gxz;
                }
            }
            this.responce = false;
        } else {
            this.responce = true;
        }
    }

    public void dosmokes(Graphics graphics, ContO conto) {
        if (conto.y > 200) {
            if (this.smoke && !conto.exp && this.sms[this.ns] == -1) {
                this.sx[this.ns] = conto.x + (int) (Math.random() * 150.0D - 75.0D);
                this.sy[this.ns] = conto.y + 10;
                this.sz[this.ns] = conto.z;
                this.sxz[this.ns] = conto.xz;
                this.sms[this.ns] = 0;
                ++this.ns;
                if (this.ns == 4) {
                    this.ns = 0;
                }
                this.smoke = false;
            }
            int i = 0;
            do {
                if (this.sms[i] != -1) {
                    if (this.sms[i] < 5) {
                        this.lsr.gsmoke(graphics, this.sx[i], this.sy[i], this.sz[i], this.sxz[i], 0, this.sms[i]);
                    }
                    this.sy[i] -= 10;
                    ++this.sms[i];
                    if (this.sms[i] == 10) {
                        this.sms[i] = -1;
                    }
                }
                ++i;
            } while (i < 4);
        }
    }

    public void reset(int i, int j) {
        this.rspeed = i;
        this.pexp = false;
        this.ltyp = j;
        int k = 0;
        do {
            this.lstage[k] = 0;
            ++k;
        } while (k < 20);
    }

    public  Tank(Medium var1) {
        this.lsr = new Lasers(var1);
        int var2 = 0;
        do {
            this.sms[var2] = -1;
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
                                conto.nhits += this.lsr.damg[this.ltyp];
                            }
                        }
                        if (conto.pcol != 0) {
                            for (int k = 0; k < conto.npl; ++k) {
                                for (int l = 0; l < conto.p[k].n; ++l) {
                                    if (!conto.hit && (this.lx[i] - (conto.x + conto.p[k].ox[l])) * (this.lx[i] - (conto.x + conto.p[k].ox[l])) + (this.ly[i] - (conto.y + conto.p[k].oy[l])) * (this.ly[i] - (conto.y + conto.p[k].oy[l])) + (this.lz[i] - (conto.z + conto.p[k].oz[l])) * (this.lz[i] - (conto.z + conto.p[k].oz[l])) < this.lsr.rads[this.ltyp] * 10 / conto.pcol * (this.lsr.rads[this.ltyp] * 10 / conto.pcol)) {
                                        this.lhit[i] = 1;
                                        if (conto.maxhits != -1) {
                                            conto.hit = true;
                                            conto.nhits += this.lsr.damg[this.ltyp];
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

    public int getcpy(ContO conto, ContO conto2) {
        return (conto.x - conto2.x) / 100 * ((conto.x - conto2.x) / 100) + (conto.y - conto2.y) / 100 * ((conto.y - conto2.y) / 100) + (conto.z - conto2.z) / 100 * ((conto.z - conto2.z) / 100);
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
