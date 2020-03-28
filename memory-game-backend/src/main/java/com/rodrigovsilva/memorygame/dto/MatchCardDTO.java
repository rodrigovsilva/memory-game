package com.rodrigovsilva.memorygame.dto;

import java.io.Serializable;

/**
 * Data transfer object with match cards information
 */
public class MatchCardDTO implements Serializable {

    private Integer position;

    private Integer number;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    public static final class Builder {
        private Integer position;
        private Integer number;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder position(Integer position) {
            this.position = position;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public MatchCardDTO build() {
            MatchCardDTO matchCardDTO = new MatchCardDTO();
            matchCardDTO.setPosition(position);
            matchCardDTO.setNumber(number);
            return matchCardDTO;
        }
    }
}
