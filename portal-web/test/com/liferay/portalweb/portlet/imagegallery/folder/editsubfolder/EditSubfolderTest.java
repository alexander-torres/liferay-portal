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

package com.liferay.portalweb.portlet.imagegallery.folder.editsubfolder;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class EditSubfolderTest extends BaseTestCase {
	public void testEditSubfolder() throws Exception {
		selenium.open("/web/guest/home/");
		selenium.getEval("window.Liferay.fire('initDockbar');");

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Image Gallery Test Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.clickAt("link=Image Gallery Test Page",
			RuntimeVariables.replace("Image Gallery Test Page"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace("MG Folder Name"),
			selenium.getText("//span[@class='image-title']"));
		selenium.clickAt("//span[@class='image-title']",
			RuntimeVariables.replace("MG Folder Name"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace("MG Subfolder Name"),
			selenium.getText("//span[@class='image-title']"));
		selenium.clickAt("//span[@class='image-title']",
			RuntimeVariables.replace("MG Subfolder Name"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace("Edit"),
			selenium.getText(
				"//div[contains(@class,'lfr-component lfr-menu-list')]/ul/li[1]/a"));
		selenium.click(RuntimeVariables.replace(
				"//div[contains(@class,'lfr-component lfr-menu-list')]/ul/li[1]/a"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		selenium.type("//input[@id='_31_name']",
			RuntimeVariables.replace("MG Subfolder Name Edit"));
		selenium.type("//textarea[@id='_31_description']",
			RuntimeVariables.replace("MG Subfolder Description Edit"));
		selenium.clickAt("//input[@value='Save']",
			RuntimeVariables.replace("Save"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace(
				"Your request completed successfully."),
			selenium.getText("//div[@class='portlet-msg-success']"));
		assertEquals(RuntimeVariables.replace("MG Subfolder Name Edit"),
			selenium.getText("//div/h1/span"));
	}
}