package dk.cphbusiness.mal.utils

import java.io.File
import kotlin.concurrent.timer

fun File.toStringArray(delimiterPattern: String, toLower: Boolean = true) =
  readLines()
      .flatMap { it.split(delimiterPattern.toRegex()) }
      .filter { it.isNotEmpty() }
      .map { if (toLower) it.toLowerCase() else it }
      .toTypedArray()

fun stopwatch(body: () -> Unit): Double {
  val nanos = System.nanoTime()
  body()
  return (System.nanoTime() - nanos).toDouble()/1_000_000.0
  }

data class Result(val n: Int, val t: Double, val r: Double)

fun doubleRatioExperiment(number: Int = 1, algorithm: (Int) -> Unit) = sequence {
  var n = number
  var t = 1.0
  while (true) {
    val time = stopwatch { algorithm(n) }
    yield(Result(n, time, time/t))
    n *= 2
    t = time
    }
  }
