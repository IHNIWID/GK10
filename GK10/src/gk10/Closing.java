package gk10;

public class Closing {

    public static void grayscaleImage(MyImage img) {
        Dilation.grayscaleImage(img);
        Erosion.grayscaleImage(img);
    }
}//class ends here
