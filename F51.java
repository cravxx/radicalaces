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
			if (var11.startsWith("http:/")) {
				for (; var12 < var11.length() - 1; ++var12) {
					if (var11.startsWith("radicalplay.com", var12)) {
						var10 = true;
					}
				}
			} else {
				var10 = true;
			}

			if (!var10) {
				rd.setColor(new Color(223, 223, 223));
				rd.fillRect(0, 0, 500, 360);
				rd.setColor(new Color(0, 0, 0));
				rd.drawString("Access Denied !", 30, 50);
				rd.drawString("This game will not run under this loaction:", 30, 100);
				rd.drawString("" + getDocumentBase(), 30, 120);
				rd.drawString("Please contact Radicalplay.com for details.", 30, 200);
				repaint();
				var9.eval(
						"window.open(\'http://www.radicalplay.com/aces/\',\'olen\',\'menubar=1,toolbar=1,location=1,resizable=1,status=1,scrollbars=1\');");
				gamer.stop();
			}

			if (var2 == 15) {
				var9.eval("sti=\'\'+screen.colorDepth;");
				var11 = String.valueOf(String.valueOf(var9.getMember("sti")));
				if (var11.equals("16")) {
					var1.isun = true;
				}
			}
		} catch (Exception var8) {
			boolean var3 = false;
			String var4 = "" + getDocumentBase();
			int var5 = 0;
			if (var4.startsWith("http:/")) {
				for (; var5 < var4.length() - 1; ++var5) {
					if (var4.startsWith("radicalplay.com", var5)) {
						var3 = true;
					}
				}
			} else {
				var3 = true;
			}

			if (!var3) {
				rd.setColor(new Color(223, 223, 223));
				rd.fillRect(0, 0, 500, 360);
				rd.setColor(new Color(0, 0, 0));
				rd.drawString("Access Denied !", 30, 50);
				rd.drawString("This game will not run under this loaction:", 30, 100);
				rd.drawString("" + getDocumentBase(), 30, 120);
				rd.drawString("Please contact Radicalplay.com for details.", 30, 200);
				repaint();

				try {
					URL var6 = new URL("http://www.radicalplay.com/aces/");
					getAppletContext().showDocument(var6, "olen");
				} catch (Exception var7) {
					;
				}

				gamer.stop();
			}
		}

	}

	public void stop() {
		into.stop();
		miso.stop();
		selo.stop();
		mano.stop();
		upl.stop();
		downl.stop();
		low.stop();
		med.stop();
		ljump.stop();
		grnd.stop();
		exp.stop();
		exph.stop();
		hit.stop();
		hitl.stop();
		charged.stop();
		int var1 = 0;

		do {
			las[var1].stop();
			++var1;
		} while (var1 < 5);

		var1 = 0;

		do {
			if (loadet[var1]) {
				mtrak[var1].stop();
			}

			++var1;
		} while (var1 < 7);

		if (gamer != null) {
			gamer.stop();
		}

		gamer = null;
		rd.dispose();
	}

	public boolean lostFocus(Event var1, Object var2) {
		if (!nounif) {
			mon = true;
		}

		if (maxmo != -1) {
			view = 0;
			u.radar = false;
			u.plus = false;
			u.mins = false;
			enterd = false;
			tab = false;
			u.fire = false;
			u.left = false;
			u.right = false;
			u.down = false;
			u.up = false;
		}

		return false;
	}

	public void playsounds(userCraft var1, ContO var2, boolean var3, xtGraphics var4) {
		if (!var3) {
			if (!nosound) {
				if (!var2.exp && var1.speed > 10.0F && !pmed) {
					if (!plow) {
						low.loop();
						plow = true;
					}
				} else if (plow) {
					low.stop();
					plow = false;
				}

				if (var1.speed > 65.0F) {
					if (!pmed) {
						med.loop();
						pmed = true;
					}
				} else if (pmed) {
					med.stop();
					pmed = false;
				}

				if (var1.speed > 65.0F && u.up) {
					if (pupl == 0) {
						pupl = 70;
						upl.play();
					}
				} else if (pupl != 0) {
					pupl += -1;
				}

				if (var1.speed > 65.0F && u.down) {
					if (pdownl == 0) {
						pdownl = 70;
						downl.play();
					}
				} else if (pdownl != 0) {
					pdownl += -1;
				}

				if (var1.speed == 400.0F) {
					ljump.play();
				}

				if (var1.ester == 1) {
					charged.play();
				}

				if (var2.hit && frags == 0) {
					hit.play();
					if (sosun) {
						frags = 3;
					}
				}

				if (sosun && frags != 0) {
					frags += -1;
				}

				if (u.fire && !var2.exp) {
					if (lascnt == 0) {
						las[var1.ltyp].play();
						lascnt = 14;
					} else {
						lascnt += -1;
					}
				} else if (lascnt != 0) {
					lascnt = 0;
				}

				if (pgrnd == 0) {
					if (!var2.exp && var2.y > 200
							&& (var1.sms[0] == 1 || var1.sms[1] == 1 || var1.sms[2] == 1 || var1.sms[3] == 1)) {
						grnd.play();
						pgrnd = 2;
					}
				} else {
					pgrnd += -1;
				}

				if (var2.exp) {
					if (!pexph) {
						exph.play();
						pexph = true;
						shake();
					}
				} else if (pexph) {
					pexph = false;
				}
			} else {
				if (pmed) {
					med.stop();
					pmed = false;
				}

				if (plow) {
					low.stop();
					plow = false;
				}
			}

			if (psel) {
				selo.stop();
				psel = false;
			}

			if (plcnt == 100) {
				++crntt;
				if (crntt == 7) {
					crntt = 0;
				}

				if (loadet[crntt]) {
					mtrak[crntt].loop();
				} else {
					crntt = -1;
					int var5 = 6;

					do {
						if (loadet[var5]) {
							crntt = var5;
						}

						--var5;
					} while (var5 >= 0);

					if (crntt != -1) {
						mtrak[crntt].loop();
					}
				}
			}

			if (plcnt != 2000) {
				if (!nomusic) {
					++plcnt;
				}
			} else {
				plcnt = 80;
				mtrak[crntt].stop();
			}
		} else {
			if (pmed) {
				med.stop();
				pmed = false;
			}

			if (plow) {
				low.stop();
				plow = false;
			}

			if (plcnt != 0 && crntt != -1 && var4.fase != -4 && var4.fase != 1 && var4.fase != 2) {
				if (plcnt >= 100) {
					mtrak[crntt].stop();
				}

				if (var4.fase == 3 && plcnt >= 100) {
					crntt += -1;
				}

				plcnt = 0;
			}

			if (var4.fase == -8 && var4.cnty < 351 && !nomusic) {
				if (!pint) {
					into.loop();
					pint = true;
				}
			} else {
				if (pint) {
					into.stop();
					pint = false;
				}

				if (var4.cnty == 352) {
					hit.play();
					var4.cnty = 353;
				}
			}

			if ((var4.fase == -5 || var4.fase == 7) && !nomusic) {
				if (!pman) {
					mano.loop();
					pman = true;
				}
			} else if (pman) {
				mano.stop();
				pman = false;
			}

			if (var4.fase == -1 && !nomusic) {
				if (!pmis) {
					miso.loop();
					pmis = true;
				}
			} else if (pmis) {
				miso.stop();
				pmis = false;
			}

			if ((var4.fase == 0 || var4.fase == 5 || var4.fase == 6) && !nomusic) {
				if (!psel) {
					selo.loop();
					psel = true;
				}
			} else if (psel) {
				selo.stop();
				psel = false;
			}

			if (var4.fase == 7) {
				if (pupl == 0) {
					pupl = 30;
					upl.play();
				} else {
					pupl += -1;
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
		AudioClip var2 = getAudioClip(getCodeBase(), var1);
		if (!sosun) {
			var2.play();
			Thread.yield();
			var2.stop();
		}

		return var2;
	}

	public String getstring(String var1, String var2, int var3) {
		int var5 = 0;
		String var7 = "";

		for (int var4 = var1.length() + 1; var4 < var2.length(); ++var4) {
			String var6 = "" + var2.charAt(var4);
			if (var6.equals(",") || var6.equals(")")) {
				++var5;
				++var4;
			}

			if (var5 == var3) {
				var7 = var7 + var2.charAt(var4);
			}
		}

		return var7;
	}

	public int getint(String var1, String var2, int var3) {
		int var5 = 0;
		String var7 = "";

		for (int var4 = var1.length() + 1; var4 < var2.length(); ++var4) {
			String var6 = "" + var2.charAt(var4);
			if (var6.equals(",") || var6.equals(")")) {
				++var5;
				++var4;
			}

			if (var5 == var3) {
				var7 = var7 + var2.charAt(var4);
			}
		}

		return Integer.valueOf(var7).intValue();
	}

	public void paint(Graphics var1) {
		var1.drawImage(offImage, 0, 0, this);
	}

	public void savegame(ContO[] var1, xtGraphics var2, int var3) {
		try {
			JSObject var4 = JSObject.getWindow(this);
			var4.eval("SetCookie(\'radxv\',\'" + var2.level + "\')");

			int var5;
			for (var5 = var3; var5 < var3 + 13; ++var5) {
				var4.eval("SetCookie(\'radnhits" + var5 + "\',\'" + var1[var5].nhits + "\')");
			}

			var5 = 0;

			do {
				var4.eval("SetCookie(\'raddest" + var5 + "\',\'" + var2.dest[var5] + "\')");
				++var5;
			} while (var5 < 5);

			var2.sgame = 1;
		} catch (Exception var6) {
			;
		}

	}

	public void destroy() {
		if (gamer != null) {
			gamer.stop();
		}

		gamer = null;
	}

	public void loadrots(ContO[] var1, boolean var2) {
		for (int var3 = 0; var3 < maxco; ++var3) {
			var1[var3].loadrots(var2);
		}

	}

	public Image loadimg(String var1, MediaTracker var2) {
		Image var3 = getImage(getCodeBase(), var1);
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
			URL var3 = new URL(getCodeBase(), "graphics/models.radq");
			DataInputStream var4 = new DataInputStream(var3.openStream());
			ZipInputStream var5 = new ZipInputStream(var4);
			ZipEntry var6 = var5.getNextEntry();
			Object var7 = null;

			for (int var8 = 0; var6 != null; var6 = var5.getNextEntry()) {
				int var9 = (int) var6.getSize();
				byte[] var13 = new byte[var9];

				int var11;
				for (int var10 = 0; var9 > 0; var9 -= var11) {
					var11 = var5.read(var13, var10, var9);
					var10 += var11;
				}

				var1[var8] = new ContO(var13, var2, 0, 0, 0);
				obj[var8] = var6.getName();
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
		paint(var1);
	}

	public void loadmovers(int[] var1, int[] var2, ContO[] conto, Craft[] craft, Tank[] tank, userCraft usercraft,
			xtGraphics xtgraphics) {
		for (int var8 = 1; var8 < maxmo; ++var8) {
			conto[var1[var8]].out = true;
		}

		maxmo = 1;
		xtgraphics.nb = 0;
		xtgraphics.mcomp = false;

		try {
			URL var13 = new URL(getCodeBase(), "levels/" + xtgraphics.level + ".txt");
			DataInputStream var9 = new DataInputStream(var13.openStream());

			String var10;
			while ((var10 = var9.readLine()) != null) {
				String var11 = "" + var10.trim();
				if (var11.startsWith("craft")) {
					var1[maxmo] = getint("craft", var11, 0);
					var2[maxmo] = 0;
				}

				if (var11.startsWith("tank")) {
					var1[maxmo] = getint("tank", var11, 0);
					var2[maxmo] = 1;
				}

				if (var11.startsWith("name")) {
					xtgraphics.mname[maxmo - 1] = getstring("name", var11, 0);
					xtgraphics.cnte[maxmo - 1] = 0;
				}

				if (var11.startsWith("l")) {
					conto[var1[maxmo]].x = getint("l", var11, 0) * 10;
					conto[var1[maxmo]].y = getint("l", var11, 1) * 10;
					conto[var1[maxmo]].z = getint("l", var11, 2) * 10;
					conto[var1[maxmo]].out = false;
					conto[var1[maxmo]].reset();
				}

				if (var11.startsWith("prompt")) {
					if (getstring("prompt", var11, 0).equals("tank")) {
						xtgraphics.tnk[xtgraphics.nb] = true;
					} else {
						xtgraphics.tnk[xtgraphics.nb] = false;
					}

					xtgraphics.ob[xtgraphics.nb] = getint("prompt", var11, 1);
					xtgraphics.nam[xtgraphics.nb] = getstring("prompt", var11, 2).replace('|', ',');
					++xtgraphics.nb;
				}

				if (var11.startsWith("stat")) {
					if (var2[maxmo] == 0) {
						craft[maxmo].reset(getint("stat", var11, 0), getint("stat", var11, 1), getint("stat", var11, 2),
								getint("stat", var11, 3), getint("stat", var11, 4), getint("stat", var11, 5));
					} else {
						tank[maxmo].reset(getint("stat", var11, 0), getint("stat", var11, 1));
					}

					++maxmo;
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
		if (var2 == 49 && view == 1) {
			view = 0;
		}

		if (var2 == 50 && view == 2) {
			view = 0;
		}

		if (var2 == 51 && view == 3) {
			view = 0;
		}

		if (var2 == 52 && view == 4) {
			view = 0;
		}

		if (var2 == 53 && view == 5) {
			view = 0;
		}

		if (var2 == 114 || var2 == 82) {
			u.radar = false;
		}

		if (var2 == 43 || var2 == 61) {
			u.plus = false;
		}

		if (var2 == 45 || var2 == 8) {
			u.mins = false;
		}

		if (var2 == 10 || var2 == 27) {
			enterd = false;
		}

		if (var2 == 9) {
			tab = false;
		}

		if (var2 == 32) {
			u.fire = false;
		}

		if (var2 == 1006) {
			u.left = false;
		}

		if (var2 == 1007) {
			u.right = false;
		}

		if (var2 == 1005) {
			u.down = false;
		}

		if (var2 == 1004) {
			u.up = false;
		}

		return false;
	}

	public void start() {
		if (gamer == null) {
			gamer = new Thread(this);
		}

		gamer.start();
	}

	public void downloadall(xtGraphics var1) {
		MediaTracker var2 = new MediaTracker(this);
		var1.radar = loadimg("graphics/radar.gif", var2);
		lstat("Loading Images...", 1);
		var1.stube = loadimg("graphics/stube.gif", var2);
		lstat("Loading Images...", 2);
		var1.sback = loadimg("graphics/select.jpg", var2);
		lstat("Loading Images...", 18);
		var1.destr = loadimg("graphics/destroyed.gif", var2);
		lstat("Loading Images...", 2);
		var1.saveit(loadimg("graphics/failed.jpg", var2), var1.bpix);
		lstat("Loading Images...", 31);
		var1.saveit(loadimg("graphics/mission.jpg", var2), var1.mpix);
		lstat("Loading Images...", 22);
		var1.saveit(loadimg("graphics/over.jpg", var2), var1.opix);
		lstat("Loading Images...", 21);
		var1.saveit(loadimg("graphics/paused.jpg", var2), var1.ppix);
		lstat("Loading Images...", 10);
		var1.lay = loadimg("graphics/layout.gif", var2);
		lstat("Loading Images...", 1);
		var1.complete = loadimg("graphics/comp.gif", var2);
		lstat("Loading Images...", 2);
		var1.main = loadimg("graphics/main.gif", var2);
		lstat("Loading Images...", 32);
		var1.rad = loadimg("graphics/radicalplay.gif", var2);
		lstat("Loading Images...", 2);
		int var3 = 0;

		do {
			var1.as[var3] = loadimg("graphics/a" + var3 + ".gif", var2);
			lstat("Loading Images...", 1);
			++var3;
		} while (var3 < 5);

		var1.inst1 = loadimg("graphics/inst1.gif", var2);
		lstat("Loading Images...", 10);
		var1.inst2 = loadimg("graphics/inst2.gif", var2);
		lstat("Loading Images...", 11);
		var1.inst3 = loadimg("graphics/inst3.gif", var2);
		lstat("Loading Images...", 4);
		var1.text = loadimg("graphics/text.gif", var2);
		lstat("Loading Images...", 6);
		var1.mars = loadimg("graphics/mars.jpg", var2);
		lstat("Loading Images...", 15);
		into = loadsnd("music/intro.au");
		lstat("Loading Music...", 24);
		miso = loadsnd("music/mission.au");
		lstat("Loading Music...", 29);
		selo = loadsnd("music/select.au");
		lstat("Loading Music...", 52);
		mano = loadsnd("music/main.au");
		lstat("Loading Music...", 50);
		upl = loadsnd("sounds/" + sndfrm + "/up.au");
		lstat("Loading Sound Effects...", 11);
		hitl = loadsnd("sounds/" + sndfrm + "/hitl.au");
		lstat("Loading Sound Effects...", 7);
		downl = loadsnd("sounds/" + sndfrm + "/down.au");
		lstat("Loading Sound Effects...", 10);
		low = loadsnd("sounds/" + sndfrm + "/low.au");
		lstat("Loading Sound Effects...", 11);
		med = loadsnd("sounds/" + sndfrm + "/med.au");
		lstat("Loading Sound Effects...", 6);
		ljump = loadsnd("sounds/" + sndfrm + "/jump.au");
		lstat("Loading Sound Effects...", 25);
		grnd = loadsnd("sounds/" + sndfrm + "/grnd.au");
		lstat("Loading Sound Effects...", 5);
		exp = loadsnd("sounds/" + sndfrm + "/exp.au");
		lstat("Loading Sound Effects...", 10);
		exph = loadsnd("sounds/" + sndfrm + "/exph.au");
		lstat("Loading Sound Effects...", 12);
		hit = loadsnd("sounds/" + sndfrm + "/hit.au");
		lstat("Loading Sound Effects...", 25);
		var3 = 0;

		do {
			las[var3] = loadsnd("sounds/" + sndfrm + "/l" + var3 + ".au");
			lstat("Loading Sound Effects...", 9);
			++var3;
		} while (var3 < 5);

		charged = loadsnd("sounds/" + sndfrm + "/charged.au");
		lstat("Loading Sound Effects...", 12);
	}

	public boolean mouseDown(Event var1, int var2, int var3) {
		if (maxmo != -1) {
			mon = false;
			if (moner.equals("Click here to Start")) {
				moner = "Click here to Continue";
			}
		}

		if (u.canclick) {
			u.space = true;
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
		} while (var5 < 5);

		var1[0] = var4.selected;
		var2[var1[0]].x = 3000;
		var2[var1[0]].y = 250;
		var2[var1[0]].z = -500;
		var2[var1[0]].out = false;
		var3.reset(var1[0]);
		var2[var1[0]].reset();
		var2[var1[0]].xz = 360;
		u.jump = 0;
		var4.creset();
	}

	public void loadobjects(ContO[] var1, ContO[] var2, Medium var3, String var4) {
		try {
			URL var5 = new URL(getCodeBase(), "siters/" + var4 + ".txt");
			DataInputStream var6 = new DataInputStream(var5.openStream());
			boolean var8 = false;

			String var7;
			while ((var7 = var6.readLine()) != null) {
				String var9 = "" + var7.trim();
				if (var9.startsWith("l")) {
					String var10 = getstring("l", var9, 0);
					int var11;
					int var12;
					int var13;
					if (!var8) {
						var11 = getint("l", var9, 1) * 10;
						var12 = getint("l", var9, 2) * 10;
						var13 = getint("l", var9, 3) * 10;
					} else {
						var11 = getint("l", var9, 1);
						var12 = getint("l", var9, 2);
						var13 = getint("l", var9, 3);
					}

					int var14 = 0;

					do {
						if (obj[var14].equals(var10 + ".rad")) {
							var1[maxco] = new ContO(var3, var2[var14], var11, var12, var13);
							++maxco;
						}

						++var14;
					} while (var14 < 53);
				}

				if (var9.startsWith("xy")) {
					var1[maxco - 1].xy = getint("xy", var9, 0);
				}

				if (var9.startsWith("xz")) {
					var1[maxco - 1].xz = getint("xz", var9, 0);
				}

				if (var9.startsWith("zy")) {
					var1[maxco - 1].zy = getint("zy", var9, 0);
				}

				if (var9.startsWith("xmult")) {
					if (var8) {
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
		gamer.setPriority(10);
		Medium m = new Medium();
		xtGraphics xtgraphics = new xtGraphics(m, rd);
		byte var3 = 5;
		String javaV = System.getProperty("java.version");
		String appC = "" + getAppletContext();
		if (appC.startsWith("sun.")) {
			if (javaV.startsWith("1.3")) {
				xtgraphics.goodsun = true;
			} else if (javaV.startsWith("1.4")) {
				sosun = true;
			} else {
				sosun = true;
				sndfrm = "newsun";
			}

			var3 = 15;
		}

		testlocation(m, var3);
		lstat("Preparing for loading...", 0);
		ContO[] conto = new ContO[53];
		ContO[] conto1 = new ContO[3000];
		userCraft usercraft = new userCraft(m);

		/*
		 * set up 20 tanks
		 */
		Tank[] tank = new Tank[20];
		for (int i = 0; i < 20; i++) {
			tank[i] = new Tank(m);
		}

		int var11 = 0;

		/*
		 * set up 20 crafts
		 */
		Craft[] craft = new Craft[20];
		for (int i = 0; i < 20; i++) {
			craft[i] = new Craft(m);
		}

		loadbase(conto, m);
		lstat("Loading 3D Models...", 17);
		loadobjects(conto1, conto, m, "aces");
		lstat("Loading 3D Models...", 1);
		var11 = maxco;
		loadobjects(conto1, conto, m, "base");
		lstat("Loading 3D Models...", 2);
		loadobjects(conto1, conto, m, "smap");
		lstat("Loading 3D Models...", 44);
		loadobjects(conto1, conto, m, "clmap" + (int) (Math.random() * 5.0D) + "");
		lstat("Loading 3D Models...", 1);
		loadrots(conto1, true);
		int var12 = 0;
		int[] var13 = new int[600];

		for (int var14 = 0; var14 < maxco; ++var14) {
			if (conto1[var14].colides) {
				var13[var12] = var14;
				++var12;
			}
		}

		int[] var40 = new int[20];
		int[] var15 = new int[20];
		int var16 = 0;

		do {
			loadet[var16] = false;
			++var16;
		} while (var16 < 7);

		downloadall(xtgraphics);
		Date var41 = new Date();
		long var17 = 0L;
		long var19 = var41.getTime();
		float var21 = 30.0F;
		float var22 = 35.0F;
		boolean var23 = false;
		int var24 = 0;
		int var25 = 0;
		boolean var26 = true;
		maxmo = 0;

		while (true) {
			var41 = new Date();
			long var27 = var41.getTime();
			if (!mon) {
				int var29;
				int[] var30;
				int var31;
				int var32;
				int var33;
				int[] var43;
				if (!var26) {
					m.d(rd);
					var29 = 0;
					var30 = new int[100];

					for (var31 = 0; var31 < maxco; ++var31) {
						if (conto1[var31].dist != 0) {
							var30[var29] = var31;
							++var29;
						} else {
							conto1[var31].d(rd);
						}
					}

					var43 = new int[var29];

					for (var32 = 0; var32 < var29; ++var32) {
						var43[var32] = 0;

						for (var33 = 0; var33 < var29; ++var33) {
							if (conto1[var30[var32]].dist != conto1[var30[var33]].dist) {
								if (conto1[var30[var32]].dist < conto1[var30[var33]].dist) {
									++var43[var32];
								}
							} else if (var33 > var32) {
								++var43[var32];
							}
						}
					}

					int var34;
					for (var32 = 0; var32 < var29; ++var32) {
						for (var33 = 0; var33 < var29; ++var33) {
							if (var43[var33] == var32) {
								if (conto1[var30[var33]].fire) {
									if (var30[var33] == var40[0]) {
										usercraft.dl(rd);
									} else {
										for (var34 = 1; var34 < maxmo; ++var34) {
											if (var30[var33] == var40[var34]) {
												if (var15[var34] == 0) {
													craft[var34].dl(rd);
												}

												if (var15[var34] == 1) {
													tank[var34].dl(rd);
												}
											}
										}
									}
								}

								conto1[var30[var33]].d(rd);
							}
						}
					}

					if (xtgraphics.level < 6) {
						for (var32 = 0; var32 < var12; ++var32) {
							for (var33 = 0; var33 < maxmo; ++var33) {
								if (var40[var33] != var13[var32]) {
									conto1[var13[var32]].tryexp(conto1[var40[var33]]);
									if (conto1[var40[var33]].fire) {
										if (var33 == 0) {
											usercraft.lasercolid(conto1[var13[var32]]);
										} else {
											if (var15[var33] == 0) {
												craft[var33].lasercolid(conto1[var13[var32]]);
											}

											if (var15[var33] == 1) {
												tank[var33].lasercolid(conto1[var13[var32]]);
											}
										}
									}
								}
							}
						}
					} else {
						for (var32 = var12 - 1; var32 >= 0; --var32) {
							for (var33 = 0; var33 < maxmo; ++var33) {
								if (var40[var33] != var13[var32]) {
									if (xtgraphics.level != 15 || var33 != 1) {
										conto1[var13[var32]].tryexp(conto1[var40[var33]]);
									}

									if (conto1[var40[var33]].fire) {
										if (var33 == 0) {
											usercraft.lasercolid(conto1[var13[var32]]);
										} else {
											if (var15[var33] == 0) {
												craft[var33].lasercolid(conto1[var13[var32]]);
											}

											if (var15[var33] == 1) {
												tank[var33].lasercolid(conto1[var13[var32]]);
											}
										}
									}
								}
							}
						}
					}

					for (var32 = 1; var32 < maxmo; ++var32) {
						if (var15[var32] == 0) {
							craft[var32].dosmokes(rd, conto1[var40[var32]]);
							craft[var32].preform(conto1[var40[var32]], conto1, var13, var12, var40[0], var11);
							if (conto1[var40[var32]].exp) {
								if (!nosound) {
									exp.play();
								}

								var15[var32] = -1;
							}

							if (conto1[var40[var32]].hit && !nosound && frags == 0) {
								hitl.play();
								if (sosun) {
									frags = 3;
								}
							}
						}

						if (var15[var32] == 1) {
							tank[var32].dosmokes(rd, conto1[var40[var32]]);
							tank[var32].preform(conto1[var40[var32]], conto1, var40[0], var11);
							if (conto1[var40[var32]].exp) {
								if (!nosound) {
									exp.play();
								}

								var15[var32] = -1;
							}

							if (conto1[var40[var32]].hit && !nosound && frags == 0) {
								hitl.play();
								if (sosun) {
									frags = 3;
								}
							}
						}
					}

					usercraft.dosmokes(rd, conto1[var40[0]]);
					usercraft.preform(u, conto1[var40[0]], conto1, var40, maxmo);
					var32 = 0;
					if (tab) {
						var32 = xtgraphics.cl;
					} else if (view != 4 && view != 5) {
						xtgraphics.dtrakers(rd, var15, var40, maxmo, conto1, usercraft, u);
					}

					if (view == 0) {
						m.behinde(conto1[var40[var32]]);
					}

					if (view == 1) {
						m.right(conto1[var40[var32]]);
					}

					if (view == 2) {
						m.infront(conto1[var40[var32]]);
					}

					if (view == 3) {
						m.left(conto1[var40[var32]]);
					}

					if (view == 4) {
						m.around(conto1[var40[var32]], 800);
					}

					if (view == 5) {
						m.watch(conto1[var40[var32]]);
					} else if (m.td) {
						m.td = false;
					}

					if (conto1[var40[0]].exp) {
						var33 = 0;

						for (var34 = 0; var34 < conto1[var40[0]].npl; ++var34) {
							if (conto1[var40[0]].p[var34].exp == 7) {
								++var33;
							}
						}

						if (var33 == conto1[var40[0]].npl) {
							var26 = true;
							xtgraphics.dest[var40[0]] = true;
							if (xtgraphics.alldest()) {
								xtgraphics.fase = 2;
								xtgraphics.drawovimg(offImage);
							} else {
								xtgraphics.fase = 1;
								xtgraphics.drawefimg(offImage);
							}
						}

						if (u.space) {
							u.space = false;
						}
					} else {
						if (xtgraphics.mcomp) {
							if (u.space) {
								if (xtgraphics.level != 15) {
									xtgraphics.fase = -4;
									++xtgraphics.level;
								} else {
									xtgraphics.fase = 4;
									xtgraphics.oldfase = 7;
								}

								var26 = true;
								u.space = false;
							}
						} else if (u.space) {
							var26 = true;
							xtgraphics.drawpimg(offImage);
							xtgraphics.fase = 3;
							u.space = false;
							xtgraphics.select = 0;
						}

						var33 = 0;

						for (var34 = var11; var34 < var11 + 13; ++var34) {
							if (conto1[var34].exp) {
								++var33;
							}
						}

						if (var33 == 13) {
							var26 = true;
							xtgraphics.drawovimg(offImage);
							xtgraphics.fase = 2;
						}
					}
				} else {
					if (xtgraphics.fase == -4) {
						m.d(rd);
						var29 = 0;
						var30 = new int[100];

						for (var31 = 0; var31 < maxco; ++var31) {
							if (conto1[var31].dist != 0) {
								var30[var29] = var31;
								++var29;
							} else {
								conto1[var31].d(rd);
							}
						}

						var43 = new int[var29];

						for (var32 = 0; var32 < var29; ++var32) {
							var43[var32] = 0;

							for (var33 = 0; var33 < var29; ++var33) {
								if (conto1[var30[var32]].dist != conto1[var30[var33]].dist) {
									if (conto1[var30[var32]].dist < conto1[var30[var33]].dist) {
										++var43[var32];
									}
								} else if (var33 > var32) {
									++var43[var32];
								}
							}
						}

						for (var32 = 0; var32 < var29; ++var32) {
							for (var33 = 0; var33 < var29; ++var33) {
								if (var43[var33] == var32) {
									conto1[var30[var33]].d(rd);
								}
							}
						}

						m.around(conto1[var11 + 4], 6000);
						if (u.space) {
							xtgraphics.drawl(rd, offImage);
						}
					}

					xtgraphics.denter(rd, var11, conto1, usercraft, u);
					if (xtgraphics.fase == -5 && u.space) {
						if (xtgraphics.select == 0) {
							loadrots(conto1, false);

							for (var29 = var11; var29 < var11 + 13; ++var29) {
								conto1[var29].out = false;
							}

							xtgraphics.reset();
							xtgraphics.fase = -4;
						}

						if (xtgraphics.select == 1 && xtgraphics.sgame == 1) {
							loadrots(conto1, false);
							xtgraphics.reset();
							loadsaved(conto1, xtgraphics, var11);
							xtgraphics.fase = -4;
						}

						if (xtgraphics.select == 4 && maxmo != 0) {
							moner = "Exiting game...";
							mon = true;
						}

						u.space = false;
					}

					if (xtgraphics.fase == 4) {
						shake();
					}

					if (xtgraphics.fase == -33) {
						if (xtgraphics.frst && xtgraphics.select == 0) {
							savegame(conto1, xtgraphics, var11);
						} else if (!xtgraphics.frst) {
							xtgraphics.frst = true;
						}

						if (var25 != 7) {
							if (xtgraphics.goodsun) {
								nounif = true;
							}

							mtrak[var25] = getAudioClip(getCodeBase(), "music/" + var25 + ".au");
							loadet[var25] = true;
							++var25;
						} else if (xtgraphics.goodsun) {
							xtgraphics.goodsun = false;
						}

						loadmovers(var40, var15, conto1, craft, tank, usercraft, xtgraphics);
						nounif = false;
						xtgraphics.fase = -2;
					}

					if (xtgraphics.fase == -3) {
						xtgraphics.fase = -33;
					}

					if (xtgraphics.fase == 0 && u.space) {
						if (!xtgraphics.dest[xtgraphics.selected]) {
							setmover(var40, conto1, usercraft, xtgraphics);
							var26 = false;
							view = 0;
						}

						u.space = false;
					}

					if (xtgraphics.fase == 2 && xtgraphics.sgame == 1 && !xtgraphics.alldest()) {
						set0();
						xtgraphics.sgame = 0;
					}

					if (xtgraphics.fase == 3 && u.space) {
						if (xtgraphics.select == 0) {
							System.gc();
							var26 = false;
						}

						u.space = false;
					}

					if (xtgraphics.fase == -8) {
						if (xtgraphics.sgame == -1) {
							getslevel(xtgraphics);
						}

						if (xtgraphics.cnty == 351) {
							xtgraphics.drawop(rd, offImage);
							xtgraphics.cnty = 352;
						}
					}

					if (xtgraphics.fase == 7 && u.space) {
						moner = "One moment...";
						mon = true;
						u.space = false;
					}
				}
			} else {
				if (u.space) {
					u.space = false;
				}

				rd.setColor(new Color(223, 223, 223));
				rd.fillRect(0, 0, 500, 360);
				xtgraphics.drawcs(rd, 170, moner, 0, 0, 0, false);
				URL var42;
				if (moner.equals("Exiting game...")) {
					repaint();
					System.gc();

					try {
						var42 = new URL(getCodeBase(), "web/exit.html");
						getAppletContext().showDocument(var42);
					} catch (Exception var37) {
						;
					}

					System.gc();
					gamer.stop();
				}

				if (moner.equals("One moment...")) {
					repaint();
					System.gc();
					unloadit();

					try {
						var42 = new URL(getCodeBase(), "http://www.radicalplay.com/aces/winner/index.html");
						getAppletContext().showDocument(var42);
					} catch (Exception var36) {
						;
					}

					System.gc();
					gamer.stop();
				}
			}

			repaint();
			if (!mon) {
				playsounds(usercraft, conto1[var40[0]], var26, xtgraphics);
			}

			var41 = new Date();
			long var44 = var41.getTime();
			if (!var26) {
				if (!var23) {
					var21 = var22;
					var23 = true;
					var24 = 0;
				}

				if (var24 == 10) {
					if (var44 - var19 < 560L) {
						var21 = (float) ((double) var21 + 0.5D);
					} else {
						var21 = (float) ((double) var21 - 0.5D);
						if (var21 < 5.0F) {
							var21 = 5.0F;
						}
					}

					var19 = var44;
					var24 = 0;
				} else {
					++var24;
				}
			} else {
				if (var23) {
					var22 = var21;
					var23 = false;
					var24 = 0;
				}

				if (var24 == 10) {
					if (var44 - var19 < 400L) {
						var21 = (float) ((double) var21 + 3.5D);
					} else {
						var21 = (float) ((double) var21 - 3.5D);
						if (var21 < 5.0F) {
							var21 = 5.0F;
						}
					}

					var19 = var44;
					var24 = 0;
				} else {
					++var24;
				}
			}

			var17 = (long) Math.round(var21) - (var44 - var27);
			if (var17 < (long) var3) {
				var17 = (long) var3;
			}

			try {
				Thread.sleep(var17);
			} catch (InterruptedException var35) {
				;
			}
		}
	}

	public void lstat(String var1, int var2) {
		dnload += var2;
		rd.setColor(new Color(223, 223, 223));
		rd.fillRect(0, 0, 500, 360);
		rd.setColor(new Color(174, 185, 198));
		rd.drawRect(150, 200, 200, 5);
		rd.fillRect(150, 200, 24 + (int) ((float) dnload / 594.0F * 176.0F), 5);
		rd.setColor(new Color(151, 166, 183));
		rd.drawString(var1, 290, 220);
		rd.drawString("Remaining: " + (594 - dnload) + " KB", 202, 250);
		rd.setColor(new Color(0, 0, 0));
		rd.drawString(
				"Loading " + (int) ((float) (24 + (int) ((float) dnload / 594.0F * 176.0F)) / 200.0F * 100.0F) + "%",
				103, 194);
		repaint();
	}

	public void init() {
		offImage = createImage(500, 360);
		if (offImage != null) {
			rd = offImage.getGraphics();
		}

		rd.setFont(new Font("SansSerif", 1, 11));
	}

	public void getslevel(xtGraphics var1) {
		try {
			JSObject var2 = JSObject.getWindow(this);
			var2.eval("s=GetCookie(\'radxv\')");
			String var3 = String.valueOf(String.valueOf(var2.getMember("s")));
			if (var3.equals("0")) {
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
			for (var6 = var3; var6 < var3 + 13; ++var6) {
				var4.eval("ss=GetCookie(\'radnhits" + var6 + "\')");
				var7 = String.valueOf(String.valueOf(var4.getMember("ss")));
				var1[var6].nhits = Integer.valueOf(var7).intValue();
				if (var1[var6].nhits >= var1[var6].maxhits) {
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
				if (var7.equals("false")) {
					var2.dest[var6] = false;
				} else {
					var2.dest[var6] = true;
				}

				++var6;
			} while (var6 < 5);
		} catch (Exception var8) {
			;
		}

	}

	public boolean keyDown(Event var1, int var2) {
		if (var2 == 49) {
			view = 1;
		}

		if (var2 == 50) {
			view = 2;
		}

		if (var2 == 51) {
			view = 3;
		}

		if (var2 == 52) {
			view = 4;
		}

		if (var2 == 53) {
			view = 5;
		}

		if (var2 == 109 || var2 == 77) {
			if (nomusic) {
				nomusic = false;
			} else {
				nomusic = true;
				if (plcnt >= 100 && crntt != -1) {
					mtrak[crntt].stop();
					crntt += -1;
					plcnt = 95;
				}
			}
		}

		if (var2 == 116 || var2 == 84) {
			if (plcnt >= 100) {
				mtrak[crntt].stop();
			}

			plcnt = 95;
		}

		if (var2 == 115 || var2 == 83) {
			if (nosound) {
				nosound = false;
			} else {
				nosound = true;
			}
		}

		if (var2 == 114 || var2 == 82) {
			u.radar = true;
		}

		if (var2 == 9) {
			tab = true;
		}

		if (var2 == 43 || var2 == 61) {
			u.plus = true;
		}

		if (var2 == 45 || var2 == 8) {
			u.mins = true;
		}

		if ((var2 == 106 || var2 == 74) && u.jump == 0) {
			u.jump = 1;
			if (!u.jade) {
				u.jade = true;
			}
		}

		if ((var2 == 10 || var2 == 27) && !enterd) {
			u.space = true;
			enterd = true;
		}

		if (var2 == 32) {
			u.fire = true;
		}

		if (var2 == 1006) {
			u.left = true;
		}

		if (var2 == 1007) {
			u.right = true;
		}

		if (var2 == 1005) {
			u.down = true;
		}

		if (var2 == 1004) {
			u.up = true;
		}

		return false;
	}
}
