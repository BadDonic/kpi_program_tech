import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import piwords.WordFinder;

public class WordFinderTest {
	@Test
	public void basicGetSubstringsTest() {
		String haystack = "abcde";
		String[] needles = { "ab", "abc", "de", "fg" };

		Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
		expectedOutput.put("ab", 0);
		expectedOutput.put("abc", 0);
		expectedOutput.put("de", 3);

		assertEquals(expectedOutput, WordFinder.findWords(haystack, needles));
	}

	// haystack is empty
	@Test
	public void emptyHaystackTest() {
		String haystack = "";
		String[] needles = { "ab", "abc", "de", "fg" };

		Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

		assertEquals(expectedOutput, WordFinder.findWords(haystack, needles));
	}

	@Test
	public void nullInputsTest() {
		String haystack = "abcde";
		String[] needles = { "ab", "abc", "de", "fg" };

		Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

		assertEquals(expectedOutput, WordFinder.findWords(null, needles));
		assertEquals(expectedOutput, WordFinder.findWords(haystack, null));
	}

	// repeated words
	@Test
	public void RepeatedNeedlesTest() {
		String haystack = "abababc";
		String[] needles = { "ab", "abc", "de", "fg", "ab", "abc" };
		Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
		expectedOutput.put("ab", 0);
		expectedOutput.put("abc", 4);

		assertEquals(expectedOutput, WordFinder.findWords(haystack, needles));
	}

	// Needle is longer than haystack
	@Test
	public void longNeedleTest() {
		String haystack = "abcde";
		String[] needles = { "abcdefgh", "ab", "abc", "de", "fg" };

		Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
		expectedOutput.put("ab", 0);
		expectedOutput.put("abc", 0);
		expectedOutput.put("de", 3);

		assertEquals(expectedOutput, WordFinder.findWords(haystack, needles));
	}

	// Special chars
	@Test
	public void specialCharTest() {
		String haystack = "~!@#$%^&*()";
		String[] needles = { "~!", "~!@#$", "%^&", "fg" };

		Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
		expectedOutput.put("~!", 0);
		expectedOutput.put("~!@#$", 0);
		expectedOutput.put("%^&", 5);

		assertEquals(expectedOutput, WordFinder.findWords(haystack, needles));
	}
}
