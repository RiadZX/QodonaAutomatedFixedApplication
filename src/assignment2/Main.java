package assignment2;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String file1 = "./src/assignment2/BugbanExample.json";
        String file2 = "./src/assignment2/BugbanExample2.json";

        String output1 = "./src/assignment2/BugbanExampleOutput1.json";
        String output2 = "./src/assignment2/BugbanExampleOutput2.json";
        String output3 = "./src/assignment2/BugbanExampleOutput3.json";

        Set<Problem> x;
        Set<Problem> y;
        Application app = new Application();
        try {
            x = app.readProblems(file1);
            y = app.readProblems(file2);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Set<Problem> z = app.setMinus(x, y);
        Set<Problem> w = app.setMinus(y, x);
        Set<Problem> i = app.intersection(x,y);

        try {
            app.writeProblems(z, output1);
            app.writeProblems(w, output2);
            app.writeProblems(i, output3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
