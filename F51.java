import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import netscape.javascript.JSObject;

public class F51 extends Applet implements Runnable {

   Graphics rd;
   Image offImage;
   Thread gamer;
   boolean mon = true;
   String moner = "Click here to Start";
   String[] obj = new String[53];
   String sndfrm = "default";
   boolean nounif = false;
   Control u = new Control();
   boolean tab = false;
   int view = 0;
   int maxco = 0;
   int maxmo = -1;
   AudioClip upl;
   AudioClip low;
   AudioClip med;
   AudioClip downl;
   AudioClip ljump;
   AudioClip grnd;
   AudioClip exp;
   AudioClip exph;
   AudioClip hit;
   AudioClip hitl;
   AudioClip charged;
   AudioClip into;
   AudioClip miso;
   AudioClip mano;
   AudioClip selo;
   AudioClip[] las = new AudioClip[5];
   AudioClip[] mtrak = new AudioClip[7];
   boolean[] loadet = new boolean[7];
   boolean plow = false;
   boolean pmed = false;
   boolean pexph = false;
   boolean pint = false;
   boolean pmis = false;
   boolean pman = false;
   boolean psel = false;
   boolean nomusic = false;
   boolean nosound = false;
   boolean enterd = false;
   boolean sosun = false;
   int pgrnd = 0;
   int pdownl = 0;
   int pupl = 0;
   int lascnt = 0;
   int crntt = -1;
   int plcnt = 0;
   int frags = 0;
   int dnload = 0;


   public void testlocation(Medium var1, int var2) {
      try {
         JSObject var9 = JSObject.getWindow(this);
         var9.eval("canstand=true;");
         boolean var10 = false;
         var9.eval("var sti=\'\'+top.location;");
         String var11 = String.valueOf(String.valueOf(var9.getMember("sti")));
         int var12 = 0;
         if(var11.startsWith("http:/")) {
            for(; var12 < var11.length() - 1; ++var12) {
               if(var11.startsWith("radicalplay.com", var12)) {
                  var10 = true;
               }
            }
         } else {
            var10 = true;
         }

         if(!var10) {
            this.rd.setColor(new Color(223, 223, 223));
            this.rd.fillRect(0, 0, 500, 360);
            this.rd.setColor(new Color(0, 0, 0));
            this.rd.drawString("Access Denied !", 30, 50);
            this.rd.drawString("This game will not run under this loaction:", 30, 100);
            this.rd.drawString("" + this.getDocumentBase(), 30, 120);
            this.rd.drawString("Please contact Radicalplay.com for details.", 30, 200);
            this.repaint();
            var9.eval("window.open(\'http://www.radicalplay.com/aces/\',\'olen\',\'menubar=1,toolbar=1,location=1,resizable=1,status=1,scrollbars=1\');");
            this.gamer.stop();
         }

         if(var2 == 15) {
            var9.eval("sti=\'\'+screen.colorDepth;");
            var11 = String.valueOf(String.valueOf(var9.getMember("sti")));
            if(var11.equals("16")) {
               var1.isun = true;
            }
         }
      } catch (Exception var8) {
         boolean var3 = false;
         String var4 = "" + this.getDocumentBase();
         int var5 = 0;
         if(var4.startsWith("http:/")) {
            for(; var5 < var4.length() - 1; ++var5) {
               if(var4.startsWith("radicalplay.com", var5)) {
                  var3 = true;
               }
            }
         } else {
            var3 = true;
         }

         if(!var3) {
            this.rd.setColor(new Color(223, 223, 223));
            this.rd.fillRect(0, 0, 500, 360);
            this.rd.setColor(new Color(0, 0, 0));
            this.rd.drawString("Access Denied !", 30, 50);
            this.rd.drawString("This game will not run under this loaction:", 30, 100);
            this.rd.drawString("" + this.getDocumentBase(), 30, 120);
            this.rd.drawString("Please contact Radicalplay.com for details.", 30, 200);
            this.repaint();

            try {
               URL var6 = new URL("http://www.radicalplay.com/aces/");
               this.getAppletContext().showDocument(var6, "olen");
            } catch (Exception var7) {
               ;
            }

            this.gamer.stop();
         }
      }

   }

   public void stop() {
      this.into.stop();
      this.miso.stop();
      this.selo.stop();
      this.mano.stop();
      this.upl.stop();
      this.downl.stop();
      this.low.stop();
      this.med.stop();
      this.ljump.stop();
      this.grnd.stop();
      this.exp.stop();
      this.exph.stop();
      this.hit.stop();
      this.hitl.stop();
      this.charged.stop();
      int var1 = 0;

      do {
         this.las[var1].stop();
         ++var1;
      } while(var1 < 5);

      var1 = 0;

      do {
         if(this.loadet[var1]) {
            this.mtrak[var1].stop();
         }

         ++var1;
      } while(var1 < 7);

      if(this.gamer != null) {
         this.gamer.stop();
      }

      this.gamer = null;
      this.rd.dispose();
   }

   public boolean lostFocus(Event var1, Object var2) {
      if(!this.nounif) {
         this.mon = true;
      }

      if(this.maxmo != -1) {
         this.view = 0;
         this.u.radar = false;
         this.u.plus = false;
         this.u.mins = false;
         this.enterd = false;
         this.tab = false;
         this.u.fire = false;
         this.u.left = false;
         this.u.right = false;
         this.u.down = false;
         this.u.up = false;
      }

      return false;
   }

