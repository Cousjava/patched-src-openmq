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
 */ 

package com.sun.messaging.jmq.admin.apps.broker;

import java.util.Properties;

/**
 * This class is sub classed by imqcmd (BrokerCmdException) 
 * and imqbridgemgr (BridgeCmdException) 
 *
 * Please place individual admin tool specific references in its subclass 
 *
 * This exception is thrown when problems are
 * encountered when validating the information
 * that is provided to execute commands. Examples
 * of errors include:
 * <UL>
 * <LI>bad command type
 * <LI>missing mandatory values
 * </UL>
 *
 * <P>
 * The information that is provided by the user is encapsulated
 * in a BrokerCmdProperties object. This exception will
 * contain a BrokerCmdProperties object to encapsulate
 * the erroneous information.
 **/

public class CommonCmdException extends Exception  {

    /**************************************************************************
     * use integer >= 10000, each subclass should use different integer ranges
     **************************************************************************/
    public static final int		NO_CMD_SPEC		           = 10000;
    public static final int		BAD_CMD_SPEC		       = 10001;
    public static final int		CMDARG_NOT_SPEC		       = 10002;
    public static final int		BAD_CMDARG_SPEC		       = 10003;
    public static final int		INVALID_RECV_TIMEOUT_VALUE = 10004;
    public static final int		INVALID_NUM_RETRIES_VALUE  = 10005;
    public static final int		READ_PASSFILE_FAIL		   = 10006;
    public static final int		INVALID_TIME			   = 10007;
     public static final int     INVALID_INTEGER_VALUE     = 10008;


    /**
     * Props object encapsulating the user specified options/commands.
     **/
    private Properties cmdProps;
    private String validCmdArgs[];
    private String validAttrs[];
    private String badAttr;
    private String badValue;
    private int type;
    // this is a convenient storage place for a single value
    private String errorString = null;
    private Exception           linkedEx;

    /**
     * Constructs an BrokerCmdException
     */ 
    public CommonCmdException() {
        super();
        cmdProps = null;
    }

    /** 
     * Constructs an BrokerCmdException with type
     *
     * @param  type       type of exception 
     **/
    public CommonCmdException(int type) {
        super();
        cmdProps = null;
	this.type = type;
    }

    /** 
     * Constructs an BrokerCmdException with reason
     *
     * @param  reason        a description of the exception
     **/
    public CommonCmdException(String reason) {
        super(reason);
        cmdProps = null;
    }

    /**
     * Gets the properties object that encapsulates the user specified
     * options/commands.
     *
     * @return the properties object that encapsulates the user 
     *		specified options/commands.
     **/
    public Properties getProperties() {
        return (cmdProps);
    }

    /**
     * Sets the properties object that encapsulates the user specified
     * options/commands.
     *
     * @param p		the properties object that encapsulates the user 
     *			specified options/commands.
     **/
    public synchronized void setProperties(Properties p) {
        cmdProps = p;
    }

    /**
     * Gets the type of exception.
     *
     * @return the exception type.
     **/
    public synchronized int getType() {
	return (type);
    }

    /**
     * Sets the valid cmd args - to be used for exceptions of type
     * BAD_CMDARG_SPEC when printing out the error message.
     *
     * @param validCmdArgs	the array of Strings that contains
     *				the valid command arguments for
     *				the command specified in the
     *				BrokerCmdProperties object.
     **/
    public void setValidCmdArgs(String validCmdArgs[]) {
	this.validCmdArgs = validCmdArgs;
    }

    /**
     * Gets the valid cmd args - to be used for exceptions of type
     * BAD_CMDARG_SPEC when printing out the error message.
     *
     * @return	The array of Strings that contains
     *		the valid command arguments for
     *		the command specified in the
     *		BrokerCmdProperties object.
     **/
    public String[] getValidCmdArgs() {
	return (validCmdArgs);
    }

    /**
     * Sets the valid attrs - to be used for exceptions of type
     * BAD_ATTR_SPEC_* when printing out the error message.
     *
     * @param validAttrs	the array of Strings that contains
     *				the valid attributes for
     *				the command specified in the
     *				BrokerCmdProperties object.
     **/
    public void setValidAttrs(String validAttrs[]) {
	this.validAttrs = validAttrs;
    }

    /**
     * Gets the valid attrs - to be used for exceptions of type
     * BAD_ATTR_SPEC_* when printing out the error message.
     *
     * @return	The array of Strings that contains
     *		the valid attributes for
     *		the command specified in the
     *		BrokerCmdProperties object.
     **/
    public String[] getValidAttrs() {
	return (validAttrs);
    }

    /**
     * Sets the bad attr that caused the BAD_ATTR_SPEC_* exception.
     * This will be used when printing out the error message.
     *
     * @param badAttr		the bad attribute causing the exception.
     **/
    public void setBadAttr(String badAttr)  {
	this.badAttr = badAttr;
    }

    /**
     * Gets the bad attr that caused the BAD_ATTR_SPEC_* exception.
     * This will be used when printing out the error message.
     *
     * @return	The bad attribute causing the exception.
     **/
    public String getBadAttr()  {
	return (badAttr);
    }

    /**
     * Sets the bad value that caused the exception.
     * This will be used when printing out the error message.
     *
     * @param badAttr		the bad value causing the exception.
     **/
    public void setBadValue(String badValue)  {
	this.badValue = badValue;
    }

    /**
     * Returns the bad value that caused the exception.
     * This will be used when printing out the error message.
     *
     * @return	The bad value causing the exception.
     **/
    public String getBadValue()  {
	return (badValue);
    }

    /**
     * Sets an error string.  This can be used to print a more explicit error message.
     **/
    public void setErrorString(String value) {
	this.errorString = value;
    }

    /**
     * Gets an error string.
     *
     * @return errorString
     **/
    public String getErrorString() {
	return (errorString);
    }

    /**
     * Set linked exception.
     *
     * @param Exception relevant to this one.
     **/
    public void setLinkedException(Exception ex)  {
        linkedEx = ex;
    }

    /**
     * Get linked exception.
     *
     * @return Exception relevant to this one.
     **/
    public Exception getLinkedException()  {
        return (linkedEx);
    }
}
