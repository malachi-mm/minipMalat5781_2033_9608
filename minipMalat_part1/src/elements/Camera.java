package elements;

import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.*;

public class Camera {
    final Point3D p0;
    final Vector vTo;
    final Vector vUp;
    final Vector vRight;
    double distance;

    public Camera(Point3D p0, Vector vto, Vector vup) {
        this.p0 = p0;
        if(isZero(vto.dotProduct(vup)))
            throw new IllegalArgumentException("");
        this.vTo = vto;
        this.vUp = vup;
        vRight=vto;////////////
    }
}
