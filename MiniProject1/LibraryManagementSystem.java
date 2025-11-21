package MiniProject1;

import java.io.*;
import java.util.*;

public class LibraryManagementSystem {

    static class Book {
        private String id;
        private String title;
        private String author;
        private boolean isIssued;

        public Book(String id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isIssued() {
            return isIssued;
        }

        public void setIssued(boolean isIssued) {
            this.isIssued = isIssued;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
        }
    }

    static class Member {
        private String memberId;
        private String name;
        private List<String> borrowedBookIds;

        public Member(String memberId, String name) {
            this.memberId = memberId;
            this.name = name;
            this.borrowedBookIds = new ArrayList<>();
        }

        // Getters and Setters
        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getBorrowedBookIds() {
            return borrowedBookIds;
        }

        public void setBorrowedBookIds(List<String> borrowedBookIds) {
            this.borrowedBookIds = borrowedBookIds;
        }

        public void borrowBook(String bookId) {
            borrowedBookIds.add(bookId);
        }

        public void returnBook(String bookId) {
            borrowedBookIds.remove(bookId);
        }
    }

    static class BookNotAvailableException extends Exception {
        public BookNotAvailableException(String message) {
            super(message);
        }
    }

    static class InvalidReturnException extends Exception {
        public InvalidReturnException(String message) {
            super(message);
        }
    }

    static class Library {
        private HashMap<String, Book> inventory;
        private HashMap<String, Member> members;

        public Library() {
            inventory = new HashMap<>();
            members = new HashMap<>();
        }

        // Add a new book to the library inventory
        public void addBook(Book book) {
            inventory.put(book.getId(), book);
        }

        public void addMember(Member member) {
            members.put(member.getMemberId(), member);
        }

        // Issue a book to a member
        public void issueBook(String bookId, String memberId) throws BookNotAvailableException {
            Book book = inventory.get(bookId);
            if (book == null || book.isIssued()) {
                throw new BookNotAvailableException("Book is either not available or already issued.");
            }
            Member member = members.get(memberId);
            if (member == null) {
                System.out.println("Member not found.");
                return;
            }
            book.setIssued(true);
            member.borrowBook(bookId);
            logOperation("Book issued: " + bookId + " to member: " + memberId);
        }

        // Return a book to the library
        public void returnBook(String bookId, String memberId, int daysLate) throws InvalidReturnException {
            Book book = inventory.get(bookId);
            if (book == null || !book.isIssued()) {
                throw new InvalidReturnException("Book cannot be returned. It wasn't issued.");
            }
            Member member = members.get(memberId);
            if (member == null || !member.getBorrowedBookIds().contains(bookId)) {
                throw new InvalidReturnException("The member did not borrow this book.");
            }
            book.setIssued(false);
            member.returnBook(bookId);
            double lateFee = daysLate * 2.0;
            logOperation("Book returned: " + bookId + " by member: " + memberId + " with late fee: ₹" + lateFee);
        }

        public void logOperation(String message) {
            try (FileWriter writer = new FileWriter("library_log.txt", true)) {
                writer.write(message + "\n");
            } catch (IOException e) {
                System.out.println("Error writing to log file: " + e.getMessage());
            }
        }

        public void showInventory() {
            for (Book book : inventory.values()) {
                System.out.println(book);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add a book");
            System.out.println("2. Issue a book");
            System.out.println("3. Return a book");
            System.out.println("4. Show inventory");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {

                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    Book book = new Book(bookId, title, author);
                    library.addBook(book);
                    System.out.println("Book added successfully.");
                    break;
                }
                case 2: {

                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    try {
                        library.issueBook(bookId, memberId);
                        System.out.println("Book issued successfully.");
                    } catch (BookNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3: {

                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter number of days late: ");
                    int daysLate = scanner.nextInt();
                    try {
                        library.returnBook(bookId, memberId, daysLate);
                        System.out.println("Book returned successfully.");
                    } catch (InvalidReturnException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {

                    library.showInventory();
                    break;
                }
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

