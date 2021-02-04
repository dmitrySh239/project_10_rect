package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.*;
/*import java.util.ArrayList;
import java.util.Scanner;*/


public class Problem {
    /**
     * task
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "Заданы два множества точек в пространстве.\n" +
            "Требуется построить пересечения и разность этих множеств";


    public static final String PROBLEM_CAPTION = "Итоговый проект ученика 10-7 Иванова Ивана";


    private static final String FILE_NAME = "points.txt";

    /**
     * points
     */
    private ArrayList<Point> points;

    /**
     * constructor
     */
    public Problem() {
        points = new ArrayList<>();
    }

    /**
     * Добавить точку
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param setVal number of point
     */
    public void addPoint(double x, double y, int setVal) {
        Point point = new Point(x, y, setVal);
        points.add(point);
    }

    /**
     * solving problem
     */
    public void solve() {
        for (Point p : points) {
            for (Point p2 : points) {
                // iff points different
                if (p != p2) {
                    // if y1 == y2
                    if (Math.abs(p.x - p2.x) < 0.0001 && Math.abs(p.y - p2.y) < 0.0001) {
                        p.isSolution = true;
                        p2.isSolution = true;
                    }
                }
            }
        }
    }

    /**
     *
     */
    public void loadFromFile() {
        points.clear();
        try {
            File file = new File(FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                int setVal = sc.nextInt();
                sc.nextLine();
                Point point = new Point(x, y, setVal);
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    /**
     * save task in file
     */
    public void saveToFile() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
            for (Point point : points) {
                out.printf("%.2f %.2f %d\n", point.x, point.y, point.setNumber);
            }
            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    /**
     * add random points     *
     * @param n - number of points
     */
    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            Point p = Point.getRandomPoint();
            points.add(p);
        }
    }

    /**
     * clear task
     */
    public void clear() {
        points.clear();
    }

    /**
     * drawing task
     *
     * @param gl - OpenGL for drawing
     */
    public void render(GL2 gl) {
        for (Point point : points) {
            point.render(gl);
        }
    }
}
