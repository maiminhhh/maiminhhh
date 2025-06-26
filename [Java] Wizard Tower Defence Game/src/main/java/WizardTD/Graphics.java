package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;

public interface Graphics {
    /**
     * set method for the graphics' image
     * @param sprite a PImage which would be later drawn
     */
    void setSprite(PImage sprite);

    /**
     * get method for the graphics' image
     * @return the instances' visual aid
     */
    PImage getSprite();

    /**
     * draw function
     * @param app a PApplet object on which the graphics were drawn
     */
    void draw(PApplet app);
}
