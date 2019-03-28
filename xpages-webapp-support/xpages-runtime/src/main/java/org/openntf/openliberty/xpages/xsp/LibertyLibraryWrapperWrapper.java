/**
 * Copyright © 2019 Jesse Gallagher
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
package org.openntf.openliberty.xpages.xsp;

import org.openntf.openliberty.xpages.wrapper.LibertyClassLoaderWrapper;

import com.ibm.xsp.library.LibraryWrapper;

public class LibertyLibraryWrapperWrapper extends LibraryWrapper {
	@SuppressWarnings("deprecation")
	public LibertyLibraryWrapperWrapper(LibraryWrapper delegate) {
		super(delegate.getLibraryId(), delegate.getWrapped());
	}

	@Override
	public ClassLoader getClassLoader() {
		return new LibertyClassLoaderWrapper(super.getClassLoader());
	}
}