public class Writer extends User {

    DataEngine dataEngine = new CsvDataEngine();

    Writer() {}

    Writer(
            Integer id,
            String username,
            String password,
            Boolean saveInFile) {

        super(id, username, password);
        if (saveInFile) {
            dataEngine.saveNewUser(id, username, password, true);
        }
    }



}
