public class Coordinates {
    private double latitude;
    private double longitude;
    private String toString;

    public Coordinates(String coordinatesText) {
        String[] coordinates = coordinatesText.split("[(),]");
        this.latitude = Double.parseDouble(coordinates[1]);
        this.longitude = Double.parseDouble(coordinates[2]);
        this.toString = coordinatesText;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double calculateDistance(Coordinates coordinates) {
        double RADIUS = 6371000;
        double lat1 = latitude;
        double lat2 = coordinates.getLatitude();
        double lon1 = longitude;
        double lon2 = coordinates.getLongitude();
        double inner = Math.pow(Math.sin((lat2-lat1)/2),2)+Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin((lon2-lon1)/2),2);
        double haversineDistance = 2 * RADIUS * Math.asin(Math.sqrt(inner));
        return haversineDistance;
    }

    @Override
    public String toString() {
        return toString;
    }
}
