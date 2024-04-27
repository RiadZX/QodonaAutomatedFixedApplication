package assignment2;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String file1 = scanner.nextLine();
        String file2 = scanner.nextLine();
        String output1 = scanner.nextLine();
        String output2 = scanner.nextLine();
        String output3 = scanner.nextLine();

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
