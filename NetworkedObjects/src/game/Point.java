package game;


/**
 * 
 * Models a point in 2-D space. 
 *
 * <p>
 * You may find that you need to modify the code in this class, or add
 * members/methods; feel free, but of course be sure to document. (For example,
 * what if you need to generate a random (x,y) value?)
 * 
 * @author Scott Dexter
 *
 */
class Point implements Cloneable {
	double x, y;

	public Point(double inX, double inY) {
		x = inX;
		y = inY;
	}
	
	/** 
	 * A little bit of magic to make it easy to copy Points.
	 * 
	 * This is slightly advanced Java; don't worry about grasping the details!
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Point clone() {
		try {
			return (Point) super.clone();
		} catch (CloneNotSupportedException e) {
			//  Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}