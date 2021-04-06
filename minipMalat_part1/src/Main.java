import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;


/**
 *  Achiya Danziger
 * ID:214092033
 * email- achiya.danziger@gmail.com
 *
 * malachy mahpod
 * id:213919608
 * email- mahpod.malachi@gmail.com
 */

/**
 * Test program for the 1st stage
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     * 
     * @param args irrelevant here
     */
    public static void main(String[] args) {

        try { // test zero vector
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);


        // Test operations with points and vectors


        out.println("If there were no any other outputs - all tests succeeded!");
    }
}
