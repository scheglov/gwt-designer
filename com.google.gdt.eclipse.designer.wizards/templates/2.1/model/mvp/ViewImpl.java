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
package %packageName%;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Sample implementation of {@link %viewName%}.
 */
public class %className% extends FlowPanel implements %viewName% {
	private Presenter listener;

	private Button button;

	public %className%() {

		button = new Button("button");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Place newPlace = null;
				// TODO
				listener.goTo(newPlace);
			}
		});
		add(button);

	}

	@Override
	public void setName(String name) {
		button.setHTML(name);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
