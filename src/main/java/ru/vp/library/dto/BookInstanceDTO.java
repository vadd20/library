package ru.vp.library.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2023.12.10
 */
@Getter
@Setter
public class BookInstanceDTO {
    private String isbn;
    private int numberOfInstances;

}

