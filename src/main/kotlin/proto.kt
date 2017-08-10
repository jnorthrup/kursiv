//Protobuf's language EBNF grammar by Alek Strom obtained from:
//http://groups.google.com/group/protobuf/browse_thread/thread/1cccfc624cd612da
//
//proto  ::= ( message | extend | enum | import | package | option | ";" )*
package proto;


abstract class  Oper (val state: parseState): Function<Oper> {

    abstract fun invoke(vararg p1: Oper): Oper;

 companion object Dead : Oper(state= parseState.Empty ) {
         override fun invoke(vararg p1: Oper): Oper {
             return Dead;
        }

    }
}


 class parseState( ) {
companion object {
    val Empty= parseState ();
}}
;
fun repeated(any_of: Any): Any {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
/*

class proto(
        repeated(
        any_of(
        message_kw (),
extend_kw() ,
enum_kw (),
import_kw (),
package_kw (),
option_kw() ,
chlit(";")
)
)
)
*/

//
//import ::= "import" strLit ";"
//
//package ::= "package" ident ";"
//
//option ::= "option" optionBody ";"
//
//optionBody ::= ident ( "." ident )* "=" constant
//
//message ::= "message" ident messageBody
//
//extend ::= "extend" userType messageBody
//
//enum ::= "enum" ident "{" ( option | enumField | ";" )* "}"
//
//enumField ::= ident "=" intLit ";"
//
//service ::= "service" ident "{" ( option | rpc | ";" )* "}"
//
//rpc ::= "rpc" ident "(" userType ")" "returns" "(" userType ")" ";"
//
//messageBody ::= "{" ( field | enum | message | extend | extensions |
//group | option | ":" )* "}"
//
//group ::= modifier "group" camelIdent "=" intLit messageBody
//
//# tag number must be 2^28-1 or lower
//field ::= modifier type ident "=" intLit ( "[" fieldOption ( ","
//fieldOption )* "]" )? ";"
//
//fieldOption ::= optionBody | "default" "=" constant
//
//extensions ::= "extensions" intLit "to" ( intLit | "max" ) ";"
//
//modifier ::= "required" | "optional" | "repeated"
//
//type ::= "double" | "float" | "int32" | "int64" | "uint32" | "uint64"
//| "sint32" | "sint64" | "fixed32" | "fixed64" | "sfixed32" |
//"sfixed64"
//| "bool" | "string" | "bytes" | userType
//
//# leading dot for identifiers means they're fully qualified
//userType ::= ( "."? ident )+
//
//constant ::= ident | intLit | floatLit | strLit | boolLit
//
//ident ::= /[A-Za-z_][\w_]*/
//
//# according to parser.cc, group names must start with a capital letter as a
//# hack for backwards-compatibility
//camelIdent ::= /[A-Z][\w_]*/
//
//intLit ::= decInt | hexInt | octInt
//
//decInt ::= /\d+/
//
//hexInt ::= /0[xX]([A-Fa-f0-9])+/
//
//octInt ::= /0[0-7]+/
//
//floatLit ::= /\d+(\.\d+)?([Ee][\+-]?\d+)?/ # allow_f_after_float_ is
//disabled by default in tokenizer.cc
//
//boolLit ::= "true" | "false"
//
//strLit ::= quote ( hexEscape | octEscape | charEscape | /[^\0\n]/ )*
//quote
//
//quote ::= /["']/
//
//hexEscape ::= /\\[Xx][A-Fa-f0-9]{1,2}/
//
//octEscape ::= /\\0?[0-7]{1,3}/
//
//charEscape ::= /\\[abfnrtv\\\?'"]/
