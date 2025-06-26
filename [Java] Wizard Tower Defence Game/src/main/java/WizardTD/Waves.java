package WizardTD;
import java.util.*;
public class Waves {
    private float pre_wave_pause;
    private float duration;
    private ArrayList<Monsters> monsters;
    private float spawnInterval;
    public Waves(float pre_wave_pause, float duration, ArrayList<Monsters> monsters) {
        this.pre_wave_pause = pre_wave_pause;
        this.duration = duration;
        this.monsters = monsters;
        if (this.monsters != null && this.monsters.size() != 0) {
            this.spawnInterval = (float) ((this.duration / this.monsters.size()));
        }
        else {
            this.spawnInterval = 0;
        }
    }
    public float getPreWavePause() {
        return this.pre_wave_pause;
    }
    public float getDuration() {
        return this.duration;
    }
    public void setDuration(float duration) {
        this.duration = duration;
    }
    public void setPrewavePause(float preWavePause) {
        this.pre_wave_pause = preWavePause;
    }
    public ArrayList<Monsters> getMonsters() {
        return this.monsters;
    }
    public void timePassed() {
        this.duration--;
    }
    public void preWaveTimePassed() {
        this.pre_wave_pause--;
    }
    public float getSpawnInterval() {
        return this.spawnInterval;
    }
}