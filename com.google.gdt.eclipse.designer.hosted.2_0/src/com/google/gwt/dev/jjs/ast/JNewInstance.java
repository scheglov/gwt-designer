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
 * A new instance expression. This differs from a standard Java new operation in
 * that no constructor is implied. Rather, a new operation creates an
 * uninitialized Object which is passed as an argument to a constructor method.
 */
public class JNewInstance extends JExpression {

  private final JNonNullType type;

  public JNewInstance(SourceInfo info, JNonNullType type) {
    super(info);
    assert type.getUnderlyingType() instanceof JClassType;
    this.type = type;
  }

  public JClassType getClassType() {
    return (JClassType) type.getUnderlyingType();
  }

  public JNonNullType getType() {
    return type;
  }

  @Override
  public boolean hasSideEffects() {
    // The actual new operation itself has no side effects (see class comment).
    return false;
  }

  public void traverse(JVisitor visitor, Context ctx) {
    if (visitor.visit(this, ctx)) {
    }
    visitor.endVisit(this, ctx);
  }

}
