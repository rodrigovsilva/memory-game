package com.rodrigovsilva.memorygame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

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


    public static final class Builder {
        private Player player;

        private Builder() {
            player = new Player();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            player.setId(id);
            return this;
        }

        public Builder name(String name) {
            player.setName(name);
            return this;
        }

        public Player build() {
            return player;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getId().equals(player.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
