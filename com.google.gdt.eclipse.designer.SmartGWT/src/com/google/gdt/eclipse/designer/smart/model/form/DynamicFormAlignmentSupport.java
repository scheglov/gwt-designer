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
package com.google.gdt.eclipse.designer.smart.model.form;

import org.eclipse.wb.core.model.IAbstractComponentInfo;
import org.eclipse.wb.core.model.ObjectInfo;
import org.eclipse.wb.draw2d.geometry.Dimension;
import org.eclipse.wb.draw2d.geometry.Point;
import org.eclipse.wb.internal.core.gef.policy.layout.absolute.actions.SimpleAlignmentActionsSupport;

/**
 * Alignment actions for {@link DynamicFormInfo}.
 * 
 * @author sablin_aa
 * @coverage SmartGWT.model
 */
public final class DynamicFormAlignmentSupport extends SimpleAlignmentActionsSupport<FormItemInfo> {
  private final DynamicFormInfo m_form;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public DynamicFormAlignmentSupport(DynamicFormInfo form) {
    m_form = form;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Implementation
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected boolean isComponentInfo(ObjectInfo object) {
    return object instanceof FormItemInfo;
  }

  @Override
  protected boolean isValidObjectOnRootPath(IAbstractComponentInfo parent) {
    return parent instanceof DynamicFormInfo;
  }

  @Override
  protected IAbstractComponentInfo getLayoutContainer() {
    return m_form;
  }

  @Override
  protected void commandChangeBounds(FormItemInfo component, Point location, Dimension size)
      throws Exception {
    DynamicFormInfo parent = (DynamicFormInfo) component.getParent();
    parent.command_BOUNDS(component, location, size);
  }
}