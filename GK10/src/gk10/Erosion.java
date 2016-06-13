package gk10;

public class Erosion {

    public static void grayscaleImage(MyImage img) {

        int width = img.getImageWidth();
        int height = img.getImageHeight();

        //buff
        int buff[];

        //output of erosion
        int output[] = new int[width * height];

        //perform erosion
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buff = new int[9];
                int i = 0;
                for (int ty = y - 1; ty <= y + 1; ty++) {
                    for (int tx = x - 1; tx <= x + 1; tx++) {

                        if (ty >= 0 && ty < height && tx >= 0 && tx < width) {
                            //pixel under the mask
                            buff[i] = img.getRed(tx, ty);
                            i++;
                        }
                    }
                }

                //sort buff
                java.util.Arrays.sort(buff);

                //save lowest value
                output[x + y * width] = buff[9 - i];
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int v = output[x + y * width];
                img.setPixel(x, y, 255, v, v, v);
            }
        }
    }

}//class ends here
