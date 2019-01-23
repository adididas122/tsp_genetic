package tsp

class Algorithm(var population: Population) {

    private fun crossOver(): MutableList<Gene> {
        val parent1 = population.selectParent().first
        val parent2 = population.selectParent().second

        val crossOverPoint1 = (0 until  parent1.chromosome.size).random()
        val crossOverPoint2 = (0 until  parent1.chromosome.size).random()

        val child = mutableListOf<Gene>()
        (Math.min(crossOverPoint1, crossOverPoint2) .. Math.max(crossOverPoint1, crossOverPoint2))
            .forEach {
                child.add(parent1.chromosome[it])
            }

        parent2.chromosome
            .asSequence()
            .filterNot { child.contains(it) }
            .forEach { child.add(it) }

        return child
    }

    fun newPopulation() {
        val newPopulation = mutableListOf<Individual>()
        newPopulation.add(population.selectParent().first)
        repeat(population.populationSize() - 1) {
            newPopulation
                .add(
                    Individual(
                        this.mutate(crossOver())
                    )
                )
        }
        population = Population(newPopulation)
    }

    private fun mutate(individual: MutableList<Gene>): MutableList<Gene> {
        val index1 = (0 until individual.size).random()
        val index2 = (0 until individual.size).random()
        val temp = individual[index1]
        individual[index1] = individual[index2]
        individual[index2] = temp

        return individual
    }
}

