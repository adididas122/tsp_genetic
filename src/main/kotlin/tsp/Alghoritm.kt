package tsp

import kotlin.random.Random

class Alghoritm(var population: Population) {

    private fun crossOver(): MutableList<Gene> {
        val parent1 = population.selectParent().first
        val parent2 = population.selectParent().second

        val crossOverPoint1 = Random.nextInt(parent1.chromosome.size)
        val crossOverPoint2 = Random.nextInt(parent1.chromosome.size)

        val child = mutableListOf<Gene>()
        (Math.min(crossOverPoint1, crossOverPoint2) until Math.max(crossOverPoint1, crossOverPoint2)).forEach { it ->
            child.add(parent1.chromosome[it])
        }

        for(gene in parent2.chromosome) {
            if(!child.contains(gene)) child.add(gene)
        }

        return child
    }

    fun newPopulation(populationSize: Int) {
        val newPopulation = mutableListOf<Individual>()
        newPopulation.add(population.selectParent().first)
        (0 until populationSize  - 1).forEach{
            var individual = this.mutate(crossOver())
            newPopulation.add(Individual(individual))
        }


        population = Population(newPopulation)
    }

    private fun mutate(individual: MutableList<Gene>): MutableList<Gene> {
        val index1 = Random.nextInt(individual.size)
        val index2 = Random.nextInt(individual.size)
        var temp = individual[index1]
        individual[index1] = individual[index2]
        individual[index2] = temp

        return individual
    }
}

