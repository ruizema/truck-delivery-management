import java.io.*;
import java.util.*;

public class Tp1 {

    public static void main(String[] args) throws IOException {
	    String input = args[0];
	    String output = args[1];

	    // Parsing input file
        Scanner scanner = new Scanner(new File(input));
        int lineCount = 0;
        int truckCapacity = 0;
        LinkedList<Building> buildings = new LinkedList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            if (lineCount == 0) {
                // We ignore the number of requested boxes if it is different from the truck capacity
                truckCapacity = Integer.parseInt(line[1]);
            } else {
                // Multiple buildings on a single line
                for (int i = 0; i < line.length; i += 2) {
                    int boxCapacity = Integer.parseInt(line[i]);
                    Coordinates coordinates = new Coordinates(line[i + 1]);
                    buildings.add(new Building(boxCapacity, coordinates));
                }
            }
            lineCount++;
        }

        // Selecting the position of the truck
        Building mostBoxes = buildings.getFirst();
        for (Building building : buildings) {
            if (building.getNbBoxes() > mostBoxes.getNbBoxes()) {
                mostBoxes = building;
            }
        }
        buildings.remove(mostBoxes);
        Truck truck = new Truck(truckCapacity, mostBoxes.getCoordinates());
        truck.loadBoxes(mostBoxes);

        // Calculating distance from truck to building, and sorting building by that distance
        for (Building building : buildings) {
            building.calculateDistanceFromTruck(truck);
        }
        Queue<Building> sortedBuildings = Building.mergeSort(buildings);

        // Filling truck from closest buildings until full
        Iterator<Building> iterator = sortedBuildings.iterator();
        int lastIndex = -1;
        while (!truck.getIsFull() && iterator.hasNext()) {
            truck.loadBoxes(iterator.next());
            lastIndex++;
        }

        // Writing to output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        writer.write("Truck position: " + truck.getCoordinates().toString() + "\n");

        // First building
        mostBoxes.setDistanceFromTruck(0.0);
        writer.write(mostBoxes.toString());

        // Other buildings
        int currentIndex = 0;
        for (Building building : sortedBuildings) {
            writer.write(building.toString());
            if (currentIndex >= lastIndex) {
                break;
            }
            currentIndex++;
        }
        writer.close();
    }
}
