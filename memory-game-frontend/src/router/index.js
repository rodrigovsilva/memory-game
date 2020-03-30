import Vue from 'vue';
import Router from 'vue-router';
import GamePlayground from '@/components/GamePlayground';

Vue.use(Router);

export default new Router({
  linkActiveClass: 'active',
  base: '/',
  routes: [
    {
      path: '/',
      name: 'GamePlayground',
      component: GamePlayground
    },
  ]
});
