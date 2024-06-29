package com.retroStore.model.terminal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Getter
@Setter
@Entity
@Table(name = "process_execution_period")
public class ProcessExecutionPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "id")
    private Integer ID;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


}
