//
// ========================================================================
// Copyright (c) 1995-2020 Mort Bay Consulting Pty Ltd and others.
//
// This program and the accompanying materials are made available under
// the terms of the Eclipse Public License 2.0 which is available at
// https://www.eclipse.org/legal/epl-2.0
//
// This Source Code may also be made available under the following
// Secondary Licenses when the conditions for such availability set
// forth in the Eclipse Public License, v. 2.0 are satisfied:
// the Apache License v2.0 which is available at
// https://www.apache.org/licenses/LICENSE-2.0
//
// SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
// ========================================================================
//

package org.eclipse.jetty.websocket.servlet;

import org.eclipse.jetty.http.pathmap.PathSpec;
import org.eclipse.jetty.websocket.core.Configuration;
import org.eclipse.jetty.websocket.core.WebSocketExtensionRegistry;

public interface WebSocketServletFactory extends Configuration
{
    WebSocketExtensionRegistry getExtensionRegistry();

    void addMapping(String pathSpec, WebSocketCreator creator);

    /**
     * add a WebSocket mapping to a provided {@link WebSocketCreator}.
     * <p>
     * If mapping is added before this configuration is started, then it is persisted through
     * stop/start of this configuration's lifecycle.  Otherwise it will be removed when
     * this configuration is stopped.
     * </p>
     *
     * @param pathSpec the pathspec to respond on
     * @param creator the WebSocketCreator to use
     * @since 10.0
     */
    void addMapping(PathSpec pathSpec, WebSocketCreator creator);

    /**
     * Add a WebSocket mapping at PathSpec "/" for a creator which creates the endpointClass
     *
     * @param endpointClass the WebSocket class to use
     */
    void register(Class<?> endpointClass);

    /**
     * Add a WebSocket mapping at PathSpec "/" for a creator
     *
     * @param creator the WebSocketCreator to use
     */
    void setCreator(WebSocketCreator creator);

    /**
     * Returns the creator for the given path spec.
     *
     * @param pathSpec the pathspec to respond on
     * @return the websocket creator if path spec exists, or null
     */
    WebSocketCreator getMapping(PathSpec pathSpec);

    /**
     * Get the MappedResource for the given target path.
     *
     * @param target the target path
     * @return the MappedResource if matched, or null if not matched.
     */
    WebSocketCreator getMatch(String target);

    /**
     * Parse a PathSpec string into a PathSpec instance.
     * <p>
     * Recognized Path Spec syntaxes:
     * </p>
     * <dl>
     * <dt>{@code /path/to} or {@code /} or {@code *.ext} or {@code servlet|{spec}}</dt>
     * <dd>Servlet Syntax</dd>
     * <dt>{@code ^{spec}} or {@code regex|{spec}}</dt>
     * <dd>Regex Syntax</dd>
     * <dt>{@code uri-template|{spec}}</dt>
     * <dd>URI Template (see JSR356 and RFC6570 level 1)</dd>
     * </dl>
     *
     * @param rawSpec the raw path spec as String to parse.
     * @return the {@link PathSpec} implementation for the rawSpec
     */
    PathSpec parsePathSpec(String rawSpec);

    /**
     * Removes the mapping based on the given path spec.
     *
     * @param pathSpec the pathspec to respond on
     * @return true if underlying mapping were altered, false otherwise
     */
    boolean removeMapping(PathSpec pathSpec);
}
