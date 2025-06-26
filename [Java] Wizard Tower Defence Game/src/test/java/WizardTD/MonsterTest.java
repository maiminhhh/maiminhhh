package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {
    private Monsters monster;
    @BeforeEach
    public void setup() {
        PImage sprite = new PImage(72,377);
        float hp = 302;
        float speed = 2;
        float armour = 1;
        int manaGainedOnKill = 503;
        this.monster = new Monsters(sprite, hp, speed, armour, manaGainedOnKill);
    }
    @Test
    public void testConstruction() {
        assertEquals(302, this.monster.getCurrentHp());
        assertEquals(2, this.monster.getSpeed());
        assertEquals(503, this.monster.getManaGainedOnKill());
        assertNotNull(this.monster.getSprite());
    }
    @Test
    public void testSetWizardHouse() {
        this.monster.setWizardHouse(new Wizard(23, 77, 1));
        assertNotNull(this.monster.getWizardHouse());
    }
    @Test
    public void testDamagedNotDead() {
        this.monster.setWizardHouse(new Wizard(23, 77, 1));
        this.monster.damaged(7);
        assertEquals(302-7,this.monster.getCurrentHp());
        assertFalse(this.monster.isDisappear());
        assertEquals(23, this.monster.getWizardHouse().getMana());
    }
    @Test
    public void testDamagedDead() {
        this.monster.setWizardHouse(new Wizard(23, 77, 1));
        this.monster.damaged(570);
        assertEquals(0, this.monster.getCurrentHp());
        assertTrue(this.monster.isDisappear());
        assertEquals(77,this.monster.getWizardHouse().getMana());
    }
    @Test
    public void testMoveNullNextStructure() {
        this.monster.move(1);
        assertEquals(0, this.monster.getX());
        assertEquals(0, this.monster.getY());
    }
}