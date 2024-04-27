package assignment1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        int n = new Scanner(System.in).nextInt();
        for (int i = 0; i < n; i++) {
            words.add(new Scanner(System.in).next());
        }
        Solution(words);
    }

    private static List<String> sortWords(List<String> words) {
        //remove the %i from each word, and sort based on the i.
        List<String> sortedWords = new ArrayList<>();
        for(int i = 0; i < words.size(); i++) {
            sortedWords.add("");
        }
        for(String word : words) {
            int i = Integer.parseInt(word.split("%")[1]);
            sortedWords.set(i, word.split("%")[0]);
        }
        return sortedWords;
    }

    public static void Solution(List<String> words) {
        words = sortWords(words);
        List<Vertex> graph = LexicographicalOrder(words);
        for(Vertex v : graph) {
            if(hasCycles(v, new HashSet<>(), new HashSet<>())) {
                System.out.println("Impossible");
                return;
            }
        }
        System.out.println(printTopologicalOrder(graph));
    }

    private static String printTopologicalOrder(List<Vertex> graph) {
        //use priority q to get the smallest outgoing edge each time.
        PriorityQueue<Vertex> q = new PriorityQueue<>(
                Comparator.comparingInt((Vertex v) -> getIncomingEdges(graph, v.getId()).size()));
        List<Vertex> orderedVertices = new ArrayList<>();
        // Add all vertices with no outgoing edges to the q.
        for(Vertex v : graph) {
            if(getIncomingEdges(graph, v.getId()).isEmpty()) {
                q.add(v);
            }
        }
        while(!q.isEmpty()){
            Vertex v = q.poll();
            orderedVertices.add(v);
            //now we need to remove, all edges from vertex v. and if one of them has no incoming edges, add it to the q.
            while(!v.getOutgoingEdges().isEmpty()) {
                Vertex neighbour = v.getOutgoingEdges().getFirst();
                v.getOutgoingEdges().remove(neighbour);
                List<Vertex> incomingEdges = getIncomingEdges(graph, neighbour.getId());
                if(incomingEdges.isEmpty()) {
                    q.add(neighbour);
                }
            }

        }
        StringBuilder result = new StringBuilder();
        for(Vertex v : orderedVertices) {
            result.append(v.getId());
        }
        return result.toString();
    }

    private static List<Vertex> getIncomingEdges(List<Vertex> vertices, char c){
        List<Vertex> incomingEdges = new ArrayList<>();
        for(Vertex v : vertices) {
            for(Vertex edge : v.getOutgoingEdges()) {
                if(edge.getId() == c) {
                    incomingEdges.add(v);
                }
            }
        }
        return incomingEdges;

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