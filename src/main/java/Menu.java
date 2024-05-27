import exceptions.DataNotFoundException;
import exceptions.InvalidInputException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final LoginManager loginManager;
    private final DataEngine dataEngine;
    private final SavingRequestInterface savingRequest=new SavingRequest();
    private User user;

    public Menu(LoginManager loginManager, DataEngine dataEngine) {
        this.loginManager = loginManager;
        this.dataEngine = dataEngine;
        this.user = null;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int choice = 0;
        while (!exit) {
            printMenu();
            try {
                choice = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Input has to be an integer");
                continue;
            } finally {
                scanner.nextLine();
            }

            try {
                switch (choice) {
                    case 1:
                        case1(scanner);
                        break;
                    case 2:
                        case2(scanner);
                        break;
                    case 3:
                        case3(scanner);

                        break;
                    case 4:
                        case4(scanner);
                        break;
                    case 5:
                        case5(scanner);

                        break;

                    case 6:
                        case6(scanner);
                        break;
                    case 7:
                        case7(scanner);
                        break;
                    case 8:
                        case8(scanner);
                        break;
                    case 9:
                        case9(scanner);
                        break;
                    case 10:
                        case10(scanner);
                        exit = true;
                        break;
                    default:
                        caseDefault();
                }
            } catch (InvalidInputException | DataNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("1. Log in");
        System.out.println("2. Create Book");
        System.out.println("3. Get your book list");
        System.out.println("4. Show all books");
        System.out.println("5. Read a chapter");
        System.out.println("6. Create a writer account");
        System.out.println("7. Create a reader account");
        System.out.println("8. Create chapter for book");
        System.out.println("9. Get past requests");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private void case1(Scanner scanner) {

        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("enter password");
        String password = scanner.nextLine();
        if (username.length() == 0 || password.length() == 0) {
            throw new InvalidInputException("Username or password is empty");
        }
        Integer ok2 = this.loginManager.authenticate(username, password);
        if (ok2 == 1) {
            user = dataEngine.getWriter(username, password);
        }
        if (ok2 == -1) {
            user = dataEngine.getReader(username, password);
        }
        if (ok2 == 0) {
            throw new DataNotFoundException("User not found");
        }
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        saveParameters.add(username);
        saveParameters.add(password);
        System.out.println("Login successful!");
        saveResult.add("Login successful!");
        savingRequest.SaveRequest(1, saveParameters, saveResult);
    }

    private void case2(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        if(user==null){throw new DataNotFoundException("No user found");}
        if (user.isWriter()) {
            Integer idBook = dataEngine.lastBookId() + 1;
            System.out.println("Enter title");
            String title = scanner.nextLine();
            if (title.length() == 0) {
                throw new InvalidInputException("Title is empty");
            }
            Book newBook = new Book(idBook, title);
            dataEngine.saveNewBook(idBook, title);
            WriterHasBooks link = new WriterHasBooks(user.getId(), newBook.getId());
            dataEngine.saveNewLink(user.getId(), newBook.getId());
            saveResult.add("Book was created");
            System.out.println("Book was created");
        } else {
            saveResult.add("Must be logged in for this action");
            System.out.println("Must be logged in for this action");
        }
        savingRequest.SaveRequest(2, saveParameters, saveResult);
    }

    private void case3(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        if(user==null){throw new DataNotFoundException("No user found");}
        if (user.isWriter()) {
            List<Book> books = dataEngine.getBooks();
            for (Book bookFromList : books) {
                if (dataEngine.verifyBookOwnership(user.getId(), bookFromList.getId())) {
                    System.out.println(
                            "Book Id: "
                                    + bookFromList.getId()
                                    + ", Title: "
                                    + bookFromList.getTitle());
                    saveResult.add(
                            "Book Id: "
                                    + bookFromList.getId()
                                    + ", Title: "
                                    + bookFromList.getTitle());
                    ;
                }
            }
            if (books.size() == 0) {
                saveResult.add("No books found");
                System.out.println("No books found");
            }
        } else {
            saveResult.add("Must be logged in for this action");
            System.out.println("Must be logged in for this action");
        }
        savingRequest.SaveRequest(3, saveParameters, saveResult);
    }

    private void case4(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        List<Book> books = dataEngine.getBooks();
        for (Book bookFromList : books) {
            System.out.println(
                    "Book Id: " + bookFromList.getId() + ", Title: " + bookFromList.getTitle());
            saveResult.add(
                    "Book Id: " + bookFromList.getId() + ", Title: " + bookFromList.getTitle());
        }
        if (books.size() == 0) {
            saveResult.add("No books found");
            System.out.println("No books found");
        }
        savingRequest.SaveRequest(4, saveParameters, saveResult);
    }

    private void case5(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();

            if (user != null) {
                System.out.println("Enter book id");
                String input = scanner.nextLine();
                if (input.length() == 0) {
                    throw new InvalidInputException("Id is empty");
                }
                Integer id = Integer.parseInt(input);
                System.out.println("Enter chapter number");
                if (input.length() == 0) {
                    throw new InvalidInputException("Chapter number is empty");
                }
                Integer idChapter = Integer.parseInt(scanner.nextLine());
                Chapter chapter2 = dataEngine.getChapter(id, idChapter);
                if (chapter2 == null) {
                    throw new DataNotFoundException("Chapter doesn't exist");
                }
                saveParameters.add(id.toString());
                saveParameters.add(idChapter.toString());
                saveResult.add(chapter2.getTitle());
                System.out.println(chapter2.getTitle());
                for (String line : chapter2.getContent()) {
                    System.out.println(line);
                    saveResult.add(line);
                }
            } else {
                saveResult.add("Log in first");
                System.out.println("Log in first");
            }
            savingRequest.SaveRequest(5, saveParameters, saveResult);

    }

    private void case6(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        Integer idUser = dataEngine.lastUserId(true) + 1;
        System.out.println("Enter username");
        String username = scanner.nextLine();
        if (username.length() == 0) {
            throw new InvalidInputException("Username is empty");
        }
        System.out.println("enter password");
        String password = scanner.nextLine();
        if (password.length() == 0) {
            throw new InvalidInputException("Password is empty");
        }
        User newUser = new Writer(idUser, username, password);
        dataEngine.saveNewUser(idUser, username, password, true);
        saveParameters.add(idUser.toString());
        saveParameters.add(username);
        saveParameters.add(password);
        if (newUser != null) {
            System.out.println("Writer created");
            saveResult.add("Writer created");
        } else {
            System.out.println("Writer couldn't be created");
            saveResult.add("Writer couldn't created");
        }
        savingRequest.SaveRequest(6, saveParameters, saveResult);
    }

    private void case7(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        Integer idUser = dataEngine.lastUserId(false) + 1;
        System.out.println("Enter username");
        String username = scanner.nextLine();
        if (username.length() == 0) {
            throw new InvalidInputException("Username is empty");
        }
        System.out.println("enter password");
        String password = scanner.nextLine();
        if (password.length() == 0) {
            throw new InvalidInputException("Password is empty");
        }
        User reader = new Reader(idUser, username, password);
        dataEngine.saveNewUser(idUser, username, password, false);
        saveParameters.add(idUser.toString());
        saveParameters.add(username);
        saveParameters.add(password);
        if (reader != null) {
            System.out.println("Reader created");
            saveResult.add("Reader created");
        } else {
            System.out.println("Reader couldn't be created");
            saveResult.add("Reader couldn't created");
        }
        savingRequest.SaveRequest(7, saveParameters, saveResult);
    }

    private void case8(Scanner scanner) {
        String input;
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        if(user==null){throw new DataNotFoundException("No user found");}

            System.out.println("Enter book id:");
            input = scanner.nextLine();
            Integer id = 0;
            if (input.length() == 0) {
                throw new InvalidInputException("Id is empty");
            }
            try {
                id = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw  new InvalidInputException("Input isn't integer");
            }
            if (id >= 0) {
                Boolean access = dataEngine.verifyBookOwnership(user.getId(), id);

                if (user.isWriter() && dataEngine.verifyBookOwnership(user.getId(), id)) {
                    System.out.println("Enter chapter title:");
                    String title = scanner.nextLine();
                    if (title.length() == 0) {
                        throw new InvalidInputException("Title is empty");
                    }
                    List<String> stringList = new ArrayList<>();
                    System.out.println("How many paragraphs:");
                    Integer nrParagraphs = 0;
                    input = scanner.nextLine();
                    if (input.length() == 0) {
                        throw new InvalidInputException("Paragraph nr is empty");
                    }
                    try {
                        nrParagraphs = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                       throw new InvalidInputException("Input isn't integer");
                    }
                    if (nrParagraphs != 0) {
                        for (int i = 1; i <= nrParagraphs; i++) {
                            System.out.println("Enter pharagraph content:");
                            String content = scanner.nextLine();
                            stringList.add(content);
                            saveParameters.add(content);
                        }
                        Integer idChapter = dataEngine.lastChapterId(id) + 1;
                        saveParameters.add(id.toString());
                        saveParameters.add((title));
                        saveParameters.add(user.getId().toString());
                        Chapter chapter1 = new Chapter(id, idChapter, title, stringList);
                        dataEngine.saveNewChapter(id, idChapter, title, stringList);
                        saveResult.add("Chapter created");
                        System.out.println("Chapter added");
                    }
                } else{saveResult.add("Access not allowed");
                    System.out.println("Access not allowed");}
            }
        savingRequest.SaveRequest(8, saveParameters, saveResult);
    }

    private void case9(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        saveResult = savingRequest.getRequests();
        for (String request : saveResult) {
            System.out.println(request);
        }
        savingRequest.SaveRequest(9, saveParameters, saveResult);
    }

    private void case10(Scanner scanner) {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        saveResult.add("Goodbye");
        System.out.println("Goodbye");
        savingRequest.SaveRequest(10, saveParameters, saveResult);
    }

    private void caseDefault() {
        List<String> saveParameters = new ArrayList<>();
        List<String> saveResult = new ArrayList<>();
        saveResult.add("Invalid number");
        savingRequest.SaveRequest(0, saveParameters, saveResult);
        System.out.println("Invalid number");
    }
}
