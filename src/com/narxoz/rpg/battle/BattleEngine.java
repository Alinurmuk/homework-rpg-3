package com.narxoz.rpg.battle;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result = new EncounterResult();
        int roundCount = 0;

        while (!teamA.isEmpty() && !teamB.isEmpty()) {
            roundCount++;
            result.addLog("--- Round " + roundCount + " ---");

            performAttack(teamA, teamB, result);
            if (teamB.isEmpty()) break;

            performAttack(teamB, teamA, result);
        }

        result.setRounds(roundCount);
        result.setWinner(teamA.isEmpty() ? "Team B" : "Team A");
        result.addLog("Winner: " + result.getWinner());
        return result;
    }

    private void performAttack(List<Combatant> attackers, List<Combatant> defenders, EncounterResult result) {
        for (Combatant attacker : new ArrayList<>(attackers)) {
            if (defenders.isEmpty()) break;
            
            Combatant target = defenders.get(0);
            int damage = attacker.getAttackPower();
            target.takeDamage(damage);
            
            result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage);

            if (!target.isAlive()) {
                result.addLog(target.getName() + " defeated!");
                defenders.remove(0);
            }
        }
    }
}