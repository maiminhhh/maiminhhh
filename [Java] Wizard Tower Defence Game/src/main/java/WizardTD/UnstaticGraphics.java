package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;

public class UnstaticGraphics implements Graphics {
    protected float x;
    protected float y;
    protected PImage sprite;
    protected float speed;
    protected boolean disappear;

    public UnstaticGraphics(float speed, PImage sprite) {
        this.speed = speed;
        this.disappear = false;
        this.sprite = sprite;
    }

    public void setDisappear(boolean disappear) {
        this.disappear = disappear;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public boolean isDisappear() {
        return this.disappear;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    @Override
    public PImage getSprite() {
        return this.sprite;
    }

    @Override
    public void draw(PApplet app) {
        if (!this.disappear) {
            app.image(this.sprite, this.x, this.y);
        }
    }
}
