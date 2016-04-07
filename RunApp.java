import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class RunApp extends Panel {

    /**
     *
     */
    private static final long serialVersionUID = 2824314018556470097L;
    static Frame frame;
    static F51 applet;
    public static ArrayList<Image> icons;

    /**
     * Fetches icons of 16, 32 and 48 pixels from the 'data' folder.
     */
    public static ArrayList<Image> getIcons() {
        if (icons == null) {
            icons = new ArrayList<Image>();
            final int[] resols = {
                    16, 32, 48
            };
            for (final int res : resols) {
                icons.add(Toolkit.getDefaultToolkit().createImage("data/ico_" + res + ".gif"));
            }
        }
        return icons;
    }

    public static void main(final String[] strings) {
        System.runFinalizersOnExit(true);
        //Change this to the messgae of your preference
        System.out.println("Nfm2-Mod Console");
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception ex) {
            System.out.println("Could not setup System Look&Feel: " + ex.toString());
        }
        startup();
    }

    static void startup() {
        //Change this to the name of your preference
        frame = new Frame("Radical Aces");
        frame.setBackground(new Color(0, 0, 0));
        frame.setIgnoreRepaint(true);
        frame.setIconImages(getIcons());
        applet = new F51();
        applet.setStub(new DesktopStub());
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent windowevent) {
                exitsequance();
            }
        });
        //The resolution of your game goes here
        applet.setPreferredSize(new java.awt.Dimension(500, 360));
        frame.add("Center", applet);
        //If you plan to make you game support changes in resolution, you can comment out this line.
        frame.setResizable(false);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        applet.init();
        applet.start();
    }

    public static void exitsequance() {
        applet.stop();
        frame.removeAll();
        try {
            Thread.sleep(200L);
        } catch (final Exception exception) {
        }
        applet.destroy();
        applet = null;
        System.exit(0);
    }
}
