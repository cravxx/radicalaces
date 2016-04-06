import java.awt.Graphics;

public class userCraft {

   int rspeed = 0;
   float speed = 0.0F;
   int rlift = 0;
   double lift = 0.0D;
   boolean pexp = false;
   int ltyp = 0;
   int[] maxspeed = new int[]{120, 100, 90, 80, 76};
   int[] elev = new int[]{1, 2, 1, 1, 1};
   int[] trnn = new int[]{0, 0, 1, 2, 1};
   int[] dnjm = new int[]{7, 5, 4, 3, 4};
   String[] name = new String[]{"E-7 Sky Bullet", "BP-6 Hammer Head", "E-9 Dragon Bird", "EXA-1 Destroyer", "Silver F-51 Legend"};
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


   public void preform(Control var1, ContO var2, ContO[] var3, int[] var4, int var5) {
      int var6;
      for(var6 = Math.abs(var2.zy); var6 > 360; var6 -= 360) {
         ;
      }

      byte var7 = 1;
      if(var6 > 90 && var6 < 270) {
         var7 = -1;
      }

      int var8;
      int var9;
      int var10;
      int var11;
      int var14;
      int var15;
      if(var2.y < 207) {
         if(var1.up) {
            var2.zy -= (int)((float)(4 + this.elev[this.ltyp]) * var2.m.cs.getcos(var2.xy));
            var2.xz += (int)((float)(var7 * (2 + this.elev[this.ltyp])) * var2.m.cs.getsin(var2.xy));
         }

         if(var1.down) {
            var2.zy += (int)((float)(4 + this.elev[this.ltyp]) * var2.m.cs.getcos(var2.xy));
            var2.xz -= (int)((float)(var7 * (2 + this.elev[this.ltyp])) * var2.m.cs.getsin(var2.xy));
         }
      } else {
         for(var8 = Math.abs(var2.zy); var8 > 90; var8 -= 180) {
            ;
         }

         for(var9 = Math.abs(var2.xy); var9 > 90; var9 -= 180) {
            ;
         }

         for(var10 = Math.abs(var2.zy); var10 > 270; var10 -= 360) {
            ;
         }

         for(var11 = Math.abs(var2.xy); var11 > 270; var11 -= 360) {
            ;
         }

         boolean var12 = Math.abs(var10) < 90 && Math.abs(var11) < 90 || Math.abs(var10) > 90 && Math.abs(var11) > 90;
         boolean var13 = Math.abs(var8) > 30 || Math.abs(var9) > 30;
         if((!var12 || var13) && !var2.exp) {
            var2.exp = true;
            var2.y = 170;
            this.speed = 30.0F;
            this.pexp = true;
         }

         for(var14 = Math.abs(var2.zy); var14 > 270; var14 -= 360) {
            ;
         }

         if(var14 > 90) {
            var2.xy = 180;
         } else {
            var2.xy = 0;
         }

         for(var15 = var2.zy; var15 > 90; var15 -= 180) {
            ;
         }

         while(var15 < -90) {
            var15 += 180;
         }

         if(var15 > 0) {
            var2.zy += -1;
            this.smoke = true;
         }

         if(var15 < 0) {
            ++var2.zy;
            this.smoke = true;
         }

         if(this.speed > 10.0F && var1.down) {
            var2.zy += (int)(5.0F * var2.m.cs.getcos(var2.xy));
         }
      }

      if(var1.left) {
         if(var2.y < 207) {
            var2.xy -= 10;
         } else {
            var2.xz += 2;
         }
      }

      if(var1.right) {
         if(var2.y < 207) {
            var2.xy += 10;
         } else {
            var2.xz -= 2;
         }
      }

      var8 = (int)((float)(var7 * (3 + this.trnn[this.ltyp])) * var2.m.cs.getsin(var2.xy));
      var2.xz -= var8;
      this.rlift = (int)(this.speed * var2.m.cs.getcos(var2.zy) * var2.m.cs.getcos(var2.xy)) - 40;
      if(this.lift < (double)this.rlift) {
         this.lift += 0.5D;
      }

      if(this.lift > (double)this.rlift) {
         this.lift -= 0.5D;
      }

      if(this.lift < (double)(-(50.0F - this.speed / 2.0F))) {
         this.lift = (double)(-(50.0F - this.speed / 2.0F));
      }

      var9 = (int)(5.0F * var2.m.cs.getcos(var2.zy) * var2.m.cs.getcos(var2.xy));
      if(this.lift > (double)var9) {
         this.lift = (double)var9;
      }

      var2.y -= (int)this.lift;
      if(var2.x < -40000) {
         var2.x = -40000;
         if(var8 <= 0) {
            var2.xz += 5;
         } else {
            var2.xz -= 5;
         }
      }

      if(var2.x > '\u9c40') {
         var2.x = '\u9c40';
         if(var8 <= 0) {
            var2.xz += 5;
         } else {
            var2.xz -= 5;
         }
      }

      if(var2.z > '\u9c40') {
         var2.z = '\u9c40';
         if(var8 <= 0) {
            var2.xz += 5;
         } else {
            var2.xz -= 5;
         }
      }

      if(var2.z < -40000) {
         var2.z = -40000;
         if(var8 <= 0) {
            var2.xz += 5;
         } else {
            var2.xz -= 5;
         }
      }

      if(!this.pexp && var2.exp) {
         if(this.speed > 40.0F) {
            this.speed = -15.0F;
            this.pexp = true;
         } else if(var2.nhits > var2.maxhits) {
            this.pexp = true;
         } else {
            var2.exp = false;
            this.speed = -(((float)this.rspeed + this.speed) / 2.0F);
         }
      }

      if(this.pexp) {
         if(this.speed > 0.0F) {
            this.speed = (float)((double)this.speed - 0.3D);
         }

         if(this.speed < 0.0F) {
            this.speed = (float)((double)this.speed + 0.3D);
         }
      } else {
         if(this.speed > (float)this.rspeed) {
            if(this.speed > (float)this.maxspeed[this.ltyp]) {
               this.speed -= (this.speed - (float)this.rspeed) / 20.0F;
            } else {
               this.speed = (float)((double)this.speed - 0.5D);
            }
         }

         if(this.speed < (float)this.rspeed) {
            ++this.speed;
         }
      }

      if(var2.nhits > var2.maxhits - var2.maxhits / 6 && !var2.exp) {
         if(this.speed > 60.0F) {
            this.speed = 60.0F;
         }

         var2.xz += (int)(Math.random() * (double)(this.speed / 10.0F) - (double)(this.speed / 20.0F));
         var2.zy += (int)(Math.random() * (double)(this.speed / 10.0F) - (double)(this.speed / 20.0F));
      }

      if(var1.plus && this.rspeed < this.maxspeed[this.ltyp]) {
         this.rspeed += 2;
      }

      if(var1.mins && this.rspeed > 0) {
         this.rspeed -= 2;
      }

      if(var1.jump != 0 && this.njumps != 0) {
         if(var1.jump == 1) {
            this.speed = 400.0F;
            var1.jump = 2;
            var2.m.jumping = 5;
         }

         if(var2.m.jumping == 0) {
            this.speed = 800.0F;
            var1.jump = 0;
            this.njumps += -1;
         }
      }

      if(var1.fire && !var2.exp) {
         if(this.skip && this.bulkc < this.lsr.srate[this.ltyp]) {
            this.lx[this.nl] = var2.x;
            this.ly[this.nl] = var2.y;
            this.lz[this.nl] = var2.z;
            this.lxz[this.nl] = var2.xz;
            this.lzy[this.nl] = var2.zy;
            this.lxy[this.nl] = var2.xy;
            if(this.ly[this.nl] > 215) {
               this.ly[this.nl] = 215;
            }

            this.lspeed[this.nl] = (int)((float)this.lsr.speed[this.ltyp] + this.speed);
            this.lstage[this.nl] = 1;
            this.lhit[this.nl] = 0;
            ++this.nl;
            if(this.nl == 20) {
               this.nl = 0;
            }

            this.skip = false;
         } else if(!this.skip) {
            this.skip = true;
         }

         ++this.bulkc;
         if(this.bulkc > 12) {
            this.bulkc = 0;
         }
      }

      var10 = 0;
      var11 = 0;

      do {
         if(this.lstage[var11] != 0) {
            ++var10;
            if(this.ly[var11] > 240 && this.lhit[var11] == 0) {
               this.lhit[var11] = 1;
            }

            if(this.lhit[var11] == 0) {
               if(this.lstage[var11] > 10) {
                  int var19 = 22500;
                  int var20 = -1;

                  for(var14 = 1; var14 < var5; ++var14) {
                     var15 = this.getpy(var3[var4[var14]].x, var3[var4[var14]].y, var3[var4[var14]].z, var11);
                     if(var15 < var19 && var15 > 0 && !var3[var4[var14]].exp) {
                        var19 = var15;
                        var20 = var14;
                     }
                  }

                  if(var20 != -1) {
                     if(this.lspeed[var11] > 230) {
                        this.lspeed[var11] = 230;
                     }

                     var14 = var3[var4[var20]].x;
                     var15 = var3[var4[var20]].z;
                     int var16 = var3[var4[var20]].y;
                     short var17 = 0;
                     if(var14 - this.lx[var11] > 0) {
                        var17 = 180;
                     }

                     this.lxz[var11] = (int)((double)(90 + var17) + Math.atan((double)(var15 - this.lz[var11]) / (double)(var14 - this.lx[var11])) / 0.017453292519943295D);
                     short var21 = 0;
                     if(var16 - this.ly[var11] < 0) {
                        var21 = -180;
                     }

                     int var18 = (int)Math.sqrt((double)((var15 - this.lz[var11]) * (var15 - this.lz[var11]) + (var14 - this.lx[var11]) * (var14 - this.lx[var11])));
                     this.lzy[var11] = -((int)((double)(90 + var21) - Math.atan((double)var18 / (double)(var16 - this.ly[var11])) / 0.017453292519943295D));
                  }
               }

               this.lx[var11] -= (int)((float)this.lspeed[var11] * var2.m.cs.getsin(this.lxz[var11]) * var2.m.cs.getcos(this.lzy[var11]));
               this.lz[var11] += (int)((float)this.lspeed[var11] * var2.m.cs.getcos(this.lxz[var11]) * var2.m.cs.getcos(this.lzy[var11]));
               this.ly[var11] -= (int)((float)this.lspeed[var11] * var2.m.cs.getsin(this.lzy[var11]));
               ++this.lstage[var11];
               if(this.lstage[var11] > 80) {
                  this.lstage[var11] = 0;
               }
            }
         }

         ++var11;
      } while(var11 < 20);

      if(var10 != 0) {
         if(!var2.fire) {
            var2.fire = true;
         }
      } else if(var2.fire) {
         var2.fire = false;
         this.bulkc = 0;
      }

      var2.x -= (int)(this.speed * var2.m.cs.getsin(var2.xz) * var2.m.cs.getcos(var2.zy));
      var2.z += (int)(this.speed * var2.m.cs.getcos(var2.xz) * var2.m.cs.getcos(var2.zy));
      var2.y -= (int)(this.speed * var2.m.cs.getsin(var2.zy));
      if(var2.y > 215) {
         var2.y = 215;
      }

      if(var2.y < -25000) {
         var2.y = -25000;
      }

      if(this.ester == 0) {
         if(var2.x > 2800 && var2.x < 3200 && var2.z > -2100 && var2.z < -1900 && var2.y > -30) {
            this.ester = 1;
            var2.nhits = 0;
            var1.jump = 0;
            this.njumps = this.dnjm[this.ltyp];
         }
      } else {
         if(this.ester < 13) {
            if(this.ltyp == 0) {
               if(var2.m.er == 0) {
                  var2.m.er = 1;
               } else {
                  var2.m.er = 0;
               }
            }

            if(this.ltyp == 1) {
               if(var2.m.eg == 0) {
                  var2.m.eg = 1;
               } else {
                  var2.m.eg = 0;
               }
            }

            if(this.ltyp == 2) {
               if(var2.m.eb == 0) {
                  var2.m.eb = 1;
               } else {
                  var2.m.eb = 0;
               }
            }

            if(this.ltyp == 3) {
               if(var2.m.er == 0) {
                  var2.m.er = 1;
                  var2.m.eg = 1;
               } else {
                  var2.m.er = 0;
                  var2.m.eg = 0;
               }
            }

            if(this.ltyp == 4) {
               if(var2.m.eb == 0) {
                  var2.m.eb = 1;
                  var2.m.eg = 1;
               } else {
                  var2.m.eb = 0;
                  var2.m.eg = 0;
               }
            }
         }

         if(this.ester == 1) {
            var2.wire = true;
         }

         if(this.ester == 3) {
            var2.wire = false;
         }

         ++this.ester;
         if(this.ester == 45) {
            this.ester = 0;
         }
      }

   }

