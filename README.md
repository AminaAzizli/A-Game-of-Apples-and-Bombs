This Java-based GUI game involves controlling a ship to collect apples while avoiding bombs. The game begins with a main menu where the player enters their name and selects a speed parameter. The game features real-time interaction using MouseMotionListener, and the ship's position is updated according to the player's mouse movements. The game includes the following features:

MenuFrame: A menu interface with GridLayout to input the player's name and speed.

GameFrame: The main game window that displays the ship*, apples, and bombs.

GamePanel: The gameplay area, which implements ActionListener for game updates and MouseMotionListener for ship* control.

Ship, Apple, and Bomb Classes: Represent the player-controlled ship* and moving objects (apples and bombs). These classes implement the InteractableDrawing interface.

The game ends when the player's life reaches 0, with an option to restart or exit.
