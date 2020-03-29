package com.rodrigovsilva.memorygame.dto;

import com.rodrigovsilva.memorygame.model.MatchCard;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data transfer object with match cards information
 */
public class MatchCardDTO implements Comparable<MatchCardDTO>, Serializable {

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

    @Override
    public int compareTo(MatchCardDTO matchCard) {
        if (matchCard == null) return -1;
        return Integer.compare(this.getPosition(), matchCard.getPosition());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchCardDTO that = (MatchCardDTO) o;
        return Objects.equals(getPosition(), that.getPosition()) &&
                Objects.equals(getNumber(), that.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getNumber());
    }


}
