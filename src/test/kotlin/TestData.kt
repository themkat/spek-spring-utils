package net.themkat.spek.spring.utils.testing.data

import org.springframework.data.annotation.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity


@Entity
class Game(@Id val id: Number, val name: String, val releaseYear: Number, val developer: String)

@Repository
interface GameRepository : JpaRepository<Game, Number>

@RestController
class GameRestController(val gameRepository: GameRepository) {

    @GetMapping("/game")
    fun getAllGames() = gameRepository.findAll()

    @GetMapping("/game/{id}")
    fun getGameById(@PathVariable id: Number) = gameRepository.findById(id).orElseThrow { throw Exception("Game not found") }
}