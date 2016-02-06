package com.zdm.breach.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LineGrid {

	
	private static Color DEFAULT_COLOR_HORIZONTAL = Color.CYAN;
	private static Color DEFAULT_COLOR_VERTICAL = Color.SALMON;
	private static int DEFAULT_COLLISON_RECT_THICKNESS = 10;
	
	private ShapeRenderer sr;
	private ShapeRenderer rectSR;
	private int width;
	private int height;
	private int pixelsPerSquare;
	private Vector2 position;
	private Color xColor = DEFAULT_COLOR_HORIZONTAL;
	private Color yColor = DEFAULT_COLOR_VERTICAL;
	private boolean hasPerimeterCollision = false;
	private Rectangle rTop;
	private Rectangle rBottom;
	private Rectangle rLeft;
	private Rectangle rRight;
	
	
	public LineGrid(int width, int height, int pixelsPerSquare, float x, float y){
		this(width, height, pixelsPerSquare, new Vector2(x, y), DEFAULT_COLOR_HORIZONTAL, DEFAULT_COLOR_VERTICAL);
	} // END
	
	public LineGrid(int width, int height, int pixelsPerSquare, Vector2 position){
		this(width, height, pixelsPerSquare, position, DEFAULT_COLOR_HORIZONTAL, DEFAULT_COLOR_VERTICAL);
	} // END
	
	public LineGrid(int width, int height, int pixelsPerSquare, float x, float y, Color xColor, Color yColor){
		this(width, height, pixelsPerSquare, new Vector2(x, y), xColor, yColor);
	} // END
	
	private LineGrid(int width, int height, int pixelsPerSquare, Vector2 position, Color xColor, Color yColor){
		
		this.width = width;
		this.height = height;
		this.position = position;
		this.xColor = xColor;
		this.yColor = yColor;
		this.pixelsPerSquare = pixelsPerSquare;
		this.sr = new ShapeRenderer();
		
	} // END
	
	
	
	/**
	 * Calls this object's ShapeRenderer.begin() method,
	 * creates the line grid,
	 * and finishes with ShapeRenderer.end().
	 * */
	public void render(Matrix4 matrix){
		this.sr.setProjectionMatrix(matrix);
		
		this.sr.begin(ShapeType.Line);
		
		int widthInPx = this.width * this.pixelsPerSquare;
		int heightInPx = this.height * this.pixelsPerSquare;
		
		// Populate lines.
		for(int i=0; i < this.width + 1; i++){
			
			int pxCoord = i * this.pixelsPerSquare;
			// Horizontal lines.
			this.sr.line(0, pxCoord, widthInPx, pxCoord, this.xColor, this.xColor);
			// Vertical lines.
			this.sr.line(pxCoord, 0, pxCoord, heightInPx, this.yColor, this.yColor);
		} // for i
		
		
		// If enabled, create and place the bounding-box rectangles usable for collision detection based on Rectangle.overlaps(Rectangle r) method.
		if(hasPerimeterCollision){
			rectSR = new ShapeRenderer();
			rectSR.setProjectionMatrix(matrix);
			rectSR.begin(ShapeType.Filled);
			
			//DEFAULT_COLLISON_RECT_THICKNESS
			// Create top collision rectangle.
			rTop = new Rectangle(0, heightInPx, widthInPx, DEFAULT_COLLISON_RECT_THICKNESS);
			// Create bottom as a copy of the top rectangle.
			rBottom = new Rectangle(rTop);
			// Create left collision rectangle.
			rLeft = new Rectangle(-(DEFAULT_COLLISON_RECT_THICKNESS), 0, DEFAULT_COLLISON_RECT_THICKNESS, heightInPx);
			// Create right as a copy of the left rectangle.
			rRight = new Rectangle(rLeft);
			
			// Set correct positions of bottom and right collision rectangles.
			rBottom.setPosition(0, -(DEFAULT_COLLISON_RECT_THICKNESS));
			rRight.setPosition(widthInPx, 0);
			
			// Render visible boxes on top of the collision rectangles.
			rectSR.setColor(this.xColor);
			rectSR.box(rTop.x, rTop.y, 0, rTop.width, rTop.height, 0);
			rectSR.box(rBottom.x, rBottom.y, 0, rBottom.width, rBottom.height, 0);
			
			rectSR.setColor(this.yColor);
			rectSR.box(rLeft.x, rLeft.y, 0, rLeft.width, rLeft.height, 0);
			rectSR.box(rRight.x, rRight.y, 0, rRight.width, rRight.height, 0);
			rectSR.end();
		}
		
		this.sr.end();
	} // END
	
	
	
	
	/**
	 * Returns this object's ShapeRenderer
	 * */
	public ShapeRenderer getShapeRenderer(){
		return this.sr;
	} // END
	
	
	/**
	 * Returns this object's position as a Vector2.
	 * Position is the coordinate of the lower-left corner of the grid.
	 * */
	public Vector2 getPosition(){
		return this.position;
	} // END
	
	
	/**
	 * Returns this object's width
	 * */
	public int getWidth(){
		return this.width;
	} // END
	
	/**
	 * Returns this object's height
	 * */
	public int getHeight(){
		return this.height;
	} // END
	
	
	/**
	 * Returns the size of each square in pixels-per-side
	 * */
	public int getPixelsPerSquare(){
		return this.pixelsPerSquare;
	} // END
	
	
	/**
	 * Returns the status of whether the collision bounding-boxes are enabled.
	 * */
	public boolean getPerimeterCollision(){
		return this.hasPerimeterCollision;
	} // END
	
	
	/**
	 * If true, then when this object's render method is called a transparent rectangle will be placed along each side (outside the grid).
	 * */
	public void setPerimeterCollision(boolean b){
		this.hasPerimeterCollision = b;
	} // END
	
	
	public void dispose(){
		this.sr.dispose();
		this.rectSR.dispose();
	} // END
	
} // END