   public void playsounds(userCraft var1, ContO var2, boolean var3, xtGraphics var4) {
      if(!var3) {
         if(!this.nosound) {
            if(!var2.exp && var1.speed > 10.0F && !this.pmed) {
               if(!this.plow) {
                  this.low.loop();
                  this.plow = true;
               }
            } else if(this.plow) {
               this.low.stop();
               this.plow = false;
            }

            if(var1.speed > 65.0F) {
               if(!this.pmed) {
                  this.med.loop();
                  this.pmed = true;
               }
            } else if(this.pmed) {
               this.med.stop();
               this.pmed = false;
            }

            if(var1.speed > 65.0F && this.u.up) {
               if(this.pupl == 0) {
                  this.pupl = 70;
                  this.upl.play();
               }
            } else if(this.pupl != 0) {
               this.pupl += -1;
            }

            if(var1.speed > 65.0F && this.u.down) {
               if(this.pdownl == 0) {
                  this.pdownl = 70;
                  this.downl.play();
               }
            } else if(this.pdownl != 0) {
               this.pdownl += -1;
            }

            if(var1.speed == 400.0F) {
               this.ljump.play();
            }

            if(var1.ester == 1) {
               this.charged.play();
            }

            if(var2.hit && this.frags == 0) {
               this.hit.play();
               if(this.sosun) {
                  this.frags = 3;
               }
            }

            if(this.sosun && this.frags != 0) {
               this.frags += -1;
            }

            if(this.u.fire && !var2.exp) {
               if(this.lascnt == 0) {
                  this.las[var1.ltyp].play();
                  this.lascnt = 14;
               } else {
                  this.lascnt += -1;
               }
            } else if(this.lascnt != 0) {
               this.lascnt = 0;
            }

            if(this.pgrnd == 0) {
               if(!var2.exp && var2.y > 200 && (var1.sms[0] == 1 || var1.sms[1] == 1 || var1.sms[2] == 1 || var1.sms[3] == 1)) {
                  this.grnd.play();
                  this.pgrnd = 2;
               }
            } else {
               this.pgrnd += -1;
            }

            if(var2.exp) {
               if(!this.pexph) {
                  this.exph.play();
                  this.pexph = true;
                  this.shake();
               }
            } else if(this.pexph) {
               this.pexph = false;
            }
         } else {
            if(this.pmed) {
               this.med.stop();
               this.pmed = false;
            }

            if(this.plow) {
               this.low.stop();
               this.plow = false;
            }
         }

         if(this.psel) {
            this.selo.stop();
            this.psel = false;
         }

         if(this.plcnt == 100) {
            ++this.crntt;
            if(this.crntt == 7) {
               this.crntt = 0;
            }

            if(this.loadet[this.crntt]) {
               this.mtrak[this.crntt].loop();
            } else {
               this.crntt = -1;
               int var5 = 6;

               do {
                  if(this.loadet[var5]) {
                     this.crntt = var5;
                  }

                  --var5;
               } while(var5 >= 0);

               if(this.crntt != -1) {
                  this.mtrak[this.crntt].loop();
               }
            }
         }

         if(this.plcnt != 2000) {
            if(!this.nomusic) {
               ++this.plcnt;
            }
         } else {
            this.plcnt = 80;
            this.mtrak[this.crntt].stop();
         }
      } else {
         if(this.pmed) {
            this.med.stop();
            this.pmed = false;
         }

         if(this.plow) {
            this.low.stop();
            this.plow = false;
         }

         if(this.plcnt != 0 && this.crntt != -1 && var4.fase != -4 && var4.fase != 1 && var4.fase != 2) {
            if(this.plcnt >= 100) {
               this.mtrak[this.crntt].stop();
            }

            if(var4.fase == 3 && this.plcnt >= 100) {
               this.crntt += -1;
            }

            this.plcnt = 0;
         }

         if(var4.fase == -8 && var4.cnty < 351 && !this.nomusic) {
            if(!this.pint) {
               this.into.loop();
               this.pint = true;
            }
         } else {
            if(this.pint) {
               this.into.stop();
               this.pint = false;
            }

            if(var4.cnty == 352) {
               this.hit.play();
               var4.cnty = 353;
            }
         }

         if((var4.fase == -5 || var4.fase == 7) && !this.nomusic) {
            if(!this.pman) {
               this.mano.loop();
               this.pman = true;
            }
         } else if(this.pman) {
            this.mano.stop();
            this.pman = false;
         }

         if(var4.fase == -1 && !this.nomusic) {
            if(!this.pmis) {
               this.miso.loop();
               this.pmis = true;
            }
         } else if(this.pmis) {
            this.miso.stop();
            this.pmis = false;
         }

         if((var4.fase == 0 || var4.fase == 5 || var4.fase == 6) && !this.nomusic) {
            if(!this.psel) {
               this.selo.loop();
               this.psel = true;
            }
         } else if(this.psel) {
            this.selo.stop();
            this.psel = false;
         }

         if(var4.fase == 7) {
            if(this.pupl == 0) {
               this.pupl = 30;
               this.upl.play();
            } else {
               this.pupl += -1;
            }
         }
      }

   }

   public void unloadit() {
      try {
         JSObject var1 = JSObject.getWindow(this);
         var1.eval("loaded=false;");
      } catch (Exception var2) {
         ;
      }

   }

   public AudioClip loadsnd(String var1) {
      AudioClip var2 = this.getAudioClip(this.getCodeBase(), var1);
      if(!this.sosun) {
         var2.play();
         Thread.yield();
         var2.stop();
      }

      return var2;
   }

   public String getstring(String var1, String var2, int var3) {
      int var5 = 0;
      String var7 = "";

      for(int var4 = var1.length() + 1; var4 < var2.length(); ++var4) {
         String var6 = "" + var2.charAt(var4);
         if(var6.equals(",") || var6.equals(")")) {
            ++var5;
            ++var4;
         }

         if(var5 == var3) {
            var7 = var7 + var2.charAt(var4);
         }
      }

      return var7;
   }

