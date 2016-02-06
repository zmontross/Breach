package com.zdm.breach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zdm.breach.objects.LineGrid;
import com.zdm.breach.objects.Mill;

public class BreachGame extends ApplicationAdapter implements InputProcessor{
	
	//private static final int WINDOW_WIDTH = Gdx.graphics.getWidth();
	//private static final int WINDOW_HEIGHT = Gdx.graphics.getHeight();
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	ArrayList<Texture> textures;
	ArrayList<Mill> mills;
	Mill user;
	
	
	private float DEFAULT_ZOOM = 2.5f;
	private float ZOOM_MIN = 0.1f;
	private float ZOOM_MAX = 5f;
	
	private int GRID_SQUARE_SIZE_PX = 64;
	private int GRID_WIDTH = 25;
	private int GRID_HEIGHT = 25;
	LineGrid grid;
	
	private Vector3 DEFAULT_POS = new Vector3 ((GRID_WIDTH / 2) * GRID_SQUARE_SIZE_PX, (GRID_HEIGHT / 2) * GRID_SQUARE_SIZE_PX, 0);

	FreeTypeFontGenerator generator;
	FreeTypeFontParameter parameter;
	BitmapFont font12;
	String sDebug;
	SpriteBatch batchGUI;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(DEFAULT_POS);
		camera.zoom = DEFAULT_ZOOM;
		camera.update();
		
		batch = new SpriteBatch();
		
		textures = new ArrayList<Texture>();
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_RED.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_CYAN.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_GREEN.png")));
		textures.add(new Texture(Gdx.files.internal("res\\Mills_256x256_WHITE.png")));
		
		mills = new ArrayList<Mill>();
		mills.add(new Mill(textures.get(0), GRID_SQUARE_SIZE_PX*2, GRID_SQUARE_SIZE_PX*3));
		mills.add(new Mill(textures.get(1), GRID_SQUARE_SIZE_PX*7, GRID_SQUARE_SIZE_PX*9));
		mills.add(new Mill(textures.get(2), GRID_SQUARE_SIZE_PX*15, GRID_SQUARE_SIZE_PX*20));
		mills.add(new Mill(textures.get(3), GRID_SQUARE_SIZE_PX*20, GRID_SQUARE_SIZE_PX*15));
		
		
		// Create a grid of lines. 100x100 where each square is 64px by 64px
		grid = new LineGrid(GRID_WIDTH, GRID_HEIGHT, GRID_SQUARE_SIZE_PX, 0f, 0f);
		grid.setPerimeterCollision(true);

		
		batchGUI = new SpriteBatch();
		
		sDebug = new String();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("res\\fonts\\arial.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 16;
		parameter.borderWidth = 0.25f;
		parameter.borderColor = Color.BLACK;
		font12 = generator.generateFont(parameter);
		font12.setColor(Color.YELLOW);
		
		Gdx.input.setInputProcessor(this);
		
		
		
	} // END

	public void dispose(){
		batch.dispose();
		batchGUI.dispose();
		
		Iterator<Texture> itr = textures.iterator();
		while(itr.hasNext()){
			Texture t = itr.next();
			t.dispose();
		}
		grid.dispose();
		generator.dispose();
		font12.dispose();
	} // END
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// SpriteBatch used for rendering game world objects.
		batch.setProjectionMatrix(camera.combined);
		
		grid.render(camera.combined);
		
		batch.begin();
		//
		for(Mill m : mills){
			m.draw(batch);
		}
		//
		batch.end();
		
		
		// SpriteBatch used for drawing GUI.
		batchGUI.begin();
		font12.draw(batchGUI, sDebug, 5f, Gdx.graphics.getHeight() - 5f);
		batchGUI.end();
		
		// Updates.
		Vector3 tempV3 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		sDebug = "FPS: " + Gdx.graphics.getFramesPerSecond() +
				"     Camera X,Y,Z: " + Math.round(camera.position.x) + "|" + Math.round(camera.position.y) + "|" + Math.round(camera.zoom) +
				"     Pointer X,Y: " +  Math.round(tempV3.x) + "|" + Math.round(tempV3.y);
		
		//user.update(Gdx.graphics.getDeltaTime());


		
		
		// Controls.
		
		
		/**
		 * Note to self. I REALLY need to invest in some unit vectors.
		 * When a vert+horiz key combo is simultaneously pressed their mutual effects amplify each other.
		 * i.e. When UP+LEFT, the sprite will move 2x normal speed in that direction as compared to a single-held key.
		 * */
		
	} // END

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Input.Keys.SPACE){
			camera.position.setZero();
			camera.zoom = DEFAULT_ZOOM;
			camera.update();			
		}
		return false;
	} // END

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		Vector3 coords = new Vector3(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
		for(Mill m : mills){
			if(m.getHitbox().contains(coords.x, coords.y)){
				System.out.println("\tContact! " + m.toString());
				camera.position.x = m.getPosition().x;
				camera.position.y = m.getPosition().y;
				camera.zoom = 1.5f;
				camera.update();
			}
		} // for m in mills
		
		return false;
	} // END

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub

		camera.translate(-Gdx.input.getDeltaX() * camera.zoom, Gdx.input.getDeltaY() * camera.zoom);
		camera.update();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		float temp = camera.zoom + (amount * ZOOM_MIN); 
		//camera.zoom = clamp(temp, ZOOM_MIN, ZOOM_MAX);
		
		camera.zoom = MathUtils.clamp(temp, ZOOM_MIN, ZOOM_MAX);
		
		camera.update();
		//System.out.println(amount + "\t" + camera.zoom);
		
		return false;
	} // END
	
} // END
