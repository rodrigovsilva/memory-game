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
            <v-flex xs8 offset-xs2>
                <v-btn color="primary" @click="createNewGame()">Create New Game</v-btn>
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
                Selected Cards: {{selectedCards}}
                <v-container fluid v-if="this.playerMatch.matchCards">
                    <v-btn-toggle v-model="selectedCards" multiple class="cards-group">
                        <v-row no-gutters>
                            <template v-for="card in this.playerMatch.matchCards">
                                <v-col class="cards-column" :key="card.position">
                                    <v-btn class="card-custom" >{{card.position}}</v-btn>
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
                <v-flex xs8 offset-xs2>
                    <v-btn color="primary" @click="checkCards()">Check Cards</v-btn>
                </v-flex>

            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data: () => ({
            form: {
                player: {
                    name: ''
                },
                totalCards: 12,
            },
            player: null,
            playerMatch: null,
            alert: null,
            selectedCards: []
        }),
        methods: {
            createNewGame() {
                this.$store.dispatch("CREATE_NEW_GAME", this.form).then(() => {
                    this.player = this.$store.state.game.player;
                    this.playerMatch = this.$store.state.game.playerMatch;
                }).catch(error => {
                    this.alert = error.message;
                });
            },
            checkCards() {

            },
        }
    };
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


</style>
