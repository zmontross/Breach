package com.zdm.breach.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mill {
	
	private static final int SPRITE_IMAGE_SIZE_PX = 256;
	
	private static final float DEFAULT_SIZE_PX = 92f;
	
	private static final int UP = 0;
	private static final int DOWN = 180;
	private static final int LEFT = 90;
	private static final int RIGHT = 270;
	
	
	private Rectangle hitbox;
	private Sprite sprite;

	
	public Mill(Texture t){
		this(t, 0f, 0f);
	} // END
	
	
	public Mill(Texture t, float x, float y){
		this.sprite = new Sprite(t, 0, 0, SPRITE_IMAGE_SIZE_PX, SPRITE_IMAGE_SIZE_PX);
		this.hitbox = new Rectangle();
		
		this.sprite.setSize(DEFAULT_SIZE_PX, DEFAULT_SIZE_PX);
		this.hitbox.setSize(DEFAULT_SIZE_PX, DEFAULT_SIZE_PX);
		
		this.sprite.setRotation(UP);
		
		this.setPosition(x, y);
	} // END
	
	
	public void setPosition(float x, float y){
		this.hitbox.x = x;
		this.hitbox.y = y;
		this.sprite.setPosition(x, y);
	} // END
	
	
	public void draw(SpriteBatch batch){
		this.sprite.draw(batch);
	} // END
	
		
	public boolean overlaps(Rectangle r){
		return this.hitbox.overlaps(r);
	} // END
	
	
	public void update(float delta){
		// Gravity updates, for example, would go here.
	} // END
	
	
	
	public Vector2 getPosition(){
		Vector2 v2Pos = new Vector2(this.hitbox.x, this.hitbox.y);
		return v2Pos;
	} // END
	
	public Rectangle getHitbox(){
		return this.hitbox;
	} // END
	
} // END
