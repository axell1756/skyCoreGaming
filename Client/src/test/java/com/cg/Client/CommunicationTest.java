package com.cg.Client;

import com.cg.Client.Client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommunicationTest {

	@Test
	public void testHelloRequestCorrect() throws Exception {
		String res = Client.postRequest("Hello");
		assert (res.equals("Hello stranger!"));
	}

	@Test
	public void testHelloRequestIncorrect() throws Exception {
		String res = Client.postRequest("hello");
		assert (res.equals("Error! No or invalid request name specified! (hello)"));
	}

	@Test
	public void testOccuranceOfZero() throws Exception {
		int occuranceOfZero = 0, occuranceOfOne = 0, occuranceOfTwo = 0, occuranceOfThree = 0, res = 0;

		/*
		 * Sending 100k requests to validate results returned with 99% Confidence and,
		 * depending on returned value, increment occuranceOf value
		 */

		for (int i = 0; i < 100000; i++) {
			res = Integer.parseInt(Client.postRequest("Table"));
			if (res == 0) {
				occuranceOfZero++;
			} else if (res == 1) {
				occuranceOfOne++;
			} else if (res == 2) {
				occuranceOfTwo++;
			} else {
				occuranceOfThree++;
			}
		}

		// Asserting if returned percentage of values is within allowance limits
		assertTrue((float) occuranceOfZero / 1000 <= 40.3997f && (float) occuranceOfZero / 1000 >= 39.6003f
				&& (float) occuranceOfOne / 1000 <= 30.3739f && (float) occuranceOfOne / 1000 >= 29.6261f
				&& (float) occuranceOfTwo / 1000 <= 20.3263f && (float) occuranceOfTwo / 1000 >= 19.6737f
				&& (float) occuranceOfThree / 1000 <= 10.2448f && (float) occuranceOfThree / 1000 >= 9.7552f);

	}
}
