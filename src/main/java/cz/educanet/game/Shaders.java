package cz.educanet.game;

import org.lwjgl.opengl.GL33;

public class Shaders {

    private static final String fragmentShader = "#version 330 core\n" +
            "\n" +
            "out vec4 FragColor;\n" +
            "\n" +
            "void main() {\n" +
            "    FragColor = vec4(0.0, 1.0, 0.5, 1.0);\n" +
            "}";

    private static final String vertexShader = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "\n" +
            "void main() {\n" +
            "    // TODO: Do some transformations\n" +
            "    gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
            "}";

    private static int vertexId;
    private static int fragmentId;
    public static int shaderId;

    public static void initShaders() {
        // generate ids
        vertexId = GL33.glCreateShader(GL33.GL_VERTEX_SHADER);
        fragmentId = GL33.glCreateShader(GL33.GL_FRAGMENT_SHADER);

        // compile shader and send to gpu
        GL33.glShaderSource(vertexId, vertexShader); // source code to gpu
        GL33.glCompileShader(vertexId); // compile

        System.out.println(GL33.glGetShaderInfoLog(vertexId)); // print possible error codes

        GL33.glShaderSource(fragmentId, fragmentShader); // source code to gpu
        GL33.glCompileShader(fragmentId); // compile

        System.out.println(GL33.glGetShaderInfoLog(fragmentId)); // print possible error codes

        shaderId = GL33.glCreateProgram();
        GL33.glAttachShader(shaderId, vertexId); // add vertex shader
        GL33.glAttachShader(shaderId, fragmentId); // add fragment shader
        GL33.glLinkProgram(shaderId);

        System.out.println(GL33.glGetProgramInfoLog(shaderId)); // print possible error codes

        // already sent to gpu > no longer needed on my part
        GL33.glDeleteShader(vertexId);
        GL33.glDeleteShader(fragmentId);
    }
}
