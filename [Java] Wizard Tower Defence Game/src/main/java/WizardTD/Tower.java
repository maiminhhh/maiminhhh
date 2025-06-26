package WizardTD;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Random;
public class Tower extends StaticGraphics {
    //class attributes
    private int rangeCost = 20;
    private int speedCost = 20;
    private int damageCost = 20;
    private int buildCost;
    private float range;
    private float speed;
    private int damage;
    private int rangeLvGraphed = 0;
    private int speedLvGraphed = 0;
    private int damageLvGraphed = 0;
    private int allLv = 1;
    private int centerX;
    private int centerY;
    private int initialDamage;
    private ArrayList<FireBall> fireBalls = new ArrayList<>();
    private ArrayList<Monsters> enemiesInRange = new ArrayList<>();
    private Monsters target;

    //constructor method
    public Tower(int initialTowerCost, float initialTowerRange, float initialTowerFiringSpeed, int initialTowerDamage, int x, int y) {
        super(x,y);
        this.buildCost = initialTowerCost;
        this.range = initialTowerRange;
        this.damage = initialTowerDamage;
        this.initialDamage = initialTowerDamage;
        this.speed = initialTowerFiringSpeed;
        this.centerX = x + 16;
        this.centerY = y + 16;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getRange() {
        return this.range;
    }

    //get the amount of damage received by the monster
    public int getDamage(){
        return this.damage;
    }

    //get the cost for upgrading this tower's range
    public int getRangeCost() {
        return this.rangeCost;
    }

    // get the cost for upgrading this tower's speed
    public int getSpeedCost() {
        return this.speedCost;
    }

    //get the cost for upgrading this tower's damage
    public int getDamageCost(){
        return this.damageCost;
    }

    //get the cost for building the tower
    public int getBuildCost() {
        return this.buildCost;
    }

    public void upgradeAllLv() {
        if (this.allLv <= 3) {
            Integer[] levelGraphed = new Integer[3];
            levelGraphed[0] = this.rangeLvGraphed;
            levelGraphed[1] = this.speedLvGraphed;
            levelGraphed[2] = this.damageLvGraphed;
            for (int i = 0; i < levelGraphed.length; i++) {
                if (levelGraphed[i] == 0) {
                    return;
                }
            }
            this.rangeLvGraphed --;
            this.speedLvGraphed --;
            this.damageLvGraphed --;
            this.allLv++;
        }
    }

    //upgrade the range of the tower
    public void upgradeRange() {
        if (this.rangeLvGraphed < 5) {
            this.rangeLvGraphed++;
            this.range += 32;
            this.rangeCost += 10;
        }
        this.upgradeAllLv();
    }

    //upgrade the speed of the tower
    public void upgradeSpeed() {
        if (this.speedLvGraphed < 5) {
            this.speed += 0.5;
            this.speedLvGraphed += 1;
            this.speedCost += 10;
        }
        this.upgradeAllLv();
    }

    //upgrade the damage of the tower
    public void upgradeDamage() {
        if (this.damageLvGraphed < 5) {
            this.damage += this.initialDamage*0.5;
            this.damageLvGraphed += 1;
            this.damageCost += 10;
        }
        this.upgradeAllLv();
    }

    //get a list of all fireballs to be generated
    public ArrayList<FireBall> getFireBalls() {
        return this.fireBalls;
    }

    //get the enemies within the tower's range
    public ArrayList<Monsters> getEnemiesInRange() {
        return this.enemiesInRange;
    }

    //get the center x-coordinate of the tower
    public int getCenterX() {
        return this.centerX;
    }

    //get the center y-coorinate of the tower
    public int getCenterY() {
        return this.centerY;
    }

    public int getAllLv() {
        return this.allLv;
    }

    public int getRangeLvGraphed() {
        return this.rangeLvGraphed;
    }

    public int getSpeedLvGraphed() {
        return this.speedLvGraphed;
    }

    public int getDamageLvGraphed() {
        return this.damageLvGraphed;
    }

    //check which enemies is in the range of this tower's range
    public void setEnemiesInRange(ArrayList<Monsters> monsters) {
        if (monsters!=null) {
            for (Monsters monster : monsters) {
                if (monster.getSprite() != null) {
                    if (!monster.isDisappear()) {
                        float monsterCenterX = monster.getX() + (float) (monster.getSprite().pixelWidth / 2);
                        float monsterCenterY = monster.getY() + (float) (monster.getSprite().pixelHeight / 2);
                        float deltaX = this.centerX - monsterCenterX;
                        float deltaY = this.centerY - monsterCenterY;
                        float distance = (float) (Math.sqrt(deltaX * deltaX + deltaY * deltaY));
                        if (distance < this.range) {
                            this.enemiesInRange.add(monster);
                        }
                    }
                }
            }
        }
    }

    //randomly pick a target in the list of all enemies in range
    public void setTarget() {
        if (this.getEnemiesInRange().size() == 0) {
            this.target = null;
        }
        else if (this.getEnemiesInRange().size() == 1) {
            this.target = this.getEnemiesInRange().get(0);
        }
        else {
            Random rand = new Random();
            int targetIndex = rand.nextInt(this.getEnemiesInRange().size());
            this.target = this.getEnemiesInRange().get(targetIndex);
        }
    }

    //get the target of the tower
    public Monsters getTarget() {
        return this.target;
    }

    //generate the fireballs based on listed condition
    public void generateFireBall(int currentFrame, float gameSpeed, PImage fireBallSprite) {
        if (this.enemiesInRange.size() != 0) {
            if (this.fireBalls.size() == 0) {
                this.fireBalls.add(new FireBall(this, currentFrame, fireBallSprite));
            }
            else {
                int previousFrame = this.fireBalls.get(this.fireBalls.size()-1).getShootingFrame();
                if (currentFrame - previousFrame >= 60/(this.speed*gameSpeed)){
                    this.fireBalls.add(new FireBall(this, currentFrame, fireBallSprite));
                }
            }
        }
    }

    public void setAllLv(int allLv) {
        this.allLv = allLv;
    }

    //clear all the enemies in range
    public void resetEnemiesInRange() {
        this.enemiesInRange.clear();
    }

    public void setSprite(PImage tower0, PImage tower1, PImage tower2) {
        if (this.allLv == 1) {
            this.setSprite(tower0);
        }
        else if (this.allLv == 2) {
            this.setSprite(tower1);
        }
        else if (this.allLv >= 3) {
            this.setSprite(tower2);
        }
    }

    public void drawSign(PApplet app) {
        int distanceBetweenSign = 6;
        if (this.damageLvGraphed != 0) {
            app.noFill();
            app.stroke(123,181,255);
            app.strokeWeight(1+this.damageLvGraphed);
            app.rect(this.x+6, this.y+6, 20,20);
        }
        for (int i = 0; i < this.rangeLvGraphed; i++) {
            app.fill(176,47,182);
            app.textSize(10);
            app.text("O", this.x+i*distanceBetweenSign, this.y+8);
        }
        for (int i = 0; i < this.speedLvGraphed; i++) {
            app.fill(176, 47, 182);
            app.textSize(10);
            app.text("X", this.x+i*distanceBetweenSign, this.y + 32);
        }
        if (app.mouseX > this.x && app.mouseX < this.x+sprite.pixelWidth && app.mouseY > this.y && app.mouseY < this.y + sprite.pixelHeight) {
            app.noFill();
            app.stroke(255,255,0);
            app.strokeWeight(2);
            app.ellipse(this.x+(float)(sprite.pixelWidth/2), this.y+(float) (sprite.pixelHeight/2), this.range*2, this.range*2);
        }
    }
}