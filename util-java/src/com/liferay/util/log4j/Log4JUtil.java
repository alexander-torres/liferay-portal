/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.util.log4j;

import java.net.URL;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.xml.DOMConfigurator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Log4JUtil {

	public static void configureLog4J(URL url) {
		if (url == null) {
			return;
		}

		if (Logger.getRootLogger().getAllAppenders() instanceof
				NullEnumeration) {

			DOMConfigurator.configure(url);
		}
		else {
			Set<String> currentLoggerNames = new HashSet<String>();

			Enumeration<Logger> enu = LogManager.getCurrentLoggers();

			while (enu.hasMoreElements()) {
				Logger logger = enu.nextElement();

				currentLoggerNames.add(logger.getName());
			}

			try {
				SAXReader reader = new SAXReader();

				Document doc = reader.read(url);

				Element root = doc.getRootElement();

				Iterator<Element> itr = root.elements("category").iterator();

				while (itr.hasNext()) {
					Element category = itr.next();

					String name = category.attributeValue("name");
					String priority =
						category.element("priority").attributeValue("value");

					setLevel(name, priority);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setLevel(String name, String priority) {
		Logger logger = Logger.getLogger(name);

		logger.setLevel(Level.toLevel(priority));

		java.util.logging.Logger jdkLogger = java.util.logging.Logger.getLogger(
			name);

		jdkLogger.setLevel(_getJdkLevel(priority));
	}

	private static java.util.logging.Level _getJdkLevel(String priority) {
		if (priority.equalsIgnoreCase(Level.DEBUG.toString())) {
			return java.util.logging.Level.FINE;
		}
		else if (priority.equalsIgnoreCase(Level.ERROR.toString())) {
			return java.util.logging.Level.SEVERE;
		}
		else if (priority.equalsIgnoreCase(Level.WARN.toString())) {
			return java.util.logging.Level.WARNING;
		}
		else {
			return java.util.logging.Level.INFO;
		}
	}

}