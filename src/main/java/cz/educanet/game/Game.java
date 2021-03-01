package cz.educanet.game;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Game {

    private static final float[] verticies = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0,5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };
    private static int vboId;
    private static int vaoId;

    public static void init(long window) {
        vaoId = GL33.glGenVertexArrays();
        int vaoId2 = GL33.glGenVertexArrays();
        vboId = GL33.glGenBuffers();

        GL33.glBindVertexArray(vaoId);
        {
            GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
            {
                FloatBuffer fb = BufferUtils.createFloatBuffer(verticies.length)
                        .put(verticies)
                        .flip();

                GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
                GL33.glVertexAttribPointer(0,3, GL33.GL_FLOAT,);
                // TODO:

                MemoryUtil.memFree(fb);
            }
            // Setup texture
            // Setup coords
            // Setup matrix
        }
    }

    public static void render() {
        GL33.glUseProgram(Shaders.shaderId); // use this shader to render
        GL33.glBindVertexArray(vaoId);
        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, 3);
    }

    public static void update() {

    }
}
