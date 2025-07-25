package model;

public class Description {
    private String title;
    private String content;
    private String author;

    public Description() {}

    public Description(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Description(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Description{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
