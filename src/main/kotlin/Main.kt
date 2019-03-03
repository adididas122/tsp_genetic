import graphic.DrawLines
import kotlin.system.measureTimeMillis

object Main {
    private val config = Config()

    @JvmStatic
    fun main(args: Array<String>) {
        config.initProperties(args)
        val algorithm = Algorithm(config.geneNumber, config.populationSize)

        val time = measureTimeMillis {
            algorithm.evolve(config.maxGeneration)
        }

        println("Time: $time millis")
        println("Shortest path: ${algorithm.currentScore()}")
        DrawLines.draw(algorithm.population.selectParent().first.chromosome)
    }
}