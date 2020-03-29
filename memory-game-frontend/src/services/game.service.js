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

axios.interceptors.response.use((response) => {
    return response;
}, function (error) {
    return Promise.reject(error.response);
});

export default {
    createNewGame(player) {
        return axios.post(`${API_URL}/game/create`, player, {
            headers: HEADERS
        });
    },
    checkCards(gamePlay) {
        return axios.post(`${API_URL}/game/check`, gamePlay, {
            headers: HEADERS
        });
    },
    listPlayedGames() {
        return axios.get(`${API_URL}/game`, {
            headers: HEADERS
        });
    },
};
