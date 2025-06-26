package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManaPoolSpellTest {
    private ManaPoolSpell spell;
    private ArrayList<Monsters> monsters;
    @BeforeEach
    public void setUp() {
        int cost = 46;
        int costIncreasePerUse = 27;
        float capMultiplier = 3;
        float manaGainedMultiplier = 2;
        this.spell = new ManaPoolSpell(cost, costIncreasePerUse, capMultiplier, manaGainedMultiplier);
        Monsters monster1 = new Monsters(null, 37, 2, 1,64);
        Monsters monster2 = new Monsters(null, 49, 1, 3, 57);
        this.monsters = new ArrayList<>();
        monsters.add(monster1);
        monsters.add(monster2);
    }
    @Test
    public void testConstruction() {
        assertEquals(46, this.spell.getCost());
        assertEquals(27, this.spell.getCostIncreasePerUse());
        assertEquals(3, this.spell.getCapMultiplier());
        assertEquals(2, this.spell.getManaGainedMultiplier());
        assertEquals(0, this.spell.getCastTimes());
    }
    @Test
    public void testCastSpellNormalCase() {
        Wizard wizard = new Wizard(700, 1000, 3);
        this.spell.cast(this.monsters, wizard);
        assertEquals(1, this.spell.getCastTimes());
        assertEquals(64*2, this.monsters.get(0).getManaGainedOnKill());
        assertEquals(57*2, this.monsters.get(1).getManaGainedOnKill());
        assertEquals(3000, wizard.getManaCap());
        assertEquals(654, wizard.getMana());
        assertEquals(73, this.spell.getCost());
    }
    @Test
    public void testCastSpellNullMonsters() {
        Wizard wizard = new Wizard(700, 1000, 3);
        this.spell.cast(null, wizard);
        assertEquals(1, this.spell.getCastTimes());
        assertEquals(3000, wizard.getManaCap());
        assertEquals(654, wizard.getMana());
        assertEquals(73, this.spell.getCost());
    }
    @Test
    public void testCastSpellNotEnoughMana() {
        Wizard wizard = new Wizard(32, 1000, 3);
        this.spell.cast(this.monsters, wizard);
        assertEquals(0, this.spell.getCastTimes());
        assertEquals(64, this.monsters.get(0).getManaGainedOnKill());
        assertEquals(57, this.monsters.get(1).getManaGainedOnKill());
        assertEquals(1000, wizard.getManaCap());
        assertEquals(32, wizard.getMana());
        assertEquals(46, this.spell.getCost());
    }
}
