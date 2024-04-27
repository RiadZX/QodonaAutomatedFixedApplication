package assignment2;

import java.util.List;
import java.util.Set;

public record Problem(
        String hash,
        Set<String> data
) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Problem)) return false;
        Problem other = (Problem) obj;
        return hash.equals(other.hash) && data.equals(other.data);
    }
}
