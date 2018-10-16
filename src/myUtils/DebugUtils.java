package myUtils;

import java.util.List;

import propp.Configuration;

public class DebugUtils {
	
	public static void debugPrint(String s) {
		if (!Configuration.getInstance().debugMode) return;
		System.out.println(s);
	}
	
	public static void debugPrintList(List<String> ls) {
		if (!Configuration.getInstance().debugMode) return;
		ListUtils.printList(ls);
	}

}
