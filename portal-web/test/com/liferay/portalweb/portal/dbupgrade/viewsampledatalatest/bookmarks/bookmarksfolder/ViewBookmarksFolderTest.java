/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.portal.dbupgrade.viewsampledatalatest.bookmarks.bookmarksfolder;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewBookmarksFolderTest extends BaseTestCase {
	public void testViewBookmarksFolder() throws Exception {
		selenium.open("/web/bookmarks-folder-community/");
		selenium.getEval("window.Liferay.fire('initDockbar');");

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Bookmarks Entry Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Bookmarks Entry Page",
			RuntimeVariables.replace("Bookmarks Entry Page"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace("Bookmarks Folder Name"),
			selenium.getText("//a/strong"));
		assertTrue(selenium.isPartialText("//tr[3]/td[1]/a",
				"Bookmarks Folder Description"));
		selenium.clickAt("//a/strong",
			RuntimeVariables.replace("Bookmarks Folder Name"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace("Bookmarks Folder Name"),
			selenium.getText("//h1[@class='header-title']/span"));
	}
}