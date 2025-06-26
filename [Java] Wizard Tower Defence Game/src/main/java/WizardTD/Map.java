package WizardTD;

import processing.core.PApplet;
import processing.core.PImage;
import java.io.*;
import java.util.*;

public class Map {
    //class attributes
    private File mapFile;
    private ArrayList<Structure> graphicStructures;
    private ArrayList<Structure> startablePoints;
    private Structure[][] mapPlan;
    private Structure destination;
    private ArrayList<ArrayList<Structure>> possiblePaths = new ArrayList<> ();

    /**
     * constructor method
      * @param map the map file
     */
    public Map(String map) {
        this.graphicStructures = new ArrayList<>();
        this.mapFile = new File(map);
        this.startablePoints = new ArrayList<>();
        this.mapPlan = new Structure[20][20];
    }

    /**
     * add tiles to the graphics
     * @param pathImage0 image 0
     * @param pathImage090 image 0 in 90 degrees
     * @param pathImage1 image 1
     * @param pathImage190 image 1 in 90 degrees
     * @param pathImage1180 image 1 in 180 degrees
     * @param pathImage1270 image 1 in 270 degrees
     * @param pathImage2 image 2
     * @param pathImage290 image 2 in 90 degrees
     * @param pathImage2180 image 2 in 180 degrees
     * @param pathImage2270 image 2 in 270 degrees
     * @param pathImage3 image 3
     * @param shrubImage image of the shrub
     * @param grassImage image of the grass
     * @param wizardHouseImage image of the wizard house
     * @param wizardHouseImage90 image of the wizard house in 90 degrees
     * @param wizardHouseImage180 image of the wizard house in 180 degrees
     * @param wizardHouseImage270 image of the wizard house in 270 degrees
     */
    public void addGraphics(PImage pathImage0, PImage pathImage090, PImage pathImage1, PImage pathImage190,
            PImage pathImage1180, PImage pathImage1270, PImage pathImage2, PImage pathImage290, PImage pathImage2180,
            PImage pathImage2270, PImage pathImage3, PImage shrubImage, PImage grassImage, PImage wizardHouseImage,
            PImage wizardHouseImage90, PImage wizardHouseImage180, PImage wizardHouseImage270) {
        try {
            Scanner scan = new Scanner(this.mapFile);
            String[] mapLines = new String[20];
            for (int line = 0; line < 20; line++) {
                mapLines[line] = scan.nextLine();
            }
            int x = 0;
            int y = 41;
            char[] mapMatrix = new char[400];
            int charIndex = 0;
            for (String content : mapLines) {
                char[] contentChars = content.toCharArray();
                char[] contentCharsModified = new char[20];
                for (int i = 0; i < contentChars.length; i++) {
                    contentCharsModified[i] = contentChars[i];
                }
                for (int i = 0; i < 20; i++) {
                    mapMatrix[charIndex] = contentCharsModified[i];
                    charIndex += 1;
                }
            }
            int row = 0;
            int col = 0;
            charIndex = 0;
            int wizardHouseIndex = 0;
            int wizardHouseX = 0;
            int wizardHouseY = 0;
            int wizardRow = 0;
            int wizardCol = 0;
            while (row < 20) {
                while (col < 20) {
                    if (mapMatrix[charIndex] == ' ' || mapMatrix[charIndex] == '\0') {
                        this.graphicStructures.add(new Structure(grassImage, x, y, "grass"));
                        this.mapPlan[row][col] = new Structure(grassImage, x, y, "grass");
                    } else if (mapMatrix[charIndex] == 'S') {
                        this.graphicStructures.add(new Structure(shrubImage, x, y, "shrub"));
                        this.mapPlan[row][col] = new Structure(shrubImage, x, y, "shrub");
                    } else if (mapMatrix[charIndex] == 'X') {
                        if (row == 0) {
                            if (col == 0) {
                                if (mapMatrix[charIndex + 1] == 'X') {
                                    if (mapMatrix[charIndex + 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage2, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage2, x, y, "path");
                                    } else {
                                        this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                    }
                                } else {
                                    if (mapMatrix[charIndex + 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                    }
                                }
                            }
                            if (col == 19) {
                                if (mapMatrix[charIndex - 1] == 'X') {
                                    if (mapMatrix[charIndex + 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage290, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage290, x, y, "path");
                                    } else {
                                        this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                    }
                                } else {
                                    if (mapMatrix[charIndex + 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                    }
                                }
                            } else {
                                if (mapMatrix[charIndex - 1] == 'X') {
                                    if (mapMatrix[charIndex + 20] == 'X') {
                                        if (mapMatrix[charIndex + 1] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage3, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage3,x, y,"path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage290, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage290, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex + 1] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage1180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1180, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage190, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage190, x, y, "path");
                                        }
                                    }
                                } else {
                                    if (mapMatrix[charIndex + 20] == 'X') {
                                        if (mapMatrix[charIndex + 1] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage2180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage2180, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex + 1] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage1180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1180, x, y, "path");
                                        }
                                    }
                                }
                            }
                        } else if (row == 19) {
                            if (col == 0) {
                                if (mapMatrix[charIndex + 1] == 'X') {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage2180, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage2180, x, y, "path");
                                    } else {
                                        this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                    }
                                } else {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                    } else {
                                        this.graphicStructures.add(new Structure(pathImage1, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage1, x, y, "path");
                                    }
                                }
                            } else if (col == 19) {
                                if (mapMatrix[charIndex - 1] == 'X') {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage2180, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage2180, x, y, "path");
                                    } else {
                                        this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                    }
                                } else {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                    } else {
                                        this.graphicStructures.add(new Structure(pathImage1270, x, y, "path"));
                                        this.mapPlan[row][col] = new Structure(pathImage1270, x, y, "path");
                                    }
                                }
                            } else {
                                if (mapMatrix[charIndex - 1] == 'X') {
                                    if (mapMatrix[charIndex + 1] == 'X') {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage2180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage2180, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage190, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage190, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage1, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1, x, y, "path");
                                        }
                                    }
                                } else {
                                    if (mapMatrix[charIndex + 1] == 'X') {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage1180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1180, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage1270, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1270, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage1, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1, x, y, "path");
                                        }
                                    }
                                }
                            }
                        } else {
                            if (col == 0) {
                                if (mapMatrix[charIndex + 1] == 'X') {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage3, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage3, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage2180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage2180, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage2, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage2, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                        }
                                    }
                                } else {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage190, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage190, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage1, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1, x, y, "path");
                                        }
                                    }
                                }
                            } else if (col == 19) {
                                if (mapMatrix[charIndex - 1] == 'X') {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage3, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage3, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage190, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage190, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage1, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                        }
                                    }
                                } else {
                                    if (mapMatrix[charIndex - 20] == 'X') {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage2270, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage2270, x, y, "path");
                                        } else {
                                            this.graphicStructures.add(new Structure(pathImage1180, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1180, x, y, "path");
                                        }
                                    } else {
                                        if (mapMatrix[charIndex + 20] == 'X') {
                                            this.graphicStructures.add(new Structure(pathImage1270, x, y, "path"));
                                            this.mapPlan[row][col] = new Structure(pathImage1270, x, y, "path");
                                        }
                                    }
                                }
                            } else {
                                if (mapMatrix[charIndex - 1] == 'X') {
                                    if (mapMatrix[charIndex + 1] == 'X') {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage3, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage3, x, y, "path");
                                            } else {
                                                this.graphicStructures.add(new Structure(pathImage2180, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage2180, x, y, "path");
                                            }
                                        } else {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage2, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage2, x, y, "path");
                                            } else {
                                                this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                            }
                                        }
                                    } else {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage290, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage290, x, y, "path");
                                            } else {
                                                this.graphicStructures.add(new Structure(pathImage190, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage190, x, y, "path");
                                            }
                                        } else {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage1, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage1, x, y, "path");
                                            }
                                        }
                                    }
                                } else {
                                    if (mapMatrix[charIndex + 1] == 'X') {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage2270, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage2270, x, y, "path");
                                            } else {
                                                this.graphicStructures.add(new Structure(pathImage1180, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage1180, x, y, "path");
                                            }
                                        } else {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage1270, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage1270, x, y, "path");
                                            } else {
                                                this.graphicStructures.add(new Structure(pathImage0, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage0, x, y, "path");
                                            }
                                        }
                                    } else {
                                        if (mapMatrix[charIndex - 20] == 'X') {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                            } else {
                                                this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                            }
                                        } else {
                                            if (mapMatrix[charIndex + 20] == 'X') {
                                                this.graphicStructures.add(new Structure(pathImage090, x, y, "path"));
                                                this.mapPlan[row][col] = new Structure(pathImage090, x, y, "path");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (mapMatrix[charIndex] == 'W') {
                        this.graphicStructures.add(new Structure(grassImage, x, y, "grass"));
                        wizardHouseIndex = charIndex;
                        wizardHouseX = x;
                        wizardHouseY = y;
                        wizardCol = col;
                        wizardRow = row;
                    }
                    x += 32;
                    col += 1;
                    charIndex += 1;
                }
                y += 32;
                x = 0;
                col = 0;
                row += 1;
            }
            if (wizardHouseIndex % 20 == 0) {
                if (wizardHouseIndex == 0) {
                    if (mapMatrix[wizardHouseIndex+1] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else {
                        this.graphicStructures.add(new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house");
                    }
                }
                else if (wizardHouseIndex == 20*19) {
                    if (mapMatrix[wizardHouseIndex+1] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else {
                        this.graphicStructures.add(new Structure(wizardHouseImage270, wizardHouseX,wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house");
                    }
                }
                else {
                    if (mapMatrix[wizardHouseIndex+1] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage,wizardHouseX,wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else if (mapMatrix[wizardHouseIndex-20] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else if (mapMatrix[wizardHouseIndex+20] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house");
                    }
                }
            }
            else if (wizardHouseIndex % 20 == 19) {
                if (wizardHouseIndex == 19) {
                    if (mapMatrix[wizardHouseIndex-1] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else {
                        this.graphicStructures.add(new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house");
                    }
                }
                else if (wizardHouseIndex == 20*19-1) {
                    if (mapMatrix[wizardHouseIndex-1] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else {
                        this.graphicStructures.add(new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house");
                    }
                }
                else {
                    if (mapMatrix[wizardHouseIndex-1] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else if (mapMatrix[wizardHouseIndex-20] == 'X') {
                        this.graphicStructures.add(new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house");
                    }
                    else {
                        this.graphicStructures.add(new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house"));
                        this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house");
                    }
                }
            }
            else {
                if (mapMatrix[wizardHouseIndex-1] == 'X') {
                    this.graphicStructures.add(new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house"));
                    this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage180, wizardHouseX, wizardHouseY, "wizard house");
                }
                else if (mapMatrix[wizardHouseIndex+1] == 'X') {
                    this.graphicStructures.add(new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house"));
                    this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage, wizardHouseX, wizardHouseY, "wizard house");
                }
                else if (mapMatrix[wizardHouseIndex-20] == 'X') {
                    this.graphicStructures.add(new Structure(wizardHouseImage270,wizardHouseX, wizardHouseY, "wizard house"));
                    this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage270, wizardHouseX, wizardHouseY, "wizard house");
                }
                else {
                    this.graphicStructures.add(new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house"));
                    this.mapPlan[wizardRow][wizardCol] = new Structure(wizardHouseImage90, wizardHouseX, wizardHouseY, "wizard house");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }
    }

    /**
     * set the surrounding attributes
      */
    public void setSurrounding() {
        for (int row = 0; row < this.mapPlan.length; row++) {
            for (int col = 0; col < this.mapPlan[1].length; col++) {
                Structure updatedStructure = this.mapPlan[row][col];
                if (row == 0) {
                    updatedStructure.setBelow(this.mapPlan[row+1][col]);
                    updatedStructure.setAbove(null);
                    if (col == 0) {
                        updatedStructure.setFront(this.mapPlan[row][col+1]);
                        updatedStructure.setBack(null);
                    }
                    else if (col == 19) {
                        updatedStructure.setBack(this.mapPlan[row][col-1]);
                        updatedStructure.setFront(null);
                    }
                    else {
                        updatedStructure.setFront(this.mapPlan[row][col+1]);
                        updatedStructure.setBack(this.mapPlan[row][col-1]);
                    }
                }
                else if (row == 19) {
                    updatedStructure.setAbove(this.mapPlan[row-1][col]);
                    updatedStructure.setBelow(null);
                    if (col == 0) {
                        updatedStructure.setFront(this.mapPlan[row][col+1]);
                        updatedStructure.setBack(null);
                    }
                    else if (col == 19) {
                        updatedStructure.setBack(this.mapPlan[row][col-1]);
                        updatedStructure.setFront(null);
                    }
                    else {
                        updatedStructure.setFront(this.mapPlan[row][col+1]);
                        updatedStructure.setBack(this.mapPlan[row][col-1]);
                    }
                }
                else if (col == 0) {
                    updatedStructure.setAbove(this.mapPlan[row-1][col]);
                    updatedStructure.setBelow(this.mapPlan[row+1][col]);
                    updatedStructure.setFront(this.mapPlan[row][col+1]);
                    updatedStructure.setBack(null);
                }
                else if (col == 19) {
                    updatedStructure.setAbove(this.mapPlan[row-1][col]);
                    updatedStructure.setBelow(this.mapPlan[row+1][col]);
                    updatedStructure.setBack(this.mapPlan[row][col-1]);
                    updatedStructure.setFront(null);
                }
                else {
                    updatedStructure.setAbove(this.mapPlan[row-1][col]);
                    updatedStructure.setBelow(this.mapPlan[row+1][col]);
                    updatedStructure.setFront(this.mapPlan[row][col+1]);
                    updatedStructure.setBack(this.mapPlan[row][col-1]);
                }
                this.mapPlan[row][col] = updatedStructure;
                if (updatedStructure.isStartable()) {
                    this.startablePoints.add(updatedStructure);
                }
                if (updatedStructure.isDestination()){
                    this.destination = updatedStructure;
                }
            }
        }
        for (Structure start : startablePoints) {
            if (start != null) {
                if (this.destination != null) {
                    this.possiblePaths.add(this.findPath(start, this.destination));
                    this.reset();
                }
            }
        }
    }

    /**
     * get the map plan
      * @return the map plan
     */
    public Structure[][] getMapPlan(){
        return this.mapPlan;
    }

    /**
     * reset the h and g of the object
      */
    public void reset(){
        for (Structure[] row : this.mapPlan) {
            for (Structure structure : row) {
                ArrayList<Structure> neighbors = structure.getNeighbors();
                for (Structure neighbor : neighbors) {
                    if (neighbor != null) {
                        neighbor.setG(0);
                        neighbor.setH(0);
                        neighbor.setParent(null);
                    }
                }
            }
        }
    }

    /**
     * output the path that leads to the destination
      * @param structure the last structure
     * @return a list of structure leading to the destination
     */
    public ArrayList<Structure> reconstructPath(Structure structure) {
        ArrayList<Structure> path = new ArrayList<>();
        while (structure != null) {
            path.add(structure);
            structure = structure.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * get all the possible paths on the map
      * @return all the possible paths on the map
     */
    public ArrayList<ArrayList<Structure>> getPossiblePaths() {
        return this.possiblePaths;
    }

    /**
     * finding and update the possible paths
      * @param start the starting position
     * @param end the destination
     * @return null if their is no way to the destination
     */
    public ArrayList<Structure> findPath(Structure start, Structure end) {
        ArrayList<Structure> openSet = new ArrayList<>();
        Set closedSet = new HashSet();
        Map map = this;
        openSet.add(start);
        while (!openSet.isEmpty()) {
            Structure current = openSet.get(0);
            openSet.remove(0);
            if (current.equals(end)) {
                return map.reconstructPath(current);
            }
            closedSet.add(current);
            for (Structure neighbor : current.getNeighbors()) {
                if (neighbor == null) {
                    continue;
                }
                if (!neighbor.isPathable()) {
                    continue;
                }
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                int tentativeG = current.getG() + 1;
                if (!openSet.contains(neighbor) || tentativeG < neighbor.getG()) {
                    neighbor.setG(tentativeG);
                    neighbor.setH(neighbor.h(destination));
                    neighbor.setParent(current);
                }
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }
        return null;
    }

    /**
     * draw the map
      * @param app PApplet object on which figure is drawn
     */
    public void draw(PApplet app) {
        for (Structure structure : this.graphicStructures) {
            structure.draw(app);
        }
    }
}