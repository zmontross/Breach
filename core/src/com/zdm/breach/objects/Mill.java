package com.zdm.breach.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Mill {

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
		
	} // END
	
	public void setPosition(float x, float y){
		
	} // END
	
	public void moveLeft(float delta){
		
	} // END
	
	public void moveRight(float delta){
		
	} // END

	
	public void setRandRotation(){
		this.sprite.setRotation((float) this.r.nextInt(359));
	} // END
	
	
	public void draw(SpriteBatch batch){
		
		deltaNow = Gdx.graphics.getDeltaTime();
		deltaSum += deltaNow;
		
		if(deltaSum >= 1){
			setRandRotation();
			deltaSum = 0;
			System.out.println("\tNew Rotation!");
		}
		
		this.sprite.draw(batch);
		System.out.println("Mill drawn!");
	} // END
	
} // END
