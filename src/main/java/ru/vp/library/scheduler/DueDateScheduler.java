package ru.vp.library.scheduler;

import javax.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vp.library.repository.BookIssueRepository;
import ru.vp.library.repository.BookReservationRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.24
 */
@Component
public class DueDateScheduler {

    private final BookIssueRepository bookIssueRepository;
    private final BookReservationRepository bookReservationRepository;

    public DueDateScheduler(BookIssueRepository bookIssueRepository,
                            BookReservationRepository bookReservationRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookReservationRepository = bookReservationRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateOverdueFlag() {
        bookIssueRepository.updateOverdueStatus();
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateExpiredReservationStatus() {
        bookReservationRepository.updateExpiredReservationStatus();
    }

}
