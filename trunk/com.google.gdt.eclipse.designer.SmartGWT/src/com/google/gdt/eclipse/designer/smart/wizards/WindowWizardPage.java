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
package com.google.gdt.eclipse.designer.smart.wizards;

import com.google.gdt.eclipse.designer.smart.Activator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 * Wizard page for <code>com.smartgwt.client.widgets.Window</code>.
 * 
 * @author sablin_aa
 * @coverage SmartGWT.wizard
 */
public final class WindowWizardPage extends SmartGwtWizardPage {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public WindowWizardPage() {
    setTitle("Create Window");
    setImageDescriptor(Activator.getImageDescriptor("wizards/banner.png"));
    setDescription("Create empty Window");
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Configuration
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void initTypePage(IJavaElement elem) {
    super.initTypePage(elem);
    setSuperClass("com.smartgwt.client.widgets.Window", false);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Type creation
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void createTypeMembers(IType newType, ImportsManager imports, IProgressMonitor monitor)
      throws CoreException {
    InputStream stream = getTemplate2("Window.jvt");
    try {
      fillTypeFromTemplate(newType, imports, monitor, stream);
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }
}