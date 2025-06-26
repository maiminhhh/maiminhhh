package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;

public class StaticGraphics implements Graphics {
    protected int x;
    protected int y;
    protected PImage sprite;

    public StaticGraphics(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    @Override
    public PImage getSprite(){
        return this.sprite;
    }

    @Override
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }
}