   public void dosmokes(Graphics var1, ContO var2) {
      if(!var2.exp) {
         int var3;
         if(var2.nhits > var2.maxhits - var2.maxhits / 3) {
            if(this.dms[this.nd] == -1) {
               this.dx[this.nd] = var2.x + (int)(Math.random() * 60.0D - 30.0D);
               this.dy[this.nd] = var2.y;
               this.dz[this.nd] = var2.z;
               this.dxz[this.nd] = var2.xz;
               this.dzy[this.nd] = var2.zy;
               this.dms[this.nd] = 0;
               ++this.nd;
               if(this.nd == 4) {
                  this.nd = 0;
               }
            }

            var3 = 0;

            do {
               if(this.dms[var3] != -1) {
                  if(this.dms[var3] < 4) {
                     this.lsr.hsmoke(var1, this.dx[var3], this.dy[var3], this.dz[var3], this.dxz[var3], this.dzy[var3], this.dms[var3]);
                  }

                  this.dy[var3] -= 15;
                  ++this.dms[var3];
                  if(this.dms[var3] >= 7) {
                     this.dms[var3] = -1;
                  }
               }

               ++var3;
            } while(var3 < 4);
         }

         if(this.smoke && var2.y > 200 && this.sms[this.ns] == -1) {
            this.sx[this.ns] = var2.x + (int)(Math.random() * 80.0D - 40.0D);
            this.sy[this.ns] = var2.y + 15;
            this.sz[this.ns] = var2.z;
            this.sxz[this.ns] = var2.xz;
            this.szy[this.ns] = var2.zy;
            this.sms[this.ns] = 0;
            ++this.ns;
            if(this.ns == 4) {
               this.ns = 0;
            }

            this.smoke = false;
         }

         var3 = 0;

         do {
            if(this.sms[var3] != -1) {
               if(this.sms[var3] < 4) {
                  this.lsr.gsmoke(var1, this.sx[var3], this.sy[var3], this.sz[var3], this.sxz[var3], this.szy[var3], this.sms[var3]);
               }

               this.sy[var3] -= 15;
               ++this.sms[var3];
               if(this.sms[var3] == 10) {
                  this.sms[var3] = -1;
               }
            }

            ++var3;
         } while(var3 < 4);
      }

   }

