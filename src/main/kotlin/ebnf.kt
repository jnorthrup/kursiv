/*
letter = "A" | "B" | "C" | "D" | "E" | "F" | "G"
| "H" | "I" | "J" | "K" | "L" | "M" | "N"
| "O" | "P" | "Q" | "R" | "S" | "T" | "U"
| "V" | "W" | "X" | "Y" | "Z" | "a" | "b"
| "c" | "d" | "e" | "f" | "g" | "h" | "i"
| "j" | "k" | "l" | "m" | "n" | "o" | "p"
| "q" | "r" | "s" | "t" | "u" | "v" | "w"
| "x" | "y" | "z" ;

digit = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
symbol = "[" | "]" | "{" | "}" | "(" | ")" | "<" | ">" | "'" | '"' | "=" | "|" | "." | "," | ";" ;
character = letter | digit | symbol | "_" ;

identifier = letter , { letter | digit | "_" } ;
terminal = "'" , character , { character } , "'" | '"' , character , { character } , '"' ;

lhs = identifier ;
rhs = identifier | terminal | "[" , rhs , "]" | "{" , rhs , "}" | "(" , rhs , ")" | rhs , "|" , rhs | rhs , "," , rhs ;

rule = lhs , '=' , rhs , ';' ;
grammar = { rule } ;
*/

typealias chOp = (Sequence<Char>) -> Boolean

class chIn( val chars: Array<Char>) : chOp {
    override fun invoke(p1: Sequence<Char>) = p1.first() in chars

    constructor(vararg char:  Char ) : this(chars = char.toTypedArray() )
}

val digit =  arrayOf( '0', '1', '2', '3', '4', '5', '6', '7', '8', '9').lit()

private fun Array<Char>.lit(): chOp{
    return chIn( this);

}

val letter =  charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z').lit()

private fun CharArray.lit()  = this.toTypedArray().lit()

val symbol = chIn('[', ']', '{', '}', '(', ')', '<', '>', '\'', '\'', '=', '|', '.', ',', ';')

val singleQuote = chIn('\'')
val doubleQuote = chIn('"')
val uscore = chIn('_')
val character = seq(letter, digit, symbol, uscore)


class any_of(vararg val of: chOp) : chOp {
    override fun invoke(p1: Sequence<Char>) = of.any { it(p1) }
}


val identifier = { p1: Sequence<Char> -> seq(letter, repeating(any_of(letter, digit, uscore))).invoke(p1) }

class seq( vararg val use:  chOp ) : chOp {
    override fun invoke(p1: Sequence<Char>) = use.all { it(p1) }
  }

class repeating(vararg val function: chOp) : chOp {
    override fun invoke(p1: Sequence<Char>) = function.all { it(p1) }

}
//terminal = "'" , character , { character } , "'" | '"' , character , { character } , '"' ;
val terminal = any_of(seq(singleQuote, character, repeating(character), singleQuote), seq(doubleQuote, character, repeating(character), doubleQuote))
val lhs = identifier


class rhs : chOp {
    override fun invoke(p1: Sequence<Char>) =
            any_of(
                    identifier,
                    terminal,
                    seq( ('['.lit()), rhs_,  (']').lit()),
                    seq( ('{'.lit()), rhs_,  ('}'.lit())),
                    seq( ('('.lit()), rhs_,  (')'.lit())),
                    seq(rhs_,  ('|'.lit()), rhs_),
                    seq(rhs_,  (','.lit()), rhs_))(p1)

     companion object {val rhs_ =rhs   ();}

}


fun  Char.lit(): chOp = chlit (this)
class chlit(val c:Char):chOp{
    override fun invoke(p1: Sequence<Char>)  = p1.first()==c

}

val rule = seq(lhs , chIn('=') , rhs(), chIn(';'))
val grammar  = repeating(rule )
