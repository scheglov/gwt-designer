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
package com.google.gdt.eclipse.designer.gxt.model.widgets.menu;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gdt.eclipse.designer.gxt.model.widgets.ComponentInfo;

import org.eclipse.wb.core.model.JavaInfo;
import org.eclipse.wb.core.model.association.AssociationObject;
import org.eclipse.wb.core.model.association.AssociationObjects;
import org.eclipse.wb.internal.core.model.JavaInfoUtils;
import org.eclipse.wb.internal.core.model.clipboard.JavaInfoMemento;
import org.eclipse.wb.internal.core.model.creation.ConstructorCreationSupport;
import org.eclipse.wb.internal.core.model.menu.IMenuPolicy;
import org.eclipse.wb.internal.core.utils.execution.ExecutionUtils;
import org.eclipse.wb.internal.core.utils.execution.RunnableObjectEx;

import java.util.List;

/**
 * Implementation of {@link IMenuPolicy} for {@link MenuInfo}.
 * 
 * @author scheglov_ke
 * @coverage ExtGWT.model
 */
final class MenuPolicyImpl implements IMenuPolicy {
  private final MenuInfo m_menu;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  MenuPolicyImpl(MenuInfo menu) {
    m_menu = menu;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Validation
  //
  ////////////////////////////////////////////////////////////////////////////
  public boolean validateCreate(Object newObject) {
    return isValidObjectType(newObject);
  }

  @SuppressWarnings("unchecked")
  public boolean validatePaste(final Object mementoObject) {
    return ExecutionUtils.runObjectLog(new RunnableObjectEx<Boolean>() {
      public Boolean runObject() throws Exception {
        if (mementoObject instanceof List) {
          List<JavaInfoMemento> mementos = (List<JavaInfoMemento>) mementoObject;
          for (JavaInfoMemento memento : mementos) {
            JavaInfo component = memento.create(m_menu);
            if (!isValidObjectType(component)) {
              return false;
            }
          }
          return true;
        }
        return false;
      }
    }, false);
  }

  public boolean validateMove(Object object) {
    if (isValidObjectType(object)) {
      ComponentInfo item = (ComponentInfo) object;
      // don't move item on its child menu
      return !item.isParentOf(m_menu);
    }
    return false;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Operations
  //
  ////////////////////////////////////////////////////////////////////////////
  public void commandCreate(Object newObject, Object nextObject) throws Exception {
    ComponentInfo newItem;
    ComponentInfo nextItem = (ComponentInfo) nextObject;
    if (newObject instanceof MenuInfo) {
      newItem =
          (ComponentInfo) JavaInfoUtils.createJavaInfo(
              m_menu.getEditor(),
              "com.extjs.gxt.ui.client.widget.menu.MenuItem",
              new ConstructorCreationSupport());
      commandCreate(newItem, nextItem);
      // set Menu
      MenuInfo newMenu = (MenuInfo) newObject;
      JavaInfoUtils.add(
          newMenu,
          AssociationObjects.invocationChild("%parent%.setSubMenu(%child%)", false),
          newItem,
          null);
    } else {
      newItem = (ComponentInfo) newObject;
      JavaInfoUtils.add(newItem, getNewItemAssociation(), m_menu, nextItem);
    }
    // schedule selection
    m_menu.getBroadcastObject().select(ImmutableList.of(newItem));
  }

  @SuppressWarnings("unchecked")
  public List<ComponentInfo> commandPaste(Object mementoObject, Object nextObject) throws Exception {
    List<ComponentInfo> pastedObjects = Lists.newArrayList();
    List<JavaInfoMemento> mementos = (List<JavaInfoMemento>) mementoObject;
    for (JavaInfoMemento memento : mementos) {
      ComponentInfo newItem = (ComponentInfo) memento.create(m_menu);
      commandCreate(newItem, nextObject);
      memento.apply();
      pastedObjects.add(newItem);
    }
    return pastedObjects;
  }

  public void commandMove(Object object, Object nextObject) throws Exception {
    ComponentInfo item = (ComponentInfo) object;
    ComponentInfo nextItem = (ComponentInfo) nextObject;
    JavaInfoUtils.move(item, getNewItemAssociation(), m_menu, nextItem);
    // schedule selection
    MenuUtils.setSelectingItem(item);
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Utils
  //
  ////////////////////////////////////////////////////////////////////////////
  private static boolean isValidObjectType(Object object) {
    return object instanceof ComponentInfo && !(object instanceof MenuBarItemInfo);
  }

  private AssociationObject getNewItemAssociation() {
    return AssociationObjects.invocationChild("%parent%.add(%child%)", false);
  }
}