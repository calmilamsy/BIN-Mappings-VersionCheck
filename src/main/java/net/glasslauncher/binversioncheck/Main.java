package net.glasslauncher.binversioncheck;

import net.glasslauncher.common.CommonConfig;
import net.glasslauncher.common.FileUtils;

import javax.swing.JOptionPane;
import java.net.URL;
import java.util.logging.Logger;

public class Main {
    public static final String VERSION = Main.class.getPackage().getImplementationVersion() + "";
    private static final Logger LOGGER = CommonConfig.makeLogger("BIN VersionCheck", "binversioncheck");

    public static void main(String[] args) {
		LOGGER.info("Current mappings version: " + VERSION);
		String remoteVersion = getRemoteVersion();
		LOGGER.info("Remote mappings version: " + remoteVersion);
		if (!VERSION.equals(remoteVersion)) {
			LOGGER.warning("**********************************************************************************");
			LOGGER.warning("*");
			LOGGER.warning("*               YOUR MAPPINGS ARE OUTDATED, UPDATE THEM AT");
			LOGGER.warning("*           https://github.com/calmilamsy/BIN-Mappings/releases");
			LOGGER.warning("*");
			LOGGER.warning("**********************************************************************************");
			if (!(args.length > 0 && args[0].equals("nogui"))) {
				if (!(args.length > 1 && args[2].equals("noupdate"))) {
					int response = JOptionPane.showConfirmDialog(null, "Update mappings to \"" + remoteVersion + " from " + VERSION + "\"?\nYour source will be backed up to \"srcBackup\" before migrating.\nNote: You might need to manually fix some names as Yarn's updater doesn't handle every situation yet.", "Update Mappings?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {

					}
				}
				new OpenLinkWindow(null, "Your mappings are out of date! Please update them at", "https://github.com/calmilamsy/BIN-Mappings/releases");
			}
		} else {
			LOGGER.info("Mappings are up to date!");
		}
    }

    private static String getRemoteVersion() {
    	try {
			return FileUtils.convertStreamToString((new URL("https://api.github.com/repos/calmilamsy/BIN-Mappings/commits/master")).openStream()).split("\"sha\":\"")[1].substring(0, 7);
		} catch (Exception e) {
    		e.printStackTrace();
    		return "ERROR";
		}
    }
}
