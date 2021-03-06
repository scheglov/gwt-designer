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
 * Conditional expression.
 */
public class JConditional extends JExpression {

  private JExpression elseExpr;
  private JExpression ifTest;
  private JExpression thenExpr;
  private JType type;

  public JConditional(SourceInfo info, JType type, JExpression ifTest,
      JExpression thenExpr, JExpression elseExpr) {
    super(info);
    this.type = type;
    this.ifTest = ifTest;
    this.thenExpr = thenExpr;
    this.elseExpr = elseExpr;
  }

  public JExpression getElseExpr() {
    return elseExpr;
  }

  public JExpression getIfTest() {
    return ifTest;
  }

  public JExpression getThenExpr() {
    return thenExpr;
  }

  public JType getType() {
    return type;
  }

  public boolean hasSideEffects() {
    return ifTest.hasSideEffects() || thenExpr.hasSideEffects()
        || elseExpr.hasSideEffects();
  }

  public void setType(JType newType) {
    type = newType;
  }

  public void traverse(JVisitor visitor, Context ctx) {
    if (visitor.visit(this, ctx)) {
      ifTest = visitor.accept(ifTest);
      thenExpr = visitor.accept(thenExpr);
      elseExpr = visitor.accept(elseExpr);
    }
    visitor.endVisit(this, ctx);
  }

}
