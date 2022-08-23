package com.example.jokenpo

enum class Result{
    WIN, DRAW, LOSS
}

class JokenpoEngine(private val availablePlays: Array<String>) {

    fun calculateResult(playerPlay: String): Result{
        val computerPlay = getRandomPlay()

        return when{
            playerPlay == computerPlay -> Result.DRAW
            playerPlay == "Rock" && computerPlay == "Scissor" -> Result.WIN
            playerPlay == "Scissor" && computerPlay == "Paper" -> Result.WIN
            playerPlay == "Paper" && computerPlay == "Rock" -> Result.WIN
            else -> Result.LOSS
        }

    }

    private fun getRandomPlay() : String{
        return availablePlays.random()
    }

}