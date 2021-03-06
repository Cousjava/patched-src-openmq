/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2000-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 * @(#)MessageQueue.java	1.4 06/27/07
 */ 

package com.sun.messaging.jmq.jmsclient;

/**
 *
 * Interface to store and retrieve mq client messages in the memory.
 *
 */

public interface MessageQueue {

    /**
     * get the number of elements in the queue.
     * @return the number of elements in the queue.
     */
     public int size();

     /**
      * check if the queue size is empty.
      * @return true if the queue size is empty.
      */
     public boolean isEmpty();

    /**
     * Clears all elements stored in the queue
     **/
    public void clear ();

    /**
     * Enqueues the specified object in the queue.
     * @param nobj new object to be enqueued
     */
    public void enqueue(Object nobj);
    
    /**
     * Adds the specified object to the front of the queue.
     * @param nobj new object to be added to the front of the queue
     */
    public void enqueueFirst(Object nobj);

    /**
     * Dequeues an element from the queue.
     * @return dequeued object, or null if empty queue
    */
    public Object dequeue();

    /**
     * Get all elements queue and return as an array
     * of objects.
     *
     * @return an array of objects in the queue.
     */
    public Object[] toArray();

    /**
     * Remove the specified obj from the queue.
     * @param obj the object to be removed in the queue.
     * @return if the specified object was removed.
     */
    public boolean remove (Object obj);
}
