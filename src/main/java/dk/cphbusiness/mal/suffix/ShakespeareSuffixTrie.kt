package dk.cphbusiness.mal.suffix

import dk.cphbusiness.mal.utils.toStringArray
//import javafx.application.Application.launch
import javafx.beans.property.SimpleStringProperty
import javafx.stage.FileChooser
import tornadofx.App
import tornadofx.View
import tornadofx.launch
import tornadofx.*
import java.io.File


class ShakespeareSuffixKey(val text: String, val origin: Int = 0, val position: Int = 0) : Key {
  override val max = 'z' - 'a' + 3
  private fun indexOf(position: Int) =
    if (position >= text.length) 0
    else when (text[position]) {
      ' ' -> 1
      in 'a'..'z' -> text[position] - 'a' + 2
      in 'A'..'Z' -> text[position] - 'A' + 2
      else -> max
      }
  override val index get() = indexOf(position)
  override val length get() = text.length - position
  val size get() = text.length - origin
  override val next get() = ShakespeareSuffixKey(text, origin, position + 1)
  val rest get() = ShakespeareSuffixKey(text, origin + 1, origin + 1)
  override fun includes(other: Key) =
      other is ShakespeareSuffixKey &&
      other.size <= this.size &&
      (0 until other.text.length - other.origin).all {
          val a = other.indexOf(it + other.origin)
          val b = this.indexOf(it + this.origin)
          a == b
          // other.indexOf(it + other.origin) == this.indexOf(it + this.origin)
          }
  override fun equals(other: Any?) = false // TODO Takes too long in suffix trie, but verify it's ok
//    other is ShakespeareSuffixKey &&
//    text.length == other.text.length &&
//    (position .. text.length).all { this.indexOf(it) == other.indexOf(it) }
  override fun toString() = buildString {
    for (i in (origin .. Math.min(text.length - 1, origin + 100))) {
      if (i == position) append('[')
      append(text[i])
      }
    if (position > origin + 100) append("...[")
    if (text.length <= origin + 100) append("...]")
    else append("]")
    }
  //"${text.before(position)}[${text.at(position)}${text.after(position)}]"
  }

class ShakespeareSuffixTrie : Iterable<Pair<Key, Int>> {
  var trie: Trie<Int>? = null

  fun add(key: ShakespeareSuffixKey) {
    if (trie == null) trie = Trie.KeyTrie(key) { key.position }
    else trie = trie?.add(key) { key.position }
    }

  fun load(file: File) {
    var works = ShakespeareSuffixKey(file.toStringArray("[^A-Za-z']+", toLower = false).joinToString(" "))
    while (works.size > 0) {
      add(works)
      works = works.rest
      }
    }

  operator fun get(word: String) = trie?.find(ShakespeareSuffixKey(word))

  override fun iterator(): Iterator<Pair<Key, Int>> = iterator { trie?.let { yieldAll(it) } }
  }

fun suffixTrieFromFile(fileName: String): ShakespeareSuffixTrie {
  val words = File(fileName).toStringArray("[^A-Za-z']+", toLower = false)
  var works = ShakespeareSuffixKey(words.joinToString(" "))

  val suffixTree = ShakespeareSuffixTrie()
  while (works.size > 0) {
    suffixTree.add(works)
    works = works.rest
    }
  return suffixTree
  }

const val shakespeareTextFileName =
    "/Users/AKA/DatSoftLyngby/soft2020spring-alg/data/shakespeare-complete-works.txt"

fun tryShakespeareSuffixTrie() {
  val suffixTree = suffixTrieFromFile(shakespeareTextFileName)

  val toBeOrNot = suffixTree.trie?.locate(ShakespeareSuffixKey("to be or no"))
  toBeOrNot?.forEach { println("${it.first} --> ${it.second}")}
  }

class Shakespeare : App(ShakespeareView::class)

fun main() {
  // tryShakespeareSuffixTrie()
  launch<Shakespeare>()
  }

class ShakespeareView : View() {
  val searchString = SimpleStringProperty()
  val suffixTrie = ShakespeareSuffixTrie()
  val answers = mutableListOf<String>().asObservable()
  init {
    searchString.addListener { _, _, sentence ->
      answers.clear()
      val lines = suffixTrie.trie?.locate(ShakespeareSuffixKey(sentence))
      lines?.take(20)?.forEach {
        answers += "${it.first} --> ${it.second}"
        }
      }
    }
  override val root = borderpane {
    top {
      hbox {
        menubar {
          menu("File") {
            item("Open", "Shortcut+O").action {
              val textFilter = FileChooser.ExtensionFilter("Textfiles", "*.txt")
              val files = chooseFile("Textfile to search", filters = arrayOf(textFilter))
              files.forEach { suffixTrie.load(it) }
              }
            }
          }
        textfield(searchString)
        }
      }
    center {
      listview(answers)
      }
    }
  }