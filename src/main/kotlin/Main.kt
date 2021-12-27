const val POPULATION_SIZE = 100

const val ELITE_RATE = 0.4

const val MUTATION_RATE = 0.2

const val LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

const val TARGET = "ALEXANDR"

fun main() {
    var population = createPopulation()
    while (true) {
        val eliteGenomes = getEliteGenomes(population)
        val alphaGenome = eliteGenomes[0]
        println(alphaGenome)
        if (alphaGenome.getBadCharactersCount(TARGET) == 0) {
            break
        } else {
            val mutatedGenomes = mutate(eliteGenomes)
            population = mate(mutatedGenomes)
        }
    }
}

fun createPopulation() = (0 until POPULATION_SIZE).map { generate(TARGET.length) }

fun generate(length: Int): Genome {
    val newName = generateName(length)
    return Genome(newName)
}

fun generateName(length: Int): String = (0 until length).joinToString("") { LETTERS[it].toString() }

fun mate(genomes: List<Genome>): List<Genome> = (0 until POPULATION_SIZE).map { createGenomeFrom(genomes) }

fun createGenomeFrom(genomes: List<Genome>): Genome {
    val firstGenome = getRandomGenomeFrom(genomes)
    val secondGenome = getRandomGenomeFrom(genomes)
    return firstGenome.mate(secondGenome)
}

fun getRandomGenomeFrom(genomes: List<Genome>): Genome {
    val genomeIndex = (Math.random() * genomes.size).toInt()
    return genomes[genomeIndex]
}

fun mutate(genomes: List<Genome>): List<Genome> {
    return genomes.map(::mutate)
}

fun mutate(genome: Genome): Genome = if (Math.random() < MUTATION_RATE) {
    genome.mutate()
} else {
    genome
}

fun getEliteGenomes(genomes: List<Genome>): List<Genome> = genomes
    .sortedBy { it.getBadCharactersCount(TARGET) }
    .take((POPULATION_SIZE * ELITE_RATE).toInt())