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

package com.liferay.portalweb.socialofficehome.tasks.task.addtaskstaskassignedtomemultiple;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class AddTasksTask3AssignedToMeTest extends BaseTestCase {
	public void testAddTasksTask3AssignedToMe() throws Exception {
		selenium.open("/user/joebloggs/home/");
		selenium.getEval("window.Liferay.fire('initDockbar');");

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//nav/ul/li[1]/a/span")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertEquals(RuntimeVariables.replace("Home"),
			selenium.getText("//nav/ul/li[1]/a/span"));
		selenium.clickAt("//div[2]/div[1]/ul/li[5]/a",
			RuntimeVariables.replace("Tasks"));
		selenium.waitForPageToLoad("30000");
		selenium.getEval("window.Liferay.fire('initDockbar');");
		assertEquals(RuntimeVariables.replace("Tasks"),
			selenium.getText("//h1/span[2]"));
		assertEquals(RuntimeVariables.replace("Task Description"),
			selenium.getText("//td[1]/div[1]/a"));
		assertEquals(RuntimeVariables.replace("Task2 Description"),
			selenium.getText("//tr[4]/td[1]/div[1]/a"));
		assertEquals(RuntimeVariables.replace("Add Task"),
			selenium.getText("link=Add Task"));
		selenium.clickAt("link=Add Task", RuntimeVariables.replace("Add Task"));

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//h1/span")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertEquals(RuntimeVariables.replace("Add Task"),
			selenium.getText("//h1/span"));

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("//td[2]/input")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.type("//td[2]/input",
			RuntimeVariables.replace("Task3 Description"));
		selenium.select("//select", RuntimeVariables.replace("label=Joe Bloggs"));
		selenium.select("//tr[4]/td[2]/select",
			RuntimeVariables.replace("label=Normal"));
		assertTrue(selenium.isChecked("//span/input[2]"));
		selenium.clickAt("//input[@value='Save']",
			RuntimeVariables.replace("Save"));

		for (int second = 0;; second++) {
			if (second >= 90) {
				fail("timeout");
			}

			try {
				if (RuntimeVariables.replace("Task3 Description")
										.equals(selenium.getText("//h1/span"))) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		assertEquals(RuntimeVariables.replace("Task3 Description"),
			selenium.getText("//h1/span"));
		assertEquals(RuntimeVariables.replace("Assigned to Joe Bloggs"),
			selenium.getText("//div[2]/div[2]/div[1]"));
	}
}