   public int getint(String var1, String var2, int var3) {
      int var5 = 0;
      String var7 = "";

      for(int var4 = var1.length() + 1; var4 < var2.length(); ++var4) {
         String var6 = "" + var2.charAt(var4);
         if(var6.equals(",") || var6.equals(")")) {
            ++var5;
            ++var4;
         }

         if(var5 == var3) {
            var7 = var7 + var2.charAt(var4);
         }
      }

      return Integer.valueOf(var7).intValue();
   }

   public void paint(Graphics var1) {
      var1.drawImage(this.offImage, 0, 0, this);
   }

   public void savegame(ContO[] var1, xtGraphics var2, int var3) {
      try {
         JSObject var4 = JSObject.getWindow(this);
         var4.eval("SetCookie(\'radxv\',\'" + var2.level + "\')");

         int var5;
         for(var5 = var3; var5 < var3 + 13; ++var5) {
            var4.eval("SetCookie(\'radnhits" + var5 + "\',\'" + var1[var5].nhits + "\')");
         }

         var5 = 0;

         do {
            var4.eval("SetCookie(\'raddest" + var5 + "\',\'" + var2.dest[var5] + "\')");
            ++var5;
         } while(var5 < 5);

         var2.sgame = 1;
      } catch (Exception var6) {
         ;
      }

   }

   public void destroy() {
      if(this.gamer != null) {
         this.gamer.stop();
      }

      this.gamer = null;
   }

   public void loadrots(ContO[] var1, boolean var2) {
      for(int var3 = 0; var3 < this.maxco; ++var3) {
         var1[var3].loadrots(var2);
      }

   }

   public Image loadimg(String var1, MediaTracker var2) {
      Image var3 = this.getImage(this.getCodeBase(), var1);
      var2.addImage(var3, 0);

      try {
         var2.waitForID(0);
      } catch (Exception var4) {
         ;
      }

      return var3;
   }

   public void loadbase(ContO[] var1, Medium var2) {
      try {
         URL var3 = new URL(this.getCodeBase(), "graphics/models.zrad");
         DataInputStream var4 = new DataInputStream(var3.openStream());
         ZipInputStream var5 = new ZipInputStream(var4);
         ZipEntry var6 = var5.getNextEntry();
         Object var7 = null;

         for(int var8 = 0; var6 != null; var6 = var5.getNextEntry()) {
            int var9 = (int)var6.getSize();
            byte[] var13 = new byte[var9];

            int var11;
            for(int var10 = 0; var9 > 0; var9 -= var11) {
               var11 = var5.read(var13, var10, var9);
               var10 += var11;
            }

            var1[var8] = new ContO(var13, var2, 0, 0, 0);
            this.obj[var8] = var6.getName();
            ++var8;
         }

         var5.close();
         var4.close();
      } catch (Exception var12) {
         System.out.println("Error Reading Models: " + var12);
      }

      System.gc();
   }

   public void update(Graphics var1) {
      this.paint(var1);
   }

   public void loadmovers(int[] var1, int[] var2, ContO[] var3, Craft[] var4, Tank[] var5, userCraft var6, xtGraphics var7) {
      for(int var8 = 1; var8 < this.maxmo; ++var8) {
         var3[var1[var8]].out = true;
      }

      this.maxmo = 1;
      var7.nb = 0;
      var7.mcomp = false;

      try {
         URL var13 = new URL(this.getCodeBase(), "levels/" + var7.level + ".txt");
         DataInputStream var9 = new DataInputStream(var13.openStream());

         String var10;
         while((var10 = var9.readLine()) != null) {
            String var11 = "" + var10.trim();
            if(var11.startsWith("craft")) {
               var1[this.maxmo] = this.getint("craft", var11, 0);
               var2[this.maxmo] = 0;
            }

            if(var11.startsWith("tank")) {
               var1[this.maxmo] = this.getint("tank", var11, 0);
               var2[this.maxmo] = 1;
            }

            if(var11.startsWith("name")) {
               var7.mname[this.maxmo - 1] = this.getstring("name", var11, 0);
               var7.cnte[this.maxmo - 1] = 0;
            }

            if(var11.startsWith("l")) {
               var3[var1[this.maxmo]].x = this.getint("l", var11, 0) * 10;
               var3[var1[this.maxmo]].y = this.getint("l", var11, 1) * 10;
               var3[var1[this.maxmo]].z = this.getint("l", var11, 2) * 10;
               var3[var1[this.maxmo]].out = false;
               var3[var1[this.maxmo]].reset();
            }

            if(var11.startsWith("prompt")) {
               if(this.getstring("prompt", var11, 0).equals("tank")) {
                  var7.tnk[var7.nb] = true;
               } else {
                  var7.tnk[var7.nb] = false;
               }

               var7.ob[var7.nb] = this.getint("prompt", var11, 1);
               var7.nam[var7.nb] = this.getstring("prompt", var11, 2).replace('|', ',');
               ++var7.nb;
            }

            if(var11.startsWith("stat")) {
               if(var2[this.maxmo] == 0) {
                  var4[this.maxmo].reset(this.getint("stat", var11, 0), this.getint("stat", var11, 1), this.getint("stat", var11, 2), this.getint("stat", var11, 3), this.getint("stat", var11, 4), this.getint("stat", var11, 5));
               } else {
                  var5[this.maxmo].reset(this.getint("stat", var11, 0), this.getint("stat", var11, 1));
               }

               ++this.maxmo;
            }
         }

         var9.close();
      } catch (Exception var12) {
         ;
      }

      System.gc();
   }

   public void set0() {
      try {
         JSObject var1 = JSObject.getWindow(this);
         var1.eval("SetCookie(\'radxv\',\'0\')");
      } catch (Exception var2) {
         ;
      }

   }

