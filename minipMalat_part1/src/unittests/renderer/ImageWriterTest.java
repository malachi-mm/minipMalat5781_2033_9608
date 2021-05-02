package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test ImageWriter
 */
class ImageWriterTest {

    /**
     * testing the final picture
     */
    @Test
    void imageTest() {
        ImageWriter im=new ImageWriter("test1",16*50,10*50);

       for (int i = 0; i < 16*50; i++) {
            for (int j = 0; j < 10*50; j++) {
                im.writePixel(i,j,new Color(0,200,6));
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10*50; j++) {
                im.writePixel(i*50,j,new Color(255,33,20));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16*50; j++) {
                im.writePixel(j,i*50,new Color(255,33,20));
            }
        }
        im.writeToImage();
    }

}