   public void reset(int var1) {
      this.rspeed = 0;
      this.speed = 0.0F;
      this.rlift = 0;
      this.lift = 0.0D;
      this.pexp = false;
      this.ltyp = var1;
      this.njumps = this.dnjm[var1];
      int var2 = 0;

      do {
         this.lstage[var2] = 0;
         ++var2;
      } while(var2 < 20);

   }

   public userCraft(Medium var1) {
      this.lsr = new Lasers(var1);
      int var2 = 0;

      do {
         this.sms[var2] = -1;
         ++var2;
      } while(var2 < 4);

      var2 = 0;

      do {
         this.dms[var2] = -1;
         ++var2;
      } while(var2 < 4);

   }

   public void lasercolid(ContO var1) {
      if(!var1.exp && !var1.out) {
         int var2 = 0;

         do {
            if(this.lstage[var2] != 0 && this.lhit[var2] == 0) {
               int var3 = this.getpy(var1.x, var1.y, var1.z, var2);
               if(var3 < var1.maxR / 10 * (var1.maxR / 10) && var3 > 0) {
                  if(var1.rcol != 0 && var3 < var1.maxR / (10 * var1.rcol) * (var1.maxR / (10 * var1.rcol)) + this.lsr.rads[this.ltyp] / 10 * (this.lsr.rads[this.ltyp] / 10)) {
                     this.lhit[var2] = 1;
                     if(var1.maxhits != -1) {
                        var1.hit = true;
                        if(Math.random() > 0.5D) {
                           var1.nhits += this.lsr.damg[this.ltyp];
                        } else {
                           var1.nhits += 2;
                        }
                     }
                  }

                  if(var1.pcol != 0) {
                     for(int var4 = 0; var4 < var1.npl; ++var4) {
                        for(int var5 = 0; var5 < var1.p[var4].n; ++var5) {
                           if(!var1.hit && (this.lx[var2] - (var1.x + var1.p[var4].ox[var5])) * (this.lx[var2] - (var1.x + var1.p[var4].ox[var5])) + (this.ly[var2] - (var1.y + var1.p[var4].oy[var5])) * (this.ly[var2] - (var1.y + var1.p[var4].oy[var5])) + (this.lz[var2] - (var1.z + var1.p[var4].oz[var5])) * (this.lz[var2] - (var1.z + var1.p[var4].oz[var5])) < this.lsr.rads[this.ltyp] * 10 / var1.pcol * (this.lsr.rads[this.ltyp] * 10 / var1.pcol)) {
                              this.lhit[var2] = 1;
                              if(var1.maxhits != -1) {
                                 var1.hit = true;
                                 if(Math.random() > 0.5D) {
                                    var1.nhits += this.lsr.damg[this.ltyp];
                                 } else {
                                    var1.nhits += 2;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }

            ++var2;
         } while(var2 < 20);
      }

   }

   public int getpy(int var1, int var2, int var3, int var4) {
      return (var1 - this.lx[var4]) / 10 * ((var1 - this.lx[var4]) / 10) + (var2 - this.ly[var4]) / 10 * ((var2 - this.ly[var4]) / 10) + (var3 - this.lz[var4]) / 10 * ((var3 - this.lz[var4]) / 10);
   }

   public void dl(Graphics var1) {
      int var2 = 0;

      do {
         if(this.lstage[var2] != 0) {
            this.lsr.d(var1, this.ltyp, this.lx[var2], this.ly[var2], this.lz[var2], this.lxz[var2], this.lzy[var2], this.lxy[var2], this.lhit[var2]);
            if(this.lhit[var2] != 0) {
               ++this.lhit[var2];
               if(this.lhit[var2] > 2) {
                  this.lstage[var2] = 0;
               }
            }
         }

         ++var2;
      } while(var2 < 20);

   }
}
