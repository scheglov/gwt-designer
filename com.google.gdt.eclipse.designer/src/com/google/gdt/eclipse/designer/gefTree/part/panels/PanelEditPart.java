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
package com.google.gdt.eclipse.designer.gefTree.part.panels;

import com.google.gdt.eclipse.designer.gefTree.part.UIObjectEditPart;
import com.google.gdt.eclipse.designer.model.widgets.panels.PanelInfo;

import org.eclipse.wb.gef.core.EditPart;

/**
 * {@link EditPart} for {@link PanelInfo}.
 * 
 * @author scheglov_ke
 * @coverage gwt.gefTree
 */
public class PanelEditPart extends UIObjectEditPart {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public PanelEditPart(PanelInfo panel) {
    super(panel);
  }
}
