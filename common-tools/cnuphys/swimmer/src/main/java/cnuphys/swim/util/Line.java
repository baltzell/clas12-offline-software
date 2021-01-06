package cnuphys.swim.util;

import static cnuphys.swim.util.VectorSupport.createVector;
import static cnuphys.swim.util.VectorSupport.cross;
import static cnuphys.swim.util.VectorSupport.diff;
import static cnuphys.swim.util.VectorSupport.length;

/**
 * A line is defined by the a point _p and a direction _u
 * @author devita
 *
 */
public class Line {
	
	
	
	//effectively zero
	private static double TINY = 1.0e-10;

	//the line constants
	private double[] _p = new double[3];
	private double[] _u = {0,0,1};
	
	//use to compute distance to the line
	private double _distDenom = Double.NaN;

	/**
	 * Create a line from thee point and direction coordinates
	 * @param a
	 * @param b
	 * @param c
	 * @param d
         * @param e
         * @param f
	 */
	public Line(double a, double b, double c, double d, double e, double f) {
		_p[0] = a;
		_p[1] = b;
		_p[2] = c;
		_u[0] = d;
		_u[1] = d;
                _u[2] = f;
	}
	
	/**
	 * Create a plane from a  vector and a point on the line 
         * @param p   a point on the line where the components (0, 1, 2) map to (x, y, z)
	 * @param u   a vector describing the line direction where the components (0, 1, 2) map to (x, y, z)
	 * @return the line that contains p and its along u
	 */
	public static Line createPlane(double p[], double[] u) {
		
		return new Line(p[0], p[1], p[2], u[0], u[1], u[2]);
	}
	
	
	/**
	 * See if a point is contained (on) a plane
	 * @param x x coordinate 
	 * @param y y coordinate
	 * @param z z coordinate
	 * @param tolerance the maximum distance from the plane in the same units as the coordinates
	 * @return
	 */
	public boolean contained(double x, double y, double z, double tolerance) {
		return distanceToLine(x, y, z) < tolerance;
	}
	
	/**
	 * Get the perpendicular distance for a point to the plane
	 * @param x the x coordinate of the point.
	 * @param y the y coordinate of the point.
	 * @param z the z coordinate of the point.
	 * @return the perpendicular distance
	 */
	public double distanceToLine(double x, double y, double z) {
		if (Double.isNaN(_distDenom)) {
			_distDenom = length(_u);
		}
		double[] d = diff(createVector(x,y,z),_p);                
		return length(cross(d,_u))/_distDenom;
	}
	
	
	
	public static void main(String arg[]) {
            Line line = new Line(0,0,5,-1,0,1);
            System.out.println(line.distanceToLine(5, 0, 5));
	}

}
