import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final LoginManager loginManager;
    private final DataEngine dataEngine;

    public Menu(LoginManager loginManager, DataEngine dataEngine) {
        this.loginManager = loginManager;
        this.dataEngine = dataEngine;
    }

    public void run() {
        String username = new String();
        String password = new String();
        String input = new String();
        Boolean ok = false;
        String title = new String();
        Writer newUser;
        Book newBook = new Book();
        writerHasBooks link = new writerHasBooks();
        Chapter chapter1 = new Chapter();
        Chapter chapter2 = new Chapter();
        Book book = new Book(1, "book1");
        Integer ok2 = 0;
        Integer id = 0;
        Writer writer = new Writer();
        Reader reader;
        String content;
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        List<Integer> userType = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Reader user = new Reader(1, "me", "1234", false);
        System.out.println(user.getUsername());
        // UserHasBooks link1 = new UserHasBooks(book.getId(), user.getId());
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        DataEngine dataEngine = new CsvDataEngine();
        Integer idUser;
        Boolean access;
        Integer idChapter;

        while (!exit) {
            System.out.println("1. Log in");
            System.out.println("2. Create Book");
            System.out.println("3. Get your book list");
            System.out.println("4. Show all books");
            System.out.println("5. Read a chapter");
            System.out.println("6. Create a writer account");
            System.out.println("7. Create a reader account");
            System.out.println("8. Create chapter for book");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("enter password");
                    password = scanner.nextLine();
                    ok2 = this.loginManager.authenticate(username, password);
                    if (ok2 == 1) {
                        writer = (Writer) dataEngine.getWriter(username, password);
                    }
                    if (ok2 == -1) {
                        reader = (Reader) dataEngine.getReader(username, password);
                    }
                    saveParameters.clear();
                    saveResult.clear();
                    saveParameters.add(username);
                    saveParameters.add(password);
                    if (ok2 == 1 || ok2 == -1) {
                        System.out.println("Login successful!");
                        saveResult.add("Login successful!");

                    } else {
                        saveResult.add("Login failed: Invalid username or password.");
                        System.out.println("Login failed: Invalid username or password.");
                    }
                    SavingRequest.SaveRequest(1, saveParameters, saveResult);
                    break;
                case 2:
                    saveParameters.clear();
                    saveResult.clear();
                    if (ok2 == 1) {
                        Integer idBook = dataEngine.lastBookId()+1;
                        System.out.println("Enter title");
                        title = scanner.nextLine();
                        newBook = new Book(idBook, title,true);
                        link = new writerHasBooks(writer.getId(), newBook.getId(),true);
                        saveResult.add("Book was created");
                        System.out.println("Book was created");
                    } else {
                        saveResult.add("Must be logged in for this action");
                        System.out.println("Must be logged in for this action");
                    }
                    SavingRequest.SaveRequest(2, saveParameters, saveResult);

                    break;
                case 3:
                    saveParameters.clear();
                    saveResult.clear();
                    if (ok2 == 1) {
                        books = dataEngine.getBooks();
                        for (Book bookFromList : books) {
                            if(dataEngine.verifyBookOwnership(writer.getId(),bookFromList.getId())){
                            System.out.println(
                                    "Book Id: "
                                            + bookFromList.getId()
                                            + ", Title: "
                                            + bookFromList.getTitle());
                            saveResult.add( "Book Id: "
                                    + bookFromList.getId()
                                    + ", Title: "
                                    + bookFromList.getTitle());;}
                        }
                    } else {
                        saveResult.add("Must be logged in for this action");
                        System.out.println("Must be logged in for this action");
                    }
                    SavingRequest.SaveRequest(3, saveParameters, saveResult);

                    break;
                case 4:
                    saveParameters.clear();
                    saveResult.clear();
                    books = dataEngine.getBooks();
                    for (Book bookFromList : books) {
                        System.out.println(
                                "Book Id: "
                                        + bookFromList.getId()
                                        + ", Title: "
                                        + bookFromList.getTitle());
                        saveResult.add( "Book Id: "
                                + bookFromList.getId()
                                + ", Title: "
                                + bookFromList.getTitle());
                    }
                    SavingRequest.SaveRequest(4, saveParameters, saveResult);
                    break;
                case 5:
                    saveParameters.clear();
                    saveResult.clear();
                    if(ok2!=0){
                    System.out.println("Enter book id");
                    input = scanner.nextLine();
                    id = Integer.parseInt(input);
                    System.out.println("Enter chapter number");
                    idChapter =Integer.parseInt(scanner.nextLine());
                    chapter2=dataEngine.getChapter(id,idChapter);
                        saveParameters.add(id.toString());
                        saveParameters.add(idChapter.toString());
                    saveResult.add(chapter2.getTitle());
                    saveResult.add(chapter2.getContent());
                    System.out.println(chapter2.getTitle());
                    System.out.println(chapter2.getContent());}
                    else{
                        saveResult.add("Log in first");
                        System.out.println("Log in first");
                    }
                    SavingRequest.SaveRequest(5, saveParameters, saveResult);

                    break;


                case 6:
                    saveParameters.clear();
                    saveResult.clear();
                    idUser = dataEngine.lastUserId(true) + 1;
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("enter password");
                    password = scanner.nextLine();
                    newUser = new Writer(idUser, username, password, true);
                    saveParameters.add(idUser.toString());
                    saveParameters.add(username);
                    saveParameters.add(password);
                    if(newUser!=null){
                        System.out.println("Writer created");
                        saveResult.add("Writer created");
                    }
                    else{
                        System.out.println("Writer couldn't be created");
                        saveResult.add("Writer couldn't created");
                    }
                    SavingRequest.SaveRequest(6, saveParameters, saveResult);

                    break;
                case 7:
                    saveParameters.clear();
                    saveResult.clear();
                    idUser = dataEngine.lastUserId(false) + 1;
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("enter password");
                    password = scanner.nextLine();
                    reader = new Reader(idUser, username, password, true);
                    saveParameters.add(idUser.toString());
                    saveParameters.add(username);
                    saveParameters.add(password);
                    if(reader!=null){
                        System.out.println("Reader created");
                        saveResult.add("Reader created");
                    }
                    else{
                        System.out.println("Reader couldn't be created");
                        saveResult.add("Reader couldn't created");
                    }
                    SavingRequest.SaveRequest(7, saveParameters, saveResult);

                    break;
                case 8:
                    saveParameters.clear();
                    saveResult.clear();
                    System.out.println("Enter book id:");
                    id = Integer.parseInt(scanner.nextLine());
                    access=dataEngine.verifyBookOwnership(writer.getId(),id);
                    if(access){
                    System.out.println("Enter chapter title:");
                    title = scanner.nextLine();
                    System.out.println("Enter chapter content:");
                    content = scanner.nextLine();
                    idChapter= dataEngine.lastChapterId(id)+1;
                    saveParameters.add(id.toString());
                    saveParameters.add((title));
                    saveParameters.add(content);
                    saveParameters.add(writer.getId().toString());
                    chapter1=new Chapter(id,idChapter,title,content,true);
                    saveResult.add("Chapter created");
                        System.out.println("Chapter added");}
                    else{
                        saveResult.add("Access not allowed");
                        System.out.println("Access not allowed");
                    }

                    SavingRequest.SaveRequest(8 , saveParameters, saveResult);

                    break;
                case 11:
                    saveParameters.clear();
                    saveResult.clear();
                    exit = true;
                    saveResult.add("Goodbye");
                    System.out.println("Goodbye");
                    SavingRequest.SaveRequest(11, saveParameters, saveResult);

                    break;
                default:
                    saveParameters.clear();
                    saveResult.clear();
                    saveResult.add("Invalid number");
                    SavingRequest.SaveRequest(0, saveParameters, saveResult);

                    System.out.println("Invalid number");
            }
        }

        scanner.close();
    }
}
