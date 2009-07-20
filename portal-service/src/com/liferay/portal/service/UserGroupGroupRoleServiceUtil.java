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

package com.liferay.portal.service;

public class UserGroupGroupRoleServiceUtil {
	public static void addUserGroupGroupRoles(long userGroupId, long groupId,
		long[] roleIds)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().addUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void addUserGroupGroupRoles(long[] userGroupIds,
		long groupId, long roleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().addUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static void deleteUserGroupGroupRoles(long userGroupId,
		long groupId, long[] roleIds)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteUserGroupGroupRoles(userGroupId, groupId, roleIds);
	}

	public static void deleteUserGroupGroupRoles(long[] userGroupIds,
		long groupId, long roleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteUserGroupGroupRoles(userGroupIds, groupId, roleId);
	}

	public static UserGroupGroupRoleService getService() {
		if (_service == null) {
			throw new RuntimeException("UserGroupGroupRoleService is not set");
		}

		return _service;
	}

	public void setService(UserGroupGroupRoleService service) {
		_service = service;
	}

	private static UserGroupGroupRoleService _service;
}