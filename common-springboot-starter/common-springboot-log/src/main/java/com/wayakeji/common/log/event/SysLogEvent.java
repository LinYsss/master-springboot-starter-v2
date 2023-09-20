/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wayakeji.common.log.event;

import com.wayakeji.common.api.entity.AuthorityLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author 系统日志事件
 * ApplicationContext具有发布事件的能力。这是因为该接口继承了ApplicationEventPublisher接口。Spring中与事件有关的接口和类主要包括ApplicationEvent、ApplicationListener。
 * 定义一个事件的类需要继承ApplicationEvent或者ApplicationContextEvent抽象类，该抽象类中只有一个构造函数，并 且带有一个Object类型的参数作为事件源，并且该事件源不能为null，因此我们需要在自己的构造函数中执行super(Object)。
 *
 * 针对一种事件，可能需要特定的监听器，因此，监听器需要实现ApplicationListener接口。当监听器接收到一个事件的时候，就会执行它的 onApplicationEvent()方法。由于Spring IoC中的事件模型是一种简单的、粗粒度的监听模型，当有一个事件到达时，所有的监听器都会接收到，并且作出响应，如果希望只针对某些类型进行监听，需要 在代码中进行控制。
 *
 * 至此便完成了事件的发布，当ApplicationContext接收到事件后，事件的广播是Spring内部给我们做的，不需要了解具体的细节。其实在 Spring读取配置文件之后，利用反射，将所有实现ApplicationListener的Bean找出来，注册为容器的事件监听器。当接收到事件的 时候，Spring会逐个调用事件监听器。剩下要做的就是在配置文件中配置监听器
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(AuthorityLog source) {
		super(source);
	}

}
