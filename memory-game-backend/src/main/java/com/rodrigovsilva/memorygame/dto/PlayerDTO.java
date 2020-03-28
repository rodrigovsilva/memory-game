package com.rodrigovsilva.memorygame.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data transfer object with all player information
 */
public class PlayerDTO implements Serializable {

    private Long id;

    @NotNull
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

    /**
     * Inner builder for player DTO
     */
    public static final class PlayerDTOBuilder {
        private Long id;
        private String name;

        private PlayerDTOBuilder() {
        }

        public static PlayerDTOBuilder builder() {
            return new PlayerDTOBuilder();
        }

        public PlayerDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PlayerDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PlayerDTO build() {
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setId(id);
            playerDTO.setName(name);
            return playerDTO;
        }
    }
}
