import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class F51 extends JComponent implements KeyListener, MouseListener, FocusListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1399200686375699720L;

    public static final String modelsDir = "data/models.radq";

    public static final String imagesDir = "data/images/";

    Graphics2D rd;

    BufferedImage offImage;

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

    SoundClip upl;

    SoundClip low;

    SoundClip med;

    SoundClip downl;

    SoundClip ljump;

    SoundClip grnd;

    SoundClip exp;

    SoundClip exph;

    SoundClip hit;

    SoundClip hitl;

    SoundClip charged;

    SoundClip into;

    SoundClip miso;

    SoundClip mano;

    SoundClip selo;

    SoundClip[] las = new SoundClip[5];

    SoundClip[] mtrak = new SoundClip[7];

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

    private long lastTime;
    private ContO[] levelContos;
    private Craft[] crafts;
    private Tank[] tanks;
    private userCraft usercraft;
    private xtGraphics xt;
    private Medium m;

    // runner fields
    private boolean __flag2;

    private int __j1;

    private int[] __ints3s;

    private int[] __ints2s;

    private int[] __ints;

    private int __j;

    private int __i;

    public void playsounds(userCraft usercraft, ContO conto, boolean flag, xtGraphics xtgraphics) {
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

    public SoundClip makeSound(String file) throws IOException {
        return new SoundClip(new File(file));
        
    }

    public String getstring(String string, String string2, int i) {
        int j = 0;
        String string3 = "";
        for (int k = string.length() + 1; k < string2.length(); ++k) {
            String string4 = "" + string2.charAt(k);
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

    public int getint(String string, String string2, int i) {
        int j = 0;
        String string3 = "";
        for (int k = string.length() + 1; k < string2.length(); ++k) {
            String string4 = "" + string2.charAt(k);
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

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(offImage, 0, 0, this);
    }

    public void savegame(ContO[] contos, xtGraphics xtgraphics, int i) {
        try {
            // TODO
            /*JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("SetCookie(\'radxv\',\'" + xtgraphics.level + "\')");
            int j;
            for (j = i; j < i + 13; ++j) {
                jsobject.eval("SetCookie(\'radnhits" + j + "\',\'" + contos[j].nhits + "\')");
            }
            j = 0;
            do {
                jsobject.eval("SetCookie(\'raddest" + j + "\',\'" + xtgraphics.dest[j] + "\')");
                ++j;
            } while (j < 5);*/
            xtgraphics.sgame = 1;
        } catch (Exception var6) {
            ;
        }
    }

    public void loadrots(ContO[] contos, boolean flag) {
        for (int i = 0; i < maxco; ++i) {
            contos[i].loadrots(flag);
        }
    }

    public Image returnImg(String string) {
        Image image = null;
        try {
            image = ImageIO.read(new FileInputStream(string));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void loadbase(ContO[] contos, Medium medium) {
        try {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(modelsDir));
            ZipInputStream zipinputstream = new ZipInputStream(datainputstream);
            ZipEntry zipentry = zipinputstream.getNextEntry();
            for (int i = 0; zipentry != null; zipentry = zipinputstream.getNextEntry()) {
                int j = (int) zipentry.getSize();
                byte[] bytes = new byte[j];
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
        } catch (Exception var12) {
            System.out.println("Error Reading Models: " + var12);
        }
        System.gc();
    }

    public void loadmovers(int[] ints, int[] ints2, ContO[] conto, Craft[] craft, Tank[] tank, userCraft usercraft, xtGraphics xtgraphics) {
        for (int i = 1; i < maxmo; ++i) {
            conto[ints[i]].out = true;
        }
        maxmo = 1;
        xtgraphics.nb = 0;
        xtgraphics.mcomp = false;
        try {
            BufferedReader stageReader = new BufferedReader(new FileReader("levels/" + xtgraphics.level + ".txt"));
            String string;
            while ((string = stageReader.readLine()) != null) {
                String string2 = "" + string.trim();
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
            stageReader.close();
        } catch (Exception var12) {
            ;
        }
        System.gc();
    }

    public void set0() {
        // TODO
        try {
            /*JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("SetCookie(\'radxv\',\'0\')");*/
        } catch (Exception var2) {
            ;
        }
    }

    public void downloadall(xtGraphics xtgraphics) throws IOException {
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
        into = makeSound("data/music/intro.au");
        lstat("Loading Music...", 24);
        miso = makeSound("data/music/mission.au");
        lstat("Loading Music...", 29);
        selo = makeSound("data/music/select.au");
        lstat("Loading Music...", 52);
        mano = makeSound("data/music/main.au");
        lstat("Loading Music...", 50);
        upl = makeSound("data/sounds/" + sndfrm + "/up.au");
        lstat("Loading Sound Effects...", 11);
        hitl = makeSound("data/sounds/" + sndfrm + "/hitl.au");
        lstat("Loading Sound Effects...", 7);
        downl = makeSound("data/sounds/" + sndfrm + "/down.au");
        lstat("Loading Sound Effects...", 10);
        low = makeSound("data/sounds/" + sndfrm + "/low.au");
        lstat("Loading Sound Effects...", 11);
        med = makeSound("data/sounds/" + sndfrm + "/med.au");
        lstat("Loading Sound Effects...", 6);
        ljump = makeSound("data/sounds/" + sndfrm + "/jump.au");
        lstat("Loading Sound Effects...", 25);
        grnd = makeSound("data/sounds/" + sndfrm + "/grnd.au");
        lstat("Loading Sound Effects...", 5);
        exp = makeSound("data/sounds/" + sndfrm + "/exp.au");
        lstat("Loading Sound Effects...", 10);
        exph = makeSound("data/sounds/" + sndfrm + "/exph.au");
        lstat("Loading Sound Effects...", 12);
        hit = makeSound("data/sounds/" + sndfrm + "/hit.au");
        lstat("Loading Sound Effects...", 25);
        i = 0;
        do {
            las[i] = makeSound("data/sounds/" + sndfrm + "/l" + i + ".au");
            lstat("Loading Sound Effects...", 9);
            ++i;
        } while (i < 5);
        charged = makeSound("data/sounds/" + sndfrm + "/charged.au");
        lstat("Loading Sound Effects...", 12);
    }

    /*public void shake() {
		try {
			JSObject var1 = JSObject.getWindow(this);
			var1.eval("shake()");
		} catch (Exception var2) {
			;
		}

	}*/
    public void setmover(int[] ints, ContO[] contos, userCraft usercraft, xtGraphics xtgraphics) {
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

    public void loadobjects(ContO[] contos, ContO[] contos2, Medium medium, String string) {
        try {
            BufferedReader objectReader = new BufferedReader(new FileReader("siters/" + string + ".txt"));
            boolean flag = false;
            String string2;
            while ((string2 = objectReader.readLine()) != null) {
                String string3 = "" + string2.trim();
                if (string3.startsWith("l")) {
                    String string4 = getstring("l", string3, 0);
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
            objectReader.close();
        } catch (Exception var15) {
            ;
        }
        System.gc();
    }

    public void runGame() {
        
        // JIT/hotspot warmup:
        // this compiles the method into native code
        for (int r = 0; r < 3000; ++r) System.currentTimeMillis();
        
        m = new Medium();
        xt = new xtGraphics(m, rd);
        String javaV = System.getProperty("java.version");
        String vendor = System.getProperty("java.vendor");
        if (vendor.startsWith("Sun Microsystems Inc.")) {
            if (javaV.startsWith("1.3")) {
                xt.goodsun = true;
            } else if (javaV.startsWith("1.4")) {
                sosun = true;
            } else {
                sosun = true;
                sndfrm = "newsun";
            }
        }
        lstat("Preparing for loading...", 0);
        ContO[] gameContos = new ContO[53];
        levelContos = new ContO[3000];
        usercraft = new userCraft(m);
        tanks = new Tank[20];
        for (int i = 0; i < 20; i++) {
            tanks[i] = new Tank(m);
        }
        __i = 0;
        crafts = new Craft[20];
        for (int j = 0; j < 20; j++) {
            crafts[j] = new Craft(m);
        }
        loadbase(gameContos, m);
        lstat("Loading 3D Models...", 17);
        loadobjects(levelContos, gameContos, m, "aces");
        lstat("Loading 3D Models...", 1);
        __i = maxco;
        loadobjects(levelContos, gameContos, m, "base");
        lstat("Loading 3D Models...", 2);
        loadobjects(levelContos, gameContos, m, "smap");
        lstat("Loading 3D Models...", 44);
        loadobjects(levelContos, gameContos, m, "clmap" + (int) (Math.random() * 5.0D) + "");
        lstat("Loading 3D Models...", 1);
        loadrots(levelContos, true);
        __j = 0;
        __ints = new int[600];
        for (int k = 0; k < maxco; ++k) {
            if (levelContos[k].colides) {
                __ints[__j] = k;
                ++__j;
            }
        }
        __ints2s = new int[20];
        __ints3s = new int[20];
        int l = 0;
        do {
            loadet[l] = false;
            ++l;
        } while (l < 7);
        try {
            downloadall(xt);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Date date = new Date();
        date.getTime();
        __j1 = 0;
        __flag2 = true;
        maxmo = 0;

        
        lastTime = System.currentTimeMillis();

        new Timer(35, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameTick();
                repaint();
            }
        }).start();
        
    }

    private void gameTick() {
        Date date;
        date = new Date();
        date.getTime();
        if (!mon) {
            int k1;
            int[] ints4;
            int l2;
            int i2;
            int j2;
            int[] ints5;
            if (!__flag2) {
                m.d(rd);
                k1 = 0;
                ints4 = new int[100];
                for (l2 = 0; l2 < maxco; ++l2) {
                    if (levelContos[l2].dist != 0) {
                        ints4[k1] = l2;
                        ++k1;
                    } else {
                        levelContos[l2].d(rd);
                    }
                }
                ints5 = new int[k1];
                for (i2 = 0; i2 < k1; ++i2) {
                    ints5[i2] = 0;
                    for (j2 = 0; j2 < k1; ++j2) {
                        if (levelContos[ints4[i2]].dist != levelContos[ints4[j2]].dist) {
                            if (levelContos[ints4[i2]].dist < levelContos[ints4[j2]].dist) {
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
                            if (levelContos[ints4[j2]].fire) {
                                if (ints4[j2] == __ints2s[0]) {
                                    usercraft.dl(rd);
                                } else {
                                    for (k2 = 1; k2 < maxmo; ++k2) {
                                        if (ints4[j2] == __ints2s[k2]) {
                                            if (__ints3s[k2] == 0) {
                                                crafts[k2].dl(rd);
                                            }
                                            if (__ints3s[k2] == 1) {
                                                tanks[k2].dl(rd);
                                            }
                                        }
                                    }
                                }
                            }
                            levelContos[ints4[j2]].d(rd);
                        }
                    }
                }
                if (xt.level < 6) {
                    for (i2 = 0; i2 < __j; ++i2) {
                        for (j2 = 0; j2 < maxmo; ++j2) {
                            if (__ints2s[j2] != __ints[i2]) {
                                levelContos[__ints[i2]].tryexp(levelContos[__ints2s[j2]]);
                                if (levelContos[__ints2s[j2]].fire) {
                                    if (j2 == 0) {
                                        usercraft.lasercolid(levelContos[__ints[i2]]);
                                    } else {
                                        if (__ints3s[j2] == 0) {
                                            crafts[j2].lasercolid(levelContos[__ints[i2]]);
                                        }
                                        if (__ints3s[j2] == 1) {
                                            tanks[j2].lasercolid(levelContos[__ints[i2]]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (i2 = __j - 1; i2 >= 0; --i2) {
                        for (j2 = 0; j2 < maxmo; ++j2) {
                            if (__ints2s[j2] != __ints[i2]) {
                                if (xt.level != 15 || j2 != 1) {
                                    levelContos[__ints[i2]].tryexp(levelContos[__ints2s[j2]]);
                                }
                                if (levelContos[__ints2s[j2]].fire) {
                                    if (j2 == 0) {
                                        usercraft.lasercolid(levelContos[__ints[i2]]);
                                    } else {
                                        if (__ints3s[j2] == 0) {
                                            crafts[j2].lasercolid(levelContos[__ints[i2]]);
                                        }
                                        if (__ints3s[j2] == 1) {
                                            tanks[j2].lasercolid(levelContos[__ints[i2]]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (i2 = 1; i2 < maxmo; ++i2) {
                    if (__ints3s[i2] == 0) {
                        crafts[i2].dosmokes(rd, levelContos[__ints2s[i2]]);
                        crafts[i2].preform(levelContos[__ints2s[i2]], levelContos, __ints, __j, __ints2s[0], __i);
                        if (levelContos[__ints2s[i2]].exp) {
                            if (!nosound) {
                                exp.play();
                            }
                            __ints3s[i2] = -1;
                        }
                        if (levelContos[__ints2s[i2]].hit && !nosound && frags == 0) {
                            hitl.play();
                            if (sosun) {
                                frags = 3;
                            }
                        }
                    }
                    if (__ints3s[i2] == 1) {
                        tanks[i2].dosmokes(rd, levelContos[__ints2s[i2]]);
                        tanks[i2].preform(levelContos[__ints2s[i2]], levelContos, __ints2s[0], __i);
                        if (levelContos[__ints2s[i2]].exp) {
                            if (!nosound) {
                                exp.play();
                            }
                            __ints3s[i2] = -1;
                        }
                        if (levelContos[__ints2s[i2]].hit && !nosound && frags == 0) {
                            hitl.play();
                            if (sosun) {
                                frags = 3;
                            }
                        }
                    }
                }
                usercraft.dosmokes(rd, levelContos[__ints2s[0]]);
                usercraft.preform(u, levelContos[__ints2s[0]], levelContos, __ints2s, maxmo);
                i2 = 0;
                if (tab) {
                    i2 = xt.cl;
                } else if (view != 4 && view != 5) {
                    xt.dtrakers(rd, __ints3s, __ints2s, maxmo, levelContos, usercraft, u);
                }
                if (view == 0) {
                    m.behinde(levelContos[__ints2s[i2]]);
                }
                if (view == 1) {
                    m.right(levelContos[__ints2s[i2]]);
                }
                if (view == 2) {
                    m.infront(levelContos[__ints2s[i2]]);
                }
                if (view == 3) {
                    m.left(levelContos[__ints2s[i2]]);
                }
                if (view == 4) {
                    m.around(levelContos[__ints2s[i2]], 800);
                }
                if (view == 5) {
                    m.watch(levelContos[__ints2s[i2]]);
                } else if (m.td) {
                    m.td = false;
                }
                if (levelContos[__ints2s[0]].exp) {
                    j2 = 0;
                    for (k2 = 0; k2 < levelContos[__ints2s[0]].npl; ++k2) {
                        if (levelContos[__ints2s[0]].p[k2].exp == 7) {
                            ++j2;
                        }
                    }
                    if (j2 == levelContos[__ints2s[0]].npl) {
                        __flag2 = true;
                        xt.dest[__ints2s[0]] = true;
                        if (xt.alldest()) {
                            xt.fase = 2;
                            xt.drawovimg(offImage);
                        } else {
                            xt.fase = 1;
                            xt.drawefimg(offImage);
                        }
                    }
                    if (u.space) {
                        u.space = false;
                    }
                } else {
                    if (xt.mcomp) {
                        if (u.space) {
                            if (xt.level != 15) {
                                xt.fase = -4;
                                ++xt.level;
                            } else {
                                xt.fase = 4;
                                xt.oldfase = 7;
                            }
                            __flag2 = true;
                            u.space = false;
                        }
                    } else if (u.space) {
                        __flag2 = true;
                        xt.drawpimg(offImage);
                        xt.fase = 3;
                        u.space = false;
                        xt.select = 0;
                    }
                    j2 = 0;
                    for (k2 = __i; k2 < __i + 13; ++k2) {
                        if (levelContos[k2].exp) {
                            ++j2;
                        }
                    }
                    if (j2 == 13) {
                        __flag2 = true;
                        xt.drawovimg(offImage);
                        xt.fase = 2;
                    }
                }
            } else {
                if (xt.fase == -4) {
                    m.d(rd);
                    k1 = 0;
                    ints4 = new int[100];
                    for (l2 = 0; l2 < maxco; ++l2) {
                        if (levelContos[l2].dist != 0) {
                            ints4[k1] = l2;
                            ++k1;
                        } else {
                            levelContos[l2].d(rd);
                        }
                    }
                    ints5 = new int[k1];
                    for (i2 = 0; i2 < k1; ++i2) {
                        ints5[i2] = 0;
                        for (j2 = 0; j2 < k1; ++j2) {
                            if (levelContos[ints4[i2]].dist != levelContos[ints4[j2]].dist) {
                                if (levelContos[ints4[i2]].dist < levelContos[ints4[j2]].dist) {
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
                                levelContos[ints4[j2]].d(rd);
                            }
                        }
                    }
                    m.around(levelContos[__i + 4], 6000);
                    if (u.space) {
                        xt.drawl(rd, offImage);
                    }
                }
                xt.denter(rd, __i, levelContos, usercraft, u);
                if (xt.fase == -5 && u.space) {
                    if (xt.select == 0) {
                        loadrots(levelContos, false);
                        for (k1 = __i; k1 < __i + 13; ++k1) {
                            levelContos[k1].out = false;
                        }
                        xt.reset();
                        xt.fase = -4;
                    }
                    if (xt.select == 1 && xt.sgame == 1) {
                        loadrots(levelContos, false);
                        xt.reset();
                        loadsaved(levelContos, xt, __i);
                        xt.fase = -4;
                    }
                    if (xt.select == 4) {
                        System.exit(0);
                    }
                    u.space = false;
                }
                if (xt.fase == -33) {
                    if (xt.frst && xt.select == 0) {
                        savegame(levelContos, xt, __i);
                    } else if (!xt.frst) {
                        xt.frst = true;
                    }
                    if (__j1 != 7) {
                        if (xt.goodsun) {
                            nounif = true;
                        }
                        try {
                            mtrak[__j1] = makeSound("data/music/" + __j1 + ".au");
                        } catch (IOException e) {
                            e.printStackTrace();
                            mtrak[__j1] = new SoundClip(false); // empty
                        }
                        loadet[__j1] = true;
                        ++__j1;
                    } else if (xt.goodsun) {
                        xt.goodsun = false;
                    }
                    loadmovers(__ints2s, __ints3s, levelContos, crafts, tanks, usercraft, xt);
                    nounif = false;
                    xt.fase = -2;
                }
                if (xt.fase == -3) {
                    xt.fase = -33;
                }
                if (xt.fase == 0 && u.space) {
                    if (!xt.dest[xt.selected]) {
                        setmover(__ints2s, levelContos, usercraft, xt);
                        __flag2 = false;
                        view = 0;
                    }
                    u.space = false;
                }
                if (xt.fase == 2 && xt.sgame == 1 && !xt.alldest()) {
                    set0();
                    xt.sgame = 0;
                }
                if (xt.fase == 3 && u.space) {
                    if (xt.select == 0) {
                        System.gc();
                        __flag2 = false;
                    }
                    u.space = false;
                }
                if (xt.fase == -8) {
                    if (xt.sgame == -1) {
                        getslevel(xt);
                    }
                    if (xt.cnty == 351) {
                        xt.drawop(rd, offImage);
                        xt.cnty = 352;
                    }
                }
            }
        } else {
            if (u.space) {
                u.space = false;
            }
            rd.setColor(new Color(223, 223, 223));
            rd.fillRect(0, 0, 500, 360);
            xt.drawcs(rd, 170, moner, 0, 0, 0, false);
        }
        if (!mon) {
            playsounds(usercraft, levelContos[__ints2s[0]], __flag2, xt);
        }
        
        long currentTime = System.currentTimeMillis();
        System.out.println("Delay per frame: " + (currentTime - lastTime));
        lastTime = currentTime;
        
    }

    public void lstat(String string, int i) {
        dnload += i;
        rd.setColor(new Color(223, 223, 223));
        rd.fillRect(0, 0, 500, 360);
        rd.setColor(new Color(174, 185, 198));
        rd.drawRect(150, 200, 200, 5);
        rd.fillRect(150, 200, 24 + (int) ((float) dnload / 594.0F * 176.0F), 5);
        rd.setColor(new Color(151, 166, 183));
        rd.drawString(string, 290, 220);
        rd.drawString("Remaining: " + (594 - dnload) + " KB", 202, 250);
        rd.setColor(new Color(0, 0, 0));
        rd.drawString("Loading " + (int) ((float) (24 + (int) ((float) dnload / 594.0F * 176.0F)) / 200.0F * 100.0F) + "%", 103, 194);
        repaint();
    }

    public void init() {
        offImage = new BufferedImage(500, 360, BufferedImage.TYPE_INT_RGB);
        if (offImage != null) {
            rd = offImage.createGraphics();
        }
        rd.setFont(new Font("SansSerif", 1, 11));
        
        addFocusListener(this);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();

        setBorder(null); //other border types are slightly flickery (invisible in nfmm, easily visible in RA)
        
        setBackground(Color.black);
        setOpaque(true);
        
        setLayout(null);
        
        runGame();
    }

    public void getslevel(xtGraphics xtgraphics) { //TODO
        try {
            /*JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("s=GetCookie(\'radxv\')");
            String string = String.valueOf(String.valueOf(jsobject.getMember("s")));
            if (string.equals("0")) {
                xtgraphics.sgame = 0;
            } else {
                xtgraphics.sgame = 1;
                xtgraphics.select = 1;
            }*/
        } catch (Exception var4) {
            ;
        }
    }

    public void loadsaved(ContO[] contos, xtGraphics xtgraphics, int i) { //TODO
        try {
            /*JSObject jsobject = JSObject.getWindow(this);
            jsobject.eval("s=GetCookie(\'radxv\')");
            String string = String.valueOf(String.valueOf(jsobject.getMember("s")));
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
            } while (j < 5);*/
        } catch (Exception var8) {
            ;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (maxmo != -1) {
            mon = false;
            if (moner.equals("Click here to Start")) {
                moner = "Click here to Continue";
            }
        }
        if (u.canclick) {
            u.space = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
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
            nosound = !nosound;
        }
        if (i == 114 || i == 82) {
            u.radar = true;
        }
        if (i == KeyEvent.VK_TAB) {
            tab = true;
        }
        if (i == KeyEvent.VK_PLUS || i == KeyEvent.VK_SHIFT || i == KeyEvent.VK_ADD) {
            u.plus = true;
        }
        if (i == KeyEvent.VK_MINUS || i == KeyEvent.VK_CONTROL || i == KeyEvent.VK_SUBTRACT) {
            u.mins = true;
        }
        if ((i == 106 || i == 74) && u.jump == 0) {
            u.jump = 1;
            if (!u.jade) {
                u.jade = true;
            }
        }
        if ((i == KeyEvent.VK_ENTER || i == KeyEvent.VK_ACCEPT) && !enterd) {
            u.space = true;
            enterd = true;
        }
        if (i == KeyEvent.VK_SPACE) {
            u.fire = true;
        }
        if (i == KeyEvent.VK_LEFT) {
            u.left = true;
        }
        if (i == KeyEvent.VK_RIGHT) {
            u.right = true;
        }
        if (i == KeyEvent.VK_DOWN) {
            u.down = true;
        }
        if (i == KeyEvent.VK_UP) {
            u.up = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int i = e.getKeyCode();

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
        if (i == KeyEvent.VK_PLUS || i == KeyEvent.VK_SHIFT || i == KeyEvent.VK_ADD) {
            u.plus = false;
        }
        if (i == KeyEvent.VK_MINUS || i == KeyEvent.VK_CONTROL || i == KeyEvent.VK_SUBTRACT) {
            u.mins = false;
        }
        if (i == KeyEvent.VK_ENTER) {
            enterd = false;
        }
        if (i == KeyEvent.VK_TAB) {
            tab = false;
        }
        if (i == KeyEvent.VK_SPACE) {
            u.fire = false;
        }
        if (i == KeyEvent.VK_LEFT) {
            u.left = false;
        }
        if (i == KeyEvent.VK_RIGHT) {
            u.right = false;
        }
        if (i == KeyEvent.VK_DOWN) {
            u.down = false;
        }
        if (i == KeyEvent.VK_UP) {
            u.up = false;
        }
    
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void focusLost(FocusEvent e) {
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
    }
}
