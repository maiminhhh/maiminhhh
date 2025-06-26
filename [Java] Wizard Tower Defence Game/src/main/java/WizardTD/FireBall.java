package WizardTD;
import processing.core.PImage;
import java.lang.*;

public class FireBall extends UnstaticGraphics implements MovingGraphics {
    private Monsters target;
    final private Tower tower;
    final private int shootingFrame;

    /**
     * constructing a fireBall object
     * @param tower the tower that this fireball origins from
     * @param shootingFrame the frame at which this fireball was shot
     * @param sprite the sprite used to draw the fireball
     */
    public FireBall(Tower tower, int shootingFrame, PImage sprite) {
        super(5, sprite);
        this.x = tower.getCenterX();
        this.y = tower.getCenterY();
        this.setDisappear(false);
        this.tower = tower;
        this.shootingFrame = shootingFrame;
    }

    /**
     * get method for the shooting frame attribute
     * @return the frame at which this fireball was shot
     */
    public int getShootingFrame() {
        return this.shootingFrame;
    }

    /**
     * get method for the fireball's target
     * @return the target of this fireball
     */

    public Monsters getTarget(){
        return this.target;
    }

    /**
     * set an arbitrary monster to be the fireball's target
     */
    public void setTarget() {
        if (this.tower.getTarget()==null) {
            this.tower.setTarget();
        }
        else if (this.tower.getTarget().isDisappear() || !this.tower.getEnemiesInRange().contains(this.tower.getTarget())) {
            this.tower.setTarget();
        }
        this.target = this.tower.getTarget();
    }

    /**
     * get method for the original tower
     * @return the tower from which this fireball was shot
     */
    public Tower getTower() {
        return this.tower;
    }

    /**
     * the moving logic of the fireball
     * @param gameSpeed the speed at which the whole game is running
     */
    @Override
    public void move(float gameSpeed) {
        if (this.target!=null && !this.disappear) {
            float targetCenterX = this.target.getX() + (float) (this.target.getSprite().pixelWidth/2);
            float targetCenterY = this.target.getY() + (float) (this.target.getSprite().pixelHeight/2);
            float deltaX = targetCenterX - this.x;
            float deltaY = targetCenterY - this.y;
            float distance = (float) (Math.sqrt(deltaX * deltaX + deltaY * deltaY));
            if (distance != 0) {
                float directionX = deltaX / distance;
                float directionY = deltaY / distance;
                float velocityX = directionX * this.speed*gameSpeed;
                float velocityY = directionY * this.speed*gameSpeed;
                this.x += velocityX;
                this.y += velocityY;
            }
            if (distance <= this.target.getSprite().pixelWidth/2) {
                this.disappear = true;
                this.target.damaged(this.tower.getDamage());
            }
            if (this.target.isDisappear()) {
                this.disappear = true;
            }
        }
        else if (this.target == null) {
            this.disappear = true;
        }
    }
}
