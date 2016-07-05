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
package ts.eclipse.ide.angular2.internal.core.html;

import org.eclipse.core.resources.IFile;
import org.eclipse.wst.sse.core.internal.validate.ValidationMessage;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import ts.eclipse.ide.angular2.internal.core.Angular2CoreMessages;

/**
 * Property binding: ref-$name.
 *
 */
public class RefBindingCanonicalSyntax extends AbstractNgBindingType {

	public RefBindingCanonicalSyntax() {
		super("ref-");
	}

	@Override
	protected ValidationMessage validate(String name, IDOMElement target, String attrName, IFile file) {
		String tagName = target.getTagName();
		if (name.startsWith("-")) {
			return createValidationMessage(target, attrName, Angular2CoreMessages.RefDontAllow_error,
					ValidationMessage.ERROR);
		}
		return null;
	}

}
