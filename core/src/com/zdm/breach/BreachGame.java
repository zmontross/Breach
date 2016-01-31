package com.zdm.breach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.zdm.breach.objects.Mill;

public class BreachGame extends ApplicationAdapter {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	ArrayList<Texture> textures;
	ArrayList<Mill> mills;
	Mill user;
	
	Rectangle temp;
	private float DEFAULT_ZOOM = 0f;
	
	@Override
	public void create () {
		//camera = new OrthographicCamera();
		camera = new testCam();
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
		//DEFAULT_ZOOM = camera.position.z;
		DEFAULT_ZOOM = camera.zoom;
		
		batch = new SpriteBatch();
		
		textures = new ArrayList<Texture>();
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_RED.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_CYAN.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_GREEN.png")));
		
		Random r = new Random();
		
		mills = new ArrayList<Mill>();
		mills.add(new Mill(textures.get(0), (float) r.nextInt(WINDOW_WIDTH / 2), (float) r.nextInt(WINDOW_HEIGHT / 2)));
		mills.add(new Mill(textures.get(1), (float) r.nextInt(WINDOW_WIDTH / 2), (float) r.nextInt(WINDOW_HEIGHT / 2)));
		mills.add(new Mill(textures.get(2), (float) r.nextInt(WINDOW_WIDTH / 2), (float) r.nextInt(WINDOW_HEIGHT / 2)));
		
		user = new Mill(new Texture(Gdx.files.internal("res\\Mills_256x256_WHITE.png")), 0, 0);
		
		temp = new Rectangle(0, 0, WINDOW_WIDTH, 10);
		Gdx.input.setInputProcessor((InputProcessor) camera);
		
	} // END

	public void dispose(){
		batch.dispose();
		
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
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		//
		for(Mill m : mills){
			m.draw(batch);
		}
		
		user.draw(batch);
		
		camera.update();
		
		//
		batch.end();
		
		
		// Updates.
		//user.update(Gdx.graphics.getDeltaTime());
		if(user.hits(temp) != -1){
			user.moveUp(Gdx.graphics.getDeltaTime());
		}
		
		// Controls.
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			user.moveLeft(Gdx.graphics.getDeltaTime());
			//System.out.println("\tLeft!");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			user.moveRight(Gdx.graphics.getDeltaTime());
			//System.out.println("\tRight!");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			user.moveUp(Gdx.graphics.getDeltaTime());
			//System.out.println("\tUp!");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			user.moveDown(Gdx.graphics.getDeltaTime());
			//System.out.println("\tDown!");
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			//camera.position.z = DEFAULT_ZOOM;
			camera.zoom = DEFAULT_ZOOM;
			//camera.update();
			System.out.println("\tCurrent zoom: " + camera.position.z);
		}
		
		//System.out.println(user.toString());
		
		/**
		 * Note to self. I REALLY need to invest in some unit vectors.
		 * When a vert+horiz key combo is simultaneously pressed their mutual effects amplify each other.
		 * i.e. When UP+LEFT, the sprite will move 2x normal speed in that direction as compared to a single-held key.
		 * */
		
	} // END
} // END
