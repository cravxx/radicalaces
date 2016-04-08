import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An implementation of <code>AppletStub</code>, optimized for desktop apps.
 * It's not complete though, only the methods needed by Nfm2 are implemented.
 * @author DragShot
 */
public class DesktopStub implements AppletStub {

    AppletContext context = new DesktopContext();

    /**
    * 
    */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
    * 
    */
    @Override
    public URL getDocumentBase() {
        try {
            return new URL("file:///" + System.getProperty("user.dir") + "/");
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    /**
    * 
    */
    @Override
    public URL getCodeBase() {
        try {
            return new URL("file:///" + System.getProperty("user.dir") + "/");
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    /**
    * This method is not implemented.
    */
    @Override
    public String getParameter(String name) {
        return null;
    }

    /**
    * 
    */
    @Override
    public AppletContext getAppletContext() {
        return context;
    }

    /**
    * 
    */
    @Override
    public void appletResize(int width, int height) {
    }
}
