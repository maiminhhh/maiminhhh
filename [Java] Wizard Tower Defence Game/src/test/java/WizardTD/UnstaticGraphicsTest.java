package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnstaticGraphicsTest {
    private UnstaticGraphics unstatic;
    private PApplet app;
    @BeforeEach
    public void setUp() {
        float speed = 5;
        this.unstatic = new UnstaticGraphics(speed, null);
        this.app = new PApplet();
    }
    @Test
    public void testConstruction() {
        assertEquals(5, this.unstatic.getSpeed());
        assertFalse(this.unstatic.isDisappear());
        assertSame(null, this.unstatic.getSprite());
        assertEquals(0, this.unstatic.getX());
        assertEquals(0, this.unstatic.getY());
    }
    @Test
    public void testSetDisappearTrue() {
        this.unstatic.setDisappear(true);
        assertTrue(this.unstatic.isDisappear());
    }
    @Test
    public void testSetDisappearFalse() {
        this.unstatic.setDisappear(false);
        assertFalse(this.unstatic.isDisappear());
    }
    @Test
    public void testSetSprite() {
        this.unstatic.setSprite(null);
        assertSame(null, this.unstatic.getSprite());
    }
}
