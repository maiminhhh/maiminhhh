package WizardTD;
import java.util.*;

import processing.core.PApplet;
import processing.core.PImage;
public class Monsters extends UnstaticGraphics implements MovingGraphics {
    //class attributes
    private float maxHp;
    private float initialSpeed;
    private float armour;
    private int mana_gained_on_kill;
    private Structure currentStructure;
    private Structure nextStructure;
    private Structure startingPoint;
    private ArrayList<Structure> path;
    private int currentIndex;
    private float currentHp;
    private Wizard wizardHouse;
    private int deathFrameIndex = 0;

    /**
     * constructor method
      * @param sprite the image of the monster
     * @param hp the hp of the monster
     * @param speed the speed of the monster
     * @param armour the armour of the monster
     * @param mana_gained_on_kill the mana gained when the monster is dead
     */
    public Monsters(PImage sprite, float hp, float speed, float armour, int mana_gained_on_kill) {
        super(speed, sprite);
        this.maxHp = hp;
        this.currentHp = hp;
        this.initialSpeed = speed;
        this.armour = armour;
        this.mana_gained_on_kill = mana_gained_on_kill;
        this.path = new ArrayList<> ();
        this.nextStructure = null;
        this.currentStructure = null;
        this.startingPoint = null;
        this.currentIndex = 0;
    }

    /**
     * set the destination of the monster
      * @param wizardHouse the destination of the monster
     */
    public void setWizardHouse(Wizard wizardHouse) {
        this.wizardHouse = wizardHouse;
    }

    /**
     * get the destination of the monster
     * @return the destination of the monster
     */
    public Wizard getWizardHouse() {
        return this.wizardHouse;
    }

    /**
     * get the image of the monster
      * @return the image of the monster
     */
    public PImage getSprite() {
        return this.sprite;
    }

    /**
     * get the mana gained when the monster is dead
      * @return mana gained when the monster is dead
     */
    public int getManaGainedOnKill() {
        return this.mana_gained_on_kill;
    }


    /**
     * set the mana gained when the monster is dead
      * @param manaGainedOnKill the mana gained when the monster is dead
     */
    public void setManaGainedOnKill(int manaGainedOnKill) {
        this.mana_gained_on_kill = manaGainedOnKill;
    }

    /**
     * get the current hp of the monster
      * @return the current hp of the monster
     */
    public float getCurrentHp() {
        return this.currentHp;
    }

    /**
     * choose the starting point of the monster
      * @param map the map of the game
     */
    public void launch(Map map) {
        Random random = new Random();
        int randomStartIndex = random.nextInt(map.getPossiblePaths().size());
        if (map.getPossiblePaths()!=null) {
            this.path = map.getPossiblePaths().get(randomStartIndex);
            if (this.path != null) {
                this.currentStructure = this.path.get(0);
                this.startingPoint = this.path.get(0);
                int firstTileX = this.startingPoint.getX();
                int firstTileY = this.startingPoint.getY();
                if (this.path != null) {
                    this.nextStructure = this.path.get(1);
                }
                if (this.startingPoint.getAbove() != null) {
                    if (this.nextStructure != null) {
                        if (this.nextStructure.equals(this.startingPoint.getAbove())) {
                            this.x = firstTileX + (float) (this.currentStructure.getSprite().pixelWidth - this.sprite.pixelWidth) / 2;
                            this.y = firstTileY - 20;
                        }
                    }
                }
                if (this.startingPoint.getFront() != null) {
                    if (this.nextStructure != null) {
                        if (this.nextStructure.equals(this.startingPoint.getFront())) {
                            this.x = firstTileX - 20;
                            this.y = firstTileY + (float) (this.currentStructure.getSprite().pixelHeight - this.sprite.pixelHeight) / 2;
                        }
                    }
                }
                if (this.startingPoint.getBelow() != null) {
                    if (this.nextStructure != null) {
                        if (this.nextStructure.equals(this.startingPoint.getBelow())) {
                            this.x = firstTileX + (float) (this.currentStructure.getSprite().pixelWidth - this.sprite.pixelWidth) / 2;
                            this.y = firstTileY + 20;
                        }
                    }
                }
                if (this.startingPoint.getBack() != null) {
                    if (this.nextStructure != null) {
                        if (this.nextStructure.equals(this.startingPoint.getBack())) {
                            this.x = firstTileX + 20;
                            this.y = firstTileY + (float) (this.currentStructure.getSprite().pixelHeight - this.sprite.pixelHeight) / 2;
                        }
                    }
                }
            }
        }
    }

