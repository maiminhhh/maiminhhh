package WizardTD;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class WizardTest {
    private Wizard wizard;
    private Monsters monster;
    @BeforeEach
    public void setUp() {
        int initialMana = 700;
        int initialManaCap = 1000;
        int initialManaGainedPerSec = 5;
        this.wizard = new Wizard(initialMana, initialManaCap, initialManaGainedPerSec);
        float hp = 70;
        float speed = 3;
        float armour = 1.3F;
        int manaGainedOnKill = 75;
        this.monster = new Monsters(null, hp, speed, armour, manaGainedOnKill);
    }
    @Test
    public void testConstruction() {
        assertEquals(700, this.wizard.getMana());
        assertEquals(1000, this.wizard.getManaCap());
        assertEquals(5, this.wizard.getManaGainedPerSec());
    }
    @Test
    public void testTimePassedWith1FramePassed() {
        this.wizard.timePassed();
        assertEquals(705,this.wizard.getMana());
    }
    @Test
    public void testTimePassedFullMana() {
        int runTimes = (this.wizard.getManaCap() - this.wizard.getMana())/this.wizard.getManaGainedPerSec() + 1;
        for (int count = 0; count < runTimes; count++) {
            this.wizard.timePassed();
        }
        assertEquals(1000, this.wizard.getMana());
    }
    @Test
    public void testMonsterDamagedWithInvalidMonster() {
        this.wizard.monsterDamaged(null);
        assertEquals(700, this.wizard.getMana());
    }
    @Test
    public void testMonsterDamagedWithValidMonster() {
        this.wizard.monsterDamaged(this.monster);
        assertEquals(700-70, this.wizard.getMana());
    }
    @Test
    public void testMonsterDamagedWithMonsterHpLargerThanTowerMana() {
        this.wizard.monsterDamaged(new Monsters(null, 800, 3, 1.3F, 75));
        assertEquals(0, this.wizard.getMana());
    }
    @Test
    public void testCastSpellEnoughMana() {
        this.wizard.castSpell(23);
        assertEquals(700-23, this.wizard.getMana());
    }
    @Test
    public void testCastSpellNotEnoughMana() {
        this.wizard.castSpell(850);
        assertEquals(700, this.wizard.getMana());
    }
    @Test
    public void testSetManaCap() {
        this.wizard.setManaCap(2000);
        assertEquals(2000, this.wizard.getManaCap());
    }
    @Test
    public void testBuildTowerEnoughMana() {
        this.wizard.buildTower(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(700-50, this.wizard.getMana());
    }
    @Test
    public void testBuildTowerNotEnoughMana() {
        this.wizard.buildTower(new Tower(850, 30, 3, 20, 300, 265));
        assertEquals(700, this.wizard.getMana());
    }
    @Test
    public void testBuildTowerInvalidTower() {
        this.wizard.buildTower(null);
        assertEquals(700, this.wizard.getMana());
    }
    @Test
    public void testUpgradeRangeEnoughMana() {
        this.wizard.upgradeRange(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(680, this.wizard.getMana());
    }
    @Test
    public void testUpgradeRangeNotEnoughMana() {
        this.wizard.monsterDamaged(new Monsters(null, 690, 3, 1.3F, 75));
        this.wizard.upgradeRange(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(10, this.wizard.getMana());
    }
    @Test
    public void testUpgradeRangeNullTower() {
        this.wizard.upgradeRange(null);
        assertEquals(700, this.wizard.getMana());
    }
    @Test
    public void testUpgradeSpeedEnoughMana() {
        this.wizard.upgradeSpeed(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(700-20, this.wizard.getMana());
    }
    @Test
    public void testUpgradeSpeedNotEnoughMana() {
        this.wizard.monsterDamaged(new Monsters(null, 690, 3, 1.3F, 75));
        this.wizard.upgradeSpeed(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(10, this.wizard.getMana());
    }
    @Test
    public void testUpgradeSpeedNullTower() {
        this.wizard.upgradeSpeed(null);
        assertEquals(700, this.wizard.getMana());
    }
    @Test
    public void testUpgradeDamageEnoughMana() {
        this.wizard.upgradeDamage(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(700-20, this.wizard.getMana());
    }
    @Test
    public void testUpgradeDamageNotEnoughMana() {
        this.wizard.monsterDamaged(new Monsters(null, 690, 3, 1.3F, 75));
        this.wizard.upgradeDamage(new Tower(50, 30, 3, 20, 300, 265));
        assertEquals(10, this.wizard.getMana());
    }
    @Test
    public void testManaGainedOnKill() {
        this.wizard.manaGainedOnKill(200);
        assertEquals(700+200, this.wizard.getMana());
    }
    @Test
    public void testManaGainedOnKillExceedMaxMana() {
        this.wizard.manaGainedOnKill(500);
        assertEquals(1000, this.wizard.getMana());
    }
    @Test
    public void testManaGainedOnKillExactlyMaxMana() {
        this.wizard.manaGainedOnKill(300);
        assertEquals(1000, this.wizard.getMana());
    }
}
