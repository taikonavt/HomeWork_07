package otus.homework.customview

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.BufferedReader
import java.io.InputStreamReader

fun getPayload(context: Context): List<Purchase>? {
    val bufferedReader = BufferedReader(
        InputStreamReader(
            context.resources.openRawResource(
                context.resources.getIdentifier(
                    "payload",
                    "raw", context.packageName
                )
            )
        )
    )
    val payloadString = bufferedReader.use { it.readText() }
    val type = Types.newParameterizedType(MutableList::class.java, Purchase::class.java)
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build().adapter<List<Purchase>>(type).fromJson(payloadString)
}
