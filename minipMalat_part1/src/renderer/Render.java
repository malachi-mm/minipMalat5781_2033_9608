package renderer;

import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;
import java.util.MissingResourceException;

public class Render {

    RayTracerBase rayTracer;
    ImageWriter writer;
    Camera camera;

    /**
     * what features  we want to use in the picture
     */
    boolean useDOF = false;
    boolean useAntiAliasing = false;
    //boolean useGlossySurfaces = false;

    private int threadsNumber = 1;
    private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private boolean print = false; // printing progress percentage

    /**
     * Pixel is an internal helper class
     * whose objects are associated with a Render object that
     * they are generated in scope of.
     * It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects -one in each thread.
     */
    private class Pixel {
        private long maxRows = 0;     // Ny
        private long maxCols = 0;// Nx
        private long pixels = 0;// Total number of pixels: Nx*Ny
        public volatile int row = 0;// Last processed row
        public volatile int col = -1;// Last processed column
        private long counter = 0;// Total number of pixels processed
        private int percents = 0;// Percent of pixels processed
        private long nextCounter = 0;// Next amount of processed pixels for percent progress

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @parammaxRowsthe amount of pixel rows
         * @parammaxColsthe amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            this.maxRows = maxRows;
            this.maxCols = maxCols;
            pixels = maxRows * maxCols;
            nextCounter = pixels / 100;
            if (Render.this.print) System.out.printf("\r %02d%%", percents);
        }

        /***  Default constructor for secondary Pixel objects*/
        public Pixel() {
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @paramtarget targetsecondary Pixel object to copy the row/column of the next pixel
         * @returntrue if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (print && percents > 0)
                System.out.println(percents + "%");
            if (percents >= 0)
                return true;
            if (print)
                System.out.println(100 + "%");
            return false;
        }

