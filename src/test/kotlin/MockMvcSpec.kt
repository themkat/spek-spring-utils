package net.themkat.spek.spring.utils.testing

import io.mockk.every
import io.mockk.mockk
import net.themkat.spek.spring.utils.mockmvc.mockMvc
import net.themkat.spek.spring.utils.testing.data.Game
import net.themkat.spek.spring.utils.testing.data.GameRepository
import net.themkat.spek.spring.utils.testing.data.GameRestController
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.Optional

object MockMvcSpec : Spek({
    val gameRepository = mockk<GameRepository>()
    val gameRestController = GameRestController(gameRepository)

    mockMvc(gameRestController) {
        val mockMvc = standaloneSetup()

        describe("fetching some data using mockmvc from the controller") {
            it("should return game given id") {
                val gameId = 1
                val gameName = "Turok 2: Seeds of Evil"
                val gameReleaseYear = 1998
                val gameDeveloper = "Acclaim Entertainment"

                every { gameRepository.findById(gameId) }
                        .returns(Optional.of(Game(gameId, gameName, gameReleaseYear, gameDeveloper)))

                mockMvc.perform(get("/game/$gameId"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPathEquals("$.id", gameId))
                        .andExpect(jsonPathEquals("$.name", gameName))
                        .andExpect(jsonPathEquals("$.releaseYear", gameReleaseYear))
                        .andExpect(jsonPathEquals("$.developer", gameDeveloper))
            }
        }
    }
})