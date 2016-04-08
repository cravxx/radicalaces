import java.awt.Color;
import java.awt.Dimension;
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
    * @return icons
    */
    public static ArrayList<Image> getIcons() {
        if (icons == null) {
            icons = new ArrayList<Image>();
            int[] resols = { 16, 32, 48 };
            for (int res : resols) {
                icons.add(Toolkit.getDefaultToolkit().createImage("data/ico_" + res + ".gif"));
            }
        }
        return icons;
    }

    public static void main(String[] strings) {
        //Change this to the messgae of your preference
        System.out.println("<<< Radical Aces Console >>>");
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("Java Runtime: " + System.getProperty("java.runtime.name"));
        System.out.println("Java Version: " + System.getProperty("java.runtime.version"));
        
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
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
        applet.setDoubleBuffered(false); //prevents flickering
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent windowevent) {
                exitsequance();
            }
        });
        //The resolution of your game goes here
        applet.setPreferredSize(new Dimension(500, 360));
        frame.add("Center", applet);
        //If you plan to make you game support changes in resolution, you can comment out this line.
        frame.setResizable(false);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        applet.init();
    }
    
    public static void exitsequance() {
        frame.removeAll();
        try {
            Thread.sleep(200L);
        } catch (Exception exception) {
        }
        applet = null;
        System.exit(0);
    }
}
