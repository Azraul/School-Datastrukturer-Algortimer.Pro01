import java.util.ArrayList;

public class Node {
    private String name;
    private double latitude;
    private double longitude;
    private ArrayList<Node> neighbours;
    private Node previous;

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
        this.neighbours = new ArrayList<>();
    }

    private void VRApp() {
        //Build the arraylist we are using
        ArrayList<Node> graph = createGraph();
        //Print the arraylist we made above
        ShowNodesAndLinks(graph);
        //Trying the calculate distance
        Node one = graph.get(1);
        Node two = graph.get(4);
        System.out.println(one.getName() + " = Source");
        System.out.println(two.getName() + " = Destination");
        ArrayList<Node> daway = getRoute(one, two);
        System.out.println("This is da way:");
        for (Node way : daway
        ) {
            System.out.println(way.getName());
        }

    }

    //region [map data]
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

    //method to print ArrayList populated with createGraph()
    private void ShowNodesAndLinks(ArrayList<Node> graph) {
        for (Node destination : graph) {
            System.out.println(destination.getName());
            for (Node neighbour : destination.getNeighbours()
            ) {
                System.out.println("\t" + neighbour.getName());
            }
        }
    }

    //endregion
    //region [set/get methods]
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

    //endregion
    //region [getDistance]
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

    //endregion
    //region [getF = CalculateG + calculateH]
    private double calculateH(Node destination) {
        double km = getDistance(Node.this.getLongitude(), Node.this.getLatitude(), destination.getLongitude(), destination.getLatitude());
        return km;
    }

    private double calculateG(Node source) {
        double G = 0;
        Node current = source;
        while (current != source) {
            G += getDistance(current.getLongitude(), current.getLatitude(), current.previous.getLongitude(), current.previous.getLatitude());
            current = current.previous;
        }
        return G;
    }

    private double getF(Node candidate) {
        double F = calculateG(candidate) + calculateH(candidate);
        return F;
    }

    //endregion
    //region [getRoute method]
    ArrayList<Node> getRoute(Node source, Node destination) {
        ArrayList<Node> candidates = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node current = source;
        boolean done = false;

        while (done != true) {
            double minF = 0;
            Node next = null;
            for (Node neighbour : current.getNeighbours()
            ) {
                if (!candidates.contains(neighbour) && !visited.contains(neighbour)) {
                    candidates.add(neighbour);
                    neighbour.previous = current;
                }

            }
            for (Node candidate : candidates
            ) {
                if (candidate == destination) {
                    done = true;
                    break;
                } else {
                    double candidateF = getF(candidate);
                    if (minF == 0 || minF > candidateF) {
                        minF = candidateF;
                        next = candidate;
                    }
                }

            }
            if (!done) {
                current = next;
                visited.add(current);
                candidates.remove(current);
            }
        }
        ArrayList<Node> route = new ArrayList<>();
        current = destination;

        while (current != source) {
            route.add(0, current);
            current = current.previous;
        }
        return route;
    }
    //endregion

}
