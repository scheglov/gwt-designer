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
package com.google.gdt.eclipse.designer.hosted.tdz;

import com.google.gdt.eclipse.designer.hosted.IHostedModeSupport;
import com.google.gdt.eclipse.designer.hosted.IHostedModeSupportFactory;
import com.google.gdt.eclipse.designer.hosted.IModuleDescription;

/**
 * Implementation for {@link IHostedModeSupportFactory} for GWT 2.0.
 * 
 * @author mitin_aa
 */
public class HostedModeSupportFactory implements IHostedModeSupportFactory {
  public IHostedModeSupport create(String version,
      ClassLoader parentClassLoader,
      IModuleDescription moduleDescription) throws Exception {
    if ("2.0".equals(version) || "2.1".equals(version)) {
      return new HostedModeSupport(parentClassLoader, moduleDescription);
    }
    return null;
  }
}