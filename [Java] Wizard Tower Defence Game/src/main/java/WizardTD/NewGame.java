package WizardTD;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.*;
public class NewGame {
    private Random rand;
    final private String[] POSSIBLE_LAYOUTS = {"level1.txt", "level2.txt", "level3.txt", "level4.txt"};
    private final String[] POSSIBLE_MONSTER_TYPES = {"gremlin", "beetle", "worm"};
    public NewGame() {
        this.rand = new Random();
    }
    public JSONObject constructNewGame() {
        JSONObject jsonObject = new JSONObject();
        int layoutIndex = rand.nextInt(4);
        String layout = POSSIBLE_LAYOUTS[layoutIndex];
        jsonObject.put("layout", layout);
        JSONArray wavesJsonArray = new JSONArray();
        int wavesNumber = rand.nextInt(3)+1;
        JSONObject[] wavesList = new JSONObject[wavesNumber];
        ArrayList<JSONObject> monsters = new ArrayList<> ();
        for (int i = 0; i < wavesNumber; i++) {
            wavesList[i] = new JSONObject();
            wavesList[i].put("duration", this.rand.nextInt(20));
            wavesList[i].put("pre_wave_pause", this.rand.nextInt(10));
            int monstersTypeNumber = this.rand.nextInt(2)+1;
            ArrayList<String> possibleMonsterTypes = new ArrayList<>();
            possibleMonsterTypes.add(this.POSSIBLE_MONSTER_TYPES[0]);
            possibleMonsterTypes.add(this.POSSIBLE_MONSTER_TYPES[1]);
            possibleMonsterTypes.add(this.POSSIBLE_MONSTER_TYPES[2]);
            int possibleIndex = 3;
            for (int m = 0; m < monstersTypeNumber; m++) {
                monsters.add(new JSONObject());
                int monsterTypeIndex = this.rand.nextInt(possibleIndex);
                String monsterType = possibleMonsterTypes.get(monsterTypeIndex);
                possibleMonsterTypes.remove(monsterTypeIndex);
                int hp = this.rand.nextInt(200);
                while (hp < 70) {
                    hp = this.rand.nextInt(200);
                }
                float speed = this.rand.nextFloat()+1;
                float armour = this.rand.nextFloat()+1;
                int manaGainedOnKill = this.rand.nextInt(150);
                while (manaGainedOnKill < 20) {
                    manaGainedOnKill = this.rand.nextInt(150);
                }
                int quantity = this.rand.nextInt(20);
                while (quantity < 5) {
                    quantity = this.rand.nextInt(20);
                }
                monsters.get(m).put("type", monsterType);
                monsters.get(m).put("hp", hp);
                monsters.get(m).put("speed", speed);
                monsters.get(m).put("armour", armour);
                monsters.get(m).put("mana_gained_on_kill", manaGainedOnKill);
                monsters.get(m).put("quantity", quantity);
                if (m == monstersTypeNumber-1) {
                    JSONArray monstersJsonArray = new JSONArray();
                    for (JSONObject monsterObj : monsters) {
                        monstersJsonArray.append(monsterObj);
                    }
                    wavesList[i].put("monsters", monstersJsonArray);
                    monsters.clear();
                }
                possibleIndex--;
            }
            wavesJsonArray.append(wavesList[i]);
        }
        jsonObject.put("waves", wavesJsonArray);
        int initialTowerRange = this.rand.nextInt(150);
        while (initialTowerRange < 50) {
            initialTowerRange = this.rand.nextInt(150);
        }
        jsonObject.put("initial_tower_range", initialTowerRange);
        float initialTowerFiringSpeed = this.rand.nextFloat()+1;
        jsonObject.put("initial_tower_firing_speed", initialTowerFiringSpeed);
        int initialTowerDamage = this.rand.nextInt(70);
        while (initialTowerDamage < 10) {
            initialTowerDamage = this.rand.nextInt(70);
        }
        jsonObject.put("initial_tower_damage", initialTowerDamage);
        int initialManaCap = this.rand.nextInt(2000);
        while (initialManaCap < 500) {
            initialManaCap = this.rand.nextInt(2000);
        }
        jsonObject.put("initial_mana_cap", initialManaCap);
        int initialMana = this.rand.nextInt(1500);
        while (initialMana < 200) {
            initialMana = this.rand.nextInt(1300);
        }
        jsonObject.put("initial_mana", initialMana);
        int initialManaGainedPerSec = this.rand.nextInt(10);
        while (initialManaGainedPerSec == 0) {
            initialManaGainedPerSec = this.rand.nextInt(10);
        }
        jsonObject.put("initial_mana_gained_per_second", initialManaGainedPerSec);
        int towerCost = this.rand.nextInt(200);
        while (towerCost == 0 || towerCost > initialManaCap) {
            towerCost = this.rand.nextInt(200);
        }
        jsonObject.put("tower_cost", towerCost);
        int manaPoolSpellCost = this.rand.nextInt(200);
        while (manaPoolSpellCost < 50) {
            manaPoolSpellCost = this.rand.nextInt(200);
        }
        jsonObject.put("mana_pool_spell_initial_cost", manaPoolSpellCost);
        int manaPoolSpellCostIncreasePerUse = this.rand.nextInt(200);
        while (manaPoolSpellCostIncreasePerUse < 70) {
            manaPoolSpellCostIncreasePerUse = this.rand.nextInt(200);
        }
        jsonObject.put("mana_pool_spell_cost_increase_per_use", manaPoolSpellCostIncreasePerUse);
        float capMultiplier = this.rand.nextFloat()+1;
        jsonObject.put("mana_pool_spell_cap_multiplier", capMultiplier);
        float manaGainedMultiplier = this.rand.nextFloat()+1;
        jsonObject.put("mana_pool_spell_mana_gained_multiplier", manaGainedMultiplier);
        return jsonObject;
    }
}
