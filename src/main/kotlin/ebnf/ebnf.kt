package ebnf

import std.*

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

val digit = chIn('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
val letter = chIn('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
val symbol = chIn('[', ']', '{', '}', '(', ')', '<', '>', '\'', '\'', '=', '|', '.', ',', ';')
val singleQuote = '\''.lit
val doubleQuote = '"'.lit
val uscore = '_'.lit
val character = seq(letter, digit, symbol, uscore)
val identifier = { p1: Sequence<Char> -> seq(letter, repeating(any_of(letter, digit, uscore)))(p1) }
val terminal = any_of(seq(singleQuote, character, repeating(character), singleQuote), seq(doubleQuote, character, repeating(character), doubleQuote))
val lhs = identifier
object rhs : chOp  { override fun invoke(p1: Sequence<Char>) = any_of(identifier, terminal, seq('['.lit, rhs, ']'.lit), seq(('{'.lit), rhs, '}'.lit), seq(('('.lit), rhs, (')'.lit)), seq(rhs, ('|'.lit), rhs), seq(rhs, (','.lit), rhs))(p1) }
val rule = seq(lhs, '='.lit, rhs , ';'.lit)
val grammar = repeating(rule)

