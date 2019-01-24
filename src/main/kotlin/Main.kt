import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import graphic.DrawLines
import kotlin.system.measureTimeMillis

object Main {
    private var geneNumber = 60
    private var populationSize = 100
    private var maxGeneration = 10_000

    @JvmStatic
    fun main(args: Array<String>) {

        initProperties(args)
        val algorithm = Algorithm(geneNumber, populationSize)
        val time = measureTimeMillis {
            repeat(maxGeneration) {
                algorithm.newPopulation()
                println("${algorithm.population.selectParent().first.score}")
            }
        }

        println("Time: $time millis")
        println("Shortest path: ${algorithm.population.selectParent().first.score}")
        DrawLines.draw(algorithm.population.selectParent().first.chromosome)

    }

    private fun initProperties(args: Array<String>) {
        val options = Options()
        options.addOption("g", true, "number of genes")
        options.addOption("p", true, "population size")
        options.addOption("m", true, "number of generations")

        val parser = DefaultParser()
        val cmd = parser.parse(options, args)

        if (cmd.getOptionValue("g") != null) geneNumber = cmd.getOptionValue("g").toInt()
        if (cmd.getOptionValue("p") != null) populationSize = cmd.getOptionValue("p").toInt()
        if (cmd.getOptionValue("m") != null) maxGeneration = cmd.getOptionValue("m").toInt()
    }
}