    @Override
/**
 * the moving logic of the monster
  */
    public void move(float gameSpeed) {
        boolean changeTile = false;
        if (this.nextStructure != null) {
            if (this.currentStructure.getRow() == this.nextStructure.getRow()) {
                if (this.currentStructure.getCol() < this.nextStructure.getCol()){
                    this.x = this.x + this.speed*gameSpeed;
                    if (this.x >= this.nextStructure.getX() + ((float)(this.nextStructure.getSprite().width - this.sprite.width)/2)) {
                        changeTile = true;
                    }
                }
                else {
                    this.x = this.x - this.speed*gameSpeed;
                    if (this.x <= this.nextStructure.getX()+((float)(this.nextStructure.getSprite().width - this.sprite.width)/2)){
                        changeTile = true;
                    }
                }
            }
            else if (this.currentStructure.getCol() == this.nextStructure.getCol()) {
                if (this.currentStructure.getRow() < this.nextStructure.getRow()) {
                    this.y = this.y + this.speed*gameSpeed;
                    if (this.y >= this.nextStructure.getY() + ((float)(this.nextStructure.getSprite().width - this.sprite.width)/2)) {
                        changeTile = true;
                    }
                }
                else {
                    this.y = this.y - this.speed*gameSpeed;
                    if (this.y <= this.nextStructure.getY()+((float)(this.nextStructure.getSprite().width - this.sprite.width)/2)) {
                        changeTile = true;
                    }
                }
            }
            if (changeTile) {
                if (this.currentIndex == this.path.size()-2) {
                    this.currentIndex = 0;
                    this.currentStructure = this.path.get(currentIndex);
                    this.nextStructure = this.path.get(currentIndex + 1);
                    int firstTileX = this.startingPoint.getX();
                    int firstTileY = this.startingPoint.getY();
                    this.nextStructure = this.path.get(1);
                    if (this.currentStructure.getAbove() != null && this.nextStructure != null) {
                        if (this.nextStructure.equals(this.currentStructure.getAbove())) {
                            this.x = firstTileX + (float) ((this.currentStructure.getSprite().pixelWidth-this.sprite.pixelWidth)/2);
                            this.y = firstTileY - 20;
                        }
                    }
                    if (this.currentStructure.getFront() != null && this.nextStructure != null) {
                        if (this.nextStructure.equals(this.currentStructure.getFront())) {
                            this.x = firstTileX - 20;
                            this.y = firstTileY + (float) ((this.currentStructure.getSprite().pixelHeight-this.sprite.pixelHeight)/2);
                        }
                    }
                    if (this.currentStructure.getBelow() != null && this.nextStructure != null) {
                        if (this.nextStructure.equals(this.currentStructure.getBelow())) {
                            this.x = firstTileX + (float) ((this.currentStructure.getSprite().pixelWidth-this.sprite.pixelWidth)/2);
                            this.y = firstTileY + 20;
                        }
                    }
                    if (this.currentStructure.getBack() != null && this.nextStructure != null) {
                        if (this.nextStructure.equals(this.currentStructure.getBack())) {
                            this.x = firstTileX + 20;
                            this.y = firstTileY + (float) ((this.currentStructure.getSprite().pixelHeight-this.sprite.pixelHeight)/2);
                        }
                    }
                    this.wizardHouse.monsterDamaged(this);
                }
                else {
                    currentIndex++;
                    this.currentStructure = this.path.get(currentIndex);
                    this.nextStructure = this.path.get(currentIndex + 1);
                }
            }
        }
    }

    /**
     * when the moster was hit by the fireball
      * @param damage damage received by the monster
     */
    public void damaged(int damage){
        this.currentHp -= damage*this.armour;
        if (this.currentHp <= 0) {
            this.currentHp = 0;
            this.disappear = true;
            this.wizardHouse.manaGainedOnKill(this.mana_gained_on_kill);
        }
    }

    /**
     * draw out the mana of the monster
     * @param app where the mana is drawn
     */
    public void drawMana(PApplet app) {
        if (!this.disappear) {
            app.fill(255, 0, 0);
            app.noStroke();
            app.rect(this.x - 6, this.y - 5, 32, 3);
            app.fill(0, 255, 0);
            app.noStroke();
            app.rect(this.x - 6, this.y - 5, (float) (this.currentHp / this.maxHp * 32), 3);
        }
    }

    /**
     * draw the death of the monster
     * @param app where the graphic is drawn
     */
    public void drawDeath(PApplet app) {
        if (this.disappear) {
            if (deathFrameIndex <4 ) {
                ArrayList<PImage> deathFrameSprites = new ArrayList<>();
                deathFrameSprites.add(app.loadImage("src/main/resources/WizardTD/gremlin1.png"));
                deathFrameSprites.add(app.loadImage("src/main/resources/WizardTD/gremlin2.png"));
                deathFrameSprites.add(app.loadImage("src/main/resources/WizardTD/gremlin3.png"));
                deathFrameSprites.add(app.loadImage("src/main/resources/WizardTD/gremlin4.png"));
                app.image(deathFrameSprites.get(this.deathFrameIndex), this.x, this.y);
                this.deathFrameIndex++;
            }
        }
    }
}