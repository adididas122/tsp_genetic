package tsp

data class Gene(
    val x: Int,
    val y: Int
) {
    companion object {
        fun fitnessScore(g1: Gene, g2: Gene): Double {
            val xSq = Math.pow((g1.x - g2.x).toDouble(), 2.0)
            val ySq = Math.pow((g1.y - g2.y).toDouble(), 2.0)

            // Calculate shortest path
            return Math.sqrt(Math.abs(xSq + ySq))
        }
    }
}

class Individual(
    val chromosome: List<Gene>,
    var score: Double = 0.0
) {
    fun fitnessScore(): Double {
        var score = 0.0
        (0 until this.chromosome.size - 1).forEach { gene ->
            score += Gene.fitnessScore(chromosome[gene], chromosome[gene + 1])
        }
        score += Gene.fitnessScore(chromosome.first(), chromosome.last())

        this.score = score
        return score
    }

    override fun toString(): String {
        return score.toString()
    }
}

class Population(
    var individuals: List<Individual>,
    var score: Double = 0.0
) {
    fun fitnessScore(): Double {
        score = individuals.stream().mapToDouble(Individual::fitnessScore).sum()
        return score
    }

    fun selectParent(): Pair<Individual, Individual> {
        this.fitnessScore()


        individuals = individuals
            .sortedBy { it.score }

//        println(individuals)
//        println(individuals.first().score)
        return Pair(individuals[0], individuals[1])
    }


}