   public boolean keyUp(Event var1, int var2) {
      if(var2 == 49 && this.view == 1) {
         this.view = 0;
      }

      if(var2 == 50 && this.view == 2) {
         this.view = 0;
      }

      if(var2 == 51 && this.view == 3) {
         this.view = 0;
      }

      if(var2 == 52 && this.view == 4) {
         this.view = 0;
      }

      if(var2 == 53 && this.view == 5) {
         this.view = 0;
      }

      if(var2 == 114 || var2 == 82) {
         this.u.radar = false;
      }

      if(var2 == 43 || var2 == 61) {
         this.u.plus = false;
      }

      if(var2 == 45 || var2 == 8) {
         this.u.mins = false;
      }

      if(var2 == 10 || var2 == 27) {
         this.enterd = false;
      }

      if(var2 == 9) {
         this.tab = false;
      }

      if(var2 == 32) {
         this.u.fire = false;
      }

      if(var2 == 1006) {
         this.u.left = false;
      }

      if(var2 == 1007) {
         this.u.right = false;
      }

      if(var2 == 1005) {
         this.u.down = false;
      }

      if(var2 == 1004) {
         this.u.up = false;
      }

      return false;
   }

   public void start() {
      if(this.gamer == null) {
         this.gamer = new Thread(this);
      }

      this.gamer.start();
   }

   public void downloadall(xtGraphics var1) {
      MediaTracker var2 = new MediaTracker(this);
      var1.radar = this.loadimg("graphics/radar.gif", var2);
      this.lstat("Loading Images...", 1);
      var1.stube = this.loadimg("graphics/stube.gif", var2);
      this.lstat("Loading Images...", 2);
      var1.sback = this.loadimg("graphics/select.jpg", var2);
      this.lstat("Loading Images...", 18);
      var1.destr = this.loadimg("graphics/destroyed.gif", var2);
      this.lstat("Loading Images...", 2);
      var1.saveit(this.loadimg("graphics/failed.jpg", var2), var1.bpix);
      this.lstat("Loading Images...", 31);
      var1.saveit(this.loadimg("graphics/mission.jpg", var2), var1.mpix);
      this.lstat("Loading Images...", 22);
      var1.saveit(this.loadimg("graphics/over.jpg", var2), var1.opix);
      this.lstat("Loading Images...", 21);
      var1.saveit(this.loadimg("graphics/paused.jpg", var2), var1.ppix);
      this.lstat("Loading Images...", 10);
      var1.lay = this.loadimg("graphics/layout.gif", var2);
      this.lstat("Loading Images...", 1);
      var1.complete = this.loadimg("graphics/comp.gif", var2);
      this.lstat("Loading Images...", 2);
      var1.main = this.loadimg("graphics/main.gif", var2);
      this.lstat("Loading Images...", 32);
      var1.rad = this.loadimg("graphics/radicalplay.gif", var2);
      this.lstat("Loading Images...", 2);
      int var3 = 0;

      do {
         var1.as[var3] = this.loadimg("graphics/a" + var3 + ".gif", var2);
         this.lstat("Loading Images...", 1);
         ++var3;
      } while(var3 < 5);

      var1.inst1 = this.loadimg("graphics/inst1.gif", var2);
      this.lstat("Loading Images...", 10);
      var1.inst2 = this.loadimg("graphics/inst2.gif", var2);
      this.lstat("Loading Images...", 11);
      var1.inst3 = this.loadimg("graphics/inst3.gif", var2);
      this.lstat("Loading Images...", 4);
      var1.text = this.loadimg("graphics/text.gif", var2);
      this.lstat("Loading Images...", 6);
      var1.mars = this.loadimg("graphics/mars.jpg", var2);
      this.lstat("Loading Images...", 15);
      this.into = this.loadsnd("music/intro.au");
      this.lstat("Loading Music...", 24);
      this.miso = this.loadsnd("music/mission.au");
      this.lstat("Loading Music...", 29);
      this.selo = this.loadsnd("music/select.au");
      this.lstat("Loading Music...", 52);
      this.mano = this.loadsnd("music/main.au");
      this.lstat("Loading Music...", 50);
      this.upl = this.loadsnd("sounds/" + this.sndfrm + "/up.au");
      this.lstat("Loading Sound Effects...", 11);
      this.hitl = this.loadsnd("sounds/" + this.sndfrm + "/hitl.au");
      this.lstat("Loading Sound Effects...", 7);
      this.downl = this.loadsnd("sounds/" + this.sndfrm + "/down.au");
      this.lstat("Loading Sound Effects...", 10);
      this.low = this.loadsnd("sounds/" + this.sndfrm + "/low.au");
      this.lstat("Loading Sound Effects...", 11);
      this.med = this.loadsnd("sounds/" + this.sndfrm + "/med.au");
      this.lstat("Loading Sound Effects...", 6);
      this.ljump = this.loadsnd("sounds/" + this.sndfrm + "/jump.au");
      this.lstat("Loading Sound Effects...", 25);
      this.grnd = this.loadsnd("sounds/" + this.sndfrm + "/grnd.au");
      this.lstat("Loading Sound Effects...", 5);
      this.exp = this.loadsnd("sounds/" + this.sndfrm + "/exp.au");
      this.lstat("Loading Sound Effects...", 10);
      this.exph = this.loadsnd("sounds/" + this.sndfrm + "/exph.au");
      this.lstat("Loading Sound Effects...", 12);
      this.hit = this.loadsnd("sounds/" + this.sndfrm + "/hit.au");
      this.lstat("Loading Sound Effects...", 25);
      var3 = 0;

      do {
         this.las[var3] = this.loadsnd("sounds/" + this.sndfrm + "/l" + var3 + ".au");
         this.lstat("Loading Sound Effects...", 9);
         ++var3;
      } while(var3 < 5);

      this.charged = this.loadsnd("sounds/" + this.sndfrm + "/charged.au");
      this.lstat("Loading Sound Effects...", 12);
   }

   public boolean mouseDown(Event var1, int var2, int var3) {
      if(this.maxmo != -1) {
         this.mon = false;
         if(this.moner.equals("Click here to Start")) {
            this.moner = "Click here to Continue";
         }
      }

      if(this.u.canclick) {
         this.u.space = true;
      }

      return true;
   }

