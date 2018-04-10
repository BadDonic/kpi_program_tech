import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class AlphabetGeneratorTest {
	@Test
	public void generateFrequencyAlphabetTest() {
		// Expect in the training data that Pr(a) = 2/5, Pr(b) = 2/5,
		// Pr(c) = 1/5.
		String[] trainingData = { "aa", "bbc" };
		// In the output for base 10, they should be in the same proportion.
		char[] expectedOutput = { 'a', 'a', 'a', 'a', 'b', 'b', 'b', 'b', 'c',
				'c' };
		assertArrayEquals(expectedOutput,
				AlphabetGenerator.generateFrequencyAlphabet(10, trainingData));
	}

	// example from description
	@Test
	public void generateFrequencyAlphabetTest2() {

		String[] trainingData = new String[3];

		char[] a = new char[302];
		Arrays.fill(a, 'a');
		trainingData[0] = new String(a);

		char[] b = new char[500];
		Arrays.fill(b, 'b');
		trainingData[1] = new String(b);

		char[] c = new char[198];
		Arrays.fill(c, 'c');
		trainingData[2] = new String(c);

		char[] expectedOutput = new char[93];
		Arrays.fill(expectedOutput, 0, 29, 'a');
		Arrays.fill(expectedOutput, 29, 75, 'b');
		Arrays.fill(expectedOutput, 75, 93, 'c');

		assertArrayEquals(expectedOutput,
				AlphabetGenerator.generateFrequencyAlphabet(93, trainingData));
	}

	@Test
	public void generateFrequencyAlphabetTest3() {

		String[] trainingData = { "a single string" };
		char[] expectedOutput = { 'a', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'e',
				'e', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'i',
				'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'l', 'l', 'l',
				'l', 'l', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n',
				'r', 'r', 'r', 'r', 'r', 's', 's', 's', 's', 's', 's', 's',
				's', 's', 's', 't', 't', 't', 't' };

		// base = 64
		assertArrayEquals(expectedOutput,
				AlphabetGenerator.generateFrequencyAlphabet(64, trainingData));
	}

	@Test
	public void generateFrequencyAlphabetTest4() {

		// Expect in the training data that Pr(a) = 1/5, Pr(b) = 1/5,
		// Pr(c) = 1/5, Pr(d) = 1/5, Pr(e) = 1/5.
		String[] trainingData = { "1+1=2", "aabb", "ccdd", "e@@e" };

		/*
		 * expectedOutput = {"a", "a", ... (13 As, indexes 0-12), "b", "b", ...
		 * (13 Bs, indexes 13-25), "c", "c", ... (12 Cs, indexes 26-37), "d",
		 * "d", ... (13 Ds, indexes 38-50), "e", "e", ... (13 Bs, indexes
		 * 51-63)}
		 */

		char[] expectedOutput = { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
				'a', 'a', 'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b',
				'b', 'b', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c',
				'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd', 'd', 'd', 'd',
				'd', 'd', 'd', 'd', 'd', 'd', 'd', 'e', 'e', 'e', 'e', 'e',
				'e', 'e', 'e', 'e', 'e', 'e', 'e' };
		// base = 64
		assertArrayEquals(expectedOutput,
				AlphabetGenerator.generateFrequencyAlphabet(64, trainingData));
	}

}
