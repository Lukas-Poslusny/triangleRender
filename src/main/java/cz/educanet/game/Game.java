package cz.educanet.game;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Game {

    private static final float[] vertices = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };
    private static int vboId;
    private static int vaoId;
    private static double increase = 0.00025;

    public static void init(long window) {
        // setup shaders
        Shaders.initShaders();

        vaoId = GL33.glGenVertexArrays();
        int vaoId2 = GL33.glGenVertexArrays();
        vboId = GL33.glGenBuffers();

        GL33.glBindVertexArray(vaoId);
        {
            GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
            {
                FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                        .put(vertices)
                        .flip();

                GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
                GL33.glVertexAttribPointer(0,3, GL33.GL_FLOAT, false, 0, 0);
                GL33.glEnableVertexAttribArray(0);

                MemoryUtil.memFree(fb); // Clear the buffer from memory
            }
        }
    }

    public static void render(long window) {
        GL33.glUseProgram(Shaders.shaderId); // use this shader to render
        GL33.glBindVertexArray(vaoId);
        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, vertices.length / 3);
    }

    public static void update(long window) {
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS) {
            GL33.glBindVertexArray(vaoId);
            {
                GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vaoId);
                if (vertices[0] >= 1.0f) { // if triangle goes offscreen to the right, push it back left
                    vertices[0] = -1.0f;
                    vertices[3] = -0.0f;
                    vertices[6] = -0.0f;
                }
                vertices[3] += increase;
                vertices[0] += increase;
                vertices[6] += increase;

                FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                        .put(vertices)
                        .flip();
                GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
            }

            GL33.glBindVertexArray(vboId);
            {
                GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
                if (vertices[9] >= 1.0f) { // if triangle goes offscreen loop it from the left
                    vertices[9] = -1.0f;
                    vertices[12] = -1.0f;
                    vertices[15] = -0.0f;
                }
                vertices[9] += increase;
                vertices[15] += increase;
                vertices[12] += increase;

                FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                        .put(vertices)
                        .flip();

                GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);

            }
        }


    }
}
