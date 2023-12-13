package extensions

import kotlin.reflect.KClass

fun <T : Any> KClass<T>.fromMap(values: Map<String, Any?>): T {
    val cons = this.constructors.first()
    val valueMap = values.mapKeys { entry -> cons.parameters.first { it.name == entry.key } }
    return cons.callBy(valueMap)
}