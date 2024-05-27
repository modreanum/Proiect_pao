public class Main {
    public static void main(String[] args) {

        DataEngine dataEngine = new CsvDataEngine();
        LoginManager loginManager = new LoginManager(dataEngine);
        Menu menu = new Menu(loginManager, dataEngine);
        menu.run();
    }
}
