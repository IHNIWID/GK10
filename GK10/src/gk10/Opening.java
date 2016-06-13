package gk10;

public class Opening {

    public static void grayscaleImage(MyImage img) {
        Erosion.grayscaleImage(img);
        Dilation.grayscaleImage(img);
    }
}//class ends here
