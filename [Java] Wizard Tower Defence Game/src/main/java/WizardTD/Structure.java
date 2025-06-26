package WizardTD;

import java.util.ArrayList;
import processing.core.PImage;

public class Structure extends StaticGraphics {
    private String type;
    private boolean pathable;
    private Structure frontStructure;
    private Structure backStructure;
    private Structure aboveStructure;
    private Structure belowStructure;
    private boolean destination;
    private boolean startable;
    private int row;
    private int col;
    private int g;
    private int h;
    private Structure parent;
    private boolean buildable;
    public Structure(PImage sprite, int x, int y, String type) {
        super(x,y);
        this.setSprite(sprite);
        this.type = type;
        this.row = (this.y-41)/32;
        this.col = this.x/32;
        this.g = 0;
        this.h = 0;
        this.parent = null;
        if (this.type.equals("path") || this.type.equals("wizard house")) {
            this.pathable = true;
        }
        else {
            this.pathable = false;
        }
        if (this.type.equals("path") && (this.row == 0 || this.row == 19 || this.col == 0 || this.col == 19)) {
            this.startable = true;
        }
        else {
            this.startable = false;
        }
        if (this.type.equals("wizard house")){
            this.destination = true;
        }
        else {
            this.destination = false;
        }
        if (this.type.equals("grass")) {
            this.buildable = true;
        }
        else {
            this.buildable = false;
        }
    }

    public boolean isStartable() {
        return this.startable;
    }

    public boolean isPathable() {
        return this.pathable;
    }

    public boolean isBuildable() {
        return this.buildable;
    }

    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }
    public Structure getAbove() {
        return this.aboveStructure;
    }

    public Structure getBelow() {
        return this.belowStructure;
    }

    public Structure getFront() {
        return this.frontStructure;
    }

    public Structure getBack() {
        return this.backStructure;
    }

    public void setAbove(Structure above) {
        this.aboveStructure = above;
    }

    public void setBelow(Structure below) {
        this.belowStructure = below;
    }
    
    public void setFront(Structure front) {
        this.frontStructure = front;
    }

    public void setBack(Structure back) {
        this.backStructure = back;
    }

    public boolean isDestination(){
        return this.destination;
    }

    public Structure getParent() {
        return this.parent;
    }

    public ArrayList<Structure> getNeighbors() {
        ArrayList<Structure> neighbors = new ArrayList<>();
        neighbors.add(this.getAbove());
        neighbors.add(this.getFront());
        neighbors.add(this.getBelow());
        neighbors.add(this.getBack());
        return neighbors;
    }

    public int getG() {
        return this.g;
    }
    public void setG(int g) {
        this.g = g;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int h(Structure destination){
        int rowDist = Math.abs(this.row - destination.getRow());
        int colDist = Math.abs(this.col - destination.getCol());
        return rowDist + colDist;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getH() {
        return this.h;
    }

    public void setParent(Structure parent) {
        this.parent = parent;
    }
}