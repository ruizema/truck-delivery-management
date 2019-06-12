public class Truck extends BoxContainer {

    private boolean isFull = false;

    public Truck(int boxCapacity, Coordinates coordinates) {
        this.nbBoxes = 0;
        this.boxCapacity = boxCapacity;
        this.coordinates = coordinates;
    }

    public boolean getIsFull() {
        return isFull;
    }

    public void loadBoxes(Building building) {
        if (this.nbBoxes + building.getNbBoxes() >= this.boxCapacity) {
            this.nbBoxes += building.removeBoxes(this.boxCapacity - this.nbBoxes);
            this.isFull = true;
        } else {
            this.nbBoxes += building.removeBoxes(building.getNbBoxes());
        }
    }
}
