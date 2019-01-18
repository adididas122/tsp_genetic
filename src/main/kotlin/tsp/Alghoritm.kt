package tsp

import kotlin.random.Random

class Alghoritm(var population: Population) {

    fun crossOver(): MutableList<Gene> {
        val parent1 = population.selectParent().first
        val parent2 = population.selectParent().second

        val crossOverPoint = Random.nextInt(parent1.chromosome.size)

        val child = mutableListOf<Gene>()
        (0 until crossOverPoint).forEach { it ->
            child.add(parent1.chromosome[it])
        }

        for(gene in parent2.chromosome) {
            if(!child.contains(gene)) child.add(gene)
        }

        return child
    }

    fun newPopulation(populationSize: Int) {
        val newPoulation = mutableListOf<Individual>()
        newPoulation.add(population.selectParent().first)
        (0 until populationSize  - 1).forEach{
            var individual = this.mutate(crossOver())
            newPoulation.add(Individual(individual))
        }


        population = Population(newPoulation)
    }

    fun mutate(individual: MutableList<Gene>): MutableList<Gene> {
        val index1 = Random.nextInt(individual.size)
        val index2 = Random.nextInt(individual.size)
        var temp = individual[index1]
        individual[index1] = individual[index2]
        individual[index2] = temp

        return individual
    }
}

