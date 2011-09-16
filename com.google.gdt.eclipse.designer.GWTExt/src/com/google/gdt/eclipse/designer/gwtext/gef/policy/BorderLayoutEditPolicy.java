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
package com.google.gdt.eclipse.designer.gwtext.gef.policy;

import com.google.gdt.eclipse.designer.gef.policy.WidgetPositionLayoutEditPolicy;
import com.google.gdt.eclipse.designer.gwtext.model.layout.BorderLayoutInfo;
import com.google.gdt.eclipse.designer.model.widgets.WidgetInfo;

import org.eclipse.wb.draw2d.geometry.Insets;
import org.eclipse.wb.gef.graphical.policies.LayoutEditPolicy;

/**
 * Implementation of {@link LayoutEditPolicy} for {@link BorderLayoutInfo}.
 * 
 * @author scheglov_ke
 * @coverage GWTExt.gef.policy
 */
public final class BorderLayoutEditPolicy
    extends
      WidgetPositionLayoutEditPolicy<WidgetInfo, String> {
  private final BorderLayoutInfo m_layout;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public BorderLayoutEditPolicy(BorderLayoutInfo layout) {
    super(layout);
    m_layout = layout;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Feedbacks
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void addFeedbacks() throws Exception {
    addFeedback(0, 0, 1, 0.25, new Insets(0, 0, 1, 0), "North", "north");
    addFeedback(0, 0.75, 1, 1, new Insets(1, 0, 0, 0), "South", "south");
    addFeedback(0, 0.25, 0.25, 0.75, new Insets(1, 0, 1, 1), "West", "west");
    addFeedback(0.75, 0.25, 1, 0.75, new Insets(1, 1, 1, 0), "East", "east");
    addFeedback(0.25, 0.25, 0.75, 0.75, new Insets(1, 1, 1, 1), "Center", "center");
  }

  /**
   * Adds feedback for given constraints.
   */
  private void addFeedback(double px1,
      double py1,
      double px2,
      double py2,
      Insets insets,
      String hint,
      String position) throws Exception {
    if (m_layout.getWidget(position) == null) {
      super.addFeedback(px1, py1, px2, py2, insets, hint, position);
    }
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Implementation of commands
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void command_CREATE(WidgetInfo component, String data) throws Exception {
    m_layout.command_CREATE(component, null);
    BorderLayoutInfo.getBorderData(component).setPosition(data);
  }

  @Override
  protected void command_MOVE(WidgetInfo component, String data) throws Exception {
    BorderLayoutInfo.getBorderData(component).setPosition(data);
  }

  @Override
  protected void command_ADD(WidgetInfo component, String data) throws Exception {
    m_layout.command_MOVE(component, null);
    BorderLayoutInfo.getBorderData(component).setPosition(data);
  }
}
