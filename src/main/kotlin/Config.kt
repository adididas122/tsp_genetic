import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options

class Config {
    var geneNumber = 60
    var populationSize = 100
    var maxGeneration = 10_000

    fun initProperties(args: Array<String>) {
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