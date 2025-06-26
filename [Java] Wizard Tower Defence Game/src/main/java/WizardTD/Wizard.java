package WizardTD;
public class Wizard {
    private int mana;
    private int manaCap;
    private int manaGainedPerSec;
    public Wizard(int initialMana, int initialManaCap, int initialManaGainedPerSec) {
        this.mana = initialMana;
        this.manaCap = initialManaCap;
        this.manaGainedPerSec = initialManaGainedPerSec;
    }
    public void timePassed() {
        this.mana += this.manaGainedPerSec;
        if (this.mana >= this.manaCap) {
            this.mana = this.manaCap;
        }
    }
    public int getMana() {
        return this.mana;
    }
    public int getManaCap() {
        return this.manaCap;
    }
    public void monsterDamaged(Monsters monster) {
        if (monster != null) {
            this.mana -= monster.getCurrentHp();
            if (this.mana <= 0) {
                this.mana = 0;
            }
        }
    }
    public void castSpell(int spellCost) {
        if (this.mana > spellCost) {
            this.mana -= spellCost;
        }
    }
    public void buildTower(Tower tower) {
        if (tower != null) {
            if (this.mana > tower.getBuildCost()) {
                this.mana -= tower.getBuildCost();
            }
        }
    }
    public void upgradeRange(Tower tower) {
        if (tower!=null) {
            if (this.mana > tower.getRangeCost()) {
                this.mana -= tower.getRangeCost();
                tower.upgradeRange();
            }
        }
    }
    public void upgradeSpeed(Tower tower) {
        if (tower!=null) {
            if (this.mana > tower.getSpeedCost()) {
                this.mana -= tower.getSpeedCost();
                tower.upgradeSpeed();
            }
        }
    }
    public void upgradeDamage(Tower tower) {
        if (this.mana > tower.getDamageCost()) {
            this.mana -= tower.getDamageCost();
            tower.upgradeDamage();
        }
    }
    public void manaGainedOnKill(int manaGainedOnKill) {
        this.mana += manaGainedOnKill;
        if (this.mana >= this.manaCap) {
            this.mana -= (this.mana - this.manaCap);
        }
    }
    public void setManaCap(int manaCap) {
        this.manaCap = manaCap;
    }
    public int getManaGainedPerSec() {
        return this.manaGainedPerSec;
    }
}
