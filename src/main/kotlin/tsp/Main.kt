package tsp

import tsp.graphic.DrawLines
import kotlin.system.measureTimeMillis

object Main {
    private var geneNumber = 60
    private var populationSize = 100
    private var maxGeneration = 10_000

    @JvmStatic
    fun main(args: Array<String>) {
        val algorithm = Algorithm(geneNumber, populationSize)
        val time = measureTimeMillis {
            var generation = 0
            while (maxGeneration > generation++) {
                algorithm.newPopulation()
                println("${algorithm.population.selectParent().first.score}")
            }
        }

        println("Time: $time millis")
        println("Shortest path: ${algorithm.population.selectParent().first.score}")
        DrawLines.draw(algorithm.population.selectParent().first.chromosome)

    }
}