        /*** Internal function for thread-safe manipulating of main follow up Pixel object -this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical* section.
         * The function provides next pixel number each call.
         * @paramtarget targetsecondary Pixel object to copy the row/column of the next pixel
         * @returnthe progress percentage for follow up: if it is 0 -nothing to print, if it is -1 -the task is
         * finished, any other value -the progress percentage (only when it changes)*/
        private synchronized int nextP(Pixel target) {
            ++col;
            ++counter;
            if (col < maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (print && counter == nextCounter) {
                    ++percents;
                    nextCounter = pixels * (percents + 1) / 100;
                    return percents;
                }
                return 0;
            }
            ++row;
            if (row < maxRows) {
                col = 0;
                if (print && counter == nextCounter) {
                    ++percents;
                    nextCounter = pixels * (percents + 1) / 100;
                    return percents;
                }
                return 0;
            }
            return -1;
        }
    }
    //***************setters***************************

    public Render setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    public Render setWriter(ImageWriter writer) {
        this.writer = writer;
        return this;
    }


    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    /**
     * @param useDOF whether we want to calculate focus and depth of field
     * @return this according to builder pattern
     */
    public Render setUseDOF(boolean useDOF) {
        this.useDOF = useDOF;
        return this;
    }

    /**
     * sets the size of the aperture
     *
     * @param apertureRadius
     * @return
     */

    public Render setApertureSize(double apertureRadius) {
        camera.setApertureRadius(apertureRadius);
        return this;
    }

    public Render setApertureDistance(double apertureDistance) {
        camera.setApertureDistance(apertureDistance);
        return this;
    }

    public Render setSizeSuperSamplingDOF(int sizeSuperSamplingDOF) {
        camera.setSizeSuperSamplingDOF(sizeSuperSamplingDOF);
        return this;
    }


    public Render setSizeSuperSamplingAntiAliasing(int sizeSuperSampling) {
        camera.setSizeSuperSamplingAntiAliasing(sizeSuperSampling);
        return this;
    }

    /**
     * @param useGlossySurfaces whether we want to calculate glossy surfaces
     * @return this according to builder pattern
     */
    public Render setUseGlossySurfaces(boolean useGlossySurfaces) {
        rayTracer.setUseGlossySurfaces(useGlossySurfaces);
        return this;
    }

    public Render setSizeSuperSamplingGlossySurfaces(int sizeSuperSamplingDOF) {
        rayTracer.setSizeSuperSamplingPart2(sizeSuperSamplingDOF);
        return this;
    }

    public Render setUseAntiAliasing(boolean useAntiEliasing) {
        this.useAntiAliasing = useAntiEliasing;
        return this;
    }

    public Render setUseBounding(Boolean useBounding) {
        Geometries.setUseBounding(useBounding);
        return this;
    }

    public Render buildHierarchy(){
        rayTracer.scene.geometries.buildHierarchy();
        return this;
    }

        /**
         * Set debug printing on
         *
         * @return the Render object itself
         */
    public Render setDebugPrint() {
        print = true;
        return this;
    }

    //*********************************
    public void renderImage() {
        if (camera == null)
            throw new MissingResourceException(camera.getClass().getName(), "missing resource", "1");

        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "3");
        if (rayTracer == null)
            throw new MissingResourceException(rayTracer.getClass().getName(), "missing resource", "4");

        int nX = writer.getNx(), nY = writer.getNy();

        final Pixel thePixel = new Pixel(nX, nY);// Main pixel management object
        Thread[] threads = new Thread[threadsNumber];
        for (int i = threadsNumber - 1; i >= 0; --i) {// create all threads
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel(); // Auxiliary thread’s pixel object
                while (thePixel.nextPixel(pixel)) {
                    writer.writePixel(pixel.col, pixel.row, calcColorInPixel(nX, nY, pixel.col, pixel.row));
                }
            });
        }
        for (Thread thread : threads)
            thread.start(); // Start all the threads
        // Wait for all threads to finish
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (Exception e) {
            }
        if (print) System.out.printf("\r100%%\n"); // Print 100%


    }


    /**
     * @param nX
     * @param nY
     * @param i
     * @param j
     * @return
     */
    private Color calcColorInPixel(int nX, int nY, int i, int j) {
        Color color = Color.BLACK;
        if (useDOF) {
            List<Ray> rays = camera.calcApertureRays(nX, nY, i, j);
            for (Ray ray : rays) {
                Color c = rayTracer.traceRay(ray);
                color = color.add(c);
            }
            color = color.reduce(rays.size());
        } //if using depth of field I assumed that you can't use both antialiasing and DOF on the same picture
        else if (useAntiAliasing) {

            List<Ray> rays = camera.calcAntiAliasingRays(nX, nY, i, j);
            for (Ray ray : rays) {
                Color c = rayTracer.traceRay(ray);
                color = color.add(c);
            }
            color = color.reduce(rays.size());
        }//if using antialiasing
        else //the normal way
            color = rayTracer.traceRay(camera.constructRayThroughPixel(nX, nY, i, j));
        return color;
    }

    public void printGrid(int interval, Color color) {
        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "1");

        for (int i = 0; i < writer.getNx() / 50; i++) {
            for (int j = 0; j < writer.getNy(); j++) {
                writer.writePixel(i * 50, j, new Color(100, 100, 20));
            }
        }
        for (int i = 0; i < writer.getNy() / 50; i++) {
            for (int j = 0; j < writer.getNx(); j++) {
                writer.writePixel(j, i * 50, new Color(100, 100, 20));
            }
        }
    }

    public void writeToImage() {
        if (writer == null)
            throw new MissingResourceException(writer.getClass().getName(), "missing resource", "1");
        writer.writeToImage();
    }

    /**
     * Set multithreading <br>
     * -if the parameter is 0 -number of coressless SPARE (2) is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading must be 0 or higher");
        if (threads != 0)
            this.threadsNumber = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            this.threadsNumber = cores <= 2 ? 1 : cores;
        }
        return this;
    }


}
