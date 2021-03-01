package cz.educanet.game;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

public class Main {
    public static void main(String[] args) throws Exception {
        // Window init
        GLFW.glfwInit();
        // Use opengl v3.3
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);

        long window = GLFW.glfwCreateWindow(800, 600, "Eye surgery", 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new Exception("Unable to open window");
        }
        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities();
        GL33.glViewport(0, 0, 800, 600);

        float r = 0;
        float g = 0;
        float b = 0;

        boolean goingup = true;
        Game.init(window);
        while (!GLFW.glfwWindowShouldClose(window)) {

            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_UP) == GLFW.GLFW_PRESS) {

                if(r >= 1)
                    goingup = true;
                else if (r <= 0) {
                    goingup = false;
                }
                if (goingup) {
                    r += 0.0002;
                    g += 0.0002;
                    b += 0.0002;
                }
                else {
                    r -= 0.0002;
                    g -= 0.0002;
                    b -= 0.0002;
                }
                System.out.println("r: " + r + "  g: " + g + "  b: " + b);

                GL33.glClearColor(r, g, b, 100);
            }
            else {
                // change background color
                GL33.glClearColor(0f, 0f, 0f, 1f);
                // clear previous color
            }
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            // Escape on "esc" keypress
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }

            // Update
            // Render


            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }
}
