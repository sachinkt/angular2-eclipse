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
package ts.eclipse.ide.angular2.internal.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import ts.eclipse.ide.angular2.ui.Angular2UIPlugin;

/**
 * Utility class to handle image resources.
 */
public class Angular2ImageResource {
	// the image registry
	private static ImageRegistry imageRegistry;

	// map of image descriptors since these
	// will be lost by the image registry
	private static Map<String, ImageDescriptor> imageDescriptors;

	// base urls for images
	private static URL ICON_BASE_URL;

	private static final String URL_OBJ = "full/obj16/";
	private static final String ELCL_OBJ = "full/elcl16/";

	// General Object Images
	public static final String IMG_ANGULAR2 = "angular2";

	static {
		try {
			String pathSuffix = "icons/";
			ICON_BASE_URL = Angular2UIPlugin.getDefault().getBundle().getEntry(pathSuffix);
		} catch (Exception e) {
			Trace.trace(Trace.SEVERE, "Images error", e);
		}
	}

	/**
	 * Cannot construct an ImageResource. Use static methods only.
	 */
	private Angular2ImageResource() {
		// do nothing
	}

	/**
	 * Dispose of element images that were created.
	 */
	protected static void dispose() {
		// do nothing
	}

	/**
	 * Return the image with the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return org.eclipse.swt.graphics.Image
	 */
	public static Image getImage(String key) {
		return getImage(key, null);
	}

	/**
	 * Return the image with the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return org.eclipse.swt.graphics.Image
	 */
	public static Image getImage(String key, String keyIfImageNull) {
		if (imageRegistry == null)
			initializeImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null) {
			if (keyIfImageNull != null) {
				return getImage(keyIfImageNull, null);
			}
			imageRegistry.put(key, ImageDescriptor.getMissingImageDescriptor());
			image = imageRegistry.get(key);
		}
		return image;
	}

	/**
	 * Return the image descriptor with the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return org.eclipse.jface.resource.ImageDescriptor
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		if (imageRegistry == null)
			initializeImageRegistry();
		ImageDescriptor id = imageDescriptors.get(key);
		if (id != null)
			return id;

		return ImageDescriptor.getMissingImageDescriptor();
	}

	/**
	 * Initialize the image resources.
	 */
	protected static void initializeImageRegistry() {
		imageRegistry = Angular2UIPlugin.getDefault().getImageRegistry();
		imageDescriptors = new HashMap<String, ImageDescriptor>();

		// load general object images
		registerImage(IMG_ANGULAR2, URL_OBJ + IMG_ANGULAR2 + ".png");
	}

	/**
	 * Register an image with the registry.
	 * 
	 * @param key
	 *            java.lang.String
	 * @param partialURL
	 *            java.lang.String
	 */
	private static void registerImage(String key, String partialURL) {
		try {
			ImageDescriptor id = ImageDescriptor.createFromURL(new URL(ICON_BASE_URL, partialURL));
			imageRegistry.put(key, id);
			imageDescriptors.put(key, id);
		} catch (Exception e) {
			Trace.trace(Trace.SEVERE, "Error registering image " + key + " from " + partialURL, e);
		}
	}

}