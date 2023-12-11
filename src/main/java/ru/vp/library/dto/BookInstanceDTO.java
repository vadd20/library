package ru.vp.library.dto;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.10
 */
public class BookInstanceDTO {
    private String isbn;
    private int numberOfInstances;

    // Геттеры и сеттеры
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }
}

