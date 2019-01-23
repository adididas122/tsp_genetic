package tsp

import kotlin.math.roundToInt

data class Gene(val x: Int, val y: Int) {
    companion object {
        fun fitnessScore(g1: Gene, g2: Gene): Int {
            val xSq = Math.pow((g1.x - g2.x).toDouble(), 2.0)
            val ySq = Math.pow((g1.y - g2.y).toDouble(), 2.0)
            return Math.sqrt(Math.abs(xSq + ySq)).roundToInt()
        }
    }
}

class Individual(val chromosome: List<Gene>) {
    var score: Int = 0
        get() {
            if (field != 0) {
                return field
            }
            return fitnessScore()
        }

    private fun fitnessScore(): Int {
        var score = 0
        (0 until this.chromosome.lastIndex).forEach { geneIndex ->
            score += Gene.fitnessScore(chromosome[geneIndex], chromosome[geneIndex.inc()])
        }
        score += Gene.fitnessScore(chromosome.first(), chromosome.last())

        this.score = score
        return score
    }

    override fun toString(): String {
        return score.toString()
    }
}

class Population(private val individuals: MutableList<Individual>) {

    fun selectParent(): Pair<Individual, Individual> {
        individuals.sortBy { it.score }
        return Pair(individuals[0], individuals[1])
    }

    fun populationSize() = individuals.size

}



