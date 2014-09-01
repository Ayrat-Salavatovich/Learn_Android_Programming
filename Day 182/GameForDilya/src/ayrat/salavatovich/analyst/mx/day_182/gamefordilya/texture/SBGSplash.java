package ayrat.salavatovich.analyst.mx.day_182.gamefordilya.texture;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class SBGSplash {
	
	private final FloatBuffer vertexBuffer;
	private final FloatBuffer textureBuffer;
	private final ByteBuffer indexBuffer;
	
	private final int mProgram;
	private int mPositionHandle;
	private int mMVPMatrixHandle;
	
	static final int COORDS_PER_VERTEX = 3;
	static final int COORDS_PER_TEXTURE = 2;
	private final int vertexStride = COORDS_PER_VERTEX * 4;
	private final int textureStride = COORDS_PER_TEXTURE * 4;
	
	// This matrix member variable provides a hook to manipulate
	// the coordinates of the objects that use this vertex shader
	private final String vertexShaderCode =
			"uniform mat4 uMVPMatrix;" +
			"attribute vec4 vPosition;" +
			"attribute vec2 TexCoordIn;" +
			"varying vec2 TexCoordOut;" +
			"void main() {" +
			"  gl_Position = uMVPMatrix * vPosition;" +
			"  TexCoordOut = TexCoordIn;" +
			"}";
	
	private final String fragmentShaderCode =
			"precision mediump float;" +
			"uniform vec4 vColor;" +
			"uniform sampler2D TexCoordIn;" +
			"uniform float scrool;" +
			"uniform vec2 TexCoordOut;" +
			"void main() {" +
			"  gl_FragColor = texture2D(TexCoordIn, vec2(TecCoordOut.x + scroll.TexCoordOut.y));" +
			"}";

	// Идентификатор каждой загружаемой текстуры
	private int[] textures = new int[1];
	
	// Набор точек: x, y, z
	private float[] vertices = {
			0f, 1f, 0f,
			0f, 0f, 0f,
			1f, 0f, 0f,
			1f, 1f, 0f
	};
	
	// Точки пересечения текстуры с углами созданного квадрата
	private float[] texture = {
			1f, 0f,
			1f, 1f,
			0f, 1f,
			0f, 0f
	};
	
	// Граница площади
	private byte[] indices = {
			0, 1, 2,
			0, 2, 3
	};
	
	public SBGSplash() {
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
		
		indexBuffer = ByteBuffer.allocateDirect(indices.length * 4);
		indexBuffer.order(ByteOrder.nativeOrder());
		indexBuffer.put(indices);
		indexBuffer.position(0);
		
		int vertexShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		GLES20.glShaderSource(vertexShader, vertexShaderCode);
		GLES20.glCompileShader(vertexShader);
		
		int fragmentShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		GLES20.glShaderSource(fragmentShader, fragmentShaderCode);
		GLES20.glCompileShader(fragmentShader);
		
		mProgram = GLES20.glCreateProgram();
		GLES20.glAttachShader(mProgram, vertexShader);
		GLES20.glAttachShader(mProgram, fragmentShader);
		GLES20.glLinkProgram(mProgram);
	}
	
}
