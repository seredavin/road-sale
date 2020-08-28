package ru.avokzal63.roadsale.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class OfdConfigureKey implements Serializable {
    static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;
}
