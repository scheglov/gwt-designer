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
package com.google.gwt.dev.jjs.ast;

import com.google.gwt.dev.jjs.SourceInfo;

/**
 * Java instance of expression.
 */
public class JInstanceOf extends JExpression {

  private JExpression expr;
  private final JReferenceType testType;

  public JInstanceOf(SourceInfo info, JReferenceType testType,
      JExpression expression) {
    super(info);
    this.testType = testType;
    this.expr = expression;
  }

  public JExpression getExpr() {
    return expr;
  }

  public JReferenceType getTestType() {
    return testType;
  }

  public JType getType() {
    return JPrimitiveType.BOOLEAN;
  }

  public boolean hasSideEffects() {
    return false;
  }

  public void traverse(JVisitor visitor, Context ctx) {
    if (visitor.visit(this, ctx)) {
      expr = visitor.accept(expr);
    }
    visitor.endVisit(this, ctx);
  }

}
