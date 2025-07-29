import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton addBookBtn = new JButton("Add Book");
        JButton viewBooksBtn = new JButton("View Books");
        JButton issueBookBtn = new JButton("Issue Book");
        JButton returnBookBtn = new JButton("Return Book");
        JButton deleteBookBtn = new JButton("Delete Book");

        panel.add(addBookBtn);
        panel.add(viewBooksBtn);
        panel.add(issueBookBtn);
        panel.add(returnBookBtn);
        panel.add(deleteBookBtn);

        frame.add(panel);
        frame.setVisible(true);

        // Add Book
        addBookBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            String author = JOptionPane.showInputDialog("Enter Author Name:");
            String isbn = JOptionPane.showInputDialog("Enter ISBN:");
            books.add(new Book(title, author, isbn, true));
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        });

        // View Books
        viewBooksBtn.addActionListener(e -> {
            StringBuilder list = new StringBuilder();
            for (Book b : books) {
                list.append(b).append("\n");
            }
            JOptionPane.showMessageDialog(null, list.length() > 0 ? list.toString() : "No books available.");
        });

        // Issue Book
        issueBookBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to issue:");
            for (Book b : books) {
                if (b.getIsbn().equals(isbn) && b.isAvailable()) {
                    b.setAvailable(false);
                    JOptionPane.showMessageDialog(null, "Book issued successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Book not found or already issued.");
        });

        // Return Book
        returnBookBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to return:");
            for (Book b : books) {
                if (b.getIsbn().equals(isbn) && !b.isAvailable()) {
                    b.setAvailable(true);
                    JOptionPane.showMessageDialog(null, "Book returned successfully.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Book not found or already available.");
        });

        // Delete Book
        deleteBookBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("Enter ISBN to delete:");
            books.removeIf(b -> b.getIsbn().equals(isbn));
            JOptionPane.showMessageDialog(null, "Book deleted if it existed.");
        });
    }

    static class Book {
        private String title, author, isbn;
        private boolean available;

        public Book(String title, String author, String isbn, boolean available) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.available = available;
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public String toString() {
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + available;
        }
    }
}
