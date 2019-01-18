package tsp

import tsp.graphic.DrawLines
import kotlin.random.Random

object tsp {

    private val geneNumber = 30
    private val populationSize = 100
    private val maxGeneration = 2000
    @JvmStatic
    fun main(args: Array<String>) {


        val genes = mutableListOf<Gene>()

//  init population
        (0 until geneNumber).forEach {
            genes.add(Gene(Random.nextInt(400), Random.nextInt(400)))
        }

        val individuals = mutableListOf<Individual>()
        (0 until populationSize).forEach {
            individuals.add(Individual(genes.shuffled()))
        }
        val alghoritm = Alghoritm(Population(individuals))


        var generation = 0
        while(maxGeneration > generation ++) {

            alghoritm.newPopulation(populationSize)
            var par = alghoritm.population.selectParent()
            println("${par.first.score} , ${par.second.score}" )

        }

        DrawLines.draw(alghoritm.population.selectParent().first.chromosome)

    }
}