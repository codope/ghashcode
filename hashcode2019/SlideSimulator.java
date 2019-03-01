package hashcode2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SlideSimulator {

    String fileName;
    Map<String, Integer> tagFreqMap;

    public SlideSimulator(String fileName) {
        this.fileName = fileName;
    }

    public void parseFile() {
        BufferedReader br = null;
        FileReader fr = null;
        List<Photo> photoList = new ArrayList<>();
        int count = 0;
        try {
            fr = new FileReader(this.fileName + ".txt");
            br = new BufferedReader(fr);
            String firstLine = br.readLine();
            int n = Integer.parseInt(firstLine);
            String line;
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                if (line != null) {
                    Photo photo = parseInput(line, count++);
                    photoList.add(photo);
                }
            }
            buildMap(photoList);
            for (Photo photo : photoList) {
                setPhotoWeight(photo);
            }

            Collections.sort(photoList);

            List<Slide> slides = new ArrayList<>();
            Slide s0 = new Slide();
            s0.setPhotos(new ArrayList<>(Collections.singleton(photoList.get(0))));
            s0.setTags(photoList.get(0).getTags());
            slides.add(s0);
            Slide prevSlide = s0;
            for (int i = 1; i < photoList.size(); i++) {
                int interest = minInterest(prevSlide.getPhotos(), photoList.get(i));
                if (interest > 0) {
                    Slide s1 = new Slide();
                    s1.setPhotos(new ArrayList<>(Collections.singleton(photoList.get(i))));
                    s1.setTags(photoList.get(i).getTags());
                    if (prevSlide.getPhotos().get(0).getOrientation() == Orientation.VERTICAL) {
                        if (s1.getPhotos().get(0).getOrientation() == Orientation.VERTICAL) {
                            prevSlide.getPhotos().add(s1.getPhotos().get(0));
                        } else {
                            slides.remove(prevSlide);
                            slides.add(s1);
                            prevSlide = s1;
                        }
                    } else {
                        slides.add(s1);
                        prevSlide = s1;
                    }
                }
            }

            writeOutput(slides);

        } catch (IOException e) {
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int minInterest(List<Photo> p1, Photo p2) {
        int common = 0;
        Set<String> p1Tags = p1.get(0).getTags();
        if (p1.size() > 1) {
            p1Tags.addAll(p1.get(1).getTags());
        }
        Set<String> p2Tags = p2.getTags();

        for (String tag : p1Tags) {
            if (p2Tags.contains(tag)) {
                common++;
            }
        }

        int p1Prime = p1Tags.size() - common;
        int p2Prime = p2Tags.size() - common;

        return Math.min(common, Math.min(p1Prime, p2Prime));
    }

    public Photo parseInput(String line, int count) {
        String[] parts = line.split("\\s+");
        int n = parts.length;
        int id = count;
        String orientation = parts[0];
        int m = Integer.parseInt(parts[1]);
        Set<String> tags = new HashSet<>();
        for (int j = 2; j < m + 2; j++) {
            String tag = parts[j];
            tags.add(tag);
        }
        return new Photo(id, tags, orientation, false);
    }

    public void buildMap(List<Photo> photos) {
        tagFreqMap = new HashMap<>();
        for (Photo photo : photos) {
            for (String tag : photo.getTags()) {
                if (!tagFreqMap.containsKey(tag)) {
                    tagFreqMap.put(tag, 1);
                } else {
                    int currFreq = tagFreqMap.get(tag);
                    tagFreqMap.put(tag, currFreq + 1);
                }
            }
        }
    }

    public void setPhotoWeight(Photo photo) {
        int totalWeight = 0;
        for (String tag : photo.getTags()) {
            totalWeight += tagFreqMap.get(tag);
        }
        photo.setWeight(totalWeight);
    }

    public void writeOutput(List<Slide> slides) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(this.fileName + ".out", StandardCharsets.UTF_8);

            int noOfSlides = slides.size();

            writer.print(noOfSlides);
            writer.println();

            for (Slide slide : slides) {
                writer.print(slide.getPhotos().get(0).getId());
                if (slide.getPhotos().size() > 1) {
                    writer.print(" " + slide.getPhotos().get(1).getId());
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
