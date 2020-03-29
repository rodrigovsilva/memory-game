import axios from 'axios';
// eslint-disable-next-line
const API_URL = 'http://localhost:8081/memorygame';

const HEADERS = {
  'Content-Type': 'application/json',
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': '*',
  'Access-Control-Expose-Headers': '*',
  'Access-Control-Allow-Credentials': true,
  'withCredentials': 'true',
}

export default {
  createNewGame(player) {
    return axios.post(`${API_URL}/game/create`, player, {
      headers: HEADERS
    });
  },
};
