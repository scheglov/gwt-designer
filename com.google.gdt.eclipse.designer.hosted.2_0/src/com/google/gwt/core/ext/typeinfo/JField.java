/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.google.gwt.core.ext.typeinfo;

import com.google.gwt.dev.util.StringInterner;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Represents a field declaration.
 */
@SuppressWarnings("deprecation")
public class JField implements HasAnnotations, HasMetaData {

  private final Annotations annotations;

  private final JClassType enclosingType;

  private int modifierBits;

  private final String name;

  private JType type;

  JField(JClassType enclosingType, JField srcField) {
    this.annotations = new Annotations(srcField.annotations);
    this.enclosingType = enclosingType;
    this.modifierBits = srcField.modifierBits;
    this.name = srcField.name;
    this.type = srcField.type;
  }

  JField(JClassType enclosingType, String name) {
    this(enclosingType, name, null);
  }

  JField(JClassType enclosingType, String name,
      Map<Class<? extends Annotation>, Annotation> declaredAnnotations) {
    assert (enclosingType != null);
    this.enclosingType = enclosingType;
    this.name = StringInterner.get().intern(name);
    this.enclosingType.addField(this);
    annotations = new Annotations(declaredAnnotations);
  }

  public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
    return annotations.getAnnotation(annotationClass);
  }

  public JClassType getEnclosingType() {
    return enclosingType;
  }

  @Deprecated
  public final String[][] getMetaData(String tagName) {
    return TypeOracle.NO_STRING_ARR_ARR;
  }

  @Deprecated
  public final String[] getMetaDataTags() {
    return TypeOracle.NO_STRINGS;
  }

  public String getName() {
    assert (name != null);
    return name;
  }

  public JType getType() {
    assert (type != null);
    return type;
  }

  public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
    return annotations.isAnnotationPresent(annotationClass);
  }

  public boolean isDefaultAccess() {
    return 0 == (modifierBits & (TypeOracle.MOD_PUBLIC | TypeOracle.MOD_PRIVATE | TypeOracle.MOD_PROTECTED));
  }

  public JEnumConstant isEnumConstant() {
    return null;
  }

  public boolean isFinal() {
    return 0 != (modifierBits & TypeOracle.MOD_FINAL);
  }

  public boolean isPrivate() {
    return 0 != (modifierBits & TypeOracle.MOD_PRIVATE);
  }

  public boolean isProtected() {
    return 0 != (modifierBits & TypeOracle.MOD_PROTECTED);
  }

  public boolean isPublic() {
    return 0 != (modifierBits & TypeOracle.MOD_PUBLIC);
  }

  public boolean isStatic() {
    return 0 != (modifierBits & TypeOracle.MOD_STATIC);
  }

  public boolean isTransient() {
    return 0 != (modifierBits & TypeOracle.MOD_TRANSIENT);
  }

  public boolean isVolatile() {
    return 0 != (modifierBits & TypeOracle.MOD_VOLATILE);
  }

  @Override
  public String toString() {
    String[] names = TypeOracle.modifierBitsToNames(modifierBits);
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < names.length; i++) {
      if (i > 0) {
        sb.append(" ");
      }
      sb.append(names[i]);
    }
    if (names.length > 0) {
      sb.append(" ");
    }
    sb.append(type.getParameterizedQualifiedSourceName());
    sb.append(" ");
    sb.append(getName());
    return sb.toString();
  }

  void addModifierBits(int modifierBits) {
    this.modifierBits |= modifierBits;
  }

  /**
   * NOTE: This method is for testing purposes only.
   */
  Annotation[] getAnnotations() {
    return annotations.getAnnotations();
  }

  /**
   * NOTE: This method is for testing purposes only.
   */
  Annotation[] getDeclaredAnnotations() {
    return annotations.getDeclaredAnnotations();
  }

  void setType(JType type) {
    this.type = type;
  }
}
