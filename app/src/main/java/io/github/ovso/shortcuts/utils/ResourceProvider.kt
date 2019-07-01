package io.github.ovso.shortcuts.utils

import android.content.Context
import android.content.res.Resources
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.IntegerRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import java.nio.charset.StandardCharsets
import java.nio.charset.Charset as Charset1

class ResourceProvider(private var context: Context) {

  @NonNull
  fun getText(@StringRes resId: Int) = context.getText(resId)

  @NonNull
  fun getTextArray(@ArrayRes resId: Int) = context.resources.getTextArray(resId)

  @NonNull
  fun getQuantityText(@PluralsRes resId: Int, quantity: Int) =
    context.resources.getQuantityText(resId, quantity)

  @NonNull
  fun getString(@StringRes resId: Int): String {
    return context.getString(resId)
  }

  @NonNull
  fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
    return context.getString(resId, formatArgs)
  }

  @NonNull
  fun getStringArray(@ArrayRes resId: Int) = context.resources.getStringArray(resId)

  @NonNull
  fun getQuantityString(@PluralsRes resId: Int, quantity: Int) =
    context.resources.getQuantityString(resId, quantity)

  @NonNull
  fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any) =
    context.resources.getQuantityString(resId, quantity, formatArgs)

  fun getInteger(@IntegerRes resId: Int) = context.resources.getInteger(resId)

  @NonNull
  fun getIntArray(@ArrayRes resId: Int) = context.resources.getIntArray(resId)

  fun getBoolean(@BoolRes resId: Int) = context.resources.getBoolean(resId)

  fun getDimension(@DimenRes resId: Int) = context.resources.getDimension(resId)

  fun getDimensionPixelSize(@DimenRes resId: Int) =
    context.resources.getDimensionPixelSize(resId)

  fun getDimensionPixelOffset(@DimenRes resId: Int) =
    context.resources.getDimensionPixelOffset(resId)

  fun getDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(context, resId)

  @ColorInt
  fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(context, resId)

  fun getColorStateList(@ColorRes resId: Int) = ContextCompat.getColorStateList(context, resId)

  @Nullable
  @Throws(Resources.NotFoundException::class)
  fun getFont(@FontRes id: Int) = ResourcesCompat.getFont(context, id)

  fun loadAnimation(@AnimRes id: Int) = AnimationUtils.loadAnimation(context, id)

  @Nullable fun toJsonStr(fileName: String) =
    try {
      val assetManager = context.resources.assets
      val inputStream = assetManager.open(fileName)
      val buffer = ByteArray(inputStream.available())
      inputStream.read(buffer)
      inputStream.close()
      String(buffer, StandardCharsets.UTF_8)
    } catch (e: Exception) {
      null
    }
}