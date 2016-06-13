package gk10;

public class HitMiss {

    public static void binaryImage(MyImage img, int mask[], int maskSize) {

        int width = img.getImageWidth();
        int height = img.getImageHeight();

        int output[] = new int[width * height];

        //perform hit-miss
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean flag = false;   //this will be set if mismatch is found
                for (int ty = y - (maskSize / 2), mr = 0; ty <= y + (maskSize / 2) && flag == false; ty++, mr++) {
                    for (int tx = x - (maskSize / 2), mc = 0; tx <= x + (maskSize / 2) && flag == false; tx++, mc++) {
                        //pixel under the mask
                        if (ty >= 0 && ty < height && tx >= 0 && tx < width) {

                            //for don't-care value in mask
                            if (mask[mc + mr * maskSize] > 1) {
                                continue;
                            }

                            //if image pixel not same as mask
                            if (img.getRed(tx, ty) != (mask[mc + mr * maskSize] * 255)) {
                                flag = true;
                                output[x + y * width] = 0;  //BLACK
                                break;
                            }
                        }
                    }
                }
                if (flag == false) {
                    //all pixels of image under the mask has matched
                    output[x + y * width] = 255;    //FOREGROUND COLOR WHITE
                }
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
