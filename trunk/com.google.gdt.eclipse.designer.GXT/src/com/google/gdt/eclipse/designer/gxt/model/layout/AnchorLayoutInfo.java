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
package com.google.gdt.eclipse.designer.gxt.model.layout;

import com.google.gdt.eclipse.designer.gxt.model.layout.assistant.AnchorLayoutAssistant;
import com.google.gdt.eclipse.designer.model.widgets.WidgetInfo;

import org.eclipse.wb.internal.core.model.creation.CreationSupport;
import org.eclipse.wb.internal.core.model.description.ComponentDescription;
import org.eclipse.wb.internal.core.utils.ast.AstEditor;

/**
 * Model for <code>AnchorLayout</code>.
 * 
 * @author scheglov_ke
 * @coverage ExtGWT.model.layout
 */
public class AnchorLayoutInfo extends LayoutInfo {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public AnchorLayoutInfo(AstEditor editor,
      ComponentDescription description,
      CreationSupport creationSupport) throws Exception {
    super(editor, description, creationSupport);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Initialize
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void initializeLayoutAssistant() {
    new AnchorLayoutAssistant(this);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Widget notifications
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void onWidgetAddAfter(WidgetInfo widget) throws Exception {
    super.onWidgetAddAfter(widget);
    applyDefaultAnchorSize(widget);
  }

  protected void applyDefaultAnchorSize(WidgetInfo widget) throws Exception {
    getAnchorData(widget).setAnchorWidth("100%");
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // LayoutData
  //
  ////////////////////////////////////////////////////////////////////////////
  /**
   * @return {@link AnchorDataInfo} associated with given {@link WidgetInfo}.
   */
  public static AnchorDataInfo getAnchorData(WidgetInfo widget) {
    return (AnchorDataInfo) getLayoutData(widget);
  }
}