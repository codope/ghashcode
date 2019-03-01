package hashcode2019;

import java.util.List;
import java.util.Set;

public class Slide {
    private List<Photo> photos;
    private Set<String> tags;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
