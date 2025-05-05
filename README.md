# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA

### Course Info
- Instructor: Lily Romano
- Semester: Spring 2025
## Team 12
- Sebastian Stewart (Scrum Master)
- Aleena Sultan (Product Owner)
- Khanh Cao (Developer)
- Radley Le (Developer)

## Project Information
*205 Simulator* is a Java-based 2D simulation of the Dungeon Crawler game genre. The main goal in DANA 325 is for the 
player to cure Professor Lily - the final boss. It comprises 2 parts:

1. Level 1 allows the player to interact with sword objects and chest containers that require riddle-solving to 
obtain equipment and magic dust. The player then encounters the notorious TA goon squad and fight them with the equipments.
they collected.
2. Level 2 introduces the player to Professor Lily, who is the final boss. The player can access this level by 
passing through an evil teleporting portal after defeating all the evil goons. The player has to cure Lily to win the game.

The combat system features a basic melee mechanic which requires players to approach enemies strategically 
while managing their health and using collected items for survival.

Object-oriented programming principles are applied with event-driven game logic through its tile-based map rendering system and 
dynamic entity animations. 

### Game demonstration
  <div>
    <a href="https://www.loom.com/share/1bb2f74a12474038b498b5b672981e9e">
    </a>
    <a href="https://www.loom.com/share/1bb2f74a12474038b498b5b672981e9e">
      <img style="max-width:300px;" src="https://cdn.loom.com/sessions/thumbnails/1bb2f74a12474038b498b5b672981e9e-a8bc39aa50f6d7ef-full-play.gif">
    </a>
  </div>


## Package Structure
### `src/main`
#### `org.team12.controller`
Contains the game logic and control flow:
- `GameController` – Coordinates game state updates, including player actions, enemy movements, and item interactions.
- `InputController` – Handles user keyboard input for movement and interactions.
- `CollisionController` – Manages collision detection between the player, enemies, and obstacles.

#### `org.team12.model`
Defines the core game model:
- `Map` – Represents the dungeon layout, loaded from a `.txt` file, and manages all items and enemies.
- `Tile` – Represents individual tiles of the dungeon grid and their properties.
- `entities` (subpackage) – Contains all entity classes:
    - `Player` – Represents the main playable character.
    - `Enemy` – Represents the evil TA goons with custom behavior.
    - `LilyFinalBoss` – Special final boss character with custom behavior.
- `items` (subpackage) – Contains all entity classes:
    - `Item` – Abstract class for interactable items.
    - `Sword`, `MagicDust`, `Laptop`, `Heart` – Specific item types with different functions.

#### `org.team12.states`
Defines the state enums used by entities and items:
- `EnemyStatus` – Indicates enemy behavior state (e.g., PEACEFUL, HOSTILE, DEAD, CURED).
- `ItemState` – Tracks whether an item is interactable or already picked up.
- `GameState` – Tracks the global game flow (e.g., PLAYING, QUIZ, PLAYER_DEAD, LILY_CURED, PAUSE, END).

#### `org.team12.view`
Handles rendering and UI presentation:
- `GameUI` – Main game panel responsible for launching the game loop and rendering the interface.
- `MapRenderer` – Renders the dungeon layout and tile-based map.
- `EntityRenderer` – Draws player and enemy entities with sprite animations.
- `PlayerHud` – Displays title menu, player's inventory, and quiz popups.
- `UtilityTool` – Provides helper functions (e.g., image scaling) used throughout the project.

### `src/test`
Contains Junit tests for non-GUI core classes:
#### `org.team12.controller`
- `GameControllerTest`
- `InputControllerTest`
- `CollisionControllerTest`

#### `org.team12.model`
- `MapTest` 
- `TileTest` 
- `entities` (subpackage)
  - `PlayerTest` 
  - `EnemyTest` 
  - `LilyFinalBossTest` 
- `items`
  - `ItemTest`
  - `SwordTest`, `MagicDustTest`, `LaptopTest`, `HeartTest`

#### `org.team12.states`
- `EnemyStatusTest`
- `ItemStateTest`
- `GameStateTest`

### Resources Folder Structure

All game assets such as sprite images, tile textures, and map files are stored under the `resources/` directory.

- `resources/Lily/`  
  Contains sprite images for the final boss character, Professor Lily.

- `resources/evilGoon/`  
  Sprite assets for the TA goon enemies.

- `resources/map/`  
  Text-based dungeon layout files used by the `Map` class to generate levels (e.g., `dungeonMap.txt`).

- `resources/objects/`  
  Icons and images for interactive items like swords, riddle chests, and magic dust.

- `resources/player/`  
  Player character sprites facing all four directions, with animation frames.

- `resources/tiles/`  
  Tile textures such as floor, walls, and portals used in the dungeon grid.

## How to run it
You can run the game through DungeonGameApp.java.
### Game instruction
- *Movement*: The `arrow keys` or `W` `A` `S` `D` keys allow the player to move around the map.
- *Attacking*: To attack enemies, move into them as if you were going in that certain direction. Press `E` to attack them.
- *Picking up an object*: To pick up an item, move the player over it and press the `Space bar`.
- *Pause/Resume game*: To pause the game, press `ESC`.
