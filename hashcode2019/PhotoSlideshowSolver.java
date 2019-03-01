package hashcode2019;

public class PhotoSlideshowSolver {

    public static void main(String[] args) {
        String[] inputs = {"a_example", "b_lovely_landscapes", "c_memorable_moments", "d_pet_pictures", "e_shiny_selfies"};
        for (String input : inputs) {
            SlideSimulator slideSimulator = new SlideSimulator("data/photos/" + input);
            slideSimulator.parseFile();
        }
    }
}
