package net.glasslauncher.binversionchecker;

import net.glasslauncher.common.FileUtils;

import java.net.URL;

public class Main {
    public static final String VERSION = Main.class.getPackage().getImplementationVersion() + "";

    public static void main(String[] args) {
		System.out.println("Current mappings version: " + VERSION);
		String remoteVersion = getRemoteVersion();
		System.out.println("Remote mappings version: " + remoteVersion);
		if (!VERSION.equals(remoteVersion)) {
			System.out.println("**********************************************************************************");
			System.out.println("*");
			System.out.println("*               YOUR MAPPINGS ARE OUTDATED, UPDATE THEM AT");
			System.out.println("*           https://github.com/calmilamsy/BIN-Mappings/releases");
			System.out.println("*");
			System.out.println("**********************************************************************************");
			if (!(args.length == 1 && args[0].equals("nogui"))) {
				new OpenLinkWindow(null, "Your mappings are out of date! Please update them at", "https://github.com/calmilamsy/BIN-Mappings/releases");
			}
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
