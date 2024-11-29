# Angry Birds Clone

---

## How to run :
 -  Ideally open the project in Intellij Idea(other editors work too).
 - Go to lwjgl3/src/main/java/birds/angry/lwjgl3/Lwjgl3Launcher.java and run the main function there to launch the app

## About the game:
 - There are 3 save slots available to the user. On first use these contain the initial level and when a user progresses through a level and saves from the pause menu, that state is saved in the chosen save slot (or defaults to the 1st one if not chosen at the start).
 - There are currently 3 birds: red, yellow and blue, each having its own special ability which can be activated once per launch by pressing space after launching.
 - There are 3 pigs: soldier, king and peasant which have different healths. Pigs take fall damage if they hit the ground with a velocity higher than the set threshold.
 - There are 3 types of materials: stone, wood and ice which have different densities, friction etc. and break after a certain number of hits by birds.
 - There are 3 levels, the last one being space themed.
 - To launch a bird, touch and drag to aim and apply force; release to launch. A preview of the trajectory  is also shown.
 - To change bird on the slingshot, tap on of the remaining birds.
 - If score is above 2400, the user can change the skin of the birds from the battle pass button in the main menu screen.
 - The third level is space based where the objects will be pulled towards the planet's center.
 - This project was tested using JUnit.
## Contribution :
 - ### Rohan Shanker
   - Setting up the libgdx environment and the main game loop workflow
   - Main menu, level select, settings menu, pause menu and win/lose screen
   - Some assets
   - Battle pass screen, Load game screen
   - Score functionality
   - Bird abilities
   - Level 3 physics
   - Level 1 layout
   - save game / load game
   - Trajectory preview
   - level design
     
 - ### Shivank Rajput
   - Majority of the assets and asset manager
   - Converting the sprites to classes and objects
   - Level 2 layout
   - Level 3 implementation 
   - Launch functionality
   - save game / load game
   - Material, Pigs, Birds body definitions
   - Collision logic
   - Battle pass skins
   - JUnit tests
   - level design


## References :
 - [Libgdx Wiki(main source)](https://libgdx.com/wiki/)
 - [Helpful Tutorial series to take reference from](https://happycoding.io/tutorials/libgdx/)
 - [Angry birds wiki for many of the assets used in our game](https://angrybirds.fandom.com/wiki/Angry_Birds_Wiki)
 - ChatGpt for help in some error handling

