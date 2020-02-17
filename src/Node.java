import java.util.ArrayList;

public class Node {
    private String name;
    private double latitude;
    private double longitude;
    private ArrayList<Node> neighbours;


    public Node() {
        {
            ArrayList<Node> graph = createGraph();
            ShowNodesAndLinks(graph);

        }
    }

    public Node(String destination, double latitude, double longitude) {
        setName(destination);
        setLatitude(latitude);
        setLongitude(longitude);
        this.neighbours = new ArrayList<Node>();

    }

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
    /*
for (type var : array)
    {
        statements using var;
    }
*/
    private void ShowNodesAndLinks(ArrayList<Node> graph) {
        for (Node destination : graph) {
            System.out.println(destination.getName());
            for (Node neighbour: destination.getNeighbours()
                 ) {
                System.out.println("\t"+neighbour.getName());
            }
            }
    }

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
