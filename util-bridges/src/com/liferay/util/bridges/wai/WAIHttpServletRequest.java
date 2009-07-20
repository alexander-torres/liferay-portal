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

package com.liferay.util.bridges.wai;

import com.liferay.portal.kernel.util.StringPool;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class WAIHttpServletRequest extends HttpServletRequestWrapper {

	public WAIHttpServletRequest(
		HttpServletRequest request, String contextPath, String pathInfo,
		String queryString, Map<String, String[]> params) {

		super(request);

		_contextPath = contextPath;
		_pathInfo = pathInfo;
		_queryString = queryString;
		_params = params;
	}

	public String getContextPath() {
		return _contextPath;
	}

	public String getPathInfo() {
		return super.getPathInfo();
	}

	public String getQueryString() {
		return _queryString;
	}

	public String getRequestURI() {
		StringBuilder sb = new StringBuilder();

		sb.append(getContextPath());
		sb.append(_pathInfo);

		if (getQueryString().trim().length() > 0) {
			sb.append(StringPool.QUESTION);
			sb.append(getQueryString());
		}

		return sb.toString();
	}

	public StringBuffer getRequestURL() {
		return new StringBuffer(getRequestURI());
	}

	public Map<String, String[]> getParameterMap() {
		return _params;
	}

	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(_params.keySet());
	}

	public String getParameter(String key) {
		String[] values = _params.get(key);

		if (values == null) {
			return null;
		}

		return values[0];
	}

	public String[] getParameterValues(String key) {
		return _params.get(key);
	}

	private String _contextPath;
	private String _pathInfo;
	private String _queryString;
	private Map<String, String[]> _params;

}