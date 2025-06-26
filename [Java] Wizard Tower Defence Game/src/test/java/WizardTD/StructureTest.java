package WizardTD;

import org.junit.jupiter.api.*;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class StructureTest {
    private Structure path;
    private Structure wizardHouse;
    private Structure grass;
    private Structure shrub;
    private Structure pathCol0;
    private Structure pathCol19;
    private Structure pathRow0;
    private Structure pathRow19;
    @BeforeEach
    public void setup() {
        int x = 32*84;
        int y = 41+32*15;
        this.path = new Structure(new PImage(32, 32), x, y, "path");
        this.wizardHouse = new Structure(new PImage(32, 32), x, y, "wizard house");
        this.grass = new Structure(new PImage(32, 32), x, y, "grass");
        this.shrub = new Structure(new PImage(32,32), x, y, "shrub");
        this.pathRow0 = new Structure(new PImage(32, 32), x, 41, "path");
        this.pathRow19 = new Structure(new PImage(32,32), x, 41+32*19, "path");
        this.pathCol0 = new Structure(new PImage(32, 32), 0, y, "path");
        this.pathCol19 = new Structure(new PImage(32, 32), 19*32, y, "path");

    }
    @Test
    public void testPathConstruction() {
        assertEquals(32*84, this.path.getX());
        assertEquals(41+32*15, this.path.getY());
        assertEquals(84, this.path.getCol());
        assertEquals(15, this.path.getRow());
        assertFalse(this.path.isStartable());
        assertTrue(this.path.isPathable());
        assertFalse(this.path.isDestination());
        assertFalse(this.path.isBuildable());
        for (Structure structure : this.path.getNeighbors()) {
            assertNull(structure);
        }
    }
    @Test
    public void testWizardHouseConstruction() {
        assertTrue(this.wizardHouse.isDestination());
        assertFalse(this.wizardHouse.isStartable());
        assertTrue(this.wizardHouse.isPathable());
        assertFalse(this.wizardHouse.isBuildable());
    }
    @Test
    public void testGrassConstruction() {
        assertFalse(this.grass.isPathable());
        assertTrue(this.grass.isBuildable());
        assertFalse(this.grass.isDestination());
        assertFalse(this.grass.isStartable());
    }
    @Test
    public void testShrubConstruction() {
        assertFalse(this.shrub.isPathable());
        assertFalse(this.shrub.isDestination());
        assertFalse(this.shrub.isBuildable());
        assertFalse(this.shrub.isStartable());
    }
    @Test
    public void testPathRow0Construction() {
        assertFalse(this.pathRow0.isBuildable());
        assertFalse(this.pathRow0.isDestination());
        assertTrue(this.pathRow0.isPathable());
    }
    @Test
    public void testPathRow19Construction() {
        assertFalse(this.pathRow19.isBuildable());
        assertFalse(this.pathRow19.isDestination());
        assertTrue(this.pathRow19.isStartable());
        assertTrue(this.pathRow19.isPathable());
    }
    @Test
    public void testPathCol0Construction() {
        assertFalse(this.pathCol0.isBuildable());
        assertFalse(this.pathCol0.isDestination());
        assertTrue(this.pathCol0.isStartable());
        assertTrue(this.pathCol0.isPathable());
    }
    @Test
    public void testPathCol19Construction() {
        assertFalse(this.pathCol19.isBuildable());
        assertFalse(this.pathCol19.isDestination());
        assertTrue(this.pathCol19.isStartable());
        assertTrue(this.pathCol19.isPathable());
    }
    @Test
    public void testSetBuildable() {
        this.grass.setBuildable(false);
        assertFalse(this.grass.isBuildable());
    }
    @Test
    public void testSetAbove() {
        this.path.setAbove(this.grass);
        assertSame(this.grass, this.path.getAbove());
    }
    @Test
    public void testSetBelow() {
        this.wizardHouse.setBelow(this.shrub);
        assertSame(this.shrub, this.wizardHouse.getBelow());
    }
    @Test
    public void testSetFront() {
        this.path.setFront(this.pathCol0);
        assertSame(this.pathCol0, this.path.getFront());
    }
    @Test
    public void testSetBack() {
        this.path.setBack(this.wizardHouse);
        assertSame(this.wizardHouse, this.path.getBack());
    }
    @Test
    public void testSetParent() {
        this.wizardHouse.setParent(this.grass);
        assertSame(this.grass, this.wizardHouse.getParent());
    }
    @Test
    public void testSetG() {
        this.path.setG(324);
        assertEquals(324, this.path.getG());
    }
    @Test
    public void testSetH() {
        int h = this.pathCol19.h(this.wizardHouse);
        this.pathCol19.setH(h);
        assertEquals(65, this.pathCol19.getH());
    }
}
