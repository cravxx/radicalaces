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
      int var1 = 0;

      do {
         int var2 = 0;

         do {
            if(var2 != var1) {
               this.projf *= (float)(Math.sqrt((double)((this.ox[var1] - this.ox[var2]) * (this.ox[var1] - this.ox[var2]) + (this.oz[var1] - this.oz[var2]) * (this.oz[var1] - this.oz[var2]))) / 100.0D);
            }

            ++var2;
         } while(var2 < 3);

         ++var1;
      } while(var1 < 3);

      this.projf /= 3.0F;
   }

   public int ys(int var1, int var2) {
      if(var2 < 10) {
         var2 = 10;
      }

      return (var2 - this.m.focus_point) * (this.m.cy - var1) / var2 + var1;
   }

   public Plane(Medium var1, int[] var2, int[] var3, int[] var4, int var5, int[] var6) {
      this.m = var1;
      this.n = var5;
      this.ox = new int[this.n];
      this.oz = new int[this.n];
      this.oy = new int[this.n];

      int var7;
      for(var7 = 0; var7 < this.n; ++var7) {
         this.ox[var7] = var2[var7];
         this.oy[var7] = var4[var7];
         this.oz[var7] = var3[var7];
      }

      var7 = 0;

      do {
         this.c[var7] = var6[var7];
         ++var7;
      } while(var7 < 3);

      var7 = 0;

      do {
         int var8 = 0;

         do {
            if(var8 != var7) {
               this.deltaf *= (float)(Math.sqrt((double)((this.ox[var8] - this.ox[var7]) * (this.ox[var8] - this.ox[var7]) + (this.oy[var8] - this.oy[var7]) * (this.oy[var8] - this.oy[var7]) + (this.oz[var8] - this.oz[var7]) * (this.oz[var8] - this.oz[var7]))) / 100.0D);
            }

            ++var8;
         } while(var8 < 3);

         ++var7;
      } while(var7 < 3);

      this.deltaf /= 3.0F;
   }

   public void d(Graphics var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, boolean var10) {
      if(this.exp != 7) {
         int[] var11 = new int[this.n];
         int[] var12 = new int[this.n];
         int[] var13 = new int[this.n];

         int var14;
         for(var14 = 0; var14 < this.n; ++var14) {
            var11[var14] = this.ox[var14] + var2;
            var13[var14] = this.oy[var14] + var3;
            var12[var14] = this.oz[var14] + var4;
         }

         this.rot(var11, var13, var2, var3, var6, this.n);
         this.rot(var13, var12, var3, var4, var7, this.n);
         this.rot(var11, var12, var2, var4, var5, this.n);
         if(this.exp == 2) {
            this.sdx = (int)(Math.random() * 100.0D - 50.0D);
            this.sdz = (int)(Math.random() * 100.0D - 50.0D);
            this.sdy = Math.random() * 100.0D - 50.0D;
            this.sx[0] = this.ofcx + var11[this.nx] + 2 - var2;
            this.sx[1] = this.ofcx + var11[this.nx] - 2 - var2;
            this.sy[0] = this.ofcy + var13[this.ny] + 2 - var3;
            this.sy[1] = this.ofcy + var13[this.ny] - 2 - var3;
            this.sz[0] = this.ofcz + var12[this.nx] + 2 - var4;
            this.sz[1] = this.ofcz + var12[this.nx] - 2 - var4;
            this.sx[2] = this.sx[1] - this.sdx;
            this.sx[3] = this.sx[0] - this.sdx;
            this.sy[2] = (int)((double)this.sy[1] - this.sdy);
            this.sy[3] = (int)((double)this.sy[0] - this.sdy);
            this.sz[2] = this.sz[1] - this.sdz;
            this.sz[3] = this.sz[0] - this.sdz;
            this.sr = 255;
            this.sg = 220;
            this.exp = 3;
         }

         int[] var15;
         int[] var16;
         int var17;
         int var20;
         if(this.exp != 0) {
            this.ofx += this.adx;
            this.ofz += this.adz;
            this.ofy += (int)this.ady;

            for(var14 = 0; var14 < this.n; ++var14) {
               var11[var14] += this.ofx;
               var12[var14] += this.ofz;
               var13[var14] += this.ofy;
            }

            this.rot(var13, var12, this.ofcy + var13[this.ny], this.ofcz + var12[this.nx], this.ezy, this.n);
            this.rot(var11, var13, this.ofcx + var11[this.nx], this.ofcy + var13[this.ny], this.exy, this.n);

            for(var14 = 0; var14 < this.n; ++var14) {
               if(var13[var14] > this.m.ground) {
                  this.exp = 7;
               }
            }

            this.ezy += this.azy;
            this.exy += this.axy;
            this.ady += 0.5D;
            if(this.sy[3] < this.m.ground) {
               int[] var34 = new int[4];
               var15 = new int[4];
               var16 = new int[4];
               var17 = 0;

               do {
                  if(this.exp < 6) {
                     var34[var17] = this.sx[var17] + var2 + (int)(Math.random() * 50.0D - 25.0D);
                     var15[var17] = this.sy[var17] + var3 + (int)(Math.random() * 50.0D - 25.0D);
                     var16[var17] = this.sz[var17] + var4 + (int)(Math.random() * 50.0D - 25.0D);
                     if(this.exp >= 4) {
                        ++this.exp;
                     }
                  } else {
                     var34[var17] = this.sx[var17] + var2;
                     var15[var17] = this.sy[var17] + var3;
                     var16[var17] = this.sz[var17] + var4;
                  }

                  this.sx[var17] += this.sdx;
                  this.sy[var17] = (int)((double)this.sy[var17] + this.sdy);
                  this.sz[var17] += this.sdz;
                  ++var17;
               } while(var17 < 4);

               this.sdy += 0.5D;
               this.rot(var34, var16, this.m.cx, this.m.cz, this.m.xz, 4);
               this.rot(var15, var16, this.m.cy, this.m.cz, this.m.zy, 4);
               int[] var36 = new int[4];
               int[] var18 = new int[4];
               boolean var19 = false;
               var20 = 0;

               do {
                  var36[var20] = this.xs(var34[var20], var16[var20]);
                  var18[var20] = this.ys(var15[var20], var16[var20]);
                  if(var18[var20] > 0 && var18[var20] < this.m.h && var36[var20] > 0 && var36[var20] < this.m.w && var16[var20] > 10 && var15[var20] < this.m.ground) {
                     var19 = true;
                  }

                  ++var20;
               } while(var20 < 4);

               if(var19 && this.sr > 111) {
                  var1.setColor(new Color(this.sr, this.sg, 111));
                  if(this.exp == 3) {
                     var1.setColor(new Color(255, 255, 255));
                     this.exp = 4;
                  }

                  var1.fillPolygon(var36, var18, 4);
                  if(this.sr > 111) {
                     this.sr -= 2;
                  }

                  if(this.sg > 111) {
                     this.sg -= 2;
                  }
               }
            }
         }

         if(var6 != 0 || var7 != 0 || this.exp != 0 || var5 != 0) {
            this.projf = 1.0F;
            var14 = 0;

            do {
               int var33 = 0;

               do {
                  if(var33 != var14) {
                     this.projf *= (float)(Math.sqrt((double)((var11[var14] - var11[var33]) * (var11[var14] - var11[var33]) + (var12[var14] - var12[var33]) * (var12[var14] - var12[var33]))) / 100.0D);
                  }

                  ++var33;
               } while(var33 < 3);

               ++var14;
            } while(var14 < 3);

            this.projf /= 3.0F;
         }

         this.rot(var11, var12, this.m.cx, this.m.cz, this.m.xz, this.n);
         boolean var35 = false;
         var15 = new int[this.n];
         var16 = new int[this.n];
         var17 = 500;

         int var37;
         for(var37 = 0; var37 < this.n; ++var37) {
            var15[var37] = this.xs(var11[var37], var12[var37]);
            var16[var37] = this.ys(var13[var37], var12[var37]);
         }

         var37 = 0;
         int var38 = 1;

         int var21;
         for(var20 = 0; var20 < this.n; ++var20) {
            for(var21 = 0; var21 < this.n; ++var21) {
               if(var20 != var21 && Math.abs(var15[var20] - var15[var21]) - Math.abs(var16[var20] - var16[var21]) < var17) {
                  var38 = var20;
                  var37 = var21;
                  var17 = Math.abs(var15[var20] - var15[var21]) - Math.abs(var16[var20] - var16[var21]);
               }
            }
         }

         if(var16[var37] < var16[var38]) {
            var20 = var37;
            var37 = var38;
            var38 = var20;
         }

         if(this.spy(var11[var37], var12[var37]) > this.spy(var11[var38], var12[var38])) {
            var35 = true;
            var20 = 0;

            for(var21 = 0; var21 < this.n; ++var21) {
               if(var12[var21] < 50 && var13[var21] > this.m.cy) {
                  var35 = false;
               } else if(var13[var21] == var13[0]) {
                  ++var20;
               }
            }

            if(var20 == this.n && var13[0] > this.m.cy) {
               var35 = false;
            }
         }

         this.rot(var13, var12, this.m.cy, this.m.cz, this.m.zy, this.n);
         boolean var39 = true;
         boolean var40 = false;
         int[] var22 = new int[this.n];
         int[] var23 = new int[this.n];
         int var24 = 0;
         int var25 = 0;
         int var26 = 0;
         int var27 = 0;
         int var28 = 0;

         int var29;
         for(var29 = 0; var29 < this.n; ++var29) {
            var22[var29] = this.xs(var11[var29], var12[var29]);
            var23[var29] = this.ys(var13[var29], var12[var29]);
            if(var23[var29] < 0 || var12[var29] < 10) {
               ++var24;
            }

            if(var23[var29] > this.m.h || var12[var29] < 10) {
               ++var25;
            }

            if(var22[var29] < 0 || var12[var29] < 10) {
               ++var26;
            }

            if(var22[var29] > this.m.w || var12[var29] < 10) {
               ++var27;
            }

            if(var12[var29] > '\uc350') {
               ++var28;
            }
         }

         if(var26 == this.n || var24 == this.n || var25 == this.n || var27 == this.n || var28 == this.n) {
            var39 = false;
         }

         if(var26 != 0 || var24 != 0 || var25 != 0 || var27 != 0 || var12[0] > 2000) {
            var40 = true;
         }

         if(var39) {
            float var41 = (float)((double)(this.projf / this.deltaf) + 0.5D);
            if((double)var41 > 1.2D) {
               var41 = 1.2F;
            }

            if(!var10) {
               if((double)var41 < 0.5D || var35) {
                  var41 = 0.5F;
               }
            } else if((double)var41 < 0.9D || var35) {
               var41 = 0.9F;
            }

            int var30;
            int var31;
            int var32;
            if(!var8) {
               if(this.m.er == 0) {
                  var30 = (int)((float)this.c[0] * var41);
               } else {
                  var30 = this.c[0];
               }

               if(var30 > 225) {
                  var30 = 225;
               }

               if(this.m.eg == 0) {
                  var31 = (int)((float)this.c[1] * var41);
               } else {
                  var31 = this.c[1];
               }

               if(var31 > 225) {
                  var31 = 225;
               }

               if(this.m.eb == 0) {
                  var32 = (int)((float)this.c[2] * var41);
               } else {
                  var32 = this.c[2];
               }

               if(var32 > 225) {
                  var32 = 225;
               }
            } else {
               var30 = (int)(400.0F * var41);
               if(var30 > 255) {
                  var30 = 255;
               }

               var31 = (int)(400.0F * var41);
               if(var31 > 255) {
                  var31 = 255;
               }

               var32 = (int)(400.0F * var41);
               if(var32 > 255) {
                  var32 = 255;
               }
            }

            var1.setColor(new Color(var30, var31, var32));
            if(!var9) {
               var1.fillPolygon(var22, var23, this.n);
            }

            if(!var40) {
               if(!var9) {
                  var30 -= 15;
                  if(var30 < 0) {
                     var30 = 0;
                  }

                  var31 -= 15;
                  if(var31 < 0) {
                     var31 = 0;
                  }

                  var32 -= 15;
                  if(var32 < 0) {
                     var32 = 0;
                  }

                  var1.setColor(new Color(var30, var31, var32));
               } else {
                  var1.setColor(new Color(var30 / 2, (var31 + 255) / 2, var32 / 2));
               }

               var1.drawPolygon(var22, var23, this.n);
            }
         }

         this.av = 0;

         for(var29 = 0; var29 < this.n; ++var29) {
            this.av += (int)Math.sqrt((double)((this.m.cy - var23[var29]) * (this.m.cy - var23[var29]) + (this.m.cx - var22[var29]) * (this.m.cx - var22[var29]) + var12[var29] * var12[var29]));
         }

         this.av /= this.n;
      }

   }

   public void rot(int[] var1, int[] var2, int var3, int var4, int var5, int var6) {
      if(var5 != 0) {
         for(int var7 = 0; var7 < var6; ++var7) {
            int var8 = var1[var7];
            int var9 = var2[var7];
            var1[var7] = var3 + (int)((float)(var8 - var3) * this.m.cs.getcos(var5) - (float)(var9 - var4) * this.m.cs.getsin(var5));
            var2[var7] = var4 + (int)((float)(var8 - var3) * this.m.cs.getsin(var5) + (float)(var9 - var4) * this.m.cs.getcos(var5));
         }
      }

   }

   public int xs(int var1, int var2) {
      if(var2 < 10) {
         var2 = 10;
      }

      return (var2 - this.m.focus_point) * (this.m.cx - var1) / var2 + var1;
   }

   public void s(Graphics var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if(this.exp != 7) {
         int[] var9 = new int[this.n];
         int[] var10 = new int[this.n];
         int[] var11 = new int[this.n];

         int var12;
         for(var12 = 0; var12 < this.n; ++var12) {
            var9[var12] = this.ox[var12] + var2;
            var11[var12] = this.oy[var12] + var3;
            var10[var12] = this.oz[var12] + var4;
         }

         this.rot(var9, var11, var2, var3, var6, this.n);
         this.rot(var11, var10, var3, var4, var7, this.n);
         this.rot(var9, var10, var2, var4, var5, this.n);
         if(this.exp == 1) {
            this.adx = (int)(Math.random() * 30.0D - 15.0D);
            this.adz = (int)(Math.random() * 30.0D - 15.0D);
            this.ady = -(Math.random() * 20.0D);
            this.ofcx = (int)(Math.random() * 10.0D - 5.0D);
            this.ofcy = (int)(Math.random() * 10.0D - 5.0D);
            this.ofcz = (int)(Math.random() * 10.0D - 5.0D);
            this.nx = (int)(Math.random() * (double)this.n);
            this.ny = (int)(Math.random() * (double)this.n);
            this.nz = (int)(Math.random() * (double)this.n);
            this.azy = (int)(Math.random() * 30.0D - 15.0D);
            this.axy = (int)(Math.random() * 30.0D - 15.0D);
            this.exy = 0;
            this.ezy = 0;
            this.ofx = 0;
            this.ofy = 0;
            this.ofz = 0;
            this.exp = 2;
         }

         if(this.exp != 0) {
            this.ofx += this.adx;
            this.ofz += this.adz;
            this.ofy += (int)this.ady;

            for(var12 = 0; var12 < this.n; ++var12) {
               var9[var12] += this.ofx;
               var10[var12] += this.ofz;
               var11[var12] += this.ofy;
            }

            this.rot(var11, var10, this.ofcy + var11[this.ny], this.ofcz + var10[this.nz], this.ezy, this.n);
            this.rot(var9, var11, this.ofcx + var9[this.nx], this.ofcy + var11[this.nx], this.exy, this.n);
         }

         var12 = 0;

         for(int var13 = 0; var13 < this.n; ++var13) {
            if(var11[var13] >= this.m.ground) {
               ++var12;
            } else {
               var11[var13] = this.m.ground;
            }
         }

         if(var12 != this.n) {
            this.rot(var9, var10, this.m.cx, this.m.cz, this.m.xz, this.n);
            this.rot(var11, var10, this.m.cy, this.m.cz, this.m.zy, this.n);
            boolean var17 = false;
            int[] var14 = new int[this.n];
            int[] var15 = new int[this.n];

            for(int var16 = 0; var16 < this.n; ++var16) {
               var14[var16] = this.xs(var9[var16], var10[var16]);
               var15[var16] = this.ys(var11[var16], var10[var16]);
               if(var15[var16] > 0 && var15[var16] < this.m.h && var14[var16] > 0 && var14[var16] < this.m.w && var10[var16] > 10 && var10[var16] < '\uc350') {
                  var17 = true;
               }
            }

            if(var17) {
               if(!var8) {
                  var1.setColor(new Color(60, 54, 42));
               } else {
                  var1.setColor(new Color(60, 60, 60));
               }

               var1.fillPolygon(var14, var15, this.n);
            }
         }
      }

   }

   public int spy(int var1, int var2) {
      return (int)Math.sqrt((double)((var1 - this.m.cx) * (var1 - this.m.cx) + var2 * var2));
   }
}
