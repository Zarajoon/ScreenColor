import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ScreenColor extends PApplet {



/**
* Tide up Camera.
*by Zahra Zolfaghari  
*
*pixel from the cam source is tide up 
*/

// grid cell size
int cellSize = 10;
// number of columns and rows
int cols, rows;
float camX = mouseX;
Capture cam; 
 
public void setup() { 
  size(1280, 800, P3D); 
  frameRate(5);
  cols = width/cellSize;
  rows = height/cellSize;
  colorMode(RGB, 255, 255, 255, 100);
  
  cam = new Capture(this, width,height);
  cam.start(); 
  
  background(180);
} 
 
public void draw() { 
    //image(cam, 0, 0, width/2, height/2);
    cam.read();
    cam.loadPixels();
    background(180);
    //directionalLight(126, 126, 300, 0, 0, -1);
//
    ambientLight(200, 200, 200);


    
    // Begin loop for columns
    for (int i = 0; i < cols; i++) {
      // Begin loop for rows
      for (int j = 0; j < rows; j++) {        
                // Where are we?
        int x = i*cellSize;
        int y = j*cellSize;
        int loc = (cam.width - x - 1) + y*cam.width; //  mirror the image
      
        float r = red(cam.pixels[loc]);
        float g = green(cam.pixels[loc]);
        float b = blue(cam.pixels[loc]);

        // Griddy
         int Xr =  round(r - (r%cellSize));
         int Yg = round(g - (g%cellSize));
         int Zb = round(b - (b%cellSize));         
          noStroke();
          fill(r, g, b);
          pushMatrix();
            translate(1.1f*Zb+ 300, 1.1f*Yg+100, 1.1f*Xr);  

            box(cellSize);
          popMatrix();
          }         
      }
}

public void mouseDragged() 
{
//  if (camX < width/2) {
//    camX = camX *(-1);
//  }
 camera(mouseX, mouseY, (height/2) / tan(PI*30.0f / 180.0f), width/2.0f, height/2.0f, 0, 0, 1, 0);
}




  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "ScreenColor" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
