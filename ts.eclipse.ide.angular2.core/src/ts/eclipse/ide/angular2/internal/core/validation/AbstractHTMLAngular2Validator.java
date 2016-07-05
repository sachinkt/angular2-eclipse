/**
 *  Copyright (c) 2015-2016 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package ts.eclipse.ide.angular2.internal.core.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

import ts.eclipse.ide.angular2.core.Angular2Project;
import ts.eclipse.ide.angular2.core.utils.DOMUtils;

/**
 * Abstract class for custom Angular2 validator.
 *
 */
public class AbstractHTMLAngular2Validator {

	private IFile file;

	/**
	 * Cache the project of the given document if project has angular2 nature.
	 * 
	 * @param doc
	 */
	public void init(IStructuredDocument doc) {
		this.file = null;
		if (doc instanceof IDocument) {
			IFile file = DOMUtils.getFile((IDocument) doc);
			IProject project = file.getProject();
			if (Angular2Project.isAngular2Project(project)) {
				// project has angular2 nature, cache the file
				this.file = file;
			}
		}
	}

	/**
	 * Returns true if the project has angular2 nature and false otherwise.
	 * 
	 * @return true if the project has angular2 nature and false otherwise.
	 */
	protected boolean hasAngular2Nature() {
		return file != null;
	}

	/**
	 * Returns the HTML file which is validating.
	 * 
	 * @return the HTML file which is validating.
	 */
	public IFile getFile() {
		return file;
	}
}
