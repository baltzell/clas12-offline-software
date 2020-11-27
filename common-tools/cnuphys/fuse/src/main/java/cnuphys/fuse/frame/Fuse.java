package cnuphys.fuse.frame;

import java.awt.EventQueue;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import cnuphys.bCNU.application.BaseMDIApplication;
import cnuphys.bCNU.application.Desktop;
import cnuphys.bCNU.util.PropertySupport;
import cnuphys.bCNU.view.ViewManager;
import cnuphys.bCNU.view.VirtualView;

public class Fuse extends BaseMDIApplication {

	// the singleton
	private static Fuse _instance;

	
	//ced release 
	private static final String _release = "build 0.01";
	
	// used for one time inits
	private int _firstTime = 0;

	//the views
	private VirtualView _virtualView;
	
	
	private Fuse(Object... keyVals) {
		super(keyVals);
		
		ComponentListener cl = new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent ce) {
			}

			@Override
			public void componentMoved(ComponentEvent ce) {
				placeViewsOnVirtualDesktop();
			}

			@Override
			public void componentResized(ComponentEvent ce) {
				placeViewsOnVirtualDesktop();
			}

			@Override
			public void componentShown(ComponentEvent ce) {
				placeViewsOnVirtualDesktop();
			}

		};

		addComponentListener(cl);
		
	}
	
	// arrange the views on the virtual desktop
	private void placeViewsOnVirtualDesktop() {
		if (_firstTime == 1) {
			// rearrange some views in virtual space
			_virtualView.reconfigure();
			restoreDefaultViewLocations();

			// now load configuration
			Desktop.getInstance().loadConfigurationFile();
			Desktop.getInstance().configureViews();
		}
		_firstTime++;
	}
	
	/**
	 * Restore the default locations of the default views. Cloned views are
	 * unaffected.
	 */
	private void restoreDefaultViewLocations() {
		
	}
	
	/**
	 * Add the initial views to the desktop.
	 */
	private void addInitialViews() {
		// add a virtual view
		_virtualView = VirtualView.createVirtualView(6);
		ViewManager.getInstance().getViewMenu().addSeparator();

	}
	
	/**
	 * Add items to existing menus and/or create new menus NOTE: Swim menu is
	 * created by the SwimManager
	 */
	private void createMenus() {
		
	}
	
	/**
	 * Refresh all views (with containers)
	 */
	public static void refresh() {
		ViewManager.getInstance().refreshAllViews();
	}
	
	/**
	 * Get the virtual view
	 * 
	 * @return the virtual view
	 */
	public VirtualView getVirtualView() {
		return _virtualView;
	}

	/**
	 * private access to the Ced singleton.
	 * 
	 * @return the singleton Ced (the main application frame.)
	 */
	private static Fuse getInstance() {
		if (_instance == null) {
			_instance = new Fuse(PropertySupport.TITLE, "fuse " + versionString(), PropertySupport.BACKGROUNDIMAGE,
					"images/cnu.png", PropertySupport.FRACTION, 0.9);

			_instance.addInitialViews();
			_instance.createMenus();
			_instance.placeViewsOnVirtualDesktop();
		}
		return _instance;
	}

	/**
	 * public access to the singleton
	 * 
	 * @return the singleton Fuse (the main application frame.)
	 */
	public static Fuse getFuse() {
		return _instance;
	}

	/**
	 * Generate the version string
	 * 
	 * @return the version string
	 */
	public static String versionString() {
		return _release;
	}
	
	/**
	 * Main program
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		// now make the frame visible, in the AWT thread
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				getInstance();
				getFuse().setVisible(true);
				System.out.println("fuse  " + _release + " is ready.");
			}

		});
	}
}