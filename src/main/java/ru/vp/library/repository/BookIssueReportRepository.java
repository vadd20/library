package ru.vp.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vp.library.domain.BookIssueReport;

/**
 * Слой repository для отчета о выдачи книги.
 *
 * @author Vadim Podogov
 * @since 2023.11.24
 */
public interface BookIssueReportRepository extends JpaRepository<BookIssueReport, String> {
}