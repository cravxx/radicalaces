import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class xtGraphics extends Panel {

    /**
	 * 
	 */
    private static final long serialVersionUID = -7269675935604010359L;

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

    int[] ws = new int[] { 62, 73, 59, 40, 50 };

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

    public void denter(Graphics graphics, int i, ContO[] contos, userCraft usercraft, Control control) {
        int j;
        if (fase == 4) {
            j = 0;
            do {
                contos[j].out = false;
                contos[j].wire = true;
                contos[j].x = 0;
                contos[j].y = 180;
                contos[j].z = 0;
                contos[j].xy = 90;
                ++j;
            } while (j < 5);
            m.x = -100;
            m.y = 0;
            m.ground = 950 - m.y;
            m.z = -50;
            m.xz = -90;
            m.zy = 0;
            contos[0].zy = 0;
            graphics.setColor(new Color(255, 255, 0));
            j = 0;
            do {
                graphics.drawLine(j * 2, 0, j * 2, 360);
                ++j;
            } while (j < 250);
            if (oldfase == 7) {
                fase = 7;
                oldfase = 0;
                cnt = 0;
            } else {
                fase = 5;
            }
        }
        if (fase == -8) {
            if (cnty < 351) {
                graphics.drawImage(mars, 0, 0, (ImageObserver) null);
                graphics.drawImage(text, 10, 380 - cnty, (ImageObserver) null);
                if (cnty != 350) {
                    ++cnty;
                } else {
                    drawcs(graphics, 345, "Press Enter to continue", 225, 225, 225, true);
                    cnty = 351;
                }
            }
            if (control.space) {
                fase = -5;
                if (sgame == 1) {
                    select = 1;
                } else {
                    select = 2;
                }
                control.space = false;
            }
        }
        if (fase == -7) {
            graphics.drawImage(inst1, 0, 0, (ImageObserver) null);
            drawcs(graphics, 354, "Press Enter to continue >", 170, 170, 170, false);
            if (control.space) {
                fase = -6;
                control.space = false;
            }
        }
        if (fase == -6) {
            graphics.drawImage(inst2, 0, 0, (ImageObserver) null);
            drawcs(graphics, 354, "Press Enter to continue >", 170, 170, 170, false);
            if (control.space) {
                fase = -55;
                control.space = false;
            }
        }
        if (fase == -55) {
            graphics.drawImage(inst3, 0, 0, (ImageObserver) null);
            drawcs(graphics, 354, "Press Enter to continue >", 170, 170, 170, false);
            if (control.space) {
                fase = oldfase;
                control.space = false;
            }
        }
        if (fase == -5) {
            graphics.drawImage(main, 0, 0, (ImageObserver) null);
            if (cnt < 7) {
                graphics.drawImage(as[select], 25, 283, (ImageObserver) null);
                graphics.drawImage(as[select], 423, 283, (ImageObserver) null);
                ++cnt;
            } else {
                cnt = 0;
            }
            graphics.setColor(new Color(225, 230, 255));
            j = 50 + (int) (Math.random() * 150.0D);
            graphics.drawLine((int) (Math.random() * 400.0D), j, (int) (Math.random() * 200.0D), j);
            j = 50 + (int) (Math.random() * 150.0D);
            graphics.drawLine(500 - (int) (Math.random() * 400.0D), j, 500 - (int) (Math.random() * 200.0D), j);
            if (cnts < -900) {
                cnts = 0;
                cntf = (int) (Math.random() * 150.0D);
            } else {
                cnts -= 7;
            }
            if (control.space) {
                cnts = 10;
            }
            graphics.drawImage(rad, 500 + cnts, 50 + cntf, (ImageObserver) null);
            drawcs(graphics, 274, "Start New Game", 0, 0, 0, false);
            if (sgame != 0) {
                drawcs(graphics, 289, "Resume Saved Game", 0, 0, 0, false);
            } else {
                if (control.space && select == 1) {
                    wcnt = 20;
                }
                if (wcnt != 0) {
                    drawcs(graphics, 289, "No Saved Game!", 100, 0, 0, false);
                    wcnt += -1;
                } else {
                    drawcs(graphics, 289, "Resume Saved Game", 200, 200, 200, false);
                }
            }
            drawcs(graphics, 304, "Game Controls", 0, 0, 0, false);
            drawcs(graphics, 319, "Credits", 0, 0, 0, false);
            drawcs(graphics, 334, "Exit Game", 0, 0, 0, false);
            if (!flik) {
                graphics.setColor(new Color(225, 230, 255));
                flik = true;
                graphics.drawLine(250 - ws[select], 271 + 15 * select, 250 + ws[select], 271 + 15 * select);
                graphics.drawRect(250 - ws[select], 264 + 15 * select, ws[select] * 2, 11);
                graphics.setColor(new Color(0, 0, 0));
                graphics.drawLine(251 - ws[select], 271 + 15 * select, 255 - ws[select], 271 + 15 * select);
                graphics.drawLine(245 + ws[select], 271 + 15 * select, 249 + ws[select], 271 + 15 * select);
            } else {
                graphics.setColor(new Color(168, 183, 255));
                graphics.drawRect(250 - ws[select], 264 + 15 * select, ws[select] * 2, 11);
                flik = false;
            }
            if (control.down) {
                ++select;
                control.down = false;
            }
            if (control.up) {
                select += -1;
                control.up = false;
            }
            if (select == 5) {
                select = 0;
            }
            if (select == -1) {
                select = 4;
            }
            if (control.space) {
                if (select == 2) {
                    fase = -7;
                    oldfase = -5;
                    control.space = false;
                }
                if (select == 3) {
                    fase = 4;
                    control.space = false;
                }
            }
            drawcs(graphics, 354, "( use keyboard arrows to select and press Enter )", 170, 170, 170, false);
            if (frst) {
                frst = false;
            }
        }
        if (fase == -4) {
            if (control.space) {
                fase = -3;
                control.space = false;
            } else {
                j = 0;
                int k = 0;
                int l;
                for (l = i; l < i + 13; ++l) {
                    j += contos[l].nhits;
                    k += contos[l].maxhits;
                }
                if (j > k) {
                    j = k;
                }
                l = (int) ((float) j / (float) k * 100.0F);
                drawcs(graphics, 30, "The Mars Station..", 255, 255, 255, true);
                if (l >= 90 && !flik) {
                    drawcs(graphics, 60, "Damage status:  " + l + "%", 255, 0, 0, false);
                    flik = true;
                } else {
                    drawcs(graphics, 60, "Damage status:  " + l + "%", 0, 0, 0, false);
                    flik = false;
                }
                if (!frst) {
                    drawcs(graphics, 340, "Press Enter to continue", 255, 255, 255, false);
                } else {
                    drawcs(graphics, 300, "Mission " + level + " completed, do you wish to save game here?", 255, 255, 255, false);
                    if (select == 0) {
                        graphics.setColor(new Color(255, 255, 255));
                        graphics.fillRect(220, 319, 29, 14);
                        graphics.setColor(new Color(192, 192, 192));
                        graphics.drawRect(220, 319, 29, 14);
                    }
                    if (select != 0) {
                        graphics.setColor(new Color(255, 255, 255));
                        graphics.fillRect(256, 319, 22, 14);
                        graphics.setColor(new Color(192, 192, 192));
                        graphics.drawRect(256, 319, 22, 14);
                    }
                    if (control.up || control.down || control.left || control.right) {
                        if (select == 0) {
                            select = 1;
                        } else {
                            select = 0;
                        }
                        control.up = false;
                        control.down = false;
                        control.left = false;
                        control.right = false;
                    }
                    drawcs(graphics, 330, "Yes     No", 0, 0, 0, false);
                }
            }
        }
        if (fase == -3) {
            graphics.setColor(new Color(225, 230, 255));
            graphics.drawRect(1, 1, 497, 357);
            drawcs(graphics, 180, "Loading Mission " + (level + 1) + " ...", 225, 230, 255, true);
        }
        if (fase == -2) {
            rcnt = 0;
            j = 0;
            do {
                contos[j].reset();
                contos[j].out = false;
                contos[j].x = (j - selected) * 500;
                contos[j].y = 180;
                contos[j].z = 0;
                ++j;
            } while (j < 5);
            m.x = -m.cx;
            m.y = 0;
            m.ground = 250 - m.y;
            m.z = -620;
            m.xz = 0;
            m.zy = 0;
            contos[0].zy = 15;
            contos[0].xy = -15;
            contos[2].xy = -30;
            contos[3].zy = -15;
            contos[1].zy = 30;
            for (j = 0; j < nb; ++j) {
                obx[j] = contos[ob[j]].x;
                oby[j] = contos[ob[j]].y;
                obz[j] = contos[ob[j]].z;
                contos[ob[j]].x = -525;
                if (tnk[j]) {
                    contos[ob[j]].y = 95 + 305 * j;
                    contos[ob[j]].zy = 0;
                } else {
                    contos[ob[j]].y = 55 + 305 * j;
                    contos[ob[j]].zy = 20;
                }
                contos[ob[j]].z = 1000;
                contos[ob[j]].xy = 0;
                contos[ob[j]].xz = (int) (Math.random() * 270.0D);
                contos[ob[j]].out = false;
            }
            cmback(nb);
            fase = -1;
        }
        if (fase == 0) {
            if (!dest[selected]) {
                if (wcnt < 5) {
                    contos[selected].wire = true;
                } else {
                    contos[selected].wire = false;
                }
                if (wcnt > 9) {
                    wcnt = 0;
                } else {
                    ++wcnt;
                }
            }
            if (rcnt == 0) {
                if (control.left) {
                    left = true;
                    rcnt = 1;
                }
                if (control.right) {
                    left = false;
                    rcnt = 1;
                }
            } else {
                j = 0;
                do {
                    if (contos[j].x == 2000) {
                        contos[j].x = -500;
                    }
                    if (contos[j].x == -2000) {
                        contos[j].x = 500;
                    }
                    if (left) {
                        contos[j].x -= 100;
                    } else {
                        contos[j].x += 100;
                    }
                    ++j;
                } while (j < 5);
                contos[selected].wire = false;
                ++rcnt;
                if (rcnt == 6) {
                    wcnt = 7;
                    rcnt = 0;
                    if (left) {
                        if (selected != 4) {
                            ++selected;
                        } else {
                            selected = 0;
                        }
                    } else if (selected != 0) {
                        selected += -1;
                    } else {
                        selected = 4;
                    }
                    contos[selected].hit = true;
                    contos[selected].nhits = 0;
                }
            }
            if (control.space) {
                contos[selected].wire = false;
            }
            graphics.drawImage(sback, 0, 0, (ImageObserver) null);
            j = 0;
            do {
                contos[j].d(graphics);
                contos[j].xz += 2;
                ++j;
            } while (j < 5);
            if (dest[selected] && rcnt == 0) {
                graphics.drawImage(destr, 117, 103, (ImageObserver) null);
            }
            drawcs(graphics, 16, "Select your Ship", 255, 255, 255, false);
            drawcs(graphics, 354, "( use keyboard arrows to select )", 150, 150, 160, false);
            drawcs(graphics, 265, usercraft.name[selected], 190, 200, 255, false);
            if (control.space && dest[selected]) {
                drawcs(graphics, 80, "Cannot Select Ship!", 255, 230, 230, true);
            }
            int[] ints = new int[3];
            int[] ints2 = new int[3];
            graphics.setColor(new Color(100, 100, 100));
            if (rcnt == 1 && left) {
                graphics.setColor(new Color(225, 225, 225));
            }
            ints[0] = 50;
            ints2[0] = 255;
            ints[1] = 75;
            ints2[1] = 250;
            ints[2] = 75;
            ints2[2] = 260;
            graphics.fillPolygon(ints, ints2, 3);
            graphics.setColor(new Color(100, 100, 100));
            if (rcnt == 1 && !left) {
                graphics.setColor(new Color(225, 225, 225));
            }
            ints[0] = 450;
            ints2[0] = 255;
            ints[1] = 425;
            ints2[1] = 250;
            ints[2] = 425;
            ints2[2] = 260;
            graphics.fillPolygon(ints, ints2, 3);
            graphics.setColor(new Color(225, 225, 255));
            graphics.drawString("Max Speed", 57, 300);
            graphics.setColor(new Color(190, 200, 255));
            graphics.fillRect(125, 295, (int) (100.0F * ((float) usercraft.maxspeed[selected] / 120.0F)), 4);
            graphics.setColor(new Color(225, 225, 255));
            graphics.drawString(" Fire Power", 57, 315);
            graphics.setColor(new Color(190, 200, 255));
            graphics.fillRect(125, 310, (int) (100.0F * ((float) (usercraft.lsr.damg[selected] + 2) / 6.0F)), 4);
            graphics.setColor(new Color(225, 225, 255));
            graphics.drawString("  Tolerance", 57, 330);
            graphics.setColor(new Color(190, 200, 255));
            graphics.fillRect(125, 325, (int) (100.0F * ((float) contos[selected].maxhits / 300.0F)), 4);
            graphics.setColor(new Color(225, 225, 255));
            graphics.drawString("       Turning", 285, 300);
            graphics.setColor(new Color(190, 200, 255));
            graphics.fillRect(355, 295, (int) (100.0F * ((float) (usercraft.trnn[selected] + 3) / 5.0F)), 4);
            graphics.setColor(new Color(225, 225, 255));
            graphics.drawString("     Elevation", 285, 315);
            graphics.setColor(new Color(190, 200, 255));
            graphics.fillRect(355, 310, (int) (100.0F * ((float) (usercraft.elev[selected] + 3) / 5.0F)), 4);
            graphics.setColor(new Color(225, 225, 255));
            graphics.drawString("Light Speed Jumps:  " + usercraft.dnjm[selected], 285, 330);
        }
        if (fase == -1) {
            graphics.drawImage(mback, 0, 0, (ImageObserver) null);
            if (level == 15) {
                drawcs(graphics, 30, "Final Mission !", 255, 255, 255, true);
            } else {
                drawcs(graphics, 30, "Mission " + (level + 1), 255, 255, 255, true);
            }
            drawcs(graphics, 60, "Incoming Enemies:", 240, 240, 220, false);
            for (j = 0; j < nb; ++j) {
                graphics.drawImage(lay, 79, 90 + 80 * j, (ImageObserver) null);
                contos[ob[j]].d(graphics);
                contos[ob[j]].xz += 7 + j;
                drawcs(graphics, 125 + 80 * j, nam[j], 0, 0, 0, false);
            }
            if (nb == 0) {
                drawcs(graphics, 180, "- Error loading mission " + (level + 1) + " -", 255, 255, 255, false);
                drawcs(graphics, 200, "Connection Error!", 255, 255, 255, false);
                drawcs(graphics, 280, "Click screen or Press Enter to continue >", 180, 180, 150, true);
            } else if (goodsun) {
                if (flik) {
                    drawcs(graphics, 110 + 80 * nb, "Click Screen to Continue >", 180, 180, 150, true);
                    flik = false;
                } else {
                    drawcs(graphics, 110 + 80 * nb, "Click Screen to Continue >", 255, 255, 240, true);
                    flik = true;
                }
            } else {
                drawcs(graphics, 110 + 80 * nb, "Click screen or Press Enter to continue >", 180, 180, 150, true);
            }
            if (!control.canclick) {
                control.canclick = true;
            }
            if (control.space) {
                control.canclick = false;
                if (nb != 0) {
                    for (j = 0; j < nb; ++j) {
                        contos[ob[j]].x = obx[j];
                        contos[ob[j]].y = oby[j];
                        contos[ob[j]].z = obz[j];
                    }
                    fase = 0;
                } else {
                    fase = -5;
                    if (sgame == 1) {
                        select = 1;
                    } else {
                        select = 0;
                    }
                }
                control.space = false;
            }
        }
        if (fase == 1) {
            graphics.drawImage(mback, 0, 0, (ImageObserver) null);
            if (frst) {
                frst = false;
            }
            if (control.space) {
                fase = -3;
                control.space = false;
                drawcs(graphics, 230, "Loading Mission " + (level + 1) + " again...", 255, 255, 255, true);
            } else {
                if (!control.jade) {
                    drawcs(graphics, 250, "Don\'t forget to press the  [J]  key to escape lasers...", 225, 225, 225, false);
                }
                drawcs(graphics, 300, "Press Enter to continue", 225, 225, 225, false);
            }
        }
        if (fase == 2) {
            graphics.drawImage(mback, 0, 0, (ImageObserver) null);
            if (alldest()) {
                drawcs(graphics, 180, "All your ships were destroyed!", 255, 255, 255, true);
            } else {
                drawcs(graphics, 180, "The mars station was destroyed!", 255, 255, 255, true);
            }
            drawcs(graphics, 320, "Press Enter to continue", 225, 225, 225, true);
            if (control.space) {
                fase = -5;
                if (alldest() && sgame == 1) {
                    select = 1;
                } else {
                    select = 0;
                }
                control.space = false;
            }
        }
        if (fase == 3) {
            graphics.drawImage(mback, 0, 0, (ImageObserver) null);
            drawcs(graphics, 163, "Resume Game", 255, 255, 255, false);
            drawcs(graphics, 183, "Game Controls", 255, 255, 255, false);
            drawcs(graphics, 203, "Quit Game", 255, 255, 255, false);
            if (flik) {
                graphics.setColor(new Color(255, 0, 0));
                flik = false;
            } else {
                graphics.setColor(new Color(0, 128, 255));
                flik = true;
            }
            graphics.drawRect(190, 153 + select * 20, 120, 11);
            if (control.down) {
                ++select;
                control.down = false;
            }
            if (control.up) {
                select += -1;
                control.up = false;
            }
            if (select == 3) {
                select = 0;
            }
            if (select == -1) {
                select = 2;
            }
            if (control.space) {
                if (select == 1) {
                    fase = -7;
                    oldfase = 3;
                    control.space = false;
                }
                if (select == 2) {
                    fase = -5;
                    if (sgame == 1) {
                        select = 1;
                    } else {
                        select = 0;
                    }
                    control.space = false;
                }
            }
            drawcs(graphics, 354, "( use keyboard arrows to select )", 210, 210, 210, false);
        }
        if (fase == 5 || fase == 6 || fase == 7) {
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(100, 60, 300, 190);
            contos[(int) (Math.random() * 5.0D)].d(graphics);
            j = 0;
            do {
                contos[j].zy += 5;
                contos[j].xy += -1;
                ++j;
            } while (j < 5);
            if (contos[0].zy == 360) {
                contos[0].zy = 0;
                graphics.setColor(new Color(255, 255, 0));
                j = 0;
                do {
                    graphics.drawLine(j * 2, 0, j * 2, 360);
                    ++j;
                } while (j < 250);
            }
            graphics.drawImage(rad, 93, 32, (ImageObserver) null);
            if (fase == 5) {
                drawcs(graphics, 84, "Wild Polygons 3D engine by:", 0, 0, 0, false);
                drawcs(graphics, 96, "Omar Waly", 100, 100, 100, false);
                drawcs(graphics, 114, "3D models by:", 0, 0, 0, false);
                drawcs(graphics, 126, "Omar Waly", 100, 100, 100, false);
                drawcs(graphics, 144, "Game programming by:", 0, 0, 0, false);
                drawcs(graphics, 156, "Omar Waly", 100, 100, 100, false);
                drawcs(graphics, 174, "Graphics by:", 0, 0, 0, false);
                drawcs(graphics, 186, "Omar Waly", 100, 100, 100, false);
                drawcs(graphics, 204, "Sound effects by:", 0, 0, 0, false);
                drawcs(graphics, 216, "Guess who?", 100, 100, 100, false);
            }
            if (fase == 6) {
                drawcs(graphics, 80, "Music was obtained from FlashKit.com", 0, 0, 0, false);
                drawcs(graphics, 92, "and by the following artists:", 0, 0, 0, false);
                drawcs(graphics, 118, ".::Dj Hemp::.", 100, 100, 100, false);
                drawcs(graphics, 130, "Gen A Dee", 100, 100, 100, false);
                drawcs(graphics, 142, "Alex Volkmar", 100, 100, 100, false);
                drawcs(graphics, 154, "Empty", 100, 100, 100, false);
                drawcs(graphics, 166, "[BoD]Raven", 100, 100, 100, false);
                drawcs(graphics, 178, "Jeff Heysen", 100, 100, 100, false);
                drawcs(graphics, 190, "Degz", 100, 100, 100, false);
                drawcs(graphics, 202, "Justin Perkins", 100, 100, 100, false);
                drawcs(graphics, 214, "and Vika", 100, 100, 100, false);
            }
            if (fase == 7) {
                if (flik) {
                    drawcs(graphics, 140, "G a m e   C o m p l e t e !", 255, 0, 0, false);
                    flik = false;
                } else {
                    drawcs(graphics, 140, "G a m e   C o m p l e t e !", 0, 128, 255, true);
                    flik = true;
                }
                drawcs(graphics, 180, "Congratulations, you saved humanity!", 50, 50, 50, false);
                if (cnt % 5 == 0) {
                    drawcs(graphics, 195, "You are a true Radical Ace!", 255, 50, 50, false);
                } else {
                    drawcs(graphics, 195, "You are a true Radical Ace!", 150, 50, 50, false);
                }
                ++cnt;
                if (cnt > 600) {
                    fase = 5;
                }
                if (control.space) {
                    control.space = false;
                    fase = 5;
                }
            }
            drawcs(graphics, 246, "> Press Enter to continue >", 150, 150, 150, false);
            drawcs(graphics, 354, "Copyright © RadicalPlay.com", 255, 255, 255, true);
            if (control.space && fase != 7) {
                if (fase == 5) {
                    fase = 6;
                } else {
                    j = 0;
                    do {
                        contos[j].out = true;
                        contos[j].wire = false;
                        ++j;
                    } while (j < 5);
                    fase = -5;
                }
                control.space = false;
            }
        }
    }

    public void drawefimg(Image image) {
        saveit(image, pix);
        int i = 0;
        do {
            Color color = new Color(pix[i]);
            Color color2 = new Color(bpix[i]);
            int j = (color.getRed() + color2.getRed()) / 2;
            if (j > 225) {
                j = 225;
            }
            if (j < 0) {
                j = 0;
            }
            int k = (color.getGreen() + color2.getGreen()) / 2;
            if (k > 225) {
                k = 225;
            }
            if (k < 0) {
                k = 0;
            }
            int l = (color.getBlue() + color2.getBlue()) / 2;
            if (l > 225) {
                l = 225;
            }
            if (l < 0) {
                l = 0;
            }
            Color color3 = new Color(j, k, l);
            pix[i] = color3.getRGB();
            ++i;
        } while (i < 180000);
        mback = createImage(new MemoryImageSource(500, 360, pix, 0, 500));
    }

    public boolean alldest() {
        int i = 0;
        int j = 0;
        do {
            if (dest[j]) {
                ++i;
            }
            ++j;
        } while (j < 5);
        if (i == 5) {
            return true;
        } else {
            return false;
        }
    }

    public void drawpimg(Image image) {
        saveit(image, pix);
        int i = 0;
        do {
            int j = 0;
            do {
                Color color = new Color(pix[i + j * 500]);
                Color color2 = new Color(ppix[i + j * 500]);
                int k;
                int l;
                int i1;
                if (i > 150 && i < 350 && j > 130 && j < 230) {
                    k = (color.getRed() + color2.getRed()) / 4;
                    if (k > 225) {
                        k = 225;
                    }
                    if (k < 0) {
                        k = 0;
                    }
                    l = (color.getGreen() + color2.getGreen()) / 4;
                    if (l > 225) {
                        l = 225;
                    }
                    if (l < 0) {
                        l = 0;
                    }
                    i1 = (color.getBlue() + color2.getBlue()) / 4;
                    if (i1 > 225) {
                        i1 = 225;
                    }
                    if (i1 < 0) {
                        i1 = 0;
                    }
                } else {
                    k = (color.getRed() + color2.getRed()) / 2;
                    if (k > 225) {
                        k = 225;
                    }
                    if (k < 0) {
                        k = 0;
                    }
                    l = (color.getGreen() + color2.getGreen()) / 2;
                    if (l > 225) {
                        l = 225;
                    }
                    if (l < 0) {
                        l = 0;
                    }
                    i1 = (color.getBlue() + color2.getBlue()) / 2;
                    if (i1 > 225) {
                        i1 = 225;
                    }
                    if (i1 < 0) {
                        i1 = 0;
                    }
                }
                Color color3 = new Color(k, l, i1);
                pix[i + j * 500] = color3.getRGB();
                ++j;
            } while (j < 360);
            ++i;
        } while (i < 500);
        mback = createImage(new MemoryImageSource(500, 360, pix, 0, 500));
    }

    public int ys(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - m.focus_point) * (m.cy - i) / j + i;
    }

    public void reset() {
        int i = 0;
        do {
            dest[i] = false;
            ++i;
        } while (i < 5);
        level = 0;
    }

    public void creset() {
        cnt = 0;
        flik = false;
        cnts = 10;
        cntf = 0;
        left = false;
        wcnt = 0;
        rcnt = 0;
        cnty = 0;
    }

    public xtGraphics(Medium var1, Graphics var2) {
        m = var1;
        ftm = var2.getFontMetrics();
    }

    public void saveit(Image image, int[] ints) {
        PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, 500, 360, ints, 0, 500);
        try {
            pixelgrabber.grabPixels();
        } catch (InterruptedException var4) {
            ;
        }
    }

    public int xs(int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - m.focus_point) * (m.cx - i) / j + i;
    }

    public int getcpy(ContO conto, ContO conto2) {
        return (conto.x - conto2.x) / 100 * ((conto.x - conto2.x) / 100) + (conto.y - conto2.y) / 100 * ((conto.y - conto2.y) / 100) + (conto.z - conto2.z) / 100 * ((conto.z - conto2.z) / 100);
    }

    public void drawop(Graphics graphics, Image image) {
        saveit(image, pix);
        int i = 0;
        do {
            Color color = new Color(pix[i]);
            int j = Math.abs(255 - color.getRed());
            if (j > 255) {
                j = 255;
            }
            if (j < 0) {
                j = 0;
            }
            int k = Math.abs(255 - color.getGreen());
            if (k > 255) {
                k = 255;
            }
            if (k < 0) {
                k = 0;
            }
            int l = Math.abs(255 - color.getBlue());
            if (l > 255) {
                l = 255;
            }
            if (l < 0) {
                l = 0;
            }
            Color color2 = new Color(j, k, l);
            pix[i] = color2.getRGB();
            ++i;
        } while (i < 180000);
        graphics.drawImage(createImage(new MemoryImageSource(500, 360, pix, 0, 500)), 0, 0, (ImageObserver) null);
    }

    public void cmback(int i) {
        int j = 0;
        do {
            int k = 0;
            do {
                pix[j + k * 500] = mpix[j + k * 500];
                for (int l = 0; l < i; ++l) {
                    if (j > 82 && j < 416 && k > 95 + l * 80 && k < 147 + l * 80) {
                        Color color = new Color(222, 184, 34);
                        Color color2 = new Color(pix[j + k * 500]);
                        int i1 = (color.getRed() + color2.getRed()) / 2;
                        if (i1 > 225) {
                            i1 = 225;
                        }
                        if (i1 < 0) {
                            i1 = 0;
                        }
                        int j1 = (color.getGreen() + color2.getGreen()) / 2;
                        if (j1 > 225) {
                            j1 = 225;
                        }
                        if (j1 < 0) {
                            j1 = 0;
                        }
                        int k1 = (color.getBlue() + color2.getBlue()) / 2;
                        if (k1 > 225) {
                            k1 = 225;
                        }
                        if (k1 < 0) {
                            k1 = 0;
                        }
                        Color color3 = new Color(i1, j1, k1);
                        pix[j + k * 500] = color3.getRGB();
                    }
                }
                ++k;
            } while (k < 360);
            ++j;
        } while (j < 500);
        mback = createImage(new MemoryImageSource(500, 360, pix, 0, 500));
    }

    public void drawl(Graphics graphics, Image image) {
        saveit(image, pix);
        int i = 0;
        do {
            Color color = new Color(pix[i]);
            int j = Math.abs((color.getRed() - 15) / 2);
            if (j > 225) {
                j = 225;
            }
            if (j < 0) {
                j = 0;
            }
            int k = Math.abs((color.getGreen() - 10) / 2);
            if (k > 225) {
                k = 225;
            }
            if (k < 0) {
                k = 0;
            }
            int l = Math.abs((color.getBlue() + 20) / 2);
            if (l > 225) {
                l = 225;
            }
            if (l < 0) {
                l = 0;
            }
            Color color2 = new Color(j, k, l);
            pix[i] = color2.getRGB();
            ++i;
        } while (i < 180000);
        graphics.drawImage(createImage(new MemoryImageSource(500, 360, pix, 0, 500)), 0, 0, (ImageObserver) null);
    }

    public void drawovimg(Image image) {
        saveit(image, pix);
        int i = 0;
        do {
            Color color = new Color(pix[i]);
            Color color2 = new Color(opix[i]);
            int j = (int) (((double) color.getRed() / 1.7D + (double) color2.getRed()) / 2.0D);
            if (j > 225) {
                j = 225;
            }
            if (j < 0) {
                j = 0;
            }
            int k = (int) (((double) color.getGreen() / 1.7D + (double) color2.getGreen()) / 2.0D);
            if (k > 225) {
                k = 225;
            }
            if (k < 0) {
                k = 0;
            }
            int l = (int) (((double) color.getBlue() / 1.7D + (double) color2.getBlue()) / 2.0D);
            if (l > 225) {
                l = 225;
            }
            if (l < 0) {
                l = 0;
            }
            Color color3 = new Color(j, k, l);
            pix[i] = color3.getRGB();
            ++i;
        } while (i < 180000);
        mback = createImage(new MemoryImageSource(500, 360, pix, 0, 500));
    }

    public void dtrakers(Graphics graphics, int[] ints, int[] ints2, int i, ContO[] contos, userCraft usercraft, Control control) {
        cl = 1;
        int j = getcpy(contos[ints2[0]], contos[ints2[1]]);
        for (int k = 2; k < i; ++k) {
            if (j != 0 && !contos[ints2[cl]].exp) {
                int l = getcpy(contos[ints2[0]], contos[ints2[k]]);
                if ((l > 0 || j == 0) && l < j && !contos[ints2[k]].exp) {
                    j = l;
                    cl = k;
                }
            } else {
                cl = k;
                j = getcpy(contos[ints2[0]], contos[ints2[k]]);
            }
        }
        int[] ints3 = new int[4];
        int[] ints4 = new int[4];
        boolean flag = false;
        boolean flag2 = false;
        int i1 = 0;
        int j1;
        int k1;
        int l2;
        int i2;
        int j2;
        int k2;
        for (j1 = 1; j1 < i; ++j1) {
            short s = 1000;
            if (ints[j1] == 1) {
                s = 4000;
            }
            j = getcpy(contos[ints2[0]], contos[ints2[j1]]);
            if (j > s && !contos[ints2[j1]].exp) {
                k1 = m.cx + (int) ((float) (contos[ints2[j1]].x - m.x - m.cx) * SinCos.getcos(m.xz) - (float) (contos[ints2[j1]].z - m.z - m.cz) * SinCos.getsin(m.xz));
                l2 = m.cz + (int) ((float) (contos[ints2[j1]].x - m.x - m.cx) * SinCos.getsin(m.xz) + (float) (contos[ints2[j1]].z - m.z - m.cz) * SinCos.getcos(m.xz));
                i2 = m.cz + (int) ((float) (contos[ints2[j1]].y - m.y - m.cy) * SinCos.getsin(m.zy) + (float) (l2 - m.cz) * SinCos.getcos(m.zy));
                if (i2 > 100) {
                    j2 = m.cy + (int) ((float) (contos[ints2[j1]].y - m.y - m.cy) * SinCos.getcos(m.zy) - (float) (l2 - m.cz) * SinCos.getsin(m.zy));
                    k2 = xs(k1, i2);
                    int l3 = ys(j2, i2);
                    if (k2 > 0 && k2 < m.w && l3 > 0 && l3 < m.h) {
                        if (!flag && j != 0 && j < 10000) {
                            flag = true;
                        }
                        if (ints[j1] == 0) {
                            if (!contos[ints2[j1]].fire) {
                                graphics.setColor(new Color(164, 209, 255));
                            } else {
                                graphics.setColor(new Color(164, 229, 255));
                            }
                        } else if (!contos[ints2[j1]].fire) {
                            graphics.setColor(new Color(255, 150, 100));
                        } else {
                            graphics.setColor(new Color(255, 180, 100));
                        }
                        ints3[0] = k2 - 10;
                        ints4[0] = l3 - 10;
                        ints3[1] = k2 + 10;
                        ints4[1] = l3 - 10;
                        ints3[2] = k2 + 10;
                        ints4[2] = l3 + 10;
                        ints3[3] = k2 - 10;
                        ints4[3] = l3 + 10;
                        graphics.drawPolygon(ints3, ints4, 4);
                    }
                }
            }
            if (contos[ints2[j1]].exp) {
                if (cnte[j1 - 1] < 20 && !flag2) {
                    if (cntf < 2) {
                        if (contos[ints2[j1]].nhits >= contos[ints2[j1]].maxhits) {
                            drawcs(graphics, 120, mname[j1 - 1] + " distroyd!", 255, 255, 128, false);
                        } else {
                            drawcs(graphics, 120, mname[j1 - 1] + " Crashed!", 255, 255, 128, false);
                        }
                    } else if (contos[ints2[j1]].nhits >= contos[ints2[j1]].maxhits) {
                        drawcs(graphics, 120, mname[j1 - 1] + " distroyd!", 186, 223, 57, false);
                    } else {
                        drawcs(graphics, 120, mname[j1 - 1] + " Crashed!", 186, 223, 57, false);
                    }
                    if (cntf < 2) {
                        ++cntf;
                    } else {
                        cntf = 0;
                    }
                    ++cnte[j1 - 1];
                    flag2 = true;
                } else {
                    ++i1;
                }
            }
        }
        if (!mcomp && i1 == i - 1) {
            mcomp = true;
            select = 0;
        }
        if (mcomp && !contos[ints2[0]].exp) {
            if (rcnt == 0) {
                rcnt = 1;
            } else {
                graphics.setColor(new Color(50 + (int) (Math.random() * 200.0D), 50 + (int) (Math.random() * 200.0D), 50 + (int) (Math.random() * 200.0D)));
                graphics.fillRect(110, 67, 270, 13);
                rcnt = 0;
            }
            graphics.drawImage(complete, 105, 60, (ImageObserver) null);
            drawcs(graphics, 300, "Press Enter to continue", 0, 0, 0, false);
        }
        int i3;
        if (!flag && !contos[ints2[cl]].exp) {
            boolean flag3 = false;
            i3 = m.cx + (int) ((float) (contos[ints2[cl]].x - m.x - m.cx) * SinCos.getcos(m.xz) - (float) (contos[ints2[cl]].z - m.z - m.cz) * SinCos.getsin(m.xz));
            k1 = m.cz + (int) ((float) (contos[ints2[cl]].x - m.x - m.cx) * SinCos.getsin(m.xz) + (float) (contos[ints2[cl]].z - m.z - m.cz) * SinCos.getcos(m.xz));
            l2 = m.cz + (int) ((float) (contos[ints2[cl]].y - m.y - m.cy) * SinCos.getsin(m.zy) + (float) (k1 - m.cz) * SinCos.getcos(m.zy));
            i2 = m.cy + (int) ((float) (contos[ints2[cl]].y - m.y - m.cy) * SinCos.getcos(m.zy) - (float) (k1 - m.cz) * SinCos.getsin(m.zy));
            j2 = ys(i2, l2);
            k2 = xs(i3, l2);
            if (k2 < m.w && k2 > 0) {
                if (j2 > m.h || j2 < 0) {
                    if (k2 > m.w - 10) {
                        k2 = m.w - 50;
                    }
                    if (k2 < 5) {
                        k2 = 50;
                    }
                    if (i2 > m.cy) {
                        ints3[0] = k2;
                        ints4[0] = m.h - 1;
                        ints3[1] = k2 - 5;
                        ints4[1] = m.h - 20;
                        ints3[2] = k2 + 5;
                        ints4[2] = m.h - 20;
                        flag3 = true;
                    } else {
                        ints4[0] = 1;
                        ints3[0] = k2;
                        ints4[1] = 20;
                        ints3[1] = k2 - 5;
                        ints4[2] = 20;
                        ints3[2] = k2 + 5;
                        flag3 = true;
                    }
                }
            } else {
                if (j2 > m.h - 10) {
                    j2 = m.h - 50;
                }
                if (j2 < 5) {
                    j2 = 50;
                }
                if (i3 > m.cx) {
                    ints3[0] = m.w - 1;
                    ints4[0] = j2;
                    ints3[1] = m.w - 20;
                    ints4[1] = j2 - 5;
                    ints3[2] = m.w - 20;
                    ints4[2] = j2 + 5;
                    flag3 = true;
                } else {
                    ints3[0] = 1;
                    ints4[0] = j2;
                    ints3[1] = 20;
                    ints4[1] = j2 - 5;
                    ints3[2] = 20;
                    ints4[2] = j2 + 5;
                    flag3 = true;
                }
            }
            if (flag3) {
                if (ints[cl] == 0) {
                    graphics.setColor(new Color(164, 209, 255));
                } else {
                    graphics.setColor(new Color(255, 180, 100));
                }
                graphics.fillPolygon(ints3, ints4, 3);
            }
        }
        if (contos[ints2[0]].nhits > contos[ints2[0]].maxhits - contos[ints2[0]].maxhits / 3 && !contos[ints2[0]].exp && !mcomp) {
            if (cnt > 90) {
                if (flik) {
                    drawcs(graphics, 300, "Recharge Ship !", 255, 255, 255, false);
                    flik = false;
                } else {
                    drawcs(graphics, 300, "Recharge Ship !", 200, 200, 200, false);
                    flik = true;
                }
            } else {
                drawcs(graphics, 300, "Damage Critical", 255, 0, 0, false);
            }
            ++cnt;
            if (cnt == 130) {
                cnt = 0;
            }
        }
        if (control.jump >= 1 && usercraft.njumps == 0) {
            drawcs(graphics, 330, "Light speed jumps expired - Recharge Ship !", 255, 255, 255, false);
            ++control.jump;
            if (control.jump == 40) {
                control.jump = 0;
            }
        }
        if (usercraft.ester != 0 && !contos[ints2[0]].exp && !mcomp) {
            drawcs(graphics, 300, "Ship Recharged !", 255 * m.er, 255 - m.eg * 100, 64 + m.eb * 191, false);
        }
        if (control.radar && !mcomp) {
            graphics.drawImage(radar, 200, 60, (ImageObserver) null);
            j1 = contos[ints2[0]].zy;
            for (i3 = -contos[ints2[0]].xz; j1 > 360; j1 -= 360) {
                ;
            }
            while (j1 < 0) {
                j1 += 360;
            }
            if (j1 > 90 && j1 < 270) {
                i3 += 180;
            }
            for (k1 = 1; k1 < i; ++k1) {
                if (!contos[ints2[k1]].exp) {
                    l2 = m.cx + (int) ((float) (contos[ints2[k1]].x - m.x - m.cx) * SinCos.getcos(i3) - (float) (contos[ints2[k1]].z - m.z - m.cz) * SinCos.getsin(i3));
                    i2 = m.cz + (int) ((float) (contos[ints2[k1]].x - m.x - m.cx) * SinCos.getsin(i3) + (float) (contos[ints2[k1]].z - m.z - m.cz) * SinCos.getcos(i3));
                    graphics.setColor(new Color(0, 255, 128));
                    l2 = l2 / 400 + 249;
                    i2 = -i2 / 400 + 109;
                    if (l2 < 204) {
                        l2 = 204;
                    }
                    if (l2 > 296) {
                        l2 = 296;
                    }
                    if (i2 < 64) {
                        i2 = 64;
                    }
                    if (i2 > 156) {
                        i2 = 156;
                    }
                    graphics.fillRect(l2, i2, 2, 2);
                }
            }
        }
        if (control.plus || control.mins || cnts < 10) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawString("" + usercraft.rspeed + " zic/tes", 50, 55);
            graphics.drawImage(stube, 50, 60, (ImageObserver) null);
            j1 = (int) (260.0F - (float) usercraft.rspeed * (200.0F / (float) usercraft.maxspeed[usercraft.ltyp]));
            graphics.setColor(new Color(255, j1 - 10, 0));
            graphics.fillRect(61, j1, 12, 260 - j1);
            if (!control.plus && !control.mins) {
                ++cnts;
            } else {
                cnts = 0;
            }
        }
        if (tcnt != 0) {
            if (usercraft.rspeed == 0) {
                ++tcnt;
            } else {
                tcnt = 0;
            }
            if (!control.space) {
                if (tcnt > 90) {
                    drawcs(graphics, 80, "Press Enter for game controls and to pause game!", 255, 255, 255, false);
                }
            } else {
                tcnt = 0;
            }
        }
    }

    public void drawcs(Graphics graphics, int i, String string, int j, int k, int l, boolean flag) {
        if (flag) {
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawString(string, 250 - ftm.stringWidth(string) / 2 + 1, i + 1);
        }
        graphics.setColor(new Color(j, k, l));
        graphics.drawString(string, 250 - ftm.stringWidth(string) / 2, i);
    }
}
