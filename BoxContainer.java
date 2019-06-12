public abstract class BoxContainer {
    protected Coordinates coordinates;
    protected int boxCapacity;
    protected int nbBoxes;

    public int getBoxCapacity() {
        return boxCapacity;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getNbBoxes() {
        return nbBoxes;
    }
}
