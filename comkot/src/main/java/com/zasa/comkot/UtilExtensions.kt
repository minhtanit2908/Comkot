package com.zasa.comkot

inline fun <T : Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) = if (elements.all { it != null }) {
    closure(elements.filterNotNull())
} else null

inline fun <T, R> List<T>?.ifNotEmptyLet(closure: (List<T>) -> R): R? {
    return if (this.isNullOrEmpty()) null else closure(this)
}

inline fun <R> Boolean.ifLet(closure: () -> R): R? = if (this) closure.invoke() else null
