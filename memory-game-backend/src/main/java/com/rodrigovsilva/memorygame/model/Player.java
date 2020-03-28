package com.rodrigovsilva.memorygame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * This entity represents all information about a player match.
 *
 * @author Rodrigo Silva
 */
@Entity
@Table(name = "player", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"name"},
                name = "UC_PLAYER_NAME"
        )
})
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;


    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
