package problem;

import java.util.Scanner;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;

public class Point {
    /**
     * constant of set 1
     */
    public static final int SET_1 = 0;
    /**
     * constant of set 2
     */
    public static final int SET_2 = 1;
    /**
     * number of point
     */
    int setNumber;


    boolean isSolution = false;
    double x;
    double y;

    /**
     * point constructor
     *
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param setNumber number of point's set
     */
    Point(double x, double y, int setNumber) {
        this.x = x;
        this.y = y;
        this.setNumber = setNumber;
    }

    /**
     * get random point
     *
     * @return random point
     */
    static Point getRandomPoint() {
        Random r = new Random();
        double nx = (double) r.nextInt(50) / 25 - 1;
        double ny = (double) r.nextInt(50) / 25 - 1;
        int nSetVal = r.nextInt(2);
        return new Point(nx, ny, nSetVal);
    }

    /**
     * drawing point
     *
     * @param gl - OpenGl for drawing
     */
    void render(GL2 gl) {
        if (isSolution)
            gl.glColor3d(1.0, 0.0, 0.0);
        else
            switch (setNumber) {
                case Point.SET_1:
                    gl.glColor3d(0.0, 1.0, 0.0);
                    break;
                case Point.SET_2:
                    gl.glColor3d(0.0, 0.0, 1.0);
                    break;
            }
        gl.glPointSize(3);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
        gl.glPointSize(1);
    }


    @Override
    public String toString() {
        return "Точка с координатами: {" + x + "," + y + "} из множества: " + setNumber;
    }
}
