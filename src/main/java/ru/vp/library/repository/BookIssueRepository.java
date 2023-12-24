package ru.vp.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.vp.library.domain.BookIssue;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookIssueRepository extends JpaRepository<BookIssue, String> {

    @Modifying
    @Query("update BookIssue issue set issue.isOverdue = true" +
        " where issue.returnDate < current_date and issue.isOverdue = false")
    void updateFlagsOlderThanThoWeeks();


}
