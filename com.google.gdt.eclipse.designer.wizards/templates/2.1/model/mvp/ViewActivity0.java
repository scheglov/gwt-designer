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
package %packageName%;

import %placePackageName%.%placeName%;
import %viewPackageName%.%viewName%;
import %viewPackageName%.%viewName%Impl;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class %className% extends AbstractActivity implements %viewName%.Presenter {

	/**
	 * Sample property.
	 */
	private String name;

	public %className%(%placeName% place) {
		this.name = place.getName();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		%viewName% view = new %viewName%Impl();
		view.setName(name);
		view.setPresenter(this);
		containerWidget.setWidget(view.asWidget());
	}

	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}

	/**
	 * @see %viewName%.Presenter#goTo(Place)
	 */
	public void goTo(Place place) {
		// TODO
	}
}
