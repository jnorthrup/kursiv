package std;

fun <T> cat(vararg b: Sequence<T>) = b.reduce { a, x-> a+x}






fun bstr(k: Sequence<Byte>) =String(k.toList().toByteArray(), Charsets.UTF_8)


fun cstr(k: Sequence<Char>) = String(k.toList().toCharArray())

fun str(vararg k:  Byte ) =String(k)

fun str(vararg k: Char  ) = String(k)