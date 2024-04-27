import java.util.ArrayList;
import java.util.List;

class Vertex {

    List<Vertex> outgoingEdges;

    char id;

    public Vertex(char id) {
        this.outgoingEdges = new ArrayList<>();
        this.id = id;
    }

    public List<Vertex> getOutgoingEdges() {
        return outgoingEdges;
    }

    public char getId() {
        return id;
    }

    public String toString() {
        return Integer.toString(id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}