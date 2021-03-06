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
package com.google.gwt.dev.javac;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JAbstractMethod;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JPackage;
import com.google.gwt.core.ext.typeinfo.JRealClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.JTypeParameter;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dev.javac.asm.CollectAnnotationData;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * Interface for resolving various aspects of a class.
 */
public interface Resolver {

  void addImplementedInterface(JRealClassType type, JClassType intf);

  void addThrows(JAbstractMethod method, JType exception);

  Map<String, JRealClassType> getBinaryMapper();

  TypeOracle getTypeOracle();

  JMethod newMethod(JClassType type, String name,
      Map<Class<? extends Annotation>, Annotation> declaredAnnotations,
      JTypeParameter[] typeParams);

  void newParameter(JAbstractMethod method, JType argType, String argName,
      Map<Class<? extends Annotation>, Annotation> declaredAnnotations,
      boolean argNamesAreReal);

  JRealClassType newRealClassType(JPackage pkg, String enclosingTypeName,
      boolean isLocalType, String className, boolean isIntf);

  boolean resolveAnnotation(TreeLogger logger,
      CollectAnnotationData annotVisitor,
      Map<Class<? extends Annotation>, Annotation> declaredAnnotations);

  boolean resolveAnnotations(TreeLogger logger,
      List<CollectAnnotationData> annotations,
      Map<Class<? extends Annotation>, Annotation> declaredAnnotations);

  boolean resolveClass(TreeLogger logger, JRealClassType type);

  void setReturnType(JAbstractMethod method, JType returnType);

  void setSuperClass(JRealClassType type, JClassType superType);
}
