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
package com.google.gdt.eclipse.designer.gwtext.model.layout.assistant;

import com.google.gdt.eclipse.designer.gwtext.model.layout.RowLayoutDataInfo;

import org.eclipse.wb.core.model.ObjectInfo;
import org.eclipse.wb.internal.core.utils.ui.GridLayoutFactory;

import org.eclipse.swt.widgets.Composite;

import java.util.List;

/**
 * Assistant for GWT-Ext {@link RowLayoutDataInfo}.
 * 
 * @author sablin_aa
 * @coverage GWTExt.model.layout.assistant
 */
public final class RowLayoutDataAssistantPage extends LayoutDataAssistantPage {
  public RowLayoutDataAssistantPage(Composite parent, List<ObjectInfo> selection) {
    super(parent, selection);
    GridLayoutFactory.create(this).columns(2);
    addIntegerProperty(this, "height(int)");
    // do not work addStringProperty(this, "height(java.lang.String)", "height(String)");
  }
}