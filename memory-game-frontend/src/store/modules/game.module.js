import GameService from '../../services/game.service';
import {CHECK_CARDS, CREATE_NEW_GAME} from '../action.type';
import {CARDS_CHECKED, GAME_CREATED} from '../mutation.type';

import Vue from 'vue';

export const state = {
    player: {},
    playerMatch: {}
};

export const actions = {
    [CREATE_NEW_GAME]({commit}, playerMatch) {
        return GameService.createNewGame(playerMatch)
            .then(response => {
                if(response.data.errors) {
                    throw new Error(response.data.details)
                } else if (response.data) {
                    commit(GAME_CREATED, response.data);
                }
            })
            .catch((error) => {
                throw new Error(error.data.details);
            });
    },

    [CHECK_CARDS]({commit}, playerMatch) {
        return GameService.checkCards(playerMatch)
            .then(response => {
                if(response.data.errors) {
                    throw new Error(response.data.details)
                } else if (response.data) {
                    commit(CARDS_CHECKED, response.data);
                }
            })
            .catch((error) => {
                throw new Error(error.data.details);
            });
    },


};

/* eslint no-param-reassign: ["error", { "props": false }] */
export const mutations = {

    [GAME_CREATED](currentState, playerMatch) {
        Vue.set(currentState, 'player', playerMatch.player);
        Vue.set(currentState, 'playerMatch', playerMatch);

    },

    [CARDS_CHECKED](currentState, gamePlay) {
        Vue.set(currentState, 'playerMatch', gamePlay.playerMatch);
    },
};

export default {
    state,
    actions,
    mutations,
};
