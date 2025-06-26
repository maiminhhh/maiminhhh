package WizardTD;
import processing.core.PApplet;
import org.junit.jupiter.api.*;
import processing.core.PImage;

import static org.junit.jupiter.api.Assertions.*;

public class StaticGraphicsTest {
    private StaticGraphics staticGraphic;
    @BeforeEach
    public void setUp() {
        int x = 365;
        int y = 72;
        this.staticGraphic = new StaticGraphics(x, y);
    }
    @Test
    public void testConstruction() {
        assertEquals(365, this.staticGraphic.getX());
        assertEquals(72, this.staticGraphic.getY());
    }
    @Test
    public void testSetSprite() {
        this.staticGraphic.setSprite(null);
        assertNull(this.staticGraphic.getSprite());
    }
    @Test
    public void testSetX() {
        this.staticGraphic.setX(77);
        assertEquals(77, this.staticGraphic.getX());
    }
    @Test
    public void testSetY() {
        this.staticGraphic.setY(83);
        assertEquals(83, this.staticGraphic.getY());
    }
}
