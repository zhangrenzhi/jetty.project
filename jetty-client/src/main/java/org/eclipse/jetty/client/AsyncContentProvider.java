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

package org.eclipse.jetty.client;

import java.util.EventListener;

import org.eclipse.jetty.client.api.ContentProvider;

/**
 * A {@link ContentProvider} that notifies listeners that content is available.
 */
public interface AsyncContentProvider extends ContentProvider
{
    /**
     * @param listener the listener to be notified of content availability
     */
    public void setListener(Listener listener);

    /**
     * A listener that is notified of content availability
     */
    public interface Listener extends EventListener
    {
        /**
         * Callback method invoked when content is available
         */
        public void onContent();
    }
}
