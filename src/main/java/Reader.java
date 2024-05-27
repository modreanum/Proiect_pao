public class Reader extends User {

    DataEngine dataEngine = new CsvDataEngine();

    public Reader(Integer id, String value, String value1, Boolean saveInFile) {
        super(id, value, value1);
        if (saveInFile) {
            dataEngine.saveNewUser(id, username, password, false);
        }
    }

    //    public Reader() {}

    public Integer getId() {
        return id;
    }



}
