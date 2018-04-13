import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
class FSMTest {
    private static SwitchFSM switchFSM;
    private static TransTableFSM transTableFSM;
    private static StateFSM stateFSM;
    @BeforeAll
    static void init() {
        switchFSM = new SwitchFSM();
        transTableFSM = new TransTableFSM();
        stateFSM = new StateFSM();
    }
    static class FSMProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of("-567AAAAAAAAAAAAAAAA-", true),
                    Arguments.of("-567AG-", true),
                    Arguments.of("-55656-ASDASD", false),
                    Arguments.of("-555123- ", false),
                    Arguments.of(" ", false),
                    Arguments.of("--", false),
                    Arguments.of("-567A-", true)
            );
        }
    }
    private static Stream<String> provideInCorrectString() {
        return Stream.of("+--", "++5-", "+5515-");
    }

    private static Stream<String> provideCorrectString() { return Stream.of("+58999-", "-5665656561-", "+567123123123123-"); }

    @ParameterizedTest
    @ValueSource(strings = {"+5-", "-6-"})
    @MethodSource("provideCorrectString")
    @CsvFileSource(resources = "CorrectStrings.csv")
    void TestFSMScanInCorrectStrings(String str) {
        assertTrue(switchFSM.scan(str));
        assertTrue(transTableFSM.scan(str));
        assertTrue(stateFSM.scan(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "i", "+-", "+4-","-5G-d"})
    @CsvFileSource(resources = "InCorrectStrings.csv")
    @MethodSource("provideInCorrectString")
    void TestFSMScanInNotCorrectStrings(String str) {
        assertFalse(switchFSM.scan(str));
        assertFalse(transTableFSM.scan(str));
        assertFalse(stateFSM.scan(str));
    }

    @ParameterizedTest
    @ArgumentsSource(FSMProvider.class)
    void TestFSMScanWithMyArguments(String str, boolean res) {
        assertEquals(res, switchFSM.scan(str));
        assertEquals(res, transTableFSM.scan(str));
        assertEquals(res, stateFSM.scan(str));
    }
}