   public void shake() {
      try {
         JSObject var1 = JSObject.getWindow(this);
         var1.eval("shake()");
      } catch (Exception var2) {
         ;
      }

   }

   public void setmover(int[] var1, ContO[] var2, userCraft var3, xtGraphics var4) {
      int var5 = 0;

      do {
         var2[var5].out = true;
         var2[var5].wire = false;
         ++var5;
      } while(var5 < 5);

      var1[0] = var4.selected;
      var2[var1[0]].x = 3000;
      var2[var1[0]].y = 250;
      var2[var1[0]].z = -500;
      var2[var1[0]].out = false;
      var3.reset(var1[0]);
      var2[var1[0]].reset();
      var2[var1[0]].xz = 360;
      this.u.jump = 0;
      var4.creset();
   }

   public void loadobjects(ContO[] var1, ContO[] var2, Medium var3, String var4) {
      try {
         URL var5 = new URL(this.getCodeBase(), "siters/" + var4 + ".txt");
         DataInputStream var6 = new DataInputStream(var5.openStream());
         boolean var8 = false;

         String var7;
         while((var7 = var6.readLine()) != null) {
            String var9 = "" + var7.trim();
            if(var9.startsWith("l")) {
               String var10 = this.getstring("l", var9, 0);
               int var11;
               int var12;
               int var13;
               if(!var8) {
                  var11 = this.getint("l", var9, 1) * 10;
                  var12 = this.getint("l", var9, 2) * 10;
                  var13 = this.getint("l", var9, 3) * 10;
               } else {
                  var11 = this.getint("l", var9, 1);
                  var12 = this.getint("l", var9, 2);
                  var13 = this.getint("l", var9, 3);
               }

               int var14 = 0;

               do {
                  if(this.obj[var14].equals(var10 + ".rad")) {
                     var1[this.maxco] = new ContO(var3, var2[var14], var11, var12, var13);
                     ++this.maxco;
                  }

                  ++var14;
               } while(var14 < 53);
            }

            if(var9.startsWith("xy")) {
               var1[this.maxco - 1].xy = this.getint("xy", var9, 0);
            }

            if(var9.startsWith("xz")) {
               var1[this.maxco - 1].xz = this.getint("xz", var9, 0);
            }

            if(var9.startsWith("zy")) {
               var1[this.maxco - 1].zy = this.getint("zy", var9, 0);
            }

            if(var9.startsWith("xmult")) {
               if(var8) {
                  var8 = false;
               } else {
                  var8 = true;
               }
            }
         }

         var6.close();
      } catch (Exception var15) {
         ;
      }

      System.gc();
   }

