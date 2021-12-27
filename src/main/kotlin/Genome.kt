data class Genome(private val name: String) {
    fun getBadCharactersCount(target: String): Int {
        assertLengthEquality(target)
        return (name.indices).count { name[it] != target[it] }
    }

    fun mate(genome: Genome): Genome {
        assertLengthEquality(genome.name)
        val newName = createNewNameFrom(genome)
        return Genome(newName)
    }

    fun mutate(): Genome {
        val name = createNewName()
        return Genome(name)
    }

    private fun createNewName(): String {
        val position = generateNewPosition()
        val newChar = getRandomLatinCharacter()
        return name.substring(0, position) + newChar + name.substring(position + 1)
    }

    private fun getRandomLatinCharacter(): Char {
        return (Math.random() * 26 + 65).toInt().toChar()
    }

    private fun createNewNameFrom(genome: Genome): String {
        val position = generateNewPosition()
        return name.substring(0, position) + genome.name.substring(position)
    }

    private fun generateNewPosition(): Int {
        return (Math.random() * name.length).toInt()
    }

    private fun assertLengthEquality(target: String) {
        if (name.length != target.length) {
            throw IllegalArgumentException("target must have the same length as name")
        }
    }
}