import javax.crypto.spec.PSource;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Node {
    private String name;
    private double latitude;
    private double longitude;
    private ArrayList<Node> neighbours;

    //Constructor that runs in main file that runs VR app
    public Node() {
        {
            VRApp();
        }
    }

    public Node(String destination, double latitude, double longitude) {
        setName(destination);
        setLatitude(latitude);
        setLongitude(longitude);
        this.neighbours = new ArrayList<Node>();

    }

    private void VRApp() {

        //Build the arraylist we are using
        ArrayList<Node> graph = createGraph();
        //Print the arraylist we made above
        ShowNodesAndLinks(graph);
        //Trying the calculate distance
        Node one = graph.get(1);
        Node two = graph.get(2);
        double km = calculateH(one, two);
        System.out.println("This is the distance between " + one.getName() + " and " + two.getName() + " " + km + " km");

    }

    //Common set/get
    //TODO Can you region this?
    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private double getLatitude() {
        return latitude;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private double getLongitude() {
        return longitude;
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    private void addNeighbour(Node neighbour) {
        this.neighbours.add(neighbour);
    }
    //End of common set/get

    //Prints all stations made in createGraph()
    private void ShowNodesAndLinks(ArrayList<Node> graph) {
        for (Node destination : graph) {
            System.out.println(destination.getName());
            for (Node neighbour : destination.getNeighbours()
            ) {
                System.out.println("\t" + neighbour.getName());
            }
        }
    }

    //Takes two stations and compares their distance
    //TODO: make sure they are neighbours
    private double calculateH(Node station1, Node station2) {
        double km = getDistance(station1.getLongitude(), station1.getLatitude(), station2.getLongitude(), station2.getLatitude());
        return km;
    }
    //Does the actual math for distance calculating
    private double getDistance(double lon1, double lat1, double lon2, double lat2) {
        lon1 = lon1 * Math.PI / 180.0;
        lat1 = lat1 * Math.PI / 180.0;
        lon2 = lon2 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;


        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double km = 6367 * c;

        return km;
    }
    private double calculateG(Node start){
        double G = 0;
        Node current = start;

        return G;
    }

    //Creates all nodes for the program
    private ArrayList<Node> createGraph() {
        //Skapar en nod för varje tågstation
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);
        Node tku = new Node("Abo", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);

        //Förbindelser från Helsingfors tågstation
        hki.addNeighbour(tpe); //Tammerfors
        hki.addNeighbour(tku); //Åbo
        hki.addNeighbour(lhi); //Lahtis

        //Förbindelser från Tammerfors tågstation
        tpe.addNeighbour(hki); //Helsingfors
        tpe.addNeighbour(tku); //Åbo
        tpe.addNeighbour(jyv); //Jyväskylä
        tpe.addNeighbour(lhi); //Lahtis

        //Förbindelser från Åbo tågstation
        tku.addNeighbour(hki); //Helsingfors
        tku.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Jyväskylä tågstation
        jyv.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Kuopio tågstation
        kpo.addNeighbour(lhi); //Lahtis

        //Förbindelser från Lahtis tågstation
        lhi.addNeighbour(hki); //Helsingors
        lhi.addNeighbour(tpe); //Tammerfors
        lhi.addNeighbour(kpo); //Kuopio

        //Skapar en lista för grafen och sätter in alla noder
        ArrayList<Node> graph = new ArrayList();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);

        return graph;
    }
}
