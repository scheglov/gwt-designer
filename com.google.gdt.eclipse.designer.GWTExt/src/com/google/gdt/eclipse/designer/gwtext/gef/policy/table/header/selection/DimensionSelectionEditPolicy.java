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
package com.google.gdt.eclipse.designer.gwtext.gef.policy.table.header.selection;

import com.google.common.collect.Lists;
import com.google.gdt.eclipse.designer.gwtext.gef.policy.table.header.edit.DimensionHeaderEditPart;
import com.google.gdt.eclipse.designer.gwtext.model.layout.table.DimensionInfo;

import org.eclipse.wb.core.gef.header.AbstractHeaderSelectionEditPolicy;
import org.eclipse.wb.draw2d.Figure;
import org.eclipse.wb.draw2d.FigureUtils;
import org.eclipse.wb.draw2d.IColorConstants;
import org.eclipse.wb.draw2d.ILocator;
import org.eclipse.wb.draw2d.geometry.Rectangle;
import org.eclipse.wb.gef.graphical.handles.Handle;
import org.eclipse.wb.gef.graphical.handles.MoveHandle;
import org.eclipse.wb.gef.graphical.policies.LayoutEditPolicy;
import org.eclipse.wb.gef.graphical.policies.SelectionEditPolicy;

import java.util.List;

/**
 * Abstract {@link SelectionEditPolicy} for {@link DimensionHeaderEditPart}.
 * 
 * @author scheglov_ke
 * @coverage GWTExt.gef.TableLayout
 */
abstract class DimensionSelectionEditPolicy extends AbstractHeaderSelectionEditPolicy {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public DimensionSelectionEditPolicy(LayoutEditPolicy mainPolicy) {
    super(mainPolicy);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Handles
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected List<Handle> createSelectionHandles() {
    List<Handle> handles = Lists.newArrayList();
    // move handle
    {
      MoveHandle moveHandle = new MoveHandle(getHost(), new HeaderMoveHandleLocator());
      moveHandle.setForeground(IColorConstants.red);
      handles.add(moveHandle);
    }
    //
    return handles;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Utils
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * @return the host {@link FormDimensionInfo}.
   */
  protected final DimensionInfo getDimension() {
    return ((DimensionHeaderEditPart) getHost()).getDimension();
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Move location
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * Implementation of {@link ILocator} to place handle directly on header.
   */
  private class HeaderMoveHandleLocator implements ILocator {
    public void relocate(Figure target) {
      Figure reference = getHostFigure();
      Rectangle bounds = reference.getBounds().getCopy();
      FigureUtils.translateFigureToFigure(reference, target, bounds);
      target.setBounds(bounds);
    }
  }
}
