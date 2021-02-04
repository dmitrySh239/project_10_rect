package Gui;

import com.jogamp.opengl.util.FPSAnimator;
import problem.Problem;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;



class RendererGL implements GLEventListener {

    private final GLCanvas canvas;

    private FPSAnimator animator;

    final Problem problem;


    RendererGL() {
        // creating OpenGL-profile
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // creating drawing space
        this.canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        problem = new Problem();
    }


    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_3D);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        animator = new FPSAnimator(canvas, 25, true);
        animator.start();
    }


    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f); // Set background color to black and opaque
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);         // Clear the color buffer (background)
        problem.render(gl);
        gl.glFlush();
    }



    GLCanvas getCanvas() {
        return canvas;
    }


    void close() {
        animator.stop();
        System.out.println("terminated");
    }


    @Override
    public void dispose(GLAutoDrawable drawable) {
        animator.stop();
    }

    /**
     * @param drawable drawing object OpenGL
     * @param posX     X - posiiition on OX axe
     * @param posY     Y - posiiition on OY axe
     * @param width    window's width
     * @param height   window's height
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int posX, int posY, int width, int height) {

    }


}