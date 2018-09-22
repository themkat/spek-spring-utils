package net.themkat.spek.spring.utils.testing

import net.themkat.spek.spring.utils.context.springContext
import net.themkat.spek.spring.utils.testing.data.Game
import net.themkat.spek.spring.utils.testing.data.GameRepository
import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.fail


object SpringContextSpec : Spek({
    springContext("net.themkat.spek.spring.utils.testing") {
        val gameRepository: GameRepository = inject(GameRepository::class.java)

        describe("fetching game data from database") {
            fun Game.`should equal Wolfenstein 3D`() {
                this.id shouldEqual 1
                this.name shouldEqual "Wolfenstein 3D"
                this.releaseYear shouldEqual 1991
                this.developer shouldEqual "Id Software"
            }

            it("should return Wolfenstein 3D given correct id") {
                val game = gameRepository.findById(1).orElseGet {
                    fail("game with id 1 could not be found in database")
                }

                game.`should equal Wolfenstein 3D`()
            }

            it("should return all games in database") {
                val games = gameRepository.findAll()

                games.size shouldEqual 2
                // TODO: add tests for the games
            }
        }

        describe("saving data to database") {
            it("should save an entity to database and be able to read it afterwards") {
                // I know, I know. In a real system you would probably have auto-incrementation on the id, but that is not
                // important here :)
                val game = Game(3, "The Legend of Zelda: Ocarina of Time", 1997, "Nintendo")

                gameRepository.save(game)

                val gameFetchedFromDb = gameRepository.findById(game.id).orElseGet {
                    fail("game with id 1 could not be found in database")
                }

                gameFetchedFromDb.id shouldEqual game.id
                gameFetchedFromDb.name shouldEqual game.name
                gameFetchedFromDb.releaseYear shouldEqual game.releaseYear
                gameFetchedFromDb.developer shouldEqual game.developer
            }
        }
    }
})