import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DigitsToStringConverterTest {
	@Test
	public void toLetterTest() {
		// Input is a 4 digit number, 0.123 represented in base 4
		int[] input = { 0, 1, 2, 3 };

		// Want to map 0 -> "d", 1 -> "c", 2 -> "b", 3 -> "a"
		char[] alphabet = { 'd', 'c', 'b', 'a' };

		String expectedOutput = "dcba";
		assertEquals(expectedOutput,
				DigitsToStringConverter.convert(input, 4, alphabet));

	}

	@Test
	public void basicNumberSerializerTest() {
		// Input is a 4 digit number represented in base 10
		int[] input10 = { 6, 7, 0, 3 };

		char[] alphabet10 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		String expectedOutput = "6703";
		assertEquals(expectedOutput,
				DigitsToStringConverter.convert(input10, 10, alphabet10));

		int[] input16 = { 10, 9, 15, 3 };

		char[] alphabet16 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		String expectedOutput16 = "A9F3";
		assertEquals(expectedOutput16,
				DigitsToStringConverter.convert(input16, 16, alphabet16));

	}

	public void invalidInputTest() {
		// Invalid input
		int[] input = { 0, -11, 2, 3 };
		char[] alphabet = { 'd', 'c', 'b', 'a' };

		// digits[i] < 0
		assertNull(DigitsToStringConverter.convert(input, 4, alphabet));
		// digits[i] >= base
		input[1] = 11;
		assertNull(DigitsToStringConverter.convert(input, 4, alphabet));

		// alphabet.length != base
		input[1] = 2;
		assertNull(DigitsToStringConverter.convert(input, 7, alphabet));

	}

}
