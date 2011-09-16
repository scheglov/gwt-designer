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
package com.google.gdt.eclipse.designer.mac;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
/**
 * Abstract layer for MacOSX native WebKit window support.
 * 
 * @author mitin_aa
 */
public interface IBrowserShellMacImpl {
	////////////////////////////////////////////////////////////////////////////
	//
	// Visual data  methods
	//
	////////////////////////////////////////////////////////////////////////////
	long create(Object callback);
	void release(long handle);
	void setVisible(long handle, boolean visible);
	void setUrl(long handle, String url);
	void setBounds(long handle, Rectangle bounds);
	Rectangle getBounds(long handle);
	Rectangle computeTrim(long handle, Rectangle trim);
	Image createBrowserScreenshot(long handle) throws Exception;
	void showAsPreview(long handle);
}
