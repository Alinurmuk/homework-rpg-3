package com.narxoz.rpg;

import com.narxoz.rpg.battle.*;
import com.narxoz.rpg.adapter.*;
import com.narxoz.rpg.hero.*;
import com.narxoz.rpg.enemy.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BattleEngine engine = BattleEngine.getInstance();

        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(new Warrior("Arthur")));
        heroes.add(new HeroCombatantAdapter(new Mage("Gandalf")));

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(new Goblin()));
        enemies.add(new EnemyCombatantAdapter(new BasicEnemy("Orc", 15, 60)));

        EncounterResult result = engine.runEncounter(heroes, enemies);

        result.getBattleLog().forEach(System.out::println);
        System.out.println("Total Rounds: " + result.getRounds());
    }
}