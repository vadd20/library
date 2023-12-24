package ru.vp.library.scheduler;

import javax.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.vp.library.repository.BookIssueRepository;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.24
 */
@Component
public class DueDateScheduler {

    private final BookIssueRepository bookIssueRepository;

    public DueDateScheduler(BookIssueRepository bookIssueRepository) {
        this.bookIssueRepository = bookIssueRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateOverdueFlag() {
        bookIssueRepository.updateFlagsOlderThanThoWeeks();
    }
}
