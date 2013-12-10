package com.m4gik;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MainTest extends TestCase {
    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(MainTest.class);
    }

    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public MainTest(String testName) {
        super(testName);
    }

    /**
     * Rigourous Test :-)
     */
    public void testMain() {
        assertTrue(true);
    }
}
