import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class xtGraphics extends Panel {

   Medium m;
   FontMetrics ftm;
   boolean goodsun = false;
   int cl = 1;
   Image radar;
   Image stube;
   Image sback;
   Image destr;
   Image mback;
   Image lay;
   Image complete;
   Image main;
   Image rad;
   Image inst1;
   Image inst2;
   Image inst3;
   Image mars;
   Image text;
   Image[] as = new Image[5];
   int[] pix = new int[180000];
   int[] bpix = new int[180000];
   int[] mpix = new int[180000];
   int[] opix = new int[180000];
   int[] ppix = new int[180000];
   int cnt = 0;
   boolean flik = false;
   int cnts = 10;
   String[] mname = new String[19];
   int[] cnte = new int[19];
   int cntf = 0;
   boolean left = false;
   int wcnt = 0;
   int rcnt = 0;
   int cnty = 0;
   int fase = -8;
   int selected = 4;
   int select = 0;
   int[] ws = new int[]{62, 73, 59, 40, 50};
   boolean frst = false;
   int oldfase = -5;
   int nb = 0;
   int[] ob = new int[3];
   String[] nam = new String[3];
   boolean[] tnk = new boolean[3];
   int[] obx = new int[3];
   int[] oby = new int[3];
   int[] obz = new int[3];
   int sgame = -1;
   int level = 0;
   boolean[] dest = new boolean[10];
   boolean mcomp = false;
   int tcnt = 1;


   public void denter(Graphics var1, int var2, ContO[] var3, userCraft var4, Control var5) {
      int var6;
      if(this.fase == 4) {
         var6 = 0;

         do {
            var3[var6].out = false;
            var3[var6].wire = true;
            var3[var6].x = 0;
            var3[var6].y = 180;
            var3[var6].z = 0;
            var3[var6].xy = 90;
            ++var6;
         } while(var6 < 5);

         this.m.x = -100;
         this.m.y = 0;
         this.m.ground = 950 - this.m.y;
         this.m.z = -50;
         this.m.xz = -90;
         this.m.zy = 0;
         var3[0].zy = 0;
         var1.setColor(new Color(255, 255, 0));
         var6 = 0;

         do {
            var1.drawLine(var6 * 2, 0, var6 * 2, 360);
            ++var6;
         } while(var6 < 250);

         if(this.oldfase == 7) {
            this.fase = 7;
            this.oldfase = 0;
            this.cnt = 0;
         } else {
            this.fase = 5;
         }
      }

      if(this.fase == -8) {
         if(this.cnty < 351) {
            var1.drawImage(this.mars, 0, 0, (ImageObserver)null);
            var1.drawImage(this.text, 10, 380 - this.cnty, (ImageObserver)null);
            if(this.cnty != 350) {
               ++this.cnty;
            } else {
               this.drawcs(var1, 345, "Press Enter to continue", 225, 225, 225, true);
               this.cnty = 351;
            }
         }

         if(var5.space) {
            this.fase = -5;
            if(this.sgame == 1) {
               this.select = 1;
            } else {
               this.select = 2;
            }

            var5.space = false;
         }
      }

      if(this.fase == -7) {
         var1.drawImage(this.inst1, 0, 0, (ImageObserver)null);
         this.drawcs(var1, 354, "Press Enter to continue >", 170, 170, 170, false);
         if(var5.space) {
            this.fase = -6;
            var5.space = false;
         }
      }

      if(this.fase == -6) {
         var1.drawImage(this.inst2, 0, 0, (ImageObserver)null);
         this.drawcs(var1, 354, "Press Enter to continue >", 170, 170, 170, false);
         if(var5.space) {
            this.fase = -55;
            var5.space = false;
         }
      }

      if(this.fase == -55) {
         var1.drawImage(this.inst3, 0, 0, (ImageObserver)null);
         this.drawcs(var1, 354, "Press Enter to continue >", 170, 170, 170, false);
         if(var5.space) {
            this.fase = this.oldfase;
            var5.space = false;
         }
      }

      if(this.fase == -5) {
         var1.drawImage(this.main, 0, 0, (ImageObserver)null);
         if(this.cnt < 7) {
            var1.drawImage(this.as[this.select], 25, 283, (ImageObserver)null);
            var1.drawImage(this.as[this.select], 423, 283, (ImageObserver)null);
            ++this.cnt;
         } else {
            this.cnt = 0;
         }

         var1.setColor(new Color(225, 230, 255));
         var6 = 50 + (int)(Math.random() * 150.0D);
         var1.drawLine((int)(Math.random() * 400.0D), var6, (int)(Math.random() * 200.0D), var6);
         var6 = 50 + (int)(Math.random() * 150.0D);
         var1.drawLine(500 - (int)(Math.random() * 400.0D), var6, 500 - (int)(Math.random() * 200.0D), var6);
         if(this.cnts < -900) {
            this.cnts = 0;
            this.cntf = (int)(Math.random() * 150.0D);
         } else {
            this.cnts -= 7;
         }

         if(var5.space) {
            this.cnts = 10;
         }

         var1.drawImage(this.rad, 500 + this.cnts, 50 + this.cntf, (ImageObserver)null);
         this.drawcs(var1, 274, "Start New Game", 0, 0, 0, false);
         if(this.sgame != 0) {
            this.drawcs(var1, 289, "Resume Saved Game", 0, 0, 0, false);
         } else {
            if(var5.space && this.select == 1) {
               this.wcnt = 20;
            }

            if(this.wcnt != 0) {
               this.drawcs(var1, 289, "No Saved Game!", 100, 0, 0, false);
               this.wcnt += -1;
            } else {
               this.drawcs(var1, 289, "Resume Saved Game", 200, 200, 200, false);
            }
         }

         this.drawcs(var1, 304, "Game Controls", 0, 0, 0, false);
         this.drawcs(var1, 319, "Credits", 0, 0, 0, false);
         this.drawcs(var1, 334, "Exit Game", 0, 0, 0, false);
         if(!this.flik) {
            var1.setColor(new Color(225, 230, 255));
            this.flik = true;
            var1.drawLine(250 - this.ws[this.select], 271 + 15 * this.select, 250 + this.ws[this.select], 271 + 15 * this.select);
            var1.drawRect(250 - this.ws[this.select], 264 + 15 * this.select, this.ws[this.select] * 2, 11);
            var1.setColor(new Color(0, 0, 0));
            var1.drawLine(251 - this.ws[this.select], 271 + 15 * this.select, 255 - this.ws[this.select], 271 + 15 * this.select);
            var1.drawLine(245 + this.ws[this.select], 271 + 15 * this.select, 249 + this.ws[this.select], 271 + 15 * this.select);
         } else {
            var1.setColor(new Color(168, 183, 255));
            var1.drawRect(250 - this.ws[this.select], 264 + 15 * this.select, this.ws[this.select] * 2, 11);
            this.flik = false;
         }

         if(var5.down) {
            ++this.select;
            var5.down = false;
         }

         if(var5.up) {
            this.select += -1;
            var5.up = false;
         }

         if(this.select == 5) {
            this.select = 0;
         }

         if(this.select == -1) {
            this.select = 4;
         }

         if(var5.space) {
            if(this.select == 2) {
               this.fase = -7;
               this.oldfase = -5;
               var5.space = false;
            }

            if(this.select == 3) {
               this.fase = 4;
               var5.space = false;
            }
         }

         this.drawcs(var1, 354, "( use keyboard arrows to select and press Enter )", 170, 170, 170, false);
         if(this.frst) {
            this.frst = false;
         }
      }

      if(this.fase == -4) {
         if(var5.space) {
            this.fase = -3;
            var5.space = false;
         } else {
            var6 = 0;
            int var7 = 0;

            int var8;
            for(var8 = var2; var8 < var2 + 13; ++var8) {
               var6 += var3[var8].nhits;
               var7 += var3[var8].maxhits;
            }

            if(var6 > var7) {
               var6 = var7;
            }

            var8 = (int)((float)var6 / (float)var7 * 100.0F);
            this.drawcs(var1, 30, "The Mars Station..", 255, 255, 255, true);
            if(var8 >= 90 && !this.flik) {
               this.drawcs(var1, 60, "Damage status:  " + var8 + "%", 255, 0, 0, false);
               this.flik = true;
            } else {
               this.drawcs(var1, 60, "Damage status:  " + var8 + "%", 0, 0, 0, false);
               this.flik = false;
            }

            if(!this.frst) {
               this.drawcs(var1, 340, "Press Enter to continue", 255, 255, 255, false);
            } else {
               this.drawcs(var1, 300, "Mission " + this.level + " completed, do you wish to save game here?", 255, 255, 255, false);
               if(this.select == 0) {
                  var1.setColor(new Color(255, 255, 255));
                  var1.fillRect(220, 319, 29, 14);
                  var1.setColor(new Color(192, 192, 192));
                  var1.drawRect(220, 319, 29, 14);
               }

               if(this.select != 0) {
                  var1.setColor(new Color(255, 255, 255));
                  var1.fillRect(256, 319, 22, 14);
                  var1.setColor(new Color(192, 192, 192));
                  var1.drawRect(256, 319, 22, 14);
               }

               if(var5.up || var5.down || var5.left || var5.right) {
                  if(this.select == 0) {
                     this.select = 1;
                  } else {
                     this.select = 0;
                  }

                  var5.up = false;
                  var5.down = false;
                  var5.left = false;
                  var5.right = false;
               }

               this.drawcs(var1, 330, "Yes     No", 0, 0, 0, false);
            }
         }
      }

      if(this.fase == -3) {
         var1.setColor(new Color(225, 230, 255));
         var1.drawRect(1, 1, 497, 357);
         this.drawcs(var1, 180, "Loading Mission " + (this.level + 1) + " ...", 225, 230, 255, true);
      }

      if(this.fase == -2) {
         this.rcnt = 0;
         var6 = 0;

         do {
            var3[var6].reset();
            var3[var6].out = false;
            var3[var6].x = (var6 - this.selected) * 500;
            var3[var6].y = 180;
            var3[var6].z = 0;
            ++var6;
         } while(var6 < 5);

         this.m.x = -this.m.cx;
         this.m.y = 0;
         this.m.ground = 250 - this.m.y;
         this.m.z = -620;
         this.m.xz = 0;
         this.m.zy = 0;
         var3[0].zy = 15;
         var3[0].xy = -15;
         var3[2].xy = -30;
         var3[3].zy = -15;
         var3[1].zy = 30;

         for(var6 = 0; var6 < this.nb; ++var6) {
            this.obx[var6] = var3[this.ob[var6]].x;
            this.oby[var6] = var3[this.ob[var6]].y;
            this.obz[var6] = var3[this.ob[var6]].z;
            var3[this.ob[var6]].x = -525;
            if(this.tnk[var6]) {
               var3[this.ob[var6]].y = 95 + 305 * var6;
               var3[this.ob[var6]].zy = 0;
            } else {
               var3[this.ob[var6]].y = 55 + 305 * var6;
               var3[this.ob[var6]].zy = 20;
            }

            var3[this.ob[var6]].z = 1000;
            var3[this.ob[var6]].xy = 0;
            var3[this.ob[var6]].xz = (int)(Math.random() * 270.0D);
            var3[this.ob[var6]].out = false;
         }

         this.cmback(this.nb);
         this.fase = -1;
      }

      if(this.fase == 0) {
         if(!this.dest[this.selected]) {
            if(this.wcnt < 5) {
               var3[this.selected].wire = true;
            } else {
               var3[this.selected].wire = false;
            }

            if(this.wcnt > 9) {
               this.wcnt = 0;
            } else {
               ++this.wcnt;
            }
         }

         if(this.rcnt == 0) {
            if(var5.left) {
               this.left = true;
               this.rcnt = 1;
            }

            if(var5.right) {
               this.left = false;
               this.rcnt = 1;
            }
         } else {
            var6 = 0;

            do {
               if(var3[var6].x == 2000) {
                  var3[var6].x = -500;
               }

               if(var3[var6].x == -2000) {
                  var3[var6].x = 500;
               }

               if(this.left) {
                  var3[var6].x -= 100;
               } else {
                  var3[var6].x += 100;
               }

               ++var6;
            } while(var6 < 5);

            var3[this.selected].wire = false;
            ++this.rcnt;
            if(this.rcnt == 6) {
               this.wcnt = 7;
               this.rcnt = 0;
               if(this.left) {
                  if(this.selected != 4) {
                     ++this.selected;
                  } else {
                     this.selected = 0;
                  }
               } else if(this.selected != 0) {
                  this.selected += -1;
               } else {
                  this.selected = 4;
               }

               var3[this.selected].hit = true;
               var3[this.selected].nhits = 0;
            }
         }

         if(var5.space) {
            var3[this.selected].wire = false;
         }

         var1.drawImage(this.sback, 0, 0, (ImageObserver)null);
         var6 = 0;

         do {
            var3[var6].d(var1);
            var3[var6].xz += 2;
            ++var6;
         } while(var6 < 5);

         if(this.dest[this.selected] && this.rcnt == 0) {
            var1.drawImage(this.destr, 117, 103, (ImageObserver)null);
         }

         this.drawcs(var1, 16, "Select your Ship", 255, 255, 255, false);
         this.drawcs(var1, 354, "( use keyboard arrows to select )", 150, 150, 160, false);
         this.drawcs(var1, 265, var4.name[this.selected], 190, 200, 255, false);
         if(var5.space && this.dest[this.selected]) {
            this.drawcs(var1, 80, "Cannot Select Ship!", 255, 230, 230, true);
         }

         int[] var10 = new int[3];
         int[] var9 = new int[3];
         var1.setColor(new Color(100, 100, 100));
         if(this.rcnt == 1 && this.left) {
            var1.setColor(new Color(225, 225, 225));
         }

         var10[0] = 50;
         var9[0] = 255;
         var10[1] = 75;
         var9[1] = 250;
         var10[2] = 75;
         var9[2] = 260;
         var1.fillPolygon(var10, var9, 3);
         var1.setColor(new Color(100, 100, 100));
         if(this.rcnt == 1 && !this.left) {
            var1.setColor(new Color(225, 225, 225));
         }

         var10[0] = 450;
         var9[0] = 255;
         var10[1] = 425;
         var9[1] = 250;
         var10[2] = 425;
         var9[2] = 260;
         var1.fillPolygon(var10, var9, 3);
         var1.setColor(new Color(225, 225, 255));
         var1.drawString("Max Speed", 57, 300);
         var1.setColor(new Color(190, 200, 255));
         var1.fillRect(125, 295, (int)(100.0F * ((float)var4.maxspeed[this.selected] / 120.0F)), 4);
         var1.setColor(new Color(225, 225, 255));
         var1.drawString(" Fire Power", 57, 315);
         var1.setColor(new Color(190, 200, 255));
         var1.fillRect(125, 310, (int)(100.0F * ((float)(var4.lsr.damg[this.selected] + 2) / 6.0F)), 4);
         var1.setColor(new Color(225, 225, 255));
         var1.drawString("  Tolerance", 57, 330);
         var1.setColor(new Color(190, 200, 255));
         var1.fillRect(125, 325, (int)(100.0F * ((float)var3[this.selected].maxhits / 300.0F)), 4);
         var1.setColor(new Color(225, 225, 255));
         var1.drawString("       Turning", 285, 300);
         var1.setColor(new Color(190, 200, 255));
         var1.fillRect(355, 295, (int)(100.0F * ((float)(var4.trnn[this.selected] + 3) / 5.0F)), 4);
         var1.setColor(new Color(225, 225, 255));
         var1.drawString("     Elevation", 285, 315);
         var1.setColor(new Color(190, 200, 255));
         var1.fillRect(355, 310, (int)(100.0F * ((float)(var4.elev[this.selected] + 3) / 5.0F)), 4);
         var1.setColor(new Color(225, 225, 255));
         var1.drawString("Light Speed Jumps:  " + var4.dnjm[this.selected], 285, 330);
      }

      if(this.fase == -1) {
         var1.drawImage(this.mback, 0, 0, (ImageObserver)null);
         if(this.level == 15) {
            this.drawcs(var1, 30, "Final Mission !", 255, 255, 255, true);
         } else {
            this.drawcs(var1, 30, "Mission " + (this.level + 1), 255, 255, 255, true);
         }

         this.drawcs(var1, 60, "Incoming Enemies:", 240, 240, 220, false);

         for(var6 = 0; var6 < this.nb; ++var6) {
            var1.drawImage(this.lay, 79, 90 + 80 * var6, (ImageObserver)null);
            var3[this.ob[var6]].d(var1);
            var3[this.ob[var6]].xz += 7 + var6;
            this.drawcs(var1, 125 + 80 * var6, this.nam[var6], 0, 0, 0, false);
         }

         if(this.nb == 0) {
            this.drawcs(var1, 180, "- Error loading mission " + (this.level + 1) + " -", 255, 255, 255, false);
            this.drawcs(var1, 200, "Connection Error!", 255, 255, 255, false);
            this.drawcs(var1, 280, "Click screen or Press Enter to continue >", 180, 180, 150, true);
         } else if(this.goodsun) {
            if(this.flik) {
               this.drawcs(var1, 110 + 80 * this.nb, "Click Screen to Continue >", 180, 180, 150, true);
               this.flik = false;
            } else {
               this.drawcs(var1, 110 + 80 * this.nb, "Click Screen to Continue >", 255, 255, 240, true);
               this.flik = true;
            }
         } else {
            this.drawcs(var1, 110 + 80 * this.nb, "Click screen or Press Enter to continue >", 180, 180, 150, true);
         }

         if(!var5.canclick) {
            var5.canclick = true;
         }

         if(var5.space) {
            var5.canclick = false;
            if(this.nb != 0) {
               for(var6 = 0; var6 < this.nb; ++var6) {
                  var3[this.ob[var6]].x = this.obx[var6];
                  var3[this.ob[var6]].y = this.oby[var6];
                  var3[this.ob[var6]].z = this.obz[var6];
               }

               this.fase = 0;
            } else {
               this.fase = -5;
               if(this.sgame == 1) {
                  this.select = 1;
               } else {
                  this.select = 0;
               }
            }

            var5.space = false;
         }
      }

      if(this.fase == 1) {
         var1.drawImage(this.mback, 0, 0, (ImageObserver)null);
         if(this.frst) {
            this.frst = false;
         }

         if(var5.space) {
            this.fase = -3;
            var5.space = false;
            this.drawcs(var1, 230, "Loading Mission " + (this.level + 1) + " again...", 255, 255, 255, true);
         } else {
            if(!var5.jade) {
               this.drawcs(var1, 250, "Don\'t forget to press the  [J]  key to escape lasers...", 225, 225, 225, false);
            }

            this.drawcs(var1, 300, "Press Enter to continue", 225, 225, 225, false);
         }
      }

      if(this.fase == 2) {
         var1.drawImage(this.mback, 0, 0, (ImageObserver)null);
         if(this.alldest()) {
            this.drawcs(var1, 180, "All your ships were destroyed!", 255, 255, 255, true);
         } else {
            this.drawcs(var1, 180, "The mars station was destroyed!", 255, 255, 255, true);
         }

         this.drawcs(var1, 320, "Press Enter to continue", 225, 225, 225, true);
         if(var5.space) {
            this.fase = -5;
            if(this.alldest() && this.sgame == 1) {
               this.select = 1;
            } else {
               this.select = 0;
            }

            var5.space = false;
         }
      }

      if(this.fase == 3) {
         var1.drawImage(this.mback, 0, 0, (ImageObserver)null);
         this.drawcs(var1, 163, "Resume Game", 255, 255, 255, false);
         this.drawcs(var1, 183, "Game Controls", 255, 255, 255, false);
         this.drawcs(var1, 203, "Quit Game", 255, 255, 255, false);
         if(this.flik) {
            var1.setColor(new Color(255, 0, 0));
            this.flik = false;
         } else {
            var1.setColor(new Color(0, 128, 255));
            this.flik = true;
         }

         var1.drawRect(190, 153 + this.select * 20, 120, 11);
         if(var5.down) {
            ++this.select;
            var5.down = false;
         }

         if(var5.up) {
            this.select += -1;
            var5.up = false;
         }

         if(this.select == 3) {
            this.select = 0;
         }

         if(this.select == -1) {
            this.select = 2;
         }

         if(var5.space) {
            if(this.select == 1) {
               this.fase = -7;
               this.oldfase = 3;
               var5.space = false;
            }

            if(this.select == 2) {
               this.fase = -5;
               if(this.sgame == 1) {
                  this.select = 1;
               } else {
                  this.select = 0;
               }

               var5.space = false;
            }
         }

         this.drawcs(var1, 354, "( use keyboard arrows to select )", 210, 210, 210, false);
      }

      if(this.fase == 5 || this.fase == 6 || this.fase == 7) {
         var1.setColor(new Color(255, 255, 255));
         var1.fillRect(100, 60, 300, 190);
         var3[(int)(Math.random() * 5.0D)].d(var1);
         var6 = 0;

         do {
            var3[var6].zy += 5;
            var3[var6].xy += -1;
            ++var6;
         } while(var6 < 5);

         if(var3[0].zy == 360) {
            var3[0].zy = 0;
            var1.setColor(new Color(255, 255, 0));
            var6 = 0;

            do {
               var1.drawLine(var6 * 2, 0, var6 * 2, 360);
               ++var6;
            } while(var6 < 250);
         }

         var1.drawImage(this.rad, 93, 32, (ImageObserver)null);
         if(this.fase == 5) {
            this.drawcs(var1, 84, "Wild Polygons 3D engine by:", 0, 0, 0, false);
            this.drawcs(var1, 96, "Omar Waly", 100, 100, 100, false);
            this.drawcs(var1, 114, "3D models by:", 0, 0, 0, false);
            this.drawcs(var1, 126, "Omar Waly", 100, 100, 100, false);
            this.drawcs(var1, 144, "Game programming by:", 0, 0, 0, false);
            this.drawcs(var1, 156, "Omar Waly", 100, 100, 100, false);
            this.drawcs(var1, 174, "Graphics by:", 0, 0, 0, false);
            this.drawcs(var1, 186, "Omar Waly", 100, 100, 100, false);
            this.drawcs(var1, 204, "Sound effects by:", 0, 0, 0, false);
            this.drawcs(var1, 216, "Guess who?", 100, 100, 100, false);
         }

         if(this.fase == 6) {
            this.drawcs(var1, 80, "Music was obtained from FlashKit.com", 0, 0, 0, false);
            this.drawcs(var1, 92, "and by the following artists:", 0, 0, 0, false);
            this.drawcs(var1, 118, ".::Dj Hemp::.", 100, 100, 100, false);
            this.drawcs(var1, 130, "Gen A Dee", 100, 100, 100, false);
            this.drawcs(var1, 142, "Alex Volkmar", 100, 100, 100, false);
            this.drawcs(var1, 154, "Empty", 100, 100, 100, false);
            this.drawcs(var1, 166, "[BoD]Raven", 100, 100, 100, false);
            this.drawcs(var1, 178, "Jeff Heysen", 100, 100, 100, false);
            this.drawcs(var1, 190, "Degz", 100, 100, 100, false);
            this.drawcs(var1, 202, "Justin Perkins", 100, 100, 100, false);
            this.drawcs(var1, 214, "and Vika", 100, 100, 100, false);
         }

         if(this.fase == 7) {
            if(this.flik) {
               this.drawcs(var1, 140, "G a m e   C o m p l e t e !", 255, 0, 0, false);
               this.flik = false;
            } else {
               this.drawcs(var1, 140, "G a m e   C o m p l e t e !", 0, 128, 255, true);
               this.flik = true;
            }

            this.drawcs(var1, 180, ">  Press Enter to continue  >", 150, 150, 150, false);
            ++this.cnt;
            if(this.cnt > 140) {
               var5.space = true;
            }
         } else {
            this.drawcs(var1, 246, "Press Enter to continue >", 150, 150, 150, false);
         }

         this.drawcs(var1, 354, "Copyright © RadicalPlay.com", 255, 255, 255, true);
         if(var5.space && this.fase != 7) {
            if(this.fase == 5) {
               this.fase = 6;
            } else {
               var6 = 0;

               do {
                  var3[var6].out = true;
                  var3[var6].wire = false;
                  ++var6;
               } while(var6 < 5);

               this.fase = -5;
            }

            var5.space = false;
         }
      }

   }

   public void drawefimg(Image var1) {
      this.saveit(var1, this.pix);
      int var2 = 0;

      do {
         Color var3 = new Color(this.pix[var2]);
         Color var4 = new Color(this.bpix[var2]);
         int var5 = (var3.getRed() + var4.getRed()) / 2;
         if(var5 > 225) {
            var5 = 225;
         }

         if(var5 < 0) {
            var5 = 0;
         }

         int var6 = (var3.getGreen() + var4.getGreen()) / 2;
         if(var6 > 225) {
            var6 = 225;
         }

         if(var6 < 0) {
            var6 = 0;
         }

         int var7 = (var3.getBlue() + var4.getBlue()) / 2;
         if(var7 > 225) {
            var7 = 225;
         }

         if(var7 < 0) {
            var7 = 0;
         }

         Color var8 = new Color(var5, var6, var7);
         this.pix[var2] = var8.getRGB();
         ++var2;
      } while(var2 < 180000);

      this.mback = this.createImage(new MemoryImageSource(500, 360, this.pix, 0, 500));
   }

   public boolean alldest() {
      int var1 = 0;
      int var2 = 0;

      do {
         if(this.dest[var2]) {
            ++var1;
         }

         ++var2;
      } while(var2 < 5);

      if(var1 == 5) {
         return true;
      } else {
         return false;
      }
   }

   public void drawpimg(Image var1) {
      this.saveit(var1, this.pix);
      int var2 = 0;

      do {
         int var3 = 0;

         do {
            Color var4 = new Color(this.pix[var2 + var3 * 500]);
            Color var5 = new Color(this.ppix[var2 + var3 * 500]);
            boolean var6 = false;
            boolean var7 = false;
            boolean var8 = false;
            int var10;
            int var11;
            int var12;
            if(var2 > 150 && var2 < 350 && var3 > 130 && var3 < 230) {
               var10 = (var4.getRed() + var5.getRed()) / 4;
               if(var10 > 225) {
                  var10 = 225;
               }

               if(var10 < 0) {
                  var10 = 0;
               }

               var11 = (var4.getGreen() + var5.getGreen()) / 4;
               if(var11 > 225) {
                  var11 = 225;
               }

               if(var11 < 0) {
                  var11 = 0;
               }

               var12 = (var4.getBlue() + var5.getBlue()) / 4;
               if(var12 > 225) {
                  var12 = 225;
               }

               if(var12 < 0) {
                  var12 = 0;
               }
            } else {
               var10 = (var4.getRed() + var5.getRed()) / 2;
               if(var10 > 225) {
                  var10 = 225;
               }

               if(var10 < 0) {
                  var10 = 0;
               }

               var11 = (var4.getGreen() + var5.getGreen()) / 2;
               if(var11 > 225) {
                  var11 = 225;
               }

               if(var11 < 0) {
                  var11 = 0;
               }

               var12 = (var4.getBlue() + var5.getBlue()) / 2;
               if(var12 > 225) {
                  var12 = 225;
               }

               if(var12 < 0) {
                  var12 = 0;
               }
            }

            Color var9 = new Color(var10, var11, var12);
            this.pix[var2 + var3 * 500] = var9.getRGB();
            ++var3;
         } while(var3 < 360);

         ++var2;
      } while(var2 < 500);

      this.mback = this.createImage(new MemoryImageSource(500, 360, this.pix, 0, 500));
   }

   public int ys(int var1, int var2) {
      if(var2 < 10) {
         var2 = 10;
      }

      return (var2 - this.m.focus_point) * (this.m.cy - var1) / var2 + var1;
   }

   public void reset() {
      int var1 = 0;

      do {
         this.dest[var1] = false;
         ++var1;
      } while(var1 < 5);

      this.level = 0;
   }

   public void creset() {
      this.cnt = 0;
      this.flik = false;
      this.cnts = 10;
      this.cntf = 0;
      this.left = false;
      this.wcnt = 0;
      this.rcnt = 0;
      this.cnty = 0;
   }

   public xtGraphics(Medium var1, Graphics var2) {
      this.m = var1;
      this.ftm = var2.getFontMetrics();
   }

   public void saveit(Image var1, int[] var2) {
      PixelGrabber var3 = new PixelGrabber(var1, 0, 0, 500, 360, var2, 0, 500);

      try {
         var3.grabPixels();
      } catch (InterruptedException var4) {
         ;
      }

   }

   public int xs(int var1, int var2) {
      if(var2 < 10) {
         var2 = 10;
      }

      return (var2 - this.m.focus_point) * (this.m.cx - var1) / var2 + var1;
   }

   public int getcpy(ContO var1, ContO var2) {
      return (var1.x - var2.x) / 100 * ((var1.x - var2.x) / 100) + (var1.y - var2.y) / 100 * ((var1.y - var2.y) / 100) + (var1.z - var2.z) / 100 * ((var1.z - var2.z) / 100);
   }

   public void drawop(Graphics var1, Image var2) {
      this.saveit(var2, this.pix);
      int var3 = 0;

      do {
         Color var4 = new Color(this.pix[var3]);
         int var5 = Math.abs(255 - var4.getRed());
         if(var5 > 255) {
            var5 = 255;
         }

         if(var5 < 0) {
            var5 = 0;
         }

         int var6 = Math.abs(255 - var4.getGreen());
         if(var6 > 255) {
            var6 = 255;
         }

         if(var6 < 0) {
            var6 = 0;
         }

         int var7 = Math.abs(255 - var4.getBlue());
         if(var7 > 255) {
            var7 = 255;
         }

         if(var7 < 0) {
            var7 = 0;
         }

         Color var8 = new Color(var5, var6, var7);
         this.pix[var3] = var8.getRGB();
         ++var3;
      } while(var3 < 180000);

      var1.drawImage(this.createImage(new MemoryImageSource(500, 360, this.pix, 0, 500)), 0, 0, (ImageObserver)null);
   }

   public void cmback(int var1) {
      int var2 = 0;

      do {
         int var3 = 0;

         do {
            this.pix[var2 + var3 * 500] = this.mpix[var2 + var3 * 500];

            for(int var4 = 0; var4 < var1; ++var4) {
               if(var2 > 82 && var2 < 416 && var3 > 95 + var4 * 80 && var3 < 147 + var4 * 80) {
                  Color var5 = new Color(222, 184, 34);
                  Color var6 = new Color(this.pix[var2 + var3 * 500]);
                  int var7 = (var5.getRed() + var6.getRed()) / 2;
                  if(var7 > 225) {
                     var7 = 225;
                  }

                  if(var7 < 0) {
                     var7 = 0;
                  }

                  int var8 = (var5.getGreen() + var6.getGreen()) / 2;
                  if(var8 > 225) {
                     var8 = 225;
                  }

                  if(var8 < 0) {
                     var8 = 0;
                  }

                  int var9 = (var5.getBlue() + var6.getBlue()) / 2;
                  if(var9 > 225) {
                     var9 = 225;
                  }

                  if(var9 < 0) {
                     var9 = 0;
                  }

                  Color var10 = new Color(var7, var8, var9);
                  this.pix[var2 + var3 * 500] = var10.getRGB();
               }
            }

            ++var3;
         } while(var3 < 360);

         ++var2;
      } while(var2 < 500);

      this.mback = this.createImage(new MemoryImageSource(500, 360, this.pix, 0, 500));
   }

   public void drawl(Graphics var1, Image var2) {
      this.saveit(var2, this.pix);
      int var3 = 0;

      do {
         Color var4 = new Color(this.pix[var3]);
         int var5 = Math.abs((var4.getRed() - 15) / 2);
         if(var5 > 225) {
            var5 = 225;
         }

         if(var5 < 0) {
            var5 = 0;
         }

         int var6 = Math.abs((var4.getGreen() - 10) / 2);
         if(var6 > 225) {
            var6 = 225;
         }

         if(var6 < 0) {
            var6 = 0;
         }

         int var7 = Math.abs((var4.getBlue() + 20) / 2);
         if(var7 > 225) {
            var7 = 225;
         }

         if(var7 < 0) {
            var7 = 0;
         }

         Color var8 = new Color(var5, var6, var7);
         this.pix[var3] = var8.getRGB();
         ++var3;
      } while(var3 < 180000);

      var1.drawImage(this.createImage(new MemoryImageSource(500, 360, this.pix, 0, 500)), 0, 0, (ImageObserver)null);
   }

   public void drawovimg(Image var1) {
      this.saveit(var1, this.pix);
      int var2 = 0;

      do {
         Color var3 = new Color(this.pix[var2]);
         Color var4 = new Color(this.opix[var2]);
         int var5 = (int)(((double)var3.getRed() / 1.7D + (double)var4.getRed()) / 2.0D);
         if(var5 > 225) {
            var5 = 225;
         }

         if(var5 < 0) {
            var5 = 0;
         }

         int var6 = (int)(((double)var3.getGreen() / 1.7D + (double)var4.getGreen()) / 2.0D);
         if(var6 > 225) {
            var6 = 225;
         }

         if(var6 < 0) {
            var6 = 0;
         }

         int var7 = (int)(((double)var3.getBlue() / 1.7D + (double)var4.getBlue()) / 2.0D);
         if(var7 > 225) {
            var7 = 225;
         }

         if(var7 < 0) {
            var7 = 0;
         }

         Color var8 = new Color(var5, var6, var7);
         this.pix[var2] = var8.getRGB();
         ++var2;
      } while(var2 < 180000);

      this.mback = this.createImage(new MemoryImageSource(500, 360, this.pix, 0, 500));
   }

   public void dtrakers(Graphics var1, int[] var2, int[] var3, int var4, ContO[] var5, userCraft var6, Control var7) {
      this.cl = 1;
      int var8 = this.getcpy(var5[var3[0]], var5[var3[1]]);

      for(int var9 = 2; var9 < var4; ++var9) {
         if(var8 != 0 && !var5[var3[this.cl]].exp) {
            int var10 = this.getcpy(var5[var3[0]], var5[var3[var9]]);
            if((var10 > 0 || var8 == 0) && var10 < var8 && !var5[var3[var9]].exp) {
               var8 = var10;
               this.cl = var9;
            }
         } else {
            this.cl = var9;
            var8 = this.getcpy(var5[var3[0]], var5[var3[var9]]);
         }
      }

      int[] var22 = new int[4];
      int[] var23 = new int[4];
      boolean var11 = false;
      boolean var12 = false;
      int var13 = 0;

      int var14;
      int var16;
      int var17;
      int var18;
      int var19;
      int var20;
      for(var14 = 1; var14 < var4; ++var14) {
         short var15 = 1000;
         if(var2[var14] == 1) {
            var15 = 4000;
         }

         var8 = this.getcpy(var5[var3[0]], var5[var3[var14]]);
         if(var8 > var15 && !var5[var3[var14]].exp) {
            var16 = this.m.cx + (int)((float)(var5[var3[var14]].x - this.m.x - this.m.cx) * this.m.cs.getcos(this.m.xz) - (float)(var5[var3[var14]].z - this.m.z - this.m.cz) * this.m.cs.getsin(this.m.xz));
            var17 = this.m.cz + (int)((float)(var5[var3[var14]].x - this.m.x - this.m.cx) * this.m.cs.getsin(this.m.xz) + (float)(var5[var3[var14]].z - this.m.z - this.m.cz) * this.m.cs.getcos(this.m.xz));
            var18 = this.m.cz + (int)((float)(var5[var3[var14]].y - this.m.y - this.m.cy) * this.m.cs.getsin(this.m.zy) + (float)(var17 - this.m.cz) * this.m.cs.getcos(this.m.zy));
            if(var18 > 100) {
               var19 = this.m.cy + (int)((float)(var5[var3[var14]].y - this.m.y - this.m.cy) * this.m.cs.getcos(this.m.zy) - (float)(var17 - this.m.cz) * this.m.cs.getsin(this.m.zy));
               var20 = this.xs(var16, var18);
               int var21 = this.ys(var19, var18);
               if(var20 > 0 && var20 < this.m.w && var21 > 0 && var21 < this.m.h) {
                  if(!var11 && var8 != 0 && var8 < 10000) {
                     var11 = true;
                  }

                  if(var2[var14] == 0) {
                     if(!var5[var3[var14]].fire) {
                        var1.setColor(new Color(164, 209, 255));
                     } else {
                        var1.setColor(new Color(164, 229, 255));
                     }
                  } else if(!var5[var3[var14]].fire) {
                     var1.setColor(new Color(255, 150, 100));
                  } else {
                     var1.setColor(new Color(255, 180, 100));
                  }

                  var22[0] = var20 - 10;
                  var23[0] = var21 - 10;
                  var22[1] = var20 + 10;
                  var23[1] = var21 - 10;
                  var22[2] = var20 + 10;
                  var23[2] = var21 + 10;
                  var22[3] = var20 - 10;
                  var23[3] = var21 + 10;
                  var1.drawPolygon(var22, var23, 4);
               }
            }
         }

         if(var5[var3[var14]].exp) {
            if(this.cnte[var14 - 1] < 20 && !var12) {
               if(this.cntf < 2) {
                  if(var5[var3[var14]].nhits >= var5[var3[var14]].maxhits) {
                     this.drawcs(var1, 120, this.mname[var14 - 1] + " distroyd!", 255, 255, 128, false);
                  } else {
                     this.drawcs(var1, 120, this.mname[var14 - 1] + " Crashed!", 255, 255, 128, false);
                  }
               } else if(var5[var3[var14]].nhits >= var5[var3[var14]].maxhits) {
                  this.drawcs(var1, 120, this.mname[var14 - 1] + " distroyd!", 186, 223, 57, false);
               } else {
                  this.drawcs(var1, 120, this.mname[var14 - 1] + " Crashed!", 186, 223, 57, false);
               }

               if(this.cntf < 2) {
                  ++this.cntf;
               } else {
                  this.cntf = 0;
               }

               ++this.cnte[var14 - 1];
               var12 = true;
            } else {
               ++var13;
            }
         }
      }

      if(!this.mcomp && var13 == var4 - 1) {
         this.mcomp = true;
         this.select = 0;
      }

      if(this.mcomp && !var5[var3[0]].exp) {
         if(this.rcnt == 0) {
            this.rcnt = 1;
         } else {
            var1.setColor(new Color(50 + (int)(Math.random() * 200.0D), 50 + (int)(Math.random() * 200.0D), 50 + (int)(Math.random() * 200.0D)));
            var1.fillRect(110, 67, 270, 13);
            this.rcnt = 0;
         }

         var1.drawImage(this.complete, 105, 60, (ImageObserver)null);
         this.drawcs(var1, 300, "Press Enter to continue", 0, 0, 0, false);
      }

      int var25;
      if(!var11 && !var5[var3[this.cl]].exp) {
         boolean var24 = false;
         var25 = this.m.cx + (int)((float)(var5[var3[this.cl]].x - this.m.x - this.m.cx) * this.m.cs.getcos(this.m.xz) - (float)(var5[var3[this.cl]].z - this.m.z - this.m.cz) * this.m.cs.getsin(this.m.xz));
         var16 = this.m.cz + (int)((float)(var5[var3[this.cl]].x - this.m.x - this.m.cx) * this.m.cs.getsin(this.m.xz) + (float)(var5[var3[this.cl]].z - this.m.z - this.m.cz) * this.m.cs.getcos(this.m.xz));
         var17 = this.m.cz + (int)((float)(var5[var3[this.cl]].y - this.m.y - this.m.cy) * this.m.cs.getsin(this.m.zy) + (float)(var16 - this.m.cz) * this.m.cs.getcos(this.m.zy));
         var18 = this.m.cy + (int)((float)(var5[var3[this.cl]].y - this.m.y - this.m.cy) * this.m.cs.getcos(this.m.zy) - (float)(var16 - this.m.cz) * this.m.cs.getsin(this.m.zy));
         var19 = this.ys(var18, var17);
         var20 = this.xs(var25, var17);
         if(var20 < this.m.w && var20 > 0) {
            if(var19 > this.m.h || var19 < 0) {
               if(var20 > this.m.w - 10) {
                  var20 = this.m.w - 50;
               }

               if(var20 < 5) {
                  var20 = 50;
               }

               if(var18 > this.m.cy) {
                  var22[0] = var20;
                  var23[0] = this.m.h - 1;
                  var22[1] = var20 - 5;
                  var23[1] = this.m.h - 20;
                  var22[2] = var20 + 5;
                  var23[2] = this.m.h - 20;
                  var24 = true;
               } else {
                  var23[0] = 1;
                  var22[0] = var20;
                  var23[1] = 20;
                  var22[1] = var20 - 5;
                  var23[2] = 20;
                  var22[2] = var20 + 5;
                  var24 = true;
               }
            }
         } else {
            if(var19 > this.m.h - 10) {
               var19 = this.m.h - 50;
            }

            if(var19 < 5) {
               var19 = 50;
            }

            if(var25 > this.m.cx) {
               var22[0] = this.m.w - 1;
               var23[0] = var19;
               var22[1] = this.m.w - 20;
               var23[1] = var19 - 5;
               var22[2] = this.m.w - 20;
               var23[2] = var19 + 5;
               var24 = true;
            } else {
               var22[0] = 1;
               var23[0] = var19;
               var22[1] = 20;
               var23[1] = var19 - 5;
               var22[2] = 20;
               var23[2] = var19 + 5;
               var24 = true;
            }
         }

         if(var24) {
            if(var2[this.cl] == 0) {
               var1.setColor(new Color(164, 209, 255));
            } else {
               var1.setColor(new Color(255, 180, 100));
            }

            var1.fillPolygon(var22, var23, 3);
         }
      }

      if(var5[var3[0]].nhits > var5[var3[0]].maxhits - var5[var3[0]].maxhits / 3 && !var5[var3[0]].exp && !this.mcomp) {
         if(this.cnt > 90) {
            if(this.flik) {
               this.drawcs(var1, 300, "Recharge Ship !", 255, 255, 255, false);
               this.flik = false;
            } else {
               this.drawcs(var1, 300, "Recharge Ship !", 200, 200, 200, false);
               this.flik = true;
            }
         } else {
            this.drawcs(var1, 300, "Damage Critical", 255, 0, 0, false);
         }

         ++this.cnt;
         if(this.cnt == 130) {
            this.cnt = 0;
         }
      }

      if(var7.jump >= 1 && var6.njumps == 0) {
         this.drawcs(var1, 330, "Light speed jumps expired - Recharge Ship !", 255, 255, 255, false);
         ++var7.jump;
         if(var7.jump == 40) {
            var7.jump = 0;
         }
      }

      if(var6.ester != 0 && !var5[var3[0]].exp && !this.mcomp) {
         this.drawcs(var1, 300, "Ship Recharged !", 255 * this.m.er, 255 - this.m.eg * 100, 64 + this.m.eb * 191, false);
      }

      if(var7.radar && !this.mcomp) {
         var1.drawImage(this.radar, 200, 60, (ImageObserver)null);
         var14 = var5[var3[0]].zy;

         for(var25 = -var5[var3[0]].xz; var14 > 360; var14 -= 360) {
            ;
         }

         while(var14 < 0) {
            var14 += 360;
         }

         if(var14 > 90 && var14 < 270) {
            var25 += 180;
         }

         for(var16 = 1; var16 < var4; ++var16) {
            if(!var5[var3[var16]].exp) {
               var17 = this.m.cx + (int)((float)(var5[var3[var16]].x - this.m.x - this.m.cx) * this.m.cs.getcos(var25) - (float)(var5[var3[var16]].z - this.m.z - this.m.cz) * this.m.cs.getsin(var25));
               var18 = this.m.cz + (int)((float)(var5[var3[var16]].x - this.m.x - this.m.cx) * this.m.cs.getsin(var25) + (float)(var5[var3[var16]].z - this.m.z - this.m.cz) * this.m.cs.getcos(var25));
               var1.setColor(new Color(0, 255, 128));
               var17 = var17 / 400 + 249;
               var18 = -var18 / 400 + 109;
               if(var17 < 204) {
                  var17 = 204;
               }

               if(var17 > 296) {
                  var17 = 296;
               }

               if(var18 < 64) {
                  var18 = 64;
               }

               if(var18 > 156) {
                  var18 = 156;
               }

               var1.fillRect(var17, var18, 2, 2);
            }
         }
      }

      if(var7.plus || var7.mins || this.cnts < 10) {
         var1.setColor(new Color(0, 0, 0));
         var1.drawString("" + var6.rspeed + " zic/tes", 50, 55);
         var1.drawImage(this.stube, 50, 60, (ImageObserver)null);
         var14 = (int)(260.0F - (float)var6.rspeed * (200.0F / (float)var6.maxspeed[var6.ltyp]));
         var1.setColor(new Color(255, var14 - 10, 0));
         var1.fillRect(61, var14, 12, 260 - var14);
         if(!var7.plus && !var7.mins) {
            ++this.cnts;
         } else {
            this.cnts = 0;
         }
      }

      if(this.tcnt != 0) {
         if(var6.rspeed == 0) {
            ++this.tcnt;
         } else {
            this.tcnt = 0;
         }

         if(!var7.space) {
            if(this.tcnt > 90) {
               this.drawcs(var1, 80, "Press Enter for game controls and to pause game!", 255, 255, 255, false);
            }
         } else {
            this.tcnt = 0;
         }
      }

   }

   public void drawcs(Graphics var1, int var2, String var3, int var4, int var5, int var6, boolean var7) {
      if(var7) {
         var1.setColor(new Color(0, 0, 0));
         var1.drawString(var3, 250 - this.ftm.stringWidth(var3) / 2 + 1, var2 + 1);
      }

      var1.setColor(new Color(var4, var5, var6));
      var1.drawString(var3, 250 - this.ftm.stringWidth(var3) / 2, var2);
   }
}
