import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import piwords.BaseTranslator;

public class BaseTranslatorTest {
	@Test
	public void simpleTranslatorTest() {
		// Expect that .01 in base-2 is .25 in base-10
		// (0 * 1/2^1 + 1 * 1/2^2 = .25)
		int[] input = { 0, 1 };
		int[] expectedOutput = { 2, 5 };
		assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 10, 2));
	}

	@Test
	public void invalidInputTests() {
		int[] empty = {};
		int[] emptyExpected = {};
		assertArrayEquals(emptyExpected, BaseTranslator.convertBase(empty, 2, 10, 2));

		// invalid baseA, baseB, precisionB
		int[] input = { 7, 5 };
		assertNull(BaseTranslator.convertBase(input, 1, 10, 2));
		assertNull(BaseTranslator.convertBase(input, 2, 10, 0));
		assertNull(BaseTranslator.convertBase(input, 2, 1, 2));
		assertNull(BaseTranslator.convertBase(null, 2, 10, 2));

		// invalid input array
		int[] pi10 = { 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6 };
		assertNull(BaseTranslator.convertBase(pi10, 2, 16, 5));
		pi10[2] = -pi10[2];
		assertNull(BaseTranslator.convertBase(pi10, 10, 16, 5));

	}

	@Test
	public void binToOtherTests() {
		// 0b0.01111 = 0.132 (base 4) = 0.36 (base 8) = 0.612ac79461 (base 13) =
		// 0x0.78
		int[] input = { 0, 1, 1, 1, 1 };
		int[] base4 = { 1, 3, 2 };
		int[] base8 = { 3, 6 };
		int[] base13 = { 6, 1, 2, 10, 12, 7 };
		assertArrayEquals(input, BaseTranslator.convertBase(input, 2, 2, input.length));
		assertArrayEquals(base4, BaseTranslator.convertBase(input, 2, 4, base4.length));
		assertArrayEquals(base8, BaseTranslator.convertBase(input, 2, 8, base8.length));
		assertArrayEquals(base13, BaseTranslator.convertBase(input, 2, 13, base13.length));

	}

	@Test
	public void decToOtherTranslatorTest() {

		// 3.1415926535897932384626 -> 3.243F6A8885A
		int[] pi10 = { 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9, 3, 2, 3, 8, 4, 6, 2, 6 };
		int[] base16 = { 2, 4, 3, 15, 6, 10, 8, 8, 8, 5, 10 };
		assertArrayEquals(base16, BaseTranslator.convertBase(pi10, 10, 16, 11));

		int[] base2 = { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1 };
		assertArrayEquals(base2, BaseTranslator.convertBase(pi10, 10, 2, 14));

		int[] base3 = { 0, 1, 0, 2, 1, 1, 0 };
		assertArrayEquals(base3, BaseTranslator.convertBase(pi10, 10, 3, 7));

		int[] base9 = { 1, 2, 4, 1, 8, 8 };
		assertArrayEquals(base9, BaseTranslator.convertBase(pi10, 10, 9, 6));

	}

}
