package com.xczj.libgdxDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShaderDemoApplication extends ApplicationAdapter {

	ShaderProgram shader;
	Mesh mesh;
	Texture texture;
	Matrix4 matrix = new Matrix4();
	Vector2 viewPort = new Vector2();
	public SpriteBatch batch;
	public Sprite sprite;
	float time = 0.0f;
	@Override
	public void create() {
		batch = new SpriteBatch();
		sprite =new Sprite(new Texture(Gdx.files.internal("data/libgdx.png")));
		sprite.setSize(Gdx.graphics.getWidth() - 30, Gdx.graphics.getHeight() - 30);
		sprite.setPosition(15, 15);
		String vertexShader = Gdx.files.internal("data/vertex.glsl").readString();
		String fragmentShader = Gdx.files.internal("data/fragment.glsl").readString();
		shader = new ShaderProgram(vertexShader, fragmentShader);
		System.out.println("shader日志：" + shader.getLog());
		mesh = new Mesh(true, 4, 6, VertexAttribute.Position(),
				VertexAttribute.ColorUnpacked(), VertexAttribute.TexCoords(0));
		
		mesh.setVertices(new float[]{
				//(x,y,z)坐标 + RGBA +纹理坐标
				-1f,-1f,0,1,1,1,1,0,1,
				 1f,-1f,0,1,1,1,1,1,1,
				 1f, 1f,0,1,1,1,1,1,0,
				-1f, 1f, 0,1,1,1,1,0,0
		});
		
		mesh.setIndices(new short[]{0,1,2,2,3,0});
		
		texture = new Texture(Gdx.files.internal("data/map.png"));
		viewPort.x = Gdx.graphics.getWidth();
		viewPort.y = Gdx.graphics.getHeight();
	}
	
	Vector3 axis = new Vector3(0,0,1);
	float angle = 0;
	@Override
	public void render() {
		time += 0.05f;
		if(time > 1000000){
			time = 0.0f;
		}
//		angle += Gdx.graphics.getDeltaTime() * 45;
//		matrix.setToRotation(axis, angle);
		Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl20.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		texture.bind();
//		batch.begin();
//		sprite.draw(batch);
//		batch.end();
		shader.begin();
		shader.setUniformf("viewPort", viewPort);
		shader.setUniformf("myTimes", time);
		shader.setUniformMatrix("u_worldView", matrix);
		mesh.render(shader, GL20.GL_TRIANGLES);
		shader.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		mesh.dispose();
		texture.dispose();
		shader.dispose();

	}

	@Override
	public void pause() {
	}
}
