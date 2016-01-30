package com.zdm.breach;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BreachGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture img;
	private Sprite sprite;
	private Texture img2;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("D:\\Users\\Zachary\\Documents\\_ZDM_Programming\\logo-full.png");
		sprite = new Sprite(img);
		sprite.setPosition(100f, 50f);
		//sprite.
		
	} // END

	public void dispose(){
		batch.dispose();
		img.dispose();
		img2.dispose();
	} // END
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		//batch.draw(img, 0, 0);
		//batch.draw(img, 400 - (img.getWidth() / 2), 300 - (img.getHeight() / 2)); // 800 x 600 screen
		//batch.draw(img2, 400 - (img2.getWidth() / 2), 300 - (img2.getHeight() / 2)); // 800 x 600 screen
		sprite.draw(batch);
		//
		batch.end();
	} // END
} // END
