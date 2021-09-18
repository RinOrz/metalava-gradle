/*
 * Copyright (c) 2021. The Meowool Organization Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.

 * In addition, if you modified the project, you must include the Meowool
 * organization URL in your code file: https://github.com/meowool
 *
 * 如果您修改了此项目，则必须确保源文件中包含 Meowool 组织 URL: https://github.com/meowool
 */
package me.tylerbwong.gradle.metalava.extension

import me.tylerbwong.gradle.metalava.Documentation
import me.tylerbwong.gradle.metalava.Format
import me.tylerbwong.gradle.metalava.Signature
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

open class MetalavaExtension(val project: Project) {

  private val parentExtension: MetalavaExtension? get() = project.parent?.extensions?.findByType()

  /**
   * The version of Metalava to use.
   */
  var version: String? = null
    get() = field ?: parentExtension?.version ?: "1.0.0-alpha04"

  /**
   * A custom Metalava JAR location path to use instead of the embedded dependency.
   */
  var metalavaJarPath: String? = null
    get() = field ?: parentExtension?.metalavaJarPath

  /**
   * Sets the source level for Java source files; default is 11.
   */
  var javaSourceLevel: JavaVersion? = null
    get() = field ?: parentExtension?.javaSourceLevel ?: JavaVersion.VERSION_11

  /**
   * @see Format
   */
  var format: Format? = null
    get() = field ?: parentExtension?.format ?: Format.V4

  /**
   * @see Signature
   */
  var signature: Signature? = null
    get() = field ?: parentExtension?.signature ?: Signature.API

  /**
   * The final descriptor file output name.
   */
  var filename: String? = null
    get() = field ?: parentExtension?.filename
      ?: "api/${project.version.takeUnless { it == "unspecified" } ?: "current"}.api"

  /**
   * @see Documentation
   */
  var documentation: Documentation? = null
    get() = field ?: parentExtension?.documentation ?: Documentation.PROTECTED

  /**
   * Controls whether nullness annotations should be formatted as in Kotlin (with "?" for nullable
   * types, "" for non-nullable types, and "!" for unknown. The default is yes.
   */
  var outputKotlinNulls: Boolean? = null
    get() = field ?: parentExtension?.outputKotlinNulls ?: true

  /**
   * Controls whether default values should be included in signature files. The default is yes.
   */
  var outputDefaultValues: Boolean? = null
    get() = field ?: parentExtension?.outputDefaultValues ?: true

  /**
   * Whether the signature files should include a comment listing the format version of the
   * signature file.
   */
  var includeSignatureVersion: Boolean? = null
    get() = field ?: parentExtension?.includeSignatureVersion ?: true

  /**
   * Remove the given packages from the API even if they have not been marked with @hide.
   */
  val hiddenPackages: MutableSet<String>? = null
    get() = field ?: parentExtension?.hiddenPackages?.toMutableSet() ?: mutableSetOf()

  /**
   * Treat any elements annotated with the given annotation as hidden.
   */
  val hiddenAnnotations: MutableSet<String>? = null
    get() = field ?: parentExtension?.hiddenAnnotations?.toMutableSet() ?: mutableSetOf()

  /**
   * Whether the signature file being read should be interpreted as having encoded its types using
   * Kotlin style types: a suffix of "?" for nullable types, no suffix for non-nullable types, and
   * "!" for unknown. The default is no.
   */
  var inputKotlinNulls: Boolean? = null
    get() = field ?: parentExtension?.inputKotlinNulls ?: false

  /**
   * Promote all warnings to errors.
   */
  var reportWarningsAsErrors: Boolean? = null
    get() = field ?: parentExtension?.reportWarningsAsErrors ?: false

  /**
   * Promote all API lint warnings to errors.
   */
  var reportLintsAsErrors: Boolean? = null
    get() = field ?: parentExtension?.reportLintsAsErrors ?: false

  /**
   * If the value is `true`, ignore the unsupported module, otherwise the module that does not
   * support will throw an exception.
   */
  var ignoreUnsupportedModules: Boolean? = null
    get() = field ?: parentExtension?.ignoreUnsupportedModules ?: false

  /**
   * For Android modules defines which variant should be used to resolve classpath when Metalava
   * generates or checks API.
   */
  var androidVariantName: String? = null
    get() = field ?: parentExtension?.androidVariantName ?: "debug"

  /**
   * Remove the given packages from the API even if they have not been marked with @hide.
   */
  fun hiddenPackages(vararg packages: String) {
    hiddenPackages!! += packages
  }

  /**
   * Treat any elements annotated with the given annotation as hidden.
   */
  fun hiddenAnnotations(vararg annotations: String) {
    hiddenAnnotations!! += annotations
  }
}
