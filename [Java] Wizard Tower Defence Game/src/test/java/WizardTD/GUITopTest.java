package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class GUITopTest {
    private GUITop guiOneWave;
    private GUITop guiTwoWaves;
    @BeforeEach
    public void setup() {
        JSONObject gremlin = new JSONObject();
        gremlin.put("type", "gremlin");
        gremlin.put("hp", 44);
        gremlin.put("speed", 1.2);
        gremlin.put("armour", 1.1);
        gremlin.put("mana_gained_on_kill", 46);
        gremlin.put("quantity", 27);
        JSONObject beetle = new JSONObject();
        beetle.put("type", "beetle");
        beetle.put("hp", 96);
        beetle.put("speed", 1);
        beetle.put("armour", 1.3);
        beetle.put("mana_gained_on_kill", 113);
        beetle.put("quantity", 13);
        JSONObject worm = new JSONObject();
        worm.put("type", "worm");
        worm.put("hp", 68);
        worm.put("speed", 1.5);
        worm.put("armour", 1.3);
        worm.put("mana_gained_on_kill", 103);
        worm.put("quantity", 4);
        JSONObject other = new JSONObject();
        other.put("type", "other");
        other.put("hp", 73);
        other.put("speed", 1.4);
        other.put("armour", 1.2);
        other.put("mana_gained_on_kill", 115);
        other.put("quantity", 19);
        int wizardMana = 100;
        int wizardManaCap = 400;
        int wizardManaGainedPerSec = 3;
        Wizard wizard = new Wizard(wizardMana, wizardManaCap, wizardManaGainedPerSec);
        JSONArray monstersJsonArray = new JSONArray();
        monstersJsonArray.append(gremlin);
        monstersJsonArray.append(beetle);
        monstersJsonArray.append(worm);
        monstersJsonArray.append(other);
        JSONObject waveJsonObj = new JSONObject();
        waveJsonObj.put("duration", 30);
        waveJsonObj.put("pre_wave_pause", 22);
        waveJsonObj.put("monsters", monstersJsonArray);
        JSONArray wavesJsonArray = new JSONArray();
        wavesJsonArray.append(waveJsonObj);
        this.guiOneWave = new GUITop(wavesJsonArray, wizard);
        JSONArray twoWavesJsonArray = new JSONArray();
        JSONObject waveTwo = new JSONObject();
        JSONObject waveOne = new JSONObject();
        waveOne.put("duration",49);
        waveOne.put("pre_wave_pause", 37);
        waveOne.put("monsters", new JSONArray());
        twoWavesJsonArray.append(waveOne);
        waveTwo.put("duration", 44);
        waveTwo.put("pre_wave_pause", 13);
        waveTwo.put("monsters", null);
        twoWavesJsonArray.append(waveTwo);
        this.guiTwoWaves = new GUITop(twoWavesJsonArray, wizard);
    }
    @Test
    public void testConstruction() {
        assertEquals(0, this.guiOneWave.getWaveCount());
    }
    @Test
    public void testAddWaves() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiOneWave.addWaves(beetleImage, gremlinImage, wormImage);
        this.guiOneWave.setTimer();
        assertEquals(1, this.guiOneWave.getWaves().size());
        assertEquals(30, this.guiOneWave.getWaitingWave().getDuration());
        assertEquals(22, this.guiOneWave.getWaitingWave().getPreWavePause());
        assertEquals(22, this.guiOneWave.getTimer());
        assertEquals(0, this.guiOneWave.getLastWave().getDuration());
    }
    @Test
    public void testTickLastWaveCountTimer22() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiOneWave.addWaves(beetleImage, gremlinImage, wormImage);
        this.guiOneWave.tick();
        assertEquals(21, this.guiOneWave.getTimer());
    }
    @Test
    public void testTickLastWaveCountTimer0() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiOneWave.addWaves(beetleImage, gremlinImage, wormImage);
        for (int i  = 0; i < 23; i++) {
            this.guiOneWave.tick();
        }
        assertEquals(0, this.guiOneWave.getTimer());
        assertEquals(0, this.guiOneWave.getWaitingWave().getPreWavePause());
    }
    @Test
    public void testTickTwoWavesTimer22() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiTwoWaves.addWaves(beetleImage, gremlinImage, wormImage);
        assertEquals(2, this.guiTwoWaves.getWaves().size());
        this.guiTwoWaves.getWaitingWave().setPrewavePause(0);
        this.guiTwoWaves.tick();
        this.guiTwoWaves.tick();
        assertEquals(49+13, this.guiTwoWaves.getTimer());
    }
    @Test
    public void testTickTwoWavesTimerWaveCountEqualsWaveSize() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiTwoWaves.addWaves(beetleImage, gremlinImage, wormImage);
        assertEquals(2, this.guiTwoWaves.getWaves().size());
        this.guiTwoWaves.getWaitingWave().setPrewavePause(0);
        this.guiTwoWaves.tick();
        this.guiTwoWaves.tick();
        this.guiTwoWaves.getWaitingWave().setPrewavePause(0);
        this.guiTwoWaves.getLastWave().setDuration(0);
        this.guiTwoWaves.tick();
        assertEquals(0, this.guiTwoWaves.getTimer());
    }
    @Test
    public void testTickTwoWavesReducingLastWaveDuration() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiTwoWaves.addWaves(beetleImage, gremlinImage, wormImage);
        this.guiTwoWaves.getWaitingWave().setPrewavePause(0);
        this.guiTwoWaves.tick();
        this.guiTwoWaves.tick();
        this.guiTwoWaves.tick();
        assertEquals(48+13, this.guiTwoWaves.getTimer());
    }
    @Test
    public void testTickTwoWavesReducingWaitingWavePreWaveTime() {
        PImage beetleImage = new PImage(48, 133);
        PImage gremlinImage = new PImage(127, 64);
        PImage wormImage = new PImage(413, 29);
        this.guiTwoWaves.addWaves(beetleImage, gremlinImage, wormImage);
        this.guiTwoWaves.getWaitingWave().setPrewavePause(0);
        this.guiTwoWaves.tick();
        this.guiTwoWaves.tick();
        this.guiTwoWaves.getLastWave().setDuration(0);
        this.guiTwoWaves.tick();

    }
}

