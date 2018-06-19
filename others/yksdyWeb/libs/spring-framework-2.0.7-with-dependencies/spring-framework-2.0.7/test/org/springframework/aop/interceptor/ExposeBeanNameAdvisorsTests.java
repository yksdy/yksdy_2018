/*
 * Copyright 2002-2006 the original author or authors.
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

package org.springframework.aop.interceptor;

import junit.framework.TestCase;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.ITestBean;
import org.springframework.beans.TestBean;
import org.springframework.beans.factory.NamedBean;

/**
 * 
 * @author Rod Johnson
 *
 */
public class ExposeBeanNameAdvisorsTests extends TestCase {
	
	private class RequiresBeanNameBoundTestBean extends TestBean {
		private final String beanName;
	
		public RequiresBeanNameBoundTestBean(String beanName) {
			this.beanName = beanName;
		}
		
		public int getAge() {
			assertEquals(beanName, ExposeBeanNameAdvisors.getBeanName());
			return super.getAge();
		}
	}
	
	public void testNoIntroduction() {
		String beanName = "foo";
		TestBean target = new RequiresBeanNameBoundTestBean(beanName);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		pf.addAdvisor(ExposeBeanNameAdvisors.createAdvisorWithoutIntroduction(beanName));
		ITestBean proxy = (ITestBean) pf.getProxy();
		
		assertFalse("No introduction", proxy instanceof NamedBean);
		// Requires binding
		proxy.getAge();
	}
	
	public void testWithIntroduction() {
		String beanName = "foo";
		TestBean target = new RequiresBeanNameBoundTestBean(beanName);
		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		pf.addAdvisor(ExposeBeanNameAdvisors.createAdvisorIntroducingNamedBean(beanName));
		ITestBean proxy = (ITestBean) pf.getProxy();
		
		assertTrue("Introduction was made", proxy instanceof NamedBean);
		// Requires binding
		proxy.getAge();
		
		NamedBean nb = (NamedBean) proxy;
		assertEquals("Name returned correctly", beanName, nb.getBeanName());
	}

}
