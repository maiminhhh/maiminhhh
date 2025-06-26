package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;

public class App extends PApplet {

    public static final int CELLSIZE = 32;
    public static final int SIDEBAR = 120;
    public static final int TOPBAR = 40;
    public static final int BOARD_WIDTH = 20;
    public static int WIDTH = CELLSIZE * BOARD_WIDTH + SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH * CELLSIZE + TOPBAR;
    public static final int FPS = 60;
    public String configPath;
    public Map map;
    public JSONObject gameInfo;
    public GUITop guiTop;
    public ArrayList<Monsters> totalMonstersList = new ArrayList<>();
    public int currentFrame = 0;
    private boolean spawning = false;
    private int monsterSpawned = 0;
    private int spawningStartFrame = 0;
    private Waves currentWave;
    private ArrayList<Waves> waveList;
    private int speed = 1;
    private GUIRight guiRight;
    private Wizard wizardHouse;
    private int startingFrame = 0;
    private ArrayList<Tower> towers;
    private boolean lose = false;
    private boolean win = false;
    private PImage fireBallSprite;
    private PImage tower0;
    private PImage tower1;
    private PImage tower2;
    
    // Feel free to add any additional methods or attributes you want. Please put
    // classes in different files.

    public App() {
        this.configPath = "config.json";
    }

    

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        this.gameInfo = loadJSONObject(this.configPath);
        String layoutPath = this.gameInfo.getString("layout");
        this.map = new Map(layoutPath);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the
     * player, enemies and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);
        PImage pathImage0 = this.loadImage("src/main/resources/WizardTD/path0.png");
        PImage pathImage1 = this.loadImage("src/main/resources/WizardTD/path1.png");
        PImage pathImage2 = this.loadImage("src/main/resources/WizardTD/path2.png");
        PImage pathImage3 = this.loadImage("src/main/resources/WizardTD/path3.png");
        PImage shrubImage = this.loadImage("src/main/resources/WizardTD/shrub.png");
        PImage grassImage = this.loadImage("src/main/resources/WizardTD/grass.png");
        PImage wizardHouseImage = this.loadImage("src/main/resources/WizardTD/wizard_house.png");
        this.tower0 = this.loadImage("src/main/resources/WizardTD/tower0.png");
        this.tower1 = this.loadImage("src/main/resources/WizardTD/tower1.png");
        this.tower2 = this.loadImage("src/main/resources/WizardTD/tower2.png");
        this.fireBallSprite = this.loadImage("src/main/resources/WizardTD/fireball.png");
        this.map.addGraphics(pathImage0, this.rotateImageByDegrees(pathImage0, 90), pathImage1, this.rotateImageByDegrees(pathImage1, 90), this.rotateImageByDegrees(pathImage1, 180), this.rotateImageByDegrees(pathImage1, 270), pathImage2, this.rotateImageByDegrees(pathImage2, 90), this.rotateImageByDegrees(pathImage2, 180), this.rotateImageByDegrees(pathImage2, 270), pathImage3, shrubImage, grassImage, wizardHouseImage, this.rotateImageByDegrees(wizardHouseImage, 90), this.rotateImageByDegrees(wizardHouseImage, 180), this.rotateImageByDegrees(wizardHouseImage, 270));
        this.map.setSurrounding();
        JSONArray wavesJSONArray = this.gameInfo.getJSONArray("waves");
        int initialMana = this.gameInfo.getInt("initial_mana");
        int initialManaCap = this.gameInfo.getInt("initial_mana_cap");
        int initialManaGainedPerSec = this.gameInfo.getInt("initial_mana_gained_per_second");
        int manaPoolSpellInitialCost = this.gameInfo.getInt("mana_pool_spell_initial_cost");
        int manaPoolSpellCostIncreasePerUse = this.gameInfo.getInt("mana_pool_spell_cost_increase_per_use");
        float manaPoolSpellCapMultiplier = this.gameInfo.getFloat("mana_pool_spell_cap_multiplier");
        float manaPoolSpellManaGainedMultiplier = this.gameInfo.getFloat("mana_pool_spell_mana_gained_multiplier");
        this.wizardHouse = new Wizard(initialMana, initialManaCap, initialManaGainedPerSec);
        this.guiTop = new GUITop(wavesJSONArray, wizardHouse);
        this.guiTop.addWaves(this.loadImage("src/main/resources/WizardTD/beetle.png"), this.loadImage("src/main/resources/WizardTD/gremlin.png"), this.loadImage("src/main/resources/WizardTD/worm.png"));
        this.waveList = this.guiTop.getWaves();
        for (Waves wave : this.waveList) {
            for (Monsters monster : wave.getMonsters()) {
                monster.setWizardHouse(this.wizardHouse);
                monster.launch(this.map);
            }
        }
        this.guiRight = new GUIRight(manaPoolSpellInitialCost, manaPoolSpellCostIncreasePerUse,this.gameInfo.getInt("tower_cost"),manaPoolSpellCapMultiplier, manaPoolSpellManaGainedMultiplier);
        this.towers = new ArrayList<>();
    }

    /**
     * reset the game setting to the beginning of the game
     */
    public void reset() {
        if (this.lose) {
            this.settings();
            this.totalMonstersList = new ArrayList<>();
            this.currentFrame = 0;
            this.spawning = false;
            this.monsterSpawned = 0;
            this.spawningStartFrame = 0;
            this.waveList = new ArrayList<>();
            this.speed = 1;
            this.startingFrame = 0;
            this.towers.clear();
            this.setup();
        }
        if (this.win) {
            this.totalMonstersList = new ArrayList<>();
            this.currentFrame = 0;
            this.spawning = false;
            this.monsterSpawned = 0;
            this.spawningStartFrame = 0;
            this.waveList = new ArrayList<>();
            this.speed = 1;
            this.startingFrame = 0;
            this.towers.clear();
            this.gameInfo = new JSONObject();
            NewGame newGame = new NewGame();
            this.gameInfo = newGame.constructNewGame();
            String layoutPath = this.gameInfo.getString("layout");
            this.map = new Map(layoutPath);
            this.setup();
        }
        this.lose = false;
        this.win = false;
    }


    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed() {
        if (key == 'f') {
            this.guiRight.setFaster(!this.guiRight.isFaster());
            this.startingFrame = this.currentFrame;
        }
        else if (key == 'p') {
            this.guiRight.setPause(!this.guiRight.isPausing());
        }
        else if (key == 't') {
            this.guiRight.setBuildTower(!this.guiRight.isBuildingTower());
        }
        else if (key == '1') {
            this.guiRight.setUpgradeRange(!this.guiRight.isUpgradingRange());
        }
        else if (key == '2') {
            this.guiRight.setUpgradeSpeed(!this.guiRight.isUpgradingSpeed());
        }
        else if (key == '3') {
            this.guiRight.setUpgradeDamage(!this.guiRight.isUpgradingDamage());
        }
        else if (key == 'm') {
            this.guiRight.setManaPoolSpell(!this.guiRight.isCastingSpell());
            if (this.guiRight.isCastingSpell()) {
                this.guiRight.getSpell().cast(this.totalMonstersList, this.wizardHouse);
                this.guiRight.setManaPoolSpell(false);
            }
        }
        if (this.lose) {
            if (key == 'r') {
                this.reset();
            }
        }
        if (this.win) {
            if (key == 'n') {
                this.reset();
            }
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */

    @Override
    public void mouseClicked() {
        if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 48 && this.mouseY <=48+40)) {
            this.guiRight.setFaster(!this.guiRight.isFaster());
            this.startingFrame = this.currentFrame;
        }
        else if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 102 && this.mouseY <=102+40)) {
            this.guiRight.setPause(!this.guiRight.isPausing());
        }
        else if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 156 && this.mouseY <=156+40)) {
            this.guiRight.setBuildTower(!this.guiRight.isBuildingTower());
        }
        else if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 210 && this.mouseY <=210+40)) {
            this.guiRight.setUpgradeRange(!this.guiRight.isUpgradingRange());
        }
        else if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 264 && this.mouseY <=264+40)) {
            this.guiRight.setUpgradeSpeed(!this.guiRight.isUpgradingSpeed());
        }
        else if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 318 && this.mouseY <=318+40)) {
            this.guiRight.setUpgradeDamage(!this.guiRight.isUpgradingDamage());
        }
        else if ((this.mouseX >= 650 && this.mouseX <= 650+40) && (this.mouseY >= 372 && this.mouseY <=372+40)) {
            this.guiRight.setManaPoolSpell(!this.guiRight.isCastingSpell());
            if (this.guiRight.isCastingSpell()) {
                this.guiRight.getSpell().cast(this.totalMonstersList, this.wizardHouse);
                this.guiRight.setManaPoolSpell(false);
            }
        }
        else if ((this.mouseX > 0 && this.mouseX < 640) && (this.mouseY > 40 && this.mouseY < 680)) {
            for (Structure[] row : this.map.getMapPlan()) {
                for (Structure tile : row) {
                    if ((this.mouseX > tile.getX() && this.mouseX < tile.getX() + 32) && (this.mouseY > tile.getY() && this.mouseY < tile.getY()+32)) {
                        if (tile.isBuildable()) {
                            if (this.guiRight.isBuildingTower()) {
                                Tower towerConsidered = new Tower(this.gameInfo.getInt("tower_cost"), this.gameInfo.getFloat("initial_tower_range"), this.gameInfo.getFloat("initial_tower_firing_speed"), this.gameInfo.getInt("initial_tower_damage"), tile.getX(), tile.getY());
                                if (this.wizardHouse.getMana() > towerConsidered.getBuildCost()) {
                                    this.towers.add(towerConsidered);
                                    this.wizardHouse.buildTower(this.towers.get(this.towers.size() - 1));
                                    tile.setBuildable(false);
                                }
                            }
                        }
                        for (Tower tower : this.towers) {
                            if (tile.getX() == tower.getX() && tile.getY() == tower.getY()) {
                                if (this.guiRight.isUpgradingRange()) {
                                    this.wizardHouse.upgradeRange(tower);
                                }
                                if (this.guiRight.isUpgradingSpeed()) {
                                    this.wizardHouse.upgradeSpeed(tower);
                                }
                                if (this.guiRight.isUpgradingDamage()) {
                                    this.wizardHouse.upgradeDamage(tower);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * @Override
     * public void mouseDragged(MouseEvent e) {
     * 
     * }
     */

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {
        this.guiRight.updateTowers(this.towers);
        this.guiTop.draw(this);
        this.map.draw(this);
        this.guiRight.draw(this);

        if (this.guiRight.isFaster()) {
            this.speed = 2;
        }
        else {
            this.speed = 1;
        }

        if ((int) this.guiTop.getWaitingWave().getPreWavePause() == 0 && this.monsterSpawned == 0) {
            if (this.guiTop.getWaveCount() < this.waveList.size()) {
                this.currentWave = this.waveList.get(this.guiTop.getWaveCount());
                this.spawning = true;
            }
            else {
                this.spawning = false;
            }
            this.spawningStartFrame = this.currentFrame;
        }

        if (this.spawning) {
            if (this.currentWave.getMonsters()!=null) {
                if (this.currentWave.getMonsters().size()!=0) {
                    if (this.monsterSpawned == 0) {
                        this.totalMonstersList.add(this.currentWave.getMonsters().get(this.monsterSpawned));
                        this.monsterSpawned++;
                    } else if (this.monsterSpawned == this.currentWave.getMonsters().size()) {
                        this.spawning = false;
                        this.monsterSpawned = 0;
                    } else {
                        if (this.currentFrame - this.spawningStartFrame >= this.currentWave.getSpawnInterval() * 60) {
                            this.totalMonstersList.add(this.currentWave.getMonsters().get(this.monsterSpawned));
                            this.monsterSpawned++;
                            this.spawningStartFrame = this.currentFrame;
                        }
                    }
                }
            }
        }

        for (Monsters monster : this.totalMonstersList) {
            monster.drawMana(this);
            monster.draw(this);
            monster.drawDeath(this);
            if (!this.guiRight.isPausing() && !this.win && !this.lose) {
                monster.move(this.speed);
            }
        }

        if (this.guiTop.getWaitingWave().getPreWavePause() != (int)this.guiTop.getWaitingWave().getPreWavePause()) {
            double diff = this.guiTop.getWaitingWave().getPreWavePause() - (int) this.guiTop.getWaitingWave().getPreWavePause();
            diff = diff - (int) diff;
            if (this.currentFrame == diff*FPS) {
                this.guiTop.tick();
            }
        }
        else if (this.guiTop.getLastWave().getDuration() != (int) this.guiTop.getLastWave().getDuration()) {
            double diff = this.guiTop.getLastWave().getDuration() - (int) this.guiTop.getLastWave().getDuration();
            diff = diff - (int) diff;
            if (this.currentFrame == diff*FPS) {
                this.guiTop.tick();
            }
        }
        for (Tower tower : this.towers) {
            tower.setSprite(this.tower0, this.tower1, this.tower2);
            tower.draw(this);
            tower.drawSign(this);
        }

        if (this.wizardHouse.getMana() <= 0) {
            this.lose = true;
        }
        else {
            this.win = true;
            int totalMonsters = 0;
            for (Waves wave : this.guiTop.getWaves()) {
                totalMonsters += wave.getMonsters().size();
            }
            if (totalMonsters > this.totalMonstersList.size()) {
                this.win = false;
            }
            else {
                for (Monsters monster : this.totalMonstersList) {
                    if (!monster.isDisappear()) {
                        this.win = false;
                        break;
                    }
                }
            }
        }

        if (this.win) {
            this.fill(213, 24, 197);
            this.textSize(36);
            this.text("YOU WIN", 235, 230);
            this.textSize(22);
            this.text("Press 'n' to get to the next level", 230, 268);
            return;
        }
        if (this.lose) {
            this.fill(64, 231, 90);
            this.textSize(36);
            this.text("YOU LOST", 235, 230);
            this.textSize(22);
            this.text("Press 'r' to restart", 230, 268);
            return;
        }

        if (this.guiRight.isPausing()) {
            return;
        }

        if ((this.currentFrame - this.startingFrame) % (60/this.speed) == 0) {
            this.guiTop.tick();
            this.wizardHouse.timePassed();
        }

        if (this.towers!=null) {
            for (Tower tower : this.towers) {
                if (tower!=null) {
                    tower.setEnemiesInRange(this.totalMonstersList);
                    tower.generateFireBall(this.currentFrame, this.speed, this.fireBallSprite);
                    if (tower.getFireBalls()!=null) {
                        for (FireBall fireBall : tower.getFireBalls()) {
                            if (fireBall!=null) {
                                if (fireBall.getTarget() == null) {
                                    fireBall.setTarget();
                                }
                                fireBall.move(this.speed);
                                fireBall.draw(this);
                            }
                        }
                    }
                    tower.resetEnemiesInRange();
                }
            }
        }
        this.currentFrame ++;
    }

    public static void main(String[] args) {
        PApplet.main("WizardTD.App");
    }

    /**
     * Source:
     * https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java
     * 
     * @param pimg  The image to be rotated
     * @param angle between 0 and 360 degrees
     * @return the new rotated image
     */
    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = this.createImage(newWidth, newHeight, ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((float) (newWidth - w) / 2, (float)(newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }
        return result;
    }
}
