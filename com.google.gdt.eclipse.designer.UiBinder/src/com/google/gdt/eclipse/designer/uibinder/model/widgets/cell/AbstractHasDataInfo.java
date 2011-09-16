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
package com.google.gdt.eclipse.designer.uibinder.model.widgets.cell;

import com.google.gdt.eclipse.designer.uibinder.model.widgets.WidgetInfo;

import org.eclipse.wb.internal.core.xml.model.EditorContext;
import org.eclipse.wb.internal.core.xml.model.creation.CreationSupport;
import org.eclipse.wb.internal.core.xml.model.description.ComponentDescription;

/**
 * Model for {@link com.google.gwt.user.cellview.client.AbstractHasData} in GWT UiBinder.
 * 
 * @author scheglov_ke
 * @coverage GWT.UiBinder.model
 */
public class AbstractHasDataInfo extends WidgetInfo {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public AbstractHasDataInfo(EditorContext context,
      ComponentDescription description,
      CreationSupport creationSupport) throws Exception {
    super(context, description, creationSupport);
  }
}
