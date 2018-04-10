package pi_project;


public class AlphabetGenerator {
	/**
	 * Given a numeric base, return a char[] that maps every digit that is
	 * representable in that base to a lower-case char.
	 * 
	 * This method will try to weight each character of the alphabet
	 * proportional to their occurrence in words in a training set.
	 * 
	 * The letters should occur in lexicographically ascending order in the
	 * returned array. - {"a", "b", "c", "c", "d"} is a valid output. - {"b",
	 * "c", "c", "d", "a"} is not.
	 * 
	 * If base >= 0, the returned array should have length equal to the size of
	 * the base.
	 * 
	 * If base < 0, return null.
	 * 
	 * If a String of trainingData has any characters outside the range a-z,
	 * ignore those characters and continue.
	 * 
	 * @param base
	 *            A numeric base to get an alphabet for.
	 * @param trainingData
	 *            The training data from which to generate frequency counts.
	 *            This array is not mutated.
	 * @return A char[] that maps every digit of the base to a char that the
	 *         digit should be translated into.
	 */
	public static char[] generateFrequencyAlphabet(int base,
			String[] trainingData) {

		return null;
	}
}
