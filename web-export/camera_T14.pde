import processing.video.*;

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
 
void setup() { 
  size(1280, 800, P3D); 
  frameRate(5);
  cols = width/cellSize;
  rows = height/cellSize;
  colorMode(RGB, 255, 255, 255, 100);
  
  cam = new Capture(this, width,height);
  cam.start(); 
  
  background(180);
} 
 
void draw() { 
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
            translate(1.1*Zb+ 300, 1.1*Yg+100, 1.1*Xr);  

            box(cellSize);
          popMatrix();
          }         
      }
}

void mouseDragged() 
{
//  if (camX < width/2) {
//    camX = camX *(-1);
//  }
 camera(mouseX, mouseY, (height/2) / tan(PI*30.0 / 180.0), width/2.0, height/2.0, 0, 0, 1, 0);
}





