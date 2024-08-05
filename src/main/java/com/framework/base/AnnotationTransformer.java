package com.framework.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.framework.utilities.RetryAnalyzer;

/**
 * This Class Implements IAnnotationTransformer (Test NG Listener) to facilitate
 * retry Actions which will get performed evrerytime a given test case fails
 * 
 * RetryAnalyzer is the Class , That describes the Retry Actions based on the
 * test case status
 *
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	/**
	 * transform method get overriden, It Sets the Retry Analyzer to the Defined
	 * RetryAnalyzer Class
	 */
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}