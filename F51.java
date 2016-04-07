import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import netscape.javascript.JSObject;

public class F51 extends Applet implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = -1399200686375699720L;
    public static final String modelsDir = "data/models.radq";
    public static final String imagesDir = "data/images/";
    Graphics rd;
    Image offImage;
    Thread gamer;
    boolean mon = true;
    String moner = "Click here to Start";

    /**
     * names of the .rad's are loaded into this String array in loadbase()
     */
    String[] modelNames = new String[53];
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

    @Override
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
        int i = 0;
        do {
            las[i].stop();
            ++i;
        } while (i < 5);
        i = 0;
        do {
            if (loadet[i]) {
                mtrak[i].stop();
            }
            ++i;
        } while (i < 7);
        if (gamer != null) {
            gamer.stop();
        }
        gamer = null;
        rd.dispose();
    }

    @Override
    public boolean lostFocus(final Event event, final Object object) {
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

    public void playsounds(final userCraft usercraft, final ContO conto, final boolean flag, final xtGraphics xtgraphics) {
        if (!flag) {
            if (!nosound) {
                if (!conto.exp && usercraft.speed > 10.0F && !pmed) {
                    if (!plow) {
                        low.loop();
                        plow = true;
                    }
                } else if (plow) {
                    low.stop();
                    plow = false;
                }
                if (usercraft.speed > 65.0F) {
                    if (!pmed) {
                        med.loop();
                        pmed = true;
                    }
                } else if (pmed) {
                    med.stop();
                    pmed = false;
                }
                if (usercraft.speed > 65.0F && u.up) {
                    if (pupl == 0) {
                        pupl = 70;
                        upl.play();
                    }
                } else if (pupl != 0) {
                    pupl += -1;
                }
                if (usercraft.speed > 65.0F && u.down) {
                    if (pdownl == 0) {
                        pdownl = 70;
                        downl.play();
                    }
                } else if (pdownl != 0) {
                    pdownl += -1;
                }
                if (usercraft.speed == 400.0F) {
                    ljump.play();
                }
                if (usercraft.ester == 1) {
                    charged.play();
                }
                if (conto.hit && frags == 0) {
                    hit.play();
                    if (sosun) {
                        frags = 3;
                    }
                }
                if (sosun && frags != 0) {
                    frags += -1;
                }
                if (u.fire && !conto.exp) {
                    if (lascnt == 0) {
                        las[usercraft.ltyp].play();
                        lascnt = 14;
                    } else {
                        lascnt += -1;
                    }
                } else if (lascnt != 0) {
                    lascnt = 0;
                }
                if (pgrnd == 0) {
                    if (!conto.exp && conto.y > 200 && (usercraft.sms[0] == 1 || usercraft.sms[1] == 1 || usercraft.sms[2] == 1 || usercraft.sms[3] == 1)) {
                        grnd.play();
                        pgrnd = 2;
                    }
                } else {
                    pgrnd += -1;
                }
                if (conto.exp) {
                    if (!pexph) {
                        exph.play();
                        pexph = true;
                        /*shake();*/
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
                    int i = 6;
                    do {
                        if (loadet[i]) {
                            crntt = i;
                        }
                        --i;
                    } while (i >= 0);
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
            if (plcnt != 0 && crntt != -1 && xtgraphics.fase != -4 && xtgraphics.fase != 1 && xtgraphics.fase != 2) {
                if (plcnt >= 100) {
                    mtrak[crntt].stop();
                }
                if (xtgraphics.fase == 3 && plcnt >= 100) {
                    crntt += -1;
                }
                plcnt = 0;
            }
            if (xtgraphics.fase == -8 && xtgraphics.cnty < 351 && !nomusic) {
                if (!pint) {
                    into.loop();
                    pint = true;
                }
            } else {
                if (pint) {
                    into.stop();
                    pint = false;
                }
                if (xtgraphics.cnty == 352) {
                    hit.play();
                    xtgraphics.cnty = 353;
                }
            }
            if ((xtgraphics.fase == -5 || xtgraphics.fase == 7) && !nomusic) {
                if (!pman) {
                    mano.loop();
                    pman = true;
                }
            } else if (pman) {
                mano.stop();
                pman = false;
            }
            if (xtgraphics.fase == -1 && !nomusic) {
                if (!pmis) {
                    miso.loop();
                    pmis = true;
                }
            } else if (pmis) {
                miso.stop();
                pmis = false;
            }
            if ((xtgraphics.fase == 0 || xtgraphics.fase == 5 || xtgraphics.fase == 6) && !nomusic) {
                if (!psel) {
                    selo.loop();
                    psel = true;
                }
            } else if (psel) {
                selo.stop();
                psel = false;
            }
            if (xtgraphics.fase == 7) {
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
            final JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("loaded=false;");
        } catch (final Exception var2) {
            ;
        }
    }

    public AudioClip loadsnd(final String string) {
        final AudioClip audioclip = getAudioClip(getCodeBase(), string);
        if (!sosun) {
            audioclip.play();
            Thread.yield();
            audioclip.stop();
        }
        return audioclip;
    }

    public String getstring(final String string, final String string2, final int i) {
        int j = 0;
        String string3 = "";
        for (int k = string.length() + 1; k < string2.length(); ++k) {
            final String string4 = "" + string2.charAt(k);
            if (string4.equals(",") || string4.equals(")")) {
                ++j;
                ++k;
            }
            if (j == i) {
                string3 = string3 + string2.charAt(k);
            }
        }
        return string3;
    }

    public int getint(final String string, final String string2, final int i) {
        int j = 0;
        String string3 = "";
        for (int k = string.length() + 1; k < string2.length(); ++k) {
            final String string4 = "" + string2.charAt(k);
            if (string4.equals(",") || string4.equals(")")) {
                ++j;
                ++k;
            }
            if (j == i) {
                string3 = string3 + string2.charAt(k);
            }
        }
        return Integer.valueOf(string3).intValue();
    }

    @Override
    public void paint(final Graphics graphics) {
        graphics.drawImage(offImage, 0, 0, this);
    }

    public void savegame(final ContO[] contos, final xtGraphics xtgraphics, final int i) {
        try {
            final JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("SetCookie(\'radxv\',\'" + xtgraphics.level + "\')");
            int j;
            for (j = i; j < i + 13; ++j) {
                jsobject.eval("SetCookie(\'radnhits" + j + "\',\'" + contos[j].nhits + "\')");
            }
            j = 0;
            do {
                jsobject.eval("SetCookie(\'raddest" + j + "\',\'" + xtgraphics.dest[j] + "\')");
                ++j;
            } while (j < 5);
            xtgraphics.sgame = 1;
        } catch (final Exception var6) {
            ;
        }
    }

    @Override
    public void destroy() {
        if (gamer != null) {
            gamer.stop();
        }
        gamer = null;
    }

    public void loadrots(final ContO[] contos, final boolean flag) {
        for (int i = 0; i < maxco; ++i) {
            contos[i].loadrots(flag);
        }
    }

    public Image returnImg(final String string) {
        Image image = null;
        try {
            image = ImageIO.read(new URL(getCodeBase(), string).openStream());
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void loadbase(final ContO[] contos, final Medium medium) {
        try {
            final URL url = new URL(getCodeBase(), modelsDir);
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            final ZipInputStream zipinputstream = new ZipInputStream(datainputstream);
            ZipEntry zipentry = zipinputstream.getNextEntry();
            final Object object = null;
            for (int i = 0; zipentry != null; zipentry = zipinputstream.getNextEntry()) {
                int j = (int) zipentry.getSize();
                final byte[] bytes = new byte[j];
                int k;
                for (int l = 0; j > 0; j -= k) {
                    k = zipinputstream.read(bytes, l, j);
                    l += k;
                }
                contos[i] = new ContO(bytes, medium, 0, 0, 0);
                modelNames[i] = zipentry.getName();
                ++i;
            }
            zipinputstream.close();
            datainputstream.close();
        } catch (final Exception var12) {
            System.out.println("Error Reading Models: " + var12);
        }
        System.gc();
    }

    @Override
    public void update(final Graphics graphics) {
        paint(graphics);
    }

    public void loadmovers(final int[] ints, final int[] ints2, final ContO[] conto, final Craft[] craft, final Tank[] tank, final userCraft usercraft, final xtGraphics xtgraphics) {
        for (int i = 1; i < maxmo; ++i) {
            conto[ints[i]].out = true;
        }
        maxmo = 1;
        xtgraphics.nb = 0;
        xtgraphics.mcomp = false;
        try {
            final URL url = new URL(getCodeBase(), "levels/" + xtgraphics.level + ".txt");
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            String string;
            while ((string = datainputstream.readLine()) != null) {
                final String string2 = "" + string.trim();
                if (string2.startsWith("craft")) {
                    ints[maxmo] = getint("craft", string2, 0);
                    ints2[maxmo] = 0;
                }
                if (string2.startsWith("tank")) {
                    ints[maxmo] = getint("tank", string2, 0);
                    ints2[maxmo] = 1;
                }
                if (string2.startsWith("name")) {
                    xtgraphics.mname[maxmo - 1] = getstring("name", string2, 0);
                    xtgraphics.cnte[maxmo - 1] = 0;
                }
                if (string2.startsWith("l")) {
                    conto[ints[maxmo]].x = getint("l", string2, 0) * 10;
                    conto[ints[maxmo]].y = getint("l", string2, 1) * 10;
                    conto[ints[maxmo]].z = getint("l", string2, 2) * 10;
                    conto[ints[maxmo]].out = false;
                    conto[ints[maxmo]].reset();
                }
                if (string2.startsWith("prompt")) {
                    if (getstring("prompt", string2, 0).equals("tank")) {
                        xtgraphics.tnk[xtgraphics.nb] = true;
                    } else {
                        xtgraphics.tnk[xtgraphics.nb] = false;
                    }
                    xtgraphics.ob[xtgraphics.nb] = getint("prompt", string2, 1);
                    xtgraphics.nam[xtgraphics.nb] = getstring("prompt", string2, 2).replace('|', ',');
                    ++xtgraphics.nb;
                }
                if (string2.startsWith("stat")) {
                    if (ints2[maxmo] == 0) {
                        craft[maxmo].reset(getint("stat", string2, 0), getint("stat", string2, 1), getint("stat", string2, 2), getint("stat", string2, 3), getint("stat", string2, 4), getint("stat", string2, 5));
                    } else {
                        tank[maxmo].reset(getint("stat", string2, 0), getint("stat", string2, 1));
                    }
                    ++maxmo;
                }
            }
            datainputstream.close();
        } catch (final Exception var12) {
            ;
        }
        System.gc();
    }

    public void set0() {
        try {
            final JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("SetCookie(\'radxv\',\'0\')");
        } catch (final Exception var2) {
            ;
        }
    }

    @Override
    public boolean keyUp(final Event event, final int i) {
        if (i == 49 && view == 1) {
            view = 0;
        }
        if (i == 50 && view == 2) {
            view = 0;
        }
        if (i == 51 && view == 3) {
            view = 0;
        }
        if (i == 52 && view == 4) {
            view = 0;
        }
        if (i == 53 && view == 5) {
            view = 0;
        }
        if (i == 114 || i == 82) {
            u.radar = false;
        }
        if (i == 43 || i == 61) {
            u.plus = false;
        }
        if (i == 45 || i == 8) {
            u.mins = false;
        }
        if (i == 10 || i == 27) {
            enterd = false;
        }
        if (i == 9) {
            tab = false;
        }
        if (i == 32) {
            u.fire = false;
        }
        if (i == 1006) {
            u.left = false;
        }
        if (i == 1007) {
            u.right = false;
        }
        if (i == 1005) {
            u.down = false;
        }
        if (i == 1004) {
            u.up = false;
        }
        return false;
    }

    @Override
    public void start() {
        if (gamer == null) {
            gamer = new Thread(this);
        }
        gamer.start();
    }

    public void downloadall(final xtGraphics xtgraphics) {
        xtgraphics.radar = returnImg("data/images/radar.gif");
        lstat("Loading Images...", 1);
        xtgraphics.stube = returnImg("data/images/stube.gif");
        lstat("Loading Images...", 2);
        xtgraphics.sback = returnImg("data/images/select.jpg");
        lstat("Loading Images...", 18);
        xtgraphics.destr = returnImg("data/images/destroyed.gif");
        lstat("Loading Images...", 2);
        xtgraphics.saveit(returnImg("data/images/failed.jpg"), xtgraphics.bpix);
        lstat("Loading Images...", 31);
        xtgraphics.saveit(returnImg("data/images/mission.jpg"), xtgraphics.mpix);
        lstat("Loading Images...", 22);
        xtgraphics.saveit(returnImg("data/images/over.jpg"), xtgraphics.opix);
        lstat("Loading Images...", 21);
        /*var1.saveit(returnImg("data/images/paused.jpg"), var1.ppix);*/
        lstat("Loading Images...", 10);
        xtgraphics.lay = returnImg("data/images/layout.gif");
        lstat("Loading Images...", 1);
        xtgraphics.complete = returnImg("data/images/comp.gif");
        lstat("Loading Images...", 2);
        xtgraphics.main = returnImg("data/images/main.gif");
        lstat("Loading Images...", 32);
        xtgraphics.rad = returnImg("data/images/radicalplay.gif");
        lstat("Loading Images...", 2);
        int i = 0;
        do {
            xtgraphics.as[i] = returnImg("data/images/a" + i + ".gif");
            lstat("Loading Images...", 1);
            ++i;
        } while (i < 5);
        xtgraphics.inst1 = returnImg("data/images/inst1.gif");
        lstat("Loading Images...", 10);
        xtgraphics.inst2 = returnImg("data/images/inst2.gif");
        lstat("Loading Images...", 11);
        xtgraphics.inst3 = returnImg("data/images/inst3.gif");
        lstat("Loading Images...", 4);
        /*var1.text = returnImg("data/images/text.gif");*/
        lstat("Loading Images...", 6);
        xtgraphics.mars = returnImg("data/images/mars.jpg");
        lstat("Loading Images...", 15);
        into = loadsnd("data/music/intro.au");
        lstat("Loading Music...", 24);
        miso = loadsnd("data/music/mission.au");
        lstat("Loading Music...", 29);
        selo = loadsnd("data/music/select.au");
        lstat("Loading Music...", 52);
        mano = loadsnd("data/music/main.au");
        lstat("Loading Music...", 50);
        upl = loadsnd("data/sounds/" + sndfrm + "/up.au");
        lstat("Loading Sound Effects...", 11);
        hitl = loadsnd("data/sounds/" + sndfrm + "/hitl.au");
        lstat("Loading Sound Effects...", 7);
        downl = loadsnd("data/sounds/" + sndfrm + "/down.au");
        lstat("Loading Sound Effects...", 10);
        low = loadsnd("data/sounds/" + sndfrm + "/low.au");
        lstat("Loading Sound Effects...", 11);
        med = loadsnd("data/sounds/" + sndfrm + "/med.au");
        lstat("Loading Sound Effects...", 6);
        ljump = loadsnd("data/sounds/" + sndfrm + "/jump.au");
        lstat("Loading Sound Effects...", 25);
        grnd = loadsnd("data/sounds/" + sndfrm + "/grnd.au");
        lstat("Loading Sound Effects...", 5);
        exp = loadsnd("data/sounds/" + sndfrm + "/exp.au");
        lstat("Loading Sound Effects...", 10);
        exph = loadsnd("data/sounds/" + sndfrm + "/exph.au");
        lstat("Loading Sound Effects...", 12);
        hit = loadsnd("data/sounds/" + sndfrm + "/hit.au");
        lstat("Loading Sound Effects...", 25);
        i = 0;
        do {
            las[i] = loadsnd("data/sounds/" + sndfrm + "/l" + i + ".au");
            lstat("Loading Sound Effects...", 9);
            ++i;
        } while (i < 5);
        charged = loadsnd("data/sounds/" + sndfrm + "/charged.au");
        lstat("Loading Sound Effects...", 12);
    }

    @Override
    public boolean mouseDown(final Event event, final int i, final int j) {
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

    /*public void shake() {
    	try {
    		JSObject var1 = JSObject.getWindow(this);
    		var1.eval("shake()");
    	} catch (Exception var2) {
    		;
    	}

    }*/
    public void setmover(final int[] ints, final ContO[] contos, final userCraft usercraft, final xtGraphics xtgraphics) {
        int i = 0;
        do {
            contos[i].out = true;
            contos[i].wire = false;
            ++i;
        } while (i < 5);
        ints[0] = xtgraphics.selected;
        contos[ints[0]].x = 3000;
        contos[ints[0]].y = 250;
        contos[ints[0]].z = -500;
        contos[ints[0]].out = false;
        usercraft.reset(ints[0]);
        contos[ints[0]].reset();
        contos[ints[0]].xz = 360;
        u.jump = 0;
        xtgraphics.creset();
    }

    public void loadobjects(final ContO[] contos, final ContO[] contos2, final Medium medium, final String string) {
        try {
            final URL url = new URL(getCodeBase(), "siters/" + string + ".txt");
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            boolean flag = false;
            String string2;
            while ((string2 = datainputstream.readLine()) != null) {
                final String string3 = "" + string2.trim();
                if (string3.startsWith("l")) {
                    final String string4 = getstring("l", string3, 0);
                    int i;
                    int j;
                    int k;
                    if (!flag) {
                        i = getint("l", string3, 1) * 10;
                        j = getint("l", string3, 2) * 10;
                        k = getint("l", string3, 3) * 10;
                    } else {
                        i = getint("l", string3, 1);
                        j = getint("l", string3, 2);
                        k = getint("l", string3, 3);
                    }
                    int l = 0;
                    do {
                        if (modelNames[l].equals(string4 + ".rad")) {
                            contos[maxco] = new ContO(medium, contos2[l], i, j, k);
                            ++maxco;
                        }
                        ++l;
                    } while (l < 53);
                }
                if (string3.startsWith("xy")) {
                    contos[maxco - 1].xy = getint("xy", string3, 0);
                }
                if (string3.startsWith("xz")) {
                    contos[maxco - 1].xz = getint("xz", string3, 0);
                }
                if (string3.startsWith("zy")) {
                    contos[maxco - 1].zy = getint("zy", string3, 0);
                }
                if (string3.startsWith("xmult")) {
                    if (flag) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
            datainputstream.close();
        } catch (final Exception var15) {
            ;
        }
        System.gc();
    }

    @Override
    public void run() {
        gamer.setPriority(10);
        final Medium m = new Medium();
        final xtGraphics xtgraphics = new xtGraphics(m, rd);
        byte b = 5;
        final String javaV = System.getProperty("java.version");
        final String appC = "" + getAppletContext();
        if (appC.startsWith("sun.")) {
            if (javaV.startsWith("1.3")) {
                xtgraphics.goodsun = true;
            } else if (javaV.startsWith("1.4")) {
                sosun = true;
            } else {
                sosun = true;
                sndfrm = "newsun";
            }
            b = 15;
        }
        /*testlocation(m, var3);*/
        lstat("Preparing for loading...", 0);
        final ContO[] conto = new ContO[53];
        final ContO[] conto1 = new ContO[3000];
        final userCraft usercraft = new userCraft(m);
        /*
         * set up 20 tanks
         */
        final Tank[] tank = new Tank[20];
        for (int i = 0; i < 20; i++) {
            tank[i] = new Tank(m);
        }
        int i = 0;
        /*
         * set up 20 crafts
         */
        final Craft[] craft = new Craft[20];
        for (int j = 0; j < 20; j++) {
            craft[j] = new Craft(m);
        }
        loadbase(conto, m);
        lstat("Loading 3D Models...", 17);
        loadobjects(conto1, conto, m, "aces");
        lstat("Loading 3D Models...", 1);
        i = maxco;
        loadobjects(conto1, conto, m, "base");
        lstat("Loading 3D Models...", 2);
        loadobjects(conto1, conto, m, "smap");
        lstat("Loading 3D Models...", 44);
        loadobjects(conto1, conto, m, "clmap" + (int) (Math.random() * 5.0D) + "");
        lstat("Loading 3D Models...", 1);
        loadrots(conto1, true);
        int j = 0;
        final int[] ints = new int[600];
        for (int k = 0; k < maxco; ++k) {
            if (conto1[k].colides) {
                ints[j] = k;
                ++j;
            }
        }
        final int[] ints2 = new int[20];
        final int[] ints3 = new int[20];
        int l = 0;
        do {
            loadet[l] = false;
            ++l;
        } while (l < 7);
        downloadall(xtgraphics);
        Date date = new Date();
        long lo = 0L;
        long lo2 = date.getTime();
        float f = 30.0F;
        float f2 = 35.0F;
        boolean flag = false;
        int i1 = 0;
        int j1 = 0;
        boolean flag2 = true;
        maxmo = 0;
        while (true) {
            date = new Date();
            final long lo3 = date.getTime();
            if (!mon) {
                int k1;
                int[] ints4;
                int l2;
                int i2;
                int j2;
                int[] ints5;
                if (!flag2) {
                    m.d(rd);
                    k1 = 0;
                    ints4 = new int[100];
                    for (l2 = 0; l2 < maxco; ++l2) {
                        if (conto1[l2].dist != 0) {
                            ints4[k1] = l2;
                            ++k1;
                        } else {
                            conto1[l2].d(rd);
                        }
                    }
                    ints5 = new int[k1];
                    for (i2 = 0; i2 < k1; ++i2) {
                        ints5[i2] = 0;
                        for (j2 = 0; j2 < k1; ++j2) {
                            if (conto1[ints4[i2]].dist != conto1[ints4[j2]].dist) {
                                if (conto1[ints4[i2]].dist < conto1[ints4[j2]].dist) {
                                    ++ints5[i2];
                                }
                            } else if (j2 > i2) {
                                ++ints5[i2];
                            }
                        }
                    }
                    int k2;
                    for (i2 = 0; i2 < k1; ++i2) {
                        for (j2 = 0; j2 < k1; ++j2) {
                            if (ints5[j2] == i2) {
                                if (conto1[ints4[j2]].fire) {
                                    if (ints4[j2] == ints2[0]) {
                                        usercraft.dl(rd);
                                    } else {
                                        for (k2 = 1; k2 < maxmo; ++k2) {
                                            if (ints4[j2] == ints2[k2]) {
                                                if (ints3[k2] == 0) {
                                                    craft[k2].dl(rd);
                                                }
                                                if (ints3[k2] == 1) {
                                                    tank[k2].dl(rd);
                                                }
                                            }
                                        }
                                    }
                                }
                                conto1[ints4[j2]].d(rd);
                            }
                        }
                    }
                    if (xtgraphics.level < 6) {
                        for (i2 = 0; i2 < j; ++i2) {
                            for (j2 = 0; j2 < maxmo; ++j2) {
                                if (ints2[j2] != ints[i2]) {
                                    conto1[ints[i2]].tryexp(conto1[ints2[j2]]);
                                    if (conto1[ints2[j2]].fire) {
                                        if (j2 == 0) {
                                            usercraft.lasercolid(conto1[ints[i2]]);
                                        } else {
                                            if (ints3[j2] == 0) {
                                                craft[j2].lasercolid(conto1[ints[i2]]);
                                            }
                                            if (ints3[j2] == 1) {
                                                tank[j2].lasercolid(conto1[ints[i2]]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (i2 = j - 1; i2 >= 0; --i2) {
                            for (j2 = 0; j2 < maxmo; ++j2) {
                                if (ints2[j2] != ints[i2]) {
                                    if (xtgraphics.level != 15 || j2 != 1) {
                                        conto1[ints[i2]].tryexp(conto1[ints2[j2]]);
                                    }
                                    if (conto1[ints2[j2]].fire) {
                                        if (j2 == 0) {
                                            usercraft.lasercolid(conto1[ints[i2]]);
                                        } else {
                                            if (ints3[j2] == 0) {
                                                craft[j2].lasercolid(conto1[ints[i2]]);
                                            }
                                            if (ints3[j2] == 1) {
                                                tank[j2].lasercolid(conto1[ints[i2]]);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for (i2 = 1; i2 < maxmo; ++i2) {
                        if (ints3[i2] == 0) {
                            craft[i2].dosmokes(rd, conto1[ints2[i2]]);
                            craft[i2].preform(conto1[ints2[i2]], conto1, ints, j, ints2[0], i);
                            if (conto1[ints2[i2]].exp) {
                                if (!nosound) {
                                    exp.play();
                                }
                                ints3[i2] = -1;
                            }
                            if (conto1[ints2[i2]].hit && !nosound && frags == 0) {
                                hitl.play();
                                if (sosun) {
                                    frags = 3;
                                }
                            }
                        }
                        if (ints3[i2] == 1) {
                            tank[i2].dosmokes(rd, conto1[ints2[i2]]);
                            tank[i2].preform(conto1[ints2[i2]], conto1, ints2[0], i);
                            if (conto1[ints2[i2]].exp) {
                                if (!nosound) {
                                    exp.play();
                                }
                                ints3[i2] = -1;
                            }
                            if (conto1[ints2[i2]].hit && !nosound && frags == 0) {
                                hitl.play();
                                if (sosun) {
                                    frags = 3;
                                }
                            }
                        }
                    }
                    usercraft.dosmokes(rd, conto1[ints2[0]]);
                    usercraft.preform(u, conto1[ints2[0]], conto1, ints2, maxmo);
                    i2 = 0;
                    if (tab) {
                        i2 = xtgraphics.cl;
                    } else if (view != 4 && view != 5) {
                        xtgraphics.dtrakers(rd, ints3, ints2, maxmo, conto1, usercraft, u);
                    }
                    if (view == 0) {
                        m.behinde(conto1[ints2[i2]]);
                    }
                    if (view == 1) {
                        m.right(conto1[ints2[i2]]);
                    }
                    if (view == 2) {
                        m.infront(conto1[ints2[i2]]);
                    }
                    if (view == 3) {
                        m.left(conto1[ints2[i2]]);
                    }
                    if (view == 4) {
                        m.around(conto1[ints2[i2]], 800);
                    }
                    if (view == 5) {
                        m.watch(conto1[ints2[i2]]);
                    } else if (m.td) {
                        m.td = false;
                    }
                    if (conto1[ints2[0]].exp) {
                        j2 = 0;
                        for (k2 = 0; k2 < conto1[ints2[0]].npl; ++k2) {
                            if (conto1[ints2[0]].p[k2].exp == 7) {
                                ++j2;
                            }
                        }
                        if (j2 == conto1[ints2[0]].npl) {
                            flag2 = true;
                            xtgraphics.dest[ints2[0]] = true;
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
                                flag2 = true;
                                u.space = false;
                            }
                        } else if (u.space) {
                            flag2 = true;
                            xtgraphics.drawpimg(offImage);
                            xtgraphics.fase = 3;
                            u.space = false;
                            xtgraphics.select = 0;
                        }
                        j2 = 0;
                        for (k2 = i; k2 < i + 13; ++k2) {
                            if (conto1[k2].exp) {
                                ++j2;
                            }
                        }
                        if (j2 == 13) {
                            flag2 = true;
                            xtgraphics.drawovimg(offImage);
                            xtgraphics.fase = 2;
                        }
                    }
                } else {
                    if (xtgraphics.fase == -4) {
                        m.d(rd);
                        k1 = 0;
                        ints4 = new int[100];
                        for (l2 = 0; l2 < maxco; ++l2) {
                            if (conto1[l2].dist != 0) {
                                ints4[k1] = l2;
                                ++k1;
                            } else {
                                conto1[l2].d(rd);
                            }
                        }
                        ints5 = new int[k1];
                        for (i2 = 0; i2 < k1; ++i2) {
                            ints5[i2] = 0;
                            for (j2 = 0; j2 < k1; ++j2) {
                                if (conto1[ints4[i2]].dist != conto1[ints4[j2]].dist) {
                                    if (conto1[ints4[i2]].dist < conto1[ints4[j2]].dist) {
                                        ++ints5[i2];
                                    }
                                } else if (j2 > i2) {
                                    ++ints5[i2];
                                }
                            }
                        }
                        for (i2 = 0; i2 < k1; ++i2) {
                            for (j2 = 0; j2 < k1; ++j2) {
                                if (ints5[j2] == i2) {
                                    conto1[ints4[j2]].d(rd);
                                }
                            }
                        }
                        m.around(conto1[i + 4], 6000);
                        if (u.space) {
                            xtgraphics.drawl(rd, offImage);
                        }
                    }
                    xtgraphics.denter(rd, i, conto1, usercraft, u);
                    if (xtgraphics.fase == -5 && u.space) {
                        if (xtgraphics.select == 0) {
                            loadrots(conto1, false);
                            for (k1 = i; k1 < i + 13; ++k1) {
                                conto1[k1].out = false;
                            }
                            xtgraphics.reset();
                            xtgraphics.fase = -4;
                        }
                        if (xtgraphics.select == 1 && xtgraphics.sgame == 1) {
                            loadrots(conto1, false);
                            xtgraphics.reset();
                            loadsaved(conto1, xtgraphics, i);
                            xtgraphics.fase = -4;
                        }
                        if (xtgraphics.select == 4 && maxmo != 0) {
                            moner = "Exiting game...";
                            mon = true;
                        }
                        u.space = false;
                    }
                    if (xtgraphics.fase == -33) {
                        if (xtgraphics.frst && xtgraphics.select == 0) {
                            savegame(conto1, xtgraphics, i);
                        } else if (!xtgraphics.frst) {
                            xtgraphics.frst = true;
                        }
                        if (j1 != 7) {
                            if (xtgraphics.goodsun) {
                                nounif = true;
                            }
                            mtrak[j1] = getAudioClip(getCodeBase(), "data/music/" + j1 + ".au");
                            loadet[j1] = true;
                            ++j1;
                        } else if (xtgraphics.goodsun) {
                            xtgraphics.goodsun = false;
                        }
                        loadmovers(ints2, ints3, conto1, craft, tank, usercraft, xtgraphics);
                        nounif = false;
                        xtgraphics.fase = -2;
                    }
                    if (xtgraphics.fase == -3) {
                        xtgraphics.fase = -33;
                    }
                    if (xtgraphics.fase == 0 && u.space) {
                        if (!xtgraphics.dest[xtgraphics.selected]) {
                            setmover(ints2, conto1, usercraft, xtgraphics);
                            flag2 = false;
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
                            flag2 = false;
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
                final URL url;
                if (moner.equals("Exiting game...")) {
                    repaint();
                    System.gc();
                    /*try {
                    	var42 = new URL(getCodeBase(), "web/exit.html");
                    	getAppletContext().showDocument(var42);
                    } catch (Exception var37) {
                    	;
                    }*/
                    System.gc();
                    gamer.stop();
                }
                if (moner.equals("One moment...")) {
                    repaint();
                    System.gc();
                    unloadit();
                    /*try {
                    	var42 = new URL(getCodeBase(), "http://www.radicalplay.com/aces/winner/index.html");
                    	getAppletContext().showDocument(var42);
                    } catch (Exception var36) {
                    	;
                    }*/
                    System.gc();
                    gamer.stop();
                }
            }
            repaint();
            if (!mon) {
                playsounds(usercraft, conto1[ints2[0]], flag2, xtgraphics);
            }
            date = new Date();
            final long lo4 = date.getTime();
            if (!flag2) {
                if (!flag) {
                    f = f2;
                    flag = true;
                    i1 = 0;
                }
                if (i1 == 10) {
                    if (lo4 - lo2 < 560L) {
                        f = (float) (f + 0.5D);
                    } else {
                        f = (float) (f - 0.5D);
                        if (f < 5.0F) {
                            f = 5.0F;
                        }
                    }
                    lo2 = lo4;
                    i1 = 0;
                } else {
                    ++i1;
                }
            } else {
                if (flag) {
                    f2 = f;
                    flag = false;
                    i1 = 0;
                }
                if (i1 == 10) {
                    if (lo4 - lo2 < 400L) {
                        f = (float) (f + 3.5D);
                    } else {
                        f = (float) (f - 3.5D);
                        if (f < 5.0F) {
                            f = 5.0F;
                        }
                    }
                    lo2 = lo4;
                    i1 = 0;
                } else {
                    ++i1;
                }
            }
            lo = Math.round(f) - (lo4 - lo3);
            if (lo < b) {
                lo = b;
            }
            try {
                Thread.sleep(lo);
            } catch (final InterruptedException var35) {
                ;
            }
        }
    }

    public void lstat(final String string, final int i) {
        dnload += i;
        rd.setColor(new Color(223, 223, 223));
        rd.fillRect(0, 0, 500, 360);
        rd.setColor(new Color(174, 185, 198));
        rd.drawRect(150, 200, 200, 5);
        rd.fillRect(150, 200, 24 + (int) (dnload / 594.0F * 176.0F), 5);
        rd.setColor(new Color(151, 166, 183));
        rd.drawString(string, 290, 220);
        rd.drawString("Remaining: " + (594 - dnload) + " KB", 202, 250);
        rd.setColor(new Color(0, 0, 0));
        rd.drawString("Loading " + (int) ((24 + (int) (dnload / 594.0F * 176.0F)) / 200.0F * 100.0F) + "%", 103, 194);
        repaint();
    }

    @Override
    public void init() {
        offImage = createImage(500, 360);
        if (offImage != null) {
            rd = offImage.getGraphics();
        }
        rd.setFont(new Font("SansSerif", 1, 11));
    }

    public void getslevel(final xtGraphics xtgraphics) {
        try {
            final JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("s=GetCookie(\'radxv\')");
            final String string = String.valueOf(String.valueOf(jsobject.getMember("s")));
            if (string.equals("0")) {
                xtgraphics.sgame = 0;
            } else {
                xtgraphics.sgame = 1;
                xtgraphics.select = 1;
            }
        } catch (final Exception var4) {
            ;
        }
    }

    public void loadsaved(final ContO[] contos, final xtGraphics xtgraphics, final int i) {
        try {
            final JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("s=GetCookie(\'radxv\')");
            final String string = String.valueOf(String.valueOf(jsobject.getMember("s")));
            xtgraphics.level = Integer.valueOf(string).intValue();
            int j;
            String string2;
            for (j = i; j < i + 13; ++j) {
                jsobject.eval("ss=GetCookie(\'radnhits" + j + "\')");
                string2 = String.valueOf(String.valueOf(jsobject.getMember("ss")));
                contos[j].nhits = Integer.valueOf(string2).intValue();
                if (contos[j].nhits >= contos[j].maxhits) {
                    contos[j].exp = true;
                    contos[j].out = true;
                } else {
                    contos[j].out = false;
                }
            }
            j = 0;
            do {
                jsobject.eval("sss=GetCookie(\'raddest" + j + "\')");
                string2 = String.valueOf(String.valueOf(jsobject.getMember("sss")));
                if (string2.equals("false")) {
                    xtgraphics.dest[j] = false;
                } else {
                    xtgraphics.dest[j] = true;
                }
                ++j;
            } while (j < 5);
        } catch (final Exception var8) {
            ;
        }
    }

    @Override
    public boolean keyDown(final Event event, final int i) {
        if (i == 49) {
            view = 1;
        }
        if (i == 50) {
            view = 2;
        }
        if (i == 51) {
            view = 3;
        }
        if (i == 52) {
            view = 4;
        }
        if (i == 53) {
            view = 5;
        }
        if (i == 109 || i == 77) {
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
        if (i == 116 || i == 84) {
            if (plcnt >= 100) {
                mtrak[crntt].stop();
            }
            plcnt = 95;
        }
        if (i == 115 || i == 83) {
            if (nosound) {
                nosound = false;
            } else {
                nosound = true;
            }
        }
        if (i == 114 || i == 82) {
            u.radar = true;
        }
        if (i == 9) {
            tab = true;
        }
        if (i == 43 || i == 61) {
            u.plus = true;
        }
        if (i == 45 || i == 8) {
            u.mins = true;
        }
        if ((i == 106 || i == 74) && u.jump == 0) {
            u.jump = 1;
            if (!u.jade) {
                u.jade = true;
            }
        }
        if ((i == 10 || i == 27) && !enterd) {
            u.space = true;
            enterd = true;
        }
        if (i == 32) {
            u.fire = true;
        }
        if (i == 1006) {
            u.left = true;
        }
        if (i == 1007) {
            u.right = true;
        }
        if (i == 1005) {
            u.down = true;
        }
        if (i == 1004) {
            u.up = true;
        }
        return false;
    }
}
