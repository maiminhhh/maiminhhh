package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;
import processing.core.PImage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {
    private Tower tower;

    @BeforeEach
    public void setup() {
        int cost = 50;
        float range = 100;
        float firingSpeed = 3;
        int damage = 20;
        int x = 3;
        int y = 5;
        this.tower = new Tower(cost, range, firingSpeed, damage, x, y);
    }

    @Test
    public void testUpgradeRangeHighLevel() {
        for (int i = 0; i < 5; i++) {
            this.tower.upgradeRange();
        }
        this.tower.upgradeRange();
        assertEquals(260, this.tower.getRange());
        assertEquals(70, this.tower.getRangeCost());
    }

    @Test
    public void testUpgradeSpeedHighLevel() {
        for (int i = 0; i < 5; i++) {
            this.tower.upgradeSpeed();
        }
        this.tower.upgradeSpeed();
        assertEquals(5.5, this.tower.getSpeed());
        assertEquals(70, this.tower.getSpeedCost());
    }

    @Test
    public void testUpgradeDamageHighLevel() {
        for (int i = 0; i < 5; i++) {
            this.tower.upgradeDamage();
        }
        this.tower.upgradeDamage();
        assertEquals(70, this.tower.getDamage());
        assertEquals(70, this.tower.getDamageCost());
    }

    @Test
    public void testUpgradeRangeAllLvIncrease() {
        this.tower.upgradeSpeed();
        this.tower.upgradeDamage();
        this.tower.upgradeRange();
        assertEquals(2, this.tower.getAllLv());
        assertEquals(0, this.tower.getRangeLvGraphed());
        assertEquals(0, this.tower.getSpeedLvGraphed());
        assertEquals(0, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testUpgradeSpeedAllLvIncrease() {
        this.tower.upgradeDamage();
        this.tower.upgradeRange();
        this.tower.upgradeSpeed();
        assertEquals(2, this.tower.getAllLv());
        assertEquals(0, this.tower.getRangeLvGraphed());
        assertEquals(0, this.tower.getSpeedLvGraphed());
        assertEquals(0, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testUpgradeDamageAllLvIncrease() {
        this.tower.upgradeRange();
        this.tower.upgradeSpeed();
        this.tower.upgradeDamage();
        assertEquals(2, this.tower.getAllLv());
        assertEquals(0, this.tower.getRangeLvGraphed());
        assertEquals(0, this.tower.getSpeedLvGraphed());
        assertEquals(0, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testUpgradeSpeedAllLvEquals3() {
        for (int i = 0; i < 3; i++) {
            this.tower.upgradeSpeed();
            this.tower.upgradeRange();
            this.tower.upgradeDamage();
        }
        this.tower.upgradeSpeed();
        assertEquals(4, this.tower.getAllLv());
        assertEquals(0, this.tower.getRangeLvGraphed());
        assertEquals(1, this.tower.getSpeedLvGraphed());
        assertEquals(0, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testUpgradeRangeAllLvEquals3() {
        for (int i = 0; i < 3; i++) {
            this.tower.upgradeDamage();
            this.tower.upgradeSpeed();
            this.tower.upgradeRange();
        }
        this.tower.upgradeRange();
        assertEquals(4, this.tower.getAllLv());
        assertEquals(1, this.tower.getRangeLvGraphed());
        assertEquals(0, this.tower.getSpeedLvGraphed());
        assertEquals(0, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testUpgradeDamageAllLvEquals3() {
        for (int i = 0; i < 3; i++) {
            this.tower.upgradeRange();
            this.tower.upgradeDamage();
            this.tower.upgradeSpeed();
        }
        this.tower.upgradeDamage();
        assertEquals(4, this.tower.getAllLv());
        assertEquals(0, this.tower.getRangeLvGraphed());
        assertEquals(0, this.tower.getSpeedLvGraphed());
        assertEquals(1, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testUpgradeRangeAllLvExceeds3() {
        for (int i = 0; i < 3; i++) {
            this.tower.upgradeRange();
            this.tower.upgradeSpeed();
            this.tower.upgradeDamage();
        }
        this.tower.upgradeSpeed();
        this.tower.upgradeRange();
        this.tower.upgradeDamage();
        assertEquals(4, this.tower.getAllLv());
        assertEquals(1, this.tower.getRangeLvGraphed());
        assertEquals(1, this.tower.getSpeedLvGraphed());
        assertEquals(1, this.tower.getDamageLvGraphed());
    }

    @Test
    public void testSetEnemiesInRangeNullMonstersList() {
        this.tower.setEnemiesInRange(null);
        assertEquals(0, this.tower.getEnemiesInRange().size());
    }

    @Test
    public void testSetEnemiesInRangeNullSprite() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(null, 37, 2, 1, 64);
        monsters.add(monster1);
        this.tower.setEnemiesInRange(monsters);
        assertEquals(0, this.tower.getEnemiesInRange().size());
    }

    @Test
    public void testSetEnemiesInRangeFarAwayMonsters() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(23, 57), 37, 2, 1, 64);
        monster1.setX(1370);
        monster1.setY(2530);
        monsters.add(monster1);
        this.tower.setEnemiesInRange(monsters);
        assertEquals(0, this.tower.getEnemiesInRange().size());
    }

    @Test
    public void testGenerateFireBallsNoEnemiesInRange() {
        this.tower.setEnemiesInRange(null);
        this.tower.generateFireBall(27, 1, new PImage(23, 74));
        assertEquals(0, this.tower.getFireBalls().size());
    }

    @Test
    public void testGenerateFireBallsNoneFireBalls() {
        Monsters monster1 = new Monsters(new PImage(20, 40), 37, 2, 1, 64);
        ArrayList<Monsters> monsters = new ArrayList<>();
        monsters.add(monster1);
        this.tower.setEnemiesInRange(monsters);
        this.tower.generateFireBall(27, 1, new PImage(23, 74));
        assertEquals(1, this.tower.getFireBalls().size());
    }

    @Test
    public void testGenerateFireBallWithElementsInFireBalls() {
        Monsters monster1 = new Monsters(new PImage(20, 40), 37, 2, 1, 64);
        ArrayList<Monsters> monsters = new ArrayList<>();
        monsters.add(monster1);
        this.tower.setEnemiesInRange(monsters);
        this.tower.generateFireBall(27, 1, new PImage(23, 74));
        this.tower.generateFireBall(47, 1, new PImage(23, 74));
        assertEquals(2, this.tower.getFireBalls().size());
    }

    @Test
    public void testGenerateFireBallBadTiming() {
        Monsters monster1 = new Monsters(new PImage(20, 40), 37, 2, 1, 64);
        ArrayList<Monsters> monsters = new ArrayList<>();
        monsters.add(monster1);
        this.tower.setEnemiesInRange(monsters);
        this.tower.generateFireBall(27, 1, new PImage(23, 74));
        this.tower.generateFireBall(33, 1, new PImage(23, 74));
        assertEquals(1, this.tower.getFireBalls().size());
    }
    @Test
    public void testSetSpriteLv1() {
        this.tower.setSprite(null, new PImage(73, 25), new PImage(64, 47));
        assertNull(this.tower.getSprite());
    }
    @Test
    public void testSetSpriteLv2(){
        this.tower.setAllLv(2);
        this.tower.setSprite(null, new PImage(73, 25), new PImage(64, 47));
        assertEquals(73, this.tower.getSprite().pixelWidth);
        assertEquals(25, this.tower.getSprite().pixelHeight);
    }
    @Test
    public void testSetSpriteLv3() {
        this.tower.setAllLv(3);
        this.tower.setSprite(null, new PImage(73, 25), new PImage(64, 47));
        assertEquals(64, this.tower.getSprite().pixelWidth);
        assertEquals(47, this.tower.getSprite().pixelHeight);
    }
    @Test
    public void testSetSpriteLv0() {
        this.tower.setAllLv(0);
        this.tower.setSprite(new PImage(77, 29), new PImage(73, 25), new PImage(64, 47));
        assertNull(this.tower.getSprite());
        }
}
