// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: sample.proto
package com.github.pavlospt

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.WireField
import kotlin.Any
import kotlin.AssertionError
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.Nothing
import kotlin.String
import kotlin.hashCode
import kotlin.jvm.JvmField
import okio.ByteString

class Box(
  @field:WireField(
    tag = 5,
    adapter = "com.github.pavlospt.Subscription#ADAPTER"
  )
  val subscription: Subscription? = null,
  unknownFields: ByteString = ByteString.EMPTY
) : Message<Box, Nothing>(ADAPTER, unknownFields) {
  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN
  )
  override fun newBuilder(): Nothing = throw AssertionError()

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is Box) return false
    return unknownFields == other.unknownFields
        && subscription == other.subscription
  }

  override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + subscription.hashCode()
      super.hashCode = result
    }
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    if (subscription != null) result += """subscription=$subscription"""
    return result.joinToString(prefix = "Box{", separator = ", ", postfix = "}")
  }

  fun copy(subscription: Subscription? = this.subscription, unknownFields: ByteString =
      this.unknownFields): Box = Box(subscription, unknownFields)

  companion object {
    @JvmField
    val ADAPTER: ProtoAdapter<Box> = object : ProtoAdapter<Box>(
      FieldEncoding.LENGTH_DELIMITED, 
      Box::class, 
      "type.googleapis.com/com.github.pavlospt.Box"
    ) {
      override fun encodedSize(value: Box): Int = 
        Subscription.ADAPTER.encodedSizeWithTag(5, value.subscription) +
        value.unknownFields.size

      override fun encode(writer: ProtoWriter, value: Box) {
        if (value.subscription != null) Subscription.ADAPTER.encodeWithTag(writer, 5,
            value.subscription)
        writer.writeBytes(value.unknownFields)
      }

      override fun decode(reader: ProtoReader): Box {
        var subscription: Subscription? = null
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            5 -> subscription = Subscription.ADAPTER.decode(reader)
            else -> reader.readUnknownField(tag)
          }
        }
        return Box(
          subscription = subscription,
          unknownFields = unknownFields
        )
      }

      override fun redact(value: Box): Box = value.copy(
        subscription = value.subscription?.let(Subscription.ADAPTER::redact),
        unknownFields = ByteString.EMPTY
      )
    }
  }
}
