import java.util.LinkedList;
import java.util.Queue;

public class Building extends BoxContainer {

    private double distanceFromTruck;

    public Building(int nbBoxes, Coordinates coordinates) {
        this.nbBoxes = nbBoxes;
        this.boxCapacity = nbBoxes;
        this.coordinates = coordinates;
    }

    public int removeBoxes(int boxes) {
        nbBoxes -= boxes;
        return boxes;
    }

    public double getDistanceFromTruck() {
        return distanceFromTruck;
    }

    public static Queue<Building> mergeSort(Queue<Building> buildings) {

        // Base case
        if (buildings.size() <= 1) {
            return buildings;
        }

        // Splitting the list in two
        Queue<Building> left = new LinkedList<>();
        Queue<Building> right = new LinkedList<>();
        int index = 0;
        int splitIndex = buildings.size() / 2;
        for (Building building : buildings) {
            if (index < splitIndex) {
                left.add(building);
            } else {
                right.add(building);
            }
            index++;
        }

        // Sorting the two lists recursively
        left = mergeSort(left);
        right = mergeSort(right);

        // Merging back the two lists
        Queue<Building> sorted = new LinkedList<>();
        while (!left.isEmpty() && !right.isEmpty()) {
            double leftDistance = left.peek().getDistanceFromTruck();
            double rightDistance = right.peek().getDistanceFromTruck();
            if (leftDistance <= rightDistance) {
                sorted.add(left.remove());
            } else {
                sorted.add(right.remove());
            }
        }

        while (!left.isEmpty()) {
            sorted.add(left.remove());
        }

        while (!right.isEmpty()) {
            sorted.add(right.remove());
        }

        return sorted;
    }

    public void calculateDistanceFromTruck(Truck truck) {
        this.distanceFromTruck = coordinates.calculateDistance(truck.getCoordinates());
    }

    @Override
    public String toString() {
        double roundedDistance = Math.floor(distanceFromTruck * 10) / 10;
        return "Distance:" + roundedDistance + "\tNumber of Boxes:" + nbBoxes + "\tPosition:" + coordinates.toString() + "\n";
    }

    public void setDistanceFromTruck(double distanceFromTruck) {
        this.distanceFromTruck = distanceFromTruck;
    }
}
