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

import com.google.gwt.dev.util.Name.InternalName;

import org.eclipse.jdt.internal.compiler.ASTVisitor;
import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.ast.TypeDeclaration;
import org.eclipse.jdt.internal.compiler.lookup.BlockScope;
import org.eclipse.jdt.internal.compiler.lookup.ClassScope;
import org.eclipse.jdt.internal.compiler.lookup.CompilationUnitScope;

/**
 * Base class of things that walk methods in a CUD and collect things
 * about interesting methods.
 */
public abstract class MethodVisitor {

  /**
   * Gets a unique name for this method, including its signature.
   */
  protected static String getMemberSignature(AbstractMethodDeclaration method) {
    String name = String.valueOf(method.selector);
    StringBuilder sb = new StringBuilder();
    sb.append(name);
    sb.append("(");
    if (method.arguments != null) {
      for (Argument param : method.arguments) {
        sb.append(param.binding.type.signature());
      }
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * Collect data about interesting methods in one compilation unit.
   * 
   * @param cud
   */
  public final void collect(final CompilationUnitDeclaration cud) {
    cud.traverse(new ASTVisitor() {
      @Override
      public void endVisit(TypeDeclaration type, BlockScope scope) {
        collectMethods(cud, type);
      }

      @Override
      public void endVisit(TypeDeclaration type, ClassScope scope) {
        collectMethods(cud, type);
      }

      @Override
      public void endVisit(TypeDeclaration type, CompilationUnitScope scope) {
        collectMethods(cud, type);
      }
    }, cud.scope);
  }

  /**
   * Provided by a subclass to return true if this method should be processed.
   * This is separate since some extra work is performed in order to call
   * {@link #processMethod}.
   * 
   * @param method
   * @return true if processMethod should be called on this method
   */
  protected abstract boolean interestingMethod(
      AbstractMethodDeclaration method);

  /**
   * Provided by a subclass to process a method definition.  Methods which have
   * no name are not passed to this method, even if {@link #interestingMethod}
   * returns true.
   * 
   * @param typeDecl
   * @param method
   * @param enclosingType
   * @param loc
   */
  protected abstract void processMethod(TypeDeclaration typeDecl,
      AbstractMethodDeclaration method, String enclosingType, String loc);

  /**
   * Collect data about interesting methods on a particular type in a
   * compilation unit.
   *  
   * @param cud
   * @param typeDecl
   */
  private void collectMethods(CompilationUnitDeclaration cud,
      TypeDeclaration typeDecl) {
    AbstractMethodDeclaration[] methods = typeDecl.methods;
    if (methods == null) {
      return;
    }

    // Lazy initialize these when an interesting method is actually hit.
    String enclosingType = null;
    String loc = null;
    boolean lazyInitialized = false;

    for (AbstractMethodDeclaration method : methods) {
      if (!interestingMethod(method)) {
        continue;
      }

      if (!lazyInitialized) {
        char[] constantPoolName = typeDecl.binding.constantPoolName();
        if (constantPoolName == null) {
          // Unreachable local type
          return;
        }
        enclosingType = InternalName.toBinaryName(String.valueOf(
            constantPoolName));
        loc = String.valueOf(cud.getFileName());
        lazyInitialized = true;
      }
      processMethod(typeDecl, method, enclosingType, loc);
    }
  }
}
