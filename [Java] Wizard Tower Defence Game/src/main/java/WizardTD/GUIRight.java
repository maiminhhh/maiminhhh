package WizardTD;
import processing.core.PApplet;
import java.util.*;
public class GUIRight {
    //class attributes
    private boolean faster;
    private boolean pause;
    private boolean buildTower;
    private boolean upgradeRange;
    private boolean upgradeSpeed;
    private boolean upgradeDamage;
    private boolean manaPoolSpell;
    private ManaPoolSpell spell;
    private int buildCost;
    private ArrayList<Tower> towers = new ArrayList<>();

    /**
     * constructor method
     * @param manaPoolSpellCost the cost of the mana pool spell
     * @param manaPoolCostIncreasePerUse how much the mana pool spell cost would increase each time it was called
     * @param buildCost the cost of building a tower
     * @param capMultiplier the multiplier by which max mana will increase when mana pool spell is called
     * @param manaGainedMultiplier the multiplier by which mana gained per monsters killed will increase when mana pool spell is called
     */
    public GUIRight(int manaPoolSpellCost, int manaPoolCostIncreasePerUse, int buildCost, float capMultiplier, float manaGainedMultiplier) {
        this.faster = false;
        this.pause = false;
        this.buildTower = false;
        this.upgradeRange = false;
        this.upgradeSpeed = false;
        this.upgradeDamage = false;
        this.manaPoolSpell = false;
        this.spell = new ManaPoolSpell(manaPoolSpellCost, manaPoolCostIncreasePerUse, capMultiplier, manaGainedMultiplier);
        this.buildCost = buildCost;
    }

    /**
     * check if the game was fast forwarded
     * @return whether the game was fastened
     */
    public boolean isFaster() {
        return this.faster;
    }

    /**
     * check if the game is being paused
     * @return whether the game was paused
     */
    public boolean isPausing() {
        return this.pause;
    }

    /**
     * check if the player is trying to build a tower
     * @return whether the player choose to build a tower
     */
    public boolean isBuildingTower() {
        return this.buildTower;
    }

    /**
     * check if the player is trying to upgrade the tower range
     * @return whether the player choose to upgrade the range of a tower
     */
    public boolean isUpgradingRange() {
        return this.upgradeRange;
    }

    /**
     * check if the player is trying to upgrade the tower speed
     * @return whether the player choose to upgrade the speed of a tower
     */
    public boolean isUpgradingSpeed() {
        return this.upgradeSpeed;
    }

    /**
     * check if the player is trying to upgrade the tower damage
     * @return whether the player choose to upgrade the damage of a tower
     */
    public boolean isUpgradingDamage() {
        return this.upgradeDamage;
    }

    /**
     * check if the player is trying to cast a spell
     * @return whether the player choose to cast a spell
     */
    public boolean isCastingSpell() {
        return this.manaPoolSpell;
    }

    /**
     * change the faster option of the game
     * @param faster the new faster status
     */
    public void setFaster(boolean faster) {
        this.faster = faster;
    }

    /**
     * change the pausing option of the game
     * @param pause the new pausing status
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * change the building option of the game
     * @param buildTower the new building status
     */
    public void setBuildTower(boolean buildTower) {
        this.buildTower = buildTower;
    }

    /**
     * change the upgrading range option of the game
     * @param upgradeRange the new upgrading range status
     */
    public void setUpgradeRange(boolean upgradeRange) {
        this.upgradeRange = upgradeRange;
    }

    /**
     * change the upgrading speed option of the game
     * @param upgradeSpeed the new upgrading speed status
     */
    public void setUpgradeSpeed(boolean upgradeSpeed) {
        this.upgradeSpeed = upgradeSpeed;
    }

    /**
     * change the upgrading damage option of the game
     * @param upgradeDamage the new upgrading damage status
     */
    public void setUpgradeDamage(boolean upgradeDamage) {
        this.upgradeDamage = upgradeDamage;
    }

    /**
     * change the mana pool spell option of the game
     * @param manaPoolSpell the new mana pool spell status
     */
    public void setManaPoolSpell(boolean manaPoolSpell) {
        this.manaPoolSpell = manaPoolSpell;
    }

    /**
     * get the spell casted on the game
     * @return the spell casted on the game
     */
    public ManaPoolSpell getSpell() {
        return this.spell;
    }

    /**
     * build new towers
     * @param towers the new towers being built
     */
    public void updateTowers(ArrayList<Tower> towers) {
        for (Tower tower : towers) {
            if (!this.towers.contains(tower)) {
                this.towers.add(tower);
            }
        }
    }

    /**
     * get all the tower
     * @return the list of all built towers so far within the game
     */
    public ArrayList<Tower> getTowers() {
        return this.towers;
    }

