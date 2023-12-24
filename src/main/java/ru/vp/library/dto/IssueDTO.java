package ru.vp.library.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.24
 */
@Getter
@Setter
@NoArgsConstructor
public class IssueDTO {
    private String id;
    private String isbn;
    private String readerTicketNumber;
    private String librarianName;
    LocalDate issueDate;
    LocalDate returnDate;
    LocalDate dueDate;
    private Boolean isOverdue;

    public IssueDTO(String isbn, String readerTicketNumber, String librarianName, LocalDate issueDate,
                    LocalDate returnDate, LocalDate dueDate, boolean isOverdue) {
        this.isbn = isbn;
        this.readerTicketNumber = readerTicketNumber;
        this.librarianName = librarianName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
        this.isOverdue = isOverdue;
    }
}
