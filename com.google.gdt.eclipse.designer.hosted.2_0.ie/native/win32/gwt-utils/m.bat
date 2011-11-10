@rem ***************************************************************************
@rem Copyright 2011 Google Inc. All Rights Reserved.
@rem
@rem All rights reserved. This program and the accompanying materials
@rem are made available under the terms of the Eclipse Public License v1.0
@rem which accompanies this distribution, and is available at
@rem http://www.eclipse.org/legal/epl-v10.html
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem ***************************************************************************
@echo off
cl -DWIN32=1 -I..\jni -I. -MT -LDd -GX -Ox -YXstdafx.h -Gi -Zi swt-gwt-utils.cpp user32.lib gdi32.lib wininet.lib
del *.exp
del *.ilk
del *.lib
del *.obj
del *.pch
del *.pdb

