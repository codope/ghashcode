package hashcode2019;

import java.util.Set;

public class Photo implements Comparable<Photo> {
    private int id;
    private Set<String> tags;
    private Orientation orientation;
    private boolean isUsed;
    private int weight;

    public Photo(int id, Set<String> tags, String orientation, boolean isUsed) {
        this.id = id;
        this.tags = tags;
        this.orientation = orientation.equals("H") ? Orientation.HORIZONTAL: Orientation.VERTICAL;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Override
    public int compareTo(Photo o) {
        int diff = o.tags.size() - this.tags.size();
        return diff == 0 ? o.weight - this.weight : diff;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
