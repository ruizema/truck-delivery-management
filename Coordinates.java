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
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(coordinates.getLatitude());
        double lon1 = Math.toRadians(longitude);
        double lon2 = Math.toRadians(coordinates.getLongitude());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double inner = haversine(dlat) + Math.cos(lat1) * Math.cos(lat2) * haversine(dlon);
        double haversineDistance = 2 * RADIUS * Math.asin(Math.sqrt(inner));
        return haversineDistance;
    }

    private double haversine(double value) {
        return Math.pow(Math.sin(value / 2), 2);
    }

    @Override
    public String toString() {
        return toString;
    }
}
