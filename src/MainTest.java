import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class MainTest {
    //not actual tests.
    //just to see if it works or not during developing.
    //these are supposed to be "manual" tests.
    @Test
    void lexicographicalOrder() {
        List<String> words = new ArrayList<>();
        words.add("alex");
        words.add("dwa");
        words.add("as");
        words.add("wdaffff");
        Main.Solution(words);
    }
}