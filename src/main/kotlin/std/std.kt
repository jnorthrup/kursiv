package std;

fun <T> cat(vararg b: Sequence<T>) = b.reduce { a, x -> a + x }

fun bstr(k: Sequence<Byte>) = String(k.toList().toByteArray(), Charsets.UTF_8)
fun cstr(k: Sequence<Char>) = String(k.toList().toCharArray())
fun str(vararg k: Byte) = String(k)
fun str(vararg k: Char) = String(k)


val Char.lit: chOp
    get() = chlit(this)
typealias chOp = (Sequence<Char>) -> Boolean

class chIn(val chars: Array<Char>) : chOp {
    override fun invoke(p1: Sequence<Char>) = chars.contains(p1.first())

    constructor(vararg char: Char) : this(char.toTypedArray())
}


private fun Array<Char>.lit(): chOp {
    return chIn(this);

}

class chlit(val c: Char) : chOp {
    override fun invoke(p1: Sequence<Char>) = c == p1.first()
}

class any_of(vararg val of: chOp) : chOp {
    override fun invoke(p1: Sequence<Char>) = of.any { it(p1) }
}


class seq(vararg val use: chOp) : chOp {
    override fun invoke(p1: Sequence<Char>) = use.all { it(p1) }
}

class repeating(vararg val function: chOp) : chOp {
    override fun invoke(p1: Sequence<Char>) = function.all { it(p1) }

}