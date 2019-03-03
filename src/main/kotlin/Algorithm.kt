class Algorithm(
    var geneNumber: Int,
    private var populationSize: Int
) {
    var population: Population = generateRandomPopulation()

    fun evolve(maxGeneration: Int) {
        repeat(maxGeneration) {
            population = Population(this.calculateNewPopulation())
            println("${this.currentScore()}")
        }
    }

    private fun generateRandomPopulation(): Population {
        val genes = mutableListOf<Gene>()
        repeat(geneNumber) {
            genes.add(Gene((0..400).random(), (0..400).random()))
        }

        val individuals = mutableListOf<Individual>()
        repeat(populationSize) {
            individuals.add(Individual(genes.shuffled()))
        }

        return Population(individuals)
    }

    private fun calculateNewPopulation(): MutableList<Individual> {
        return mutableListOf<Individual>().also { population ->
            population.addAll(this.population.selectParent().toList())
            repeat(this.population.populationSize() - 2) {
                population.add(
                    Individual(mutate(crossOver()))
                )
            }
        }
    }

    private fun mutate(individual: MutableList<Gene>) = individual.also {
        val index1 = (0 until it.size).random()
        val index2 = (0 until it.size).random()
        val temp = it[index1]
        it[index1] = it[index2]
        it[index2] = temp
    }

    private fun crossOver(): MutableList<Gene> {
        val parent1 = population.selectParent().first
        val parent2 = population.selectParent().second

        val crossOverPoint1 = (0 until parent1.chromosome.size).random()
        val crossOverPoint2 = (0 until parent1.chromosome.size).random()

        return mutableListOf<Gene>().also { genes ->
            (Math.min(crossOverPoint1, crossOverPoint2)..Math.max(crossOverPoint1, crossOverPoint2)).forEach {
                genes.add(parent1.chromosome[it])
            }
            parent2.chromosome
                .asSequence()
                .filterNot { genes.contains(it) }
                .forEach { genes.add(it) }
        }
    }

    fun currentScore() = this.population.selectParent().first.score
}