    /**
     * draw function
     * @param app the PApplet object where the instances are drawn on
     */
    public void draw(PApplet app){
        app.strokeWeight(2);
        if (this.faster) {
            app.fill(255,255,0);
            app.stroke(0);
            app.rect(650, 48, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("FF", 655, 77);
            app.textSize(12);
            app.text("2x speed",694, 65);
        }
        else if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 48 && app.mouseY <=48+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 48, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("FF", 655, 77);
            app.textSize(12);
            app.text("2x speed",694, 65);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 48, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("FF", 655, 77);
            app.textSize(12);
            app.text("2x speed",694, 65);
        }
        if (this.pause) {
            app.fill(255,255,0);
            app.stroke(0);
            app.rect(650, 102, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("P", 655, 131);
            app.textSize(12);
            app.text("PAUSE",694, 119);
        }
        else if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 102 && app.mouseY <=102+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 102, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("P", 655, 131);
            app.textSize(12);
            app.text("PAUSE",694, 119);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 102, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("P", 655, 131);
            app.textSize(12);
            app.text("PAUSE",694, 119);
        }
        if (this.buildTower) {
            app.fill(255,255,0);
            app.stroke(0);
            app.rect(650, 156, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("T", 655, 185);
            app.textSize(12);
            app.text("Build",694, 173);
            app.text("tower", 694, 188);
        }
        else if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 156 && app.mouseY <=156+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 156, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("T", 655, 185);
            app.textSize(12);
            app.text("Build",694, 173);
            app.text("tower", 694, 188);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 156, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("T", 655, 185);
            app.textSize(12);
            app.text("Build",694, 173);
            app.text("tower", 694, 188);
        }
        if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 156 && app.mouseY <=156+40)) {
            app.fill(255);
            app.strokeWeight(1);
            app.rect(575, 156, 60, 18);
            app.fill(0);
            app.textSize(12);
            boolean[] selectionCheck = new boolean[3];
            selectionCheck[0] = this.upgradeRange;
            selectionCheck[1] = this.upgradeSpeed;
            selectionCheck[2] = this.upgradeDamage;
            int trueCount = 0;
            for (boolean bool : selectionCheck) {
                if (bool) {
                    trueCount++;
                }
            }
            app.text("Cost: " + (this.buildCost + 20 * trueCount), 577, 170);
        }
        if (this.upgradeRange) {
            app.fill(255,255,0);
            app.stroke(0);
            app.rect(650, 210, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U1", 655, 239);
            app.textSize(12);
            app.text("Upgrade",694, 227);
            app.text("range", 694, 242);
        }
        else if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 210 && app.mouseY <=210+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 210, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U1", 655, 239);
            app.textSize(12);
            app.text("Upgrade",694, 227);
            app.text("range", 694, 242);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 210, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U1", 655, 239);
            app.textSize(12);
            app.text("Upgrade",694, 227);
            app.text("range", 694, 242);
        }
        if (this.upgradeSpeed) {
            app.fill(255,255,0);
            app.stroke(0);
            app.rect(650, 264, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U2", 655, 293);
            app.textSize(12);
            app.text("Upgrade",694, 281);
            app.text("speed", 694, 296);
        }
        else if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 264 && app.mouseY <=264+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 264, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U2", 655, 293);
            app.textSize(12);
            app.text("Upgrade",694, 281);
            app.text("speed", 694, 296);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 264, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U2", 655, 293);
            app.textSize(12);
            app.text("Upgrade",694, 281);
            app.text("speed", 694, 296);
        }
        if (this.upgradeDamage) {
            app.fill(255,255,0);
            app.stroke(0);
            app.rect(650, 318, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U3", 655, 347);
            app.textSize(12);
            app.text("Upgrade",694, 335);
            app.text("damage", 694, 350);
        }
        else if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 318 && app.mouseY <=318+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 318, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U3", 655, 347);
            app.textSize(12);
            app.text("Upgrade",694, 335);
            app.text("damage", 694, 350);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 318, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("U3", 655, 347);
            app.textSize(12);
            app.text("Upgrade",694, 335);
            app.text("damage", 694, 350);
        }
        if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 372 && app.mouseY <=372+40)) {
            app.fill(213,208,213);
            app.stroke(0);
            app.rect(650, 372, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("M", 655, 401);
            app.textSize(12);
            app.text("Mana pool",694, 389);
            app.text("cost: " + this.spell.getCost(), 694, 404);
        }
        else {
            app.noFill();
            app.stroke(0);
            app.rect(650, 372, 40, 40);
            app.fill(0);
            app.textSize(25);
            app.text("M", 655, 401);
            app.textSize(12);
            app.text("Mana pool",694, 389);
            app.text("cost: " + this.spell.getCost(), 694, 404);
        }
        if ((app.mouseX >= 650 && app.mouseX <= 650+40) && (app.mouseY >= 372 && app.mouseY <=372+40)) {
            app.fill(255);
            app.strokeWeight(1);
            app.rect(575, 372, 60, 18);
            app.fill(0);
            app.textSize(12);
            app.text("Cost: " + this.spell.getCost(), 577, 386);
        }
        for (Tower tower : this.towers) {
            if (app.mouseX >= tower.getX() && app.mouseX <= tower.getX()+32 && app.mouseY >= tower.getY() && app.mouseY <= tower.getY()+32) {
                if (this.upgradeRange) {
                    if (this.upgradeSpeed) {
                        if (this.upgradeDamage) {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 62);
                            app.fill(0);
                            app.textSize(14);
                            app.text("range:      " + tower.getRangeCost(),653,602);
                            app.text("speed:      " + tower.getSpeedCost(), 653, 622);
                            app.text("damage:   " + tower.getDamageCost(), 653, 642);
                            app.fill(255);
                            app.rect(650,648,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getDamageCost()+tower.getRangeCost()+tower.getSpeedCost() >= 100) {
                                app.text("Total:      " + (tower.getRangeCost() + tower.getSpeedCost() + tower.getDamageCost()), 653, 665);
                            }
                            else {
                                app.text("Total:       " + (tower.getRangeCost() + tower.getSpeedCost() + tower.getDamageCost()), 653, 665);
                            }
                        }
                        else {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 42);
                            app.fill(0);
                            app.textSize(14);
                            app.text("range:      " + tower.getRangeCost(),653,602);
                            app.text("speed:      " + tower.getSpeedCost(), 653, 622);
                            app.fill(255);
                            app.rect(650,628,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getRangeCost()+tower.getSpeedCost() >= 100) {
                                app.text("Total:      " + (tower.getRangeCost() + tower.getSpeedCost()), 653, 645);
                            }
                            else {
                                app.text("Total:       " + (tower.getRangeCost() + tower.getSpeedCost()), 653, 645);
                            }
                        }
                    }
                    else {
                        if (this.upgradeDamage) {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 42);
                            app.fill(0);
                            app.textSize(14);
                            app.text("range:      " + tower.getRangeCost(),653,602);
                            app.text("damage:   " + tower.getDamageCost(), 653, 622);
                            app.fill(255);
                            app.rect(650,628,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getDamageCost()+tower.getRangeCost() >= 100) {
                                app.text("Total:      " + (tower.getRangeCost() + tower.getDamageCost()), 653, 645);
                            }
                            else {
                                app.text("Total:       " + (tower.getRangeCost() + tower.getDamageCost()), 653, 645);
                            }
                        }
                        else {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("range:      " + tower.getRangeCost(),653,602);
                            app.fill(255);
                            app.rect(650,608,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getRangeCost() >= 100) {
                                app.text("Total:      " + tower.getRangeCost(), 653, 625);
                            }
                            else {
                                app.text("Total:       " + tower.getRangeCost(), 653, 625);
                            }
                        }
                    }
                }
                else {
                    if (this.upgradeSpeed) {
                        if (this.upgradeDamage) {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 42);
                            app.fill(0);
                            app.textSize(14);
                            app.text("speed:      " + tower.getSpeedCost(),653,602);
                            app.text("damage:   " + tower.getDamageCost(), 653, 622);
                            app.fill(255);
                            app.rect(650,628,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getDamageCost()+tower.getSpeedCost() >= 100) {
                                app.text("Total:      " + (tower.getSpeedCost() + tower.getDamageCost()), 653, 645);
                            }
                            else {
                                app.text("Total:       " + (tower.getSpeedCost() + tower.getDamageCost()), 653, 645);
                            }
                        }
                        else {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("speed:      " + tower.getSpeedCost(),653,602);
                            app.fill(255);
                            app.rect(650,608,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getSpeedCost() >= 100) {
                                app.text("Total:      " + tower.getSpeedCost(), 653, 625);
                            }
                            else {
                                app.text("Total:       " + tower.getSpeedCost(), 653, 625);
                            }
                        }
                    }
                    else {
                        if (this.upgradeDamage) {
                            app.strokeWeight(1);
                            app.fill(255);
                            app.rect(650, 564, 99,22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("Upgrade cost", 653, 581);
                            app.fill(255);
                            app.rect(650, 586, 99, 22);
                            app.fill(0);
                            app.textSize(14);
                            app.text("damage:   " + tower.getDamageCost(),653,602);
                            app.fill(255);
                            app.rect(650,608,99,22);
                            app.fill(0);
                            app.textSize(14);
                            if (tower.getDamageCost() >= 100) {
                                app.text("Total:      " + tower.getDamageCost(), 653, 625);
                            }
                            else {
                                app.text("Total:       " + tower.getDamageCost(), 653, 625);
                            }
                        }
                    }
                }
                break;
            }
        }
    }
}
