

# WARGAMES
# ---------------------------------------------------------
Written by Jørgen Finsveen
NTNU Ålesund - IDATA2001 - Year 2022


# Specification
Version: 1.0-SNAPSHOT
Last updated 23.05.2022
Project type: Maven Project
Programming language: Java


# Description
Visualize the outcome when to armies of your choosing are to fight eachother.
The user will be able to create units with a variety of options and effects.
Make cavalry units fight infantry units in the forest, make wizards fight giants 
in the hills, the choice is yours entierly!

After making to armies, you can simulate a battle between them and see which
unit attacks which, and of course, which army is staning after the battle.


# How to use
Open the wargames-folder in your IDE.
From commandline in editor, run "mvn clean compile package javafx:run".

- Use buttons to navigate through the program.
- See the help page to find information about the application.
- Open or save *.csv files containing armies.
- Add, delete or edit existing unit. (Remember to not leave input fields empty)
- Choose battlefield terrain, the terrain affects some units attack and armor


# Constraints
Due to JVM memory handling, having a large amount of units in the armies could
result in significant performance delay and much waiting for simulation results.
This can also result in a java.lang.OutOfMemoryError, which will crash the application.
Therefore, the recomended amount of units is below 400 in total. There has been attempts 
to fix this, (see pom.xml, line 30), but it appears that it was not as effective as
hoped.

If there by any chance should appear a fatal bug, please restart the application, and 
report what happend.


# Resources
All FXML-pages are made in SceneBuilder. 

All images used in the application are gathered from Google through the
"Commercial and other licenses" filter, which means they are free to use.

Some code and technologies are inspired from similar code in own earlier 
projects, literature, StackOverflow and GitHub.

TestArmy1.csv and TestArmy2.csv are files containing example armies, which
are automatically opened while launching the application. Please dont move
or delete theese files.

Facade.java is a design pattern intended to be used in the future. It is 
currently not being used in the application.
