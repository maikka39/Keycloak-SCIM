inline fun <K, V, R> Map<out K, V>.mapKeysNotNull(transform: (Map.Entry<K, V>) -> R?): Map<R, V> {
    return mapKeys(transform).mapNotNull { param ->
        param.key?.let { it to param.value }
    }.toMap()
}