package travel_app;

/**
 *
 * @author kuvajaan
 */
public class Menu {
    public void main(){
        System.out.println("Hej och välkommen till VRs ruttförslags applikation");
        System.out.println("Välj funktion genom att mata in ett nummer:");
    }
    public void start() {
        System.out.println("Följande funktioner finns att välja på:");
        System.out.println("1: See vår data");
        System.out.println("2: Använd ruttplanerings algoritmen");
        System.out.println("9: Avsluta programet");
    }
    public void showData(){
        System.out.println("Ni valde 1: Visa data");
        System.out.println("Varje tågstation samt dess närliggande tågstationer listas");
    }
    public void newRoute() {
        System.out.println("Ni valde 2: Ruttplanerings algorit");
        System.out.println("Vänligen ange följande:");
    }
    public void pickSource(){
        System.out.println("Ange siffran för er startpunkt");
    }
    public void pickDestination(){
        System.out.println("Ange siffran för er slutpunkt");
    }
    public void choiceSource(String s){
        System.out.println("Ni valde " + s + " som startpunkt");
    }
    public void choiceDestination(String s){
        System.out.println("Ni valde " + s + " som slutpunkt");
    }
    public void quit() {
        System.out.println("Tack för att ni använde VRs applikation");
        System.out.println("Återkom gärna");
    }
    public void travel(){
        System.out.println("Den bästa resvägen bör vara:");
    }
    public  void bookHere(){
        System.out.println("Vänligen ring en receptionist för att boka resan!");
    }
    public void troll(String s){
        System.out.println("Ser ut som ni redan är framme i " +s+", prova njut av vädret istället för att sitta vid datorn!");
    }
}
