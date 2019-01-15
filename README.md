# DemoFX in JavaFX 11
Performance test platform for JavaFX.

Effects can be layered and scheduled on a timeline.

Used to discover the best techniques for optimising JavaFX performance on the Raspberry Pi and Desktop.

Configure Java 11 in Mac:
```
export JAVA_HOME=`/usr/libexec/java_home -v 11.0.1`
```
Configure Java 11 in Linux:
```
export JAVA_HOME=PATH_TO_JDK_11/jdk-11
```
Compile in your IDE or with gradle:
```
bash ./gradlew build
```

Run with:
```
bash ./gradlew run --args="[options]" [-Pverbose] [-Psw_rendering]

-Pverbose                  -Dprism.verbose=true + -Xlog:gc* 
-Psw_rendering             JavaFX software rendering (-Dprism.order=sw)

options:
-e <effects>               comma separated list of effects (See SimpleEffectFactory)
-t <seconds>               run for t seconds
-c <count>                 number of items on screen
-f <true>                  fullscreen mode (no stats pane)
-w <width>                 canvas width
-h <height>                canvas height
-l [sqrt,trig,rand,none]   use lookup tables for Math.sqrt, Math.{sin|cos}, Math.Random
-m <line|poly|fill>        canvas plot mode
-a <audio filename>        Play audio file
-s <true>                  use ScriptedDemoConfig

Current list of effects:
blur, bobs, bounce, burst, checkerboard, chord, chromakey, cogs, colourbackground,
concentric, credits, creditssprite, cubefield, cyclebackground, diamonds, doomfire, equaliser,
equalisercubes, falling, feedback, fireworks, flash, glowboard, grid, hexagons,
honeycomb, hue, imagebackground, inversechromakey, mandala, mandelbrot, mask,
maskstack, mirrorx, mirrory, moire, pentagons, picinpic, quadplay, rain, rainbow,
rawplayer, raytrace, rings, rotations, sea, sheet, shift, sierpinski, snowfieldsprite, spin,
sprite3d, spritewave, squares, starfield, starfieldsprite, stars, texcube,
texsphere, textbounce, textlabel, textlayers, textring, textwave, textwavesprite,
tiles, triangles, tubestack, tunnel, twister, vumeter, wordsearch

```
Examples:
```
# Default settings
bash ./gradlew run

# Default settings and verbose and software rendering enabled
bash ./gradlew run -Pverbose -Psw_rendering

# Triangle effect, 500 shapes run for 10 seconds
bash ./gradlew run --args="-e colourbackground,triangles -c 500 -t 10"

# Square effect, set 640x480 canvas size
bash ./gradlew run --args="-e colourbackground,squares -w 640 -h 480"

# Star effect, plot mode line
bash ./gradlew run --args="-e colourbackground,stars -m line"

# Bounce effect with rainbow background
bash ./gradlew run --args="-e rainbow,bounce"

# Burst effect with rainbow background
bash ./gradlew run --args="-e rainbow,burst"

# Layered effects: grid,hexagons
bash ./gradlew run --args="-e grid,hexagons"

# DemoFX Part III scripted demo:
bash ./gradlew run --args="-s true -f true -w 1280 -h 720"

# Christmas DemoFX scripted demo:
bash ./gradlew run --args="-s xmas -f true -w 1280 -h 720"
```

