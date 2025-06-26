package WizardTD;
import java.util.*;
import java.lang.*;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.core.PApplet;
import processing.core.PImage;
public class GUITop {
    //class attributes
    final private JSONArray wavesJSONArray;
    private ArrayList<Waves> waves;
    private Waves waitingWave;
    private Waves lastWave;
    private int waveCount;
    private float timer;
    private Wizard wizardHouse;

    /**
     * constructor method
     * @param wavesJSONArray all the information related to the waves
     * @param wizardHouse the final destination of all the monsters
     */
    public GUITop(JSONArray wavesJSONArray, Wizard wizardHouse){
        this.wavesJSONArray = wavesJSONArray;
        this.waves = new ArrayList<>();
        this.waveCount = 0;
        this.wizardHouse = wizardHouse;
    }

    /**
     * get all the waves
     * @return all the waves within the level
     */
    public ArrayList<Waves> getWaves() {
        return this.waves;
    }

    /**
     * get the wave being waited
     * @return the upcoming wave
     */
    public Waves getWaitingWave() {
        return this.waitingWave;
    }

    /**
     * get the previous wave
     * @return the previous wave
     */
    public Waves getLastWave() {
        return this.lastWave;
    }

    /**
     * get the current index of wave
     * @return the current index of wave
     */
    public int getWaveCount() {
        return this.waveCount;
    }

    /**
     * extract the information of the waves
     * @param beetleImage the image of mosters of type beetle
     * @param gremlinImage the image of monsters of type gremlin
     * @param wormImage the image of monsters of type worm
     */
    public void addWaves(PImage beetleImage, PImage gremlinImage, PImage wormImage){
        for (int i=0; i < this.wavesJSONArray.size(); i++) {
            JSONObject waveJSONObject = (JSONObject) this.wavesJSONArray.get(i);
            float duration = waveJSONObject.getFloat("duration");
            float preWavePause = waveJSONObject.getFloat("pre_wave_pause");
            JSONArray monstersJSONArray = (JSONArray) waveJSONObject.get("monsters");
            ArrayList<Monsters> monsters = new ArrayList<>();
            if (monstersJSONArray!=null) {
                for (int m=0; m<monstersJSONArray.size(); m++) {
                    JSONObject monsterJSONObject = (JSONObject) monstersJSONArray.get(m);
                    String type = monsterJSONObject.getString("type");
                    float hp = monsterJSONObject.getFloat("hp");
                    float speed = monsterJSONObject.getFloat("speed");
                    float armour = monsterJSONObject.getFloat("armour");
                    int manaGainedOnKill = monsterJSONObject.getInt("mana_gained_on_kill");
                    int quantity = monsterJSONObject.getInt("quantity");
                    if (type.equals("beetle")) {
                        for (int times = 0; times < quantity; times++) {
                            monsters.add(new Monsters(beetleImage, hp, speed, armour, manaGainedOnKill));
                        }
                    } else if (type.equals("gremlin")) {
                        for (int times = 0; times < quantity; times++) {
                            monsters.add(new Monsters(gremlinImage, hp, speed, armour, manaGainedOnKill));
                        }
                    } else if (type.equals("worm")) {
                        for (int times = 0; times < quantity; times++) {
                            monsters.add(new Monsters(wormImage, hp, speed, armour, manaGainedOnKill));
                        }
                    }
                }
            }
            Collections.shuffle(monsters);
            this.waves.add(new Waves(preWavePause, duration, monsters));
        }
        this.waitingWave = this.waves.get(this.waveCount);
        this.lastWave = new Waves(0,0,null);
        this.timer = (float) this.waitingWave.getPreWavePause();
    }

    /**
     * get the total waiting time
     * @return the total waiting time
     */
    public float getTimer() {
        return this.timer;
    }

    /**
     * set the total waiting time
     */
    public void setTimer() {
        this.timer = (float) (waitingWave.getPreWavePause() + lastWave.getDuration());
    }

    /**
     * the moving logic of the time
     */
    public void tick() {
        if (this.waveCount == this.waves.size()-1) {
            if (this.timer == 0) {
                this.waveCount += 1;
            }
        }
        if (this.waveCount == 0) {
            if (this.waitingWave.getPreWavePause()>0) {
                this.waitingWave.preWaveTimePassed();
            }
            if (this.waitingWave.getPreWavePause()<=0) {
                this.waitingWave.setPrewavePause(0);
            }
        }
        else if (this.waveCount < this.waves.size()) {
            if (this.lastWave.getDuration()>0) {
                this.lastWave.timePassed();
            }
            else {
                if (this.waitingWave.getPreWavePause()>0) {
                    this.waitingWave.preWaveTimePassed();
                }
            }
        }
        if ((int) this.timer == 0) {
            if (this.waveCount < this.waves.size()-1) {
                this.waveCount += 1;
                this.waitingWave = this.waves.get(this.waveCount);
                this.lastWave = this.waves.get(this.waveCount - 1);
            }
        }
        this.setTimer();
    }

    /**
     * draw function
     * @param app PApplet object on which images were drawn
     */
    public void draw(PApplet app) {
        app.background(135, 114, 68);
        if (this.waveCount < this.waves.size()){
            app.fill(0);
            String timerNoti = "Wave " + (this.waveCount + 1) + " starts: " + (int) this.timer;
            app.textSize(23);
            app.text(timerNoti, 10, 30);
        }
        app.strokeWeight(2);
        app.fill(0);
        app.textSize(21);
        String mana = "MANA: ";
        app.text(mana, 310, 30);
        float barWidth = 320;
        app.stroke(0);
        app.fill(255, 255, 255);
        app.rect(385, 10, barWidth, 22);
        app.fill(0, 218, 216);
        app.stroke(0);
        app.rect(385, 10, (float) this.wizardHouse.getMana()/this.wizardHouse.getManaCap() * barWidth, 22);
        app.fill(0);
        app.textSize(20);
        String manaValue = this.wizardHouse.getMana() + " / " + this.wizardHouse.getManaCap();
        app.text(manaValue,15*32,30);
    }
}