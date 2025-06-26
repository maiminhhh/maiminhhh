package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class FireBallTest {
    private FireBall fireBall;
    private PApplet app = new PApplet();
    @BeforeEach
    public void setup() {
        int cost = 50;
        float range = 200;
        float firingSpeed = 3;
        int damage = 20;
        int x = 3;
        int y = 5;
        Tower tower = new Tower(cost, range, firingSpeed, damage, x, y);
        int shootingFrame = 177;
        this.fireBall = new FireBall(tower, shootingFrame,null);
    }
    @Test
    public void testConstruction() {
        assertEquals(177, this.fireBall.getShootingFrame());
        assertEquals(50, this.fireBall.getTower().getBuildCost());
        assertEquals(200, this.fireBall.getTower().getRange());
        assertEquals(3, this.fireBall.getTower().getSpeed());
        assertEquals(20, this.fireBall.getTower().getDamage());
        assertEquals(3, this.fireBall.getTower().getX());
        assertEquals(5, this.fireBall.getTower().getY());
        assertEquals(19, this.fireBall.getX());
        assertEquals(21, this.fireBall.getY());
        assertFalse(this.fireBall.isDisappear());
        assertEquals(5, this.fireBall.getSpeed());
    }
    @Test
    public void testSetTargetNullMonsters() {
        this.fireBall.setTarget();
        assertNull(this.fireBall.getTarget());
    }
    @Test
    public void testSetTargetWithMonstersInRange() {
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        Monsters monster2 = new Monsters(new PImage(25, 66), 49, 1, 3, 57);
        ArrayList<Monsters> monsters = new ArrayList<>();
        monsters.add(monster1);
        monsters.add(monster2);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        assertTrue(this.fireBall.getTarget().equals(monster1) || this.fireBall.getTarget().equals(monster2));
    }
    @Test
    public void testSetTargetWithValidTowerTarget() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monsters.add(monster1);
        Monsters monster2 = new Monsters(new PImage(25, 66), 49, 1, 3, 57);
        monsters.add(monster2);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.getTower().setTarget();
        this.fireBall.setTarget();
        assertTrue(monsters.contains(this.fireBall.getTarget()) && monsters.contains(this.fireBall.getTower().getTarget()));
    }
    @Test
    public void testSetTargetWithDeadTarget() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monsters.add(monster1);
        Monsters monster2 = new Monsters(new PImage(25, 66), 49, 1, 3, 57);
        monsters.add(monster2);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.getTower().setTarget();
        Monsters previousTarget = this.fireBall.getTower().getTarget();
        this.fireBall.getTower().getTarget().setDisappear(true);
        this.fireBall.getTower().resetEnemiesInRange();
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        assertNotSame(previousTarget, this.fireBall.getTarget());
    }
    @Test
    public void testSetTargetWithTargetNotInRange() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monsters.add(monster1);
        Monsters monster2 = new Monsters(new PImage(25, 66), 49, 1, 3, 57);
        monsters.add(monster2);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.getTower().setTarget();
        Monsters previousTarget = this.fireBall.getTower().getTarget();
        this.fireBall.getTower().resetEnemiesInRange();
        monsters.remove(this.fireBall.getTower().getTarget());
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        assertNotSame(previousTarget, this.fireBall.getTarget());
    }
    @Test
    public void testMoveNullTargetNotDisappear() {
        this.fireBall.setTarget();
        this.fireBall.move(73);
        assertTrue(this.fireBall.isDisappear());
    }
    @Test
    public void testMoveDistanceTouchTarget() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monsters.add(monster1);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        this.fireBall.move(1);
        assertSame(this.fireBall.getTarget(), monster1);
        assertEquals((float) (19-45/Math.sqrt(82)), this.fireBall.getX());
        assertEquals((float) (21-5/Math.sqrt(82)), this.fireBall.getY());
        assertTrue(this.fireBall.isDisappear());
        assertEquals(17, monster1.getCurrentHp());
    }
    @Test
    public void testMoveDeadTarget() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monsters.add(monster1);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        this.fireBall.getTarget().setDisappear(true);
        this.fireBall.move(1);
        assertTrue(this.fireBall.isDisappear());
    }
    @Test
    public void testMoveNoDistance() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monster1.setX(9);
        monster1.setY(1);
        monsters.add(monster1);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        this.fireBall.move(1);
        assertTrue(this.fireBall.isDisappear());
        assertEquals(17, this.fireBall.getTarget().getCurrentHp());
    }
    @Test
    public void testMoveNotNullTargetIsDisappear() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monsters.add(monster1);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        this.fireBall.setDisappear(true);
        this.fireBall.move(1);
        assertEquals(19, this.fireBall.getX());
        assertEquals(21, this.fireBall.getY());
        assertEquals(37, this.fireBall.getTarget().getCurrentHp());
    }
    @Test
    public void testMoveHaveJustTouchTarget() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monster1.setX(9);
        monster1.setY(11);
        monsters.add(monster1);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        this.fireBall.move(1);
        assertEquals(17, this.fireBall.getTarget().getCurrentHp());
        assertTrue(this.fireBall.isDisappear());
    }
    @Test
    public void testMoveNotTouchYet() {
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(new PImage(20,40), 37, 2, 1,64);
        monster1.setX(9);
        monster1.setY(15);
        monsters.add(monster1);
        this.fireBall.getTower().setEnemiesInRange(monsters);
        this.fireBall.setTarget();
        this.fireBall.move(1);
        assertEquals(37, this.fireBall.getTarget().getCurrentHp());
        assertFalse(this.fireBall.isDisappear());
    }
}
