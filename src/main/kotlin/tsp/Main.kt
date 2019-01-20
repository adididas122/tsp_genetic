package tsp

import tsp.graphic.DrawLines
import kotlin.random.Random
import kotlin.system.measureTimeMillis

object Main {

    private val geneNumber = 60
    private val populationSize = 100
    private val maxGeneration = 5_000
    @JvmStatic
    fun main(args: Array<String>) {

        val genes = mutableListOf<Gene>()
        for (it in 0 until geneNumber) {
            genes.add(Gene(Random.nextInt(400), Random.nextInt(400)))
        }

        val individuals = mutableListOf<Individual>()
        for (it in 0 until populationSize) {
            individuals.add(Individual(genes.shuffled()))
        }
        val algorithm = Alghoritm(Population(individuals))


        var time = measureTimeMillis {
            var generation = 0
            while (maxGeneration > generation++) {
                algorithm.newPopulation(populationSize)
                var par = algorithm.population.selectParent()
                println("${par.first.score} , ${par.second.score}")
            }
        }

        println("Time: $time millis")
        println("Shortest path: ${algorithm.population.selectParent().first.score}")
        DrawLines.draw(algorithm.population.selectParent().first.chromosome)

    }
}