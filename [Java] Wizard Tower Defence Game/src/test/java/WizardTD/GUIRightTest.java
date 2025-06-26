package WizardTD;


import processing.core.PApplet;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GUIRightTest {
    private GUIRight gui;
    @BeforeEach
    public void setup() {
        int manaPoolSpellCost = 27;
        int manaPoolCostIncreasePerUse = 12;
        int buildCost = 49;
        float capMultiplier = 2;
        float manaGainedMultiplier = (float)1.13;
        this.gui = new GUIRight(manaPoolSpellCost,manaPoolCostIncreasePerUse,buildCost,capMultiplier, manaGainedMultiplier);
    }
    @Test
    public void testConstruction() {
        assertEquals(27, this.gui.getSpell().getCost());
        assertEquals(12, this.gui.getSpell().getCostIncreasePerUse());
        assertEquals(2, this.gui.getSpell().getCapMultiplier());
        assertEquals((float) 1.13, this.gui.getSpell().getManaGainedMultiplier());
        assertNotNull(this.gui.getSpell());
    }
    @Test
    public void testSetFaster() {
        this.gui.setFaster(true);
        assertTrue(this.gui.isFaster());
    }
    @Test
    public void testSetPause() {
        this.gui.setPause(true);
        assertTrue(this.gui.isPausing());
    }
    @Test
    public void testSetBuildTower() {
        this.gui.setBuildTower(true);
        assertTrue(this.gui.isBuildingTower());
    }
    @Test
    public void testSetUpgraderange() {
        this.gui.setUpgradeRange(true);
        assertTrue(this.gui.isUpgradingRange());
    }
    @Test
    public void testSetUpgradeSpeed() {
        this.gui.setUpgradeSpeed(true);
        assertTrue(this.gui.isUpgradingSpeed());
    }
    @Test
    public void testSetUpgradeDamage() {
        this.gui.setUpgradeDamage(true);
        assertTrue(this.gui.isUpgradingDamage());
    }
    @Test
    public void testSetManaPoolSpell() {
        this.gui.setManaPoolSpell(true);
        assertTrue(this.gui.isCastingSpell());
    }
    @Test
    public void testUpdateTowersNotYetContain() {
        ArrayList<Tower> towers = new ArrayList<>();
        towers.add(new Tower(73, 27, 14, 65, 23,49));
        this.gui.updateTowers(towers);
        assertEquals(1, this.gui.getTowers().size());
    }
    @Test
    public void testUpdateTowersContained() {
        ArrayList<Tower> towers = new ArrayList<>();
        Tower tower = new Tower(73, 27, 14, 65, 23,49);
        towers.add(tower);
        this.gui.updateTowers(towers);
        ArrayList<Tower> towers2 = new ArrayList<>();
        towers2.add(tower);
        this.gui.updateTowers(towers2);
        assertEquals(1, this.gui.getTowers().size());
    }
}