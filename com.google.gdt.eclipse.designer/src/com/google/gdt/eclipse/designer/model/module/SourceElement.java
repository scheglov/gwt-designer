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
package com.google.gdt.eclipse.designer.model.module;

import java.util.List;

/**
 * @author scheglov_ke
 * @coverage gwt.model.module
 */
public class SourceElement extends AbstractModuleElement {
  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public SourceElement() {
    super("source");
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Path
  //
  ////////////////////////////////////////////////////////////////////////////
  public void setPath(String path) {
    setAttribute("path", path);
  }

  public String getPath() {
    return getAttribute("path");
  }

  public List<ExcludeElement> getExcludeElements() {
    return getChildren(ExcludeElement.class);
  }
}
