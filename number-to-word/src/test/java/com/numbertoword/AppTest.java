package com.numbertoword;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.numbertoword.exception.InvalidNumberException;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test1() {
		NumberToWords.convert(1);
	}

	@Test
	public void test21() {
		NumberToWords.convert(21);
	}

	@Test
	public void test105() {
		NumberToWords.convert(105);
	}

	@Test
	public void test_999999999() {
		NumberToWords.convert(999999999);
	}

	@Test
	public void test_2147483647() {
		NumberToWords.convert(2147483647);
		thrown.expect(InvalidNumberException.class);

	}
}
