import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class F51 extends JComponent implements KeyListener, MouseListener, FocusListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1399200686375699720L;

    private static final String modelsDir = "data/models.radq";

    private static final String imagesDir = "data/images.radq";
    
    private static final String saveDir = "data/";

    private Graphics2D rd;

    private BufferedImage offImage;

    private boolean mon = true;

    private String moner = "Click here to Start";

    /**
	 * names of the .rad's are loaded into this String array in loadbase()
	 */
    private String[] modelNames = new String[53];

    private boolean nounif = false;

    private Control u = new Control();

    private boolean tab = false;

    private int view = 0;

    private int maxco = 0;

    private int maxmo = -1;

    private SoundClip upl;

    private SoundClip low;

    private SoundClip med;

    private SoundClip downl;

    private SoundClip ljump;

    private SoundClip grnd;

    private SoundClip exp;

    private SoundClip exph;

    private SoundClip hit;

    private SoundClip hitl;

    private SoundClip charged;

    private SoundClip into;

    private SoundClip miso;

    private SoundClip mano;

    private SoundClip selo;

    private SoundClip[] las = new SoundClip[5];

    private SoundClip[] mtrak = new SoundClip[7];

    private boolean[] loadet = new boolean[7];

    private boolean plow = false;

    private boolean pmed = false;

    private boolean pexph = false;

    private boolean pint = false;

    private boolean pmis = false;

    private boolean pman = false;

    private boolean psel = false;

    private boolean nomusic = false;

    private boolean nosound = false;

    private boolean enterd = false;

    private boolean sosun = false;

    private int pgrnd = 0;

    private int pdownl = 0;

    private int pupl = 0;

    private int lascnt = 0;

    private int crntt = -1;

    private int plcnt = 0;

    private int frags = 0;

    private int dnload = 0;

    private ContO[] levelContos;
    
    private Craft[] crafts;
    
    private Tank[] tanks;
    
    private userCraft usercraft;
    
    private xtGraphics xt;
    
    static Medium m;

    // runner fields
    private boolean __flag2;

    private int __j1;

    private int[] __ints3s;

    private int[] __ints2s;

    private int[] __ints;

    private int __j;

    private int __i;

    private boolean failedLoad = false;

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

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(offImage, 0, 0, this);
    }

    /**
     * Saves the game
     * @param contos the contos
     * @param xtgraphics the xtgraphics
     * @param i this value must be __i
     */
    public void savegame(ContO[] contos, xtGraphics xtgraphics, int i) {
        try {
        	
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(saveDir + "game.dat"))));
            fout.println("radxv(" + xtgraphics.level + ")");
            
            StringBuilder sb = new StringBuilder();
            sb.append("radnhits(");
            sb.append(contos[i].nhits);
            
            for (int j = i + 1; j < i + 13; ++j) {
                sb.append(",");
                sb.append(contos[j].nhits);
            }
            sb.append(")");
            fout.println(sb.toString());
            
            sb = new StringBuilder();
            sb.append("raddest(");
            sb.append(xtgraphics.dest[0] ? 1 : 0);
            
            for (int j = 1; j < 5; ++j) {
                sb.append(",");
                sb.append(xtgraphics.dest[j] ? 1 : 0);
            }
            sb.append(")");
            fout.println(sb.toString());
            
            fout.close();            
            
            xtgraphics.sgame = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadrots(ContO[] contos, boolean flag) {
        for (int i = 0; i < maxco; ++i) {
            contos[i].loadrots(flag);
        }
    }

    /**
     * byte array to image
     * @param b byte input
     * @param toolkit toolkit
     * @return image
     */
    public Image returnImg(byte b[], Toolkit toolkit) {
        return toolkit.createImage(b);
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
            System.out.println("Contos loaded: " + modelNames.length);
        } catch (IOException e) {
            System.out.println("Error Reading Models: " + e);
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
                    ints[maxmo] = Utility.getint("craft", string2, 0);
                    ints2[maxmo] = 0;
                }
                if (string2.startsWith("tank")) {
                    ints[maxmo] = Utility.getint("tank", string2, 0);
                    ints2[maxmo] = 1;
                }
                if (string2.startsWith("name")) {
                    xtgraphics.mname[maxmo - 1] = Utility.getstring("name", string2, 0);
                    xtgraphics.cnte[maxmo - 1] = 0;
                }
                if (string2.startsWith("l")) {
                    conto[ints[maxmo]].x = Utility.getint("l", string2, 0) * 10;
                    conto[ints[maxmo]].y = Utility.getint("l", string2, 1) * 10;
                    conto[ints[maxmo]].z = Utility.getint("l", string2, 2) * 10;
                    conto[ints[maxmo]].out = false;
                    conto[ints[maxmo]].reset();
                }
                if (string2.startsWith("prompt")) {
                    if (Utility.getstring("prompt", string2, 0).equals("tank")) {
                        xtgraphics.tnk[xtgraphics.nb] = true;
                    } else {
                        xtgraphics.tnk[xtgraphics.nb] = false;
                    }
                    xtgraphics.ob[xtgraphics.nb] = Utility.getint("prompt", string2, 1);
                    xtgraphics.nam[xtgraphics.nb] = Utility.getstring("prompt", string2, 2).replace('|', ',');
                    ++xtgraphics.nb;
                }
                if (string2.startsWith("stat")) {
                    if (ints2[maxmo] == 0) {
                        craft[maxmo].reset(Utility.getint("stat", string2, 0), Utility.getint("stat", string2, 1), Utility.getint("stat", string2, 2), Utility.getint("stat", string2, 3), Utility.getint("stat", string2, 4), Utility.getint("stat", string2, 5));
                    } else {
                        tank[maxmo].reset(Utility.getint("stat", string2, 0), Utility.getint("stat", string2, 1));
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

    /** 
     * Resets the game state
     */
    public void set0() {
        try {
            PrintWriter fout = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(saveDir + "game.dat"))));
            fout.println("radxv(0)");
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads images
     * @param xtgraphics xt instance 
     */
	public void loadimages(xtGraphics xtgraphics) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		dnload += 12;
		int howManyImages = 0;
		
		try {
			ZipInputStream zipinputstream = new ZipInputStream(new FileInputStream(imagesDir));
			for (ZipEntry zipentry = zipinputstream.getNextEntry(); zipentry != null; zipentry = zipinputstream
					.getNextEntry()) {
				int i = (int) zipentry.getSize();
				String s = zipentry.getName();
				byte abyte0[] = new byte[i];
				int j = 0;
				int k;
				for (; i > 0; i -= k) {
					k = zipinputstream.read(abyte0, j, i);
					j += k;
				}

				if ("radar.gif".equals(s)) {
					xtgraphics.radar = returnImg(abyte0, toolkit);
				}
				if ("stube.gif".equals(s)) {
					xtgraphics.stube = returnImg(abyte0, toolkit);
				}
				if ("select.jpg".equals(s)) {
					xtgraphics.sback = returnImg(abyte0, toolkit);
				}
				if ("destroyed.gif".equals(s)) {
					xtgraphics.destr = returnImg(abyte0, toolkit);
				}
				if ("layout.gif".equals(s)) {
					xtgraphics.lay = returnImg(abyte0, toolkit);
				}
				if ("comp.gif".equals(s)) {
					xtgraphics.complete = returnImg(abyte0, toolkit);
				}
				if ("main.gif".equals(s)) {
					xtgraphics.main = returnImg(abyte0, toolkit);
				}
				if ("radicalplay.gif".equals(s)) {
					xtgraphics.rad = returnImg(abyte0, toolkit);
				}
				
				for(int asInc = 0; asInc < 5; asInc++){
					if (("a" + asInc + ".gif").equals(s)) {
						xtgraphics.as[asInc] = returnImg(abyte0, toolkit);
					}
				}
				
				if ("inst1.gif".equals(s)) {
					xtgraphics.inst1 = returnImg(abyte0, toolkit);
				}
				if ("inst2.gif".equals(s)) {
					xtgraphics.inst2 = returnImg(abyte0, toolkit);
				}
				if ("inst3.gif".equals(s)) {
					xtgraphics.inst3 = returnImg(abyte0, toolkit);
				}
				
				if ("mars.jpg".equals(s)) {
					xtgraphics.mars = returnImg(abyte0, toolkit);
				}
				
				if ("failed.jpg".equals(s)) {
					xtgraphics.saveit(returnImg(abyte0, toolkit), xtgraphics.bpix);
				}
				if ("mission.jpg".equals(s)) {
					xtgraphics.saveit(returnImg(abyte0, toolkit), xtgraphics.mpix);
				}
				if ("over.jpg".equals(s)) {
					xtgraphics.saveit(returnImg(abyte0, toolkit), xtgraphics.opix);
				}
				
				howManyImages++;
				dnload += 3;
			}			
			zipinputstream.close();
			System.out.println("Images loaded: " + howManyImages);
		} catch (IOException e) {
			System.out.println("Error Reading Images: " + e);
			e.printStackTrace();
		}
		System.gc();
	}

    public void loaddata(xtGraphics xtgraphics) throws IOException {
    	
    	loadimages(xt); 
    	/*
    	 * I think the loadimages method increments the loading bar itself so I'll just make this 0, i dunno. 
    	 * who cares about the loading bar anyway eh
    	 */
    	lstat("Loading Images...", 0);
    	
        into = makeSound("data/music/intro.wav");
        lstat("Loading Music...", 24);
        miso = makeSound("data/music/mission.wav");
        lstat("Loading Music...", 29);
        selo = makeSound("data/music/select.wav");
        lstat("Loading Music...", 52);
        mano = makeSound("data/music/main.wav");
        lstat("Loading Music...", 50);
        upl = makeSound("data/sounds/up.wav");
        lstat("Loading Sound Effects...", 11);
        hitl = makeSound("data/sounds/hitl.wav");
        lstat("Loading Sound Effects...", 7);
        downl = makeSound("data/sounds/down.wav");
        lstat("Loading Sound Effects...", 10);
        low = makeSound("data/sounds/low.wav");
        lstat("Loading Sound Effects...", 11);
        med = makeSound("data/sounds/med.wav");
        lstat("Loading Sound Effects...", 6);
        ljump = makeSound("data/sounds/jump.wav");
        lstat("Loading Sound Effects...", 25);
        grnd = makeSound("data/sounds/grnd.wav");
        lstat("Loading Sound Effects...", 5);
        exp = makeSound("data/sounds/exp.wav");
        lstat("Loading Sound Effects...", 10);
        exph = makeSound("data/sounds/exph.wav");
        lstat("Loading Sound Effects...", 12);
        hit = makeSound("data/sounds/hit.wav");
        lstat("Loading Sound Effects...", 25);
        int i = 0;
        do {
            las[i] = makeSound("data/sounds/l" + i + ".wav");
            lstat("Loading Sound Effects...", 9);
            ++i;
        } while (i < 5);
        charged = makeSound("data/sounds/charged.wav");
        lstat("Loading Sound Effects...", 12);
    }

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
                    String string4 = Utility.getstring("l", string3, 0);
                    int i;
                    int j;
                    int k;
                    if (!flag) {
                        i = Utility.getint("l", string3, 1) * 10;
                        j = Utility.getint("l", string3, 2) * 10;
                        k = Utility.getint("l", string3, 3) * 10;
                    } else {
                        i = Utility.getint("l", string3, 1);
                        j = Utility.getint("l", string3, 2);
                        k = Utility.getint("l", string3, 3);
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
                    contos[maxco - 1].xy = Utility.getint("xy", string3, 0);
                }
                if (string3.startsWith("xz")) {
                    contos[maxco - 1].xz = Utility.getint("xz", string3, 0);
                }
                if (string3.startsWith("zy")) {
                    contos[maxco - 1].zy = Utility.getint("zy", string3, 0);
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
    	
    	Utility.startTimer("Game loading");
        
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
            } else {
                sosun = true;
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
            loaddata(xt);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Utility.stopTimer();
        Date date = new Date();
        date.getTime();
        __j1 = 0;
        __flag2 = true;
        maxmo = 0;

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
                            savegame(levelContos, xt, __i);
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
                            mtrak[__j1] = makeSound("data/music/" + __j1 + ".wav");
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
                    if (xt.sgame == -1 && !failedLoad) {
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
        /*
         * load some fonts
         */
        new FontHandler();
        
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

    /**
     * Checks if the game has been saved at least once before
     * 
     * @param xtgraphics the xtGraphics
     */
    public void getslevel(xtGraphics xtgraphics) {
        try {
        	File saveFile = new File(saveDir + "game.dat");
        	if (saveFile.exists()) {
        		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(saveFile)));
                String radxv = reader.readLine();
                if (radxv != null && Utility.getint("radxv", radxv, 0) == 0) {
                    xtgraphics.sgame = 0;
                } else {
                    xtgraphics.sgame = 1;
                    xtgraphics.select = 1;
                }
                reader.close();
                System.out.println(saveFile + " loaded!");
        	} else {
                failedLoad = true;
        		System.out.println(saveFile + " does not exist yet!");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            failedLoad = true;
            JOptionPane.showMessageDialog(this, "Failed to load game!\nYour saved data may be corrupted.", "Radical Aces", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads the saved game
     * 
     * @param contos the game contos
     * @param xtgraphics the xtgraphics
     * @param i must be equal to __i
     */
    public void loadsaved(ContO[] contos, xtGraphics xtgraphics, int i) {
        try {
            File saveFile = new File(saveDir + "game.dat");
            
            if (saveFile.exists()) { //not required but i suppose it can stay
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(saveFile)));
                
                String line = reader.readLine();
                if (line != null)
                    xtgraphics.level = Utility.getint("radxv", line, 0);
                else {
                    reader.close();
                    return;
                }
                
                //
                line = reader.readLine();
                for (int j = i; j < i + 13; ++j) {
                    contos[j].nhits = Utility.getint("radnhits", line, j);
                    if (contos[j].nhits >= contos[j].maxhits) {
                        contos[j].exp = true;
                        contos[j].out = true;
                    } else {
                        contos[j].out = false;
                    }
                }
    
                //
                line = reader.readLine();
                for (int j = 0; j < 5; ++j) {
                    xtgraphics.dest[j] = Utility.getint("raddest", line, j) == 1;
                }
                
                
                //radxv, radhits then raddest
                
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
