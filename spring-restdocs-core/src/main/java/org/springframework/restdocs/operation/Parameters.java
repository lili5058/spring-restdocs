/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.restdocs.operation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;

/**
 * The parameters received in a request.
 *
 * @author Andy Wilkinson
 */
@SuppressWarnings("serial")
public class Parameters extends LinkedMultiValueMap<String, String> {

	/**
	 * Converts the parameters to a query string suitable for use in a URI or the body of
	 * a form-encoded request.
	 *
	 * @return the query string
	 */
	public String toQueryString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, List<String>> entry : entrySet()) {
			for (String value : entry.getValue()) {
				if (sb.length() > 0) {
					sb.append("&");
				}
				sb.append(urlEncodeUTF8(entry.getKey())).append('=')
						.append(urlEncodeUTF8(value));
			}
		}
		return sb.toString();
	}

	private static String urlEncodeUTF8(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			throw new IllegalStateException("Unable to URL encode " + s + " using UTF-8",
					ex);
		}
	}

}
