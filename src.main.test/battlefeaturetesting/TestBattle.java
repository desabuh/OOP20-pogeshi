import org.junit.Test;

import models.Battle;

import org.junit.Assert.*;

public class TestBattle {
    Battle b = new BattleImpl();
    
    @Test
    public void testCurrentTurn() {
        b.endTurn();
        assertEquals(b.currentTurn(), new EnemyImpl(newDeck()));
    }
    
}
