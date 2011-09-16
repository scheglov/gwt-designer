/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gdt.eclipse.designer.uibinder.gef.policy;

import com.google.gdt.eclipse.designer.uibinder.model.widgets.GridInfo;
import com.google.gdt.eclipse.designer.uibinder.model.widgets.GridInfo.Cell;
import com.google.gdt.eclipse.designer.uibinder.model.widgets.GridInfo.Row;
import com.google.gdt.eclipse.designer.uibinder.model.widgets.WidgetInfo;

import org.eclipse.wb.core.gef.policy.layout.flow.ObjectFlowLayoutEditPolicy;
import org.eclipse.wb.core.gef.policy.validator.LayoutRequestValidators;
import org.eclipse.wb.gef.core.EditPart;
import org.eclipse.wb.gef.core.policies.ILayoutRequestValidator;
import org.eclipse.wb.gef.core.requests.Request;
import org.eclipse.wb.gef.tree.policies.LayoutEditPolicy;

/**
 * {@link LayoutEditPolicy} for {@link GridInfo}.
 * 
 * @author scheglov_ke
 * @coverage GWT.UiBinder.gef
 */
public final class GridLayoutEditPolicy extends ObjectFlowLayoutEditPolicy<Object> {
  public static final ILayoutRequestValidator VALIDATOR = LayoutRequestValidators.or(
      LayoutRequestValidators.modelType(Row.class),
      LayoutRequestValidators.modelType(Cell.class),
      LayoutRequestValidators.modelType(WidgetInfo.class));
  private final GridInfo m_grid;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public GridLayoutEditPolicy(GridInfo grid) {
    super(grid);
    m_grid = grid;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Requests
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected boolean isGoodReferenceChild(Request request, EditPart editPart) {
    return editPart.getModel() instanceof Row;
  }

  @Override
  protected ILayoutRequestValidator getRequestValidator() {
    return VALIDATOR;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // AbstractFlowLayoutEditPolicy
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected boolean isHorizontal(Request request) {
    return false;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Commands
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void command_CREATE(Object newObject, Object referenceObject) throws Exception {
    if (newObject instanceof WidgetInfo) {
      m_grid.command_CREATE((WidgetInfo) newObject, (Row) referenceObject);
    }
  }

  @Override
  protected void command_MOVE(Object object, Object referenceObject) throws Exception {
    if (object instanceof Row) {
      m_grid.command_MOVE((Row) object, (Row) referenceObject);
    }
  }

  @Override
  protected void command_ADD(Object object, Object referenceObject) throws Exception {
    if (object instanceof Cell) {
      m_grid.command_ADD((Cell) object, (Row) referenceObject);
    }
    if (object instanceof WidgetInfo) {
      m_grid.command_ADD((WidgetInfo) object, (Row) referenceObject);
    }
  }
}