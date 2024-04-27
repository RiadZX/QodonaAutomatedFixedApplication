package assignment1;

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
        words.add("alex%0");
        words.add("dwa%3");
        words.add("as%1");
        words.add("wdaffff%2");
        Main.Solution(words);
    }
}