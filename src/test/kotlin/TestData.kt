package net.themkat.spek.spring.utils.testing.data

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


// configuration class for JPA to work
@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories
open class TestDataConfig

@Entity
@Table(name = "Game")
open class Game(@Id val id: Int = 0,
                val name: String = "",
                @Column(name = "releaseYear") val releaseYear: Int = 0,
                val developer: String = "")

@Repository
interface GameRepository : JpaRepository<Game, Number>

@RestController
class GameRestController(val gameRepository: GameRepository) {

    @GetMapping("/game")
    fun getAllGames() = gameRepository.findAll()

    @GetMapping("/game/{id}")
    fun getGameById(@PathVariable id: Number) = gameRepository.findById(id).orElseThrow { throw Exception("Game not found") }
}