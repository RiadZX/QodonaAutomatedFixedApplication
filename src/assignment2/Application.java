package assignment2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import org.json.*;
public class Application {
    public Application(){
    }

    public Set<Problem> readProblems(String path) throws IOException {
        Path filePath = Path.of(path);
        String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(fileContent);
        JSONArray problems = jsonObject.getJSONArray("problems");
        Set<Problem> problemList = new HashSet<>();
        for (int i = 0; i < problems.length(); i++) {
            JSONObject problem = problems.getJSONObject(i);
            List<String> data = problem.getJSONArray("data").toList()
                    .stream().map(
                            Object::toString
                    ).toList();

            String hash = problem.getString("hash");
            Problem newProblem = new Problem(hash, new HashSet<>(data));
            problemList.add(newProblem);
        }
        return problemList;
    }
    public void writeProblems(Set<Problem> problems, String path) throws IOException {
        JSONObject jsonObject = new JSONObject();
        JSONArray problemsArray = new JSONArray();
        for (Problem problem : problems) {
            JSONObject problemObject = new JSONObject();
            problemObject.put("hash", problem.hash());
            problemObject.put("data", new JSONArray(problem.data()));
            problemsArray.put(problemObject);
        }
        jsonObject.put("problems", problemsArray);
        Files.writeString(Path.of(path), jsonObject.toString(), StandardCharsets.UTF_8);
    }

    /**
     * Returns new set z where z= set1-set2
     * @param set1 Set of problems
     * @param set2 Set of problems
     * @return Set of problems z
     */
    public Set<Problem> setMinus(Set<Problem> set1, Set<Problem> set2) {
        Set<Problem> z = new HashSet<>(set1);
        z.removeAll(set2);
        return z;
    }

    public Set<Problem> intersection(Set<Problem> x, Set<Problem> y) {
        Set<Problem> z = new HashSet<>(x);
        z.retainAll(y);
        return z;
    }

}
