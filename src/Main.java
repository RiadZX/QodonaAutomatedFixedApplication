import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(
                List.of("aello", "borld", "bbva", "xrogramming", "language"));

        List<Vertex> graph = LexicographicalOrder(words);
        for(Vertex v : graph) {
            if(hasCycles(v, new HashSet<>(), new HashSet<>())) {
                System.out.println("Impossible");
                return;
            }
        }
        System.out.println("Possible");
    }

    public static List<Vertex> LexicographicalOrder(List<String> words){
        List<Vertex> vertices = getAlphabetVertices(); //Create a graph with 26 vertices.
        for(int i = 0; i < words.size() - 1; i++) {
            String thisWord = words.get(i);
            String nextWord = words.get(i + 1);
            int minLength = Math.min(thisWord.length(), nextWord.length());
            for(int j = 0; j < minLength; j++) {
                char thisWordChar = thisWord.charAt(j);
                char nextWordChar = nextWord.charAt(j);
                if(thisWordChar != nextWordChar) {
                    Vertex v1 = getVertex(vertices, thisWordChar);
                    Vertex v2 = getVertex(vertices, nextWordChar);
                    v1.outgoingEdges.add(v2);
                    break;
                }
            }
        }
        return vertices;
    }

    private static boolean hasCycles(Vertex v, HashSet<Vertex> visited, HashSet<Vertex> recursionStack){
        if(recursionStack.contains(v)) {
            return true;
        }
        if(visited.contains(v)) {
            return false;
        }
        visited.add(v);
        recursionStack.add(v);
        for(Vertex edge : v.getOutgoingEdges()) {
            if(hasCycles(edge, visited, recursionStack)) {
                return true;
            }
        }
        recursionStack.remove(v);
        return false;
    }
    private static void printGraph(List<Vertex> vertices){
        for(Vertex v : vertices) {
            System.out.print(v.getId() + " -> ");
            for(Vertex edge : v.getOutgoingEdges()) {
                System.out.print(edge.getId() + " ");
            }
            System.out.println();
        }
    }


    private static Vertex getVertex(List<Vertex> vertices, char c){
        for(Vertex v : vertices) {
            if(v.getId() == c) {
                return v;
            }
        }
        return null;
    }

    private static List<Vertex> getAlphabetVertices(){
        List<Vertex> vertices = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            vertices.add(new Vertex(c));
        }
        return vertices;
    }


}