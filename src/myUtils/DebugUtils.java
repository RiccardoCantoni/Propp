package myUtils;

import java.util.List;

import propp.SystemState;

public class DebugUtils {
	
	public static void debugPrint(String s) {
		if (!SystemState.getInstance().debugMode) return;
		System.out.println(s);
	}
	
	public static void debugPrintList(List<String> ls) {
		if (!SystemState.getInstance().debugMode) return;
		ListUtil.printList(ls);
	}

}
