import GameService from '../../services/game.service';
import {CREATE_NEW_GAME} from '../action.type';
import {GAME_CREATED} from '../mutation.type';

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
                console.log('error', error);

                throw new Error(error);
            });
    },

};

/* eslint no-param-reassign: ["error", { "props": false }] */
export const mutations = {

    [GAME_CREATED](currentState, playerMatch) {
        Vue.set(currentState, 'player', playerMatch.player);
        Vue.set(currentState, 'playerMatch', playerMatch);

    },

};

export default {
    state,
    actions,
    mutations,
};
