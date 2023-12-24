package ru.vp.library.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.24
 */
@Getter
@Setter
public class IssueDTO {
    private String id;
    private String isbn;
    private String readerTicketNumber;
}
