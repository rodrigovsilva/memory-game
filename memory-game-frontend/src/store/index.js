import Vue from 'vue';
import Vuex from 'vuex';

import game from './modules/game.module';

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        game,
    }
});