   public void run() {
      this.gamer.setPriority(10);
      Medium var1 = new Medium();
      xtGraphics var2 = new xtGraphics(var1, this.rd);
      byte var3 = 5;
      String var4 = System.getProperty("java.version");
      String var5 = "" + this.getAppletContext();
      if(var5.startsWith("sun.")) {
         if(var4.startsWith("1.3")) {
            var2.goodsun = true;
         } else if(var4.startsWith("1.4")) {
            this.sosun = true;
         } else {
            this.sosun = true;
            this.sndfrm = "newsun";
         }

         var3 = 15;
      }

      this.testlocation(var1, var3);
      this.lstat("Preparing for loading...", 0);
      ContO[] var6 = new ContO[53];
      ContO[] var7 = new ContO[3000];
      userCraft var8 = new userCraft(var1);
      Tank[] var9 = new Tank[20];
      int var10 = 0;

      do {
         var9[var10] = new Tank(var1);
         ++var10;
      } while(var10 < 20);

      Craft[] var38 = new Craft[20];
      int var11 = 0;

      do {
         var38[var11] = new Craft(var1);
         ++var11;
      } while(var11 < 20);

      this.loadbase(var6, var1);
      this.lstat("Loading 3D Models...", 17);
      boolean var39 = false;
      this.loadobjects(var7, var6, var1, "aces");
      this.lstat("Loading 3D Models...", 1);
      var11 = this.maxco;
      this.loadobjects(var7, var6, var1, "base");
      this.lstat("Loading 3D Models...", 2);
      this.loadobjects(var7, var6, var1, "smap");
      this.lstat("Loading 3D Models...", 44);
      this.loadobjects(var7, var6, var1, "clmap" + (int)(Math.random() * 5.0D) + "");
      this.lstat("Loading 3D Models...", 1);
      this.loadrots(var7, true);
      int var12 = 0;
      int[] var13 = new int[600];

      for(int var14 = 0; var14 < this.maxco; ++var14) {
         if(var7[var14].colides) {
            var13[var12] = var14;
            ++var12;
         }
      }

      int[] var40 = new int[20];
      int[] var15 = new int[20];
      int var16 = 0;

      do {
         this.loadet[var16] = false;
         ++var16;
      } while(var16 < 7);

      this.downloadall(var2);
      Date var41 = new Date();
      long var17 = 0L;
      long var19 = var41.getTime();
      float var21 = 30.0F;
      float var22 = 35.0F;
      boolean var23 = false;
      int var24 = 0;
      int var25 = 0;
      boolean var26 = true;
      this.maxmo = 0;

      while(true) {
         var41 = new Date();
         long var27 = var41.getTime();
         if(!this.mon) {
            int var29;
            int[] var30;
            int var31;
            int var32;
            int var33;
            int[] var43;
            if(!var26) {
               var1.d(this.rd);
               var29 = 0;
               var30 = new int[100];

               for(var31 = 0; var31 < this.maxco; ++var31) {
                  if(var7[var31].dist != 0) {
                     var30[var29] = var31;
                     ++var29;
                  } else {
                     var7[var31].d(this.rd);
                  }
               }

               var43 = new int[var29];

               for(var32 = 0; var32 < var29; ++var32) {
                  var43[var32] = 0;

                  for(var33 = 0; var33 < var29; ++var33) {
                     if(var7[var30[var32]].dist != var7[var30[var33]].dist) {
                        if(var7[var30[var32]].dist < var7[var30[var33]].dist) {
                           ++var43[var32];
                        }
                     } else if(var33 > var32) {
                        ++var43[var32];
                     }
                  }
               }

               int var34;
               for(var32 = 0; var32 < var29; ++var32) {
                  for(var33 = 0; var33 < var29; ++var33) {
                     if(var43[var33] == var32) {
                        if(var7[var30[var33]].fire) {
                           if(var30[var33] == var40[0]) {
                              var8.dl(this.rd);
                           } else {
                              for(var34 = 1; var34 < this.maxmo; ++var34) {
                                 if(var30[var33] == var40[var34]) {
                                    if(var15[var34] == 0) {
                                       var38[var34].dl(this.rd);
                                    }

                                    if(var15[var34] == 1) {
                                       var9[var34].dl(this.rd);
                                    }
                                 }
                              }
                           }
                        }

                        var7[var30[var33]].d(this.rd);
                     }
                  }
               }

               if(var2.level < 6) {
                  for(var32 = 0; var32 < var12; ++var32) {
                     for(var33 = 0; var33 < this.maxmo; ++var33) {
                        if(var40[var33] != var13[var32]) {
                           var7[var13[var32]].tryexp(var7[var40[var33]]);
                           if(var7[var40[var33]].fire) {
                              if(var33 == 0) {
                                 var8.lasercolid(var7[var13[var32]]);
                              } else {
                                 if(var15[var33] == 0) {
                                    var38[var33].lasercolid(var7[var13[var32]]);
                                 }

                                 if(var15[var33] == 1) {
                                    var9[var33].lasercolid(var7[var13[var32]]);
                                 }
                              }
                           }
                        }
                     }
                  }
               } else {
                  for(var32 = var12 - 1; var32 >= 0; --var32) {
                     for(var33 = 0; var33 < this.maxmo; ++var33) {
                        if(var40[var33] != var13[var32]) {
                           if(var2.level != 15 || var33 != 1) {
                              var7[var13[var32]].tryexp(var7[var40[var33]]);
                           }

                           if(var7[var40[var33]].fire) {
                              if(var33 == 0) {
                                 var8.lasercolid(var7[var13[var32]]);
                              } else {
                                 if(var15[var33] == 0) {
                                    var38[var33].lasercolid(var7[var13[var32]]);
                                 }

                                 if(var15[var33] == 1) {
                                    var9[var33].lasercolid(var7[var13[var32]]);
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               for(var32 = 1; var32 < this.maxmo; ++var32) {
                  if(var15[var32] == 0) {
                     var38[var32].dosmokes(this.rd, var7[var40[var32]]);
                     var38[var32].preform(var7[var40[var32]], var7, var13, var12, var40[0], var11);
                     if(var7[var40[var32]].exp) {
                        if(!this.nosound) {
                           this.exp.play();
                        }

                        var15[var32] = -1;
                     }

                     if(var7[var40[var32]].hit && !this.nosound && this.frags == 0) {
                        this.hitl.play();
                        if(this.sosun) {
                           this.frags = 3;
                        }
                     }
                  }

                  if(var15[var32] == 1) {
                     var9[var32].dosmokes(this.rd, var7[var40[var32]]);
                     var9[var32].preform(var7[var40[var32]], var7, var40[0], var11);
                     if(var7[var40[var32]].exp) {
                        if(!this.nosound) {
                           this.exp.play();
                        }

                        var15[var32] = -1;
                     }

                     if(var7[var40[var32]].hit && !this.nosound && this.frags == 0) {
                        this.hitl.play();
                        if(this.sosun) {
                           this.frags = 3;
                        }
                     }
                  }
               }

               var8.dosmokes(this.rd, var7[var40[0]]);
               var8.preform(this.u, var7[var40[0]], var7, var40, this.maxmo);
               var32 = 0;
               if(this.tab) {
                  var32 = var2.cl;
               } else if(this.view != 4 && this.view != 5) {
                  var2.dtrakers(this.rd, var15, var40, this.maxmo, var7, var8, this.u);
               }

               if(this.view == 0) {
                  var1.behinde(var7[var40[var32]]);
               }

               if(this.view == 1) {
                  var1.right(var7[var40[var32]]);
               }

               if(this.view == 2) {
                  var1.infront(var7[var40[var32]]);
               }

               if(this.view == 3) {
                  var1.left(var7[var40[var32]]);
               }

               if(this.view == 4) {
                  var1.around(var7[var40[var32]], 800);
               }

               if(this.view == 5) {
                  var1.watch(var7[var40[var32]]);
               } else if(var1.td) {
                  var1.td = false;
               }

               if(var7[var40[0]].exp) {
                  var33 = 0;

                  for(var34 = 0; var34 < var7[var40[0]].npl; ++var34) {
                     if(var7[var40[0]].p[var34].exp == 7) {
                        ++var33;
                     }
                  }

                  if(var33 == var7[var40[0]].npl) {
                     var26 = true;
                     var2.dest[var40[0]] = true;
                     if(var2.alldest()) {
                        var2.fase = 2;
                        var2.drawovimg(this.offImage);
                     } else {
                        var2.fase = 1;
                        var2.drawefimg(this.offImage);
                     }
                  }

                  if(this.u.space) {
                     this.u.space = false;
                  }
               } else {
                  if(var2.mcomp) {
                     if(this.u.space) {
                        if(var2.level != 15) {
                           var2.fase = -4;
                           ++var2.level;
                        } else {
                           var2.fase = 4;
                           var2.oldfase = 7;
                        }

                        var26 = true;
                        this.u.space = false;
                     }
                  } else if(this.u.space) {
                     var26 = true;
                     var2.drawpimg(this.offImage);
                     var2.fase = 3;
                     this.u.space = false;
                     var2.select = 0;
                  }

                  var33 = 0;

                  for(var34 = var11; var34 < var11 + 13; ++var34) {
                     if(var7[var34].exp) {
                        ++var33;
                     }
                  }

                  if(var33 == 13) {
                     var26 = true;
                     var2.drawovimg(this.offImage);
                     var2.fase = 2;
                  }
               }
            } else {
               if(var2.fase == -4) {
                  var1.d(this.rd);
                  var29 = 0;
                  var30 = new int[100];

                  for(var31 = 0; var31 < this.maxco; ++var31) {
                     if(var7[var31].dist != 0) {
                        var30[var29] = var31;
                        ++var29;
                     } else {
                        var7[var31].d(this.rd);
                     }
                  }

                  var43 = new int[var29];

                  for(var32 = 0; var32 < var29; ++var32) {
                     var43[var32] = 0;

                     for(var33 = 0; var33 < var29; ++var33) {
                        if(var7[var30[var32]].dist != var7[var30[var33]].dist) {
                           if(var7[var30[var32]].dist < var7[var30[var33]].dist) {
                              ++var43[var32];
                           }
                        } else if(var33 > var32) {
                           ++var43[var32];
                        }
                     }
                  }

                  for(var32 = 0; var32 < var29; ++var32) {
                     for(var33 = 0; var33 < var29; ++var33) {
                        if(var43[var33] == var32) {
                           var7[var30[var33]].d(this.rd);
                        }
                     }
                  }

                  var1.around(var7[var11 + 4], 6000);
                  if(this.u.space) {
                     var2.drawl(this.rd, this.offImage);
                  }
               }

               var2.denter(this.rd, var11, var7, var8, this.u);
               if(var2.fase == -5 && this.u.space) {
                  if(var2.select == 0) {
                     this.loadrots(var7, false);

                     for(var29 = var11; var29 < var11 + 13; ++var29) {
                        var7[var29].out = false;
                     }

                     var2.reset();
                     var2.fase = -4;
                  }

                  if(var2.select == 1 && var2.sgame == 1) {
                     this.loadrots(var7, false);
                     var2.reset();
                     this.loadsaved(var7, var2, var11);
                     var2.fase = -4;
                  }

                  if(var2.select == 4 && this.maxmo != 0) {
                     this.moner = "Exiting game...";
                     this.mon = true;
                  }

                  this.u.space = false;
               }

               if(var2.fase == 4) {
                  this.shake();
               }

               if(var2.fase == -33) {
                  if(var2.frst && var2.select == 0) {
                     this.savegame(var7, var2, var11);
                  } else if(!var2.frst) {
                     var2.frst = true;
                  }

                  if(var25 != 7) {
                     if(var2.goodsun) {
                        this.nounif = true;
                     }

                     this.mtrak[var25] = this.getAudioClip(this.getCodeBase(), "music/" + var25 + ".au");
                     this.loadet[var25] = true;
                     ++var25;
                  } else if(var2.goodsun) {
                     var2.goodsun = false;
                  }

                  this.loadmovers(var40, var15, var7, var38, var9, var8, var2);
                  this.nounif = false;
                  var2.fase = -2;
               }

               if(var2.fase == -3) {
                  var2.fase = -33;
               }

               if(var2.fase == 0 && this.u.space) {
                  if(!var2.dest[var2.selected]) {
                     this.setmover(var40, var7, var8, var2);
                     var26 = false;
                     this.view = 0;
                  }

                  this.u.space = false;
               }

               if(var2.fase == 2 && var2.sgame == 1 && !var2.alldest()) {
                  this.set0();
                  var2.sgame = 0;
               }

               if(var2.fase == 3 && this.u.space) {
                  if(var2.select == 0) {
                     System.gc();
                     var26 = false;
                  }

                  this.u.space = false;
               }

               if(var2.fase == -8) {
                  if(var2.sgame == -1) {
                     this.getslevel(var2);
                  }

                  if(var2.cnty == 351) {
                     var2.drawop(this.rd, this.offImage);
                     var2.cnty = 352;
                  }
               }

               if(var2.fase == 7 && this.u.space) {
                  this.moner = "One moment...";
                  this.mon = true;
                  this.u.space = false;
               }
            }
         } else {
            if(this.u.space) {
               this.u.space = false;
            }

            this.rd.setColor(new Color(223, 223, 223));
            this.rd.fillRect(0, 0, 500, 360);
            var2.drawcs(this.rd, 170, this.moner, 0, 0, 0, false);
            URL var42;
            if(this.moner.equals("Exiting game...")) {
               this.repaint();
               System.gc();

               try {
                  var42 = new URL(this.getCodeBase(), "web/exit.html");
                  this.getAppletContext().showDocument(var42);
               } catch (Exception var37) {
                  ;
               }

               System.gc();
               this.gamer.stop();
            }

            if(this.moner.equals("One moment...")) {
               this.repaint();
               System.gc();
               this.unloadit();

               try {
                  var42 = new URL(this.getCodeBase(), "http://www.radicalplay.com/aces/winner/index.html");
                  this.getAppletContext().showDocument(var42);
               } catch (Exception var36) {
                  ;
               }

               System.gc();
               this.gamer.stop();
            }
         }

         this.repaint();
         if(!this.mon) {
            this.playsounds(var8, var7[var40[0]], var26, var2);
         }

         var41 = new Date();
         long var44 = var41.getTime();
         if(!var26) {
            if(!var23) {
               var21 = var22;
               var23 = true;
               var24 = 0;
            }

            if(var24 == 10) {
               if(var44 - var19 < 560L) {
                  var21 = (float)((double)var21 + 0.5D);
               } else {
                  var21 = (float)((double)var21 - 0.5D);
                  if(var21 < 5.0F) {
                     var21 = 5.0F;
                  }
               }

               var19 = var44;
               var24 = 0;
            } else {
               ++var24;
            }
         } else {
            if(var23) {
               var22 = var21;
               var23 = false;
               var24 = 0;
            }

            if(var24 == 10) {
               if(var44 - var19 < 400L) {
                  var21 = (float)((double)var21 + 3.5D);
               } else {
                  var21 = (float)((double)var21 - 3.5D);
                  if(var21 < 5.0F) {
                     var21 = 5.0F;
                  }
               }

               var19 = var44;
               var24 = 0;
            } else {
               ++var24;
            }
         }

         var17 = (long)Math.round(var21) - (var44 - var27);
         if(var17 < (long)var3) {
            var17 = (long)var3;
         }

         try {
            Thread.sleep(var17);
         } catch (InterruptedException var35) {
            ;
         }
      }
   }

   public void lstat(String var1, int var2) {
      this.dnload += var2;
      this.rd.setColor(new Color(223, 223, 223));
      this.rd.fillRect(0, 0, 500, 360);
      this.rd.setColor(new Color(174, 185, 198));
      this.rd.drawRect(150, 200, 200, 5);
      this.rd.fillRect(150, 200, 24 + (int)((float)this.dnload / 594.0F * 176.0F), 5);
      this.rd.setColor(new Color(151, 166, 183));
      this.rd.drawString(var1, 290, 220);
      this.rd.drawString("Remaining: " + (594 - this.dnload) + " KB", 202, 250);
      this.rd.setColor(new Color(0, 0, 0));
      this.rd.drawString("Loading " + (int)((float)(24 + (int)((float)this.dnload / 594.0F * 176.0F)) / 200.0F * 100.0F) + "%", 103, 194);
      this.repaint();
   }

   public void init() {
      this.offImage = this.createImage(500, 360);
      if(this.offImage != null) {
         this.rd = this.offImage.getGraphics();
      }

      this.rd.setFont(new Font("SansSerif", 1, 11));
   }

   public void getslevel(xtGraphics var1) {
      try {
         JSObject var2 = JSObject.getWindow(this);
         var2.eval("s=GetCookie(\'radxv\')");
         String var3 = String.valueOf(String.valueOf(var2.getMember("s")));
         if(var3.equals("0")) {
            var1.sgame = 0;
         } else {
            var1.sgame = 1;
            var1.select = 1;
         }
      } catch (Exception var4) {
         ;
      }

   }

   public void loadsaved(ContO[] var1, xtGraphics var2, int var3) {
      try {
         JSObject var4 = JSObject.getWindow(this);
         var4.eval("s=GetCookie(\'radxv\')");
         String var5 = String.valueOf(String.valueOf(var4.getMember("s")));
         var2.level = Integer.valueOf(var5).intValue();

         int var6;
         String var7;
         for(var6 = var3; var6 < var3 + 13; ++var6) {
            var4.eval("ss=GetCookie(\'radnhits" + var6 + "\')");
            var7 = String.valueOf(String.valueOf(var4.getMember("ss")));
            var1[var6].nhits = Integer.valueOf(var7).intValue();
            if(var1[var6].nhits >= var1[var6].maxhits) {
               var1[var6].exp = true;
               var1[var6].out = true;
            } else {
               var1[var6].out = false;
            }
         }

         var6 = 0;

         do {
            var4.eval("sss=GetCookie(\'raddest" + var6 + "\')");
            var7 = String.valueOf(String.valueOf(var4.getMember("sss")));
            if(var7.equals("false")) {
               var2.dest[var6] = false;
            } else {
               var2.dest[var6] = true;
            }

            ++var6;
         } while(var6 < 5);
      } catch (Exception var8) {
         ;
      }

   }

   public boolean keyDown(Event var1, int var2) {
      if(var2 == 49) {
         this.view = 1;
      }

      if(var2 == 50) {
         this.view = 2;
      }

      if(var2 == 51) {
         this.view = 3;
      }

      if(var2 == 52) {
         this.view = 4;
      }

      if(var2 == 53) {
         this.view = 5;
      }

      if(var2 == 109 || var2 == 77) {
         if(this.nomusic) {
            this.nomusic = false;
         } else {
            this.nomusic = true;
            if(this.plcnt >= 100 && this.crntt != -1) {
               this.mtrak[this.crntt].stop();
               this.crntt += -1;
               this.plcnt = 95;
            }
         }
      }

      if(var2 == 116 || var2 == 84) {
         if(this.plcnt >= 100) {
            this.mtrak[this.crntt].stop();
         }

         this.plcnt = 95;
      }

      if(var2 == 115 || var2 == 83) {
         if(this.nosound) {
            this.nosound = false;
         } else {
            this.nosound = true;
         }
      }

      if(var2 == 114 || var2 == 82) {
         this.u.radar = true;
      }

      if(var2 == 9) {
         this.tab = true;
      }

      if(var2 == 43 || var2 == 61) {
         this.u.plus = true;
      }

      if(var2 == 45 || var2 == 8) {
         this.u.mins = true;
      }

      if((var2 == 106 || var2 == 74) && this.u.jump == 0) {
         this.u.jump = 1;
         if(!this.u.jade) {
            this.u.jade = true;
         }
      }

      if((var2 == 10 || var2 == 27) && !this.enterd) {
         this.u.space = true;
         this.enterd = true;
      }

      if(var2 == 32) {
         this.u.fire = true;
      }

      if(var2 == 1006) {
         this.u.left = true;
      }

      if(var2 == 1007) {
         this.u.right = true;
      }

      if(var2 == 1005) {
         this.u.down = true;
      }

      if(var2 == 1004) {
         this.u.up = true;
      }

      return false;
   }
}
