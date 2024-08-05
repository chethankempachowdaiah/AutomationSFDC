package com.framework.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author priyanka.acharya
 * 
 *         Garbage collector will collect exceptions in the ArrayList and Return
 *         the Exceptions at the end
 * 
 *
 */
public class GarbageCollector {
	public static List<Throwable> errorBag = new ArrayList<Throwable>();

	public static void addVerificationFailure(Throwable t) {
		errorBag.add(t);
	}

	public static List<Throwable> getVerificationFailure() {
		return errorBag;

	}

}
