import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username = new String();
        String password = new String();
        String input = new String();
        Boolean ok = false;
        String title = new String();
        Reader newUser = new Reader();
        Book newBook = new Book();
        writerHasBooks link = new writerHasBooks();
        Chapter chapter1 = new Chapter();
        Chapter chapter2 = new Chapter();
        Book book = new Book(1, "book1");
        Integer ok2 = 0;
        Integer id = 0;
        Writer writer = new Writer();
        Reader reader = new Reader();
        List<String> saveParameters=new ArrayList<>();
        List<String> saveResult=new ArrayList<>();
        List<Integer> userType = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Reader user = new Reader(1, "me", "1234");
        System.out.println(user.getUsername());
        // UserHasBooks link1 = new UserHasBooks(book.getId(), user.getId());
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Create user");
            System.out.println("2. Log in");
            System.out.println("3. Create Book");
            System.out.println("4. Get your book list");
            System.out.println("5. Enter book id");
            System.out.println("6. Get number of works written");
            System.out.println("7. Show all books");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Integer idUser = User.lastUserId() + 1;
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("enter password");
                    password = scanner.nextLine();
                    newUser = new Reader(idUser, username, password);
                    break;
                case 2:
                    System.out.println("Enter username");
                    username = scanner.nextLine();
                    System.out.println("enter password");
                    password = scanner.nextLine();
                    ok2 = LoginManager.getInstance().authenticate(username, password);
                    if (ok2 == 1) {
                        writer = Writer.GetWriter(username, password);
                    }
                    if (ok2 == -1) {
                        reader = Reader.GetReader(username, password);
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
                    SavingRequest.SaveRequest(5,saveParameters,saveResult);
                    break;
                case 3:
                    if (ok2 == 1) {
                        Integer idBook = Book.lastId();
                        System.out.println("Enter title");
                        title = scanner.nextLine();
                        newBook = new Book(idBook, title);
                        link = new writerHasBooks(user.getId(), newBook.getId());
                        System.out.println("Book was created");
                    } else {
                        System.out.println("Must be logged in for this action");
                    }
                    break;
                case 4:
                    if (ok2 == 1) {
                        books = Book.getBookListWriter(writer);
                        for (Book bookFromList : books) {
                            System.out.println(
                                    "Book Id: "
                                            + bookFromList.getId()
                                            + ", Title: "
                                            + bookFromList.getTitle());
                        }
                    } else {
                        System.out.println("Must be logged in for this action");
                    }
                    break;
                case 5:
                    System.out.println("Enter book id");
                    input = scanner.nextLine();
                    id = Integer.parseInt(input);
                    if(Book.getBookById(id)!=null){
                    input = Book.getBookById(id).getTitle();}
                    System.out.println(input);
                    saveParameters.clear();
                    saveResult.clear();
                    saveParameters.add(id.toString());
                    saveResult.add(input);
                    SavingRequest.SaveRequest(5,saveParameters,saveResult);
                    break;
                case 6:
                    if (ok2 == 1) {
                        System.out.println("Works created: " + writer.getNumberOfWorksWritten());
                    }
                    break;
                case 7:
                    books = Book.getBookList();
                    for (Book bookFromList : books) {
                        System.out.println(
                                "Book Id: "
                                        + bookFromList.getId()
                                        + ", Title: "
                                        + bookFromList.getTitle());
                    }
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
