/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2013 Six Dimensions
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sixdimensions.wcm.cq.sampleintegration;

import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;
import org.apache.sling.testing.tools.sling.SlingTestBase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example of a simple integration test for testing Apache Sling
 * functionality.
 * 
 * @author dklco
 */
public class SampleIT extends SlingTestBase {

	/**
	 * The SLF4J Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(SampleIT.class);

	/**
	 * The SlingClient can be used to interact with the repository when it is
	 * started. By retrieving the information for the Server URL, username and
	 * password, the Sling instance will be automatically started.
	 */
	private SlingClient slingClient = new SlingClient(this.getServerBaseUrl(),
			this.getServerUsername(), this.getServerPassword());

	/**
	 * Execute before the actual test, this will be used to setup the test data
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		log.info("init");

		log.info("Creating testing component...");
		if (!slingClient.exists("/apps/test")) {
			slingClient.mkdir("/apps/test");
		}
		if (!slingClient.exists("/apps/test/sample-test")) {
			slingClient.mkdir("/apps/test/sample-test");
		}
		slingClient.upload("/apps/test/sample-test/sample-test.jsp",
				SampleIT.class.getClassLoader()
						.getResourceAsStream("sample-test.jsp"), -1, true);
		log.info(getRequestExecutor()
				.execute(
						getRequestBuilder().buildGetRequest(
								"/apps/test/sample-test.3.json"))
				.assertStatus(200).getContent());

		log.info("Creating testing content...");
		if (slingClient.exists("/content/test/sample-test")) {
			slingClient.delete("/content/test/sample-test");
		}
		slingClient.createNode("/content/test/sample-test", "jcr:primaryType",
				"nt:unstructured", "sling:resourceType", "test/sample-test");

		log.info(getRequestExecutor()
				.execute(
						getRequestBuilder().buildGetRequest(
								"/content/test/sample-test.json"))
				.assertStatus(200).getContent());

		log.info("Initialization successful");
	}

	/**
	 * The actual test, will be executed once the Sling instance is started and
	 * the setup is complete.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSample() throws Exception {
		log.info("testSample");

		log.info("Executing content check");

		RequestExecutor result = getRequestExecutor().execute(
				getRequestBuilder().buildGetRequest(
						"/content/test/sample-test.html"));
		log.info(result.getContent());
		result.assertStatus(200).assertContentContains("All Tests Succeeded");

		log.info("testSample - TEST SUCCESSFUL");
	}
}
