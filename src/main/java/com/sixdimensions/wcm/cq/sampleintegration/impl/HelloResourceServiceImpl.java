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
package com.sixdimensions.wcm.cq.sampleintegration.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;

import com.sixdimensions.wcm.cq.sampleintegration.HelloResourceService;

/**
 * Implementation of the HelloResourceService
 * 
 * @author dklco
 * @see com.sixdimensions.wcm.cq.sampleintegration.HelloResourceService
 */
@Service(value = HelloResourceService.class)
@Component(description = "Greets resources", immediate = true)
public class HelloResourceServiceImpl implements HelloResourceService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdimensions.wcm.cq.sampleintegration.HelloResourceService#sayHello
	 * (org.apache.sling.api.resource.Resource)
	 */
	public String sayHello(Resource resource) {
		return "Hello " + resource.getName() + "!";
	}

}
