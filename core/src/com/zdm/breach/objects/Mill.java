package com.zdm.breach.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Mill {

	private static int MOVEMENT_MULT = 100;
	
	Rectangle hitBox;
	Sprite sprite;
	int action;
	float velocity;
	
	float deltaNow = 1;
	float deltaSum = deltaNow;
	Random r = new Random();
	
	
	public Mill(Texture t, float posX, float posY){
		this.sprite = new Sprite(t);//, 0, 0, t.getWidth(), t.getHeight());
		this.sprite.setScale(0.25f);
		this.sprite.setPosition(posX, posY);
		this.sprite.setRotation(0f);
		this.hitBox = new Rectangle();
		this.hitBox.setPosition(this.sprite.getX(), this.sprite.getY());
	} // END
	
	
	public int hits(Rectangle r){
		if(this.hitBox.overlaps(r)){
			return 1;
		}
		return -1;
	} // END
	
	public void action(int type){
		
	} // END
	
	public void update(float delta){
		// Gravity updates, for example, would go here.
	} // END
	
	public void setPosition(float x, float y){
		this.hitBox.x = x;
		this.hitBox.y = y;
		this.sprite.setPosition(x, y);
	} // END
	
	public void moveLeft(float delta){
		this.sprite.setRotation(90);
		this.hitBox.x -= (MOVEMENT_MULT * delta);
		this.sprite.setPosition(this.hitBox.x, this.hitBox.y);
	} // END
	
	public void moveRight(float delta){
		this.sprite.setRotation(270);
		this.hitBox.x += (MOVEMENT_MULT * delta);
		this.sprite.setPosition(this.hitBox.x, this.hitBox.y);
	} // END
	
	public void moveUp(float delta){
		this.sprite.setRotation(0);
		this.hitBox.y += (MOVEMENT_MULT * delta);
		this.sprite.setPosition(this.hitBox.x, this.hitBox.y);
	} // END
	
	public void moveDown(float delta){
		this.sprite.setRotation(180);
		this.hitBox.y -= (MOVEMENT_MULT * delta);
		this.sprite.setPosition(this.hitBox.x, this.hitBox.y);
	} // END

	
	public void setRandRotation(){
		this.sprite.setRotation((float) this.r.nextInt(359));
	} // END
	
	
	public String toString(){
		return this.hitBox.x + "x  " + this.hitBox.y + "y";
	} // END
	
	
	public void draw(SpriteBatch batch){
		/*
		deltaNow = Gdx.graphics.getDeltaTime();
		deltaSum += deltaNow;
		
		if(deltaSum >= 1){
			setRandRotation();
			deltaSum = 0;
			//System.out.println("\tNew Rotation!");
		}*/
		this.sprite.draw(batch);
		//System.out.println("Mill drawn!");
	} // END
	
} // END
