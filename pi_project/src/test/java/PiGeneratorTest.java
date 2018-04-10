import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Test;

public class PiGeneratorTest {
	@Test
	public void basicPowerModTest1() {
		// 5^7 mod 23 = 17
		assertEquals(17, PiGenerator.powerMod(5, 7, 23));

		// 6^181 mod 77 = 6
		assertEquals(6, PiGenerator.powerMod(6, 181, 77));
	}

	@Test
	public void zeroPowerModTest() {
		// 1^0 mod 23 = 1
		assertEquals(1, PiGenerator.powerMod(1, 0, 23));
		// 9^4 mod 23 = 0
		assertEquals(0, PiGenerator.powerMod(0, 4, 23));
		// 5^7 mod 0 = -1
		assertEquals(-1, PiGenerator.powerMod(5, 7, 0));
	}

	@Test
	public void negativeInputPowerModTest() {
		assertEquals(-1, PiGenerator.powerMod(-5, 7, 23));
		assertEquals(-1, PiGenerator.powerMod(5, -7, 23));
		assertEquals(-1, PiGenerator.powerMod(5, 7, -23));
		assertEquals(-1, PiGenerator.powerMod(-5, -7, 23));
		assertEquals(-1, PiGenerator.powerMod(-5, -7, -23));
	}

	@Test
	public void MaxValuetPowerModTest() {

		int answer = powerModTester(Integer.MAX_VALUE, 3, 5);
		assertEquals(answer, PiGenerator.powerMod(Integer.MAX_VALUE, 3, 5));

		answer = powerModTester(3, Integer.MAX_VALUE, 5);
		assertEquals(answer, PiGenerator.powerMod(3, Integer.MAX_VALUE, 5));

		answer = powerModTester(3, 5, Integer.MAX_VALUE);
		assertEquals(answer, PiGenerator.powerMod(3, 5, Integer.MAX_VALUE));
	}

	@Test
	public void computePiInHexTest() {
		int[] pidigits = { 2, 4, 3, 15, 6, 10, 8, 8, 8, 5, 10, 3, 0, 8, 13, 3,
				1, 3, 1, 9, 8, 10, 2, 14, 0, 3, 7, 0, 7, 3, 4, 4, 10, 4, 0, 9,
				3, 8, 2, 2, 2, 9, 9, 15, 3, 1, 13, 0, 0, 8 };
		assertArrayEquals(pidigits, PiGenerator.computePiInHex(50));

		assertNull(PiGenerator.computePiInHex(-5));

		assertArrayEquals(new int[0], PiGenerator.computePiInHex(0));

	}

	@Test
	public void piDigitTest() {

		// pi in hex = 3.243F6A8885A308D3...
		assertEquals(0x02, PiGenerator.piDigit(1));
		assertEquals(0x0F, PiGenerator.piDigit(4));

		int[] pi = new int[] { 0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8, 0x8,
				0x5, 0xA, 0x3, 0x0, 0x8, 0xD, 0x3 };
		assertArrayEquals(pi, PiGenerator.computePiInHex(pi.length));

	}

	private static int powerModTester(final Integer base,
			final Integer exponent, final Integer mod) {
		return new BigInteger(base.toString()).modPow(
				new BigInteger(exponent.toString()),
				new BigInteger(mod.toString())).intValue();

	}

}
