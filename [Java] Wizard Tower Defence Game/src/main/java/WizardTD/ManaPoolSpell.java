package WizardTD;
import java.util.ArrayList;
public class ManaPoolSpell {
    private int cost;
    private int costIncreasePerUse;
    private float capMultiplier;
    private float manaGainedMultiplier;
    private int castTimes = 0;

    /**
     * constructor
     * @param cost the cost to cast a spell
     * @param costIncreasePerUse by how much the cost will be increased each time a spell is cast
     * @param capMultiplier by how much the max mana will be increased
     * @param manaGainedMultiplier by how much the mana gained from each dead monsters
     */
    public ManaPoolSpell(int cost, int costIncreasePerUse, float capMultiplier, float manaGainedMultiplier) {
        this.cost = cost;
        this.costIncreasePerUse = costIncreasePerUse;
        this.capMultiplier = capMultiplier;
        this.manaGainedMultiplier = manaGainedMultiplier;
    }

    /**
     * the casting logic of the spell
     * @param monsters all the monsters being implemented
     * @param wizardHouse the wizard house being implemented
     */
    public void cast(ArrayList<Monsters> monsters, Wizard wizardHouse) {
        if (this.cost < wizardHouse.getMana()) {
            this.castTimes++;
            if (monsters != null) {
                for (Monsters monster : monsters) {
                    monster.setManaGainedOnKill((int) (monster.getManaGainedOnKill() * (1 + ((this.manaGainedMultiplier-1) * this.castTimes))));
                }
            }
            wizardHouse.setManaCap((int) (wizardHouse.getManaCap() * this.capMultiplier));
            wizardHouse.castSpell(this.cost);
            this.cost += this.costIncreasePerUse;
        }
    }

    /**
     * get the cost of casting a spell
     * @return the cost of casting a spell
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * get the cost increase per use
     * @return the cost increase per use
     */
    public int getCostIncreasePerUse() {
        return this.costIncreasePerUse;
    }

    /**
     * get the cap multiplier
     * @return the cap multiplier
     */
    public float getCapMultiplier() {
        return this.capMultiplier;
    }

    /**
     * get the multiplier of the mana gained
     * @return the multiplier of the mana gained
     */
    public float getManaGainedMultiplier() {
        return this.manaGainedMultiplier;
    }

    /**
     * get how many times the spell has been cast
     * @return cast times
     */
    public int getCastTimes() {
        return this.castTimes;
    }
}
