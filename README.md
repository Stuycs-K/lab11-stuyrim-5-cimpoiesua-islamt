### Game Mechanics 🎮

Each round, every player gets a chance to do something cool. You’ve got three main moves, and you can enter them using full words or shortcuts:

- **Attack** (`attack` or `a`): Hit your enemy to deal some damage. Just say who you’re targeting, like `attack 2` or `a 2` to hit player 2.
- **Special** (`special` or `sp`): Show off your character’s special skills. These moves cost some resources but pack a punch. Example: `special 2` or `sp 2` to aim at player 2.
- **Support** (`support` or `su`): Heal yourself or help a teammate. Say `support 2` or `su 2` to assist player 2, or just use `support`/`su` to heal yourself.

Keep an eye on your health and resources and no going over the max! If you hit zero health, you're out of the game. The team with all its players wiped out loses, so play smart and make your moves count!


## Features
**Random Game Setup** ✅
**Terminal Arguments** ✅
**Draw Screen** ✅
**Draw Text** ✅
**Text Box** ✅
**Color by Percent** ✅
**Draw Party** ✅
**Draw Background** ✅
**Tank Class** ✅
**Boss Class** ✅
**Healer Class** ✅
**Run Game** ✅



## Adventurer Subclasses

replace this with your documentation for your two Adventurer subclasses. If you modify or replace the provided CodeWarrior class, please provide documentation for that as well

- Healer✅
  - Bandage Launcher (Regular Heal)✅
     - Heals 3-5 HP of selected ally
  - Heal Ring (Special (12 special points))✅
     - Grants Regeneration status effect to all allys for 2 turns
        - Regeneration heals 2 HP per turn when applied to entity as well as 1 of their special points per turn
          - Removes all current debuffs
  - Support✅
    - Removes all current debuffs and restores 3 special points
  - Self support✅
    - 75% chance to heal 5 hp and 3 special points
- Tank (x1.7 HP)✅
  - Punch ( Regular attack)
    - Deals 2-4 damage
      - Has a 10% chance to miss
  - Body Slam ( Special (15 special points))✅
    - Has 50% chance to Paralyze opponent over three turns and deals 12 or 13 damage
        - Paralyze cause opponent to have an 80% chance of a failed attack
  - Inspiration ( Support)✅
    - Restores 2 special points and makes them deal 50% more damage next turn
  - Self-Inspiration (self-support)✅
    - Restores 4 special points and restores 3 hp
