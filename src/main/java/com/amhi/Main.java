package com.amhi;

public class Main {

	public static void main(String[] args) {

		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			System.setProperty("server.port", args[1]);
		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		if (serverName.equals("main")
				|| serverName.equals("mainApp")) {
			RegisterApplication.main(args);
		} else if (serverName.equals("app1")) {
			Application1.main(args);
		}else if (serverName.equals("app2")) {
			Application2.main(args);
		}else {
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar <abc.jar>	<server-name> [server-port]");
		System.out.println("where server-name is 'main', 'app1','app2' "
				+ " and server-port > 1024");
	}
}
