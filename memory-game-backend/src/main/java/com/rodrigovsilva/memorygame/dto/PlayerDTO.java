package com.rodrigovsilva.memorygame.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data transfer object with all player information
 */
public class PlayerDTO implements Serializable {

    private Long id;

    private String name;

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
        private PlayerDTO player;

        private Builder() {
            player = new PlayerDTO();
        }

        public static PlayerDTO.Builder builder() {
            return new PlayerDTO.Builder();
        }

        public PlayerDTO.Builder id(Long id) {
            player.setId(id);
            return this;
        }

        public PlayerDTO.Builder name(String name) {
            player.setName(name);
            return this;
        }

        public PlayerDTO build() {
            return player;
        }
    }
}
