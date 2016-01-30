package com.zdm.breach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.zdm.breach.objects.Mill;

public class BreachGame extends ApplicationAdapter {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	//private Texture texture;
	ArrayList<Texture> textures;
	//private Sprite sprite;
	ArrayList<Mill> mills;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		//texture = new Texture(Gdx.files.internal("res\\Mills_256x256_WHITE.png"));
		textures = new ArrayList<Texture>();
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_RED.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_CYAN.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_GREEN.png")));
		//sprite = new Sprite(texture, 0, 0, 256, 256);
		//sprite.setScale(0.0625f);
		//sprite.setPosition(100f, 50f);
		Random r = new Random();
		
		mills = new ArrayList<Mill>();
		mills.add(new Mill(textures.get(0), (float) r.nextInt(WINDOW_WIDTH), (float) r.nextInt(WINDOW_HEIGHT)));
		mills.add(new Mill(textures.get(1), (float) r.nextInt(WINDOW_WIDTH), (float) r.nextInt(WINDOW_HEIGHT)));
		mills.add(new Mill(textures.get(2), (float) r.nextInt(WINDOW_WIDTH), (float) r.nextInt(WINDOW_HEIGHT)));
		
	} // END

	public void dispose(){
		batch.dispose();
		//texture.dispose();
		Iterator<Texture> itr = textures.iterator();
		while(itr.hasNext()){
			Texture t = itr.next();
			t.dispose();
		}
	} // END
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		//batch.draw(img, 0, 0);
		//batch.draw(img, 400 - (img.getWidth() / 2), 300 - (img.getHeight() / 2)); // 800 x 600 screen
		//batch.draw(img2, 400 - (img2.getWidth() / 2), 300 - (img2.getHeight() / 2)); // 800 x 600 screen

		//sprite.draw(batch);
		mills.get(0).draw(batch);
		mills.get(1).draw(batch);
		mills.get(2).draw(batch);
		/*
		Iterator<Mill> itr = mills.iterator();
		while(itr.hasNext()){
			Mill m = itr.next();
			m.draw(batch);
		}*/
		
		//
		batch.end();
	} // END
} // END
