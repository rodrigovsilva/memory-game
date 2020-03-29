<template>
    <v-container>
        <v-layout
                text-center
                wrap
                v-if="!this.playerMatch"
        >
            <v-flex
                    xs12
                    mb-5
            >
                <h2 class="headline font-weight-bold mb-3">Please enter your information to start a new game</h2>
                <v-alert v-if="alert" color="pink" dark border="top" icon="mdi-keyboard-settings"
                         transition="scale-transition" dismissible>
                    <h2>{{this.alert}}</h2>
                </v-alert>
            </v-flex>
            <v-flex xs8 offset-xs2>
                <v-text-field v-model="form.player.name" label="Player Name"></v-text-field>
            </v-flex>
            <v-flex xs8 offset-xs2 class="text-left">
                <v-label>How many cards? &nbsp;</v-label>
                <v-btn-toggle v-model="form.totalCards" mandatory>
                    <v-btn text v-for="cardOption in cardOptions" :key="cardOption" :value="cardOption">
                        {{cardOption}}
                    </v-btn>
                </v-btn-toggle>
            </v-flex>
            <br><br><br>
            <v-flex xs8 offset-xs2>
                <v-btn color="primary" @click="createNewGame()">Create New Game</v-btn>
            </v-flex>
            <v-flex xs8 offset-xs2 v-if="this.playedGames" class="last-matches-container">
                <v-card>
                    <v-card-title>
                        Last Matches:
                        <v-spacer></v-spacer>
                        <v-text-field
                                v-model="search"
                                append-icon="mdi-magnify"
                                label="Search"
                                single-line
                                hide-details
                        ></v-text-field>
                    </v-card-title>
                    <v-data-table
                            :headers="headers"
                            :items="playedGames"
                            :search="search"
                    ></v-data-table>
                </v-card>
            </v-flex>
        </v-layout>
        <v-layout v-if="this.playerMatch">
            <v-flex text-center wrap>
                <div class="player-header">
                    <h2>
                        <v-label class="font-weight-bold">Player:</v-label>
                        {{this.player.name}}
                    </h2>
                </div>
                <v-divider class="game-content-divider"></v-divider>
                <h3>Click on the cards in ascending order of the numbers that are on the other side.</h3>
                <v-label>Selected Cards Order:</v-label>
                {{selectedCards}}
                <v-container fluid v-if="this.playerMatch.matchCards">
                    <v-btn-toggle v-model="selectedCards" multiple class="cards-group">
                        <v-row no-gutters>
                            <template v-for="card in this.playerMatch.matchCards">
                                <v-col class="cards-column" :key="card.position">
                                    <v-btn class="card-custom" v-if="showCardNumber" disabled>{{card.number}}</v-btn>
                                    <v-btn class="card-custom" v-if="!showCardNumber" :disabled="disableCards">
                                        {{card.position}}
                                    </v-btn>

                                </v-col>
                                <v-responsive
                                        v-if="((card.position+1) % 4) == 0"
                                        :key="`width-${card.position+1}`"
                                        width="100%"
                                ></v-responsive>
                            </template>
                        </v-row>
                    </v-btn-toggle>
                </v-container>
                <v-divider class="game-content-divider"></v-divider>
                <v-flex xs8 offset-xs2 v-if="!gameEnded">
                    <v-btn color="primary" @click="playGame()" v-if="showCardNumber">Play Game</v-btn>
                    <v-btn color="primary" @click="checkCards()" v-if="!showCardNumber">Check Cards</v-btn>
                </v-flex>
                <v-flex xs8 offset-xs2 v-if="gameEnded">
                    <h3 class="text-uppercase">{{resultText}}</h3>
                    <v-btn :color="resultColor" @click="createNewGame()" v-show="gameEnded">New Match</v-btn>
                    <div v-show="gameEnded">Do you want to start as a new player? <a @click="resetGame()">Click here</a>
                    </div>
                </v-flex>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {CHECK_CARDS, CREATE_NEW_GAME, LIST_PLAYED_GAMES} from "../store/action.type";

    export default {
        data: () => ({
            form: {
                player: {
                    name: ''
                },
                totalCards: 4,
            },
            playedGames: [],
            player: null,
            playerMatch: null,
            alert: null,
            selectedCards: [],
            showCardNumber: true,
            cardOptions: [4, 8, 12],
            search: '',
            headers: [
                {
                    text: 'Player Name',
                    align: 'start',
                    sortable: false,
                    value: 'player.name',
                },
                {text: 'Total Cards', value: 'totalCards'},
                {text: 'Date', value: 'createdAt'},
                {text: 'Result', value: 'victory'},
            ]
        }),
        mounted() {
            this.loadLastGames();
        },
        methods: {
            createNewGame() {
                this.$store.dispatch(CREATE_NEW_GAME, this.form).then(() => {
                    this.player = this.$store.state.game.player;
                    this.playerMatch = this.$store.state.game.playerMatch;

                }).catch(error => {
                    this.alert = error.message;
                });
            },
            playGame() {
                this.showCardNumber = false;
            },
            checkCards() {
                let gamePlay = {
                    selectedCards: this.selectedCards,
                    playerMatch: this.playerMatch
                }
                this.$store.dispatch(CHECK_CARDS, gamePlay).then(() => {
                    this.player = this.$store.state.game.player;
                    this.playerMatch = this.$store.state.game.playerMatch;

                }).catch(error => {
                    this.alert = error.message;
                }).finally(() => {
                    this.clearGameFields();
                });
            },
            resetGame() {

                this.form = {
                    player: {
                        name: ''
                    },
                    totalCards: 4,
                }
                this.player = null,
                    this.playerMatch = null,
                    this.alert = null,

                    this.clearGameFields();
                this.loadLastGames();
            },
            loadLastGames() {
                this.$store.dispatch(LIST_PLAYED_GAMES, this.form).then(() => {
                    this.playedGames = this.$store.state.game.playedGames;
                }).catch(error => {
                    this.alert = error.message;
                });
            },
            clearGameFields() {
                this.selectedCards = [],
                    this.showCardNumber = true

            }
        },
        computed: {
            disableCards: function () {
                return this.selectedCards === this.playerMatch.totalCards || this.gameEnded;
            }
            ,
            gameEnded: function () {
                return this.playerMatch && (this.playerMatch.victory !== null);
            },
            resultColor() {
                return this.playerMatch.victory ? 'success' : 'error';
            },
            resultText() {
                return this.playerMatch.victory ? 'You won!' : 'You lost!';
            }
        }
    }
    ;
</script>
<style lang="scss">
    .game-content-divider {
        margin-top: 1.5rem;
        margin-bottom: 1.5rem;
    }

    .cards-group {
        background-color: transparent !important;

        .cards-column {
            padding: 1rem;

            .card-custom {
                height: 8rem;
                width: 8rem;
            }
        }

    }

    .last-matches-container {
        margin-top: 2rem;
    }

</style>
