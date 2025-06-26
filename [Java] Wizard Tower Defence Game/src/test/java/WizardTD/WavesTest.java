package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WavesTest {
    private Waves waveWithMonsters;
    private Waves waveNullMonsters;
    private Waves waveWithoutMonsters;
    @BeforeEach
    public void setUp() {
        float preWavePause = 3;
        float duration = 33;
        ArrayList<Monsters> monsters = new ArrayList<>();
        Monsters monster1 = new Monsters(null, 37, 2, 1,64);
        Monsters monster2 = new Monsters(null, 49, 1, 3, 57);
        monsters.add(monster1);
        monsters.add(monster2);
        this.waveWithMonsters = new Waves(preWavePause, duration, monsters);
        this.waveNullMonsters = new Waves(preWavePause, duration, null);
        this.waveWithoutMonsters = new Waves(preWavePause, duration, new ArrayList<>());
    }
    @Test
    public void testWaveWithMonstersConstruction() {
        assertEquals(3, this.waveWithMonsters.getPreWavePause());
        assertEquals(33, this.waveWithMonsters.getDuration());
        assertEquals((float) 33/2, this.waveWithMonsters.getSpawnInterval());
    }
    @Test
    public void testWaveNullMonstersConstruction() {
        assertEquals(3, this.waveNullMonsters.getPreWavePause());
        assertEquals(33, this.waveNullMonsters.getDuration());
        assertEquals(0, this.waveNullMonsters.getSpawnInterval());
        assertSame(null, this.waveNullMonsters.getMonsters());
    }
    @Test
    public void testWaveWithoutMonstersConstruction() {
        assertEquals(3, this.waveWithoutMonsters.getPreWavePause());
        assertEquals(33, this.waveWithoutMonsters.getDuration());
        assertEquals(0, this.waveWithoutMonsters.getSpawnInterval());
        assertEquals(0, this.waveWithoutMonsters.getMonsters().size());
    }
    @Test
    public void testSetPreWavePause() {
        this.waveWithMonsters.setPrewavePause(7);
        assertEquals(7, this.waveWithMonsters.getPreWavePause());
    }
    @Test
    public void testSetDuration() {
        this.waveWithMonsters.setDuration(53);
        assertEquals(53, this.waveWithMonsters.getDuration());
    }
    @Test
    public void testTimePassed() {
        this.waveWithMonsters.timePassed();
        assertEquals(33-1, this.waveWithMonsters.getDuration());
    }
    @Test
    public void testPreWaveTimePassed() {
        this.waveWithMonsters.preWaveTimePassed();
        assertEquals(3-1, this.waveWithMonsters.getPreWavePause());
    }
}
