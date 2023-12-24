package ru.vp.library.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vp.library.domain.BookIssue;
import ru.vp.library.dto.IssueDTO;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookIssueRepository extends JpaRepository<BookIssue, String> {

    @Modifying
    @Query("update BookIssue issue set issue.isOverdue = true" +
        " where issue.dueDate < current_date and issue.isOverdue = false")
    void updateFlagsOlderThanThoWeeks();

    boolean existsByBookInstanceId(String isbn);

    Optional<BookIssue> findByBookInstanceId(String isbn);

    @Query(value =
        "SELECT new ru.vp.library.dto.IssueDTO(i.bookInstanceId, c.readerTicketNumber, u.name, i.issueDate, i.returnDate, i.dueDate, i.isOverdue) FROM BookIssue i " +
            "LEFT JOIN Client c ON c.id = i.clientId " +
            "LEFT JOIN User u ON u.id = i.librarianId " +
            "WHERE (cast(:dueDateFrom as date) is null or i.dueDate >= :dueDateFrom) and " +
            "(cast(:dueDateTo as date) is null or i.dueDate <= :dueDateTo) and " +
            "(:isOverdue IS NULL OR i.isOverdue = :isOverdue) ")
    List<IssueDTO> findBooksByFilter(@Param("dueDateFrom") LocalDate dueDateFrom,
                                     @Param("dueDateTo") LocalDate dueDateTo,
                                     @Param("isOverdue") Boolean isOverdue);

    @Query(
        "SELECT new ru.vp.library.dto.IssueDTO(i.bookInstanceId, c.readerTicketNumber, u.name, i.issueDate, i.returnDate, i.dueDate, i.isOverdue) FROM BookIssue i " +
            "LEFT JOIN Client c ON c.id = i.clientId " +
            "LEFT JOIN User u ON u.id = i.librarianId")
    List<IssueDTO> findAllBookIssues();
}